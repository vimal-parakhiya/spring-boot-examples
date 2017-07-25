package com.github.vp.examples.spring.dsl;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by vimalpar on 22/07/17.
 */
@Aspect
public class EventHandlerAspect{

    @Qualifier("my-advice")
    @Around("execution(* *..*(..))")
    public void advice(ProceedingJoinPoint pjp) throws Throwable {
        pjp.proceed();
    }
}
