package com.cjx.learning.executor.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 26 八月 2017
 */
public abstract class BaseTraversar<T extends Comparable<T>, R> implements Traversar<T, R> {

	private List<Node<T, R>> processed = new ArrayList<>();

	protected List<List<List<Node<T, R>>>> traverseLevelOrder(final Graph<T, R> graph) {
		List<List<List<Node<T, R>>>> result = new ArrayList<>();
		Set<Node<T, R>> initialNodes = graph.getInitialNodes();
		for (Node<T, R> iNode : initialNodes) {
			List<List<Node<T, R>>> iresult = new ArrayList<>();
			doTraverse(iresult, iNode);
			result.add(iresult);
		}
		return result;
	}

	private void doTraverse(final List<List<Node<T, R>>> result, final Node<T, R> iNode) {
		Queue<Node<T, R>> queue = new LinkedList<>();
		queue.offer(iNode);
		while (!queue.isEmpty()) {
			List<Node<T, R>> level = new ArrayList<>();
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Node<T, R> node = queue.poll();
				if (!this.processed.contains(node) && allProcessed(node.getInComingNodes())) {
					if (!level.contains(node) && !processedAtThisLevel(level, node.getInComingNodes())) {
						level.add(node);
						this.processed.add(node);
					}
					for (Node<T, R> ogn : node.getOutGoingNodes()) {
						if (ogn != null && !this.processed.contains(ogn)) {
							queue.offer(ogn);
						}
					}
				}
			}
			result.add(level);
		}
	}

	private boolean processedAtThisLevel(List<Node<T, R>> level, Set<Node<T, R>> inComingNodes) {
		for (Node<T, R> node : inComingNodes) {
			if (level.contains(node)) {
				return true;
			}
		}
		return false;
	}

	private boolean allProcessed(final Set<Node<T, R>> inComingNodes) {
		return this.processed.containsAll(inComingNodes);
	}

	protected void traversePath(final List<List<Node<T, R>>> list, final TraversarAction<T, R> action) {
		int level = 0;
		for (List<Node<T, R>> nodes : list) {
			action.onNewLevel(level++);
			for (Node<T, R> node : nodes) {
				action.onNode(node);
			}
		}
	}

}
