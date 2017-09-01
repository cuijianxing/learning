package com.cjx.learning.processor.task.spi;

/**
 * SPI
 *
 * @author jianxing.cui
 * @since 28 八月 2017
 */
public interface ProcessorRegister<I, O> {

    ContextProcessor<I, O> retrieve(Integer id);

}
