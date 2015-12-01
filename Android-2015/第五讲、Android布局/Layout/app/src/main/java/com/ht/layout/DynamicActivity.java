package com.ht.layout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DynamicActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		LayoutInflater layoutInflater = LayoutInflater.from(this);
		// LayoutInflater layoutInflater1 = getLayoutInflater();
		// LayoutInflater layoutInflater2 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout linearLayout = (LinearLayout) layoutInflater.inflate(R.layout.activity_dynamic, null);
		for (int i = 0; i < 10; i++) {
			View view = layoutInflater.inflate(R.layout.dynamic_item, null);
			TextView textView = (TextView) view.findViewById(R.id.dynamicTxt);
			textView.setText("Text " + i);
			textView.setTextColor(getResources().getColor(R.color.blue));
			android.widget.LinearLayout.LayoutParams params = new android.widget.LinearLayout.LayoutParams(
					android.widget.LinearLayout.LayoutParams.WRAP_CONTENT, 
					android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);
			params.gravity = Gravity.CENTER_HORIZONTAL;
			linearLayout.addView(view, params);
		}
		setContentView(linearLayout);
	}
}
