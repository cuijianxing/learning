package com.cjx.learning.pattern.creational.builder;

/**
 * Created by jianxingcui on 2017/7/13.
 */
public class KidsMealBuilder extends MealBuilder {

    public void buildDrink() {
        // add drinks to the meal
    }

    public void buildMain() {
        // add main part of the meal
    }

    public void buildDessert() {
        // add dessert part to the meal
    }

    public Meal getMeal() {
        return meal;
    }

}
