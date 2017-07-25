package com.github.vp.examples.spring.dsl;

/**
 * Created by vimalpar on 23/07/17.
 */

import org.springframework.integration.annotation.Poller;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that a method is capable of handling a message or message payload.
 * <p>
 * A method annotated with @ServiceActivator may accept a parameter of type
 * {@link org.springframework.messaging.Message} or of the expected
 * Message payload's type. Any type conversion supported by
 * {@link org.springframework.beans.SimpleTypeConverter} will be applied to
 * the Message payload if necessary. Header values can also be passed as
 * Message parameters by using the
 * {@link org.springframework.messaging.handler.annotation.Header @Header} parameter annotation.
 * <p>
 * Return values from the annotated method may be of any type. If the return
 * value is not a Message, a reply Message will be created with that object
 * as its payload.
 *
 * @author Mark Fisher
 * @author Gary Russell
 * @author Artem Bilan
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CustomServiceActivator {

    /**
     * Specify the channel from which this service activator will consume messages.
     * If the channel does not exist, a {@code DirectChannel} with this name will be
     * registered in the application context.
     * @return The channel name.
     */
    String inputChannel() default "";

    /**
     * Specify the channel to which this service activator will send any replies.
     * @return The channel name.
     */
    String outputChannel() default "";

    /**
     * Specify whether the service method must return a non-null value. This value is
     * {@code false} by default, but if set to {@code true}, a
     * {@link org.springframework.integration.handler.ReplyRequiredException} will is thrown when
     * the underlying service method (or expression) returns a null value.
     * Can be specified as 'property placeholder', e.g. {@code ${spring.integration.requiresReply}}.
     * @return the requires reply flag.
     */
    String requiresReply() default "";

    /**
     * Specify a "chain" of {@code Advice} beans that will "wrap" the message handler.
     * Only the handler is advised, not the downstream flow.
     * @return the advice chain.
     */
    String[] adviceChain() default {};

    /**
     * Specify the maximum amount of time in milliseconds to wait when sending a reply
     * {@link org.springframework.messaging.Message} to the {@code outputChannel}.
     * Defaults to {@code -1} - blocking indefinitely.
     * It is applied only if the output channel has some 'sending' limitations, e.g.
     * {@link org.springframework.integration.channel.QueueChannel} with
     * fixed a 'capacity'. In this case a {@link org.springframework.messaging.MessageDeliveryException} is thrown.
     * The 'sendTimeout' is ignored in case of
     * {@link org.springframework.integration.channel.AbstractSubscribableChannel} implementations.
     * Can be specified as 'property placeholder', e.g. {@code ${spring.integration.sendTimeout}}.
     * @return The timeout for sending results to the reply target (in milliseconds)
     */
    String sendTimeout() default "";

    /**
     * The {@link org.springframework.context.SmartLifecycle} {@code autoStartup} option.
     * Can be specified as 'property placeholder', e.g. {@code ${foo.autoStartup}}.
     * Defaults to {@code true}.
     * @return the auto startup {@code boolean} flag.
     */
    String autoStartup() default "";

    /**
     * Specify a {@link org.springframework.context.SmartLifecycle} {@code phase} option.
     * Defaults {@code 0} for {@link org.springframework.integration.endpoint.PollingConsumer}
     * and {@code Integer.MIN_VALUE} for {@link org.springframework.integration.endpoint.EventDrivenConsumer}.
     * Can be specified as 'property placeholder', e.g. {@code ${foo.phase}}.
     * @return the {@code SmartLifecycle} phase.
     */
    String phase() default "";

    /**
     * @return the {@link Poller} options for a polled endpoint
     * ({@link org.springframework.integration.scheduling.PollerMetadata}).
     * This attribute is an {@code array} just to allow an empty default (no poller).
     * Only one {@link Poller} element is allowed.
     */
    Poller[] poller() default {};

}
