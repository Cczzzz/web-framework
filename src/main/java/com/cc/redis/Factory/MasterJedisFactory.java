package com.cc.redis.Factory;

import com.cc.redis.Config.RedisInstance;
import org.apache.commons.pool2.PooledObjectFactory;
import redis.clients.jedis.Jedis;

import java.util.concurrent.atomic.AtomicReference;


/**
 * Created by chenchang on 2018/8/15.
 */
public class MasterJedisFactory extends BaseJedisFactory implements PooledObjectFactory<Jedis> {
    AtomicReference<RedisInstance> instance;

    public MasterJedisFactory(RedisInstance instance) {
        this.instance.set(instance);
    }

    @Override
    public RedisInstance getInstance() {
        return instance.get();
    }

    public void setInstance(RedisInstance instance) {
        this.instance.set(instance);
    }
}
