package cn.shawben.web;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

public class WebViewDemo extends Activity {
	/** Called when the activity is first created. */
	private WebView mWebView;
	private Button mButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupViews();
	}

	private void setupViews() {
		// TODO Auto-generated method stub
		mWebView = (WebView) findViewById(R.id.webview);
		WebSettings mWebSettings = mWebView.getSettings();
		// 加上这句话才能使用javascript方法
		mWebSettings.setJavaScriptEnabled(true);
		// 增加接口方法,让html页面调用
		mWebView.addJavascriptInterface(new Object() {
			// 这里我定义了一个打开地图应用的方法
			@SuppressWarnings("unused")
			public void startMap() {
				Intent mIntent = new Intent();
//				 ComponentName component = new ComponentName(
//				 "com.google.android.apps.maps",
//				 "com.google.android.maps.MapsActivity");
//				 mIntent.setComponent(component);
//				 startActivity(mIntent);
				mIntent.setClass(WebViewDemo.this, VideoActivity.class);
				startActivity(mIntent);
			}
		}, "demo");
		// 加载页面
		mWebView.loadUrl("file:///android_asset/demo.html");
		mButton = (Button) findViewById(R.id.button);
		// 给button添加事件响应,执行JavaScript的fillContent()方法
		mButton.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				mWebView.loadUrl("javascript:fillContent()");
			}
		});
	}

	private void quitDialog() {
		new AlertDialog.Builder(this).setMessage("Do you want to exit?")
				.setPositiveButton("ok", new AlertDialog.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

						WebViewDemo.this.finish();

					}
				}).setNegativeButton("return", null).show();
	}

	@Override
	public void onBackPressed() {
		// 这里处理逻辑代码，大家注意：该方法仅适用于2.0或更新版的sdk
		// Log.i("main返回","返回。。。");
		quitDialog();
		return;
	}
}