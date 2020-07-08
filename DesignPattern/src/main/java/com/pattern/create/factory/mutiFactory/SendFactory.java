package com.pattern.create.factory.mutiFactory;

import com.pattern.create.factory.MailSender;
import com.pattern.create.factory.Sender;
import com.pattern.create.factory.SmsSender;

public class SendFactory {

	public Sender produceMail(){
		return new MailSender();
	}

	public Sender produceSms(){
		return new SmsSender();
	}
}