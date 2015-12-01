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


public class MainActivity extends ActionBarActivity {
	
	private TextView userNameTxt;
	private TextView toLogin;
	private TextView toReg;
	private TextView helpTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        toLogin = (TextView) findViewById(R.id.mainToLoginTxt);
        toReg = (TextView) findViewById(R.id.mainToRegTxt);
        helpTxt = (TextView) findViewById(R.id.mainHelpTxt);
        userNameTxt = (TextView) findViewById(R.id.mainLoginedUserNameTxt);
        
        /**
         * 获取由其他activity返回的intent对象
         */
        Intent intent = getIntent();
        if (intent != null) {
        	Log.d("Main Activity Intent", "==== intent is not null");
	        String userName = intent.getStringExtra("userName");
	        if (userName != null) {
	        	userNameTxt.setText(userName);
	        }
        }
        
        toLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, LoginActivity.class);
				startActivityForResult(intent, Constants.MAIN_TO_LOGIN_REQUEST_CODE);
			}
        	
        });
        
        
        toReg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
				startActivity(intent);
			}
        	
        });
        
        helpTxt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, HelpActivity.class);
				startActivity(intent);
			}
        	
        });
        
        Log.d("onCreate", "==== onCreate");
    }
    
    
    
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if (requestCode == Constants.MAIN_TO_LOGIN_REQUEST_CODE) {
			if (resultCode == Constants.MAIN_TO_LOGIN_RESULT_CODE) {
				String userName = data.getStringExtra("userName");
				userNameTxt.setText(userName);
			}
		}
		
	}



	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d("main activity", "==== onDestroy");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.d("main activity", "==== onStop");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.d("main activity", "==== onPause");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.d("main activity", "==== onResume");
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.d("main activity", "==== onStart");
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.d("main activity", "==== onRestart");
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
