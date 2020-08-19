package com.icegit.xmltotr;

import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import java.io.FileInputStream;

/**
 * xml對象序列化
 * 參考文檔 https://blog.csdn.net/a532672728/article/details/76312475
 */
public class Xml2str {
    public static String filePath = "E:\\data\\xmldata.xml";

    public static void main(String[] args) throws Exception{
        FileInputStream fin = new FileInputStream(filePath);
        SAXReader reader = new SAXReader();
        Document document = reader.read(fin);
        String xml = IOUtils.toString(fin,"UTF-8");
        System.out.println(xml);

    }

}
