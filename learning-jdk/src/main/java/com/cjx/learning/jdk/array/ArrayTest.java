package com.cjx.learning.jdk.array;

import java.util.Arrays;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 24 八月 2017
 */
public class ArrayTest {

	private static final byte[] arr = new byte[] {1, 2};

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 500000; i++) {
			//Arrays.copyOf(arr, arr.length);
			arr.clone();
		}
		System.out.println("耗时:" + (System.currentTimeMillis() - start));
	}

}
