package com.cjx.learning.concurrent.synchronizer;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

/**
 * 声明可中断任务接口
 * <p>
 * Created by cuijianxing on 16/6/21.
 */
public interface CancellableTask<T> extends Callable<T> {

    void cancel();

    RunnableFuture<T> newTask();

}
