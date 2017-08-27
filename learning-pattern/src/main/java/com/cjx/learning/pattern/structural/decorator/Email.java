package com.cjx.learning.pattern.structural.decorator;

/**
 * concrete component
 *
 * @author jianxing.cui
 * @since 14 八月 2017
 */
public class Email implements IEmail {
	private String content;

	public Email(String content) {
		this.content = content;
	}

	@Override
	public String getContents() {
		//general email stuff
		return content;
	}
}
