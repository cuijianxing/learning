package com.cjx.learning.concurrent.basic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Aaron on 14-8-18.
 */
public class Test {

    public static void main(String[] args) {
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                int value = atomicInteger.getAndIncrement();
                System.out.println("线程[" + Thread.currentThread().getName() + "]获取值为：" + value);
            }).start();
        }
        System.out.println("最终的结果为：" + atomicInteger.get());
        final Counter counter = new Counter();
        long t1 = System.currentTimeMillis();
        Thread[] threads = new Thread[2];
        for (int i = 0; i < 2; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 250000000; j++) {
                    synchronized (counter) {
                        counter.increase();
                    }
                }
            });
            threads[i].start();
        }
        for (int i = 0; i < 2; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {

            }
        }
        System.out.println(counter.get() + " 耗时：" + (System.currentTimeMillis() - t1) + "毫秒");

        final int b = 0;

        Thread thread = new Thread(() -> System.out.println(10 / b));
        thread.setUncaughtExceptionHandler((t, e) -> {
            System.out.println("出现异常");
            e.printStackTrace();
        });
        thread.start();
    }

}

class Counter {
    int value = 0;

    public int get() {
        return value;
    }

    public void increase() {
        value++;
    }
}