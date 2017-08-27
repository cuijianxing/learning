package com.cjx.learning.executor;

import com.google.common.base.Preconditions;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 25 八月 2017
 */
public class ExecutionConfig {

	public enum ExecutionBehavior {
		TERMINATING, NON_TERMINATING, IMMEDIATE_RETRY_TERMINATING, SCHEDULED_RETRY_TERMINATING
	}

	private ExecutionBehavior executionBehavior;

	private int retryCount = 0;

	private Duration retryDelay = Duration.MINIMAL_DURATION;

	public static final ExecutionConfig TERMINATING = new ExecutionConfig().terminating();

	public static final ExecutionConfig NON_TERMINATING = new ExecutionConfig().nonTerminating();

	public ExecutionConfig nonTerminating() {
		this.executionBehavior = ExecutionBehavior.NON_TERMINATING;
		return this;
	}

	public ExecutionConfig terminating() {
		this.executionBehavior = ExecutionBehavior.TERMINATING;
		return this;
	}

	public ExecutionConfig immediateRetrying(int count) {
		this.executionBehavior = ExecutionBehavior.IMMEDIATE_RETRY_TERMINATING;
		this.retryCount = count;
		return this;
	}

	public ExecutionConfig scheduledRetrying(int count, Duration delay) {
		this.executionBehavior = ExecutionBehavior.SCHEDULED_RETRY_TERMINATING;
		this.retryCount = count;
		this.retryDelay = delay;
		return this;
	}

	public ExecutionBehavior getExecutionBehavior() {
		return executionBehavior;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public Duration getRetryDelay() {
		return retryDelay;
	}

	public boolean isTerminating() {
		return ExecutionBehavior.TERMINATING.equals(this.executionBehavior);
	}

	public boolean isNonTerminating() {
		return ExecutionBehavior.NON_TERMINATING.equals(this.executionBehavior);
	}

	public boolean isImmediatelyRetrying() {
		return ExecutionBehavior.IMMEDIATE_RETRY_TERMINATING.equals(this.executionBehavior);
	}

	public boolean isScheduledRetrying() {
		return ExecutionBehavior.SCHEDULED_RETRY_TERMINATING.equals(this.executionBehavior);
	}

	public boolean shouldRetry(int currentCount) {
		return this.retryCount != 0 && this.retryCount >= currentCount;
	}

	public void validate() {
		if (isScheduledRetrying()) {
			Preconditions.checkArgument(this.retryDelay != null, "retryDelay should be specified for " + ExecutionBehavior.SCHEDULED_RETRY_TERMINATING);
			Preconditions.checkArgument(this.getRetryDelay().getDuration() > 0, "Retry delay duration should be greater than ZERO");
		}
	}
}