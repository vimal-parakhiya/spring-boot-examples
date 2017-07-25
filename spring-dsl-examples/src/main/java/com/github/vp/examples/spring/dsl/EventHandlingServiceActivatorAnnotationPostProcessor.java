package com.github.vp.examples.spring.dsl;

import com.github.vp.examples.spring.dsl.annotation.EventHandlingServiceActivator;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.integration.config.annotation.AbstractMethodAnnotationPostProcessor;
import org.springframework.messaging.MessageHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by vimalpar on 23/07/17.
 */
public class EventHandlingServiceActivatorAnnotationPostProcessor extends AbstractMethodAnnotationPostProcessor<EventHandlingServiceActivator> {

    public EventHandlingServiceActivatorAnnotationPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        super(beanFactory);
    }

    @Override
    protected MessageHandler createHandler(Object bean, Method method, List<Annotation> annotations) {
        return new EventHandlingServiceActivatorHandler(bean, method);
    }
}
