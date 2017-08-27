package com.cjx.learning.pattern.structural.bridge;

/**
 * Refined abstraction
 *
 * @author jianxing.cui
 * @since 14 八月 2017
 */
public class ConcreteRemote extends RemoteControl {
	private int currentChannel;

	public void nextChannel() {
		currentChannel++;
		setChannel(currentChannel);
	}

	public void prevChannel() {
		currentChannel--;
		setChannel(currentChannel);
	}
}
