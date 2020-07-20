package com.muye.class1.polymorphism.polymorphism03;

/**
 * @author : gwh
 * @Desc:
 * @date : 2020-06-09 14:25
 **/
public class Animal {

    private String color;

    public Animal(){

    }

    public Animal(String color){
        this.color = color;
    }

    public void call(){
        System.out.println(color+"ヾ(≧O≦)〃嗷~");
    }

    public static void main(String[] args) {
        Animal a = new Cat();
        a.call();

        Animal a1 = new Dog();
        a1.call();
    }
}
