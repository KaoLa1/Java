package com.pattern.create.factory.staticFactory;

import com.pattern.create.factory.Sender;

public class FactoryTest {

    public static void main(String[] args) {
        Sender sender = SendFactory.produceMail();
        sender.Send();
    }
}