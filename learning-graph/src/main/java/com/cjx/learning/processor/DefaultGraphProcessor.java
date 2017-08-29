package com.cjx.learning.processor;

import com.cjx.learning.processor.graph.DefaultGraph;
import com.cjx.learning.processor.graph.Graph;
import com.cjx.learning.processor.graph.Node;
import com.cjx.learning.processor.utils.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.concurrent.ExecutorService;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 28 八月 2017
 */
public class DefaultGraphProcessor<I, O> implements GraphProcessor<I, O> {

    private static final Logger logger = LoggerFactory.getLogger(DefaultGraphProcessor.class);

    private Graph graph = new DefaultGraph();
    private ProcessEngine processEngine;
    private ProcessorRegister processorRegister;

    public DefaultGraphProcessor(ExecutorService executorService, ProcessorRegister register) {
        Preconditions.checkNotNull(executorService, "executorService must not be null");
        Preconditions.checkNotNull(register, "register must not be null");
        this.processEngine = new DefaultProcessEngine(executorService);
        this.processorRegister = register;
    }

    @Override
    public void process(I input, O output) {
        ProcessContext<I, O> context = ProcessContext.of(input, output);
        this.startProcess(context);
    }

    @Override
    public void addIndependent(Integer id) {
        this.graph.addIndependent(id);
    }

    @Override
    public void addDependency(Integer beforeId, Integer afterId) {
        this.graph.addDependency(beforeId, afterId);
    }

    @Override
    public void addAsDependentOnAllLeafNodes(Integer id) {
        this.addAsDependentOnAllLeafNodes(id);
    }

    @Override
    public void addAsDependencyToAllInitialNodes(Integer id) {
        this.addAsDependencyToAllInitialNodes(id);
    }

    private void startProcess(ProcessContext<I, O> context) {
        Set<Node> initialNodes = graph.getInitialNodes();
        ProcessState state = new ProcessState(context);
        this.doProcess(state, initialNodes);
        this.waitProcessingNodesDone(state);
    }

    private void doProcess(ProcessState state, Set<Node> nodes) {
        for (Node node : nodes) {
            if (state.shouldProcess(node)) {
                Processor<I, O> processor = processorRegister.retrieve(node.getId());
                state.incrementProcessingNodesCount();
                processEngine.submit(Job.newJob(processor, state.getContext()));
            } else {
                logger.info("node is not ready for process [{}][{}]", node, node.getInComingNodes());
            }
        }
    }

    private void waitProcessingNodesDone(ProcessState state) {
        while (state.getProcessingNodesCount() > 0) {
            Integer completeNodeId = processEngine.processComplete();
            state.decrementProcessingNodesCount();
            state.markProcessingDone(graph.getNode(completeNodeId));
            this.doProcess(state, graph.getOutGoingNodes(completeNodeId));
        }
    }

}
