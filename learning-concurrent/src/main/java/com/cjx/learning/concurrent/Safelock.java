package com.cjx.learning.concurrent;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Safelock {
    static class Friend {
        private final String name;
        private final Lock lock = new ReentrantLock();

        public Friend(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public boolean impendingBow(Friend bowee) {
            Boolean myLock = false;
            Boolean yourLock = false;
            try {
                myLock = lock.tryLock();
                yourLock = bowee.lock.tryLock();
            } finally {
                if (!(myLock && yourLock)) {
                    if (myLock) {
                        lock.unlock();
                    }
                    if (yourLock) {
                        bowee.lock.unlock();
                    }
                }
            }
            return myLock && yourLock;
        }

        public void bow(Friend bowee) {
            if (impendingBow(bowee)) {
                try {
                    System.out.format("%s has"
                                    + " bowed to %s!%n",
                            this.name, bowee.getName());
                    bowee.bowBack(this);
                } finally {
                    lock.unlock();
                    bowee.lock.unlock();
                }
            } else {
                System.out.format(this.name + " started"
                        + " to bow to " + bowee.getName() + ", but saw that "
                        + bowee.getName() + " was already bowing to"
                        + " " + this.name + ".%n");
            }
        }

        public void bowBack(Friend bower) {
            System.out.format("%s has" +
                            " bowed back to %s!%n",
                    this.name, bower.getName());
        }
    }

    static class BowLoop implements Runnable {
        private Friend bower;
        private Friend bowee;

        public BowLoop(Friend bower, Friend bowee) {
            this.bower = bower;
            this.bowee = bowee;
        }

        public void run() {
            Random random = new Random();
            for (; ; ) {
                try {
                    Thread.sleep(random.nextInt(10));
                } catch (InterruptedException e) {
                }
                bower.bow(bowee);
            }
        }
    }

    public static void main(String[] args) {
        final Friend zhangSan = new Friend("zhangSan");
        final Friend liSi = new Friend("liSi");
        new Thread(new BowLoop(zhangSan, liSi)).start();
        new Thread(new BowLoop(liSi, zhangSan)).start();
    }
}
