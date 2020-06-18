package com.muye.utils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class ByteUtils
{
    public static String binstrToStr(String binaryStr)
    {
        StringBuffer results = new StringBuffer();

        Matcher matcher = Pattern.compile("\\d{8}").matcher(binaryStr);
        BASE64Decoder d = new BASE64Decoder();
        while (matcher.find()) {
            int intVal = Integer.valueOf(matcher.group(), 2).intValue();
            results.append((char)intVal);
        }
        String str = null;
        try {
            str = new String(d.decodeBuffer(results.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return str;
    }

    public static String strToBinstr(String str)
    {
        BASE64Encoder e = new BASE64Encoder();
        str = e.encode(str.getBytes());
        StringBuffer stringBuffer = new StringBuffer();
        char[] arrayOfChar = str.toCharArray(); int i = arrayOfChar.length; for (int j = 0; j < i; ++j) { char c = arrayOfChar[j];
        String binaryStr = Integer.toBinaryString(c);
        String format = String.format("%8s", new Object[] { binaryStr });
        format = format.replace(" ", "0");

        stringBuffer.append(format);
    }
        return stringBuffer.toString();
    }

    public static void main(String[] args)
    {
        String str = "电信事业部-黄俊伟 20180103";
        String binStr = strToBinstr(str);
        System.out.println(binStr);
        String newStr = binstrToStr(binStr);
        System.out.println(newStr);
    }
}
