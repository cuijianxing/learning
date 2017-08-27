package com.cjx.learning.processor;

import static com.github.dexecutor.core.support.Preconditions.checkNotNull;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;

import com.cjx.learning.processor.task.ProcessResult;
import com.cjx.learning.processor.task.Task;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 27 八月 2017
 */
public final class DefaultProcessEngine<T extends Comparable<T>, R> implements ProcessEngine<T, R> {


	private final CompletionService<ProcessResult<T, R>> completionService;

	public DefaultProcessEngine(final ExecutorService executorService) {
		checkNotNull(executorService, "Executer Service should not be null");
		this.completionService = new ExecutorCompletionService<>(executorService);
	}

	@Override
	public void process(Task<T, R> task) {
		this.completionService.submit(newCallable(task));
	}

	@Override
	public ProcessResult<T, R> processResult() throws InterruptedException, ExecutionException {
		return this.completionService.take().get();
	}

	private Callable<ProcessResult<T, R>> newCallable(final Task<T, R> task) {
		return () -> {
			R r = null;
			ProcessResult<T, R> result;
			try {
				r = task.execute();
				result = ProcessResult.success(task.getId(), r);
			}
			catch (Exception e) {
				result = ProcessResult.errored(task.getId(), r, e.getMessage());
			}
			return result;
		};
	}
}
