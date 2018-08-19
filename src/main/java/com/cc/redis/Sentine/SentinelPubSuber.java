package com.cc.redis.Sentine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPubSub;

/**
 * Created by chenchang on 2018/8/15.
 * 事件分发调用
 */
public class SentinelPubSuber extends JedisPubSub {

    private static final Logger log = LoggerFactory.getLogger(SentinelPubSuber.class);

    private SentinelEventHandler event;

    public SentinelPubSuber(SentinelEventHandler event) {
        this.event = event;
    }

    @Override
    public void onMessage(String channel, String message) {
        log.info("收到消息=>类型：{},内容：{}", channel, message);
        switch (channel) {
            case SentinelEventHandler.on_odown:
                event.onOdown(message);
                break;
            case SentinelEventHandler.down_odown:
                event.downOdown(message);
                break;

            case SentinelEventHandler.on_sdown:
                event.onSdown(message);
                break;

            case SentinelEventHandler.down_sdown:
                event.downSdown(message);
                break;

            case SentinelEventHandler.switch_master:
                event.switchMaster(message);
                break;
        }
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {

    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {

    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {

    }

    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {

    }

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {

    }
}
