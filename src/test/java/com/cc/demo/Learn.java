package com.cc.demo;

import org.springframework.boot.web.embedded.netty.NettyWebServer;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class Learn {

    volatile AtomicReference<Inventory> inventory = new AtomicReference<>();

    static class Inventory {
        private volatile long upper = 0;

        private volatile long lower = 0;
    }

    void setUpper(long v) {
        long low;
        Inventory oldObj;
        Inventory newObj;
        do {
            oldObj = inventory.get();
            if (v >= (low = oldObj.lower)) {
                throw new IllegalArgumentException();
            }
            newObj = new Inventory();
            newObj.lower = low;
            newObj.upper = v;

        } while (inventory.compareAndSet(oldObj, newObj));
    }

    void setLower(long v) {
        long upp;
        Inventory oldObj;
        Inventory newObj;
        do {
            oldObj = inventory.get();
            if (v <= (upp = oldObj.upper)) {
                throw new IllegalArgumentException();
            }
            newObj = new Inventory();
            newObj.lower = v;
            newObj.upper = upp;

        } while (inventory.compareAndSet(oldObj, newObj));
    }


}
