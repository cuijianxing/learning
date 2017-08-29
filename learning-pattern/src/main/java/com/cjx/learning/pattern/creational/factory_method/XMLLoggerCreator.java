package com.cjx.learning.pattern.creational.factory_method;

//ConcreteCreator
public class XMLLoggerCreator extends AbstractLoggerCreator {

    @Override
    public Logger createLogger() {
        XMLLogger logger = new XMLLogger();
        return logger;
    }
}
