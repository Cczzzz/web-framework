package com.cc.redis;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.io.Closeable;

/**
 * Created by chenchang on 2018/8/15.
 */
public interface JedisPool<T> extends Closeable {

    boolean isClosed();

    T getResource();

    void returnResourceObject(T t);

    void initPool(final GenericObjectPoolConfig poolConfig, PooledObjectFactory<T> factory);



}
