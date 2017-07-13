package com.cjx.learning.visitor;

/**
 * Created by jianxingcui on 2017/7/13.
 */
public class Book implements Visitable {

    private double price;
    private double weight;

    //accept the visitor
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public double getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }

}
