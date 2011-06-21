package com.shawben.sui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.VideoView;
import android.widget.ViewFlipper;
import android.widget.AdapterView.OnItemClickListener;

public class TvPage extends Activity{

	private GridView tvlView;
	private GridView tvgView;
	private Button   tvButton;
	private RelativeLayout relativeLayout;
	private LinearLayout linear;
	private SimpleAdapter tvlAdapter,tvgAdapter;
	private ViewFlipper vFliper_grid,vFliper_list,vFliper_tv;
	private ImageButton button_left;
	private ImageButton button_right;
	private Button button_play,button_back;
	private VideoView mVideoView;//视频播放窗口
	//private Uri mUri;//资源链接	
	private MediaController mMediaController;//播放控制
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_tv);
        
        findViews();
        bindListener();
        //tv list and adapter
        tvlAdapter = new SimpleAdapter(this,getTvList(),R.layout.list_movies,
        			 new String[]{"img"},
        		     new int[]{R.id.list_movies_img});
        
        tvgAdapter = new SimpleAdapter(this,getTvGrid(),R.layout.tv_grid_layout,
        			 new String[]{"img"},
        			 new int[]{R.id.ItemImage});
        
		//
		tvlView.setVerticalSpacing(9);
		tvlView.setNumColumns(1);
        tvlView.setAdapter(tvlAdapter);
       
        //
        tvgView.setVerticalSpacing(5);
        //tvgView.setColumnWidth(65);
        tvgView.setNumColumns(3);
        tvgView.setAdapter(tvgAdapter);
        
        
        relativeLayout.setVisibility(View.GONE);
		linear.setVisibility(View.GONE);
		
		//Video file
        //mUri = Uri.parse(Environment.getExternalStorageDirectory() + "/video.mp4");
        //Create media controller
        mMediaController = new MediaController(this);
        mVideoView.setMediaController(mMediaController);
        
        
		button_left.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				vFliper_grid.setInAnimation(getApplicationContext(),R.anim.slide_left_in);
				vFliper_grid.setOutAnimation(getApplicationContext(),R.anim.slide_left_out);
				vFliper_grid.setDisplayedChild(0);
			}
			
			
		});
		button_right.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				vFliper_list.setInAnimation(getApplicationContext(),R.anim.slide_right_in);
				vFliper_list.setOutAnimation(getApplicationContext(),R.anim.slide_right_out);
				vFliper_list.setDisplayedChild(0);
			}
			
			
		});
		button_play.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
//		    	vFliper_tv.setDisplayedChild(1);
//		    	mVideoView.setVideoURI(mUri);
//		    	mVideoView.start();
				Intent intent = new Intent(TvPage.this,VideoPlayer.class);
		    	startActivity(intent); 
			}
			
		});
        button_back.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				vFliper_tv.setDisplayedChild(0);
				mVideoView.stopPlayback();
			}
        	
        	
        });
	}
	
	
	private List<? extends Map<String, ?>> getTvGrid() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
	
		map.put("img", R.drawable.tv5);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("img", R.drawable.tv6);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("img", R.drawable.tv7);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("img", R.drawable.tv5);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("img", R.drawable.tv6);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("img", R.drawable.tv10);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("img", R.drawable.tv11);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("img", R.drawable.tv12);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("img", R.drawable.tv13);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("img", R.drawable.tv14);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("img", R.drawable.tv15);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("img", R.drawable.tv16);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("img", R.drawable.tv17);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("img", R.drawable.tv18);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("img", R.drawable.tv19);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("img", R.drawable.tv20);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("img", R.drawable.tv22);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("img", R.drawable.tv21);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("img", R.drawable.tv12);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("img", R.drawable.tv12);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("img", R.drawable.tv13);
		list.add(map);
		return list;
	}

	public void findViews()
	{
		button_right = (ImageButton)findViewById(R.id.img_content);
		button_left = (ImageButton)findViewById(R.id.img_content_left);
		
		tvlView = (GridView)findViewById(R.id.list_tv);
		tvlView.setOnItemClickListener(new mItemClickRight());
		
		tvgView = (GridView)findViewById(R.id.grid_tv);
		tvgView.setOnItemClickListener(new mItemClickLeft());
		
		tvButton = (Button)findViewById(R.id.tvBtn);
		relativeLayout = (RelativeLayout)findViewById(R.id.to_hide_layout_tv);
        linear = (LinearLayout)findViewById(R.id.linear_hide_tv);
        
        vFliper_grid = (ViewFlipper)findViewById(R.id.vflipLeft_tv);   
        vFliper_list = (ViewFlipper)findViewById(R.id.vfliper_content);
        vFliper_tv = (ViewFlipper)findViewById(R.id.vfliper_tv);
        button_play = (Button)findViewById(R.id.btn_content_play_left);
        button_back = (Button)findViewById(R.id.button_back_tv);
        mVideoView = (VideoView)findViewById(R.id.view_videoPlayer);
        
        
        
	}
	class mItemClickLeft implements OnItemClickListener{
    	
    	@Override
		public void onItemClick(AdapterView<?> arg0,View arg1,int arg2,long arg3)
    	{
    		vFliper_grid.setInAnimation(getApplicationContext(),R.anim.slide_left_in);
			vFliper_grid.setOutAnimation(getApplicationContext(),R.anim.slide_left_out);			
    		vFliper_grid.setDisplayedChild(1);
    	}
    }
	class mItemClickRight implements OnItemClickListener{
    	
    	@Override
		public void onItemClick(AdapterView<?> arg0,View arg1,int arg2,long arg3)
    	{
    		vFliper_list.setInAnimation(getApplicationContext(),R.anim.slide_left_in);
			vFliper_list.setOutAnimation(getApplicationContext(),R.anim.slide_left_out);			
    		vFliper_list.setDisplayedChild(1);
    	}
    }
	public void bindListener()
	{
		/*
		 * 隐藏Tag面板的按钮*/
		tvButton.setOnClickListener(new OnClickListener()
    	{
    		@Override
			public void onClick(View v)
    		{
    			
    			if(relativeLayout.getVisibility() == View.GONE)
        		{
    				//relativeLayout.setLayoutAnimation(controller);
    				relativeLayout.setVisibility(View.VISIBLE);
    				linear.setVisibility(View.VISIBLE);
        		}
        		else 
        			{
        			relativeLayout.setVisibility(View.GONE);
        			linear.setVisibility(View.GONE);
        			}
    			    
        	}
    			
    		
    	});
			
	}	
	private List<Map<String, Object>> getTvList() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
	
		map.put("img", R.drawable.tv2);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("img", R.drawable.tv3);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("img", R.drawable.tv4);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("img", R.drawable.tv3);
		list.add(map);
		
		
		return list;
	}
	@Override
    public void onBackPressed() {
    
		// 这里处理逻辑代码，大家注意：该方法仅适用于2.0或更新版的sdk
    	Log.i("按了返回","返回。。。");
    	getParent().onBackPressed();
    	return;
    } 
	
	
	
}
