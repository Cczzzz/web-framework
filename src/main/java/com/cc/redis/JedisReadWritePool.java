package com.cc.redis;


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
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by chenchang on 2018/8/15.
 * 读写分离连接池
 */
public class JedisReadWritePool extends BaseJedisRWSeparationPool<Jedis> implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(JedisReadWritePool.class);

    private GenericObjectPoolConfig poolConfig;

    private JedisReadPoll slavesPool;

    private MasterJedisFactory factory;


    public JedisReadWritePool() {
    }

    public JedisReadWritePool(String masterName, Set<HostAndPort> SentinelhostAndPorts) {
        this(masterName, SentinelhostAndPorts, new GenericObjectPoolConfig());
    }

    public JedisReadWritePool(String masterName, Set<HostAndPort> SentinelhostAndPorts, GenericObjectPoolConfig poolConfig) {
        this(masterName, SentinelhostAndPorts, new GenericObjectPoolConfig(), null);
    }

    public JedisReadWritePool(String masterName, Set<HostAndPort> SentinelhostAndPorts, GenericObjectPoolConfig poolConfig, final String password) {
        this(masterName, SentinelhostAndPorts, new GenericObjectPoolConfig(), null, Protocol.DEFAULT_DATABASE);
    }

    public JedisReadWritePool(String masterName, Set<HostAndPort> SentinelhostAndPorts, GenericObjectPoolConfig poolConfig, final String password, final int database) {
        this(masterName, SentinelhostAndPorts, new GenericObjectPoolConfig(), Protocol.DEFAULT_TIMEOUT, null, Protocol.DEFAULT_DATABASE, null);
    }

    public JedisReadWritePool(String masterName, Set<HostAndPort> SentinelhostAndPorts, GenericObjectPoolConfig poolConfig, int timeout, final String password, final int database, final String clientName) {
        this.poolConfig = poolConfig;
        this.configuration = new RedisConfiguration(masterName, SentinelhostAndPorts, timeout, password, database, clientName);
       replenishConfigBySentinel(configuration);
    }

    /**
     * 加载主从实例信息
     *
     * @param configuration
     */
    private void replenishConfigBySentinel(RedisConfiguration configuration) {
        HostAndPort master = null;

        log.info("连接哨兵初始化主从信息...");

        final String masterName = configuration.getMasterName();
        for (HostAndPort sentinel : configuration.getSentinelhostAndPorts()) {

            log.info("连接哨兵：{}" + sentinel);

            Jedis sentinelJedis = null;
            try {
                sentinelJedis = new Jedis(sentinel.getHost(), sentinel.getPort());

                if (master == null) {
                    List<String> masterAddr = sentinelJedis
                            .sentinelGetMasterAddrByName(masterName);
                    if (masterAddr == null || masterAddr.size() != 2) {
                        log.warn("无法获取marster信息: "
                                + masterName + ". Sentinel: " + sentinel + ".");
                        continue;
                    }
                    master = toHostAndPort(masterAddr);
                    this.configuration.recordMaster(master);
                    log.info("找到marster：{}" + master);
                    List<Map<String, String>> maps = sentinelJedis.sentinelSlaves(masterName);
                    this.configuration.recordSlaves(maps);
                    break;
                }
            } catch (JedisConnectionException e) {
                log.warn("Cannot connect to sentinel running @ " + sentinel
                        + ". Trying next one.");
            } finally {
                if (sentinelJedis != null) {
                    sentinelJedis.close();
                }
            }
        }
    }


    private void initPool(HostAndPort master) {
        if (!master.equals(configuration.getMasterName())) {

            if (factory == null) {
                factory = new MasterJedisFactory(configuration.getMaster());
                initPool(poolConfig, factory);
            } else {
                factory.setInstance(configuration.getMaster());
                // although we clear the pool, we still have to check the returned object
                // in getResource, this call only clears idle instances, not borrowed instances
                internalPool.clear();
            }
            log.info("Created JedisPool to master at " + master);
        }
        slavesPool.initPool(poolConfig, new SlavesJedisFactory(configuration.getSlaves().values().stream().collect(Collectors.toList())));
    }


    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    Jedis getResourceByRead() {
        return null;
    }

    @Override
    Jedis getResourceByWrite() {
        return null;
    }

    @Override
    void Masterfailover(HostAndPort newMaster) {

    }

    @Override
    void SlavesOn(String id, HostAndPort hostAndPort) {

    }

    @Override
    void SlavesDown(String id, HostAndPort hostAndPort) {

    }

    @Override
    void findNewSlaves(RedisInstance instance) {

    }

    @Override
    void findNewSentinl(HostAndPort hostAndPort) {

    }

}
