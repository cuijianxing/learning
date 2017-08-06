package com.cjx.learning.guava.using_and_avoiding_null_explained;

import com.google.common.base.Optional;

/**
 * @see {@link com.google.common.base.Optional}
 */
public class OptionalSample {

    public static void main(String[] args) {
        Optional absent = Optional.absent();
        Optional<Integer> possible = Optional.of(5);
        possible.isPresent(); // returns true
        possible.get(); // returns 5
    }

}
