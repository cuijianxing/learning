package com.cjx.learning.pattern.behavioral.command;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 04 八月 2017
 */
public class Light {

	private boolean on;

	public void switchOn() {
		on = true;
	}

	public void switchOff() {
		on = false;
	}
}
