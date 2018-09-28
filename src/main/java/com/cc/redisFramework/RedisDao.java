package com.cc.redisFramework;

public interface RedisDao<T> {

    String SEPARATOR = "_";

    String getRedisPrefix(T t);

    String getRedisKey(T t);

    void saveToRedis(T t);

    T getFromRedis();

}
