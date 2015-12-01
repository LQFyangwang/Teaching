package com.ht.layout;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {
	
	private Button linearBtn;
	private Button relativeBtn;
	private Button frameBtn;
	private Button tableBtn;
	private Button absoluteBtn;
	private Button gridBtn;
	private Button dynamicBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		linearBtn = (Button) findViewById(R.id.linearBtn);
		relativeBtn = (Button) findViewById(R.id.relativeBtn);
		frameBtn = (Button) findViewById(R.id.frameBtn);
		tableBtn = (Button) findViewById(R.id.tableBtn);
		absoluteBtn = (Button) findViewById(R.id.absoluteBtn);
		gridBtn = (Button) findViewById(R.id.gridBtn);
		dynamicBtn = (Button) findViewById(R.id.dynamicBtn);
		
		LayoutBtnClick layoutBtnClick = new LayoutBtnClick();
		linearBtn.setOnClickListener(layoutBtnClick);
		relativeBtn.setOnClickListener(layoutBtnClick);
		frameBtn.setOnClickListener(layoutBtnClick);
		tableBtn.setOnClickListener(layoutBtnClick);
		absoluteBtn.setOnClickListener(layoutBtnClick);
		gridBtn.setOnClickListener(layoutBtnClick);
		dynamicBtn.setOnClickListener(layoutBtnClick);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
	
	private class LayoutBtnClick implements OnClickListener {

		@Override
		public void onClick(View v) {
			int btnId = v.getId();
			switch(btnId) {
			case R.id.linearBtn:
				clickLinearBtn();
				break;
			case R.id.relativeBtn:
				clickRelativeBtn();
				break;
			case R.id.frameBtn:
				clickFrameBtn();
				break;
			case R.id.tableBtn:
				clickTableBtn();
				break;
			case R.id.absoluteBtn:
				clickAbsoluteBtn();
				break;
			case R.id.gridBtn:
				clickGridBtn();
				break;
			case R.id.dynamicBtn:
				clickDynamicBtn();
				break;
				default:break;
			}
		}
		
		private void clickLinearBtn() {
			Intent intent = new Intent(MainActivity.this, LinearActivity.class);
			startActivity(intent);
		}
		
		private void clickRelativeBtn() {
			Intent intent = new Intent(MainActivity.this, RelativeActivity.class);
			startActivity(intent);
		}
		
		private void clickFrameBtn() {
			Intent intent = new Intent(MainActivity.this, FrameActivity.class);
			startActivity(intent);
		}
		
		private void clickTableBtn() {
			Intent intent = new Intent(MainActivity.this, TableActivity.class);
			startActivity(intent);
		}
		
		private void clickAbsoluteBtn() {
			Intent intent = new Intent(MainActivity.this, AbsoluteActivity.class);
			startActivity(intent);
		}
		
		private void clickGridBtn() {
			Intent intent = new Intent(MainActivity.this, GridActivity.class);
			startActivity(intent);
		}
		
		private void clickDynamicBtn() {
			Intent intent = new Intent(MainActivity.this, DynamicActivity.class);
			startActivity(intent);
		}
	}
}
