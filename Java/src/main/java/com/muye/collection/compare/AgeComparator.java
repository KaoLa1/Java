package com.muye.collection.compare;

import java.util.Comparator;

class AgeComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        if (o1.getAge() > o2.getAge()) {
            return 1;
        } else if (o1.getAge() < o2.getAge()) {
            return -1;
        } else {
            return 0;
        }
    }
}