package com.ht.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.ht.components.R;

public class MusicService extends Service {

	private MusicBinder musicBinder;
	
	private MediaPlayer mp;
	private int currentPosition;
	private boolean isPlaying = false;

	@Override
	public IBinder onBind(Intent intent) {
		Log.d("MusicService", "onBind");
		return musicBinder;
	}

	@Override
	public void onCreate() {
		Log.d("MusicService", "onCreate");
		musicBinder = new MusicBinder();
		super.onCreate();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		Log.d("MusicService", "onStart");
		super.onStart(intent, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		return super.onUnbind(intent);
	}

	public class MusicBinder extends Binder {

		public MusicService getService() {
			return MusicService.this;
		}
		
	}
	
	public MediaPlayer createMediaPlayer() {
		isPlaying = false;
		if (mp != null) {
			mp.release();
		}
		mp = MediaPlayer.create(getApplicationContext(),
				R.raw.our_love_will_always_last);
		mp.setLooping(true);
		return mp;
	}

	public MediaPlayer createMediaPlayer(int resId) {
		isPlaying = false;
		if (mp != null) {
			mp.release();
		}
		mp = MediaPlayer.create(getApplicationContext(), resId);
		mp.setLooping(true);
		return mp;
	}
	
	public void setMediaPlayerListener() {
		mp.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				Log.d("MusicService", "onCompletion");
				isPlaying = false;
			}
			
		});
		mp.setOnErrorListener(new OnErrorListener() {

			@Override
			public boolean onError(MediaPlayer mp, int what, int extra) {
				return false;
			}
			
		});
	}

	public void play() {
		if (!mp.isPlaying()) {
			mp.seekTo(currentPosition);
			mp.start();
		} else {
			mp.start();
		}
		isPlaying = true;
	}
	
	public void playAtPosition(int millseconds) {
		mp.seekTo(millseconds);
		mp.start();
		isPlaying = true;
	}

	public void stop() {
		if (mp != null && mp.isPlaying()) {
			mp.stop();
			currentPosition = 0;
			mp.release();
			isPlaying = false;
		}
	}

	public void pause() {
		if (mp != null && mp.isPlaying()) {
			mp.pause();
			currentPosition = mp.getCurrentPosition();
			mp.release();
			isPlaying = false;
		}
	}
	
	public int getCurrentPosition() {
		return mp.getCurrentPosition();
	}
	
	public boolean isPlaying() {
		return isPlaying;
	}
	
	public int getDuration() {
		return mp.getDuration();
	}
	
	public String getCurrentTimeString() {
		return secondsToString(mp.getCurrentPosition());
	}
	
	public String getTotalTimeString() {
		return secondsToString(mp.getDuration());
	}
	
	public String secondsToString(int millSeconds) {
		int seconds = millSeconds / 1000;
		int minutePart = seconds / 60;
		int secondPart = seconds % 60; 
		String secondPartStr = "" + secondPart;
		if (secondPart < 10) {
			secondPartStr = "0" + secondPartStr;
		}
		return minutePart + ":" + secondPartStr;
	}


}
