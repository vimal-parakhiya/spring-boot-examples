package com.github.vp.examples.spring.dsl;

import com.github.vp.examples.spring.dsl.annotation.EventHandlingServiceActivator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.integration.config.annotation.MessagingAnnotationPostProcessor;

/**
 * Created by vimalpar on 23/07/17.
 */
public class EventHandlingBeanPostProcessorRegistrar implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        EventHandlingServiceActivatorAnnotationPostProcessor postProcessor = new EventHandlingServiceActivatorAnnotationPostProcessor(beanFactory);

        beanFactory.registerSingleton("eventHandlingServiceActivatorAnnotationPostProcessor", postProcessor);
        beanFactory.getBean(MessagingAnnotationPostProcessor.class)
                .addMessagingAnnotationPostProcessor(EventHandlingServiceActivator.class, postProcessor);
    }
}
