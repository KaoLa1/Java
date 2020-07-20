package com.muye.collection.iterator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @Desc:
 *
 * @author : gwh
 * @date : 2020-07-19 10:26
 **/
public class IteratorTest {
    public static void main(String[] args) {
        Collection collection = new ArrayList();
        collection.add(1);
        collection.add(2);
        collection.add(3);
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next()+"  ");
        }
    }
}
