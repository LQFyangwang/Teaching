package com.ht.components;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ht.receivers.MyReceiver1;
import com.ht.services.MyService;

public class MainActivity extends ActionBarActivity {

	private Button contactBtn;
	private Button smsBtn;
	
	private Button allBtn;
	private Button idBtn;
	private Button nameBtn;
	
	private Button bindBtn;
	private Button unbindBtn;
	private MyReceiver1 myReceiver1;
	private Button sendBtn;
	
	private Button startService;
	private Button stopService;
	private Button bindService;
	private Button unbindService;
	private Button getValueFromService;
	private TextView valueFromService;
	private ServiceConnection sc;
	private MyService.MyBinder myBinder;
	
	private Button musicBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		contactBtn = (Button) findViewById(R.id.contactBtn);
		smsBtn = (Button) findViewById(R.id.smsBtn);
		
		allBtn = (Button) findViewById(R.id.allBtn);
		idBtn = (Button) findViewById(R.id.idBtn);
		nameBtn = (Button) findViewById(R.id.nameBtn);
		
		bindBtn = (Button) findViewById(R.id.bindBtn);
		unbindBtn = (Button) findViewById(R.id.unbindBtn);
		myReceiver1 = new MyReceiver1();
		sendBtn = (Button) findViewById(R.id.sendBtn);
		
		startService = (Button) findViewById(R.id.startService);
		stopService = (Button) findViewById(R.id.stopService);
		bindService = (Button) findViewById(R.id.bindService);
		unbindService = (Button) findViewById(R.id.unbindService);
		getValueFromService = (Button) findViewById(R.id.getValueFromService);
		valueFromService = (TextView) findViewById(R.id.valueFromService);
		
		musicBtn = (Button) findViewById(R.id.musicBtn);
		
		contactBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, ContactsActivity.class));
			}
			
		});
		
		
		smsBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, SmsActivity.class));
			}
			
		});
		
		allBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ContentResolver resolver = getContentResolver();
				Cursor cursor = resolver.query(Uri.parse("content://com.ht.providers.myprovider/all"), null, null, null, null);
				String str = "";
				for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
					int id = cursor.getInt(cursor.getColumnIndex("id"));
					String name = cursor.getString(cursor.getColumnIndex("name"));
					String password = cursor.getString(cursor.getColumnIndex("password"));
					str += "id: " + id + ", name: " + name + ", paswword: " + password + "\n";
				}
				Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
			}
			
		});
		
		idBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Cursor cursor = getContentResolver().query(Uri.parse("content://com.ht.providers.myprovider/id/1"), null, null, null, null);
				cursor.moveToFirst();
				int id = cursor.getInt(cursor.getColumnIndex("id"));
				String name = cursor.getString(cursor.getColumnIndex("name"));
				String password = cursor.getString(cursor.getColumnIndex("password"));
				Toast.makeText(MainActivity.this, "id: " + id + ", name: " + name + ", password: " + password, Toast.LENGTH_LONG).show();
			}
			
		});
		
		nameBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Cursor cursor = getContentResolver().query(Uri.parse("content://com.ht.providers.myprovider/name/Wgssmart"), null, null, null, null);
				cursor.moveToFirst();
				int id = cursor.getInt(cursor.getColumnIndex("id"));
				String name = cursor.getString(cursor.getColumnIndex("name"));
				String password = cursor.getString(cursor.getColumnIndex("password"));
				Toast.makeText(MainActivity.this, "id: " + id + ", name: " + name + ", password: " + password, Toast.LENGTH_LONG).show();
			}
			
		});
		
		bindBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
				filter.setPriority(1500);
				filter.addAction(Intent.ACTION_SCREEN_ON);
				filter.addAction(Intent.ACTION_SCREEN_OFF);
				filter.addAction(Intent.ACTION_BATTERY_CHANGED);
				registerReceiver(myReceiver1, filter);
				Toast.makeText(MainActivity.this, "注册广播接收器成功", Toast.LENGTH_LONG).show();
			}
			
		});
		
		unbindBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				unregisterReceiver(myReceiver1);
				Toast.makeText(MainActivity.this, "解除广播接收器成功", Toast.LENGTH_LONG).show();
			}
			
		});
		
		sendBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent("com.ht.action.CUSTOMIZE");
				intent.addCategory("com.ht.category.CUSTOMIZE");
				intent.putExtra("info", "广播消息");
				sendBroadcast(intent);
				Toast.makeText(MainActivity.this, "发送广播成功", Toast.LENGTH_SHORT).show();
			}
			
		});
		
		startService.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent serviceIntent = new Intent(MainActivity.this, MyService.class);
				startService(serviceIntent);
			}
			
		});
		
		stopService.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent serviceIntent = new Intent(MainActivity.this, MyService.class);
				stopService(serviceIntent);
			}
			
		});
		
		sc = new ServiceConnection() {

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				myBinder = (MyService.MyBinder) service;
				String value = myBinder.getValue();
				Log.d("service connected", "value from service: " + value);
			}

			@Override
			public void onServiceDisconnected(ComponentName name) {
				Log.d("service disconnected", "dis");
				valueFromService.setText("Value from service");
			}
			
		};
		
		bindService.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				bindService(new Intent(MainActivity.this, MyService.class), sc, Context.BIND_AUTO_CREATE);
			}
			
		});
		
		unbindService.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				unbindService(sc);
			}
			
		});
		
		getValueFromService.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				valueFromService.setText(myBinder.getValue());
			}
			
		});
		
		musicBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, MusicActivity.class));
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
}
