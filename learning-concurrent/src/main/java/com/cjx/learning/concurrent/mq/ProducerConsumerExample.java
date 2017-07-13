package com.cjx.learning.concurrent.mq;

/**
 * Created by Aaron on 15/6/1.
 */
public class ProducerConsumerExample {

    public static void main(String[] args) {
        Drop drop = new Drop();
        (new Thread(new Producer(drop))).start();
        (new Thread(new Consumer(drop))).start();
    }

}
