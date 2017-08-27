package com.cjx.learning.processor;

import static com.github.dexecutor.core.support.Preconditions.checkNotNull;

import java.util.concurrent.ExecutorService;

import com.cjx.learning.processor.graph.CyclicValidator;
import com.cjx.learning.processor.graph.Validator;
import com.cjx.learning.processor.task.TaskProvider;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 27 八月 2017
 */
public class ProcessConfig<T extends Comparable<T>, R> {

	private int immediateRetryPoolThreadsCount = 1;

	private int scheduledRetryPoolThreadsCount = 1;

	private ProcessEngine<T, R> processEngine;

	private TaskProvider<T, R> taskProvider;

	private Validator<T, R> validator = new CyclicValidator<>();

	public ProcessConfig(final ExecutorService executorService, final TaskProvider<T, R> taskProvider) {
		checkNotNull(executorService, "Executer Service should not be null");
		checkNotNull(taskProvider, "Task Provider should not be null");
		this.processEngine = new DefaultProcessEngine<>(executorService);
		this.taskProvider = taskProvider;
	}

	public ProcessConfig(final ProcessEngine<T, R> processEngine, final TaskProvider<T, R> taskProvider) {
		checkNotNull(processEngine, "Process Engine should not be null");
		checkNotNull(taskProvider, "Task Provider should not be null");
		this.processEngine = processEngine;
		this.taskProvider = taskProvider;
	}

	void validate() {
		checkNotNull(this.processEngine, "Process Engine should not be null");
		checkNotNull(this.taskProvider, "Task Provider should not be null");
		checkNotNull(this.validator, "Validator should not be null");
	}

	ProcessEngine<T, R> getExecutorEngine() {
		return this.processEngine;
	}

	TaskProvider<T, R> getTaskProvider() {
		return this.taskProvider;
	}

	Validator<T, R> getValidator() {
		return this.validator;
	}

	public void setValidator(final Validator<T, R> validator) {
		this.validator = validator;
	}

	public int getImmediateRetryPoolThreadsCount() {
		return immediateRetryPoolThreadsCount;
	}

	public void setImmediateRetryPoolThreadsCount(int immediateRetryPoolThreadsCount) {
		this.immediateRetryPoolThreadsCount = immediateRetryPoolThreadsCount;
	}

	public int getScheduledRetryPoolThreadsCount() {
		return scheduledRetryPoolThreadsCount;
	}

	public void setScheduledRetryPoolThreadsCount(int scheduledRetryPoolThreadsCount) {
		this.scheduledRetryPoolThreadsCount = scheduledRetryPoolThreadsCount;
	}

}
