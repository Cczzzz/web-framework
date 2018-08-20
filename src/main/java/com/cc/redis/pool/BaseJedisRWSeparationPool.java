package com.cc.redis.pool;

import com.cc.redis.Config.RedisConfiguration;
import com.cc.redis.Sentine.SentinelEventHandler;
import com.cc.redis.Sentine.SentinelListener;
import com.cc.redis.pool.support.LinkRoute;
import com.cc.redis.pool.support.Master;
import com.cc.redis.pool.support.Slaves;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.util.Pool;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

public abstract class BaseJedisRWSeparationPool<T> extends Pool<T> implements InitializingBean, Master<T> {

    private static final Logger log = LoggerFactory.getLogger(BaseJedisRWSeparationPool.class);

    private static final LinkRoute rout = new LinkRoute();

    protected Slaves<T> slavesPool;
    protected Set<SentinelListener> sentinelListeners;
    protected RedisConfiguration configuration;

    @Override
    public T getResource() {
        if (rout.isRead()) {
            return slavesPool.getResource();
        }
        return super.getResource();
    }

    public BaseJedisRWSeparationPool() {
    }

    public HostAndPort toHostAndPort(List<String> getMasterAddrByNameResult) {
        String host = getMasterAddrByNameResult.get(0);
        int port = Integer.parseInt(getMasterAddrByNameResult.get(1));
        return new HostAndPort(host, port);
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
        subSentinel(configuration);
    }

    protected void subSentinel(RedisConfiguration configuration) {
        JedisWritePool.FailoverHandler failoverHandler = new JedisWritePool.FailoverHandler(this);
        this.sentinelListeners = new HashSet<>();
        for (HostAndPort sentinel : configuration.getSentinelhostAndPorts()) {
            SentinelListener sentinelListener = new SentinelListener(sentinel.getHost(), sentinel.getPort(), failoverHandler);
            this.sentinelListeners.add(sentinelListener);
            sentinelListener.start();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        replenishConfigBySentinel(configuration);
        init(configuration);
    }

    public RedisConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(RedisConfiguration configuration) {
        this.configuration = configuration;
    }

    abstract void init(RedisConfiguration configuration);

    //新的哨兵上线
    public void findNewSentinl(HostAndPort hostAndPort) {

    }

    static class FailoverHandler implements SentinelEventHandler {

        private static final Logger log = LoggerFactory.getLogger(FailoverHandler.class);

        private final static ReentrantLock lock = new ReentrantLock();

        BaseJedisRWSeparationPool jedisReadWritePool;


        public FailoverHandler(BaseJedisRWSeparationPool jedisReadWritePool) {
            this.jedisReadWritePool = jedisReadWritePool;
        }

        @Override
        public void onSdown(String message) {

        }

        @Override
        public void downSdown(String message) {

        }

        @Override
        public void onOdown(String message) {

        }

        @Override
        public void downOdown(String message) {

        }

        @Override
        public void switchMaster(String message) {
            String[] switchMaster = message.split(" ");
            String host = switchMaster[3];
            Integer port = Integer.valueOf(switchMaster[4]);
            //todo 如果在此自旋等待，是否还能及时接受其他事件？
            try {
                if (lock.tryLock()) {
                    log.info("故障转移结束,切换master链接");
                    jedisReadWritePool.Masterfailover(new HostAndPort(host, port));
                    lock.unlock();
                }
            } finally {
                if (lock.isHeldByCurrentThread()) lock.unlock();
            }
        }
    }

}
