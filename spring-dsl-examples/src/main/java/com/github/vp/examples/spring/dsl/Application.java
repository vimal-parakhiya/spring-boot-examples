package com.github.vp.examples.spring.dsl;

import com.github.vp.examples.spring.dsl.event.SampleEvent1;
import com.github.vp.examples.spring.dsl.event.SampleEvent2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

import static com.github.vp.examples.spring.dsl.config.EventHandlingConfiguration.*;

/**
 * Created by vimalpar on 22/07/17.
 */
@SpringBootApplication
public class Application {
    private final static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

        MessageChannel sourceEventChannel = ctx.getBean(SOURCE_EVENT_CHANNEL, MessageChannel.class);
        MessageChannel nonQueryEventChannel = ctx.getBean(NON_QUERY_EVENT_CHANNEL, MessageChannel.class);
        MessageChannel queryEventChannel = ctx.getBean(QUERY_EVENT_CHANNEL, MessageChannel.class);

        SampleEvent1 sampleEvent1 = new SampleEvent1("1");
        SampleEvent2 sampleEvent2 = new SampleEvent2("2");
        String stringEvent = "Hello World!";

        send(sourceEventChannel, sampleEvent1, sampleEvent2, stringEvent);
        send(nonQueryEventChannel, sampleEvent1, sampleEvent2, stringEvent);
        send(queryEventChannel, sampleEvent1, sampleEvent2, stringEvent);

        Thread.sleep(10000);
        ctx.close();
    }

    public static void send(MessageChannel messageChannel, SampleEvent1 event1, SampleEvent2 event2, String stringEvent) {
        logger.info("------------------------------------------------------------------------------------------------------");
        logger.info("Submitted {} to {} channel", event1, messageChannel);
        send(messageChannel, new GenericMessage<>(event1));

        logger.info("Submitted {} to {} channel", event2, messageChannel);
        send(messageChannel, new GenericMessage<>(event2));

        logger.info("Submitted String event to {} channel", messageChannel);
        send(messageChannel, new GenericMessage<>(stringEvent));
        logger.info("------------------------------------------------------------------------------------------------------");
    }

    private static void send(MessageChannel messageChannel, GenericMessage<?> message) {
        try {
            messageChannel.send(message);
        }catch (Exception ex) {
            logger.info("Caught exception while processing message: {} - Error: {}", message, ex.getMessage());
        }
    }


}
