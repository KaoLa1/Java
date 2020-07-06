package factory.mutiFactory;

import factory.MailSender;
import factory.Sender;
import factory.SmsSender;

public class SendFactory {

	public Sender produceMail(){
		return new MailSender();
	}

	public Sender produceSms(){
		return new SmsSender();
	}
}