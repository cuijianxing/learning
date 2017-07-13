package com.cjx.learning.concurrent.mq;

import java.util.concurrent.CountDownLatch;

/**
 * 有界缓存
 * Created by Aaron on 14-8-19.
 */
public class BoundedBuffer {

    final Object[] items = new Object[100];//缓存队列
    int putIndex/*写索引*/, takeIndex/*读索引*/, count/*队列中存在的数据个数*/;

    public synchronized void put(Object x) {
        try {
            while (count == items.length)//如果队列满了,不能用if
                this.wait();//阻塞当前线程
            items[putIndex] = x;//赋值
            if (++putIndex == items.length)
                putIndex = 0;//如果写索引写到队列的最后一个位置了，那么置为0
            ++count;//个数++
            this.notifyAll();//唤醒其他被阻塞在等待池的线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized Object take() {
        try {
            while (count == 0)//如果队列为空，不能用if
                this.wait();//阻塞当前线程
            Object x = items[takeIndex];//取值
            if (++takeIndex == items.length)
                takeIndex = 0;//如果读索引读到队列的最后一个位置了，那么置为0
            --count;//个数--
            this.notifyAll();//唤醒其他被阻塞在等待池的线程
            return x;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        final BoundedBuffer buffer = new BoundedBuffer();
        final CountDownLatch start = new CountDownLatch(1);
        final CountDownLatch countDownLatch = new CountDownLatch(2000);
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                try {
                    start.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                buffer.put(Math.random() * Integer.MAX_VALUE);
                countDownLatch.countDown();
            }).start();
        }

        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                try {
                    start.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(buffer.take());
                countDownLatch.countDown();
            }).start();
        }
        start.countDown();
        long t1 = System.currentTimeMillis();
        try {
            countDownLatch.await();
            long t2 = System.currentTimeMillis();
            System.out.println("耗时：" + (t2 - t1) + "毫秒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
