package com.github.vp.examples.spring.dsl;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.handler.advice.AbstractHandleMessageAdvice;
import org.springframework.integration.handler.advice.AbstractRequestHandlerAdvice;
import org.springframework.integration.handler.advice.HandleMessageAdvice;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

/**
 * Created by vimalpar on 22/07/17.
 */
@Configuration
@SpringBootApplication
@IntegrationComponentScan
@EnableAspectJAutoProxy
public class Application {
    private final static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

        DirectChannel channel = (DirectChannel) ctx.getBean("direct.input");
        channel.send(new GenericMessage<>(new SampleEvent("1", "name", "description")));
        channel.send(new GenericMessage<>("Another Event"));
        channel.send(new GenericMessage<>(new SampleEvent("2", "name2", "description2")));

        Thread.sleep(10000);
        ctx.close();
    }

  /*  @MessagingGateway
    public interface EventGateway {
        @Gateway(requestChannel = "event.input")
        float sendObject(Object event);
    }*/

    @Bean("direct.input")
    public DirectChannel directChannel() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow convert() {
        return IntegrationFlows
                .from("direct.input")
                .channel(eventIn())
                .get();
    }

    @Bean(name = "event-in")
    public PublishSubscribeChannel eventIn() {
        return MessageChannels
                .publishSubscribe("even-in")
                .get();
    }

    @Bean("message-advice")
    public Advice handleMessageAdvice() {
        return new AbstractRequestHandlerAdvice() {
            @Override
            protected Object doInvoke(ExecutionCallback callback, Object target, Message<?> message) throws Exception {
                logger.info("Advice target " + target.getClass());
                return callback.execute();
            }
        };
        /*return methodInvocation -> {
            try {
                logger.info("Advice: {}", methodInvocation.getArguments()[0].getClass());
                return methodInvocation.proceed();
            }catch (RuntimeException ex) {
                logger.info("exception: ", ex);
                return null;
            }
        };*/
    }
}
