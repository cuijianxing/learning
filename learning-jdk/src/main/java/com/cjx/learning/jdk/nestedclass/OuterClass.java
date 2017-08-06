package com.cjx.learning.jdk.nestedclass;

/**
 * Created by aaron on 2017/7/9.
 */
public class OuterClass {

//    A nested class is a member of its enclosing class.
//    Non-static nested classes (inner classes) have access
//    to other members of the enclosing class, even if they
//    are declared private.
//    Static nested classes do not have access to other
//    members of the enclosing class.
//    As a member of the OuterClass, a nested class
//    can be declared private, public, protected, or package private.(Recall that
//    outer classes can only be declared public or package private.)

    class InterClass {

    }

    static class StaticNestedClass {

    }
}
