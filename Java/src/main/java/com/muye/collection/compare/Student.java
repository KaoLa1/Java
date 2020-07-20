package com.muye.collection.compare;

import lombok.Data;

@Data
public class Student {

    private String name;
    private Integer age;
    private Integer score;

    public Student(String name, Integer age, Integer score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }


}