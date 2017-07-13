package com.cjx.learning.pattern.behavioral.visitor;

/**
 * Created by jianxingcui on 2017/7/13.
 */
public interface Visitor {

    void visit(Book book);

//    visit other concrete items
//    void visit(CD cd);
//    void visit(DVD dvd);

}
