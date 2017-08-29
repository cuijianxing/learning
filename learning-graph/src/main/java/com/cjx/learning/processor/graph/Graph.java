package com.cjx.learning.processor.graph;

import java.util.Set;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 28 八月 2017
 */
public interface Graph extends DependencyAware {

    Set<Node> getInitialNodes();

    Set<Node> getLeafNodes();

    Node getNode(Integer id);

    Set<Node> getOutGoingNodes(Integer id);
}
