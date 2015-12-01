package com.ht.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		Log.d("MyService", "onBind");
		return new MyBinder();
	}

	@Override
	public void onCreate() {
		Log.d("MyService", "onCreate, create service");
		super.onCreate();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		Log.d("MyService", "onStart, start service");
		super.onStart(intent, startId);
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Log.d("MyService", "onUnbind");
		return super.onUnbind(intent);
	}

	@Override
	public void onDestroy() {
		Log.d("MyService", "onDestroy, stop service");
		super.onDestroy();
	}
	
	public class MyBinder extends Binder {
		public MyService getService() {
			return MyService.this;
		}
		
		public String getValue() {
			return "调用Service获取的值";
		}
	}

}
