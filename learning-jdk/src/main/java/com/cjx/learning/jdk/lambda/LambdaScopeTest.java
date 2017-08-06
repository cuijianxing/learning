package com.cjx.learning.jdk.lambda;

import java.util.function.Consumer;

/**
 * Created by aaron on 2017/7/9.
 */
public class LambdaScopeTest {

    public int x = 0;

    class FirstLevel {

        public int x = 1;

        void methodInFirstLevel(int x) {

            // The following statement causes the compiler to generate
            // the error "local variables referenced from a lambda expression
            // must be final or effectively final" in statement A:
            //
            // x = 99;

//            If you substitute the parameter x in place of y in the declaration
//            of the lambda expression myConsumer, then the compiler generates an error:
//
//            Consumer<Integer> myConsumer = (x) -> {
//                // ...
//            }
//            The compiler generates the error
//            "variable x is already defined in method methodInFirstLevel(int)"
//            because the lambda expression does not introduce a new level of scoping.
//            Consequently, you can directly access fields, methods, and local variables of the enclosing scope.
//            For example, the lambda expression directly accesses the parameter x
//            of the method methodInFirstLevel.
//            To access variables in the enclosing class, use the keyword this.
//                    In this example, this.x refers to the member variable FirstLevel.x.

            Consumer<Integer> myConsumer = (y) ->
            {
                System.out.println("x = " + x); // Statement A
                System.out.println("y = " + y);
                System.out.println("this.x = " + this.x);
                System.out.println("LambdaScopeTest.this.x = " +
                        LambdaScopeTest.this.x);
            };

            myConsumer.accept(x);

        }
    }

    public static void main(String... args) {
        LambdaScopeTest st = new LambdaScopeTest();
        LambdaScopeTest.FirstLevel fl = st.new FirstLevel();
        fl.methodInFirstLevel(23);
    }

}
