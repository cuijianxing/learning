package com.cjx.learning.concurrent;

import java.util.concurrent.*;

/**
 * Created by Aaron on 14-8-21.
 */
public class RequestHandler {

    private final static Semaphore MAX_SEMA_PHORE = new Semaphore(10);

    public int execute() {
        boolean acquired = false;
        try {
            MAX_SEMA_PHORE.acquire();
            System.out.println("线程【" + Thread.currentThread().getName() + "】得到请求许可证");
            acquired = true;
            ExecutorService es = Executors.newSingleThreadExecutor();
            Future<Integer> future = es.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    //模拟业务逻辑
                    Thread.sleep(Math.abs((int) (Math.random() * 100)));
                    return Integer.valueOf(1);
                }
            });
            try {
                //50毫秒内处理完则直接返回结果，否则抛出TimeoutException
                return future.get(50, TimeUnit.MILLISECONDS);
            } catch (TimeoutException e) {
                return 0;
            } finally {
                es.shutdown();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (acquired) {
                MAX_SEMA_PHORE.release();
            }
        }
    }

    public static void main(String[] args) {
        final RequestHandler rh = new RequestHandler();
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int result = rh.execute();
                    System.out.println("线程【" + Thread.currentThread().getName() + "】执行结果为：" + result);
                }
            }).start();
        }
    }
}
