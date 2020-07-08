package com.pattern.create.singleton;

/**
 * @author : gwh
 * @Desc: 饿汉模式
 **/
public class HSingleton {

    private static HSingleton instance = new HSingleton();

    private HSingleton() {
    }

    public static HSingleton getInstance() {
        return instance;
    }

}
