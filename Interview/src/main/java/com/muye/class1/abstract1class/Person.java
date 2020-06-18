package com.muye.class1.abstract1class;

/**
 * @author gwh
 */
public  abstract class Person {

    private String name;

    /**
     * 获取信息
     * @return
     */
    public abstract String getMessage();

    public void walk(){
        System.out.println("walk...walk");
    }

    class student extends Person{

        @Override
        public String getMessage() {
            return "看手机";
        }
    }

    public static void main(String[] args) {

    }

}
