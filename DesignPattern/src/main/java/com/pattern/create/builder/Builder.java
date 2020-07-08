package com.pattern.create.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther gwh
 *
 * 建造者模式
 */
public class Builder {
    private List<Sender> list = new ArrayList<Sender>();

    public void produceMailSender(int count){  
        for(int i=0; i<count; i++){  
            list.add(new MailSender());  
        }  
    }  

    public void produceSmsSender(int count){  
        for(int i=0; i<count; i++){  
            list.add(new SmsSender());  
        }  
    }
}