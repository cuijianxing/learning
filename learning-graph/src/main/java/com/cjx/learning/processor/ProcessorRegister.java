package com.cjx.learning.processor;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 28 八月 2017
 */
public interface ProcessorRegister<I, O> {

    Processor<I, O> retrieve(Integer id);

}
