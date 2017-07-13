package com.cjx.learning.concurrent;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by cuijianxing on 16/6/27.
 */
public class Test<T> {

    private static class Node<T> {
        final T item;
        final AtomicReference<Node<T>> next;

        public Node(T item, Node<T> next) {
            this.item = item;
            this.next = new AtomicReference<>(next);
        }
    }

    private final Node<T> dummy = new Node<>(null, null);
    private final AtomicReference<Node<T>> head = new AtomicReference<>(dummy);
    private final AtomicReference<Node<T>> tail = new AtomicReference<>(dummy);

    public boolean put(T item) {
        Node<T> node = new Node<>(item, null);
        while (true) {
            Node<T> curTail = tail.get();
            Node<T> curTailNext = curTail.next.get();
            if (curTail == tail.get()) {
                if (curTailNext != null) {
                    tail.compareAndSet(curTail, curTailNext);
                } else {
                    if (curTail.next.compareAndSet(null, node)) {
                        tail.compareAndSet(curTail, node);
                        return true;
                    }
                }
            }
        }
    }
}
