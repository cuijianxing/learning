package com.cjx.learning.pattern.creational.builder;

/**
 * Builder
 * Created by jianxingcui on 2017/7/13.
 */
public abstract class MealBuilder {

    protected Meal meal = new Meal();

    public abstract void buildDrink();

    public abstract void buildMain();

    public abstract void buildDessert();

    public abstract Meal getMeal();

}
