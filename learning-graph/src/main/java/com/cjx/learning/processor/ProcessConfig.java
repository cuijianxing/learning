package com.cjx.learning.processor;

import com.google.common.base.Preconditions;
import com.cjx.learning.processor.task.spi.ProcessorRegister;

import java.util.concurrent.ExecutorService;

/**
 * 处理器配置类
 *
 * @author jianxing.cui
 * @since 31 八月 2017
 */
public final class ProcessConfig<I, O> {

    private ProcessEngine<I, O> engine;
    private ProcessorRegister<I, O> register;

    public ProcessConfig(final ExecutorService executorService,
                         final ProcessorRegister<I, O> register) {
        Preconditions.checkArgument(executorService != null);
        Preconditions.checkArgument(register != null);
        this.engine = new DefaultProcessEngine<>(executorService);
        this.register = register;
    }

    public ProcessConfig(final ProcessEngine<I, O> engine,
                         final ProcessorRegister<I, O> register) {
        Preconditions.checkArgument(engine != null);
        Preconditions.checkArgument(register != null);
        this.engine = engine;
        this.register = register;
    }

    public ProcessEngine<I, O> getEngine() {
        return engine;
    }

    public ProcessorRegister<I, O> getRegister() {
        return register;
    }
}
