package com.cjx.learning.processor;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 28 八月 2017
 */
public interface Processor<I, O> {

    Integer getId();

    void process(ProcessContext<I, O> context);

}
