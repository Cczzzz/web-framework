package com.cc;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by chenchang on 2018/8/21.
 */
public class Counter {

    static Map<String, AtomicLong> map = new ConcurrentHashMap<>();

    public static long incr(String fundCode) {
        AtomicLong atomicLong = map.get(fundCode);
        return atomicLong.incrementAndGet();
    }

    public static boolean isValid(String fundCode, long value) {
        AtomicLong atomicLong = map.get(fundCode);
        Long now = map.get(fundCode).get();
        if (value < now) {
            return false;
        }
        return true;
    }
}
