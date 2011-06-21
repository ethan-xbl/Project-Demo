package com.led.ctrl;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class AppSetup extends Activity {
	Button mButtonFirmWareUpdate;
	FrameLayout mFrameLayoutContent;
	LinearLayout.LayoutParams LLP;
	boolean IsVisible = false;
	int height = 0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_setup);
		findViews();
		bindListener();

	}

	private void findViews() {
		// TODO Auto-generated method stub
		mButtonFirmWareUpdate = (Button) findViewById(R.id.button_firmware_update);
		mFrameLayoutContent = (FrameLayout) findViewById(R.id.content_inside);
		LLP = new LinearLayout.LayoutParams(320, 100);
	}

	private void bindListener() {
		// TODO Auto-generated method stub
		mButtonFirmWareUpdate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// Show the inside panel without animation. START
				// if(IsVisible == true){
				// mFrameLayoutContent.setVisibility(View.GONE);
				// IsVisible = false;
				// }else{
				// mFrameLayoutContent.setVisibility(View.VISIBLE);
				// IsVisible = true;
				// }
				// Show the inside panel without animation. END

				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							if (height == 0) {
								for (int i = 0; i <= 100; i++) {
									height = i;
									Thread.sleep(2); // Here can change the
														// speed.
									Message msg = new Message();
									msg.what = 0;
									mHandler.sendMessage(msg);
								}
							} else {
								for (int i = 100; i >= 0; i--) {
									height = i;
									Thread.sleep(2);// Here can change the
													// speed.
									Message msg = new Message();
									msg.what = 0;
									mHandler.sendMessage(msg);
								}
							}
							System.out.print(mFrameLayoutContent.getHeight());
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

				});
				thread.start();

			}
		});
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			LLP = new LinearLayout.LayoutParams(320, height);
			mFrameLayoutContent.setLayoutParams(LLP);
		}
	};
}
