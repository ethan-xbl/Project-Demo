package com.led.ctrl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ControlPanel extends Activity {
	Button mButtonCreateProgram;
	Button mButtonLaunchProgram;
	Button mButtonAppSetup;
	Button mButtonLampSetup;
	Button mButtonPopupWindows;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findView();
        bindListener();
    }

	private void bindListener() {
		// TODO To bind the views Listener here.
		mButtonCreateProgram.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(ControlPanel.this, CreateProgram.class);
				startActivity(intent);
				
			}});
		mButtonLaunchProgram.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(ControlPanel.this, LaunchProgram.class);
				startActivity(intent);
				
			}});
		mButtonAppSetup.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(ControlPanel.this, AppSetup.class);
				startActivity(intent);
				
			}});
		mButtonLampSetup.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(ControlPanel.this, LampSetup.class);
				startActivity(intent);
			}});
		mButtonPopupWindows.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(ControlPanel.this, PopupWindows.class);
				startActivity(intent);
			}});
	}

	private void findView() {
		// TODO Find Views here.
		mButtonCreateProgram = (Button)findViewById(R.id.button_create_program);
		mButtonLaunchProgram = (Button)findViewById(R.id.button_launch_program);
		mButtonAppSetup = (Button)findViewById(R.id.button_app_setup);
		mButtonLampSetup = (Button)findViewById(R.id.button_lamp_setup);
		mButtonPopupWindows = (Button)findViewById(R.id.button_popup_window);
	}
}