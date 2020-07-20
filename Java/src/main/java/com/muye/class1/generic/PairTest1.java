package com.muye.class1.generic; 
/**
 * @Desc:
 *
 * @author : gwh
 * @date : 2020-07-19 09:14
 **/
public class PairTest1 {
    public static void main(String[] args) {
        String[] words = {"Mary","had","a","little","lamb"};
        Pair<String> minmax = ArrayAlg.minmax(words);
        System.out.println(minmax.getFirst());
        System.out.println(minmax.getSecond());
    }
}
