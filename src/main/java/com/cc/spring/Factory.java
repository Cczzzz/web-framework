package com.cc.spring;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by chenchang on 2018/7/31.
 */
public class Factory<T> implements FactoryBean<T>, InitializingBean {

    private Class<T> redisDaoClass;

    private String redisDaoName;

    @Override
    public T getObject() throws Exception {


        return creation();
    }

    @Override
    public Class<T> getObjectType() {

        return redisDaoClass;
    }


    @Override
    public boolean isSingleton() {
        return true;
    }

    public T creation() {
        T dao = null;
        try {
            dao = redisDaoClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return dao;
    }

    public Class<T> getRedisDaoClass() {
        return redisDaoClass;
    }

    public void setRedisDaoClass(Class<T> redisDaoClass) {
        this.redisDaoClass = redisDaoClass;
    }

    public String getRedisDaoName() {
        return redisDaoName;
    }

    public void setRedisDaoName(String redisDaoName) {
        this.redisDaoName = redisDaoName;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (redisDaoClass == null) {
            redisDaoClass = (Class<T>) Class.forName(redisDaoName);
        }
    }
}
