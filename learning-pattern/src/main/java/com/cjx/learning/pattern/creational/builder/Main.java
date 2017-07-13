package com.cjx.learning.pattern.creational.builder;

/**
 * Integration with overall application
 * Created by jianxingcui on 2017/7/13.
 */
public class Main {

    public static void main(String[] args) {
        MealDirector director = new MealDirector();
        MealBuilder builder;
        boolean isKid = false;
        if (isKid) {
            builder = new KidsMealBuilder();
        } else {
            builder = new AdultMealBuilder();
        }
        Meal meal = director.createMeal(builder);
    }

}
