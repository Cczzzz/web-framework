package com.cc.toolkit.group.strategy.base;


import java.util.Collection;

/**
 * Created by chenchang on 2018/8/6.
 * T 计算对象
 * R 返回值
 */
public interface Strategy<T, R> {
    //循环前调用
    void InitParams(Collection<T> list);

    //循环时调用
    void forEachCall(T t);

    //返回结果
    R ex();

    //循环后调用
    void forEachFinish();

}
