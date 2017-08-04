package com.cjx.learning.json;

import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 03 八月 2017
 */
public class JsonSample {
	public static void main(String[] args) throws Exception {
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		InputStream inputStream = JsonSample.class.getClassLoader().getResourceAsStream("user.json");
		User user = mapper.readValue(inputStream, User.class);
		inputStream.close();
		System.out.println("ok");
	}

}
