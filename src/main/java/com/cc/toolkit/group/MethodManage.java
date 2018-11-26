package com.cc.toolkit.group;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by chenchang on 2018/8/6.
 * get set方法管理
 */
@SuppressWarnings("unchecked")
public class MethodManage<T, F> {

    private static final Logger LOG = LoggerFactory.getLogger(MethodManage.class);

    private static final String GET = "get";

    private static final String SET = "set";

    private String fieldName;

    private Class<T> prototypeClass;

    private Field field;

    private Class<F> fieldType;

    private Method readMethod;

    private Method writeMethod;

    private F value;


    public MethodManage(String fieldName, Class<T> prototypeClass) {
        this.fieldName = fieldName;
        this.prototypeClass = prototypeClass;
        this.field = findField();
        this.fieldType = (Class<F>) field.getType();
        this.readMethod = getGetMethod(prototypeClass, fieldName);
        this.writeMethod = getSetMethod(prototypeClass, fieldName);
    }

    private Field findField() {
        Field field = null;
        try {
            field = this.prototypeClass.getDeclaredField(this.fieldName);
        } catch (NoSuchFieldException e) {
            LOG.error("字段没找到:{}", this.fieldName);
            e.printStackTrace();
        }
        return field;
    }


    /**
     * @param clazz
     * @param name
     * @return
     */
    public Method getGetMethod(Class<T> clazz, String name) {
        String Getname = GET + name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
        Method m = null;
        try {
            m = clazz.getDeclaredMethod(Getname);
        } catch (NoSuchMethodException e) {
            LOG.error("获取get方法错误:{}", Getname);
            e.printStackTrace();
        }
        return m;
    }

    /**
     * @param clazz
     * @param name
     * @return
     */
    public Method getSetMethod(Class<T> clazz, String name) {
        String Getname = SET + name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
        Method m = null;
        try {
            m = clazz.getDeclaredMethod(Getname, this.field.getType());
        } catch (NoSuchMethodException e) {
            LOG.error("获取set方法错误:{}", Getname);
            e.printStackTrace();
        }
        return m;
    }

    public F invokeGet(T o) {
        F result = null;
        try {
            result = (F) readMethod.invoke(o);
        } catch (IllegalAccessException e) {
            LOG.error("执行get方法错误:{}", readMethod);
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            LOG.error("执行get方法错误:{}", readMethod);
            e.printStackTrace();
        }
        return result;
    }

    public void invokeSet(T o, Object v) {
        if (v == null) {
            return;
        }
        try {
            //如果值和set方法 类型不同并是数字 进行处理
            if (!field.getType().isAssignableFrom(v.getClass()) && Number.class.isAssignableFrom(v.getClass())) {
                if (field.getType().isAssignableFrom(Double.class)) {
                    v = Double.valueOf(v.toString());
                } else if (field.getType().isAssignableFrom(Integer.class)) {
                    String value = v.toString();
                    int i = value.indexOf('.');
                    if (i != -1) {
                        v = Integer.valueOf(value.substring(0, i));
                    } else {
                        v = Integer.valueOf(value);
                    }
                } else if (field.getType().isAssignableFrom(Long.class)) {
                    v = Long.valueOf(v.toString());
                }


            }
            writeMethod.invoke(o, v);
        } catch (IllegalAccessException e) {
            LOG.error("执行set方法错误:{}", writeMethod);
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            LOG.error("执行set方法错误:{}", writeMethod);
            e.printStackTrace();
        }
    }

    public F getValue() {
        return value;
    }

    public void setValue(F value) {
        this.value = value;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Class<T> getPrototypeClass() {
        return prototypeClass;
    }

    public void setPrototypeClass(Class<T> prototypeClass) {
        this.prototypeClass = prototypeClass;
    }

    public Method getReadMethod() {
        return readMethod;
    }

    public void setReadMethod(Method readMethod) {
        this.readMethod = readMethod;
    }

    public Method getWriteMethod() {
        return writeMethod;
    }

    public void setWriteMethod(Method writeMethod) {
        this.writeMethod = writeMethod;
    }

    public Class<F> getFieldType() {
        return fieldType;
    }

    public void setFieldType(Class<F> fieldType) {
        this.fieldType = fieldType;
    }

}
