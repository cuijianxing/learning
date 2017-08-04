package com.cjx.learning.pattern.behavioral.chain_of_responsibility;

/**
 * Handler
 *
 * @author jianxing.cui
 * @since 04 八月 2017
 */
public interface EmailHandler {
	//reference to the next handler in the chain
	void setNext(EmailHandler handler);

	//handle request
	void handleRequest(Email email);
}
