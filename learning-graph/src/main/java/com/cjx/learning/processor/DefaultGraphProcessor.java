package com.cjx.learning.processor;

import com.google.common.base.Preconditions;
import com.cjx.learning.processor.graph.Graph;
import com.cjx.learning.processor.graph.Node;
import com.cjx.learning.processor.task.ProcessContext;
import com.cjx.learning.processor.task.Task;
import com.cjx.learning.processor.task.spi.ContextProcessor;
import com.cjx.learning.processor.task.spi.ProcessorRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 28 八月 2017
 */
public final class DefaultGraphProcessor<I, O> implements GraphProcessor<I, O> {

    private static final Logger logger = LoggerFactory.getLogger(DefaultGraphProcessor.class);

    private ProcessEngine processEngine;
    private ProcessorRegister processorRegister;
    private Graph graph;

    public DefaultGraphProcessor(final ProcessConfig<I, O> config, final Graph graph) {
        Preconditions.checkArgument(config != null);
        Preconditions.checkArgument(graph != null);
        this.processEngine = config.getEngine();
        this.processorRegister = config.getRegister();
        graph.validate();
        this.graph = graph;
    }

    @Override
    public void process(I input, O output) {
        ProcessContext<I, O> context = ProcessContext.of(input, output);
        this.startProcess(context);
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
                ContextProcessor<I, O> contextProcessor = processorRegister.retrieve(node.getId());
                state.incrementProcessingNodesCount();
                processEngine.submit(Task.newJob(contextProcessor, state.getContext()));
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
