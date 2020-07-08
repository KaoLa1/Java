package com.pattern.create.factory.commonFactory;

import com.pattern.create.factory.MailSender;
import com.pattern.create.factory.Sender;
import com.pattern.create.factory.SmsSender;

public class SendFactory {
 
	public Sender produce(String type) {
		if ("mail".equals(type)) {
			return new MailSender();
		} else if ("sms".equals(type)) {
			return new SmsSender();
		} else {
			System.out.println("请输入正确的类型!");
			return null;
		}
	}
}