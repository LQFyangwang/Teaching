package com.ht.widget2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.app.Notification.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.RemoteViews;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	
	private ProgressBar progressBar;
	private TextView currentProgressTxt;
	private Button addProgressBtn;
	private Button minusProgressBtn;
	private Runnable progressRunnable;
	
	private Button downloadBtn;
	
	private SeekBar seekBar;
	private TextView seekBarTxt;
	
	private RatingBar ratingBar;
	
	private Spinner subjectSpinner;
	private Spinner osSpinner;
	
	private Button showCompanyBtn;
	private Button showNewsBtn;
	private Button showSelectListBtn;
	private Button showSettingBtn;
	private Button showCarBrandsBtn;
	private Button viewStubBtn;
	private Button showWeixinBtn;
	
	private Button toast1;
	private Button toast2;
	
	private Button buyCarBtn;
	private Button listBtn;
	
	private NotificationManager notificationManager;
	private Button showNotifications;
	private Button clearNotifications;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		progressBar = (ProgressBar) findViewById(R.id.progressBar4);
		currentProgressTxt = (TextView) findViewById(R.id.currentProgressTxt);
		addProgressBtn = (Button) findViewById(R.id.addProgressBtn);
		minusProgressBtn = (Button) findViewById(R.id.minusProgressBtn);
		
		downloadBtn = (Button) findViewById(R.id.downloadBtn);
		
		seekBar = (SeekBar) findViewById(R.id.seekBar1);
		seekBarTxt = (TextView) findViewById(R.id.seekBarTxt);
		
		ratingBar = (RatingBar) findViewById(R.id.ratingBar3);
		
		subjectSpinner = (Spinner) findViewById(R.id.subjects);
		osSpinner = (Spinner) findViewById(R.id.os);
		
		showCompanyBtn = (Button) findViewById(R.id.showCompanyBtn);
		showNewsBtn = (Button) findViewById(R.id.showNewsBtn);
		showSelectListBtn = (Button) findViewById(R.id.showSelectListBtn);
		showSettingBtn = (Button) findViewById(R.id.showSettingBtn);
		showCarBrandsBtn = (Button) findViewById(R.id.showCarBrandsBtn);
		viewStubBtn = (Button) findViewById(R.id.viewStubBtn);
		showWeixinBtn = (Button) findViewById(R.id.showWeixinBtn);
		
		toast1 = (Button) findViewById(R.id.toast1);
		toast2 = (Button) findViewById(R.id.toast2);
		
		buyCarBtn = (Button) findViewById(R.id.buyCarBtn);
		listBtn = (Button) findViewById(R.id.listBtn);
		
		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		showNotifications = (Button) findViewById(R.id.showNotifications);
		clearNotifications = (Button) findViewById(R.id.clearNotifications);
		
		addProgressBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (progressBar.getProgress() < 100) {
					new Thread(progressRunnable).start();
					addProgressBtn.setEnabled(false);
				}
			}
			
		});
		
		minusProgressBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				progressBar.incrementProgressBy(-10);
				currentProgressTxt.setText("进度值：" + progressBar.getProgress() + "%");
			}
		});
		
		final Handler handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 1) {
					Bundle data = msg.getData();
					int add = data.getInt("add");
					progressBar.incrementProgressBy(add);
					// progressBar.setProgress(progressBar.getProgress() + add);
					currentProgressTxt.setText("进度值：" + progressBar.getProgress() + "%");
					if (progressBar.getProgress() >= 100) {
						addProgressBtn.setEnabled(true);
					}
					Log.d("main activity", "update progress to: " + progressBar.getProgress());
				}
				super.handleMessage(msg);
			}
			
		};
		
		progressRunnable = new Runnable() {

			@Override
			public void run() {
				Log.d("main activity", "thread run");
				int progress = progressBar.getProgress();
				while (progress < 100) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					progress = progressBar.getProgress();
					Log.d("main activity", "progress bar: " + progress);
					Message msg = new Message();
					msg.what = 1;
					Bundle data = new Bundle();
					data.putInt("add", 10);
					msg.setData(data);
					handler.sendMessage(msg);
				}
			}
			
		};
		
		downloadBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
				dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				dialog.setTitle("下载进度");
				dialog.setMessage("正在玩命下载中……");
				dialog.setCancelable(true);
				dialog.setIndeterminate(true);
				dialog.show();
				new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						dialog.dismiss();
						Looper.prepare();
						Toast.makeText(getApplicationContext(), "下载完成", Toast.LENGTH_LONG).show();
						Looper.loop();
					}
					
				}).start();
			}
		});
		
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				if (seekBar.getId() == R.id.seekBar1) {
					Log.d("main activity", "seekbar滑块在移动：" + progress);
					seekBarTxt.setText("SeekBar当前值为：" + progress);
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				if (seekBar.getId() == R.id.seekBar1) {
					Log.d("main activity", "开始按住seekbar：" + seekBar.getProgress());
				}
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				if (seekBar.getId() == R.id.seekBar1) {
					Log.d("main activity", "seekbar滑块停止移动：" + seekBar.getProgress());
				}
			}
			
		});
		
		ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {

			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				float currentRating = ratingBar.getRating();
				Toast.makeText(getApplicationContext(), "Current rating: " + currentRating + ", " + rating, 
						Toast.LENGTH_LONG).show();
			}
			
		});
		
		String subjects[] = new String[]{"语文", "数学", "英语", "计算机"};
		ArrayAdapter<String> aryAdapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_spinner_item, subjects);
		subjectSpinner.setAdapter(aryAdapter);
		subjectSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				String subject = (String) subjectSpinner.getSelectedItem();
				Toast.makeText(getApplicationContext(), "position: " + position + ", id: " + id
						+ ", data: " + subject, Toast.LENGTH_LONG).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
			
		});
		
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		Map<String, Object> item1 = new HashMap<String, Object>();
		item1.put("image", R.drawable.apple);
		item1.put("des", "苹果操作系统简称iOS，全球最优秀的手机操作系统之一");
		Map<String, Object> item2 = new HashMap<String, Object>();
		item2.put("image", R.drawable.android);
		item2.put("des", "Android，中文安卓，全球最开放的手机操作系统，也可应用于其他设备");
		Map<String, Object> item3 = new HashMap<String, Object>();
		item3.put("image", R.drawable.windows);
		item3.put("des", "Windows Phone，微软推出的手机操作系统");
		items.add(item1);
		items.add(item2);
		items.add(item3);
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, items, R.layout.os_item, 
				new String[]{"image", "des"}, new int[]{R.id.osImage, R.id.osDes});
		osSpinner.setAdapter(simpleAdapter);
		
		showCompanyBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, ListActivity.class);
				startActivity(intent);
			}
			
		});
		
		showNewsBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, NewsActivity.class);
				startActivity(intent);
			}
			
		});
		
		showSelectListBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, SelectListActivity.class);
				startActivity(intent);
			}
			
		});
		
		showSettingBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
				startActivity(intent);
			}
			
		});
		
		showCarBrandsBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, CarBrandsActivity.class);
				startActivity(intent);
			}
			
		});
		
		viewStubBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, ViewStubActivity.class);
				startActivity(intent);
			}
			
		});
		
		showWeixinBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, WeixinActivity.class);
				startActivity(intent);
			}
			
		});
		
		toast1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "你好啊", Toast.LENGTH_SHORT).show();
			}
			
		});
		
		toast2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				View view = getLayoutInflater().inflate(R.layout.toast_view, null);
				Toast toast = new Toast(getApplicationContext());
				toast.setView(view);
				toast.setGravity(Gravity.CENTER, 50, 100);
				toast.setDuration(Toast.LENGTH_LONG);
				toast.show();
			}
			
		});
		
		buyCarBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new AlertDialog.Builder(MainActivity.this)
				.setIcon(R.drawable.bmw)
				.setTitle("买车")
				.setMessage("买宝马吗？")
				.setPositiveButton("对，买！", null)
				.setNegativeButton("不，谢谢", null)
				.setNeutralButton("再想想", null).show();
			}
			
		});
		
		final String cars[] =  new String[]{"宝马", "保时捷", "玛莎拉蒂", "劳斯莱斯", "自行车"};
		listBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new AlertDialog.Builder(MainActivity.this).setTitle("选择您想要的车")
				.setItems(cars, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						final AlertDialog ad = new AlertDialog.Builder(MainActivity.this)
						.setMessage("就买这个了！" + cars[which]).show();
						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {
							@Override
							public void run() {
								ad.dismiss();
							}
						}, 3 * 1000);
					}
				}).show();
			}
			
		});
		
		showNotifications.setOnClickListener(new ShowNotifications());
		clearNotifications.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				notificationManager.cancelAll();
			}
			
		});
	}

	private class ShowNotifications implements OnClickListener {

		@Override
		public void onClick(View v) {
			Resources res = getResources();
			// 普通通知
			Notification.Builder builder1 = new Notification.Builder(MainActivity.this)
			.setSmallIcon(R.drawable.bmw).setContentTitle("通知标题").setContentText("最新宝马7系！");
			notificationManager.notify(1, builder1.build());
			// 带大小图标的通知
			Notification.Builder builder2 = new Notification.Builder(MainActivity.this)
			.setSmallIcon(R.drawable.bmw).setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.bmw))
			.setContentTitle("汽车之家").setContentText("新宝马7系发布");
			notificationManager.notify(2, builder2.build());
			// 带图片的通知
			// notificationManager.cancel(3);
			Notification notification = new Notification.BigPictureStyle(new Notification.Builder(MainActivity.this)
			.setSmallIcon(R.drawable.bmw).setContentTitle("宝马7系").setContentText("抢购宝马7系"))
			.bigPicture(BitmapFactory.decodeResource(res, R.drawable.bmw)).build();
			notificationManager.notify(3, notification);
			// setContentInfo(), setNumber(), setProgress()
			RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification);
			remoteViews.setTextViewText(R.id.title, "全新宝马7系");
			remoteViews.setTextViewText(R.id.content, "全新宝马7系已经上市，奢华享受！");
			remoteViews.setImageViewResource(R.id.logo, R.drawable.bmw);
			Notification.Builder builder4 = new Notification.Builder(MainActivity.this)
			.setSmallIcon(R.drawable.bmw).setContent(remoteViews);
			notificationManager.notify(4, builder4.build());
		}
		
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
}
