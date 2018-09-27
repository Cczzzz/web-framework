package com.cc.redis.bean;

import com.cc.redisFramework.RedisBean;
import com.cc.redisFramework.RedisColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@RedisBean
public class RStudent {

    @RedisColumn(key = true)
    String name;
    @RedisColumn
    Integer age;
    @RedisColumn
    boolean activity;

    public static void main(String[] args) {
        RStudent rStudent = new RStudent();
    }
}
