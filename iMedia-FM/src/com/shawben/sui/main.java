package com.shawben.sui;



import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.ViewFlipper;

public class main extends ActivityGroup {
    /** Called when the activity is first created. */
	
	
	//buttonBar
	private Button btn_page_home;
	private Button btn_page_game;
	private Button btn_page_music;
	private Button btn_page_video;
	private Button btn_page_tv;
	//用来装activity的容器
	private ViewFlipper myViews;
	
	private int viewNum = 0;
	
	// 导航按钮图片
	 
	
	//private MusicPlayerService mService;
	private ServiceConnection mConnection = new ServiceConnection()
	{
		@Override
		public void onServiceConnected(ComponentName className, IBinder service){
			
			///mService = ((MusicPlayerService.MyBinder)service).getMyService();
			Log.i("main通知：","Service连接到对象");
		}
		@Override
		public void onServiceDisconnected(ComponentName className)
		{
			//mService = null;
		}
	};
	void doBindService()
	{
		
		getApplicationContext().bindService(new Intent(main.this, 
	            MusicPlayerService.class), mConnection, Context.BIND_AUTO_CREATE);
		Log.i("main通知：","绑定了Service");
	    //mIsBound = true;
	}
	//解绑Service
	void doUnbindService() {

	        // Detach our existing connection.
	        getApplicationContext().unbindService(mConnection);
	        Log.i("main通知：","解绑了Service");

	}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.btnbar);
       
        doBindService();
        doBindViews();
        doBindListens();
        AddViews();
       

    }
    public void doBindViews()
    {
    	 btn_page_home = (Button)findViewById(R.id.btn_page_home);
    	 btn_page_game = (Button)findViewById(R.id.btn_page_game);
    	 btn_page_music = (Button)findViewById(R.id.btn_page_music);
    	 btn_page_video = (Button)findViewById(R.id.btn_page_video);
    	 btn_page_tv = (Button)findViewById(R.id.btn_page_tv);
    	 myViews = (ViewFlipper)findViewById(R.id.PagesContainer);
    	 
    	 
    }
    public void doBindListens()
    {
    	btn_page_home.setOnClickListener(new OnClickListener()
		{
    		@Override
			public void onClick(View v){
    	    	viewNum = myViews.getDisplayedChild();
    	    	if(0 > viewNum){
    	
    	    		myViews.setInAnimation(getApplicationContext(), R.anim.up_in);
    	    		myViews.setOutAnimation(getApplicationContext(), R.anim.up_out);
    	    		myViews.setPersistentDrawingCache(ViewGroup.PERSISTENT_ALL_CACHES);
    	    		myViews.setDisplayedChild(0);
    	
    	            
    	    	}
    	    	else{
    	
    	    		myViews.setInAnimation(getApplicationContext(), R.anim.down_in);
    	    		myViews.setOutAnimation(getApplicationContext(), R.anim.down_out);
    	    		myViews.setPersistentDrawingCache(ViewGroup.PERSISTENT_ALL_CACHES);
    	    		myViews.setDisplayedChild(0);
    	  
    	    	}
    	    	btn_page_home.setClickable(false);
    	    	btn_page_game.setClickable(true);
    	    	btn_page_music.setClickable(true);
    	    	btn_page_video.setClickable(true);
    	    	btn_page_tv.setClickable(true);
    	    	
    	    	btn_page_home.setBackgroundResource(R.drawable.btn_social_select);
    	    	btn_page_game.setBackgroundResource(R.drawable.btn_game_normal);
    	    	btn_page_music.setBackgroundResource(R.drawable.btn_music_normal);
    	    	btn_page_video.setBackgroundResource(R.drawable.btn_video_normal);
    	    	btn_page_tv.setBackgroundResource(R.drawable.btn_tv_normal);
    		}
		});
    	btn_page_game.setOnClickListener(new OnClickListener()
		{
    		@Override
			public void onClick(View v){
    			viewNum = myViews.getDisplayedChild();
    	    	if(1 > viewNum){
    	
    	    		myViews.setInAnimation(getApplicationContext(), R.anim.up_in);
    	    		myViews.setOutAnimation(getApplicationContext(), R.anim.up_out);
    	    		myViews.setPersistentDrawingCache(ViewGroup.PERSISTENT_ALL_CACHES);
    	    		myViews.setDisplayedChild(1);
    	
    	            
    	    	}
    	    	else{
    	
    	    		myViews.setInAnimation(getApplicationContext(), R.anim.down_in);
    	    		myViews.setOutAnimation(getApplicationContext(), R.anim.down_out);
    	    		myViews.setPersistentDrawingCache(ViewGroup.PERSISTENT_ALL_CACHES);
    	    		myViews.setDisplayedChild(1);
    	  
    	    	}
    	    	btn_page_home.setClickable(true);
    	    	btn_page_game.setClickable(false);
    	    	btn_page_music.setClickable(true);
    	    	btn_page_video.setClickable(true);
    	    	btn_page_tv.setClickable(true);
    	    	
    	    	btn_page_home.setBackgroundResource(R.drawable.btn_social_normal);
    	    	btn_page_game.setBackgroundResource(R.drawable.btn_game_select);
    	    	btn_page_music.setBackgroundResource(R.drawable.btn_music_normal);
    	    	btn_page_video.setBackgroundResource(R.drawable.btn_video_normal);
    	    	btn_page_tv.setBackgroundResource(R.drawable.btn_tv_normal);
    		}
    		
		});
    	btn_page_music.setOnClickListener(new OnClickListener()
		{
    		@Override
			public void onClick(View v){
    			viewNum = myViews.getDisplayedChild();
    	    	if(2 > viewNum){
    	
    	    		myViews.setInAnimation(getApplicationContext(), R.anim.up_in);
    	    		myViews.setOutAnimation(getApplicationContext(), R.anim.up_out);
    	    		myViews.setPersistentDrawingCache(ViewGroup.PERSISTENT_ALL_CACHES);
    	    		myViews.setDisplayedChild(2);
    	
    	            
    	    	}
    	    	else{
    	
    	    		myViews.setInAnimation(getApplicationContext(), R.anim.down_in);
    	    		myViews.setOutAnimation(getApplicationContext(), R.anim.down_out);
    	    		myViews.setPersistentDrawingCache(ViewGroup.PERSISTENT_ALL_CACHES);
    	    		myViews.setDisplayedChild(2);
    	  
    	    	}
    	    	btn_page_home.setClickable(true);
    	    	btn_page_game.setClickable(true);
    	    	btn_page_music.setClickable(false);
    	    	btn_page_video.setClickable(true);
    	    	btn_page_tv.setClickable(true);
    	    	
    	    	btn_page_home.setBackgroundResource(R.drawable.btn_social_normal);
    	    	btn_page_game.setBackgroundResource(R.drawable.btn_game_normal);
    	    	btn_page_music.setBackgroundResource(R.drawable.btn_music_select);
    	    	btn_page_video.setBackgroundResource(R.drawable.btn_video_normal);
    	    	btn_page_tv.setBackgroundResource(R.drawable.btn_tv_normal);
    		}
		});
    	btn_page_video.setOnClickListener(new OnClickListener()
		{
    		@Override
			public void onClick(View v){
    			viewNum = myViews.getDisplayedChild();
    	    	if(3 > viewNum){
    	
    	    		myViews.setInAnimation(getApplicationContext(), R.anim.up_in);
    	    		myViews.setOutAnimation(getApplicationContext(), R.anim.up_out);
    	    		myViews.setPersistentDrawingCache(ViewGroup.PERSISTENT_ALL_CACHES);
    	    		myViews.setDisplayedChild(3);
    	
    	            
    	    	}
    	    	else{
    	
    	    		myViews.setInAnimation(getApplicationContext(), R.anim.down_in);
    	    		myViews.setOutAnimation(getApplicationContext(), R.anim.down_out);
    	    		myViews.setPersistentDrawingCache(ViewGroup.PERSISTENT_ALL_CACHES);
    	    		myViews.setDisplayedChild(3);
    	  
    	    	}
    	    	btn_page_home.setClickable(true);
    	    	btn_page_game.setClickable(true);
    	    	btn_page_music.setClickable(true);
    	    	btn_page_video.setClickable(false);
    	    	btn_page_tv.setClickable(true);
    	    	
    	    	btn_page_home.setBackgroundResource(R.drawable.btn_social_normal);
    	    	btn_page_game.setBackgroundResource(R.drawable.btn_game_normal);
    	    	btn_page_music.setBackgroundResource(R.drawable.btn_music_normal);
    	    	btn_page_video.setBackgroundResource(R.drawable.btn_video_select);
    	    	btn_page_tv.setBackgroundResource(R.drawable.btn_tv_normal);
    		}
    		
		});
    	btn_page_tv.setOnClickListener(new OnClickListener()
		{
    		@Override
			public void onClick(View v){
    			viewNum = myViews.getDisplayedChild();
    	    	if(4 > viewNum){
    	
    	    		myViews.setInAnimation(getApplicationContext(), R.anim.up_in);
    	    		myViews.setOutAnimation(getApplicationContext(), R.anim.up_out);
    	    		myViews.setPersistentDrawingCache(ViewGroup.PERSISTENT_ALL_CACHES);
    	    		myViews.setDisplayedChild(4);
    	
    	            
    	    	}
    	    	else{
    	
    	    		myViews.setInAnimation(getApplicationContext(), R.anim.down_in);
    	    		myViews.setOutAnimation(getApplicationContext(), R.anim.down_out);
    	    		myViews.setPersistentDrawingCache(ViewGroup.PERSISTENT_ALL_CACHES);
    	    		myViews.setDisplayedChild(4);
    	  
    	    	}
    	    	btn_page_home.setClickable(true);
    	    	btn_page_game.setClickable(true);
    	    	btn_page_music.setClickable(true);
    	    	btn_page_video.setClickable(true);
    	    	btn_page_tv.setClickable(false);
    	    	
    	    	btn_page_home.setBackgroundResource(R.drawable.btn_social_normal);
    	    	btn_page_game.setBackgroundResource(R.drawable.btn_game_normal);
    	    	btn_page_music.setBackgroundResource(R.drawable.btn_music_normal);
    	    	btn_page_video.setBackgroundResource(R.drawable.btn_video_normal);
    	    	btn_page_tv.setBackgroundResource(R.drawable.btn_tv_select);
    		}
		});

    }
    public void AddViews()
    {
    	
    	Intent intent = null;
    	intent = new Intent(main.this,HomePage.class);
    	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    	View ActivityA = getLocalActivityManager().startActivity(  
                "subActivity", intent).getDecorView();
    	myViews.addView(ActivityA, LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
    	
    	intent = new Intent(main.this,GamePage.class);
    	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    	View ActivityB = getLocalActivityManager().startActivity(  
                "subActivity", intent).getDecorView();
    	myViews.addView(ActivityB, LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
    
  	
    	intent = new Intent(main.this,MusicPage.class);
    	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);    	
    	View ActivityD = getLocalActivityManager().startActivity(  
                "subActivity", intent).getDecorView();
    	myViews.addView(ActivityD, LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
//    	
    	intent = new Intent(main.this,MoviePage.class);
    	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    	View ActivityC = getLocalActivityManager().startActivity(  
                "subActivity", intent).getDecorView();
    	myViews.addView(ActivityC, LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
    	
    
    	intent = new Intent(main.this,TvPage.class);
    	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    	View ActivityE = getLocalActivityManager().startActivity(  
                "subActivity", intent).getDecorView();
    	myViews.addView(ActivityE, LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
////    	
    	//myViews.setDisplayedChild(0);

    }
    //The QuitDialog
    //退出对话框
    private void quitDialog(){
		new AlertDialog.Builder(this)
		.setMessage("Do you want to exit?")
		.setPositiveButton("ok", new AlertDialog.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				doUnbindService();
				main.this.finish();
				
			}
		})
		.setNegativeButton("return", null)
		.show();
	}
    
    @Override
    public void onBackPressed() {
    // 这里处理逻辑代码，大家注意：该方法仅适用于2.0或更新版的sdk
    	Log.i("main返回","返回。。。");
    	quitDialog();
    	return;
    }
}
