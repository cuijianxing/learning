package com.cjx.learning.json;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 15 八月 2017
 */
public class MeasurementRecord {

	private String measurementId;

	private long duration;

	private long time;

	private MeasurementType type = MeasurementType.METHOD_CALL;

	/**
	 *
	 * @param measurementId
	 * @param duration
	 * @param time
	 * @param type
	 */
	public MeasurementRecord(String measurementId, long duration, long time,
			MeasurementType type) {
		super();
		this.measurementId = measurementId;
		this.duration = duration;
		this.time = time;
		this.type = type;
	}

	public String getMeasurementId() {
		return measurementId;
	}

	public void setMeasurementId(String measurementId) {
		this.measurementId = measurementId;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public MeasurementType getType() {
		return type;
	}

	public void setType(MeasurementType type) {
		this.type = type;
	}
}