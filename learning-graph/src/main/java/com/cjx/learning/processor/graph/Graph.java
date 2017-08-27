package com.cjx.learning.processor.graph;

import java.util.Collection;
import java.util.Set;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 25 八月 2017
 */
public interface Graph<T extends Comparable<T>, R> extends DependencyAware<T> {

	int size();

	Node<T, R> get(final T id);

	Set<Node<T, R>> getInitialNodes();

	Set<Node<T, R>> getLeafNodes();

	Collection<Node<T, R>> allNodes();

	Set<Node<T, R>> getNonProcessedNodes();

}
