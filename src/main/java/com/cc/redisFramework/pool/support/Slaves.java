package com.cc.redisFramework.pool.support;

import com.cc.redisFramework.Config.RedisConfiguration;
import com.cc.redisFramework.Config.RedisInstance;
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
