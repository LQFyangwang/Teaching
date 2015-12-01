package com.ht.otherbasic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class LoaderActivity extends ActionBarActivity {

	private Button loadData;
	private ListView listView;
	private ArrayAdapter<String> arrayAdapter;
	private MyLoaderCallbacks myLoaderCallbacks;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loader);
		
		myLoaderCallbacks = new MyLoaderCallbacks();
		Log.d("loader", "==== init loader");
		getLoaderManager().initLoader(1, null, myLoaderCallbacks);
		
		loadData = (Button) findViewById(R.id.loadData);
		listView = (ListView) findViewById(R.id.listView);
		
		List<String> strList = new ArrayList<String>();
		strList.add("string 1");
		strList.add("string 2");
		strList.add("string 3");
		arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, 
				strList);
		listView.setAdapter(arrayAdapter);
		
		loadData.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d("loader", "==== restart loader");
				getLoaderManager().restartLoader(1, null, myLoaderCallbacks);
			}
			
		});
		
		listView.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				Log.d("loader list view scroll", "scroll state: " + scrollState);
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				Log.d("loader list view scroll", "first: " + firstVisibleItem + ", visible count: "
					+ visibleItemCount + ", total count: " + totalItemCount);
				if (visibleItemCount + firstVisibleItem >= totalItemCount) {
					getLoaderManager().restartLoader(1, null, myLoaderCallbacks);
				}
			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.loader, menu);
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
	
	private class MyLoaderCallbacks implements LoaderManager.LoaderCallbacks<List<String>> {

		@Override
		public Loader<List<String>> onCreateLoader(int id, Bundle args) {
			Log.d("loader", "==== onCreateLoader");
			Random random = new Random();
			int min = random.nextInt(5) + 5;
			int max = random.nextInt(10) + 10;
			return new MyLoader(LoaderActivity.this, min, max);
		}

		@Override
		public void onLoadFinished(Loader<List<String>> loader,
				List<String> data) {
			Log.d("loader", "==== onLoadFinished");
			// arrayAdapter.clear();
			arrayAdapter.addAll(data);
		}

		@Override
		public void onLoaderReset(Loader<List<String>> loader) {
			Log.d("loader", "==== onLoaderReset");
			arrayAdapter.clear();
		}
		
	}
	
}

class MyLoader extends AsyncTaskLoader<List<String>> {

	private int min;
	private int max;
	
	public MyLoader(Context context) {
		super(context);
	}
	
	public MyLoader(Context context, int min, int max) {
		super(context);
		this.min = min;
		this.max = max;
		Log.d("loader", "==== loader construct");
	}

	@Override
	public List<String> loadInBackground() {
		Log.d("loader", "==== loadInBackground");
		List<String> strList = new ArrayList<String>();
		for (int i = min; i <= max; i++) {
			strList.add("list item " + i);
		}
		return strList;
	}

	@Override
	public void deliverResult(List<String> data) {
		// TODO Auto-generated method stub
		super.deliverResult(data);
	}

	@Override
	protected void onStartLoading() {
		// TODO Auto-generated method stub
		super.onStartLoading();
		forceLoad();
	}

	@Override
	protected void onReset() {
		// TODO Auto-generated method stub
		super.onReset();
	}

	@Override
	public void onCanceled(List<String> data) {
		// TODO Auto-generated method stub
		super.onCanceled(data);
	}
	
	
}
