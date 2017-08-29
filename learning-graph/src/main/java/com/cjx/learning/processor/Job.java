package com.cjx.learning.processor;

import com.cjx.learning.processor.utils.Preconditions;

import java.util.concurrent.Callable;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 28 八月 2017
 */
public class Job<I, O> implements Callable<Integer> {

    private Processor<I, O> processor;

    private ProcessContext<I, O> context;

    private Job(final Processor<I, O> processor, final ProcessContext<I, O> context) {
        Preconditions.checkNotNull(processor, "process must not by null");
        Preconditions.checkNotNull(context, "context must not be null");
        this.processor = processor;
        this.context = context;
    }

    public static <I, O> Job<I, O> newJob(Processor<I, O> processor, ProcessContext<I, O> context) {
        return new Job<>(processor, context);
    }

    @Override
    public Integer call() throws Exception {
        processor.process(context);
        return processor.getId();
    }

}
