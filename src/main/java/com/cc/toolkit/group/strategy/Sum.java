package com.cc.toolkit.group.strategy;


import com.cc.toolkit.calc.Calc;
import com.cc.toolkit.group.Export;
import com.cc.toolkit.group.strategy.base.Strategy;

import java.util.Collection;
import java.util.function.Function;

/**
 * Created by chenchang on 2018/9/3.
 * 求和
 */
public class Sum<T> implements Strategy<T, Export> {
    Double calcValue;
    Function<T, Number> calcFeild;

    Export export = new Export();

    @Override
    public void InitParams(Collection<T> list) {
        calcValue = 0D;
    }

    @Override
    public void forEachCall(T t) {
        Number value = calcFeild.apply(t);
        if (value != null) {
            //设置汇总的值被改动
            // 如果一个分类下的所有子元素，某个字段的值都为null
            // 那么聚合起来的分类 的这个字段也应该为null而不是0
            export.setModify(true);
            calcValue = Calc.add(calcValue, value.doubleValue());
        }
    }

    @Override
    public Export ex() {
        return export;
    }

    @Override
    public void forEachFinish() {
        if (export.isModify()) {//如果没被改动返回null
            export.exValue(calcValue);
        }
    }

    public Sum(Function<T, Number> calcFeild) {
        this.calcFeild = calcFeild;
    }
}
