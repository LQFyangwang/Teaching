package com.ht.webbrowser;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

	private WebView webView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		webView = (WebView) findViewById(R.id.webView);
		
		Uri uri = getIntent().getData();
		if (uri != null) {
			Log.d("WebBrowser", "==== " + uri.toString());
			webView.loadUrl(uri.toString());
			setTitle(uri.toString());
		} else {
			webView.loadUrl("http://gzyouxue.com");
			setTitle("优学网");
		}
		
		/**
		 * 在当前的webview中打开网页
		 */
		webView.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});
		
	}
}
