package com.cjx.learning.concurrent.basic;

/**
 * Created by cuijianxing on 16/6/12.
 */
public class HelloRunnable implements Runnable {

    public void run() {
        System.out.println("Hello from a Runnable!");
    }

    public static void main(String args[]) {
        new Thread(new HelloRunnable()).start();
    }

}
