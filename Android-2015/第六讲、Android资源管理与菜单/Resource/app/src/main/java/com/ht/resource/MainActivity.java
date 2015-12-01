package com.ht.resource;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	
	private TextView changeTxt;
	private Button changeBtn;
	
	private TextView coursesTxt;
	private TextView scoreTxt;
	private Button showCoursesBtn;
	
	private TextView songsTxt;
	private EditText songsEdit;
	private Button songsBtn;
	
	private TextView welcomeTxt;
	
	private Button showImage1Btn;
	private Button showImage2Btn;
	
	private Menu menu;
	private Button addMenusBtn;
	private Button showContextMenuBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		changeTxt = (TextView) findViewById(R.id.changeTxt);
		changeBtn= (Button) findViewById(R.id.changeBtn);
		
		coursesTxt = (TextView) findViewById(R.id.coursesTxt);
		scoreTxt = (TextView) findViewById(R.id.scoreTxt);
		showCoursesBtn = (Button) findViewById(R.id.showCoursesBtn);
		
		songsTxt = (TextView) findViewById(R.id.songsTxt);
		songsEdit = (EditText) findViewById(R.id.songsEdit);
		songsBtn = (Button) findViewById(R.id.songsBtn);
		
		showImage1Btn = (Button) findViewById(R.id.showImage1Btn);
		showImage2Btn = (Button) findViewById(R.id.showImage2Btn);
		
		addMenusBtn = (Button) findViewById(R.id.addMenusBtn);
		showContextMenuBtn = (Button) findViewById(R.id.showContextMenuBtn);
		registerForContextMenu(showContextMenuBtn);
		
		welcomeTxt = (TextView) findViewById(R.id.welcomeTxt);
		welcomeTxt.setText(String.format(getResources().getString(R.string.welcome_msg), "Wgssmart", 
				20, getResources().getInteger(R.integer.msgs)) + " Adjust: " + getResources().getBoolean(R.bool.adjust));
		
		changeBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				changeTxt.setText(R.string.change_txt);
				// changeTxt.setText(getResources().getString(R.string.change_txt));
				changeTxt.setTextColor(getResources().getColor(R.color.red));
			}
			
		});
		
		showCoursesBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Resources res = getResources();
				String[] courses = res.getStringArray(R.array.courses);
				int[] scores = res.getIntArray(R.array.scores);
				String course = "";
				String score = "";
				for (String c : courses) {
					course += c + "  ";
				}
				for (int s : scores) {
					score += s + "  ";
				}
				coursesTxt.setText(course);
				scoreTxt.setText(score);
			}
			
		});
		
		songsBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Resources res = getResources();
				String songs = songsEdit.getText().toString();
				if (songs == null || songs.length() == 0) {
					songsTxt.setText(res.getQuantityString(R.plurals.songs, 0, 0));
				} else {
					int totalSongs = Integer.valueOf(songs);
					songsTxt.setText(res.getQuantityString(R.plurals.songs, totalSongs, totalSongs));
				}
			}
			
		});
		
		ShowImageBtnClick showImageBtnClick = new ShowImageBtnClick();
		showImage1Btn.setOnClickListener(showImageBtnClick);
		showImage2Btn.setOnClickListener(showImageBtnClick);
		
		addMenusBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				menu.add(1, 1, 1, "新加菜单1");
				SubMenu optFileMenu = menu.addSubMenu(1, 2, 2, R.string.optFileMenu);
				optFileMenu.add(1, 3, 1, "选项一");
				optFileMenu.add(1, 4, 2, "选项二");
				MenuItem opt3 = optFileMenu.add(2, 5, 2, "选项三");
				opt3.setIntent(new Intent(MainActivity.this, ImageActivity.class));
			}
			
		});
		
	}

	private class ShowImageBtnClick implements OnClickListener {

		@Override
		public void onClick(View v) {
			int id = v.getId();
			switch(id) {
			case R.id.showImage1Btn:
				showImage1();
				break;
			case R.id.showImage2Btn:
				showImage2();
				break;
				default:break;
			}
		}
		
		private void showImage1() {
			Intent intent = new Intent(MainActivity.this, ImageActivity.class);
			intent.putExtra("image", "book");
			startActivity(intent);
		}
		
		private void showImage2() {
			Intent intent = new Intent(MainActivity.this, ImageActivity.class);
			intent.putExtra("image", "camera");
			startActivity(intent);
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		this.menu = menu;
		return true;
	}
	
	
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.main, menu);
	}



	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		} else if (id == R.id.newFileMenu) {
			Toast.makeText(getApplicationContext(), "新建菜单", Toast.LENGTH_SHORT).show();
		} else if (id == R.id.openFileMenu) {
			Toast.makeText(getApplicationContext(), "打开菜单", Toast.LENGTH_SHORT).show();
		} else if (id == R.id.exitMenu) {
			Toast.makeText(getApplicationContext(), "退出菜单", Toast.LENGTH_SHORT).show();
		} else if (id == 1) {
			Toast.makeText(getApplicationContext(), "新加菜单1", Toast.LENGTH_SHORT).show();
		}
		Log.d("resource", "menu item id: " + id);
		return super.onOptionsItemSelected(item);
	}
}
