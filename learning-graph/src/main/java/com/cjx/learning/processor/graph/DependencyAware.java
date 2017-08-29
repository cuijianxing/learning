package com.cjx.learning.processor.graph;

public interface DependencyAware {

    void addIndependent(Integer id);

    void addDependency(Integer beforeId, Integer afterId);

    void addAsDependentOnAllLeafNodes(Integer id);

    void addAsDependencyToAllInitialNodes(Integer id);
}
