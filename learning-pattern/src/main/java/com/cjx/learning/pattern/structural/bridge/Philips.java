package com.cjx.learning.pattern.structural.bridge;

/**
 * Concrete Implementor
 *
 * @author jianxing.cui
 * @since 14 八月 2017
 */
public class Philips implements TV {
	public void on() {
		//Philips specific on
	}

	public void off() {
		//Philips specific off
	}

	public void tuneChannel(int channel) {
		//Philips specific tuneChannel
	}
}
