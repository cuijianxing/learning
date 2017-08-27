package com.cjx.learning.executor;

import com.cjx.learning.executor.graph.Traversar;
import com.cjx.learning.executor.graph.TraversarAction;
import com.cjx.learning.executor.task.InComingResult;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 27 八月 2017
 */
public class DefaultGraphExecutor<T extends Comparable<T>,R> implements GraphExecutor<T,R> {

	@Override
	public InComingResult<T, R> execute(ExecutionConfig config) {
		return null;
	}

	@Override
	public void recoverExecution(ExecutionConfig config) {

	}

	@Override
	public void print(Traversar<T, R> traversar, TraversarAction<T, R> action) {

	}

	@Override
	public void addIndependent(T nodeValue) {

	}

	@Override
	public void addDependency(T evalFirstValue, T evalAfterValue) {

	}

	@Override
	public void addAsDependentOnAllLeafNodes(T nodeValue) {

	}

	@Override
	public void addAsDependencyToAllInitialNodes(T nodeValue) {

	}
}
