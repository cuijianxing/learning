package com.cjx.learning.executor.task;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 25 八月 2017
 */
public class InComingResult<T, R> implements Serializable {

	private static final long serialVersionUID = 7852915543349745523L;

	private List<ExecutionResult<T, R>> results = Lists.newArrayList();

	public void add(final ExecutionResult<T, R> result) {
		this.results.add(result);
	}

	public ExecutionResult<T, R> getFirst() {
		if (this.results.isEmpty()) {
			return null;
		}
		else {
			return this.results.iterator().next();
		}
	}

	public boolean hasAnyResult() {
		return !this.results.isEmpty();
	}

	public boolean anySkipped() {
		return this.results.stream().anyMatch(ExecutionResult::isSkipped);
	}

	/**
	 *
	 * @return all result in the collection
	 */
	public List<ExecutionResult<T, R>> getAll() {
		return Lists.newArrayList(this.results);
	}

	@Override
	public String toString() {
		return this.results.toString();
	}
}
