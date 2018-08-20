package com.cc;


public class Redis extends Thread {
    private volatile static Redis redis = null;

    public static Redis aaa() {
        if (redis == null) {
            synchronized (Redis.class) {
                if (redis == null) {
                    redis = new Redis();
                    System.out.println("affa");
                    return redis;
                }
            }
        }
        return redis;
    }

    @Override
    public void run() {
        aaa();
    }

    public static void main(String[] args) {

        Redis redis = new Redis();
        Redis redis2 = new Redis();
        Redis redis3 = new Redis();
        Redis redis4 = new Redis();
        Redis redis5 = new Redis();
        Redis redis6 = new Redis();
        redis.start();
        redis2.start();
        redis3.start();
        redis4.start();
        redis5.start();
        redis6.start();

    }

}
