package com.cc.redisFramework.Config;

import redis.clients.jedis.HostAndPort;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by chenchang on 2018/8/15.
 * 一个redis服务器实例基本信息
 */
public class RedisInstance {
    private String id;
    private HostAndPort hostAndPort;
    private final String releReported;

    public RedisInstance(HostAndPort hostAndPort, String releReported) {
        this.hostAndPort = (hostAndPort);
        this.releReported = releReported;
        this.id = hostAndPort.toString();
    }

    public HostAndPort getHostAndPort() {
        return hostAndPort;
    }

    public void setHostAndPort(HostAndPort hostAndPort) {
        this.hostAndPort = hostAndPort;
    }

    public String getReleReported() {
        return releReported;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
