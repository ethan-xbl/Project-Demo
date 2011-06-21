package cn.shawben.web;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class VideoActivity extends Activity implements OnBufferingUpdateListener,
		OnCompletionListener, OnPreparedListener, OnVideoSizeChangedListener,
		Callback {

	private static final String TAG = "MediaPlayerDemo";
	private static final String TAG_TOPLAY = "Please wait,Loading the Video";
	private static final String TAG_PLAYING = "RTSP:Playing the sample.mp4";
	private int mVideoWidth;			
	private int mVideoHeight;
	private MediaPlayer mMediaPlayer;
	private SurfaceView mPreview;
	private SurfaceHolder holder;
	private String path;
	private boolean mIsVideoSizeKnown = false;
	private boolean mIsVideoReadyToBePlayed = false;
	private Button buttonPlay, buttonPause, buttonReset, buttonStop;
	private TextView mTextView;
	private TextView txtLoad;
    private SeekBar  mSeekBar;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video);

		 mPreview = (SurfaceView)findViewById(R.id.surface);
		 // To set the screen on.
		 mPreview.setKeepScreenOn(true);
		 holder = mPreview.getHolder();
		 holder.addCallback(this);
		 holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

		 //Button bind
		 buttonPlay = (Button)findViewById(R.id.button_play);
		 buttonPause = (Button)findViewById(R.id.button_pause);
		 buttonReset = (Button)findViewById(R.id.button_reset);
		 buttonStop = (Button)findViewById(R.id.button_stop);
		 mTextView = (TextView)findViewById(R.id.txt_video);
		 txtLoad = (TextView)findViewById(R.id.txt_load);
		 mSeekBar = (SeekBar)findViewById(R.id.seekBar_move);
		 
		 //Button Listener
		 buttonPlay.setOnClickListener(new OnClickListener(){
			 
			 @Override
			 public void onClick(View v) {
				 //Movie start here.
				 mMediaPlayer.start();	
			 }	
		 });
		 buttonPause.setOnClickListener(new OnClickListener(){
		
			 @Override
			 public void onClick(View v) {
				 // Movie pause here.
				 mMediaPlayer.pause();
			 }
			
		 });
		 buttonReset.setOnClickListener(new OnClickListener(){
		
			 @Override
			 public void onClick(View v) {
				 // Replay the movie.
				 mMediaPlayer.seekTo(0);
			 }
		
		 });
		 buttonStop.setOnClickListener(new OnClickListener(){
		
			 @Override
			 public void onClick(View v) {
				// The video stop here
			    mMediaPlayer.stop();		
				finish();
				
			 }
		
		 });
		 // Set the title info
		 mTextView.setText(TAG_TOPLAY);
	     mSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				mMediaPlayer.seekTo(progress);
				
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}});
	     
		 
	}
	/**
	 * Play the video from the local RTSP.
	 */
	private void playVideo() {
		doCleanUp();
		try {
			/*
			 * Set path variable to progressive streamable mp4 or 3gpp format
			 * URL. Http protocol should be used. Mediaplayer can only play
			 * "progressive streamable contents" which basically means: 
			 * 1. the movie atom has to precede all the media data atoms. 
			 * 2. The clip has to be reasonably interleaved.
			 */
			
			// The path for test. START
			// path="http://www.androidbook.com/akc/filestorage/android/documentfiles/3389/movie.mp4";
			// path="http://shawbenwiki.github.com/iMedia-FM/video.mp4";
			// path="rtsp://218.204.223.237:554/live/1/298E015776FC3141/x5sn4xrdxblb9v3g.sdp";
			// path="rtsp://218.204.223.237:554/live/1/4C024DFE77DC717D/47e6sx9zybiwlz6y.sdp";
			// path="rtsp://184.72.239.149/vod/mp4:BigBuckBunny_175k.mov";
			// The path for test. END
			
			// The local service link
			path = "rtsp://192.168.1.230:1935/vod/mp4:sample-bk.mp4";
			
			// Create a new media player and set the listeners
			mMediaPlayer = new MediaPlayer();
			mMediaPlayer.setDataSource(path);
			mMediaPlayer.setDisplay(holder);
			mMediaPlayer.prepare();
			mMediaPlayer.setOnBufferingUpdateListener(this);
			mMediaPlayer.setOnCompletionListener(this);
			mMediaPlayer.setOnPreparedListener(this);
			mMediaPlayer.setOnVideoSizeChangedListener(this);
			mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mSeekBar.setMax(mMediaPlayer.getDuration());
			
			
		} catch (Exception e) {
			Log.e(TAG, "error: " + e.getMessage(), e);
		}
	}

	@Override
	public void onBufferingUpdate(MediaPlayer arg0, int percent) {
		Log.d(TAG, "onBufferingUpdate percent:" + percent);

	}

	@Override
	public void onCompletion(MediaPlayer arg0) {
		Log.d(TAG, "onCompletion called");
	}

	@Override
	public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
		Log.v(TAG, "onVideoSizeChanged called");
		if (width == 0 || height == 0) {
			Log.e(TAG, "invalid video width(" + width + ") or height(" + height
					+ ")");
			return;
		}
		
		mIsVideoSizeKnown = true;
		mVideoWidth = width;
		mVideoHeight = height;
		if (mIsVideoReadyToBePlayed && mIsVideoSizeKnown) {
			startVideoPlayback();
		}
	}

	@Override
	public void onPrepared(MediaPlayer mediaplayer) {
		Log.d(TAG, "onPrepared called");
		mIsVideoReadyToBePlayed = true;
		if (mIsVideoReadyToBePlayed && mIsVideoSizeKnown) {
			startVideoPlayback();
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder surfaceholder, int i, int j, int k) {
		//Log.d(TAG, "surfaceChanged called");
		
	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder surfaceholder) {
		Log.d(TAG, "surfaceDestroyed called");
	}
	
	// Will be called,When the SurfaceView create first time.
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		 
		Log.d(TAG, "surfaceCreated called");
		
		
		// Do not create the thread use it. START
		// playVideo();
		// Do not create the thread use it. END
		
		// Create a thread to play the video
		Thread thread = new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Log.i(TAG,"Video thread");
				Message msg = new Message();
				msg.what = 1;
				handler.sendMessage(msg);
			}});
		thread.start();
		
	}
	 
	/*
	 * Start to playVideo().
	 */
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg){
			// Get the message from thread.
			mTextView.setText(TAG_PLAYING);
			txtLoad.setVisibility(View.INVISIBLE);			
			playVideo();
		}
	};
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 */

	@Override
	protected void onPause() {
		super.onPause();
		 releaseMediaPlayer();
		 doCleanUp();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		releaseMediaPlayer();
		doCleanUp();
	}

	private void releaseMediaPlayer() {
		if (mMediaPlayer != null) {
			mMediaPlayer.release();
			mMediaPlayer = null;
		}
	}

	private void doCleanUp() {
		mVideoWidth = 0;
		mVideoHeight = 0;
		mIsVideoReadyToBePlayed = false;
		mIsVideoSizeKnown = false;
	}

	private void startVideoPlayback() {
		Log.v(TAG, "startVideoPlayback");
		holder.setFixedSize(mVideoWidth, mVideoHeight);
		mMediaPlayer.start();
	}

}
