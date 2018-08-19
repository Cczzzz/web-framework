package com.cc.redis.Factory;

import com.cc.redis.Config.RedisInstance;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenchang on 2018/8/15.
 */
public class SlavesJedisFactory extends BaseJedisFactory {

    List<RedisInstance> slaves = new ArrayList<>();


    public SlavesJedisFactory(List<RedisInstance> slaves) {
        this.slaves = slaves;
    }

    @Override
    RedisInstance getInstance() {
        return slaves.get((int) (Math.random() * slaves.size()));
    }
}

