package com.cjx.learning.processor;

import java.util.concurrent.ExecutionException;

import com.cjx.learning.processor.task.ProcessResult;
import com.cjx.learning.processor.task.Task;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 27 八月 2017
 */
public interface ProcessEngine<T extends Comparable<T>, R> {

	void process(final Task<T, R> task);

	ProcessResult<T, R> processResult() throws InterruptedException, ExecutionException;
}
