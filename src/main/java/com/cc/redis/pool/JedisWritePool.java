package com.cc.redis.pool;


import com.cc.redis.Config.RedisConfiguration;
import com.cc.redis.Config.RedisInstance;
import com.cc.redis.Factory.MasterJedisFactory;
import com.cc.redis.Factory.SlavesJedisFactory;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Protocol;

import java.util.Set;

/**
 * Created by chenchang on 2018/8/15.
 * 读写分离连接池
 */
public class JedisWritePool extends BaseJedisRWSeparationPool<Jedis> implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(JedisWritePool.class);

    private GenericObjectPoolConfig poolConfig;

    private MasterJedisFactory factory;

    public JedisWritePool() {
    }

    public JedisWritePool(String masterName, Set<HostAndPort> SentinelhostAndPorts) {
        this(masterName, SentinelhostAndPorts, new GenericObjectPoolConfig());
    }

    public JedisWritePool(String masterName, Set<HostAndPort> SentinelhostAndPorts, GenericObjectPoolConfig poolConfig) {
        this(masterName, SentinelhostAndPorts, new GenericObjectPoolConfig(), null);
    }

    public JedisWritePool(String masterName, Set<HostAndPort> SentinelhostAndPorts, GenericObjectPoolConfig poolConfig, final String password) {
        this(masterName, SentinelhostAndPorts, new GenericObjectPoolConfig(), null, Protocol.DEFAULT_DATABASE);
    }

    public JedisWritePool(String masterName, Set<HostAndPort> SentinelhostAndPorts, GenericObjectPoolConfig poolConfig, final String password, final int database) {
        this(masterName, SentinelhostAndPorts, new GenericObjectPoolConfig(), Protocol.DEFAULT_TIMEOUT, null, Protocol.DEFAULT_DATABASE, null);
    }

    public JedisWritePool(String masterName, Set<HostAndPort> SentinelhostAndPorts, GenericObjectPoolConfig poolConfig, int timeout, final String password, final int database, final String clientName) {
        this(new RedisConfiguration(masterName, SentinelhostAndPorts, timeout, password, database, clientName), poolConfig);
    }

    public JedisWritePool(RedisConfiguration configuration, GenericObjectPoolConfig poolConfig) {
        this.poolConfig = poolConfig;
        this.configuration = configuration;

    }

    private void initPool() {
        if (factory == null) {
            factory = new MasterJedisFactory(configuration.getMaster());
            super.initPool(poolConfig, factory);
        } else {
            factory.setInstance(configuration.getMaster());
            // although we clear the pool, we still have to check the returned object
            // in getResource, this call only clears idle instances, not borrowed instances
            super.internalPool.clear();
        }
        log.info("Created JedisPool to master at " + configuration.getMaster());
    }

    @Override
    void init(RedisConfiguration configuration) {
        initPool();
        this.slavesPool = new JedisReadPool(poolConfig, new SlavesJedisFactory(configuration.getSlaves()));
    }

    @Override
    public void Masterfailover(HostAndPort newMaster) {
        RedisInstance instance = new RedisInstance(newMaster, RedisConfiguration.master_id);
        //深拷贝
        configuration.setMaster(instance);
        initPool();
    }

}

