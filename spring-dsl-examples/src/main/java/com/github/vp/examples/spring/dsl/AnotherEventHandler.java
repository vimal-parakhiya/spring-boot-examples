package com.github.vp.examples.spring.dsl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

/**
 * Created by vimalpar on 22/07/17.
 */
@Component
public class AnotherEventHandler {
    private final static Logger logger = LoggerFactory.getLogger(SampleEventHandler.class);

    @CustomServiceActivator(inputChannel = "event-in")
//    @ServiceActivator(inputChannel = "event-in", adviceChain = "message-advice")
    public void on(String event) {
        logger.info("Processed another event: {}", event);
    }
}
