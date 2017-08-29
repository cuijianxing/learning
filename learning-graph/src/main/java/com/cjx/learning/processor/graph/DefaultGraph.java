package com.cjx.learning.processor.graph;

import java.util.*;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 28 八月 2017
 */
public final class DefaultGraph implements Graph {

    private Map<Integer, Node> nodes = new LinkedHashMap<>();

    @Override
    public Set<Node> getInitialNodes() {
        Set<Node> initialNodes = new LinkedHashSet<>();
        this.nodes.entrySet().stream()
                .map(Map.Entry::getValue)
                .filter(node -> node.getInComingNodes().isEmpty())
                .forEach(initialNodes::add);
        return initialNodes;
    }

    @Override
    public Set<Node> getLeafNodes() {
        Set<Node> leafNodes = new LinkedHashSet<>();
        this.nodes.entrySet().stream()
                .map(Map.Entry::getValue)
                .filter(node -> node.getOutGoingNodes().isEmpty())
                .forEach(leafNodes::add);
        return leafNodes;
    }

    @Override
    public Node getNode(Integer id) {
        return Optional.of(this.nodes.get(id)).get();
    }

    @Override
    public Set<Node> getOutGoingNodes(Integer id) {
        return Optional.of(this.nodes.get(id)).get().getOutGoingNodes();
    }

    @Override
    public void addIndependent(Integer id) {
        addOrGet(id);
    }

    @Override
    public void addDependency(Integer beforeId, Integer afterId) {
        Node beforeNode = addOrGet(beforeId);
        Node afterNode = addOrGet(afterId);
        addEdges(beforeNode, afterNode);
    }

    @Override
    public void addAsDependentOnAllLeafNodes(Integer id) {
        this.getLeafNodes().forEach(node -> addEdges(node, addOrGet(id)));
    }

    @Override
    public void addAsDependencyToAllInitialNodes(Integer id) {
        this.getInitialNodes().forEach(node -> addEdges(addOrGet(id), node));
    }

    private void addEdges(final Node beforeNode, final Node afterNode) {
        if (!beforeNode.equals(afterNode)) {
            beforeNode.addOutGoingNode(afterNode);
            afterNode.addInComingNode(beforeNode);
        }
    }

    private Node addOrGet(Integer id) {
        Node graphNode;
        if (this.nodes.containsKey(id)) {
            graphNode = this.nodes.get(id);
        } else {
            graphNode = createNode(id);
            this.nodes.put(id, graphNode);
        }
        return graphNode;
    }

    private Node createNode(Integer id) {
        return new Node(id);
    }
}
