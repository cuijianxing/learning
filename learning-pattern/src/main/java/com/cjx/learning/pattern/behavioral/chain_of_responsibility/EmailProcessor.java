package com.cjx.learning.pattern.behavioral.chain_of_responsibility;

import java.util.concurrent.atomic.AtomicReference;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 04 八月 2017
 */
public class EmailProcessor {

	private EmailHandler headHandler;

	//maintain a reference to the previous handler so we can add the next one
	private AtomicReference<EmailHandler> prevHandler = new AtomicReference<>();

	public void addHandler(EmailHandler handler) {
		if (prevHandler.compareAndSet(null, handler)) {
			headHandler = handler;
		}
		else {
			EmailHandler pre;
			while (true) {
				pre = prevHandler.get();
				if (prevHandler.compareAndSet(pre, handler)) {
					pre.setNext(handler);
					break;
				}
			}
		}
	}

	public void handleRequest(Email email) {
		assert headHandler != null;
		headHandler.handleRequest(email);
	}
}
