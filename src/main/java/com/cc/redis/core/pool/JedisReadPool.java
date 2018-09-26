package com.cc.redis.core.pool;

import com.cc.redis.core.config.RedisConfiguration;
import com.cc.redis.core.config.RedisInstance;
import com.cc.redis.core.pool.support.Slaves;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

/**
 * Created by chenchang on 2018/8/15.
 */
public class JedisReadPool extends Pool<Jedis> implements Slaves<Jedis> {

    public JedisReadPool(GenericObjectPoolConfig poolConfig, PooledObjectFactory<Jedis> factory) {
        super(poolConfig, factory);
    }

    @Override
    public void init(RedisConfiguration configuration) {
    }

    @Override
    public void SlavesOn(String id, HostAndPort hostAndPort) {

    }

    @Override
    public void SlavesDown(String id, HostAndPort hostAndPort) {

    }

    @Override
    public void findNewSlaves(RedisInstance instance) {

    }
}
