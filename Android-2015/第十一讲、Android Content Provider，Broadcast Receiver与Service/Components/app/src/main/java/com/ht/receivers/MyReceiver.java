package com.ht.receivers;

import java.util.Set;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.ht.components.MainActivity;

public class MyReceiver extends BroadcastReceiver {

	private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
	private static final String PHONE_STATE = "android.intent.action.PHONE_STATE";
	private static final String OUTGOING_CALL = "android.intent.action.NEW_OUTGOING_CALL";
	private static final String BOOT_COMPLETED = "android.intent.action.BOOT_COMPLETED";
	private static final String CUSTOMIZE_ACTION = "com.ht.action.CUSTOMIZE";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		Log.d("My Receiver", "action: " + action);
		if (action.equals(SMS_RECEIVED)) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				Set<String> keySet = bundle.keySet();
				for (String key : keySet) {
					Log.d("My Receiver", "extra key: " + key);
				}
				Object[] objArray = (Object[]) bundle.get("pdus");
				SmsMessage[] msgs = new SmsMessage[objArray.length];
				for (int i = 0; i < objArray.length; i++) {
					msgs[i] = SmsMessage.createFromPdu((byte[]) objArray[i]);
					String str = "手机号：" + msgs[i].getOriginatingAddress()
							+ "短信：" + msgs[i].getDisplayMessageBody();
					Toast.makeText(context, str, Toast.LENGTH_LONG).show();
				}
			}
		} else if (action.equals(PHONE_STATE)) {
			TelephonyManager tm = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
			switch (tm.getCallState()) {
			case TelephonyManager.CALL_STATE_RINGING:
				String incoming = intent.getStringExtra("incoming_number");
				Toast.makeText(context, "拨入电话：" + incoming, Toast.LENGTH_LONG).show();
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK:
				Log.d("My Receiver", "正在接听电话");
				break;
			case TelephonyManager.CALL_STATE_IDLE:
				Toast.makeText(context, "挂断电话", Toast.LENGTH_LONG).show();
				break;
			}
		} else if (action.equals(OUTGOING_CALL)) {
			String outgoing = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
			Toast.makeText(context, "拨打电话：" + outgoing, Toast.LENGTH_LONG).show();
		} else if (action.equals(BOOT_COMPLETED)) {
			Intent firstActivity = new Intent(context, MainActivity.class);
			firstActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(firstActivity);
		} else if (action.equals(CUSTOMIZE_ACTION)) {
			String info = intent.getStringExtra("info");
			Toast.makeText(context, "接收自定义广播消息：" + info, Toast.LENGTH_LONG).show();
		}
	}

}
