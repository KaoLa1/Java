package com.muye.class1.cycle1control;

import org.junit.Test;

/**
 * @author : gwh
 * @Desc:  --i,  i--,  ++i,   i++
 * @date : 2020-06-09 11:47
 **/
public class AutoIncremnetTest {

    @Test
    public void test(){
        int i = 1;
        int j = 1;
        int m = 1;
        int n = 1;

        int num = i++;
        int num1 = ++j;
        int num3 = m--;
        int num4 = --n;

        System.out.println(num+"---"+num1 +"---"+num3+"---"+num4);
    }

}
