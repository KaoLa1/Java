package com.pattern.create.factory.staticFactory;

import com.pattern.create.factory.MailSender;
import com.pattern.create.factory.Sender;
import com.pattern.create.factory.SmsSender;

public class SendFactory {
	
	public static Sender produceMail(){
		return new MailSender();
	}
	
	public static Sender produceSms(){
		return new SmsSender();
	}
}