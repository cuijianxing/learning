package com.cjx.learning.pattern.behavioral.command;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 04 八月 2017
 */

public class LightOnCommand implements Command {
	//reference to the light
	Light light;

	public LightOnCommand(Light light) {
		this.light = light;
	}

	public void execute() {
		light.switchOn();
	}
}
