package com.shawben.webservice;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;
public class WebService extends Activity {
	//指定 WebService 的命名空间和调用方法
	private static final String NAMESPACE = "http://WebXml.com.cn/";
	private static final String METHOD_NAME = "getWeatherbyCityName";
	private static String SOAP_ACTION = "http://WebXml.com.cn/getWeatherbyCityName";
	// WebService地址
	private static String URL = "http://www.webxml.com.cn/webservices/weatherwebservice.asmx";
	private SoapObject getInfo;
	private String weatherToday;
	//button
	private Button button;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        button = (Button)findViewById(R.id.btn_getinfo);
        button.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 String city="深圳";
				 getWeather(city);
			}
        	
        });
    }

	public void getWeather(String cityName) {
		// TODO Auto-generated method stub
		//对webservice请求数据的工作
		SoapObject request = new SoapObject(NAMESPACE,METHOD_NAME);
		//theCityName 由webService提供的函数参数
		request.addProperty("theCityName", cityName);
		//定义要发送数据的信封的封装格式
		 SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		 envelope.bodyOut = request;
		 envelope.dotNet = true;
		 envelope.setOutputSoapObject(request);
		//创建HttpTransportSE对象,通过HttpTransportSE类的构造方法可以指定WebService的url
		 HttpTransportSE transport = new HttpTransportSE(URL);
		 transport.debug = true;  
		 try {
			//使用cal调用webservice的方法
			transport.call(SOAP_ACTION,envelope);
			//获得服务器返回的信息
			getInfo =(SoapObject) envelope.getResponse();
			parseInfo(getInfo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void parseInfo(SoapObject getInfo2) {
		// TODO Auto-generated method stub
		  String date = getInfo2.getProperty(6).toString();
		  weatherToday = "今天：" + date.split(" ")[0];
		  weatherToday = weatherToday + "\n天气：" + date.split(" ")[1];
		  weatherToday = weatherToday + "\n气温："
		    + getInfo2.getProperty(5).toString();
		  weatherToday = weatherToday + "\n风力："
		    + getInfo2.getProperty(7).toString() + "\n";
		  System.out.println("weatherToday is " + weatherToday);
		  Toast.makeText(this, weatherToday, Toast.LENGTH_LONG).show();
	}
}