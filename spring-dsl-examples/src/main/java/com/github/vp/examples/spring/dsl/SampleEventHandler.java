package com.github.vp.examples.spring.dsl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

/**
 * Created by vimalpar on 22/07/17.
 */
@Component
public class SampleEventHandler {
    private final static Logger logger = LoggerFactory.getLogger(SampleEventHandler.class);

    @CustomServiceActivator(inputChannel = "event-in")
//    @ServiceActivator(inputChannel = "event-in", adviceChain = "message-advice")
    public void on(SampleEvent event) {
        ApplicationContext applicationContext = null;
        logger.info("Processed event: {}", event);
    }
}
