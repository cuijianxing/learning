package com.cjx.learning.processor.graph;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 检查图是否是有向无环图
 *
 * @author jianxing.cui
 * @since 28 八月 2017
 */
public class DagValidator implements Validator {

    private Collection<Node> processedNodes = new ArrayList<>();
    private Collection<Node> onStackNodes = new ArrayList<>();

    @Override
    public void validate(final Graph graph) {
        doProcess(graph.getAllNodes());
    }

    private void doProcess(final Collection<Node> nodes) {
        for (Node node : nodes) {
            detectCycle(node);
        }
    }

    private void detectCycle(final Node node) {
        this.processedNodes.add(node);
        this.onStackNodes.add(node);
        doDepthFirstTraversal(node);
        this.onStackNodes.remove(node);
    }

    private void doDepthFirstTraversal(final Node node) {
        for (Node adjNode : node.getOutGoingNodes()) {
            if (!isAlreadyProcessed(adjNode)) {
                detectCycle(adjNode);
            } else if (isOnStack(adjNode)) {
                throw new IllegalArgumentException("不是有向无环图");
            }
        }
    }

    private boolean isAlreadyProcessed(final Node node) {
        return this.processedNodes.contains(node);
    }

    private boolean isOnStack(final Node node) {
        return this.onStackNodes.contains(node);
    }
}
