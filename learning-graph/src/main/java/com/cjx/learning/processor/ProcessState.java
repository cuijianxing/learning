package com.cjx.learning.processor;

import com.cjx.learning.processor.graph.Node;
import com.cjx.learning.processor.utils.Preconditions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 28 八月 2017
 */
public final class ProcessState {

    private Phase currentPhase;
    private final ProcessContext context;
    private final AtomicInteger processingNodesCount;
    private final Collection<Node> processedNodes;

    public ProcessState(ProcessContext context) {
        Preconditions.checkNotNull(context, "context must not be null");
        this.currentPhase = Phase.BUILDING;
        this.context = context;
        this.processingNodesCount = new AtomicInteger(0);
        this.processedNodes = new CopyOnWriteArrayList<>();
    }

    public void setCurrentPhase(final Phase currentPhase) {
        this.currentPhase = currentPhase;
    }

    public Phase getCurrentPhase() {
        return this.currentPhase;
    }

    public ProcessContext getContext() {
        return context;
    }

    public void incrementProcessingNodesCount() {
        this.processingNodesCount.incrementAndGet();
    }

    public void decrementProcessingNodesCount() {
        this.processingNodesCount.decrementAndGet();
    }

    public int getProcessingNodesCount() {
        return this.processingNodesCount.get();
    }

    public boolean shouldProcess(final Node node) {
        return !isAlreadyProcessed(node) && allIncomingNodesProcessed(node);
    }

    private boolean isAlreadyProcessed(final Node node) {
        return this.processedNodes.contains(node);
    }

    private boolean allIncomingNodesProcessed(final Node node) {
        if (node.getInComingNodes().isEmpty() || areAlreadyProcessed(node.getInComingNodes())) {
            return true;
        }
        return false;
    }

    private boolean areAlreadyProcessed(final Set<Node> nodes) {
        return this.processedNodes.containsAll(nodes);
    }

    public void markProcessingDone(final Node node) {
        this.processedNodes.add(node);
    }

    public Collection<Node> getProcessedNodes() {
        return new ArrayList<>(this.processedNodes);
    }

}
