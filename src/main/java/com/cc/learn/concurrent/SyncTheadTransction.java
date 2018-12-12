package com.cc.learn.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by chenchang on 2018/12/12.
 */
public class SyncTheadTransction {


    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(6);
        for (int i = 0; i < 5; i++) {
            new Thread(new work(cyclicBarrier, atomicInteger) {
                @Override
                public void doSome() {
                    if (Integer.valueOf(Thread.currentThread().getName().split("-")[1]) % 2 == 0) {
                        System.out.println(2/0);
                    }
                }
            }).start();
        }
        try {
            cyclicBarrier.await();
            if (atomicInteger.get() == 0) {
                System.out.println(Thread.currentThread().getName() + "：提交");
            } else {
                System.out.println(Thread.currentThread().getName() + "：回滚");
            }
        } catch (BrokenBarrierException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static abstract class work implements Runnable {

        private CyclicBarrier cyclicBarrier;

        private AtomicInteger atomicInteger;

        public work(CyclicBarrier cyclicBarrier, AtomicInteger atomicInteger) {
            this.cyclicBarrier = cyclicBarrier;
            this.atomicInteger = atomicInteger;
        }

        @Override
        public void run() {
            launch();
            try {
                doSome();
                finish();
            } catch (Exception e) {
                System.out.println(Thread.currentThread().getName() + "：出错");
            } finally {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                } finally {
                    if (check()) {
                        System.out.println(Thread.currentThread().getName() + "：提交");
                    } else {
                        System.out.println(Thread.currentThread().getName() + "：回滚");
                    }
                }

            }
        }

        private void launch() {
            atomicInteger.incrementAndGet();
        }

        private boolean check() {
            return atomicInteger.get() == 0;
        }

        private void finish() {
            System.out.println(Thread.currentThread().getName() + "：完成");
            atomicInteger.decrementAndGet();
        }
        public abstract void doSome();
    }

}
