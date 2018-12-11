package com.cc.tablesaw;

import com.esotericsoftware.reflectasm.MethodAccess;
import tech.tablesaw.columns.Column;


import java.lang.reflect.Field;

/**
 * Created by chenchang on 2018/10/17.
 */

public class EntityColumn {

    String name;
    Class type;
    Field field;
    int getMethodIndex;
    int setMethodIndex;
    String getMethodName;
    String setMethodName;
    tech.tablesaw.columns.Column column;
    MethodAccess methodAccess;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public int getGetMethodIndex() {
        return getMethodIndex;
    }

    public void setGetMethodIndex(int getMethodIndex) {
        this.getMethodIndex = getMethodIndex;
    }

    public int getSetMethodIndex() {
        return setMethodIndex;
    }

    public void setSetMethodIndex(int setMethodIndex) {
        this.setMethodIndex = setMethodIndex;
    }

    public String getGetMethodName() {
        return getMethodName;
    }

    public void setGetMethodName(String getMethodName) {
        this.getMethodName = getMethodName;
    }

    public String getSetMethodName() {
        return setMethodName;
    }

    public void setSetMethodName(String setMethodName) {
        this.setMethodName = setMethodName;
    }

    public Column getColumn() {
        return column;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

    public MethodAccess getMethodAccess() {
        return methodAccess;
    }

    public void setMethodAccess(MethodAccess methodAccess) {
        this.methodAccess = methodAccess;
    }
}
