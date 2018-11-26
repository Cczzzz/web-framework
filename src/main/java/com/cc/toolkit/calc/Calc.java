package com.cc.toolkit.calc;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @description: double计算类
 * @auther: jialong  by 2018/5/21 18:10
 */
public class Calc {

    public static double checOutNan(double d) {
        if (Double.isNaN(d)) {
            return 0D;
        }
        return d;
    }


    /**
     * 相加
     */
    public static double add(double v1, double v2) {
        v1 = checOutNan(v1);
        v2 = checOutNan(v2);
        return innerAdd(v1, v2).doubleValue();
    }

    /**
     * 相加 四舍五入
     *
     * @param newScale 保留小数位
     */
    public static double add(double v1, double v2, int newScale) {
        return rounding(innerAdd(v1, v2), newScale);
    }

    /**
     * 批量相加
     */
    public static double add(double v1, double v2, double... v) {
        double res = add(v1, v2);
        if (v.length != 0) {
            res = Arrays.stream(v).reduce(res, Calc::add);
        }
        return res;
    }

    /**
     * 相减
     */
    public static double sub(double v1, double v2) {
        v1 = checOutNan(v1);
        v2 = checOutNan(v2);
        return innerSub(v1, v2).doubleValue();
    }

    /**
     * 相减 四舍五入
     *
     * @param newScale 保留小数位
     */
    public static double sub(double v1, double v2, int newScale) {
        return rounding(innerSub(v1, v2), newScale);
    }

    /**
     * 相乘
     */
    public static double mul(double v1, double v2) {
        v1 = checOutNan(v1);
        v2 = checOutNan(v2);
        return innerMul(v1, v2).doubleValue();
    }

    /**
     * 相乘 四舍五入
     *
     * @param newScale 保留小数位
     */
    public static double mul(double v1, double v2, int newScale) {
        return rounding(innerMul(v1, v2), newScale);
    }

    /**
     * 相除
     */
    public static double div(double v1, double v2) {
        v1 = checOutNan(v1);
        v2 = checOutNan(v2);
        return div(v1, v2, 16);
    }

    /**
     * 相除
     */
    public static double divNoByZeroEx(double v1, double v2) {
        if(Double.isNaN(v1)){
            return 0D;
        }
        if (v2 == 0D) {
            return 0d;
        }
        double result = div(v1, v2);
        if (Double.isNaN(result)) {
            result = 0D;
        }
        return result;
    }

    /**
     * 相除 四舍五入
     *
     * @param newScale 保留小数位
     */
    public static double div(double v1, double v2, int newScale) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, newScale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    /**
     * 四舍五入
     */
    public static double rounding(double v, int newScale) {
        BigDecimal b = new BigDecimal(v);
        return rounding(b, newScale);
    }

    private static double rounding(BigDecimal b, int newScale) {
        return b.setScale(newScale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    private static BigDecimal innerAdd(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2);
    }

    private static BigDecimal innerSub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2);
    }

    private static BigDecimal innerMul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2);
    }
}
