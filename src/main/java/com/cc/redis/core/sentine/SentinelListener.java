package com.cc.redis.core.sentine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by chenchang on 2018/8/15.
 */
public class SentinelListener extends Thread {
    private static final Logger log = LoggerFactory.getLogger(SentinelListener.class);

    private String host;
    private int port;
    private long subscribeRetryWaitTimeMillis = 5000;
    private Jedis j;
    private AtomicBoolean running = new AtomicBoolean(false);
    private SentinelEventHandler handler;

    private static final String channels[] = new String[]{"+sdown", "-sdown", "+odown", "-odown", "+switch-master",};

    public SentinelListener(String host, int port, SentinelEventHandler handler) {
        this.host = host;
        this.port = port;
        this.handler = handler;
    }

    public void run() {

        running.set(true);
        log.info("哨兵监听启动... host:{},port:{}", host, port);
        while (running.get()) {

            j = new Jedis(host, port);

            try {
                j.subscribe(new SentinelPubSuber(this.handler), channels);

            } catch (JedisConnectionException e) {
                if (running.get()) {
                    log.error("Lost connection to Sentinel at " + host
                            + ":" + port
                            + ". Sleeping 5000ms and retrying.");
                    try {
                        Thread.sleep(subscribeRetryWaitTimeMillis);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    log.info("Unsubscribing from Sentinel at " + host + ":"
                            + port);
                }
            }
        }
    }

}
