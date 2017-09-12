package com.cjx.learning.arithmetic;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * TODO completion javadoc.
 *
 * @author jianxing.cui
 * @since 10 九月 2017
 */
public class WheelTimer {

    private static final ScheduledExecutorService schedule = Executors.newSingleThreadScheduledExecutor();

    private Set<Integer>[] wheel;

    private int wheelSize;

    private int currentIndex = 0;

    Map<Integer, Integer> itemIndexMap = new HashMap<>();

    private Object lock = new Object();

    public WheelTimer(int count) {
        wheelSize = count + 1;
        wheel = new Set[wheelSize];
        for (int i = 0; i < wheelSize; i++) {
            wheel[i] = new HashSet<>();
        }
        schedule.scheduleAtFixedRate(() -> {
            synchronized (lock) {
                currentIndex = (currentIndex + 1 == wheelSize) ? 0 : (currentIndex + 1);
                Set<Integer> exceed = wheel[currentIndex];
                System.out.println(new Date() + " 过期item:" + exceed);
                wheel[currentIndex] = new HashSet<>();
                exceed.forEach(i -> itemIndexMap.remove(i));
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    public void itemArrive(Integer item) {
        synchronized (lock) {
            Integer index = itemIndexMap.get(item);
            if (index != null) {
                wheel[index].remove(item);
            }
            Integer safeSlot = currentIndex == 0 ? (wheelSize - 1) : (currentIndex - 1);
            itemIndexMap.put(item, safeSlot);
            wheel[safeSlot].add(item);
            System.out.println(item + " 到来时间:" + new Date());
        }
    }

    public static void main(String[] args) throws Exception {
        WheelTimer wheelTimer = new WheelTimer(30);
        wheelTimer.itemArrive(1);
        TimeUnit.SECONDS.sleep(10);
        wheelTimer.itemArrive(1);
        TimeUnit.MINUTES.sleep(1);
    }
}
