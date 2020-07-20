package com.muye.interview.cas;

import lombok.Builder;
import lombok.Data;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author : gwh
 * @date : 2020-02-29 08:00
 **/
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User z3 = new User("z3",22);
        User l4 = new User("l4",21);

        AtomicReference<User> atomicReference = new AtomicReference<User>();
        atomicReference.set(z3);
        boolean b = atomicReference.compareAndSet(z3, l4);
        System.out.println(b+"/t"+atomicReference.get().toString());
        boolean b1 = atomicReference.compareAndSet(z3, l4);
        System.out.println(b1+"/t"+atomicReference.get().toString());


    }
}
@Data
@Builder
class User{
    public String username;
    public int age;
}