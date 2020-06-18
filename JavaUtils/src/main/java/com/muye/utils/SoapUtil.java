package com.muye.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SoapUtil
{
    private static final Logger logger = LoggerFactory.getLogger(SoapUtil.class);

    public static InputStream getSoapInputStream(String soapRequest, String wsdlUrl, String SOAPAction)
            throws Exception
    {
        URL url;
        try
        {
            url = new URL(wsdlUrl);
            URLConnection conn = url.openConnection();
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Length", Integer.toString(soapRequest.length()));
            conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            conn.setRequestProperty("SOAPAction", SOAPAction);
            OutputStream os = conn.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
            osw.write(soapRequest);
            osw.flush();
            osw.close();
            InputStream is = conn.getInputStream();
            return is;
        } catch (Exception e) {
            logger.error(e.getMessage()); }
        return null;
    }

    public static Document getDocument(String soapRequest, String wsdlUrl, String SOAPAction)
    {
        Document document = null;
        SAXReader saxReader = new SAXReader();
        try {
            URL url = new URL(wsdlUrl);
            URLConnection conn = url.openConnection();
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Length", Integer.toString(soapRequest.length()));
            conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            conn.setRequestProperty("SOAPAction", SOAPAction);
            OutputStream os = conn.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
            osw.write(soapRequest);
            osw.flush();
            osw.close();
            InputStream is = conn.getInputStream();
            document = saxReader.read(is);
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }

        return document;
    }

    public static String formatXml(Document document, String charset, boolean istrans)
    {
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding(charset);
        format.setIndentSize(2);
        format.setNewlines(true);
        format.setTrimText(false);
        format.setPadText(true);

        StringWriter sw = new StringWriter();
        XMLWriter xw = new XMLWriter(sw, format);
        xw.setEscapeText(istrans);
        try {
            xw.write(document);
            xw.flush();
            xw.close();
        } catch (IOException e) {
            System.out.println("格式化XML文档发生异常，请检查！");
            e.printStackTrace();
        }
        return sw.toString();
    }
}
