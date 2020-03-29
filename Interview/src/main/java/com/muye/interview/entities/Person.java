package com.muye.interview.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : gwh
 * @date : 2020-03-02 15:16
 **/
@Data
@Getter
@Setter
public class Person {
    private Integer id;
    private String personName;

    public Person(String personName){
        this.personName = personName;
    }

}
