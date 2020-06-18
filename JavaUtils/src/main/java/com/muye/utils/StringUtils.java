package com.muye.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class StringUtil
{
    public static String strTo16(String s)
    {
        String str = "";
        for (int i = 0; i < s.length(); ++i) {
            int ch = s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }

    public static void main(String[] args) {
        String str = strTo16("123456");
        String pwd = "bonc@123";
        String dealStr = MD5Utils.EncoderByMd5(pwd);
        System.out.println(dealStr);
        System.out.println(str);

        int flowCode = 123;
        Map map = new HashMap();
        map.put("flowCode", Integer.valueOf(flowCode));
        int value = ((Integer)map.get("flowCode")).intValue();
        System.out.println(value);
    }

    public String Chinese2UTF_8(String chineseStr)
            throws Exception
    {
        StringBuffer utf8Str = new StringBuffer();
        byte[] utf8Decode = chineseStr.getBytes("utf-8");
        byte[] arrayOfByte1 = utf8Decode; int i = arrayOfByte1.length; for (int j = 0; j < i; ++j) { byte b = arrayOfByte1[j];
        utf8Str.append(Integer.toHexString(b & 0xFF)); }
        return utf8Str.toString().toUpperCase();
    }

    public static String Chinese2GBK(String chineseStr) throws Exception
    {
        StringBuffer GBKStr = new StringBuffer();
        byte[] GBKDecode = chineseStr.getBytes("gbk");
        byte[] arrayOfByte1 = GBKDecode; int i = arrayOfByte1.length; for (int j = 0; j < i; ++j) { byte b = arrayOfByte1[j];
        GBKStr.append(Integer.toHexString(b & 0xFF)); }
        return GBKStr.toString();
    }

    public String GBK2Chinese(String GBKStr) throws Exception
    {
        byte[] b = HexString2Bytes(GBKStr);
        String chineseStr = new String(b, "gbk");
        return chineseStr;
    }

    public byte[] HexString2Bytes(String hexStr)
    {
        byte[] b = new byte[hexStr.length() / 2];
        for (int i = 0; i < b.length; ++i)
            b[i] = (byte)Integer.parseInt(hexStr.substring(2 * i, 2 * i + 2), 16);
        return b;
    }

    public static final String bytesToHexString(byte[] byteArray)
    {
        StringBuffer hexStr = new StringBuffer(byteArray.length * 2);
        for (int i = 0; i < byteArray.length; ++i) {
            String sTemp = Integer.toHexString(0xFF & byteArray[i]);
            int j = 0;
            while (j < 2 - sTemp.length()) {
                sTemp = "0" + sTemp;
                ++j;
            }
            hexStr.append(sTemp.toUpperCase());
        }
        return hexStr.toString();
    }

    public static String getEncoding(String str) throws UnsupportedEncodingException {
        String iso8859 = new String(str.toString().getBytes("iso8859-1"));
        String gbk = new String(str.toString().getBytes("gbk"));
        String utf8 = new String(str.toString().getBytes("utf-8"));
        String unicode = new String(str.toString().getBytes("unicode"));
        if (iso8859.equals(str.toString()))
            System.out.println("iso8859");
        else if (gbk.equals(str.toString()))
            System.out.println("gbk");
        else if (utf8.equals(str.toString()))
            System.out.println("utf8");
        else if (unicode.equals(str.toString()))
            System.out.println("unicode");

        return "";
    }

    public static boolean isEmpty(String s){
        if (null == s || "".equals(s) || "".equals(s.trim()) || "null".equalsIgnoreCase(s)) {
            return true;
        } else {
            return false;
        }
    }

    public static String generateString(int length) {
        if (length < 1)
            length = 6;
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String genStr = "";
        for (int index = 0; index < length; index++) {
            genStr = genStr + str.charAt((int) ((Math.random() * 100) % 26));
        }
        return genStr;
    }
}
