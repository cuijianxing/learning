package com.cjx.learning.processor;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 28 八月 2017
 */
public interface ProcessEngine<I, O> {

    void submit(Job<I, O> job);

    Integer processComplete();

}
