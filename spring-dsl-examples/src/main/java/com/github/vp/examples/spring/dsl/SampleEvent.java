package com.github.vp.examples.spring.dsl;

/**
 * Created by vimalpar on 22/07/17.
 */
public class SampleEvent {
    private String id;
    private String name;
    private String description;

    public SampleEvent(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "SampleEvent{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
