package com.github.vp.examples.spring.dsl.annotation;

/**
 * Created by vimalpar on 23/07/17.
 */

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface EventHandlingServiceActivator {

    /**
     * Specify the channel from which this service activator will consume messages.
     * If the channel does not exist, a {@code DirectChannel} with this name will be
     * registered in the application context.
     * @return The channel name.
     */
    String inputChannel() default "";

    /**
     * Specify a "chain" of {@code Advice} beans that will "wrap" the message handler.
     * Only the handler is advised, not the downstream flow.
     * @return the advice chain.
     */
    String[] adviceChain() default {};
}
