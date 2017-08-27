package com.cjx.learning;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.github.dexecutor.core.DefaultDexecutor;
import com.github.dexecutor.core.DexecutorConfig;
import com.github.dexecutor.core.ExecutionConfig;
import com.github.dexecutor.core.support.ThreadPoolUtil;
import com.github.dexecutor.core.task.Task;
import com.github.dexecutor.core.task.TaskProvider;

/**
 * Unit test for simple App.
 */
public class AppTest {

	public static void main(String[] args) {
		testDependentTaskExecution();
	}

	public static void testDependentTaskExecution() {

		ExecutorService executorService = Executors.newFixedThreadPool(ThreadPoolUtil.ioIntesivePoolSize());

		try {
			DexecutorConfig<Integer, Integer> config = new DexecutorConfig<>(executorService, new SleepyTaskProvider());
			DefaultDexecutor<Integer, Integer> executor = new DefaultDexecutor<>(config);
//			executor.addDependency(1, 2);
//			executor.addDependency(1, 2);
//			executor.addDependency(1, 3);
//			executor.addDependency(3, 4);
//			executor.addDependency(3, 5);
//			executor.addDependency(3, 6);
//			executor.addDependency(2, 7);
//			executor.addDependency(2, 9);
//			executor.addDependency(2, 8);
//			executor.addDependency(9, 10);
//			executor.addDependency(12, 13);
//			executor.addDependency(13, 4);
//			executor.addDependency(13, 14);
//			executor.addIndependent(11);
			executor.addDependency(1,2);
			executor.addDependency(2,3);
			executor.addDependency(3,4);
			executor.addDependency(4,5);
			executor.addDependency(5,6);
			executor.addDependency(6,7);
			executor.addDependency(7,8);
			executor.addDependency(8,9);
			executor.addDependency(9,10);
			executor.addDependency(10,11);
			executor.addDependency(11,12);
			executor.addDependency(12,13);
			executor.addDependency(13,14);
			long start = System.currentTimeMillis();
			executor.execute(ExecutionConfig.NON_TERMINATING);
			System.out.println("耗时：" + (System.currentTimeMillis() - start));

		}
		finally {
			try {
				executorService.shutdownNow();
				executorService.awaitTermination(1, TimeUnit.SECONDS);
			}
			catch (InterruptedException e) {

			}
		}
	}

	private static class SleepyTaskProvider implements TaskProvider<Integer, Integer> {

		public Task<Integer, Integer> provideTask(final Integer id) {

			return new Task<Integer, Integer>() {

				private static final long serialVersionUID = 1L;

				public Integer execute() {
					try {
						TimeUnit.SECONDS.sleep(10);
					}
					catch (InterruptedException e) {

					}
					return id;
				}
			};
		}
	}

}
