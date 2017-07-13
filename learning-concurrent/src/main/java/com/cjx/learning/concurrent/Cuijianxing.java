package com.cjx.learning.concurrent;

/**
 * Created by Aaron on 14-8-12.
 */
public class Cuijianxing {

    public static void main(String[] args) {

        for (int i = 1; i <= 20; i++) {
            int[] arr = new int[64 * 1024 * 1024];
            long t1 = System.currentTimeMillis();
            for (int j = 0; j < arr.length; j += i) {
                arr[i] *= 3;
            }
            System.out.println("步长" + i + "耗时：" + (System.currentTimeMillis() - t1) + "毫秒");
        }

    }

}
