package com.cc.toolkit.group.strategy;


import com.cc.toolkit.calc.Calc;
import com.cc.toolkit.group.Export;
import com.cc.toolkit.group.strategy.base.Strategy;

import java.util.Collection;
import java.util.function.Function;

/**
 * Created by chenchang on 2018/8/9.
 * 计算加权平均通过权重
 */
public class AggByRatio<T> implements Strategy<T, Export> {

    Double calcValue;

    Function<T, Number> calcFeild;

    Function<T, Number> weightFeild;

    Export export = new Export();

    @Override
    public void InitParams(Collection<T> list) {
        calcValue = 0D;
    }

    @Override
    public void forEachCall(T t) {
        Number value = calcFeild.apply(t);
        Number weighet = weightFeild.apply(t);
        calcValue = Calc.add(calcValue, Calc.mul(value.doubleValue(), weighet.doubleValue()));
    }

    @Override
    public Export ex() {
        return export;
    }

    @Override
    public void forEachFinish() {
        export.exValue(calcValue);
    }

    public AggByRatio(Function<T, Number> calcFeild) {
        this.calcFeild = calcFeild;
    }

    public AggByRatio by(Function<T, Number> weightFeild) {
        this.weightFeild = weightFeild;
        return this;
    }
}
