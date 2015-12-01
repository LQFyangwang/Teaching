package com.ht.widget2;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SettingsActivity extends ActionBarActivity {
	
	private ExpandableListView settings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		settings = (ExpandableListView) findViewById(R.id.settings);
		
		SettingExpandableListAdapter settingAdapter = new SettingExpandableListAdapter(SettingsActivity.this);
		settings.setAdapter(settingAdapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.settings, menu);
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

class SettingExpandableListAdapter extends BaseExpandableListAdapter {
	
	private Context context;
	
	private final int[] icons = {R.drawable.setting_1, R.drawable.setting_2, R.drawable.setting_3};
	private final String[] titles = {"设置1", "设置2", "设置3"};
	private final String[][] subs = {
			{"设置1-1", "设置1-2", "设置1-3", "设置1-4"}, 
			{"设置2-1", "设置2-2", "设置2-3"},
			{"设置3-1", "设置3-2", "设置3-3", "设置3-4"}}; 

	public SettingExpandableListAdapter(Context context) {
		this.context = context;
	}
	
	@Override
	public int getGroupCount() {
		return titles.length;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return subs[groupPosition].length;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return titles[groupPosition];
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return subs[groupPosition][childPosition];
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		LinearLayout linearLayout = new LinearLayout(context);
		linearLayout.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, AbsListView.LayoutParams.WRAP_CONTENT));
		linearLayout.setOrientation(LinearLayout.HORIZONTAL);
		linearLayout.setPadding(60, 0, 0, 0);
		ImageView imageView = getImageView();
		imageView.setImageResource(icons[groupPosition]);
		linearLayout.addView(imageView);
		TextView textView = getTextView();
		textView.setText(titles[groupPosition]);
		linearLayout.addView(textView);
		return linearLayout;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		TextView textView = getTextView();
		textView.setText(subs[groupPosition][childPosition]);
		return textView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}
	
	private ImageView getImageView() {
		ImageView imageView = new ImageView(context);
		AbsListView.LayoutParams params = new AbsListView.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, AbsListView.LayoutParams.WRAP_CONTENT);
		params.height = 80;
		params.width = 80;
		imageView.setLayoutParams(params);
		return imageView;
	}
	
	private TextView getTextView() {
		TextView textView = new TextView(context);
		AbsListView.LayoutParams params = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT);
		textView.setLayoutParams(params);
		return textView;
	}
	
}
