package com.cc.demo;

public class         test implements Runnable {
    private volatile Thread thisThead = null;
    private static Object lock = new Object();

    @Override
    public void run() {
        System.out.println("启动。。。");
        thisThead = Thread.currentThread();
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("被中断");
                break;
            }
        }
        System.out.println("停止。。。");
    }

    private void stop() {
        if (thisThead != null) {
            thisThead.interrupt();
            thisThead = null;
        } else {
            System.out.println("this thead already stoped");
        }
    }

    public static void main(String[] args) throws InterruptedException {

        test test = new test();
        Thread thread = new Thread(test);
        thread.start();
        Thread.sleep(1000);
        test.stop();
        System.out.println("中断线程");
        synchronized (lock) {
            System.out.println("获取锁");
        }

        Thread.sleep(100000000);

    }
}
