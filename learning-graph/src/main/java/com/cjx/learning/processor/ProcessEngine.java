package com.cjx.learning.processor;

import com.cjx.learning.processor.task.Task;

/**
 * 执行引擎，可自由发挥（是单线程执行还是将任务放到线程池执行）
 *
 * @author jianxing.cui
 * @since 28 八月 2017
 */
public interface ProcessEngine<I, O> {

    void submit(Task<I, O> task);

    Integer processComplete();

}
