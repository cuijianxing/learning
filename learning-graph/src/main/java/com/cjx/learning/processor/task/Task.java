/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cjx.learning.processor.task;

import java.io.Serializable;

import com.github.dexecutor.core.task.ExecutionResults;

/**
 * Represent a unit of execution in Dexecutor framework
 *
 * @author Nadeem Mohammad
 *
 * @param <T> Type of Node/Task ID
 * @param <R> Type of Node/Task result
 */
/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 27 八月 2017
 */
public abstract class Task<T extends Comparable<T>, R> implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * id of the task, this would be same as that of {@code Node} id
	 */
	private T id;
	/**
	 * Sets the new id
	 * @param id the task id
	 */
	public void setId(final T id) {
		this.id = id;
	}
	/**
	 * 
	 * @return id of the task
	 */
	public T getId() {
		return this.id;
	}
	
	/**
	 * Framework would call this method, when it comes for tasks to be executed.
	 * @return the result of task execution
	 */
	public abstract R execute();
	/**
	 * When using retry behavior, execution error should not be considered until the last retry, this would define when execution error should be considered
	 */
	private boolean considerExecutionError = true;
	/**
	 * 
	 * @return whether execution error should be considered or not
	 */
	public boolean shouldConsiderExecutionError() {
		return this.considerExecutionError;
	}
	/**
	 * sets whether execution errors should be considered or not?
	 * 
	 * @param considerExecutionError the new value
	 */
	public void setConsiderExecutionError(boolean considerExecutionError) {
		this.considerExecutionError = considerExecutionError;
	}
	/**
	 * Defines whether or not this task should be executed
	 * 
	 * @param parentResults parent execution results
	 * 
	 * @return {@code true} If this task should be executed
	 * {@code false} If the task should be skipped
	 */
	public boolean shouldExecute(final ExecutionResults<T, R> parentResults) {
		return true;
	}
}