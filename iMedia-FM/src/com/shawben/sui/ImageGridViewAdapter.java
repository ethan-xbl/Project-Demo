package com.shawben.sui;

import java.util.List;

import com.shawben.sui.AsyncImageLoader.ImageCallback;


import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;


public class ImageGridViewAdapter extends ArrayAdapter<ImageInfo>{
	private GridView gridView;
	private AsyncImageLoader asyncImageLoader;
	public ImageGridViewAdapter(Activity activity, List<ImageInfo> imageAndTexts, GridView gridView) {
        super(activity, 0, imageAndTexts);
        this.gridView = gridView;
        asyncImageLoader = new AsyncImageLoader();
    }
	public View getView(int position, View convertView, ViewGroup parent){
		Activity activity = (Activity) getContext();
		// Inflate the views from XML
		View rowView = convertView;
		ViewCache viewCache;
		if (rowView == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            rowView = inflater.inflate(R.layout.tv_list_layout, null);
            viewCache = new ViewCache(rowView);
            rowView.setTag(viewCache);
        } else {
            viewCache = (ViewCache)rowView.getTag();
        }
		//◊¢“‚’‚¿Ô
		ImageInfo imageInfo = getItem(position);
		// Load the image and set it on the ImageView
		String imageUrl = imageInfo.getImageUrl();
		ImageView imageView = viewCache.getImageView();
        imageView.setTag(imageUrl);
        Drawable cachedImage = asyncImageLoader.loadDrawable(imageUrl, new ImageCallback(){

			@Override
			public void imageLoaded(Drawable imageDrawable, String imageUrl) {
				// TODO Auto-generated method stub
				ImageView imageViewByTag = (ImageView) gridView.findViewWithTag(imageUrl);
				 if (imageViewByTag != null) {
                     imageViewByTag.setImageDrawable(imageDrawable);
                 }
			}});
        if (cachedImage == null) {
            imageView.setImageResource(R.drawable.img_home1);
            Log.e("Adapter", "null");
        }else{
            imageView.setImageDrawable(cachedImage);
        }
        
		return rowView;
	}
}
