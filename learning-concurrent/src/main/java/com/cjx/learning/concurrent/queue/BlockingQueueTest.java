package com.cjx.learning.concurrent.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedTransferQueue;

/**
 * Created by Aaron on 14-8-22.
 */
public class BlockingQueueTest {

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 3; ++i) {
			loop();
		}
	}

	private static void loop() throws InterruptedException {
		//final BlockingQueue<Object> queue = new LinkedBlockingQueue<>();
		final BlockingQueue<Object> queue = new LinkedTransferQueue<>();

		for (int i = 0; i < 10; ++i) {
			queue.put(i);
		}

		final int THREAD_COUNT = 50;
		final CountDownLatch startLatch = new CountDownLatch(1);
		final CountDownLatch endLatch = new CountDownLatch(THREAD_COUNT);

		for (int i = 0; i < THREAD_COUNT; ++i) {
			Thread thread = new Thread() {
				public void run() {
					try {
						startLatch.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					try {
						for (int i = 0; i < 1000 * 20; ++i) {
							Object item = queue.take();
							queue.put(item);
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						endLatch.countDown();
					}
				}
			};
			thread.start();
		}

		long startMillis = System.currentTimeMillis();
		startLatch.countDown();
		endLatch.await();
		long millis = System.currentTimeMillis() - startMillis;
		System.out.println(queue.getClass().getName() + " : " + millis);
	}

}
