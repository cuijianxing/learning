package com.cjx.learning.processor.graph;

/**
 * 图校验器
 *
 * @author jianxing.cui
 * @since 31 八月 2017
 */
public interface Validator {

    /**
     * 校验指定图是否有效
     *
     * @param graph
     * @throws IllegalArgumentException
     */
    void validate(final Graph graph);

}
