package factory.commonFactory;

import factory.Sender;
import factory.commonFactory.SendFactory;

public class FactoryTest {
 
	public static void main(String[] args) {
		SendFactory factory = new SendFactory();
		Sender sender = factory.produce("sms");
		sender.Send();
	}

}