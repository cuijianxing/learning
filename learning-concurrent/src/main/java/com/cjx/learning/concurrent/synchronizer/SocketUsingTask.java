package com.cjx.learning.concurrent.synchronizer;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

/**
 * 可中断任务,适合资源占用性任务,当任务中断时执行释放资源的操作
 * <p>
 * Created by cuijianxing on 16/6/21.
 */
public class SocketUsingTask<T> implements CancellableTask<T> {

    private Socket socket;

    protected synchronized void setSocket(Socket s) {
        socket = s;
    }

    @Override
    public T call() throws Exception {
        //...
        return null;
    }

    public synchronized void cancel() {
        try {
            if (socket != null)
                socket.close();
        } catch (IOException ignored) {
        }
    }

    //把自己封装成一个FutureTask并返回
    public RunnableFuture<T> newTask() {
        return new FutureTask<T>(this) {
            public boolean cancel(boolean mayInterruptIfRunning) {
                try {
                    SocketUsingTask.this.cancel();
                } finally {
                    return super.cancel(mayInterruptIfRunning);
                }
            }
        };
    }

}
