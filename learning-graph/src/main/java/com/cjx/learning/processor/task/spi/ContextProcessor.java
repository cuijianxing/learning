package com.cjx.learning.processor.task.spi;

import com.cjx.learning.processor.task.ProcessContext;

/**
 * SPI
 *
 * @author jianxing.cui
 * @since 28 八月 2017
 */
public interface ContextProcessor<I, O> {

    Integer getId();

    void process(ProcessContext<I, O> context);

}
