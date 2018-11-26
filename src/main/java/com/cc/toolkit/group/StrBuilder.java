package com.cc.toolkit.group;


import com.cc.toolkit.group.strategy.AggByRatio;
import com.cc.toolkit.group.strategy.AggByWeight;
import com.cc.toolkit.group.strategy.SpuerAggByWeight;
import com.cc.toolkit.group.strategy.Sum;
import com.cc.toolkit.group.strategy.base.Strategy;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/**
 * Created by chenchang on 2018/8/6.
 * 构建器
 */
public class StrBuilder<T> {

    Executor<T> executor;

    public static <T> StrBuilder<T> of(Collection<T> soure) {
        Executor<T> executor = new Executor<T>(soure);
        return new StrBuilder<T>(executor);
    }

    public StrBuilder() {
        executor = new Executor<T>();
    }

    public StrBuilder(Executor<T> executor) {
        this.executor = executor;
    }


    public AggByRatio<T> AggByRatio() {
        return AggByRatio();
    }

    public AggByRatio<T> AggByRatio(Function<T, Number> calcFeild) {
        AggByRatio<T> strategy = new AggByRatio<>(calcFeild);
        executor.addStr(strategy);
        return strategy;
    }

    public Sum<T> sum(Function<T, Number> calcFeild) {
        StackTraceElement[] st = Thread.currentThread().getStackTrace();
        for (StackTraceElement element : st) {
        }
        Sum<T> strategy = new Sum<>(calcFeild);
        executor.addStr(strategy);
        return strategy;
    }

    public AggByWeight<T> AggByWeight(Function<T, Number> calcFeild) {
        AggByWeight<T> strategy = new AggByWeight<>(calcFeild);
        executor.addStr(strategy);
        return strategy;
    }

    public SpuerAggByWeight<T> SpuerAggByWeight(Function<T, Number> weigehtFeild) {
        SpuerAggByWeight<T> strategy = new SpuerAggByWeight<>(weigehtFeild);
        executor.addStr(strategy);
        return strategy;
    }

    public void up() {
        try {
            executor.up();
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void up(List<T> soure) {
        try {
            executor.up(soure);
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public void submit(Strategy strategy) {
        executor.addStr(strategy);
    }

}
