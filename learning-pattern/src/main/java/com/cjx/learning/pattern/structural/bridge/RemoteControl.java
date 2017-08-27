package com.cjx.learning.pattern.structural.bridge;

/**
 * Abstraction
 *
 * @author jianxing.cui
 * @since 14 八月 2017
 */
public abstract class RemoteControl {
	private TV implementor;

	public void on() {
		implementor.on();
	}

	public void off() {
		implementor.off();
	}

	public void setChannel(int channel) {
		implementor.tuneChannel(channel);
	}
}
