package com.cjx.learning.concurrent.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Aaron on 14-8-21.
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
//        ExecutorService threadPool = Executors.newFixedThreadPool(3);
//        //ExecutorService threadPool = Executors.newCachedThreadPool();
//        //ExecutorService threadPool = Executors.newSingleThreadExecutor();
//        for (int i = 1; i < 5; i++) {
//            final int taskID = i;
//            threadPool.execute(new Runnable() {
//                public void run() {
//                    for (int i = 1; i < 5; i++) {
//                        try {
//                            Thread.sleep(20);// 为了测试出效果，让每次任务执行都需要一定时间
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        System.out.println("第" + taskID + "次任务的第" + i + "次执行,执行线程为:" + Thread.currentThread().getName());
//                    }
//                }
//            });
//        }
//        threadPool.shutdown();// 任务执行完毕，关闭线程池

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 0L,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));
        System.out.println("active_count=" + threadPool.getActiveCount());
        System.out.println("core_pool_size=" + threadPool.getCorePoolSize());
        System.out.println("largest_pool_size=" + threadPool.getLargestPoolSize());
        System.out.println("maximum_pool_size=" + threadPool.getMaximumPoolSize());
        System.out.println("pool_size=" + threadPool.getPoolSize());
        System.out.println("task_count=" + threadPool.getTaskCount());
        System.out.println("queue=" + threadPool.getQueue().size());
        System.out.println("*****************************************");
        for (int i = 0; i < 20; i++) {
            final int num = i;
            try {
                threadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println("线程" + num + "开始执行");
                            int time = Math.abs((int) (Math.random() * 2000 + 1000000));
                            Thread.sleep(time);
                            System.out.println("线程" + num + "执行结束");
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                });
                Thread.sleep(1000);
                System.out.println("active_count=" + threadPool.getActiveCount());
                System.out.println("core_pool_size=" + threadPool.getCorePoolSize());
                System.out.println("largest_pool_size=" + threadPool.getLargestPoolSize());
                System.out.println("maximum_pool_size=" + threadPool.getMaximumPoolSize());
                System.out.println("pool_size=" + threadPool.getPoolSize());
                System.out.println("task_count=" + threadPool.getTaskCount());
                System.out.println("queue=" + threadPool.getQueue().size());
                System.out.println("*****************************************");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (RejectedExecutionException e) {
                System.out.println("rejected");
                System.out.println("active_count=" + threadPool.getActiveCount());
                System.out.println("core_pool_size=" + threadPool.getCorePoolSize());
                System.out.println("largest_pool_size=" + threadPool.getLargestPoolSize());
                System.out.println("maximum_pool_size=" + threadPool.getMaximumPoolSize());
                System.out.println("pool_size=" + threadPool.getPoolSize());
                System.out.println("task_count=" + threadPool.getTaskCount());
                System.out.println("queue=" + threadPool.getQueue().size());
                System.out.println("*****************************************");
            }
        }
    }

}
