package com.cjx.learning.pattern.creational.factory_method;

//Creator
public abstract class AbstractLoggerCreator {

    //the factory method
    public abstract Logger createLogger();

    //the operations that are implemented for all LoggerCreators
    // like anOperation() in our diagram
    public Logger getLogger() {
        //depending on the subclass, we'll get a particular logger.
        Logger logger = createLogger();
        //could do other operations on the logger here
        return logger;
    }

}

