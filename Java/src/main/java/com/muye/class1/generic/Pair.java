package com.muye.class1.generic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : gwh
 * @Desc:
 * @date : 2020-07-19 09:04
 **/
@Data
@Builder
public class Pair<T> {

    private T first;
    private T second;

    public Pair(){
        first = null;
        second = null;
    }

    public Pair(T first,T second){
        this.first = first;
        this.second = second;
    }

}
