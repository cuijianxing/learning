package com.cjx.learning.pattern.structural.bridge;

/**
 * Concrete Implementor
 *
 * @author jianxing.cui
 * @since 14 八月 2017
 */
public class Sony implements TV {

	public void on() {
		//Sony specific on
	}

	public void off() {
		//Sony specific off
	}

	public void tuneChannel(int channel) {
		//Sony specific tuneChannel
	}

}
