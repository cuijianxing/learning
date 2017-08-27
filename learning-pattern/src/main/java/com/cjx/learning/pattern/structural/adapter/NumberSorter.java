package com.cjx.learning.pattern.structural.adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * This is our adaptee, a third party implementation of a
 * number sorter that deals with Lists, not arrays.
 * @author jianxing.cui
 * @since 14 八月 2017
 */
public class NumberSorter {
	public List<Integer> sort(List<Integer> numbers) {
		//sort and return
		return new ArrayList<Integer>();
	}
}
