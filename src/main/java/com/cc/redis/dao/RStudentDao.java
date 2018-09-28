package com.cc.redis.dao;

import com.cc.redis.bean.RStudent;
import com.cc.redisFramework.BaseRedisDao;

public class RStudentDao extends BaseRedisDao<RStudent> {

    @Override
    public String getRedisPrefix(RStudent rStudent) {
        return null;
    }

    @Override
    public String getRedisKey(RStudent rStudent) {
        return null;
    }

    @Override
    public void saveToRedis(RStudent rStudent) {

    }

    @Override
    public RStudent getFromRedis() {
        return null;
    }
}
