package com.cjx.learning.json;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 03 八月 2017
 */
public class JsonSample {

	private static List<String> getList(int iteration) {
		List l = new ArrayList();
		for (int i = 0; i < iteration; i++) {
			l.add(new MeasurementRecord("/test.html", 10, System.currentTimeMillis(), MeasurementType.WEB_REQUEST));
		}
		return l;
	}

	private static long jacksonTest(int iteration) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		List<String> l = getList(iteration);
		long T1 = System.nanoTime();
		String json = mapper.writeValueAsString(l);
		long T2 = System.nanoTime();
		return (T2 - T1);
	}

	private static long gsonTest(int iteration) {
		Gson gson = new GsonBuilder().create();
		List l = getList(iteration);
		long T1 = System.nanoTime();
		String json = gson.toJson(l);
		long T2 = System.nanoTime();
		return (T2 - T1);
	}

	public static void main(String[] args) throws Exception {
		int iteration = 10000;
		System.out.println("jackson:" + jacksonTest(iteration));
		System.out.println("gson:" + gsonTest(iteration));
	}
}
