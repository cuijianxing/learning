package com.cjx.learning.processor;

import com.cjx.learning.processor.utils.Preconditions;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 28 八月 2017
 */
public class DefaultProcessEngine<I, O> implements ProcessEngine<I, O> {

    private CompletionService<Integer> completionService;

    public DefaultProcessEngine(ExecutorService executorService) {
        Preconditions.checkNotNull(executorService, "executorService must not be null");
        completionService = new ExecutorCompletionService<>(executorService);
    }

    @Override
    public void submit(Job<I, O> job) {
        completionService.submit(job);
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
