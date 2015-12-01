package com.ht.media;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.VideoView;

import com.ht.services.MusicService;

public class MainActivity extends ActionBarActivity {

	private Button playBtn;
	private Button stopBtn;
	private Button pauseBtn;

	private ServiceConnection serviceConnection;
	private MusicService musicService;

	private SeekBar musicProgress;
	private TextView musicProgressTxt;

	private Handler handler;

	private boolean toUpdateSeekBar = true;
	
	private ProgressDialog pd;
	
	private Button startRecordBtn;
	private Button stopRecordBtn;
	private Button playRecordBtn;
	private Button delRecordBtn;
	private File recordAudioFile;
	private MediaRecorder mr;
	private MediaPlayer mp;
	
	private VideoView videoView;
	
	private Button recordVideo;
	
	private Button sysCaptureBtn;
	private ImageView sysCaptureImg;

	private Button toCaptureBtn;
	
	private Button ringtoneBtn;
	private Button alarmBtn;
	private Button notificationBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		playBtn = (Button) findViewById(R.id.playBtn);
		stopBtn = (Button) findViewById(R.id.stopBtn);
		pauseBtn = (Button) findViewById(R.id.pauseBtn);

		musicProgress = (SeekBar) findViewById(R.id.musicProgress);
		musicProgressTxt = (TextView) findViewById(R.id.musicProgressTxt);
		
		startRecordBtn = (Button) findViewById(R.id.startRecordBtn);
		stopRecordBtn = (Button) findViewById(R.id.stopRecordBtn);
		playRecordBtn = (Button) findViewById(R.id.playRecordBtn);
		delRecordBtn = (Button) findViewById(R.id.delRecordBtn);
		
		videoView = (VideoView) findViewById(R.id.videoView);
		
		recordVideo = (Button) findViewById(R.id.recordVideo);
		
		sysCaptureBtn = (Button) findViewById(R.id.sysCaptureBtn);
		sysCaptureImg = (ImageView) findViewById(R.id.sysCaptureImg);
		
		toCaptureBtn = (Button) findViewById(R.id.toCaptureBtn);
 
		ringtoneBtn = (Button) findViewById(R.id.ringtoneBtn);
		alarmBtn = (Button) findViewById(R.id.alarmBtn);
		notificationBtn = (Button) findViewById(R.id.notificationBtn);
		
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

		bindService(new Intent(MainActivity.this, MusicService.class),
				serviceConnection, Context.BIND_AUTO_CREATE);

		MusicBtnClick musicBtnClick = new MusicBtnClick();
		playBtn.setOnClickListener(musicBtnClick);
		stopBtn.setOnClickListener(musicBtnClick);
		pauseBtn.setOnClickListener(musicBtnClick);
		musicProgress.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if (musicService.isPlaying()) {
					musicProgressTxt.setText(musicService
							.secondsToString(progress)
							+ " / "
							+ musicService.getTotalTimeString());
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				toUpdateSeekBar = false;
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				toUpdateSeekBar = true;
				if (musicService.isPlaying()) {
					musicService.playAtPosition(seekBar.getProgress());
				}
			}

		});
		
		RecordBtnClick recordBtnClick = new RecordBtnClick();
		startRecordBtn.setOnClickListener(recordBtnClick);
		stopRecordBtn.setOnClickListener(recordBtnClick);
		playRecordBtn.setOnClickListener(recordBtnClick);
		delRecordBtn.setOnClickListener(recordBtnClick);
		
		videoView.setVideoURI(Uri.parse("rtsp://184.72.239.149/vod/mp4:BigBuckBunny_115k.mov"));
		videoView.setMediaController(new MediaController(this));
		videoView.start();
		
		recordVideo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
				startActivityForResult(intent, 1);
			}
			
		});
		
		sysCaptureBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(intent, 2);
			}
			
		});
		
		toCaptureBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, CaptureActivity.class));
			}
			
		});
		
		RingtoneBtnClick ringtoneClick = new RingtoneBtnClick();
		ringtoneBtn.setOnClickListener(ringtoneClick);
		alarmBtn.setOnClickListener(ringtoneClick);
		notificationBtn.setOnClickListener(ringtoneClick);

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
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
	
	@Override
	protected void onDestroy() {
		unbindService(serviceConnection);
		super.onDestroy();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1) {
			if (resultCode == Activity.RESULT_OK) {
				Uri uri = data.getData();
				Cursor cursor = getContentResolver().query(uri, null, null, null, null);
				if (cursor.moveToFirst()) {
					String videoPath = cursor.getString(cursor.getColumnIndex("_data"));
					videoView.setVideoURI(Uri.parse(videoPath));
					videoView.setMediaController(new MediaController(this));
					videoView.start();
				}
			}
		} else if (requestCode == 2) {
			if (resultCode == Activity.RESULT_OK) {
				Bitmap cameraBitmap = (Bitmap) data.getExtras().get("data");
				sysCaptureImg.setImageBitmap(cameraBitmap);
			}
		} else if (requestCode == 3) {
			if (resultCode == Activity.RESULT_OK) {
				Uri uri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
				if (uri != null) {
					RingtoneManager.setActualDefaultRingtoneUri(MainActivity.this, RingtoneManager.TYPE_RINGTONE, uri);
				}
			}
		} else if (requestCode == 4) {
			if (resultCode == Activity.RESULT_OK) {
				Uri uri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
				if (uri != null) {
					RingtoneManager.setActualDefaultRingtoneUri(MainActivity.this, RingtoneManager.TYPE_ALARM, uri);
				}
			}
		} else if (requestCode == 5) {
			if (resultCode == Activity.RESULT_OK) {
				Uri uri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
				if (uri != null) {
					RingtoneManager.setActualDefaultRingtoneUri(MainActivity.this, RingtoneManager.TYPE_NOTIFICATION, uri);
				}
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}



	private class MusicBtnClick implements OnClickListener {

		@Override
		public void onClick(View v) {
			int btnId = v.getId();
			switch (btnId) {
			case R.id.playBtn:
				play();
				break;
			case R.id.stopBtn:
				musicService.stop();
				break;
			case R.id.pauseBtn:
				musicService.pause();
				break;
			default:
				break;
			}
		}

	}

	private void play() {
		if (!musicService.isPaused()) {
			pd = new ProgressDialog(this);
			pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pd.show();
		}
		createHanlder();
		beginPlay();
	}

	private void createHanlder() {
		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				int what = msg.what;
				switch(what) {
				case 1:
					Bundle data = msg.getData();
					if (toUpdateSeekBar) {
						musicProgressTxt.setText(data.getString("time") + " / "
								+ musicService.getTotalTimeString());
						musicProgress.setProgress(data.getInt("currentPosition"));
					}
					break;
				case 2:
					pd.dismiss();
					pd = null;
					break;
					default:break;
				}
				super.handleMessage(msg);
			}

		};
	}
	
	private void beginPlay() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// musicService.createMediaPlayer(R.raw.our_love_will_always_last);
				// musicService.createMediaPlayer(android.os.Environment.getExternalStorageDirectory()
				// + "/Music/our_love_will_always_last.mp3");
				musicService.createMediaPlayer(Uri.parse("http://qzone.haoduoge.com/music2/2015-07-05/1436061969.mp3"));
				musicService.setMediaPlayerListener();
				musicProgress.setMax(musicService.getDuration());
				if (!musicService.isPaused()) {
					handler.sendEmptyMessage(2);
				}
				musicService.play();
				while (musicService.isPlaying()) {
					int currentPosition = musicService.getCurrentPosition();
					Message msg = new Message();
					msg.what = 1;
					Bundle data = new Bundle();
					data.putString("time", musicService.getCurrentTimeString());
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
			
		}).start();
	}
	
	private class RecordBtnClick implements OnClickListener {

		@Override
		public void onClick(View v) {
			int btnId = v.getId();
			switch (btnId) {
			case R.id.startRecordBtn:
				startRecord();
				break;
			case R.id.stopRecordBtn:
				stopRecord();
				break;
			case R.id.playRecordBtn:
				playRecord();
				break;
			case R.id.delRecordBtn:
				delRecord();
				break;
			default:
				break;
			}
		}
	}
	
	private void startRecord() {
		mr = new MediaRecorder();
		try {
			recordAudioFile = File.createTempFile("record", ".amr");
		} catch (IOException e) {
			e.printStackTrace();
		}
		mr.setAudioSource(MediaRecorder.AudioSource.MIC);
		mr.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
		mr.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
		mr.setOutputFile(recordAudioFile.getAbsolutePath());
		try {
			mr.prepare();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mr.start();
	}
	
	private void stopRecord() {
		if (mr != null) {
			mr.stop();
			mr.release();
			mr = null;
		}
	}
	
	private void playRecord() {
		mp = new MediaPlayer();
		try {
			mp.setDataSource(recordAudioFile.getAbsolutePath());
			mp.prepare();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mp.start();
	}
	
	private void delRecord() {
		recordAudioFile.delete();
	}
	
	private class RingtoneBtnClick implements OnClickListener {

		@Override
		public void onClick(View v) {
			int btnId = v.getId();
			Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
			switch (btnId) {
			case R.id.ringtoneBtn:
				intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_RINGTONE);
				intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "设置来电铃声");
				startActivityForResult(intent, 3);
				break;
			case R.id.alarmBtn:
				intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_ALARM);
				intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "设置闹钟铃声");
				startActivityForResult(intent, 4);
				break;
			case R.id.notificationBtn:
				intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_NOTIFICATION);
				intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "设置通知铃声");
				startActivityForResult(intent, 5);
				break;
				default:break;
			}
		}
		
	}
	
}
