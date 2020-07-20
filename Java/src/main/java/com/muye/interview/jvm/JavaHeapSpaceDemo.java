package com.muye.interview.jvm;

/**
 * @author : gwh
 * @Desc:
 **/
public class JavaHeapSpaceDemo {
    public static void main(String[] args) {
        String a = "atgui";
        while (true){
            a +=a+"1111111111111";
        }
    }
}
