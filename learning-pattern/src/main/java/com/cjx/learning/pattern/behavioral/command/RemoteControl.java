package com.cjx.learning.pattern.behavioral.command;

/**
 * Invoker
 *
 * @author jianxing.cui
 * @since 04 八月 2017
 */
public class RemoteControl {
	private Command command;

	public void setCommand(Command command) {
		this.command = command;
	}

	public void pressButton() {
		command.execute();
	}
}
