package com.muye.utils;

public class BinaryUtilToCsv
{
    private static final String T_TO_ONE = "\t";
    private static final String ONE_TO_T = "1";
    private static final String SPACE_TO_ZERO = " ";
    private static final String ZERO_TO_SPACE = "0";

    public static String getEntryStr(String keyword)
    {
        String binaryStr = ByteUtils.strToBinstr(keyword);
        String entryStr = binaryStr.replace("0", " ").replace("1", "\t");
        return entryStr;
    }

    public static String getDecryptStr(String entryStr)
    {
        String binaryStr = entryStr.replace("\t", "1").replace(" ", "0");
        String str = ByteUtils.binstrToStr(binaryStr);
        return str;
    }

    public static void main(String[] args)
    {
        String name = "魏国栋-东方国信 20180330";
        String entryStr = getEntryStr(name);
        System.out.println(entryStr);
        String decryptStr = getDecryptStr(entryStr);
        System.out.println(decryptStr);
    }
}
