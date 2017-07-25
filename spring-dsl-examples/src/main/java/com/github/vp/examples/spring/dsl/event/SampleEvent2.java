package com.github.vp.examples.spring.dsl.event;

/**
 * Created by vimalpar on 22/07/17.
 */
public class SampleEvent2 {
    private String id;

    public SampleEvent2(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "SampleEvent2{" +
                "id='" + id + '\'' +
                '}';
    }
}
