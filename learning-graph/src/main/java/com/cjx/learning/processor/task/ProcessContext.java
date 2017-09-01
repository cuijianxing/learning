package com.cjx.learning.processor.task;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 请求上下文
 *
 * @author jianxing.cui
 * @since 28 八月 2017
 */
public class ProcessContext<I, O> {

    private final I input;

    private final O output;

    private ConcurrentMap<String, Object> dataHolder = new ConcurrentHashMap<>();

    public static <I, O> ProcessContext<I, O> of(I input, O output) {
        return new ProcessContext<>(input, output);
    }

    private ProcessContext(I input, O output) {
        this.input = input;
        this.output = output;
    }

    public I getInput() {
        return input;
    }

    public O getOutput() {
        return output;
    }

    public void addData(String key, Object data) {
        this.dataHolder.put(key, data);
    }

    public Object getData(String key) {
        return dataHolder.get(key);
    }
}
