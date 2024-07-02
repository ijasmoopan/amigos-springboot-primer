package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import java.util.UUID;

public class Person {

    private final UUID id;

    @NonNull
    private final String name;

    public Person(@JsonProperty("id") UUID id, @NonNull @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }
}
