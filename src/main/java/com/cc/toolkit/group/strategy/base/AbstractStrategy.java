package com.cc.toolkit.group.strategy.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chenchang on 2018/8/7.
 */
public abstract class AbstractStrategy<T, R> implements Strategy<T, R> {

    private static Logger LOG = LoggerFactory.getLogger(AbstractStrategy.class);


    @Override
    public void forEachCall(T t) {
        try {

        } catch (Exception e) {
            LOG.error(this.getClass().getName() + "=>计算错误：{}", t);
            throw e;
        }
    }

    abstract void forEachCallOn(T t);

    @Override
    public void forEachFinish() {

    }

    abstract void forEachFinishOn();
}
