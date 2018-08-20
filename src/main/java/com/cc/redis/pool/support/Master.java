package com.cc.redis.pool.support;

import redis.clients.jedis.HostAndPort;

public interface Master<T> {
    //master故障转移
    void Masterfailover(HostAndPort newMaster);
    
    T getResource();

}
