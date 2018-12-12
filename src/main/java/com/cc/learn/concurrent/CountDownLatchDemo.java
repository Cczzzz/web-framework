package com.cc.learn.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * Created by chenchang on 2018/12/12.
 */
public class CountDownLatchDemo extends Thread {
    private CountDownLatch downLatch;

    public static void main(String[] args) {
        CountDownLatch downLatch = new CountDownLatch(1);
        CountDownLatchDemo downLatchDemo = new CountDownLatchDemo();
        downLatchDemo.setDownLatch(downLatch);
        downLatchDemo.start();
        downLatch.countDown();//countDown不是堵塞的
        System.out.println("countDown");
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "：启动");
        try {
            Thread.sleep(5000);
            downLatch.await();
            System.out.println(Thread.currentThread().getName() + "：等待完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public CountDownLatch getDownLatch() {
        return downLatch;
    }

    public void setDownLatch(CountDownLatch downLatch) {
        this.downLatch = downLatch;
    }
}
