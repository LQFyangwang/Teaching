package com.ht.activity;

import com.ht.common.Constants;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends ActionBarActivity {
	
	private Button loginBtn;
	private EditText userNameEdit;
	private EditText userPwdEdit;
	private TextView toReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        loginBtn = (Button) findViewById(R.id.loginBtn);
        userNameEdit = (EditText) findViewById(R.id.loginUserNameEdit);
        userPwdEdit = (EditText) findViewById(R.id.loginUserPwdEdit);
        toReg = (TextView) findViewById(R.id.loginRegBtn);
        
        loginBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String userName = userNameEdit.getText().toString();
				String userPwd = userPwdEdit.getText().toString();
				if (userName != null && userName.length() > 0 && userPwd != null && userPwd.length() > 0) {
					Toast.makeText(getApplicationContext(), "用户名：" + userName + ", 密码：" + userPwd, Toast.LENGTH_LONG).show();
					Intent intent = new Intent();
					intent.putExtra("userName", userName);
					setResult(Constants.MAIN_TO_LOGIN_RESULT_CODE, intent);
					finish();
				} else {
					Toast.makeText(getApplicationContext(), "用户名或密码不能为空，请重新输入", Toast.LENGTH_SHORT).show();
				}
			}
        });
        
        toReg.setOnClickListener(new RegClickListener());
        
        Log.d("login activity", "==== onCreate");
    }
    
    private class RegClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
			startActivity(intent);
		}
    	
    }

    @Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d("login activity", "==== onDestroy");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.d("login activity", "==== onStop");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.d("login activity", "==== onPause");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.d("login activity", "==== onResume");
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.d("login activity", "==== onStart");
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.d("login activity", "==== onRestart");
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
}
