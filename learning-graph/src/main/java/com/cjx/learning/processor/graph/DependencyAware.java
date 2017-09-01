package com.cjx.learning.processor.graph;


/**
 * 实现该接口的类具有构造图的功能
 *
 * @author jianxing.cui
 * @since 28 八月 2017
 */
public interface DependencyAware {

    void addIndependent(Integer id);

    void addDependency(Integer beforeId, Integer afterId);

    void addAsDependentOnAllLeafNodes(Integer id);

    void addAsDependencyToAllInitialNodes(Integer id);
}
