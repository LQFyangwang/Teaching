package com.ht.widget2;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

public class WeixinActivity extends ActionBarActivity {
	
	private TabHost tabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weixin);
		
		tabHost = (TabHost) findViewById(R.id.tabhost);
		tabHost.setup();
		Resources res = getResources();
		tabHost.addTab(tabHost.newTabSpec("weixin").setIndicator("微信", 
				res.getDrawable(R.drawable.tab_weixin_normal)).setContent(R.id.tab1));
		tabHost.addTab(tabHost.newTabSpec("contacts").setIndicator("通讯录", 
				res.getDrawable(R.drawable.tab_address_normal)).setContent(R.id.tab2));
		tabHost.addTab(tabHost.newTabSpec("discovery").setIndicator("发现", 
				res.getDrawable(R.drawable.tab_find_frd_normal)).setContent(R.id.tab3));
		tabHost.addTab(tabHost.newTabSpec("me").setIndicator("我", 
				res.getDrawable(R.drawable.tab_settings_normal)).setContent(R.id.tab4));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.weixin, menu);
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
