package com.cjx.learning.pattern.behavioral.chain_of_responsibility;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 04 八月 2017
 */
public abstract class AbstractEmailHandler implements EmailHandler {

	private EmailHandler next;

	@Override
	public void setNext(EmailHandler next) {
		this.next = next;
	}

	@Override
	public void handleRequest(Email email) {
		if (!isSkip(email)) {
			this.doHandlerRequest(email);
		}
		if (next != null) {
			next.handleRequest(email);
		}
	}

	protected boolean isSkip(Email email) {
		return false;
	}

	protected abstract void doHandlerRequest(Email email);
}
