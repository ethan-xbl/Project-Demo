package com.shawben.sui;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoPlayer extends Activity implements MediaPlayer.OnErrorListener,
MediaPlayer.OnCompletionListener{

	public static final String TAG = "VideoPlayer";
	private VideoView mVideoView;
	private Uri mUri;
	private MediaController mMediaController;
	private int mPositionWhenPaused = -1;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        
		setContentView(R.layout.videoview);
		
		mVideoView = (VideoView)findViewById(R.id.video_view);
		
		//Video file
        mUri = Uri.parse(Environment.getExternalStorageDirectory() + "/video.mp4");
        //Create media controller
        mMediaController = new MediaController(this);
        mVideoView.setMediaController(mMediaController);
	}
	@Override
	public void onStart() {
    	// Play Video
    	mVideoView.setVideoURI(mUri);
    	mVideoView.start();

    	super.onStart();
    }
	@Override
	public void onPause() {
    	// Stop video when the activity is pause.
    	mPositionWhenPaused = mVideoView.getCurrentPosition();
    	mVideoView.stopPlayback();
    	Log.d(TAG, "OnStop: mPositionWhenPaused = " + mPositionWhenPaused);
    	Log.d(TAG, "OnStop: getDuration  = " + mVideoView.getDuration());

    	super.onPause();
    }
	@Override
	public void onResume() {
    	// Resume video player
    	if(mPositionWhenPaused >= 0) {
    		mVideoView.seekTo(mPositionWhenPaused);
    		mPositionWhenPaused = -1;
    	}

    	super.onResume();
    }
	@Override
	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub
		this.finish();
	}

	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		// TODO Auto-generated method stub
		return false;
	}

}
