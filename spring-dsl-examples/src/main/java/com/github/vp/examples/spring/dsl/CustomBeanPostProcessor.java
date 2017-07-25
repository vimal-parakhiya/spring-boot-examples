package com.github.vp.examples.spring.dsl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.integration.config.annotation.MessagingAnnotationPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by vimalpar on 23/07/17.
 */
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor, BeanFactoryAware, ApplicationContextAware {
    private final static Logger logger = LoggerFactory.getLogger(CustomBeanPostProcessor.class);
    private ConfigurableListableBeanFactory beanFactory;
    private ApplicationContext applicationContext;


    public CustomBeanPostProcessor() {
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        logger.info("Creating bean: {}", beanName);
        beanFactory.getBean(MessagingAnnotationPostProcessor.class)
                .addMessagingAnnotationPostProcessor(CustomServiceActivator.class, new CustomServiceActivatorAnnotationPostProcessor(beanFactory));
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        logger.info("Created bean: {}", beanName);
        return bean;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableListableBeanFactory)beanFactory;

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
