package com.cjx.learning.executor;

import com.cjx.learning.executor.graph.DependencyAware;
import com.cjx.learning.executor.graph.Traversar;
import com.cjx.learning.executor.graph.TraversarAction;
import com.cjx.learning.executor.task.InComingResult;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 25 八月 2017
 */
public interface GraphExecutor<T extends Comparable<T>, R> extends DependencyAware<T> {

	InComingResult<T, R> execute(final ExecutionConfig config);

	void recoverExecution(final ExecutionConfig config);

	void print(final Traversar<T, R> traversar, final TraversarAction<T, R> action);

}
