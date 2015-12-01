package com.ht.network;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.support.v7.app.ActionBarActivity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends ActionBarActivity {
	
	private Button httpGetBtn;
	private Button httpPostBtn;
	private Button httpUrlConnectionBtn;
	private String[] companyEN;
	private String[] companyZH;
	
	private Spinner companySpinner;
	private EditText orderIdEdit;
	
	private Button downBtn;
	private Button cancelDownBtn;
	private Button downHistoryBtn;
	private DownloadManager downMgr;
	private long downRefId = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		httpGetBtn = (Button) findViewById(R.id.httpGetBtn);
		httpPostBtn = (Button) findViewById(R.id.httpPostBtn);
		httpUrlConnectionBtn = (Button) findViewById(R.id.httpUrlConnection);
		companySpinner = (Spinner) findViewById(R.id.companys);
		orderIdEdit = (EditText) findViewById(R.id.orderIdEdit);
		
		downBtn = (Button) findViewById(R.id.downBtn);
		cancelDownBtn = (Button) findViewById(R.id.cancelDownBtn);
		downHistoryBtn = (Button) findViewById(R.id.downHistoryBtn);
		
		Resources res = getResources();
		companyEN = res.getStringArray(R.array.company_en);
		companyZH = res.getStringArray(R.array.company_zh);
		
		setCompanyAdapter();
		
		HttpBtnClick httpBtnClick = new HttpBtnClick();
		httpGetBtn.setOnClickListener(httpBtnClick);
		httpPostBtn.setOnClickListener(httpBtnClick);
		httpUrlConnectionBtn.setOnClickListener(httpBtnClick);
		
		downMgr = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
		registerReceiver(new DownloadReceiver(), new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
		downBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Uri uri = Uri.parse("http://car1.autoimg.cn/upload/2014/7/31/u_20140731132211363011.jpg");
				DownloadManager.Request request = new DownloadManager.Request(uri);
				request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
				request.setTitle("下载图片");
				request.setDescription("使用DownloadManager下载图片");
				request.setDestinationInExternalFilesDir(MainActivity.this, "image", "bmw.jpg");
				downRefId = downMgr.enqueue(request);
				setTitle("正在下载图片");
			}
			
		});
		
		cancelDownBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (downRefId > 0) {
					downMgr.remove(downRefId);
				}
			}
			
		});
		
		downHistoryBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS));
			}
			
		});
		
	}
	
	private void setCompanyAdapter() {
		ArrayAdapter<String> aryAdapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_dropdown_item_1line, companyZH);
		companySpinner.setAdapter(aryAdapter);
	}
	
	private class HttpBtnClick implements OnClickListener {

		@Override
		public void onClick(View v) {
			int id = v.getId();
			switch (id) {
			case R.id.httpGetBtn:
				new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							httpGet();
						} catch (ClientProtocolException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				}).start();
				break;
			case R.id.httpPostBtn:
				new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							httpPost();
						} catch (ClientProtocolException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
				}).start();
				break;
			case R.id.httpUrlConnection:
				new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							httpConnection();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
				}).start();
				break;
			default:
				break;
			}
		}
	}
	
	private String expressUrl = "http://www.kuaidi100.com/query";
	//?type=zhongtong&postid=365025795153
	
	@SuppressWarnings("deprecation")
	private void httpGet() throws ClientProtocolException, IOException {
		String company = companyEN[companySpinner.getSelectedItemPosition()];
		String orderId = orderIdEdit.getText().toString();
		String url = expressUrl + "?type=" + company + "&postid=" + orderId;
		Log.d("http", "url: " + url);
		HttpGet httpGet = new HttpGet(url);
		HttpResponse response = new DefaultHttpClient().execute(httpGet);
		if (response.getStatusLine().getStatusCode() == 200) {
			String json = EntityUtils.toString(response.getEntity());
			Log.d("http", "get json string: " + json.replaceAll("\r", ""));
			// 处理json数据并使用handler发送消息给主线程进行UI更新
		}
	}
	
	@SuppressWarnings("deprecation")
	private void httpPost() throws ClientProtocolException, IOException {
		String company = companyEN[companySpinner.getSelectedItemPosition()];
		String orderId = orderIdEdit.getText().toString();
		HttpPost httpPost = new HttpPost(expressUrl);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("type", company));
		params.add(new BasicNameValuePair("postid", orderId));
		httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
		HttpResponse response = new DefaultHttpClient().execute(httpPost);
		if (response.getStatusLine().getStatusCode() == 200) {
			String json = EntityUtils.toString(response.getEntity());
			Log.d("http", "post json string: " + json.replaceAll("\r", ""));
			// 处理json数据并使用handler发送消息给主线程进行UI更新
		}
	}
	
	private void httpConnection() throws IOException {
		StringBuffer params = new StringBuffer();
		String company = companyEN[companySpinner.getSelectedItemPosition()];
		String orderId = orderIdEdit.getText().toString();
		params.append("type=").append(company).append("&postid=").append(orderId);
		URL url = new URL(expressUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Connection", "Keep-Alive");
		conn.setRequestProperty("Charset", "UTF-8");
		conn.setRequestProperty("Content-Length", params.toString().length() + "");
		PrintWriter writer = new PrintWriter(conn.getOutputStream());
		writer.write(params.toString());
		writer.flush();
		int statusCode = conn.getResponseCode();
		if (statusCode == 200) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new BufferedInputStream(conn.getInputStream()), "UTF-8"));
			String line = null;
			String json = "";
			while ((line = reader.readLine()) != null) {
				json += line;
			}
			Log.d("http", "http url connection json string: " + json.replaceAll("\r", ""));
			reader.close();
		}
		writer.close();
	}
	
	private class DownloadReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
				long refId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);
				if (downRefId == refId) {
					setTitle("图片下载完成");
					downRefId = -1;
				}
			}
		}
		
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
