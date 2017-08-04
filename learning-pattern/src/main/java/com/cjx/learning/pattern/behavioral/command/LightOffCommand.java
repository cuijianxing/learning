package com.cjx.learning.pattern.behavioral.command;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 04 八月 2017
 */

public class LightOffCommand implements Command {
	//reference to the light
	Light light;

	public LightOffCommand(Light light) {
		this.light = light;
	}

	public void execute() {
		light.switchOff();
	}
}