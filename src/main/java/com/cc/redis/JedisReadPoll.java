package com.cc.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * Created by chenchang on 2018/8/15.
 */
public class JedisReadPoll extends redis.clients.jedis.JedisPool{
    public JedisReadPoll(GenericObjectPoolConfig poolConfig, String host, int port) {
        super(poolConfig, host, port);
    }


}
