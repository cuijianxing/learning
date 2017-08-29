package com.cjx.learning.processor;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 28 八月 2017
 */
public class ProcessContext<I, O> {

    private final I input;

    private final O output;

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

}
