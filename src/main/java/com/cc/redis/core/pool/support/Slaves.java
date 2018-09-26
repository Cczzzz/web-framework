package com.cc.redis.core.pool.support;


import com.cc.redis.core.config.RedisConfiguration;
import com.cc.redis.core.config.RedisInstance;
import redis.clients.jedis.HostAndPort;

public interface Slaves<T> {

    void init(RedisConfiguration configuration);

    //从服务器下线
    void SlavesOn(String id, HostAndPort hostAndPort);

    //从服务器上线
    void SlavesDown(String id, HostAndPort hostAndPort);

    //新的从服务器实例上线
    void findNewSlaves(RedisInstance instance);

    T getResource();

}
