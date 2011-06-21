package com.ryong21;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class Player extends Activity implements OnClickListener {

	WebView webView;

	String bodyHtml;
	String rtmpUrl;
	String fileName;
	Button refreshButton;
	String htmlPost = "</body></html>";
	String htmlPre = "<!DOCTYPE html>" 
		    + "<html lang=\"en\">"
			+ "<head><meta charset=\"utf-8\">" 
			+ "</head>"
			+ "<body style='margin:0; pading:0;"
			+ " background-color: #71D5CA;'>";

	String htmlCode = "<embed " 
		    + "type=\"application/x-shockwave-flash\""
			+ "id=\"player1\" " + "name=\"player1\" "
			+ "src=\"http://developer.longtailvideo.com"
			+ "/svn/trunk/fl5/player.swf\""
			+ "width=\"320\""
			+ "height=\"280\"" + " flashvars=@FILESRC@"
			+ "allowfullscreen=\"true\"" 
			+ "allowscripaccess=\"always\""
			+ "/>	";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		webView = (WebView) findViewById(R.id.webview);

		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setAllowFileAccess(true);
		webView.getSettings().setPluginsEnabled(true);
		webView.getSettings().setSupportZoom(true);
		webView.getSettings().setAppCacheEnabled(true);

		refreshButton = 
			(Button) findViewById(R.id.main_bt_refresh);
		refreshButton.setOnClickListener(this);

		refreshFileName();
	}

	@Override
	public void onClick(View v) {
		refreshFileName();
	}

	private void refreshFileName() {
		EditText etRtmpUrl = 
			(EditText) findViewById(R.id.setup_et_host);
		EditText etFileName = 
			(EditText) findViewById(R.id.setup_et_file);
		rtmpUrl = etRtmpUrl.getText().toString();
		fileName = etFileName.getText().toString();
		if (fileName.endsWith(".mp4")) {
			fileName = "mp4:" + fileName;
		}

		bodyHtml = htmlCode;
		bodyHtml = bodyHtml.replaceAll("@FILESRC@","\"file=" + fileName + "&streamer=" + rtmpUrl + "\"");
		webView.loadDataWithBaseURL("http://127.0.0.1",
				htmlPre + bodyHtml
				+ htmlPost, "text/html", "UTF-8", null);
	}

}