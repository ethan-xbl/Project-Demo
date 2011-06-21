package com.led.ctrl;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.PopupWindow;
import com.led.ctrl.ColorPickerDialog.OnColorChangedListener;

public class PopupWindows extends Activity implements OnColorChangedListener{
	Button mButtonColorPicker;
	Button mButtonTargetLamps;
	Button mButtonTapSync;
	Button mButtonCreateSet;
	Button mButtonManualWhiteCalibration;
	Paint mPaint;
	PopupWindow mPopupWindowTargetLamps;
	PopupWindow mPopupWindowTapSync;
	PopupWindow mPopupWindowCreateSet;
	PopupWindow mPopupWindowManualWrite;
	View mViewTargetLamps;
	View mViewTabSync;
	View mViewCreateSet;
	View mViewManualWrite;
	static final String TAG = "PopupWindow LifeCycle";
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_windows);
        findViews();
        bindListener();
    }
	private void findViews() {
		// TODO Auto-generated method stub
		mButtonColorPicker = (Button)findViewById(R.id.popup_color_picker);
		mButtonTargetLamps = (Button)findViewById(R.id.popup_target_lamps);
		mButtonTapSync = (Button)findViewById(R.id.popup_tap_sync);
		mButtonCreateSet = (Button)findViewById(R.id.popup_create_set);
		mButtonManualWhiteCalibration = 
				(Button)findViewById(R.id.popup_Manual_white_calibration);
		//
		Context mContext = PopupWindows.this;
		LayoutInflater mLayoutInflater = (LayoutInflater) mContext  
        .getSystemService(LAYOUT_INFLATER_SERVICE);  
		 
		 mViewTargetLamps = mLayoutInflater.inflate(  
                 R.layout.popup_window_target_lamps, null);
		 mViewTargetLamps.setFocusableInTouchMode(true);
		 
		 mViewTabSync = mLayoutInflater.inflate(
				 R.layout.popup_window_tap_sync, null);
		 mViewTabSync.setFocusableInTouchMode(true);
		 
		 mViewCreateSet = mLayoutInflater.inflate(
				 R.layout.popup_window_create_set, null);
		 mViewCreateSet.setFocusableInTouchMode(true);
		 
		 mViewManualWrite = mLayoutInflater.inflate(
				 R.layout.popup_window_manual_write, null);
		 mViewManualWrite.setFocusableInTouchMode(true);
		 
		
		 
	}
	
	private void bindListener() {
		// TODO Auto-generated method stub
		mButtonColorPicker.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mPaint = new Paint();
		        mPaint.setColor(0xFFFF0000);
		       
				new ColorPickerDialog(PopupWindows.this,PopupWindows.this,mPaint.getColor())
						.show();
			}
		});
		
		mButtonTargetLamps.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mPopupWindowTargetLamps = new PopupWindow(mViewTargetLamps, 300,200);
				mPopupWindowTargetLamps.setFocusable(true);
				mPopupWindowTargetLamps.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
			}
		});
		// 让popupwindow响应按键事件
		mViewTargetLamps.setOnKeyListener(new OnKeyListener(){

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				if ((keyCode == KeyEvent.KEYCODE_BACK)&&(mPopupWindowTargetLamps.isShowing())){
					mPopupWindowTargetLamps.dismiss();
				}
				return false;
			}
			
		});
		mButtonTapSync.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mPopupWindowTapSync = new PopupWindow(mViewTabSync, 300,200);
				mPopupWindowTapSync.setFocusable(true);
				mPopupWindowTapSync.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
				
			}});
		// 让popupwindow响应按键事件
		mViewTabSync.setOnKeyListener(new OnKeyListener(){

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				if ((keyCode == KeyEvent.KEYCODE_BACK)&&(mPopupWindowTapSync.isShowing())){
					mPopupWindowTapSync.dismiss();
				}
				return false;
			}
			
		});
		mButtonCreateSet.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mPopupWindowCreateSet = new PopupWindow(mViewCreateSet, 300,200);
				mPopupWindowCreateSet.setFocusable(true);
				mPopupWindowCreateSet.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
			}
			
		});
		// 让popupwindow响应按键事件
		mViewCreateSet.setOnKeyListener(new OnKeyListener(){

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				if ((keyCode == KeyEvent.KEYCODE_BACK)&&(mPopupWindowCreateSet.isShowing())){
					mPopupWindowCreateSet.dismiss();
				}
				return false;
			}
			
		});
		mButtonManualWhiteCalibration.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				mPopupWindowManualWrite = new PopupWindow(mViewManualWrite, 300,200);
				mPopupWindowManualWrite.setFocusable(true);
				mPopupWindowManualWrite.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
			}
			
		});
		// 让popupwindow响应按键事件,前提是要设置
		// mViewManualWrite.setFocusableInTouchMode(true);
		 mViewManualWrite.setOnKeyListener(new OnKeyListener(){

				@Override
				public boolean onKey(View v, int keyCode, KeyEvent event) {
					// TODO Auto-generated method stub
					if ((keyCode == KeyEvent.KEYCODE_BACK)&&(mPopupWindowManualWrite.isShowing())){
						mPopupWindowManualWrite.dismiss();
					}
					return false;
				}});
	}
	@Override
	public void colorChanged(int color) {
		// TODO Auto-generated method stub
		 mPaint.setColor(color);
	}
	@Override
 	public void onBackPressed() {
		// 该方法仅适用于2.0或更新版的sdk  	
		PopupWindows.this.finish();
		return;	
    }
	/**
	 *  生命周期的测试
	 */
	// 重写 onStart()
	@Override
	protected void onStart(){
		super.onStart();
		Log.i(TAG,"=== onStart ===");
	}
	// 重写 onRestart()
	@Override
	protected void onRestart(){
		super.onRestart();
		Log.i(TAG,"=== onStart ===");
	}
	// 重写onResume()
	@Override
	protected void onResume(){
		super.onResume();
		Log.i(TAG,"=== onResume ===");
	}
	// 重写onPause()
	@Override
	protected void onPause(){
		super.onPause();
		Log.i(TAG,"=== onPause ===");
	}
	// 重写onStop()
	@Override
	protected void onStop(){
		super.onStop();
		Log.i(TAG,"=== onStop ===");
	}
	// 重写onDestroy()
	protected void onDestroy(){
		super.onDestroy();
		Log.i(TAG,"=== onDestroy ===");
	}
}
