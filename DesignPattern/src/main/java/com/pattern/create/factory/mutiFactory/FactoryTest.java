package com.pattern.create.factory.mutiFactory;

import com.pattern.create.factory.Sender;

public class FactoryTest {
 
	public static void main(String[] args) {
		SendFactory factory = new SendFactory();
		Sender sender = factory.produceMail();
		sender.Send();
	}
}