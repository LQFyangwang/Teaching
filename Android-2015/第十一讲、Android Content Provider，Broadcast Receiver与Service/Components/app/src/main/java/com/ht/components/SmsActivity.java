package com.ht.components;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SmsActivity extends ActionBarActivity {

	private ListView smsList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sms);
		
		smsList = (ListView) findViewById(R.id.smsList);
		ContentResolver reslover = getContentResolver();
		Cursor smsCursor = reslover.query(Uri.parse("content://sms/inbox"), null, null, null, null);
		smsList.setAdapter(new SMSAdapter(this, smsCursor));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.sms, menu);
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
	
	private class SMSAdapter extends CursorAdapter {

		private LayoutInflater inflater;
		
		public SMSAdapter(Context context, Cursor c) {
			super(context, c, false);
			inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			return inflater.inflate(R.layout.sms_item, null);
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			TextView contact = (TextView) view.findViewById(R.id.contact);
			TextView content = (TextView) view.findViewById(R.id.content);
			contact.setText(cursor.getString(cursor.getColumnIndex("address")));
			content.setText(cursor.getString(cursor.getColumnIndex("body")));
		}

	}
	
}
