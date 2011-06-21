package com.shawben.sui;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.VideoView;
import android.widget.ViewFlipper;

public class HomePage extends Activity implements  
OnTouchListener, android.view.GestureDetector.OnGestureListener{

	private ViewFlipper vfliper_home;//整个home的容器
	private Button button_back_home;
	private Button button_home;
	private Button button_play_left;//左边的play按钮
	private RelativeLayout hide_layout;
	private LinearLayout hide_linear;	
	private LinearLayout home_layout;//整个home的布局
	private Button button_recent;
	private GridView view_imgGrid;//显示cache图片的窗口
	private GridView view_imgList;//显示图片列表的窗口
	private GridView view_msgGrid;//显示信息的窗口
	private ImageButton imgBtn;
	private ImageButton imgBtnLeft;
	private GestureDetector mGestureDetector;//注册手势识别
	private ViewFlipper flip;//容器，存放右边视频列表的信息
	private ViewFlipper flip_left;//窗口，存放左边视频列表信息
	private SimpleAdapter myMsgAdapter;//本地图片适配器
	private ProgressBar loadImgCache,pBar_loading; //加载Cache图片进度圈
	List<Bitmap> mapCache,mapCache2;//图片容器：mapCache 
	ImgCacheAdapter myImgCache,myImgCache2;	//图片适配器
	List<ImageCache> imgCaches;//存放从XML上获取的信息
	private Bitmap mBmp;//将从网络获取的一张图片放在这里
	private LinearLayout loadingLayout;//the loading
	private VideoView mVideoView;//视频播放窗口
	private MediaController mMediaController;//播放控制
	private TextView warning_info;//获取不到sd卡上图片的警告信息
	int width_imgCache;
	int iCount,loadingCount;//来用设置progressBar
	private boolean readfiles=true; //判断xml文件是否存在
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        
        //消息面板的适配器
        myMsgAdapter = new SimpleAdapter(this,getMsgInfo(),R.layout.list_msg,
        			   new String[]{"title","info","img"},
        			   new int[]{R.id.title,R.id.info,R.id.img});
        //计算cache图片的宽度
        width_imgCache = ((((this.getWindowManager().getDefaultDisplay().getWidth())-35)*54/100)-30)/3;
        
        findView();
        bindListener();
        //初始化信息显示框里面的内容
        view_msgGrid.setNumColumns(0);
        view_msgGrid.setVerticalSpacing(5);       
        view_msgGrid.setAdapter(myMsgAdapter);
        //初始化图片列表
        view_imgList.setNumColumns(2);       
        view_imgList.setVerticalSpacing(9);
              
        mapCache = new ArrayList<Bitmap>();
        mapCache2 = new ArrayList<Bitmap>();
        
        
        view_imgGrid.setVerticalSpacing(3);
        view_imgGrid.setHorizontalSpacing(8);
        view_imgGrid.setNumColumns(3);
        
       
        
        mapCache.clear();
		readXML(1);
        myImgCache = new ImgCacheAdapter(HomePage.this,mapCache,width_imgCache,36); 
        view_imgGrid.setAdapter(myImgCache);
        view_imgGrid.setOnItemClickListener(new MyItemClick());
           
   	 	//监听点击到图片的信息
        view_imgList.setOnItemClickListener(new ItemClickEvent());
        
        //手势检测器
        mGestureDetector = new GestureDetector(this);
        
        hide_layout.setVisibility(View.GONE);
		hide_linear.setVisibility(View.GONE);
		
		loadImgCache.setVisibility(View.INVISIBLE);
		flip.setDisplayedChild(0);
		
		//显示下载图片列表
		showImageList();
        mMediaController = new MediaController(this);
        mVideoView.setMediaController(mMediaController);
	 	
	}

	private void showImageList() {
		// TODO Auto-generated method stub
		if(readfiles == true){			
			List<ImageInfo> list = new ArrayList<ImageInfo>();
			for(ImageCache img : imgCaches){
				if("IMGLIST".equals(img.getCategory())){
				list.add(new ImageInfo(img.getLink()));
				}
				Log.i("showImageList","coming here");
			}
			view_imgList.setAdapter(new ImageGridViewAdapter(this,list,view_imgList));
			loadingLayout.setVisibility(View.GONE);
		}
	}

	public void findView(){
		
		vfliper_home = (ViewFlipper)findViewById(R.id.vfliper_home);
		button_home = (Button)findViewById(R.id.homeBtn);
		button_recent = (Button)findViewById(R.id.recentButton_home);	
		hide_layout = (RelativeLayout)findViewById(R.id.to_hide_layout_home);
		hide_linear = (LinearLayout)findViewById(R.id.linear_hide_home);
		home_layout = (LinearLayout)findViewById(R.id.myhome_layout);		
		view_imgGrid = (GridView)findViewById(R.id.tv_grid_home);		
		view_imgList = (GridView)findViewById(R.id.tv_list_home);		
		view_msgGrid = (GridView)findViewById(R.id.msg_home);
		imgBtn = (ImageButton)findViewById(R.id.img_content);
		imgBtnLeft = (ImageButton)findViewById(R.id.img_content_left);
		flip = (ViewFlipper)findViewById(R.id.vfliper_content);
		flip_left = (ViewFlipper)findViewById(R.id.vflipLeft);
		loadImgCache = (ProgressBar)findViewById(R.id.imgCacheLoading);
		pBar_loading = (ProgressBar)findViewById(R.id.loadingBar);
		button_play_left = (Button)findViewById(R.id.btn_content_play_left);
		loadingLayout = (LinearLayout)findViewById(R.id.fullscreen_loading_indicator);
		mVideoView = (VideoView)findViewById(R.id.view_videoPlayer);
		button_back_home = (Button)findViewById(R.id.button_back_home);
		warning_info = (TextView)findViewById(R.id.txt_error_read_files);
		flip.setOnTouchListener(this);
		flip.setLongClickable(true);
		
	}
	//右边图片列表的Item响应函数
	class ItemClickEvent implements OnItemClickListener{
    	
    	@Override
		public void onItemClick(AdapterView<?> arg0,View arg1,int arg2,long arg3)
    	{
    		flip.setInAnimation(getApplicationContext(),R.anim.slide_left_in);
			flip.setOutAnimation(getApplicationContext(),R.anim.slide_left_out);			
    		flip.setDisplayedChild(1);
    	}
    }
	//左边图片列表的Item响应函数
	class MyItemClick implements OnItemClickListener{
		
		@Override
		public void onItemClick(AdapterView<?> arg0,View arg1,int arg2,long arg3)
		{
			flip_left.setInAnimation(getApplicationContext(),R.anim.slide_right_in);
			flip_left.setOutAnimation(getApplicationContext(),R.anim.slide_right_out);
			flip_left.setDisplayedChild(1);
		}
	}
	//FLIPPER 识别手势滑动
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,  
            float velocityY) {  
        // TODO Auto-generated method stub  
        if (e2.getX()-e1.getX() > 100) {  
            //右滑 
            showNextView();  
        } else if (e1.getX() - e2.getX() > 100) {  
            //左滑 
            showPreviousView();  
        }  
        return false;  
    }  
	public void showNextView(){
		//从这里加入滑屏的动作
	}	
	public void showPreviousView(){
		//从这里加入滑屏的动作
	}
	@Override
	public boolean onTouch(View view, MotionEvent event) {  
        // TODO Auto-generated method stub  
        return mGestureDetector.onTouchEvent(event);  
    }  
	@Override
	public boolean onDown(MotionEvent arg0) {  
        // TODO Auto-generated method stub  
        return false;  
    }  
	@Override
	public void onLongPress(MotionEvent e) {  
        // TODO Auto-generated method stub  
          
    }  
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,  
            float distanceY) {  
        // TODO Auto-generated method stub  
        return false;  
    }  
    @Override
	public void onShowPress(MotionEvent e) {  
        // TODO Auto-generated method stub  
          
    }  
    @Override
	public boolean onSingleTapUp(MotionEvent e) {  
        // TODO Auto-generated method stub  
        return false;  
    }  
	public void bindListener(){
		
		button_home.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v){
				
				if(hide_layout.getVisibility() == View.GONE)
        		{
					TranslateAnimation animation = new TranslateAnimation(0, 0, -62, 0);
					
					//animation.setFillAfter(true);
					animation.setDuration(300);
					//animation.setStartOffset(250);
					//animation.setAnimationListener(new RemoveAnimationListener());
					
					hide_layout.setVisibility(View.VISIBLE);
					hide_linear.setVisibility(View.VISIBLE);
					
					home_layout.startAnimation(animation);

        		}
        		else 
        		{
        			TranslateAnimation animation = new TranslateAnimation(0, 0, 0, -64);
					
        			//animation.setFillAfter(true);
					animation.setDuration(300);
					//animation.setStartOffset(250);
					animation.setAnimationListener(new RemoveAnimationListener());
														
					//Log.i("动画","启动");
					home_layout.startAnimation(animation);
														
        		}
				
			}
		});
		
		button_recent.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v){
				
				//执行顺序一定不能错
				loadImgCache.setVisibility(View.VISIBLE);
				mapCache.clear();
				//启动一个线程
				Thread mThread = new Thread(new Runnable(){
					
					@Override
					public void run(){
						
						for(int i = 0; i<10; i++)
						{
							try{
								iCount = (i+1)*5;
								//睡一秒钟
								Thread.sleep(100);
								
								if(i == 9)
								{
									Message msg = new Message();
									msg.what = 0;
									mHandler.sendMessage(msg);
									break;
									
								}
								else
								{
									Message msg = new Message();
									msg.what = 1;
									mHandler.sendMessage(msg);
								}
								
								
							}catch(Exception e){
								e.printStackTrace();
							}
						}						
					}
				});
				
				//启动线程
				mThread.start();	       
			}
		});
		imgBtn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v){
				
				flip.setInAnimation(getApplicationContext(),R.anim.slide_right_in);
				flip.setOutAnimation(getApplicationContext(),R.anim.slide_right_out);
				flip.setDisplayedChild(0);
			}
		});
		imgBtnLeft.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				flip_left.setInAnimation(getApplicationContext(),R.anim.slide_left_in);
				flip_left.setOutAnimation(getApplicationContext(),R.anim.slide_left_out);
				flip_left.setDisplayedChild(0);
			}
		});
		button_play_left.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Log.i("进入ButtonOnClick","进入ButtonOnClick");
//				vfliper_home.setDisplayedChild(1);
//				//开始播放视频
//				mVideoView.setVideoURI(mUri);
//		    	mVideoView.start();
		    	Intent intent = new Intent(HomePage.this,VideoPlayer.class);
		    	startActivity(intent);       
			}
		});
		button_back_home.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				vfliper_home.setDisplayedChild(0);
				mVideoView.stopPlayback();
			}
			
		});
	}
	//定义 一个Handler
	private Handler mHandler = new Handler(){
		
		@Override
		public void handleMessage(Message msg){
			//读信息，0表示线程结束，进度圆圈隐藏
			//读信息，1表示线程继续运行
			switch(msg.what)
			{
				case 0:
					loadImgCache.setVisibility(View.INVISIBLE);	
					readXML(1);		
			        myImgCache = new ImgCacheAdapter(HomePage.this,mapCache,width_imgCache,35); 
			        view_imgGrid.setAdapter(myImgCache);
					break;
				case 1:
					loadImgCache.setProgress(iCount);
					break;
				case 3:
					hide_layout.setVisibility(View.GONE);
					hide_linear.setVisibility(View.GONE);
					break;
			}
		} 
	};
	 private class RemoveAnimationListener implements AnimationListener{
	    	//该方法在淡出效果执行结束之后被调用
			@Override
			public void onAnimationEnd(Animation animation) 
			{
				
				hide_layout.setVisibility(View.GONE);
				hide_linear.setVisibility(View.GONE);
				System.out.println("end");
				//Log.i("动画","结束");
				
				
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				//Log.i("动画","重播");
				System.out.println("repeat");
			}

			@Override
			public void onAnimationStart(Animation animation) {
				
				//Log.i("动画","开始");
				System.out.println("start");
			
			}
	    	
	    }
	 //Handler-对应初始化右边图片的线程
	 //当线程把图片读取下来的时候发送Message，让Handler接收，
	 private Handler imgHandler = new Handler()
	 {
		 @Override
		public void handleMessage(Message msg)
		 {
			 switch(msg.what)
			 {	

			 	 case 0: 
			 		 	
			 		 	//dismiss the loading
			 		 	loadingLayout.setVisibility(View.GONE);
			 		 	//将图片装进适配器：myImgCache，并显示出来
			 		 	myImgCache2 = new ImgCacheAdapter(HomePage.this,mapCache2,80,80);
			 		 	view_imgList.setAdapter(myImgCache2);
			 		 	
			 		 	break;
			 	 case 1:
			 			//这里将BitMap加入存放容器
			 			
			 		 	pBar_loading.setProgress(loadingCount);
			 			Log.i("标记加载Bitmap","标记加载Bitmap");
			 			break;
			 }
		 }
	 };
	//初始化右边图片列表
	public void initImageList()
	{
		loadingCount=1;
		//新建一个线程
		Thread th = new Thread(new Runnable(){	
			@Override
			public void run(){
				int times = 0;
				for(ImageCache img : imgCaches){
					times++;
					if("IMGLIST".equals(img.getCategory())){
						loadingCount++;
						//从这里开始读取图片
						//创建Client实例
						DefaultHttpClient httpClient = new DefaultHttpClient();
						//请求连接到--img.getLink
						HttpGet getRequest = new HttpGet(img.getLink());
						try {
							//连接服务器
							HttpResponse response = httpClient.execute(getRequest);
							final int statusCode = response.getStatusLine().getStatusCode();
							if (statusCode != HttpStatus.SC_OK) {
					    		Log.w("图片下载线程：", "Error " + statusCode + " while retrieving bitmap from net");		
					    	}
							//取得数据记录
							HttpEntity entity = response.getEntity();
							if(entity != null){
								InputStream is = null;
								//取出数据内容
								is = entity.getContent();
								mBmp = BitmapFactory.decodeStream(is);
								mapCache2.add(mBmp);
								//发送消息给Handler处理
								Message msg = new Message();
								msg.what =1;
								imgHandler.sendMessage(msg);
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							//请求终止
							getRequest.abort();
					    	Log.e("图片下载线程：", "Error while retrieving bitmap from net");
						}
					}//END--if("IMGLIST".equals(img.getCategory()))
					else if (times == imgCaches.size()){	
						Log.i("输出imgCaches",String.valueOf(imgCaches.size()));
						//发送线程结束的消息
						Message msg = new Message();
						msg.what = 0;
						imgHandler.sendMessage(msg);
					}
				}//END--for(ImageCache img : imgCaches)
			
			}
		});
		th.start();
		//Log.i("进入线程11","启动线程");
	}
	public void readXML(int tag){
		//从sdcard中读取XML文件
		File mf = new File(android.os.Environment.getExternalStorageDirectory()+"/tvCache/XMLforImg.xml");
		String fPath = mf.getAbsolutePath();
		File mfile = new File(fPath);
		InputStream in = null;
		//判断XML文件是否存在
		if(mfile.exists()){
			try {
				in  = new BufferedInputStream(new FileInputStream(fPath));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			imgCaches = SAXXmlService.xmlOpera(in);
			
			if(imgCaches == null){
				
				Log.i("MyXMLread","imgCaches is NULL");
			}	
			//Log.i("MyXMLread",String.valueOf(imgCaches.size()));
			if(1 == tag){
				
				for(ImageCache img : imgCaches){
					if(img.getHot() == 1){
	
						 File file=new File(img.getPath());
						 if(file.exists()){
							 
							 readBitmap(img.getPath(),1);
						 }
						 else{
							 //如果图片资源不存在读取error.png
							 readBitmap("/sdcard/tvCache/error.png",1);
						 }
						
					}
				
				}
			}
		}else{
			//显示警告信息
			readfiles = false;
			warning_info.setVisibility(View.VISIBLE);
		}
	}
	public void readBitmap(String path ,int tag){
		
		/*
		 * 读取path中的图片，并将其加入mapCache的容器里
		 * */
		if(tag == 1)
		{
			Bitmap bmp = BitmapFactory.decodeFile(path);
			mapCache.add(bmp);
		}
		else if (tag == 2)
		{
			
//			//Bitmap bmp = BitmapFactory.decodeFile(path);
//			mapCache2.add(bmp);
		}
		
	}
	
	private List<Map<String, Object>> getMsgInfo() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "Mark");
		map.put("info", "How are you?");
		map.put("img", R.drawable.contact);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "Andy");
		map.put("info", "I am Andy.");
		map.put("img", R.drawable.contact4);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "Mr.lin");
		map.put("info", "你好吗？");
		map.put("img", R.drawable.contact2);
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("title", "10086");
		map.put("info", "你的手机已停机，请充值！");
		map.put("img", R.drawable.contact5);
		list.add(map);
		
		return list;
	}
	@Override
    public void onBackPressed() {
		// 这里处理逻辑代码，该方法仅适用于2.0或更新版的sdk
		// 这个函数的作用是让父类响应返回的事件
    	getParent().onBackPressed();
    	return;
    }
}
