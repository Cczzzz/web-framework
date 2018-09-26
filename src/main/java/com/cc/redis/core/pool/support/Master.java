package com.cc.redis.core.pool.support;

import redis.clients.jedis.HostAndPort;

public interface Master<T> {
    //master故障转移
    void Masterfailover(HostAndPort newMaster);
    
    T getResource();

}
