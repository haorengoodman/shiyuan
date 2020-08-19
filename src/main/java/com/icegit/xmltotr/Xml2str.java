package com.icegit.xmltotr;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * xml 2 json
 * xml對象序列化
 * 參考文檔 https://blog.csdn.net/a532672728/article/details/76312475
 * https://blog.csdn.net/baidu_38322198/article/details/90241641
 */
public class Xml2str {
    public static String filePath = "E:\\data\\xmldata.xml";

/*    public static void main(String[] args) throws Exception{
        FileInputStream fin = new FileInputStream(filePath);
        SAXReader reader = new SAXReader();
        Document document = reader.read(fin);
        String xml = IOUtils.toString(fin,"UTF-8");
        System.out.println(xml);

    }*/

    public static String xml2jsonString() throws JSONException, IOException {
        FileInputStream fin = new FileInputStream(filePath);
        String xml = IOUtils.toString(fin,"utf-8");
        JSONObject xmlJSONObj = XML.toJSONObject(xml);
        return xmlJSONObj.toString();
    }

    public static void main(String[] args) throws JSONException, IOException{
        String rexult = xml2jsonString();
        System.out.println(rexult);
    }

}
