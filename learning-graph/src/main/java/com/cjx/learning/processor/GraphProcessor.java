package com.cjx.learning.processor;

import com.cjx.learning.processor.graph.DependencyAware;
import com.cjx.learning.processor.graph.Traversar;
import com.cjx.learning.processor.graph.TraversarAction;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 25 八月 2017
 */
public interface GraphProcessor<T extends Comparable<T>, R> extends DependencyAware<T> {

	void process(final ProcessContext context);

	void recoverExecution(final ProcessStrategy config);

	void print(final Traversar<T, R> traversar, final TraversarAction<T, R> action);

}
