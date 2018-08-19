package com.cc.redis.Config;

import redis.clients.jedis.HostAndPort;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by chenchang on 2018/8/15.
 * 全局redis配置
 */
public class RedisConfiguration {

    public static final String ip = "ip";

    public static final String port = "port";

    public static final String role_reported = "role-reported";

    public static final String master_id = "master";

    public static final String slaves_id = "slaves";
    //master名称
    private String masterName;
    //master实例
    private RedisInstance master;
    private int timeout;
    private String password;
    private int database;
    private String clientName;
    private Set<HostAndPort> SentinelhostAndPorts;
    private Map<String, RedisInstance> slaves = new HashMap<>();

    public RedisConfiguration(String masterName, Set<HostAndPort> SentinelhostAndPorts, int timeout, String password, int database, String clientName) {
        this.masterName = masterName;
        this.timeout = timeout;
        this.password = password;
        this.database = database;
        this.clientName = clientName;
        this.SentinelhostAndPorts = SentinelhostAndPorts;
    }

    public void recordMaster(HostAndPort masterAddress) {
        master = new RedisInstance(masterAddress, "master");
    }

    public RedisInstance getMaster() {
        return master;
    }

    public void setMaster(RedisInstance master) {
        this.master = master;
    }

    public void recordSlaves(List<Map<String, String>> slaveList) {
        for (Map<String, String> map : slaveList) {
            String releReported = map.get(role_reported);
            HostAndPort hostAndPort = new HostAndPort(map.get(ip), Integer.valueOf(map.get(port)));
            RedisInstance slave = new RedisInstance(hostAndPort, releReported);
            this.slaves.put(slave.getId(), slave);
        }
    }

    public Map<String, RedisInstance> getSlaves() {
        return slaves;
    }

    public void setSlaves(Map<String, RedisInstance> slaves) {
        this.slaves = slaves;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Set<HostAndPort> getSentinelhostAndPorts() {
        return SentinelhostAndPorts;
    }

    public void setSentinelhostAndPorts(Set<HostAndPort> sentinelhostAndPorts) {
        SentinelhostAndPorts = sentinelhostAndPorts;
    }
}
