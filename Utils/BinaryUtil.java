package com.watchme.dspcoresupport.utils;

public class BinaryUtil
{
    private static final String T_TO_ONE = "\t";
    private static final String ONE_TO_T = "1";
    private static final String R_TO_ZERO = "﻿";
    private static final String ZERO_TO_R = "0";
    private static final String SPACE_TO_SIGN = " ";
    private static final String SIGN_TO_SPACE = " ";

    public static String getEntryStr(String keyword)
    {
        String binaryStr = strToBinstr(keyword);
        String entryStr = binaryStr.replace("0", "﻿").replace("1", "\t").replace(" ", " ");
        System.out.println(entryStr);
        return entryStr;
    }

    public static String getDecryptStr(String entryStr) {
        String binaryStr = entryStr.replace("\t", "1").replace("﻿", "0").replace(" ", " ");
        String decryptStr = binstrToStr(binaryStr);
        return decryptStr;
    }

    private static String strToBinstr(String str)
    {
        char[] strChar = str.toCharArray();
        String result = "";
        for (int i = 0; i < strChar.length; ++i)
            result = result + Integer.toBinaryString(strChar[i]) + " ";

        return result;
    }

    private static String binstrToStr(String binStr)
    {
        String[] tempStr = strToStrArray(binStr);
        char[] tempChar = new char[tempStr.length];
        for (int i = 0; i < tempStr.length; ++i)
            tempChar[i] = binstrToChar(tempStr[i]);

        return String.valueOf(tempChar);
    }

    private static char binstrToChar(String binStr) {
        int[] temp = binstrToIntArray(binStr);
        int sum = 0;
        for (int i = 0; i < temp.length; ++i)
            sum += (temp[(temp.length - 1 - i)] << i);

        return (char)sum;
    }

    private static String[] strToStrArray(String str)
    {
        return str.split(" ");
    }

    private static int[] binstrToIntArray(String binStr)
    {
        char[] temp = binStr.toCharArray();
        int[] result = new int[temp.length];
        for (int i = 0; i < temp.length; ++i)
            result[i] = (temp[i] - '0');

        return result;
    }

    public static void main(String[] args)
    {
        String name = "部门-小强";
        String entryStr = getEntryStr(name);
        System.out.println(entryStr);
        String decryptStr = getDecryptStr(entryStr);
        System.out.println(decryptStr);
    }
}
