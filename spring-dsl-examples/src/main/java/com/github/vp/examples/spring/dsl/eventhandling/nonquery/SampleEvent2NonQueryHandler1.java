package com.github.vp.examples.spring.dsl.eventhandling.nonquery;

import com.github.vp.examples.spring.dsl.annotation.NonQueryEventHandler;
import com.github.vp.examples.spring.dsl.event.SampleEvent2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import static com.github.vp.examples.spring.dsl.eventhandling.Orders.SAMPLE_EVENT_2_NON_QUERY_HANDLER_1;

/**
 * Created by vimalpar on 22/07/17.
 */
@Order(SAMPLE_EVENT_2_NON_QUERY_HANDLER_1)
@Component
public class SampleEvent2NonQueryHandler1 {
    private final static Logger logger = LoggerFactory.getLogger(SampleEvent2NonQueryHandler1.class);

    @NonQueryEventHandler
    public void eventHandler(SampleEvent2 event) {
        logger.info("Processed event: {} using non-query event handler 1 - Thread: {}", event, Thread.currentThread().getId());
    }
}
