package com.cc.toolkit.group.strategy;


import com.cc.toolkit.calc.Calc;
import com.cc.toolkit.group.Export;
import com.cc.toolkit.group.strategy.base.Strategy;

import java.util.Collection;
import java.util.function.Function;

/**
 * Created by chenchang on 2018/9/3.
 * 加权平均通过字段
 */
public class AggByWeight<T> implements Strategy<T, Export> {

    Double calcValue;

    Double sum;

    Function<T, Number> calcFeild;

    Function<T, Number> weightFeild;
    Export export = new Export();

    @Override
    public void InitParams(Collection<T> list) {
        calcValue = 0D;
        sum = 0D;
    }

    @Override
    public void forEachCall(T t) {
        Number value = calcFeild.apply(t);
        if (value == null) {
            value = 0D;
        }
        Number weighet = weightFeild.apply(t);
        if (weighet == null) {
            weighet = 0D;
        }
        calcValue = Calc.add(calcValue, Calc.mul(value.doubleValue(), weighet.doubleValue()));
        sum = Calc.add(sum, weighet.doubleValue());
    }

    @Override
    public Export ex() {
        return export;
    }

    @Override
    public void forEachFinish() {
        if (sum == 0D) {
            export.exValue(0D);
        } else {
            export.exValue(Calc.div(calcValue, sum));
        }
    }

    public AggByWeight(Function<T, Number> calcFeild) {
        this.calcFeild = calcFeild;
    }

    public AggByWeight by(Function<T, Number> weightFeild) {
        this.weightFeild = weightFeild;
        return this;
    }
}
