package com.github.vp.examples.spring.dsl;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.integration.config.annotation.AbstractMethodAnnotationPostProcessor;
import org.springframework.integration.handler.ServiceActivatingHandler;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by vimalpar on 23/07/17.
 */
@Component
public class CustomServiceActivatorAnnotationPostProcessor extends AbstractMethodAnnotationPostProcessor<CustomServiceActivator> {

    public CustomServiceActivatorAnnotationPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        super(beanFactory);
    }

    @Override
    protected MessageHandler createHandler(Object bean, Method method, List<Annotation> annotations) {
        return new CustomServiceActivatorHandler(bean, method);
    }
}
