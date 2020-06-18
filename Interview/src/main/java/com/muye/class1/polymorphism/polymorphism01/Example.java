package com.muye.class1.polymorphism.polymorphism01;


/**
 * @author gwh
 */
public class Example {
    public static void test(DynamicArray dynamicArray) {
        dynamicArray.add(5);
        dynamicArray.add(1);
        dynamicArray.add(3);
        for (int i = 0; i < dynamicArray.size(); ++i) {
            System.out.println(dynamicArray.get(i));
        }
    }
}