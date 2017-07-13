package com.cjx.learning.pattern.behavioral.visitor;

/**
 * Element interface
 * Created by jianxingcui on 2017/7/13.
 */
public interface Visitable {

    void accept(Visitor visitor);

}
