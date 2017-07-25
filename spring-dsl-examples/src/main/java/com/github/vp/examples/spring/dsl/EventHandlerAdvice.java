package com.github.vp.examples.spring.dsl;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by vimalpar on 22/07/17.
 */
@Component
public class EventHandlerAdvice implements Advice, org.aopalliance.intercept.MethodInterceptor {
    private final static Logger logger = LoggerFactory.getLogger(EventHandlerAdvice.class);
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        logger.info("Method Invocation: {}", invocation);
        return invocation.proceed();
    }
}
