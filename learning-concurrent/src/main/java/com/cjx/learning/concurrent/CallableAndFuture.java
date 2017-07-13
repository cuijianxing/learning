package com.cjx.learning.concurrent;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by Aaron on 14-8-19.
 */
public class CallableAndFuture {
    public static void main(String[] args) {
        Callable<Integer> callable = () -> {
            Thread.sleep(7000);
            return new Random().nextInt(100);
        };
        FutureTask<Integer> future = new FutureTask<>(callable);
        new Thread(future).start();
        try {
            Thread.sleep(5000);// 可能做一些事情
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
