package com.ht.widget2;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewStub;
import android.widget.Button;

public class ViewStubActivity extends ActionBarActivity {

	private Button loadWidgetsBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_stub);
		
		loadWidgetsBtn = (Button) findViewById(R.id.loadWidgetsBtn);
		
		loadWidgetsBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				View view = findViewById(R.id.viewStub);
				if (view != null) {
					ViewStub viewStub = (ViewStub) view;
					// viewStub.inflate();
					viewStub.setVisibility(View.VISIBLE);
				}
			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.view_stub, menu);
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
