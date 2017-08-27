package com.cjx.learning.pattern.structural.decorator;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 14 八月 2017
 */
public abstract class EmailDecorator implements IEmail {

	//wrapped component
	IEmail originalEmail;

}
