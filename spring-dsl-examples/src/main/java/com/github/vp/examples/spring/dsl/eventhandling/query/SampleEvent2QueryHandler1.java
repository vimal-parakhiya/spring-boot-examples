package com.github.vp.examples.spring.dsl.eventhandling.query;

import com.github.vp.examples.spring.dsl.annotation.QueryEventHandler;
import com.github.vp.examples.spring.dsl.event.SampleEvent2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import static com.github.vp.examples.spring.dsl.eventhandling.Orders.SAMPLE_EVENT_2_QUERY_HANDLER_1;

/**
 * Created by vimalpar on 22/07/17.
 */
@Order(SAMPLE_EVENT_2_QUERY_HANDLER_1)
@Component
public class SampleEvent2QueryHandler1 {
    private final static Logger logger = LoggerFactory.getLogger(SampleEvent2QueryHandler1.class);

    @QueryEventHandler
    public void eventHandler(SampleEvent2 event) {
        logger.info("Processed event: {} using query event handler 1 - Thread: {}", event, Thread.currentThread().getId());
    }
}
