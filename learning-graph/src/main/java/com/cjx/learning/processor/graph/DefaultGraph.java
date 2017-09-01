package com.cjx.learning.processor.graph;

import com.google.common.base.Preconditions;

import java.util.*;

/**
 * 图默认实现类
 *
 * @author jianxing.cui
 * @since 28 八月 2017
 */
public final class DefaultGraph implements Graph {

    private Map<Integer, Node> nodes = new LinkedHashMap<>();

    private Validator validator;

    public DefaultGraph(final Validator validator) {
        Preconditions.checkArgument(validator != null);
        this.validator = validator;
    }

    @Override
    public void validate() {
        this.validator.validate(this);
    }

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
    public Collection<Node> getAllNodes() {
        return this.nodes.values();
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

    public static class Builder {
        private Graph graph;

        public Builder(Validator validator) {
            this.graph = new DefaultGraph(validator);
        }

        public Builder addIndependent(Integer id) {
            this.graph.addIndependent(id);
            return this;
        }

        public Builder addDependency(Integer beforeId, Integer afterId) {
            this.graph.addDependency(beforeId, afterId);
            return this;
        }

        public Builder addAsDependentOnAllLeafNodes(Integer id) {
            this.graph.addAsDependentOnAllLeafNodes(id);
            return this;
        }

        public Builder addAsDependencyToAllInitialNodes(Integer id) {
            this.graph.addAsDependencyToAllInitialNodes(id);
            return this;
        }

        public Graph create() {
            return graph;
        }
    }
}
