package com.cjx.learning.pattern.creational.builder;

/**
 * Created by jianxingcui on 2017/7/13.
 */
public class MealDirector {

    public Meal createMeal(MealBuilder builder) {
        builder.buildDrink();
        builder.buildMain();
        builder.buildDessert();
        return builder.getMeal();
    }

}
