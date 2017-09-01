package com.cjx.learning.processor.graph;

import java.util.Collection;
import java.util.Set;

/**
 * 图接口类
 *
 * @author jianxing.cui
 * @since 28 八月 2017
 */
public interface Graph extends DependencyAware {

    /**
     * 校验图是否有效
     */
    void validate();

    Set<Node> getInitialNodes();

    Set<Node> getLeafNodes();

    Node getNode(Integer id);

    Set<Node> getOutGoingNodes(Integer id);

    Collection<Node> getAllNodes();
}
