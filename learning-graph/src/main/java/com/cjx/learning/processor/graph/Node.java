package com.cjx.learning.processor.graph;

import com.cjx.learning.processor.utils.Preconditions;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 28 八月 2017
 */
public class Node {

    private Integer id;

    private Set<Node> inComingEdges = new LinkedHashSet<>();

    private Set<Node> outGoingEdges = new LinkedHashSet<>();

    public Node(Integer id) {
        Preconditions.checkNotNull(id, "id must not be null");
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void addInComingNode(final Node node) {
        this.inComingEdges.add(node);
    }

    public void addOutGoingNode(final Node node) {
        this.outGoingEdges.add(node);
    }

    public Set<Node> getInComingNodes() {
        return this.inComingEdges;
    }

    public Set<Node> getOutGoingNodes() {
        return this.outGoingEdges;
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
