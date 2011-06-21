package com.shawben.sui;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class XMLContentHandler extends DefaultHandler{

	private ImageCache imgCache;
	private List<ImageCache> imgCaches;
	private String preTag;
	@Override
	public void characters(char[] ch, int start, int length)throws 
	SAXException {
		//Log.i("我的XML","解析到文件的元素值"+new String(ch,start,length));
		if(imgCache!= null)
		{
			//Log.i("我的XML","imgCache不等于空");
			if("name".equals(preTag)){
				imgCache.setName(new String(ch,start,length));
			}
			else if ("hot".equals(preTag)){
				imgCache.setHot(new Integer(new String(ch,start,length)));
			}
			else if("category".equals(preTag)){
				imgCache.setCategory(new String(ch,start,length));
			}
			else if("link".equals(preTag)){
				imgCache.setLink(new String(ch,start,length));
			}
			else if("path".equals(preTag)){
				imgCache.setPath(new String(ch,start,length));
			}

		}
		
	}
	@Override
	public void endElement(String uri, String localName, String qName)throws 
	SAXException{
		//Log.i("我的XML","解析到结束标志："+localName);
		if("images".equals(localName)&&imgCache != null){
			//Log.i("我的XML","ADD imgCache");
			imgCaches.add(imgCache);
			imgCache = null;
			
		}
		preTag = null;
	}
	@Override
	public void startDocument() throws SAXException{
		imgCaches = new ArrayList<ImageCache>();
		Log.i("我的XML","开始解析XML文件");
	}
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws 
	SAXException{
		if("images".equals(localName)){
			
			imgCache = new ImageCache();
			imgCache.setId(new Integer(attributes.getValue(0)));
		}
		preTag = localName;
		//Log.i("我的XML","解析到开始标志："+localName);
	}
	public List<ImageCache> getImageCache(){
		return imgCaches;
	}
}
