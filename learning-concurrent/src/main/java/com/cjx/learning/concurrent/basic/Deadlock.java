package com.cjx.learning.concurrent.basic;

/**
 * Created by Aaron on 14-8-5.
 */
public class Deadlock {

    static class Friend {
        private final String name;

        public Friend(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public synchronized void bow(Friend bower) {
            System.out.format("%s"
                            + " has bowed to %s!%n",
                    this.name, bower.getName());
            bower.bowBack(this);
        }

        public synchronized void bowBack(Friend bower) {
            System.out.format("%s"
                            + " has bowed back to %s!%n",
                    this.name, bower.getName());
        }

    }

    public static void main(String[] args) {
        final Friend zhangSan =
                new Friend("zhangSan");
        final Friend liSi =
                new Friend("liSi");
        //一号线程
        new Thread(() -> zhangSan.bow(liSi)).start();
        //二号线程
        new Thread(() -> liSi.bow(zhangSan)).start();
    }

}
