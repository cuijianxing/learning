package com.cjx.learning.executor;

import java.util.concurrent.TimeUnit;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 25 八月 2017
 */
public class Duration {

	private final long duration;

	private final TimeUnit timeUnit;

	public static final Duration MINIMAL_DURATION = new Duration(1, TimeUnit.NANOSECONDS);

	public Duration(long duration, final TimeUnit timeUnit) {
		this.duration = duration;
		this.timeUnit = timeUnit;
	}

	public long getDuration() {
		return duration;
	}

	public TimeUnit getTimeUnit() {
		return timeUnit;
	}

}
