package com.cc.toolkit.group;

import com.cc.toolkit.group.strategy.base.Strategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by chenchang on 2018/8/6.
 * 执行器
 */
@SuppressWarnings("unchecked")
public class Executor<T> {
    //聚合策略
    private Collection<Strategy> strategies;
    //计算样本
    private Collection<T> source;

    private static final Logger LOG = LoggerFactory.getLogger(Executor.class);

    public Executor(Collection<T> source) {
        this.source = source;
        strategies = new ArrayList<>();
    }

    public Executor() {
        strategies = new ArrayList<>();
    }

    /**
     * 执行
     *
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public void up() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        execute(source, strategies);
    }

    public void up(Collection<T> source) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        execute(source, strategies);
    }

    public void execute(Collection<T> list, Collection<Strategy> strategies) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        //初始化
        for (Strategy strategy : strategies) {
            strategy.InitParams(list);
        }
        //计算
        for (T t : list) {
            if (t == null) {
                continue;
            }
            for (Strategy strategy : strategies) {
                strategy.forEachCall(t);
            }
        }
        //赋值
        for (Strategy strategy : strategies) {
            strategy.forEachFinish();
        }
    }

    public void addStr(Strategy strategy) {
        strategies.add(strategy);
    }
}
