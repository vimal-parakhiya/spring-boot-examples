package com.github.vp.examples.spring.dsl.eventhandling.query;

import com.github.vp.examples.spring.dsl.annotation.QueryEventHandler;
import com.github.vp.examples.spring.dsl.event.SampleEvent1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import static com.github.vp.examples.spring.dsl.eventhandling.Orders.SAMPLE_EVENT_1_QUERY_HANDLER_1;
import static com.github.vp.examples.spring.dsl.eventhandling.Orders.SAMPLE_EVENT_1_QUERY_HANDLER_2;

/**
 * Created by vimalpar on 22/07/17.
 */
@Component
public class SampleEvent1QueryHandler {
    private final static Logger logger = LoggerFactory.getLogger(SampleEvent1QueryHandler.class);

    @Order(SAMPLE_EVENT_1_QUERY_HANDLER_1)
    @QueryEventHandler
    public void eventHandler1(SampleEvent1 event) {
        logger.info("Processed event: {} using query event handler 1 - Thread: {}", event, Thread.currentThread().getId());
    }

    @Order(SAMPLE_EVENT_1_QUERY_HANDLER_2)
    @QueryEventHandler
    public void eventHandler2(SampleEvent1 event) {
        logger.info("Processed event: {} using query event handler 2 - Thread: {}", event, Thread.currentThread().getId());
    }
}
