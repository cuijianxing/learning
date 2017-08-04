package com.cjx.learning.guava.caches;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.graph.Graph;

import java.util.concurrent.TimeUnit;

public class Sample {

    public static void main(String[] args) {
//        LoadingCache<String, String> graphs = CacheBuilder.newBuilder()
//                .maximumSize(1000)
//                .expireAfterWrite(10, TimeUnit.MINUTES)
//                .removalListener(MY_LISTENER)
//                .build(
//                        new CacheLoader<String, String>() {
//                            public String load(String key) throws AnyException {
//                                return createExpensiveGraph(key);
//                            }
//                        });
    }

}
