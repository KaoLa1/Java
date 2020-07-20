package com.muye.thread.cas;

import entity.User;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author gwh
 * @DeSC 原子引用
 */
public class AtomicReferenceDemo {

    public static void main(String[] args) {
        User zs = new User("zs", 22);
        User ls = new User("ls", 22);
        AtomicReference<User> userAtomicReference = new AtomicReference<>();
        userAtomicReference.set(zs);
        System.out.println(userAtomicReference.compareAndSet(zs, ls) + "\t" + userAtomicReference.get().toString());
        System.out.println(userAtomicReference.compareAndSet(zs, ls) + "\t" + userAtomicReference.get().toString());
    }

}