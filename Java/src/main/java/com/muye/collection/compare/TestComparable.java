package com.muye.collection.compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Desc:
 *
 * @author : gwh
 * @date : 2020-07-19 10:08
 **/
public class TestComparable {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setAge(11);
        User user2 = new User();
        user2.setAge(22);

        users.add(user1);
        users.add(user2);
        Collections.sort(users);

        System.out.println(users);
    }
}
