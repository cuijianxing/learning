package com.cjx.learning.executor.graph;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 26 八月 2017
 */
public final class DefaultGraph<T extends Comparable<T>, R> implements Graph<T, R>, Serializable {

	private static final long serialVersionUID = 6594997332184919578L;

	private Map<T, Node<T, R>> nodes = Maps.newHashMap();

	@Override
	public int size() {
		return this.nodes.size();
	}

	@Override
	public Node<T, R> get(T id) {
		return this.nodes.get(id);
	}

	@Override
	public Set<Node<T, R>> getInitialNodes() {
		Set<Node<T, R>> initialNodes = new LinkedHashSet<>();
		this.nodes.values().stream()
				.filter(node -> CollectionUtils.isEmpty(node.getInComingNodes()))
				.forEach(initialNodes::add);
		return initialNodes;
	}

	@Override
	public Set<Node<T, R>> getLeafNodes() {
		Set<Node<T, R>> leafNodes = new LinkedHashSet<>();
		this.nodes.values().stream()
				.filter(node -> CollectionUtils.isEmpty(node.getOutGoingNodes()))
				.forEach(leafNodes::add);
		return leafNodes;
	}

	@Override
	public Collection<Node<T, R>> allNodes() {
		return new ArrayList<>(this.nodes.values());
	}

	@Override
	public Set<Node<T, R>> getNonProcessedNodes() {
		Set<Node<T, R>> result = new LinkedHashSet<>();
		doFind(result, getInitialNodes());
		return result;
	}

	private void doFind(final Set<Node<T, R>> result, final Set<Node<T, R>> nodes) {
		nodes.stream().forEach(node -> {
			if (node.isNotProcessed() && allParentProcessed(node.getInComingNodes())) {
				result.add(node);
			}
			else if (allParentProcessed(node.getInComingNodes())) {
				doFind(result, node.getOutGoingNodes());
			}
		});
	}

	private boolean allParentProcessed(final Set<Node<T, R>> inComingNodes) {
		return !inComingNodes.stream().anyMatch(Node::isNotProcessed);
	}

	@Override
	public void addIndependent(T nodeValue) {
		addOrGet(nodeValue);
	}

	@Override
	public void addDependency(T evalFirstValue, T evalAfterValue) {
		Node<T, R> firstNode = addOrGet(evalFirstValue);
		Node<T, R> afterNode = addOrGet(evalAfterValue);
		addEdges(firstNode, afterNode);
	}

	@Override
	public void addAsDependentOnAllLeafNodes(T nodeValue) {
		Node<T, R> node = addOrGet(nodeValue);
		getLeafNodes().stream().forEach(leafNode -> addEdges(leafNode, node));
	}

	@Override
	public void addAsDependencyToAllInitialNodes(T nodeValue) {
		Node<T, R> node = addOrGet(nodeValue);
		getInitialNodes().stream().forEach(initialNode -> addEdges(node, initialNode));
	}

	private Node<T, R> addOrGet(final T nodeValue) {
		Node<T, R> graphNode;
		if (this.nodes.containsKey(nodeValue)) {
			graphNode = this.nodes.get(nodeValue);
		}
		else {
			graphNode = createNode(nodeValue);
			this.nodes.put(nodeValue, graphNode);
		}
		return graphNode;
	}

	private Node<T, R> createNode(final T value) {
		return new Node<>(value);
	}

	private void addEdges(final Node<T, R> firstNode, final Node<T, R> afterNode) {
		if (!firstNode.equals(afterNode)) {
			firstNode.addOutGoingNode(afterNode);
			afterNode.addInComingNode(firstNode);
		}
	}
}
