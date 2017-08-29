package com.cjx.learning;

import com.cjx.learning.processor.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 28 八月 2017
 */
public class Test {

    public static void main(String[] args) {
        testDependentTaskExecution();
    }

    public static void testDependentTaskExecution() {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        try {
            GraphProcessor<Integer, Integer> executor = new DefaultGraphProcessor<>(executorService, new SleepProcessorRegister());
//            executor.addDependency(1, 2);
//            executor.addDependency(1, 2);
//            executor.addDependency(1, 3);
//            executor.addDependency(3, 4);
//            executor.addDependency(3, 5);
//            executor.addDependency(3, 6);
//            executor.addDependency(2, 7);
//            executor.addDependency(2, 9);
//            executor.addDependency(2, 8);
//            executor.addDependency(9, 10);
//            executor.addDependency(12, 13);
//            executor.addDependency(13, 4);
//            executor.addDependency(13, 14);
//            executor.addIndependent(11);
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
            executor.process(Integer.valueOf(10), Integer.valueOf(20));
            System.out.println("耗时：" + (System.currentTimeMillis() - start));

        } finally {
            try {
                executorService.shutdownNow();
                executorService.awaitTermination(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {

            }
        }
    }

    private static class SleepProcessorRegister implements ProcessorRegister<Integer, Integer> {

        @Override
        public Processor<Integer, Integer> retrieve(Integer id) {
            return new Processor<Integer, Integer>() {

                @Override
                public Integer getId() {
                    return id;
                }

                @Override
                public void process(ProcessContext<Integer, Integer> context) {
                    try {
                        System.out.println("id=" + id + " input=" + context.getInput() + " output=" + context.getOutput());
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {

                    }
                }
            };
        }
    }

}
