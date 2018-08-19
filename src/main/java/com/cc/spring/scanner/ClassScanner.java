package com.cc.spring.scanner;

import com.cc.spring.Factory;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import java.util.Set;

/**
 * Created by chenchang on 2018/7/31.
 */
public class ClassScanner extends ClassPathBeanDefinitionScanner {


    public ClassScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters) {
        super(registry, false);
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolderSet = super.doScan(basePackages);
        for (BeanDefinitionHolder holder : beanDefinitionHolderSet) {
            GenericBeanDefinition beanDefinition = (GenericBeanDefinition) holder.getBeanDefinition();
            String orgClassName = beanDefinition.getBeanClassName();
            beanDefinition.setBeanClassName(Factory.class.getName());
            beanDefinition.getPropertyValues().add("redisDaoName", orgClassName);
        }
        return beanDefinitionHolderSet;
    }
}
