package com.github.vp.examples.spring.dsl.annotation;

/**
 * Created by vimalpar on 23/07/17.
 */

import java.lang.annotation.*;

import static com.github.vp.examples.spring.dsl.config.EventHandlingConfiguration.QUERY_EVENT_CHANNEL;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@EventHandlingServiceActivator(inputChannel = QUERY_EVENT_CHANNEL)
public @interface QueryEventHandler {
}
