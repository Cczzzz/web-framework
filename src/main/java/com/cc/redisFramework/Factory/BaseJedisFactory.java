package com.cc.redisFramework.Factory;

import com.cc.redisFramework.Config.RedisConfiguration;
import com.cc.redisFramework.Config.RedisInstance;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import redis.clients.jedis.BinaryJedis;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;

/**
 * Created by chenchang on 2018/8/15.
 */
public abstract class BaseJedisFactory implements PooledObjectFactory<Jedis> {

    abstract RedisInstance getInstance();

    RedisConfiguration configuration;

    @Override
    public void activateObject(PooledObject<Jedis> pooledJedis)
            throws Exception {
        final BinaryJedis jedis = pooledJedis.getObject();
        if (jedis.getDB() != configuration.getDatabase()) {
            jedis.select(configuration.getDatabase());
        }
    }

    @Override
    public void destroyObject(PooledObject<Jedis> pooledJedis) throws Exception {
        final BinaryJedis jedis = pooledJedis.getObject();
        if (jedis.isConnected()) {
            try {
                try {
                    jedis.quit();
                } catch (Exception e) {
                }
                jedis.disconnect();
            } catch (Exception e) {

            }
        }

    }

    @Override
    public PooledObject<Jedis> makeObject() {
        final HostAndPort hostAndPort = this.getInstance().getHostAndPort();
        final Jedis jedis = new Jedis(hostAndPort.getHost(), hostAndPort.getPort(), configuration.getTimeout());
        jedis.connect();
        if (null != this.configuration.getPassword()) {
            jedis.auth(this.configuration.getPassword());
        }
        if (configuration.getDatabase() != 0) {
            jedis.select(configuration.getDatabase());
        }
        if (configuration.getClientName() != null) {
            jedis.clientSetname(configuration.getClientName());
        }
        return new DefaultPooledObject<Jedis>(jedis);
    }

    @Override
    public void passivateObject(PooledObject<Jedis> pooledJedis)
            throws Exception {
        // TODO maybe should select db 0? Not sure right now.
    }

    @Override
    public boolean validateObject(PooledObject<Jedis> pooledJedis) {
        final BinaryJedis jedis = pooledJedis.getObject();
        try {
            HostAndPort hostAndPort = this.getInstance().getHostAndPort();

            String connectionHost = jedis.getClient().getHost();
            int connectionPort = jedis.getClient().getPort();

            return hostAndPort.getHost().equals(connectionHost) && hostAndPort.getPort() == connectionPort &&
                    jedis.isConnected() && jedis.ping().equals("PONG");
        } catch (final Exception e) {
            return false;
        }
    }

}
