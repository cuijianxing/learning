package com.cjx.learning.jdk.lambda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaron on 2017/7/9.
 */
public class Person {

    /**
     * 只有当前类使用的枚举可以定义成当前类的内部类
     */
    public enum Sex {
        MALE, FEMALE
    }

    String name;
    LocalDate birthday;
    Sex gender;
    String emailAddress;

    public int getAge() {
        return LocalDate.now().getYear() - birthday.getYear();
    }

    public Sex getGender() {
        return gender;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void printPerson() {
        // ...
    }

    public static List<Person> createRoster() {
        List<Person> list = new ArrayList<>();
        return list;
    }
}
