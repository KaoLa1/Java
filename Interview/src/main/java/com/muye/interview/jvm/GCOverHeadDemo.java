package com.muye.interview.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : gwh
 * @Desc:
 **/
public class GCOverHeadDemo {
    public static void main(String[] args) {
        int i = 0 ;
        List<String> list = new ArrayList<>();
        try{
            while (true){
                list.add(String.valueOf(++i));
            }
        }catch (Exception e){
            System.out.println("******+i:"+i);
            e.printStackTrace();
            throw e;
        }



    }

}
