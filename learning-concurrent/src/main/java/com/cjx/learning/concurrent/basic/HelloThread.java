package com.cjx.learning.concurrent.basic;

/**
 * Created by cuijianxing on 16/6/12.
 */
public class HelloThread extends Thread {

    public void run() {
        System.out.println("Hello from a thread!");
    }

    public static void main(String args[]) {
        (new HelloThread()).start();
    }

}
