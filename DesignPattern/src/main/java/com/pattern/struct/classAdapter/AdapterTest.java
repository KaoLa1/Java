package com.pattern.struct.classAdapter;

import com.pattern.struct.Targetable;
import com.pattern.struct.classAdapter.Adapter;

public class AdapterTest {
    public static void main(String[] args) {  
        Targetable target = new Adapter();
        target.method1();
        target.method2();  
    }
 }