package com.ht.otherbasic;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	
	private ImageView bmwLogo;
	private FrameLayout dragDropRegion;
	
	private Button noHandlerBtn;
	private Button handlerBtn;
	private Button handlerPostBtn;
	private Handler handler;
	
	private TextView timeTxt;
	private Button startTimeBtn;
	private Button endTimeBtn1;
	private Button endTimeBtn2;
	private Button showToastBtn;
	private long time = 0;
	private boolean isRunning = true;
	
	private TextView currentTimeTxt;
	private Chronometer chronometer;
	private Button startTimeBtn1;
	private Button endTimeBtn3;
	private Button restartTimeBtn;
	
	private ProgressBar downProgress;
	private Button downBtn;
	private boolean isDownloaded = false;
	
	private AlarmManager alarmManager;
	private Button alarmBtn;
	private Button cancelAlarmBtn;
	
	private ProgressBar downProgress1;
	private TextView downProgressTxt;
	private Button downBtn1;
	private Button cancelDownBtn;
	private ProgressAsyncTask asyncTask;
	
	private Button loaderBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		bmwLogo = (ImageView) findViewById(R.id.bmw);
		dragDropRegion = (FrameLayout) findViewById(R.id.dragDropRegion);
		
		noHandlerBtn = (Button) findViewById(R.id.noHandlerBtn);
		handlerBtn = (Button) findViewById(R.id.handlerBtn);
		handlerPostBtn = (Button) findViewById(R.id.handlerPostBtn);
		
		timeTxt = (TextView) findViewById(R.id.timeTxt);
		startTimeBtn = (Button) findViewById(R.id.startTimeBtn);
		endTimeBtn1 = (Button) findViewById(R.id.endTimeBtn1);
		endTimeBtn2 = (Button) findViewById(R.id.endTimeBtn2);
		showToastBtn = (Button) findViewById(R.id.showToastBtn);
		
		currentTimeTxt = (TextView) findViewById(R.id.currentTimeTxt);
		startTimeBtn1 = (Button) findViewById(R.id.startTimeBtn1);
		endTimeBtn3 = (Button) findViewById(R.id.endTimeBtn3);
		restartTimeBtn = (Button) findViewById(R.id.restartTimeBtn);
		chronometer = (Chronometer) findViewById(R.id.chronometer);
		OnTimeClick onTimeClick = new OnTimeClick();
		startTimeBtn1.setOnClickListener(onTimeClick);
		endTimeBtn3.setOnClickListener(onTimeClick);
		restartTimeBtn.setOnClickListener(onTimeClick);
		
		downProgress = (ProgressBar) findViewById(R.id.downProgress);
		downBtn = (Button) findViewById(R.id.downBtn);
		
		alarmBtn = (Button) findViewById(R.id.alarmBtn);
		cancelAlarmBtn = (Button) findViewById(R.id.cancelAlarmBtn);
		alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		
		downProgress1 = (ProgressBar) findViewById(R.id.downProgress1);
		downProgressTxt = (TextView) findViewById(R.id.downProgressTxt);
		downBtn1 = (Button) findViewById(R.id.downBtn1);
		cancelDownBtn = (Button) findViewById(R.id.cancelDownBtn);
		
		loaderBtn = (Button) findViewById(R.id.loaderBtn);
		
		bmwLogo.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(bmwLogo);
				v.startDrag(null, shadowBuilder, null, 0);
				return true;
			}
			
		});
		dragDropRegion.setOnDragListener(new OnDragListener() {

			@Override
			public boolean onDrag(View v, DragEvent event) {
				int action = event.getAction();
				switch (action) {
				case DragEvent.ACTION_DRAG_STARTED :
					Log.d("Drag and drop", "start to drag the image view");
					break;
				case DragEvent.ACTION_DRAG_ENTERED :
					Log.d("Drag and drop", "enter the area!");
					break;
				case DragEvent.ACTION_DRAG_LOCATION :
					Log.d("Drag and drop", "drag the view to: " + event.getX() + ", " + event.getY());
					break;
				case DragEvent.ACTION_DRAG_EXITED :
					Log.d("Drag and drop", "exit the area!");
					break;
				case DragEvent.ACTION_DROP :
					Log.d("Drag and drop", "drop the view at: " + event.getX() + ", " + event.getY());
					ImageView imageView = new ImageView(MainActivity.this);
					imageView.setImageDrawable(bmwLogo.getDrawable());
					FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
							FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
					params.leftMargin = (int) event.getX() - bmwLogo.getWidth() / 2;
					params.topMargin = (int) event.getY() - bmwLogo.getHeight() / 2;
					dragDropRegion.addView(imageView, params);
					break;
				case DragEvent.ACTION_DRAG_ENDED :
					Log.d("Drag and drop", "end the drag");
					break;
					default : return false;
				}
				return true;
			}
			
		});
		
		noHandlerBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						noHandlerBtn.setText("更新的文字信息");
					}
					
				}).start();
			}
			
		});
		
		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				int what = msg.what;
				switch(what) {
				case 1 :
					handlerBtn.setText("更新后的文字信息");
					break;
				case 2 :
					int currentProgress = downProgress.getProgress();
					Log.d("====", "=======" + currentProgress);
					if (currentProgress >= downProgress.getMax()) {
						isDownloaded = true;
					} else {
						downProgress.setProgress(currentProgress + 10);
						Log.d("========", "=================");
					}
					break;
					default : break;
				}
				super.handleMessage(msg);
			}
			
		};
		handlerBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						handler.sendEmptyMessage(1);
					}
					
				}).start();
			}
			
		});
		
		handlerPostBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						handler.post(new Runnable() {

							@Override
							public void run() {
								handlerPostBtn.setText("更新后的文字信息");
							}
							
						});
					}
					
				}).start();
			}
			
		});
		
		final Runnable timeRunnable = new Runnable() {

			@Override
			public void run() {
				if (isRunning) {
					timeTxt.setText(time++ + "");
					handler.postDelayed(this, 1 * 1000);
				}
			}
			
		};
		
		startTimeBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				isRunning = true;
				handler.postDelayed(timeRunnable, 1 * 1000);
			}
			
		});
		
		endTimeBtn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				handler.removeCallbacks(timeRunnable);
				time = 0;
			}
			
		});
		
		endTimeBtn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				isRunning = false;
				time = 0;
			}
			
		});
		
		showToastBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				handler.postAtTime(new Runnable() {

					@Override
					public void run() {
						Toast.makeText(MainActivity.this, "10S后显示的Toast", Toast.LENGTH_LONG).show();
					}
					
				}, android.os.SystemClock.uptimeMillis() + 10 * 1000);
			}
			
		});
		
		chronometer.setFormat("计时器：%s");
		chronometer.setOnChronometerTickListener(new OnChronometerTickListener() {

			@Override
			public void onChronometerTick(Chronometer chronometer) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				currentTimeTxt.setText("当前时间：" + sdf.format(Calendar.getInstance().getTime()));
			}
			
		});
		
		downBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {

					@Override
					public void run() {
						if (!isDownloaded) {
							handler.sendEmptyMessage(2);
						}
					}
					
				}, 0, 2 * 1000);
			}
			
		});
		
		Intent intent = new Intent(this, AlarmActivity.class);
		final PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
		alarmBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, 5 * 1000, 60 * 2 * 1000, pendingIntent);
				// alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, 
				//		Calendar.getInstance().getTimeInMillis(), 24 * 60 * 60 * 1000, pendingIntent);
			}
		});
		
		cancelAlarmBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				alarmManager.cancel(pendingIntent);
			}
		});
		
		downBtn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				asyncTask = new ProgressAsyncTask();
				asyncTask.execute("str1", "str2", "str3", "str4", "str5");
			}
			
		});
		
		cancelDownBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				asyncTask.cancel(true);
			}
			
		});
		
		loaderBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				startActivity(new Intent(MainActivity.this, LoaderActivity.class));
			}
			
		});
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
	
	private class OnTimeClick implements OnClickListener {

		@Override
		public void onClick(View v) {
			int id = v.getId();
			switch (id) {
			case R.id.startTimeBtn1 : 
				chronometer.start();
				break;
			case R.id.endTimeBtn3 :
				chronometer.stop();
				break;
			case R.id.restartTimeBtn :
				chronometer.setBase(SystemClock.elapsedRealtime());
				break;
				default : break;
			}
		}
		
	}
	
	private class ProgressAsyncTask extends AsyncTask<String, Integer, Integer> {

		private int count;
		
		@Override
		protected Integer doInBackground(String... params) {
			count = params.length;
			downProgress1.setMax(count);
			for (int i = 0; i < count; i++) {
				if (isCancelled()) {
					break;
				}
				publishProgress(i + 1);
				try {
					Thread.sleep(2 * 1000);
				} catch (Exception e) {
				}
			}
			return count;
		}

		@Override
		protected void onPostExecute(Integer result) {
			super.onPostExecute(result);
			Toast.makeText(MainActivity.this, "下载完成，共下载" + result + "个", Toast.LENGTH_LONG).show();
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			downProgress1.setProgress(values[0]);
			downProgressTxt.setText(100 * values[0] / count + "%");
		}

		@Override
		protected void onCancelled() {
			Toast.makeText(MainActivity.this, "取消下载 任务", Toast.LENGTH_LONG).show();
			super.onCancelled();
		}
		
	}
	
}
