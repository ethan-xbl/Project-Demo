package com.shawben.sui;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImgCacheAdapter extends BaseAdapter{

	Context mcontext;
	private ImageView[] imgItems;
	List<Bitmap> maps;
	
	public ImgCacheAdapter(Context mcontext,List<Bitmap> bmaps,int width,int height){
		this.mcontext = mcontext;
		this.maps = bmaps;
		
		imgItems = new ImageView[maps.size()];
		for(int i = 0;i< maps.size();i++){
			
			imgItems[i] = new ImageView(mcontext);
			imgItems[i].setLayoutParams(new GridView.LayoutParams(width, height));
			imgItems[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
			imgItems[i].setAdjustViewBounds(false); 
			//imgItems[i].setPadding(2, 2, 2, 2);			
			imgItems[i].setImageBitmap(maps.get(i));
		}
	}

	@Override
	public int getCount(){
		return maps.size();
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		final ImageView imageView;   
	        if (convertView == null) {   
	        	imageView =imgItems[position];
	        } else {   
	        	imageView = (ImageView) convertView;
	        }   
	               
	        return imageView;
	}
	@Override
	public Object getItem(int position) {
		   // TODO Auto-generated method stub
		   return maps.get(position);
	}
	@Override
	public long getItemId(int position) {
		   // TODO Auto-generated method stub
		   return position;
	}
}
