package factory.staticFactory;

import factory.MailSender;
import factory.Sender;
import factory.SmsSender;

public class SendFactory {
	
	public static Sender produceMail(){
		return new MailSender();
	}
	
	public static Sender produceSms(){
		return new SmsSender();
	}
}