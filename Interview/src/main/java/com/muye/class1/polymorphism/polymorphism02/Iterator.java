package com.muye.class1.polymorphism.polymorphism02;

public interface Iterator {
    boolean hasNext();

    String next();

    String remove();
}

class Array implements Iterator {
    private String[] data;

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public String next() {
        return "";
    }

    @Override
    public String remove() {
        return "";
    }
    //...省略其他方法...
}

class LinkedList implements Iterator {

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public String next() {
        return "";
    }

    @Override
    public String remove() {
        return "";
    }
    //...省略其他方法...
}

class Demo {
    public static void print(Iterator iterator) {
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public static void main(String[] args) {
        Iterator arrayIterator = new Array();
        print(arrayIterator);

        Iterator linkedListIterator = new LinkedList();
        print(linkedListIterator);
    }
}