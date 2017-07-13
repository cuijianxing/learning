package com.cjx.learning.concurrent;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Aaron on 15/4/9.
 */
public class Monitor {

    private static ConcurrentMap<String, AtomicReference<MonitorItem>> items = new ConcurrentHashMap<String, AtomicReference<MonitorItem>>();
    private static ConcurrentMap<String, AtomicLong> values = new ConcurrentHashMap<String, AtomicLong>();
    private static ConcurrentMap<String, MonitorItem> jvmItems = new ConcurrentHashMap<String, MonitorItem>();

    private static Map<String, Long> currentItems = new HashMap<String, Long>();

    private static Timer timer;

    static {
        timer = new Timer("Monitor", true);
        timer.schedule(new MonitorTask(), 0, 2000);
    }

    public static void main(String[] args) {
        final CountDownLatch start = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(200);
        for (int i = 0; i < 200; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        start.await();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    Monitor.recordOne("test");
                    end.countDown();
                }
            }).start();
        }
        start.countDown();
        long b = System.currentTimeMillis();
        try {
            end.await();
            System.out.println("test=" + items.get("test").get().count + " 耗时：" + (System.currentTimeMillis() - b));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.exit(0);
        }
    }

    /**
     * 记录一条统计，统计数加1，时间计算一分钟内的平均值
     *
     * @param name
     * @param time
     */
    public static void recordOne(String name, long time) {
        recordMany(name, 1, time);
    }

    public static void recordOne(String name) {
        recordMany(name, 1, 0);
    }

    public static void decrRecord(String name) {
        recordMany(name, -1, 0);
    }

    /**
     * 可以一次记录多个统计次数和时间
     *
     * @param name
     * @param count
     * @param time
     */
    public static void recordMany(String name, long count, long time) {
        AtomicReference<MonitorItem> item = items.get(name);
        if (item == null) {
            item = new AtomicReference<MonitorItem>(new MonitorItem());
            if (items.putIfAbsent(name, item) != null) {
                item = items.get(name);
            }
        }
        MonitorItem m;
        MonitorItem i;
        do {
            m = item.get();
            i = new MonitorItem();
            i.setCount(m.getCount() + count);
            i.setTime(m.getTime() + time);
        } while (!item.compareAndSet(m, i));
    }

    public static void recordSize(String name, long size) {
        AtomicLong v = values.get(name);
        if (v == null) {
            v = new AtomicLong();
            if (values.putIfAbsent(name, v) != null) {
                v = values.get(name);
            }
        }
        v.set(size);
    }

    /**
     * 记录具体值的统计参数，可以增加或者减少统计值
     *
     * @param name
     * @param count
     */
    public static void recordValue(String name, long count) {
        AtomicLong v = values.get(name);
        if (v == null) {
            v = new AtomicLong();
            if (values.put(name, v) != null) {
                v = values.get(name);
            }
        }
        v.addAndGet(count);
    }

    public static Map<String, Long> getValues() {
        return currentItems;
    }

    private static class MonitorItem {
        private long count;
        private long time;

        public long getCount() {
            return count;
        }

        public void setCount(long count) {
            this.count = count;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public synchronized MonitorItem dumpAndClearItem() {
            MonitorItem item = new MonitorItem();
            item.count = this.count;
            item.time = this.time;
            this.count = 0;
            this.time = 0;
            return item;
        }
    }

    private static long lastUpdate = 0;

    private static class MonitorTask extends TimerTask {

        @Override
        public void run() {
            try {
                long current = System.currentTimeMillis();
                if (current - lastUpdate < 50000L) {
                    return;
                }

                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(current);
                if (cal.get(Calendar.SECOND) > 10) {
                    return;
                }
                lastUpdate = current;

                Map<String, Long> ret = new HashMap<String, Long>();

                ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();

                ret.put("JVM_Thread_Count", (long) threadBean.getThreadCount());

                List<GarbageCollectorMXBean> beans = ManagementFactory.getGarbageCollectorMXBeans();

                for (GarbageCollectorMXBean bean : beans) {
                    String name = "JVM_" + bean.getName();
                    long count = bean.getCollectionCount();
                    long time = bean.getCollectionTime();
                    MonitorItem item = jvmItems.get(name);
                    if (item == null) {
                        item = new MonitorItem();
                        //item.add(count, time);
                        jvmItems.put(name, item);
                    }

                    ret.put(makeName(name + "_Count"), count - item.count);
                    if (count - item.count > 0) {
                        ret.put(makeName(name + "_Time"), (time - item.time) / (count - item.count));
                    }

                    item = new MonitorItem();
                    //item.add(count, time);
                    jvmItems.put(name, item);
                }

                for (Map.Entry<String, AtomicReference<MonitorItem>> entry : items.entrySet()) {
                    String name = entry.getKey();
                    AtomicReference<MonitorItem> a = entry.getValue();
                    MonitorItem item = a.get();
                    a.set(new MonitorItem());
                    long count = item.count;
                    long time = item.time;
                    ret.put(makeName(name + "_Count"), count);
                    if (count > 0) {
                        ret.put(makeName(name + "_Time"), time / count);
                    } else {
                        ret.put(makeName(name + "_Time"), 0L);
                    }
                }

                for (Map.Entry<String, AtomicLong> entry : values.entrySet()) {
                    ret.put(makeName(entry.getKey() + "_Value"), entry.getValue().get());
                }

                currentItems = Collections.unmodifiableMap(ret);

            } catch (Throwable e) {
                // ignore
            }
        }

        private String makeName(String name) {
            return name.replaceAll(" ", "_");
        }
    }

}
