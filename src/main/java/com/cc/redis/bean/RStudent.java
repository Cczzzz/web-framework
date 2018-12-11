package com.cc.redis.bean;

import com.cc.redisFramework.RedisBean;
import com.cc.redisFramework.RedisColumn;


@RedisBean
public class RStudent {

    @RedisColumn(key = true)
    String name;
    @RedisColumn
    Integer age;
    @RedisColumn
    boolean activity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isActivity() {
        return activity;
    }

    public void setActivity(boolean activity) {
        this.activity = activity;
    }

    public static void main(String[] args) {
        RStudent rStudent = new RStudent();
    }
}
