package com.ht.widget2;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class NewsActivity extends ActionBarActivity {
	
	private ListView newsList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		
		newsList = (ListView) findViewById(R.id.newsList);
		
		Resources res = getResources();
		String[] newsImage = res.getStringArray(R.array.newsImage);
		String[] newsTitle = res.getStringArray(R.array.newsTitle);
		String[] newsAbstract = res.getStringArray(R.array.newsAbstract);
		String[] newsDate = res.getStringArray(R.array.newsDate);
		
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		for (int i = 0, length = newsImage.length; i < length; i++) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("id", "id_" + i);
			item.put("newsImage", getResourceId(newsImage[i]));
			item.put("newsTitle", newsTitle[i]);
			item.put("newsAbstract", newsAbstract[i]);
			item.put("newsDate", newsDate[i]);
			items.add(item);
		}
		SimpleAdapter newsAdapter = new SimpleAdapter(this, items, R.layout.news_item,
				new String[]{"newsImage", "newsTitle", "newsAbstract", "newsDate"}, 
				new int[]{R.id.newsImage, R.id.newsTitle, R.id.newsAbstract, R.id.newsDate});
		newsList.setAdapter(newsAdapter);
		
		newsList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				@SuppressWarnings("unchecked")
				Map<String, Object> item = (Map<String, Object>) newsList.getItemAtPosition(position);
				Toast.makeText(getApplicationContext(), "你点击的新闻id为：" + item.get("id"), Toast.LENGTH_LONG).show();
			}
			
		});
		
	}

	private int getResourceId(String name) {
		try {
			Field field = R.drawable.class.getField(name);
			return field.getInt(null);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.news, menu);
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
