package com.cjx.learning.executor.graph;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 25 八月 2017
 */
public interface DependencyAware<T extends Comparable<T>> {

	void addIndependent(final T nodeValue);

	void addDependency(final T evalFirstValue, final T evalAfterValue);

	void addAsDependentOnAllLeafNodes(final T nodeValue);

	void addAsDependencyToAllInitialNodes(final T nodeValue);

}
