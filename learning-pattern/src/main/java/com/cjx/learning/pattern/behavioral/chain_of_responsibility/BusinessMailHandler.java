package com.cjx.learning.pattern.behavioral.chain_of_responsibility;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 04 八月 2017
 */
public class BusinessMailHandler extends AbstractEmailHandler {

	@Override
	protected boolean isSkip(Email email) {
		return !email.getFrom().endsWith("@businessaddress.com");
	}

	@Override
	protected void doHandlerRequest(Email email) {
		//handle request (move to correct folder)
	}
}
