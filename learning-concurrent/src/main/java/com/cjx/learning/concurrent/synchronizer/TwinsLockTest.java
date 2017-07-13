package com.cjx.learning.concurrent.synchronizer;

import java.util.concurrent.locks.Lock;

/**
 * Created by Aaron on 14-8-8.
 */
public class TwinsLockTest {

    final Lock lock = new TwinsLock();

    class Worker extends Thread {

        Worker(String name) {
            setName(name);
        }

        public void run() {
            while (true) {
                lock.lock();
                try {
                    Thread.sleep(1000L);
                    System.out.println(getName());
                    Thread.sleep(1000L);
                } catch (Exception ex) {

                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        TwinsLockTest test = new TwinsLockTest();
        for (int i = 0; i < 10; i++) {
            Worker w = test.new Worker("thread-" + (i + 1));
            w.start();
        }

        new Thread() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(200L);
                        System.out.println();
                    } catch (Exception ex) {

                    }
                }
            }
        }.start();

        try {
            Thread.sleep(20000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
