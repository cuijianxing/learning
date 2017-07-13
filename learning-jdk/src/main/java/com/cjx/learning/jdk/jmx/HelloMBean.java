package com.cjx.learning.jdk.jmx;

/**
 * Created by jianxingcui on 2017/7/13.
 */
public interface HelloMBean {

    public void sayHello();

    public int add(int x, int y);

    public String getName();

    public int getCacheSize();

    public void setCacheSize(int size);

}
