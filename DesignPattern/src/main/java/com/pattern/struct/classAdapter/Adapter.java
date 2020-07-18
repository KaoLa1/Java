package com.pattern.struct.classAdapter;

import com.pattern.struct.Source;
import com.pattern.struct.Targetable;

public class Adapter extends Source implements Targetable {
    @Override  
    public void method2() {  
        System.out.println("this is the targetable method!");  
    }
}