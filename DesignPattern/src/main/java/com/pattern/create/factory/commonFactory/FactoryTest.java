package com.pattern.create.factory.commonFactory;

import com.pattern.create.factory.Sender;

public class FactoryTest {
 
	public static void main(String[] args) {
		SendFactory factory = new SendFactory();
		Sender sender = factory.produce("sms");
		sender.Send();
	}

}