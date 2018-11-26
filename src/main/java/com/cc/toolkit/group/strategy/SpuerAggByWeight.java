package com.cc.toolkit.group.strategy;


import com.cc.toolkit.calc.Calc;
import com.cc.toolkit.group.Export;
import com.cc.toolkit.group.strategy.base.Strategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/**
 * Created by chenchang on 2018/9/28.
 * v1*(m1/sum) + v2*(m2/sum) = (v1*m1+v2*m2)/sum
 */
public class SpuerAggByWeight<T> implements Strategy<T, Export> {

    Function<T, Number> weightFeild;

    Double sum;

    List<Function<T, Number>> calcFeildList = new ArrayList<>();

    List<Export> exportList = new ArrayList<>();

    List<Double> resultList = new ArrayList<>();

    @Override
    public void InitParams(Collection<T> list) {
        sum = 0D;
    }

    @Override
    public void forEachCall(T t) {
        Number weight = weightFeild.apply(t);
        if (weight == null) {
            return;
        }
        Double value = weight.doubleValue();
        sum = Calc.add(sum, value);//求和
        for (int i = 0; i < calcFeildList.size(); i++) {
            Number calcValue = calcFeildList.get(i).apply(t);
            if (calcValue == null) {
                continue;
            }
            Double result = resultList.get(i);
            //v1*m1
            result = Calc.add(Calc.mul(value, calcValue.doubleValue()), result);
            resultList.set(i, result);
            exportList.get(i).setModify(true);
        }

    }

    @Override
    public Export ex() {
        return null;
    }

    @Override
    public void forEachFinish() {
        for (int i = 0; i < exportList.size(); i++) {
            Export ex = exportList.get(i);
            if (ex.isModify()) {
                if (sum == 0D) {
                    ex.exValue(0D);
                }
                ex.exValue(Calc.div(resultList.get(i), sum));
            }

        }

        calcFeildList = new ArrayList<>();
        exportList = new ArrayList<>();
        resultList = new ArrayList<>();
    }

    public SpuerAggByWeight(Function<T, Number> weightFeild) {
        this.weightFeild = weightFeild;
    }

    public Export at(Function<T, Number> calcFeild) {
        Export export = new Export();
        calcFeildList.add(calcFeild);
        exportList.add(export);
        resultList.add(0D);
        return export;
    }

}
