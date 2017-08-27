package com.cjx.learning.executor.task;

import java.io.Serializable;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 25 八月 2017
 */
public final class ExecutionResult<T, R> implements Serializable {

	public enum ResultStatus {
		ERRORED, SKIPPED, SUCCESS
	}

	private static final long serialVersionUID = 8280858853941009031L;

	private static final String EMPTY = "";

	private T id;

	private R result;

	private ResultStatus status = ResultStatus.SUCCESS;

	private String message;

	public ExecutionResult(final T id, final R result, final ResultStatus status) {
		this(id, result, status, EMPTY);
	}

	private ExecutionResult(final T id, final R result, final ResultStatus status, final String msg) {
		this.id = id;
		this.result = result;
		this.status = status;
		this.message = msg;
	}

	public static <T, R> ExecutionResult<T, R> success(final T id, final R result) {
		return new ExecutionResult<T, R>(id, result, ResultStatus.SUCCESS, EMPTY);
	}

	public static <T, R> ExecutionResult<T, R> errored(final T id, final R result, final String msg) {
		return new ExecutionResult<T, R>(id, result, ResultStatus.ERRORED, msg);
	}

	public T getId() {
		return id;
	}

	public R getResult() {
		return result;
	}

	public ResultStatus getStatus() {
		return status;
	}

	public void errored() {
		this.status = ResultStatus.ERRORED;
	}

	public void skipped() {
		this.status = ResultStatus.SKIPPED;
	}

	public boolean isSuccess() {
		return ResultStatus.SUCCESS == this.status;
	}

	public boolean isErrored() {
		return ResultStatus.ERRORED == this.status;
	}

	public boolean isSkipped() {
		return ResultStatus.SKIPPED == this.status;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		ExecutionResult<T, R> other = (ExecutionResult<T, R>) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		}
		else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ExecutionResult [id=" + id + ", result=" + result + ", status=" + status + ", message=" + message + "]";
	}
}
