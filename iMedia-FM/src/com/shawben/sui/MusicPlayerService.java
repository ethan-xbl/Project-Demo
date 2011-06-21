package com.shawben.sui;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MusicPlayerService extends Service {

	private String Path="/sdcard/rain.mp3";
	MediaPlayer mp;
	/*
	 * Service 的创建
	 * */
	@Override
	public void onCreate()
	{
		Log.i("Service通知：","service创建成功");
		mp = new MediaPlayer();
		try{
    		mp.setDataSource(Path);
    		mp.prepare();
    	}catch(Exception e )
    	{
    		
    		e.printStackTrace();
    	}
		super.onCreate();
		
	}
	/** 
     * 当用户调用bindService方法时会触发该方法 返回一个IBinder对象，
     * 我们可以通过该对象，对service中 的某些数据进行访问 
     */  
	@Override
	public IBinder onBind(Intent intent)
	{
		Log.i("Service通知：","service绑定成功");
		return new MyBinder();
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
        
		//mp.start();
		Log.i("本地服务：", "Received start id " + startId + ": " + intent);
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        return START_STICKY;
    }
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		mp.stop();
		mp.release();
		Log.i("通知：","service销毁了");
		
	}
	@Override  
    public void onRebind(Intent intent) {  
        Log.i("通知", "service重新绑定成功！");  
        super.onRebind(intent);  
    } 
	/*这是一个测试函数*/
	private int count = 100;
	public int getCount()
	{
		return count;
	}
	public void setCount(int count)
	{
		this.count = count;
	}
	/**********以下开始做为音乐播放控制的函数*************/
	public void	prepare(){
  		try {
			mp.prepare();
		} catch (IOException e) {
			e.printStackTrace();
		}
  	}
	public void startMusic()
	{
		mp.start();//开始播放音乐
	}
	public void pauseMusic()
	{
		mp.pause();//暂停播放音乐
	}
	public void stopMusic()
	{
		mp.stop();//停止播放音乐
	}
	public void resetMusic()
	{
		mp.reset();
	}
	public void setDataSource(String path)
	{
		try {	  			
			mp.setDataSource(path);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**************我是分割线****************************/
	public class MyBinder extends Binder
	{
		/*
		 * 
		 * 返回一个个人的service对象
		 * 
		 * */
		MusicPlayerService getMyService()
		{
			return MusicPlayerService.this;
		}
	}
}
