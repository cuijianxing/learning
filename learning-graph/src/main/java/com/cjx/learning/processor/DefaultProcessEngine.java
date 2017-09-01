package com.cjx.learning.processor;

import com.google.common.base.Preconditions;
import com.cjx.learning.processor.task.Task;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;

/**
 * 默认执行引擎实现
 *
 * @author jianxing.cui
 * @since 28 八月 2017
 */
public final class DefaultProcessEngine<I, O> implements ProcessEngine<I, O> {

    private CompletionService<Integer> completionService;

    public DefaultProcessEngine(ExecutorService executorService) {
        Preconditions.checkArgument(executorService != null);
        completionService = new ExecutorCompletionService<>(executorService);
    }

    @Override
    public void submit(Task<I, O> task) {
        completionService.submit(task);
    }

    @Override
    public Integer processComplete() {
        try {
            return completionService.take().get();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
