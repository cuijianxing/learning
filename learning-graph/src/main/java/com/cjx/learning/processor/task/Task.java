package com.cjx.learning.processor.task;

import com.google.common.base.Preconditions;
import com.cjx.learning.processor.task.spi.ContextProcessor;

import java.util.concurrent.Callable;

/**
 * 将节点任务处理器与上下文请求内容一起构造成具体执行的job
 *
 * @author jianxing.cui
 * @since 28 八月 2017
 */
public class Task<I, O> implements Callable<Integer> {

    private ContextProcessor<I, O> contextProcessor;

    private ProcessContext<I, O> context;

    private Task(final ContextProcessor<I, O> contextProcessor, final ProcessContext<I, O> context) {
        Preconditions.checkArgument(contextProcessor != null);
        Preconditions.checkArgument(context != null);
        this.contextProcessor = contextProcessor;
        this.context = context;
    }

    public static <I, O> Task<I, O> newJob(ContextProcessor<I, O> contextProcessor, ProcessContext<I, O> context) {
        return new Task<>(contextProcessor, context);
    }

    @Override
    public Integer call() throws Exception {
        contextProcessor.process(context);
        return contextProcessor.getId();
    }

}
