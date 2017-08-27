package com.cjx.learning.executor.graph;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 26 八月 2017
 */
public interface TraversarAction<T extends Comparable<T>, R> {

	void onNewPath(int pathNumber);

	void onNewLevel(int levelNumber);

	void onNode(final Node<T, R> node);

}
