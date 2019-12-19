package com.muye.exercise.java_03_mq.topic;
import javax.jms.JMSException;

public class ProducerTest {      
     
    /**    
     * @param args    
     */     
    public static void main(String[] args) throws JMSException, Exception {      
        ProducerTool producer = new ProducerTool();     
        producer.produceMessage("Hello, world!");      
        producer.close();
    }      
}      

