package com.cc.redis.Sentine;

import java.util.concurrent.locks.ReentrantLock;

public class MasterFailover implements Runnable {

    private final static ReentrantLock lock = new ReentrantLock();

    private void failover() {
        try {
            if (lock.tryLock()) {
                System.out.println("doSime" + Thread.currentThread().getName());
                lock.unlock();
            } else {
                System.out.println("获取锁失败");
                return;
            }
        } finally {
            if (lock.isHeldByCurrentThread()) lock.unlock();
        }
    }

    @Override
    public void run() {
        failover();
        System.out.println("结束" + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new MasterFailover());
        Thread t2 = new Thread(new MasterFailover());
        Thread t3 = new Thread(new MasterFailover());
        Thread t4 = new Thread(new MasterFailover());
        Thread t5 = new Thread(new MasterFailover());
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

    }
}
