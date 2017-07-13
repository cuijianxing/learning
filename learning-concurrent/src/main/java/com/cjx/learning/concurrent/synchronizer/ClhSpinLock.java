package com.cjx.learning.concurrent.synchronizer;

import java.util.concurrent.atomic.AtomicReference;

/**
 * clh自旋锁的java代码实现
 * Created by Aaron on 14-8-7.
 */
public class ClhSpinLock {
    private final ThreadLocal<Node> pred;
    private final ThreadLocal<Node> node;
    private final AtomicReference<Node> tail = new AtomicReference<>(new Node());

    public ClhSpinLock() {
        this.node = new ThreadLocal<Node>() {
            protected Node initialValue() {
                return new Node();
            }
        };

        this.pred = new ThreadLocal<Node>() {
            protected Node initialValue() {
                return null;
            }
        };
    }

    public void lock() {
        final Node node = this.node.get();
        node.locked = true;
        Node pred = this.tail.getAndSet(node);
        this.pred.set(pred);
        while (pred.locked) {
        }
    }

    public void unlock() {
        final Node node = this.node.get();
        node.locked = false;
        this.node.set(this.pred.get());
    }

    private static class Node {
        private volatile boolean locked;
    }
}
