package com.cc.redis.Factory;

import com.cc.redis.Config.RedisInstance;
import org.apache.commons.pool2.PooledObjectFactory;
import redis.clients.jedis.Jedis;


/**
 * Created by chenchang on 2018/8/15.
 */
public class MasterJedisFactory extends BaseJedisFactory implements PooledObjectFactory<Jedis> {

    RedisInstance instance;

    public MasterJedisFactory(RedisInstance instance) {
        this.instance = instance;
    }

    @Override
    public RedisInstance getInstance() {
        return instance;
    }

    public void setInstance(RedisInstance instance) {
        this.instance = instance;
    }
}
