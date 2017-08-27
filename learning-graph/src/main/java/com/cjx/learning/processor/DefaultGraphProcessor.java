package com.cjx.learning.processor;

import com.cjx.learning.processor.graph.Traversar;
import com.cjx.learning.processor.graph.TraversarAction;
import com.cjx.learning.processor.task.InComingResult;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 27 八月 2017
 */
public class DefaultGraphProcessor<T extends Comparable<T>,R> implements GraphProcessor<T,R> {

	@Override
	public void process(ProcessContext context) {

	}

	public InComingResult<T, R> execute(ProcessStrategy config) {
		validate(config);

		this.state.setCurrentPhase(Phase.RUNNING);

		Set<Node<T, R>> initialNodes = this.state.getInitialNodes();

		long start = new Date().getTime();

		doProcessNodes(config, initialNodes);
		shutdownExecutors();

		long end = new Date().getTime();

		this.state.setCurrentPhase(Phase.TERMINATED);

		logger.debug("Total Time taken to process {} jobs is {} ms.", this.state.graphSize(), end - start);
		logger.debug("Processed Nodes Ordering {}", this.state.getProcessedNodes());

		return this.state.getErrored();
	}

	@Override
	public void recoverExecution(ProcessStrategy config) {

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

	private void validate(final ProcessStrategy config) {
		config.validate();
		checkValidPhase();
		this.state.validate(this.validator);
	}
}
