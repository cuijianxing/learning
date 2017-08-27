package com.cjx.learning.processor;

import com.google.common.base.Preconditions;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 25 八月 2017
 */
public class ProcessStrategy {

	public enum ProcessBehavior {
		TERMINATING, NON_TERMINATING, IMMEDIATE_RETRY_TERMINATING, SCHEDULED_RETRY_TERMINATING
	}

	private ProcessBehavior processBehavior;

	private int retryCount = 0;

	private Duration retryDelay = Duration.MINIMAL_DURATION;

	public static final ProcessStrategy TERMINATING = new ProcessStrategy().terminating();

	public static final ProcessStrategy NON_TERMINATING = new ProcessStrategy().nonTerminating();

	public ProcessStrategy nonTerminating() {
		this.processBehavior = ProcessBehavior.NON_TERMINATING;
		return this;
	}

	public ProcessStrategy terminating() {
		this.processBehavior = ProcessBehavior.TERMINATING;
		return this;
	}

	public ProcessStrategy immediateRetrying(int count) {
		this.processBehavior = ProcessBehavior.IMMEDIATE_RETRY_TERMINATING;
		this.retryCount = count;
		return this;
	}

	public ProcessStrategy scheduledRetrying(int count, Duration delay) {
		this.processBehavior = ProcessBehavior.SCHEDULED_RETRY_TERMINATING;
		this.retryCount = count;
		this.retryDelay = delay;
		return this;
	}

	public ProcessBehavior getProcessBehavior() {
		return processBehavior;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public Duration getRetryDelay() {
		return retryDelay;
	}

	public boolean isTerminating() {
		return ProcessBehavior.TERMINATING.equals(this.processBehavior);
	}

	public boolean isNonTerminating() {
		return ProcessBehavior.NON_TERMINATING.equals(this.processBehavior);
	}

	public boolean isImmediatelyRetrying() {
		return ProcessBehavior.IMMEDIATE_RETRY_TERMINATING.equals(this.processBehavior);
	}

	public boolean isScheduledRetrying() {
		return ProcessBehavior.SCHEDULED_RETRY_TERMINATING.equals(this.processBehavior);
	}

	public boolean shouldRetry(int currentCount) {
		return this.retryCount != 0 && this.retryCount >= currentCount;
	}

	public void validate() {
		if (isScheduledRetrying()) {
			Preconditions.checkArgument(this.retryDelay != null, "retryDelay should be specified for " + ProcessBehavior.SCHEDULED_RETRY_TERMINATING);
			Preconditions.checkArgument(this.getRetryDelay().getDuration() > 0, "Retry delay duration should be greater than ZERO");
		}
	}
}