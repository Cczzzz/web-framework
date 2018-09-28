package com.cc.redisFramework.Factory;

import com.cc.redisFramework.Config.RedisInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenchang on 2018/8/15.
 */
public class SlavesJedisFactory extends BaseJedisFactory {

    List<RedisInstance> slaves = new ArrayList<>();

    public SlavesJedisFactory(Map<String, RedisInstance> slaves) {
        //  this.slaves = slaves;
    }

    @Override
    RedisInstance getInstance() {
        return slaves.get((int) (Math.random() * slaves.size()));
    }
}

