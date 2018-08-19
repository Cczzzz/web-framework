package com.cc.spring.scanner;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by chenchang on 2018/7/31.
 */
public class MyScannerConfigurer implements BeanDefinitionRegistryPostProcessor, InitializingBean, ApplicationContextAware, BeanNameAware {

    private ApplicationContext context;

    private String[] basePackage;

    private String redisInterface;

    @Override
    public void setBeanName(String s) {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        ClassScanner scanner = new ClassScanner(registry, false);
        scanner.setResourceLoader(context);
        scanner.addIncludeFilter((v1, v2) -> {
            String SuperClassName = v1.getClassMetadata().getSuperClassName();
            if (SuperClassName.equals(this.redisInterface)) {
                return true;
            }
            return false;
        });
        scanner.scan(this.basePackage);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    public String[] getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String... basePackage) {
        this.basePackage = basePackage;
    }

    public String getRedisInterface() {
        return redisInterface;
    }

    public void setRedisInterface(String redisInterface) {
        this.redisInterface = redisInterface;
    }
}
