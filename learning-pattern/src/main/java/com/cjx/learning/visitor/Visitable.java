package com.cjx.learning.visitor;

/**
 * Element interface
 * Created by jianxingcui on 2017/7/13.
 */
public interface Visitable {

    void accept(Visitor visitor);

}
