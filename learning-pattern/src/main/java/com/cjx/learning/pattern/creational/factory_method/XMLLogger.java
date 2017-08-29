package com.cjx.learning.pattern.creational.factory_method;

//ConcreteProduct
public class XMLLogger implements Logger {

    @Override
    public void log(String message) {
        //log to xml
        System.err.println("logging");
    }
}
