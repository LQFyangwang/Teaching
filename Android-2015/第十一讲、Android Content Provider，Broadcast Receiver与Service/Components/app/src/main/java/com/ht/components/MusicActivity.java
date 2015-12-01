package com.ht.components;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.ht.services.MusicService;

public class MusicActivity extends ActionBarActivity {

	private Button playBtn;
	private Button stopBtn;
	private Button pauseBtn;
	
	private ServiceConnection serviceConnection;
	private MusicService musicService;
	
	private SeekBar musicProgress;
	private TextView musicProgressTxt;
	
	private Handler handler;
	
	private boolean toUpdateSeekBar = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_music);
		
		playBtn = (Button) findViewById(R.id.playBtn);
		stopBtn = (Button) findViewById(R.id.stopBtn);
		pauseBtn = (Button) findViewById(R.id.pauseBtn);
		
		musicProgress = (SeekBar) findViewById(R.id.musicProgress);
		musicProgressTxt = (TextView) findViewById(R.id.musicProgressTxt);
		
		serviceConnection = new ServiceConnection() {

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				Log.d("MusicService", "onServiceConnected");
				MusicService.MusicBinder musicBinder = (MusicService.MusicBinder) service;
				musicService = musicBinder.getService();
			}

			@Override
			public void onServiceDisconnected(ComponentName name) {
				Log.d("MusicService", "onServiceDisconnected");
			}
			
		};
		
		bindService(new Intent(MusicActivity.this, MusicService.class), serviceConnection, Context.BIND_AUTO_CREATE);
		
		MusicBtnClick musicBtnClick = new MusicBtnClick();
		playBtn.setOnClickListener(musicBtnClick);
		stopBtn.setOnClickListener(musicBtnClick);
		pauseBtn.setOnClickListener(musicBtnClick);
		musicProgress.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if (musicService.isPlaying()) {
					musicProgressTxt.setText(musicService.secondsToString(progress) + " / " + musicService.getTotalTimeString());
				}	
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				toUpdateSeekBar = false;
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				toUpdateSeekBar = true;
				play(seekBar.getProgress());
			}
			
		});
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.music, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private class MusicBtnClick implements OnClickListener {
		
		@Override
		public void onClick(View v) {
			int btnId = v.getId();
			switch (btnId) {
			case R.id.playBtn:
				play(0);
				break;
			case R.id.stopBtn:
				musicService.stop();
				break;
			case R.id.pauseBtn:
				musicService.pause();
				break;
				default:break;
			}
		}
		
	}
	
	public void play(int position) {
		musicService.createMediaPlayer(R.raw.our_love_will_always_last);
		musicService.setMediaPlayerListener();
		musicProgress.setMax(musicService.getDuration());
		if (position == 0) {
			musicService.play();
		} else {
			musicService.playAtPosition(position);
		}
		changeMusicProgress();
	}
	
	public void changeMusicProgress() {

		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				Bundle data = msg.getData();
				if (toUpdateSeekBar) {
					musicProgressTxt.setText(data.getString("time") + " / " + musicService.getTotalTimeString());
					musicProgress.setProgress(data.getInt("currentPosition"));
				}
				super.handleMessage(msg);
			}
			
		};
		
		Thread musicThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (musicService.isPlaying()) {
					int currentPosition = musicService.getCurrentPosition();
					Message msg = new Message();
					msg.what = 1;
					Bundle data = new Bundle();
					data.putString("time",musicService.getCurrentTimeString());
					data.putInt("currentPosition", currentPosition);
					msg.setData(data);
					handler.sendMessage(msg);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		});
		musicThread.start();
	}
	
}


