package com.ht.activity;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends ActionBarActivity {
	
	private EditText userNameEdit;
	private EditText userPwdEdit;
	private Button regBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		userNameEdit = (EditText) findViewById(R.id.regUserNameEdit);
		userPwdEdit = (EditText) findViewById(R.id.regUserPwdEdit);
		regBtn = (Button) findViewById(R.id.regBtn);
		
		regBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String userName = userNameEdit.getText().toString();
				String userPwd = userPwdEdit.getText().toString();
				if (userName != null && userName.length() > 0 && userPwd != null && userPwd.length() > 0) {
					Toast.makeText(getApplicationContext(), "注册成功，直接转到个人中心", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
					intent.putExtra("userName", userName);
					intent.putExtra("reg", "successful");
					startActivity(intent);
					finish();
				} else {
					Toast.makeText(getApplicationContext(), "用户名或密码输入有误，请重新输入", Toast.LENGTH_SHORT).show();
				}
			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
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
