package com.pattern.create.abstractFactory;

public class SendSmsFactory implements Provider{
    @Override  
    public Sender produce() {  
        return new SmsSender();  
    }
}