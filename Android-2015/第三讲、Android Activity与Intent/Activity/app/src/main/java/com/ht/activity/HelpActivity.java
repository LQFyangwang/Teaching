package com.ht.activity;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class HelpActivity extends ActionBarActivity {
	
	private TextView callTxt;
	private TextView viewWebTxt;
	private TextView backHomeTxt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		
		callTxt = (TextView) findViewById(R.id.helpCallTxt);
		viewWebTxt = (TextView) findViewById(R.id.helpViewWebTxt);
		backHomeTxt = (TextView) findViewById(R.id.helpBackHome);
		
		/**
		 * 直接拨打电话
		 */
		callTxt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:13800000000"));
				startActivity(intent);
			}
			
		});
		
		/**
		 * 直接访问指定的url页面
		 */
		viewWebTxt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://gzyouxue.com"));
				startActivity(intent);
			}
			
		});
		
		/**
		 * 返回到主界面
		 */
		backHomeTxt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent("android.intent.action.MAIN");
				intent.addCategory("android.intent.category.HOME");
				startActivity(intent);
			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.help, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
