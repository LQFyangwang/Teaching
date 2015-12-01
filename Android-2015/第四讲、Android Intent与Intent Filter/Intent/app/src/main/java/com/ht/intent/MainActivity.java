package com.ht.intent;

import android.support.v7.app.ActionBarActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {
	
	private Button loginBtn;
	private Button loginBtn1;
	private Button loginBtn2;
	
	private Button useActionBtn;
	private Button useSetClassNameBtn;
	private Button useSetClassNameContextBtn;
	private Button useSetClassBtn;
	private Button useSetComponentBtn;
	private Button useCalculatorBtn;
	
	private Context targetContext;
	private Class targetClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn1 = (Button) findViewById(R.id.loginBtn1);
        loginBtn2 = (Button) findViewById(R.id.loginBtn2);
        
        useActionBtn = (Button) findViewById(R.id.useActionBtn);
        useSetClassNameBtn = (Button) findViewById(R.id.useSetClassNameBtn);
        useSetClassNameContextBtn = (Button) findViewById(R.id.useSetClassNameContextBtn);
        useSetClassBtn = (Button) findViewById(R.id.useSetClassBtn);
        useSetComponentBtn = (Button) findViewById(R.id.useSetComponentBtn);
        useCalculatorBtn = (Button) findViewById(R.id.useCalculatorBtn);
        
        /**
         * 获取指定包的activity
         */
        try {
			targetContext = createPackageContext("com.ht.webbrowser", 
					Context.CONTEXT_INCLUDE_CODE | Context.CONTEXT_IGNORE_SECURITY);
			targetClass = targetContext.getClassLoader()
					.loadClass("com.ht.webbrowser.MainActivity");
        } catch (NameNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        
        loginBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setAction("com.ht.activity.LOGIN_ACTION");
				// ComponentName cn = new ComponentName(MainActivity.this, LoginActivity.class);
				// intent.setComponent(cn);
				startActivity(intent);
				
			}
        	
        });
        
        loginBtn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setAction("com.ht.activity.LOGIN_ACTION");
				intent.addCategory("com.ht.activity.LOGIN_CATEGORY");
				startActivity(intent);
			}
        	
        });
        
        loginBtn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setAction("com.ht.activity.LOGIN_ACTION");
				intent.addCategory("com.ht.activity.LOGIN_CATEGORY");
				intent.setData(Uri.parse("x-ht:test"));
				startActivity(intent);
			}
        	
        });
        
        WebViewBtnClick webViewBtnClick = new WebViewBtnClick();
        useActionBtn.setOnClickListener(webViewBtnClick);
        useSetClassNameBtn.setOnClickListener(webViewBtnClick);
        useSetClassNameContextBtn.setOnClickListener(webViewBtnClick);
        useSetClassBtn.setOnClickListener(webViewBtnClick);
        useSetComponentBtn.setOnClickListener(webViewBtnClick);
        
        /**
         * 打开系统计算器
         */
        useCalculatorBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClassName("com.android.calculator2", "com.android.calculator2.Calculator");
				startActivity(intent);
			}
        	
        });
        
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
    
    private class WebViewBtnClick implements OnClickListener {

		@Override
		public void onClick(View v) {
			int id = v.getId();
			switch (id) {
			case R.id.useActionBtn:
				useAction();
				break;
			case R.id.useSetClassBtn:
				useSetClass();
				break;
			case R.id.useSetClassNameBtn:
				useSetClassName();
				break;
			case R.id.useSetClassNameContextBtn:
				useSetClassNameContext();
				break;
			case R.id.useSetComponentBtn:
				useSetComponent();
				break;
				default:break;
			}
		}

		private void useAction() {
			Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://baidu.com"));
			startActivity(intent);
			Log.d("intent use", "use action");
		}
		
		private void useSetClass() {
			if (targetContext == null || targetClass == null) {
				return;
			}
			Intent intent = new Intent();
			intent.setClass(targetContext, targetClass);
			intent.setData(Uri.parse("http://baidu.com"));
			startActivity(intent);
			Log.d("intent use", "use setClass");
		}
    	
		private void useSetClassName() {
			Intent intent = new Intent();
			intent.setClassName("com.ht.webbrowser", "com.ht.webbrowser.MainActivity");
			intent.setData(Uri.parse("http://baidu.com"));
			startActivity(intent);
			Log.d("intent use", "use setClassName");
		}

		private void useSetClassNameContext() {
			if (targetContext == null) {
				return;
			}
			Intent intent = new Intent();
			intent.setClassName(targetContext, "com.ht.webbrowser.MainActivity");
			intent.setData(Uri.parse("http://baidu.com"));
			startActivity(intent);
			Log.d("intent use", "use setClassName Context");
		}
		
		private void useSetComponent() {
			if (targetContext == null || targetClass == null) {
				return;
			}
			Intent intent = new Intent();
			ComponentName component = new ComponentName(targetContext, targetClass);
			intent.setComponent(component);
			intent.setData(Uri.parse("http://baidu.com"));
			startActivity(intent);
			Log.d("intent use", "use setComponent");
		}
    }
}
