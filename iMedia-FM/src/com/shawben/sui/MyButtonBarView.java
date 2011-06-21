package com.shawben.sui;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.GridView;

public class MyButtonBarView extends GridView{

	public MyButtonBarView(Context context)
	{
		super(context);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
	//重写的onTouchEvent回调方法 
	switch(event.getAction()){
	//按下
	case MotionEvent.ACTION_DOWN:
	System.out.println("ACTION_DOWN");
	return super.onTouchEvent(event);
	//滑动
	case MotionEvent.ACTION_MOVE:
	System.out.println("ACTION_MOVE");
	break;
	//离开
	case MotionEvent.ACTION_UP:
	System.out.println("ACTION_UP");
	return super.onTouchEvent(event);
	}
	//注意：返回值是false
	return false;
	}
}
