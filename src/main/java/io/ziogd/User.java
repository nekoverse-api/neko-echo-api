package io.ziogd;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class User {
    private final String sid;
    private final String name;
    private final int age;

    @JsonCreator
    public User(String sid, String name, int age) {
        this.sid = sid;
        this.name = name;
        this.age = age;
    }

     public String getSid() {
        return sid;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}