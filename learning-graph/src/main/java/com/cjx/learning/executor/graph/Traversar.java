package com.cjx.learning.executor.graph;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 26 八月 2017
 */
public interface Traversar<T extends Comparable<T>, R> {

	void traverse(final Graph<T, R> graph, final TraversarAction<T, R> action);

}
