package com.ht.widget2;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListActivity extends ActionBarActivity {
	
	private ListView companyList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		companyList = (ListView) findViewById(R.id.companyList);
		
		List<String> companys = new ArrayList<String>();
		companys.add("腾讯");
		companys.add("阿里巴巴");
		companys.add("百度");
		companys.add("大疆");
		companys.add("搜狐");
		companys.add("新浪");
		ArrayAdapter<String> aryAdapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1, companys);
		companyList.setAdapter(aryAdapter);
		companyList.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Log.d("List Activity", "company list view item selected");
				Toast.makeText(getApplicationContext(), 
						"您选择的公司 ：" + (String) companyList.getSelectedItem(), Toast.LENGTH_LONG).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
			
		});
		companyList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.d("List Activity", "company list view item clicked");
				Toast.makeText(getApplicationContext(), 
						"您选择的公司 ：" + (String) companyList.getItemAtPosition(position), Toast.LENGTH_LONG).show();
			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.list, menu);
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
