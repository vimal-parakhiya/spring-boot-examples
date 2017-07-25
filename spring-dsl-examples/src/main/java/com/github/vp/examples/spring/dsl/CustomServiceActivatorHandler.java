package com.github.vp.examples.spring.dsl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.handler.ServiceActivatingHandler;
import org.springframework.integration.selector.PayloadTypeSelector;
import org.springframework.messaging.Message;

import java.lang.reflect.Method;

/**
 * Created by vimalpar on 23/07/17.
 */
public class CustomServiceActivatorHandler extends ServiceActivatingHandler {
    private final static Logger logger = LoggerFactory.getLogger(CustomServiceActivatorHandler.class);
    private Method method;

    public CustomServiceActivatorHandler(Object object, Method method) {
        super(object, method);
        this.method = method;
    }

    @Override
    protected Object handleRequestMessage(Message<?> message) {
        if(new PayloadTypeSelector(method.getParameterTypes()[0]).accept(message)) {
            return super.handleRequestMessage(message);
        }
        return null;
    }
}
