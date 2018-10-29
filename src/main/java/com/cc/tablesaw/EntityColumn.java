package com.cc.tablesaw;

import com.esotericsoftware.reflectasm.MethodAccess;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;

/**
 * Created by chenchang on 2018/10/17.
 */
@Getter
@Setter
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
}
