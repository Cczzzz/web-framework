package com.cc.learn.netty;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Future {

    static volatile boolean end = false;

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println("do job1");
        new Thread(()->{
            try {
                Thread.sleep(2000);
                System.out.println("do job2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            end = true;
        }).start();
        Thread.sleep(2000);
        System.out.println("do job3");
        while (!end){
            Thread.onSpinWait();

        }
        System.out.println("do job4");





//        long a = System.currentTimeMillis();
//        System.out.println("do job1");
//        Callable<String> callable = () -> {
//            Thread.sleep(2000);
//            System.out.println("do job2");
//            return "job2 is done";
//        };
//        FutureTask<String> task = new FutureTask<>(callable);
//        new Thread(task).start();
//        Thread.sleep(2000);
//        System.out.println("do job3");
//        while (!task.isDone()) {
//            Thread.onSpinWait();
//        }
//        System.out.println("job2结果 => " + task.get() + "时间:" + (System.currentTimeMillis() - a));
//        task.isCancelled()

    }
}
