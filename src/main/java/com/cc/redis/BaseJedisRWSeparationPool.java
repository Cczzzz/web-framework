package com.cc.redis;

import com.cc.redis.Config.RedisConfiguration;
import com.cc.redis.Config.RedisInstance;
import com.cc.redis.Sentine.SentinelEventHandler;
import com.cc.redis.Sentine.SentinelListener;
import org.springframework.beans.factory.InitializingBean;
import redis.clients.jedis.HostAndPort;
import redis.clients.util.Pool;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class BaseJedisRWSeparationPool<T> extends Pool<T> implements InitializingBean {

    protected Set<SentinelListener> sentinelListeners;
    protected RedisConfiguration configuration;

    @Override
    public T getResource() {
        //todo threadLocal
        return super.getResource();
    }

    public BaseJedisRWSeparationPool() {
    }

    public BaseJedisRWSeparationPool(RedisConfiguration configuration) {
        this.configuration = configuration;
    }

    public HostAndPort toHostAndPort(List<String> getMasterAddrByNameResult) {
        String host = getMasterAddrByNameResult.get(0);
        int port = Integer.parseInt(getMasterAddrByNameResult.get(1));
        return new HostAndPort(host, port);
    }

    protected void subSentinel(RedisConfiguration configuration) {
        JedisReadWritePool.FailoverHandler failoverHandler = new JedisReadWritePool.FailoverHandler(this);
        this.sentinelListeners = new HashSet<>();
        for (HostAndPort sentinel : configuration.getSentinelhostAndPorts()) {
            SentinelListener sentinelListener = new SentinelListener(sentinel.getHost(), sentinel.getPort(), failoverHandler);
            this.sentinelListeners.add(sentinelListener);
            sentinelListener.start();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public RedisConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(RedisConfiguration configuration) {
        this.configuration = configuration;
    }

          abstract T getResourceByRead();

    abstract T getResourceByWrite();

    //master故障转移
    abstract void Masterfailover(HostAndPort newMaster);

    //从服务器下线
    abstract void SlavesOn(String id, HostAndPort hostAndPort);

    //从服务器上线
    abstract void SlavesDown(String id, HostAndPort hostAndPort);

    //新的从服务器实例上线
    abstract void findNewSlaves(RedisInstance instance);

    //新的哨兵上线
    abstract void findNewSentinl(HostAndPort hostAndPort);


    class FailoverHandler implements SentinelEventHandler {
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
            //  jedisReadWritePool.Masterfailover();
        }
    }

}
