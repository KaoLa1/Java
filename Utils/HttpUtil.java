package com.watchme.dspcoresupport.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;
import org.apache.log4j.Logger;

public class HttpUtil
{
    private static final Logger logger = Logger.getLogger(HttpUtil.class);
    private static int CON_TIME_OUT = 60000;
    private static int READ_TIME_OUT = 120000;

    public static String doPost(String url, Map<String, Object> params)
    {
        URL u = null;
        HttpURLConnection con = null;
        StringBuffer sb = new StringBuffer();
        if (params != null) {
            for (Iterator localIterator = params.entrySet().iterator(); localIterator.hasNext(); ) {
                Map.Entry e = (Map.Entry)localIterator.next();
                sb.append(e.getKey().toString());
                sb.append("=");
                sb.append(e.getValue());
                sb.append("&");
            }
            sb.toString().substring(0, sb.length() - 1);
        }

        OutputStreamWriter osw = null;
        try {
            u = new URL(url);
            con = (HttpURLConnection)u.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setConnectTimeout(CON_TIME_OUT);
            con.setReadTimeout(READ_TIME_OUT);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
            String cont = sb.substring(0, sb.length() - 1).toString();
            osw.write(cont.toString());
            osw.flush();
        } catch (Exception e) {
            logger.error("API对接异常！" + url + "参数——》" + sb.substring(0, sb.length() - 1).toString());
            e.printStackTrace();
        } finally {
            if (con != null)
                con.disconnect();

            if (osw != null)
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


        }

        StringBuffer buffer = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String temp;
            while ((temp = br.readLine()) != null) {
                buffer.append(temp);
                buffer.append("\n");
            }
        } catch (Exception e) {
            logger.error("API数据读取异常！" + url + "参数——》" + sb.substring(0, sb.length() - 1).toString());
            e.printStackTrace();
        }
        return buffer.toString();
    }

    public static String doGet(String url, Map<String, Object> params) {
        URL u = null;
        HttpURLConnection con = null;

        StringBuffer sb = new StringBuffer();
        if (params != null) {
            for (Iterator localIterator = params.entrySet().iterator(); localIterator.hasNext(); ) { Map.Entry e = (Map.Entry)localIterator.next();
                sb.append((String)e.getKey());
                sb.append("=");
                sb.append(e.getValue());
                sb.append("&");
            }
            sb.substring(0, sb.length() - 1);
        }
        StringBuffer result = new StringBuffer();

        BufferedReader reader = null;
        try
        {
            if (!("".equals(sb.toString().trim())))
                url = url + "?" + sb.substring(0, sb.length() - 1).toString();

            u = new URL(url);
            con = (HttpURLConnection)u.openConnection();
            con.setConnectTimeout(CON_TIME_OUT);
            con.setReadTimeout(READ_TIME_OUT);
            con.connect();
            System.out.println("[doGet]ResponseCode=====" + con.getResponseCode());
            reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String lines;
            while ((lines = reader.readLine()) != null) {
                result.append(lines);
            }
        } catch (Exception e) {
            logger.error("API对接异常！" + url + "参数——》" + sb.substring(0, sb.length() - 1).toString());
            System.out.println("API对接异常！" + url + "参数——》" + sb.substring(0, sb.length() - 1).toString());
            e.printStackTrace();
            String str1 = "ERROR";

            return str1;
        }
        finally
        {
            if (con != null)
                con.disconnect();

            if (reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        return result.toString();
    }

    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);

            URLConnection conn = realUrl.openConnection();

            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");

            conn.setDoOutput(true);
            conn.setDoInput(true);

            out = new PrintWriter(conn.getOutputStream());

            out.print(param);

            out.flush();

            in = new BufferedReader(new InputStreamReader(conn.getInputStream
                    ()));
            String line;
            while ((line = in.readLine()) != null) {
                result = result + line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        finally
        {
            try {
                if (out != null)
                    out.close();

                if (in != null)
                    in.close();
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        return result;
    }
}
