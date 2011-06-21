package com.shawben.sui;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

/*
 * 操作XML文件的业务类*/
public class SAXXmlService {

	
	public static List<ImageCache> xmlOpera(InputStream instream){
		//新建解析工厂
		SAXParserFactory saxparseFac = SAXParserFactory.newInstance();
		try{
			//新建解析器
			SAXParser saxPar =saxparseFac.newSAXParser();
			//新建xml文件处理类
			XMLContentHandler xmlHandler = new XMLContentHandler();
			
			saxPar.parse(instream, xmlHandler);
			return xmlHandler.getImageCache();
		}catch (ParserConfigurationException e){
			
			e.printStackTrace();
			return null;

		}catch(SAXException e) {
			e.printStackTrace();
			return null;

		}catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	} 
}
