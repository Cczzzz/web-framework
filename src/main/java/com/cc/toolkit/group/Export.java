package com.cc.toolkit.group;

/**
 * Created by chenchang on 2018/8/9.
 * 返回值封装
 **/
public class Export {

    private Number value;
    private boolean modify = false;

    public Export(Number value) {
        this.value = value;
    }

    public Export() {
    }

    public Integer intValue() {
        if (!modify) {
            return null;
        }
        return value.intValue();
    }

    public Long longValue() {
        if (!modify) {
            return null;
        }
        return value.longValue();
    }

    public Float floatValue() {
        if (!modify) {
            return null;
        }
        return value.floatValue();
    }

    public Double doubleValue() {
        if (!modify) {
            return null;
        }
        return value.doubleValue();
    }

    public void exValue(Number value) {
        this.value = value;
    }

    public boolean isModify() {
        return modify;
    }

    public void setModify(boolean modify) {
        this.modify = modify;
    }
}
