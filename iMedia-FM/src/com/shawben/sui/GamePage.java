package com.shawben.sui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

public class GamePage extends Activity {
	private Button button_game;//点击它可以隐藏上面面板
	private LinearLayout hide_linear;
	private RelativeLayout hide_panel;
	private GridView grid_games;
	private SimpleAdapter adapter_grid_games;
	private GridView list_games;
	private SimpleAdapter adapter_list_games;
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_games);
        findViews();
        bindListener();
        createAdapter();
        //设置grid_games 行数跟大小
        grid_games.setNumColumns(3);
        grid_games.setVerticalSpacing(12);
        //设置list_games 行数跟大小
        list_games.setNumColumns(1);
        grid_games.setVerticalSpacing(4);
        //设置上面面板启动为隐藏状态
        hide_panel.setVisibility(View.GONE);
		hide_linear.setVisibility(View.GONE);
		grid_games.setAdapter(adapter_grid_games);
		list_games.setAdapter(adapter_list_games);
	}
	private void createAdapter() {
		// TODO Auto-generated method stub
		adapter_grid_games = new SimpleAdapter(this,getGridGame(),R.layout.grid_game,
					new String[]{"img"},new int[]{R.id.img_games});
		adapter_list_games = new SimpleAdapter(this,getListGame(),R.layout.list_game,
					new String[]{"img"},new int[]{R.id.img_list_game});
	}
	private List<? extends Map<String, ?>> getListGame() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("img", R.drawable.game_list_img);
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("img", R.drawable.game_list_img2);
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("img", R.drawable.game_list_img3);
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("img", R.drawable.game_list_img4);
		list.add(map);
		
		return list;
	}
	private List<? extends Map<String, ?>> getGridGame() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("img", R.drawable.img_game);
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("img", R.drawable.img_game2);
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("img", R.drawable.img_game3);
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("img", R.drawable.img_game5);
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("img", R.drawable.img_game);
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("img", R.drawable.img_game3);
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("img", R.drawable.img_game4);
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("img", R.drawable.img_game5);
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("img", R.drawable.img_game3);
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("img", R.drawable.img_game4);
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("img", R.drawable.img_game3);
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("img", R.drawable.img_game5);
		list.add(map);
		
		
		map = new HashMap<String,Object>();
		map.put("img", R.drawable.img_game4);
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("img", R.drawable.img_game5);
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("img", R.drawable.img_game3);
		list.add(map);
		
		return list;
	}
	private void bindListener() {
		// TODO Auto-generated method stub
		button_game.setOnClickListener(toHidePanel);
	}
	private void findViews() {
		// TODO Auto-generated method stub
		button_game = (Button)findViewById(R.id.gameBtn);
		hide_panel = (RelativeLayout)findViewById(R.id.to_hide_layout_games);
		hide_linear = (LinearLayout)findViewById(R.id.linear_hide_games);
		//grid_game and list_game
		grid_games = (GridView)findViewById(R.id.grid_games);
		list_games = (GridView)findViewById(R.id.list_games);
		
	}
	//将上面面析隐藏的处理函数
	private Button.OnClickListener toHidePanel = new OnClickListener(){
		
		public void onClick(View v){
			if(hide_panel.getVisibility() == View.GONE){
				
				hide_panel.setVisibility(View.VISIBLE);
				hide_linear.setVisibility(View.VISIBLE);
			}else{
				
				hide_panel.setVisibility(View.GONE);
				hide_linear.setVisibility(View.GONE);
			}
		}
	};
	@Override
    public void onBackPressed() {
		// 这里处理逻辑代码，该方法仅适用于2.0或更新版的sdk
		// 这个函数的作用是让父类响应返回的事件
    	getParent().onBackPressed();
    	return;
    }		
				
			
}
