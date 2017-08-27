package com.cjx.learning.processor.graph;

import java.io.Serializable;
import java.util.Set;

import com.google.common.collect.Sets;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 26 八月 2017
 */
public final class Node<T, R> implements Serializable {

	private static final long serialVersionUID = 2765905167115524600L;

	enum NodeStatus {
		NOT_PROCESSED, ERRORED, SKIPPED, SUCCESS
	}

	private T value;

	private R result;

	private NodeStatus status;

	private Object data;

	private Set<Node<T, R>> inComingEdges = Sets.newLinkedHashSet();

	private Set<Node<T, R>> outGoingEdges = Sets.newLinkedHashSet();

	public Node(final T val) {
		this.value = val;
		this.status = NodeStatus.NOT_PROCESSED;
	}

	public void addInComingNode(final Node<T, R> node) {
		this.inComingEdges.add(node);
	}

	public void addOutGoingNode(final Node<T, R> node) {
		this.outGoingEdges.add(node);
	}

	public Set<Node<T, R>> getInComingNodes() {
		return this.inComingEdges;
	}

	public Set<Node<T, R>> getOutGoingNodes() {
		return this.outGoingEdges;
	}

	public T getValue() {
		return this.value;
	}

	public R getResult() {
		return result;
	}

	public void setResult(final R result) {
		this.result = result;
	}

	public boolean isNotProcessed() {
		return NodeStatus.SUCCESS == this.status;
	}

	public boolean isProcessed() {
		return !isNotProcessed();
	}

	public boolean isSuccess() {
		return NodeStatus.SUCCESS == this.status;
	}

	public boolean isErrored() {
		return NodeStatus.ERRORED == this.status;
	}

	public boolean isSkipped() {
		return NodeStatus.SKIPPED == this.status;
	}

	public void setSuccess() {
		this.status = NodeStatus.SUCCESS;
	}

	public void setErrored() {
		this.status = NodeStatus.ERRORED;
	}

	public void setSkipped() {
		this.status = NodeStatus.SKIPPED;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.value == null) ? 0 : this.value.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		@SuppressWarnings("unchecked")
		Node<T, R> other = (Node<T, R>) obj;

		return this.value.equals(other.value);
	}

	@Override
	public String toString() {
		return String.valueOf(this.value);
	}

}
