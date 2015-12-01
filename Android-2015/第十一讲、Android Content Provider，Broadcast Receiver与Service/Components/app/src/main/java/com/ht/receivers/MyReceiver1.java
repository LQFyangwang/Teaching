package com.ht.receivers;

import java.util.Set;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver1 extends BroadcastReceiver {

	private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if (action.equals(SMS_RECEIVED)) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				Set<String> keySet = bundle.keySet();
				for (String key : keySet) {
					Log.d("My Receiver1", "extra key: " + key);
				}
				Object[] objArray = (Object[]) bundle.get("pdus");
				SmsMessage[] msgs = new SmsMessage[objArray.length];
				for (int i = 0; i < objArray.length; i++) {
					msgs[i] = SmsMessage.createFromPdu((byte[]) objArray[i]);
					String str = "====  手机号：" + msgs[i].getOriginatingAddress()
							+ "短信：" + msgs[i].getDisplayMessageBody();
					Toast.makeText(context, str, Toast.LENGTH_LONG).show();
				}
			}
		} else if (action.equals(Intent.ACTION_SCREEN_ON)) {
			Log.d("My Receiver1", "屏幕唤醒");
		} else if (action.equals(Intent.ACTION_SCREEN_OFF)) {
			Log.d("My Receiver1", "屏幕休眠");
		} else if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {
			Log.d("receiver", "battery");
			int level = intent.getIntExtra("level", 0);
			int scale = intent.getIntExtra("scale", 100);
			Toast.makeText(context, "电池电量：" + (level * 100) / scale + "%", Toast.LENGTH_LONG).show();
		}
	}

}
