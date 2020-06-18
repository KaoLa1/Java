package com.muye.class1.polymorphism.polymorphism01;

import static com.muye.class1.polymorphism.polymorphism01.Example.test;

/**
 * @author : gwh
 * @Desc: 多态
 * @date : 2020-06-09 10:30
 **/
public class DynamicArray {
    private static final int DEFAULT_CAPACITY = 10;
    protected int size = 0;
    protected int capacity = DEFAULT_CAPACITY;
    protected Integer[] elements = new Integer[DEFAULT_CAPACITY];

    public int size() {
        return this.size;
    }

    public Integer get(int index) {
        return elements[index];
    } //...省略n多方法...

    public void add(Integer e) {
        ensureCapacity();
        elements[size++] = e;
    }

    protected void ensureCapacity() {
        //...如果数组满了就扩容...代码省略...
    }

    public static void main(String[] args) {
        DynamicArray dynamicArray = new SortedDynamicArray();
        // 打印结果：1、3、5
        test(dynamicArray);
    }
}
