package com.pattern.struct.object;

import com.pattern.struct.Source;
import com.pattern.struct.Targetable;

public class Wrapper implements Targetable {
    private Source source;

    public Wrapper(Source source){  
        super();  
        this.source = source;  
    }  
    @Override  
    public void method2() {  
        System.out.println("this is the targetable method!");  
    }  

    @Override  
    public void method1() {  
        source.method1();  
    }
 }