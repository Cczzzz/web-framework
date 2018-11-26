package com.cc.learn.disruptor;

import com.lmax.disruptor.EventFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chenchang on 2018/11/5.
 */
public class MyFactory implements EventFactory<DisruptorEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyFactory.class);

    @Override
    public DisruptorEvent newInstance() {
        return new DisruptorEvent();
    }
}
