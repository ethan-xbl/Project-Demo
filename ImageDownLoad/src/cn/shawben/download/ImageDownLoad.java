package cn.shawben.download;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

public class ImageDownLoad extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        GridView gridView = (GridView)findViewById(R.id.myGridView);
        List<ImageInfo> list = new ArrayList<ImageInfo>();
        list.add(new ImageInfo("http://d3o76j76sx0r0h.cloudfront.net/home/img_home1.png"));
        list.add(new ImageInfo("http://d3o76j76sx0r0h.cloudfront.net/home/img_home2.png"));
        list.add(new ImageInfo("http://d3o76j76sx0r0h.cloudfront.net/home/img_home3.png"));
        list.add(new ImageInfo("http://d3o76j76sx0r0h.cloudfront.net/home/img_home4.png"));
        list.add(new ImageInfo("http://d3o76j76sx0r0h.cloudfront.net/home/img_home5.png"));
        list.add(new ImageInfo("http://d3o76j76sx0r0h.cloudfront.net/home/img_home6.png"));
        gridView.setAdapter(new ImageGridViewAdapter(this,list,gridView));
    }
}