package com.github.vp.examples.spring.dsl.eventhandling;

import com.github.vp.examples.spring.dsl.annotation.NonQueryEventHandler;
import com.github.vp.examples.spring.dsl.annotation.QueryEventHandler;
import com.github.vp.examples.spring.dsl.eventhandling.nonquery.SampleEvent1NonQueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by vimalpar on 22/07/17.
 */
@Component
public class StringEventHandler {
    private final static Logger logger = LoggerFactory.getLogger(SampleEvent1NonQueryHandler.class);

    @NonQueryEventHandler
    public void nonQueryEventHandler(String event) {
        logger.info("Processed event: \"{}\" using non-query event handler - Thread: {}", event, Thread.currentThread().getId());
    }

    @QueryEventHandler
    public void queryEventHandler(String event) {
        logger.info("Processed event: \"{}\" using query event handler - Thread: {}", event, Thread.currentThread().getId());
    }
}
