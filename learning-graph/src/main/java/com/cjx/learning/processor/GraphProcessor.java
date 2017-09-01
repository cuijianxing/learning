package com.cjx.learning.processor;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 28 八月 2017
 */
public interface GraphProcessor<I, O> {

    void process(I input, O output);
}
