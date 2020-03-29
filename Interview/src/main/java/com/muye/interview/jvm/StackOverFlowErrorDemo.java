package com.muye.interview.jvm;

/**
 * @author : gwh
 * @Desc:
 **/
public class StackOverFlowErrorDemo {
    public static void main(String[] args) {
        stackOverFlowError();
    }

    private static void stackOverFlowError() {
        stackOverFlowError();
    }
}
