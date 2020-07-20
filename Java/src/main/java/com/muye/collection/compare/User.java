package com.muye.collection.compare;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class User implements Comparable<User> {

    private String name;
    private int age;

    @Override
    public int compareTo(User o) {
        return o.getAge() - this.getAge();
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
