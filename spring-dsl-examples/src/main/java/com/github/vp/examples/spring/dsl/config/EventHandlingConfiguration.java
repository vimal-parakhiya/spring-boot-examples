package com.github.vp.examples.spring.dsl.config;

import com.github.vp.examples.spring.dsl.EventHandlingBeanPostProcessorRegistrar;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlowBuilder;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.router.AbstractMessageRouter;
import org.springframework.integration.router.RecipientListRouter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;

import java.util.Collection;

import static java.util.Arrays.asList;

/**
 * Created by vimalpar on 25/07/17.
 */
@Configuration
@IntegrationComponentScan
public class EventHandlingConfiguration {

    public static final String NON_QUERY_EVENT_CHANNEL = "non-query-event-channel";
    public static final String QUERY_EVENT_CHANNEL = "query-event-channel";
    public static final String SOURCE_EVENT_CHANNEL = "source-event-channel";

    @Bean(SOURCE_EVENT_CHANNEL)
    public DirectChannel directChannel() {
        DirectChannel channel = new DirectChannel();
        return channel;
    }

    @Bean
    public IntegrationFlow convert(@Qualifier(QUERY_EVENT_CHANNEL) PublishSubscribeChannel queryEventChannel,
                                   @Qualifier(NON_QUERY_EVENT_CHANNEL) PublishSubscribeChannel nonQueryEventChannel) {
        return IntegrationFlows
                .from(SOURCE_EVENT_CHANNEL)
                .route(messageRouter(queryEventChannel, nonQueryEventChannel))
                .get();
    }

    private AbstractMessageRouter messageRouter(final PublishSubscribeChannel queryEventChannel, final PublishSubscribeChannel nonQueryEventChannel) {
        RecipientListRouter router = new RecipientListRouter();
        router.setChannels(asList(nonQueryEventChannel, queryEventChannel));
        return router;
    }

    @Bean(name = QUERY_EVENT_CHANNEL)
    public PublishSubscribeChannel queryEventChannel() {
        return MessageChannels
                .publishSubscribe(QUERY_EVENT_CHANNEL)
                .get();
    }

    @Bean(name = NON_QUERY_EVENT_CHANNEL)
    public PublishSubscribeChannel nonQueryEventChannel() {
        return MessageChannels
                .publishSubscribe(NON_QUERY_EVENT_CHANNEL)
                .get();
    }

    @Bean
    public EventHandlingBeanPostProcessorRegistrar eventHandlingBeanPostProcessorRegistrar() {
        return new EventHandlingBeanPostProcessorRegistrar();
    }
}
