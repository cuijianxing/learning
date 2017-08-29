package com.cjx.learning.processor;

import com.cjx.learning.processor.graph.DependencyAware;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 28 八月 2017
 */
public interface GraphProcessor<I, O> extends DependencyAware {

    void process(I input, O output);

}
