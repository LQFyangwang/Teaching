package com.ht.data;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.JsonReader;
import android.util.JsonWriter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ht.modal.DBHelper;

public class MainActivity extends ActionBarActivity {

	private EditText userNameEdit;
	private EditText userPwdEdit;
	private Button saveUserBtn;
	private Button showUserBtn;
	
	private Button saveToSD;
	private Button showFromSD;
	
	private Button sqlBtn;
	private Button addBtn;
	private Button delBtn;
	private Button updateBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initWidgets();
		
		SaveUserDataBtnClick saveBtnClick = new SaveUserDataBtnClick();
		saveUserBtn.setOnClickListener(saveBtnClick);
		ShowUserDataBtnClick showBtnClick = new ShowUserDataBtnClick();
		showUserBtn.setOnClickListener(showBtnClick);
		
		saveToSD.setOnClickListener(saveBtnClick);
		showFromSD.setOnClickListener(showBtnClick);
		
		sqlBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, UserListActivity.class));
			}
			
		});
		
		addBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DBHelper dbHelper = new DBHelper(MainActivity.this);
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				ContentValues cv = new ContentValues();
				cv.put("name", "小张");
				cv.put("password", "1234567");
				db.insert("t_user", null, cv);
				String sql = "insert into t_user (name, password) values(?, ?)";
				db.execSQL(sql, new Object[]{"小李", "12345678"});
				db.close();
			}
			
		});
		
		delBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DBHelper dbHelper = new DBHelper(MainActivity.this);
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				db.delete("t_user", "password = ?", new String[]{"1234567"});
				db.close();
			}
			
		});
		
		updateBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DBHelper dbHelper = new DBHelper(MainActivity.this);
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				ContentValues cv = new ContentValues();
				cv.put("password", "1234567");
				db.update("t_user", cv, "password = ?", new String[]{"12345678"});
				db.close();
			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
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
	
	private void initWidgets() {
		userNameEdit = (EditText) findViewById(R.id.userNameEdit);
		userPwdEdit = (EditText) findViewById(R.id.userPwdEdit);
		saveUserBtn = (Button) findViewById(R.id.saveUserBtn);
		showUserBtn = (Button) findViewById(R.id.showUserBtn);
		saveToSD = (Button) findViewById(R.id.saveToSD);
		showFromSD = (Button) findViewById(R.id.showFromSD);
		sqlBtn = (Button) findViewById(R.id.sqlBtn);
		addBtn = (Button) findViewById(R.id.addBtn);
		delBtn = (Button) findViewById(R.id.delBtn);
		updateBtn = (Button) findViewById(R.id.updateBtn);
	}
	
	private class SaveUserDataBtnClick implements OnClickListener {
		@Override
		public void onClick(View v) {
			int id = v.getId();
			if (id == R.id.saveToSD) {
				saveToSD();
			} else {
				saveToSharedPreferences();
				saveToInternalStorage();
				saveToJSONFile();
			}
			
		}
		
		private void saveToSharedPreferences() {
			SharedPreferences sp = getSharedPreferences("user_data", Activity.MODE_PRIVATE);
			SharedPreferences.Editor editor = sp.edit();
			editor.putString("user_name", userNameEdit.getText().toString());
			editor.putString("user_pwd", userPwdEdit.getText().toString());
			editor.commit();
			Toast.makeText(MainActivity.this, "已经保存用户数据", Toast.LENGTH_LONG).show();
		}
		
		private void saveToInternalStorage() {
			try {
				OutputStream os = openFileOutput("user_data.txt", Activity.MODE_PRIVATE);
				BufferedWriter bufWriter = new BufferedWriter(new OutputStreamWriter(os, "utf-8"));
				bufWriter.write("user_name:" + userNameEdit.getText().toString());
				//bufWriter.write(System.getProperty("line.separator"));
				bufWriter.write("\r\n");
				bufWriter.write("user_pwd:" + userPwdEdit.getText().toString());
				bufWriter.close();
				os.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		private void saveToSD() {
			try {
				String sdDir = android.os.Environment.getExternalStorageDirectory() + "/images";
				BufferedInputStream bufIs = new BufferedInputStream(
						getResources().getAssets().open("images/bmw.png"));
				byte[] buffer = new byte[10240];
				File file = new File(sdDir);
				if (!file.exists()) {
					file.mkdir();
				}
				BufferedOutputStream bufOs = new BufferedOutputStream(
						new FileOutputStream(sdDir + "/bmw.png"));
				int count = 0;
				while((count = bufIs.read(buffer)) >= 0) {
					bufOs.write(buffer, 0, count);
				}
				bufIs.close();
				bufOs.close();
				Toast.makeText(MainActivity.this, "图像保存到SD卡", Toast.LENGTH_LONG).show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		private void saveToSQLite() {
			
		}
		
		private void saveToJSONFile() {
			try {
				JsonWriter jw = new JsonWriter(new OutputStreamWriter(new BufferedOutputStream(
						openFileOutput("user_data.json", Activity.MODE_PRIVATE)), "utf-8"));
				List<User> users = new ArrayList<User>();
				List<String> books = new ArrayList<String>();
				books.add("book1");
				books.add("book2");
				users.add(new User("小张", "123456", books));
				users.add(new User("小李", "12345678", books));
				jw.setIndent("    ");
				jw.beginArray();
				for (User user : users) {
					jw.beginObject();
					jw.name("name").value(user.getName());
					jw.name("pwd").value(user.getPwd());
					jw.name("books");
					jw.beginArray();
					for (String book : books) {
						jw.beginObject();
						jw.name("name").value(book);
						jw.endObject();
					}
					jw.endArray();
					jw.endObject();
				}
				jw.endArray();
				jw.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private class ShowUserDataBtnClick implements OnClickListener {
		@Override
		public void onClick(View v) {
			int id = v.getId();
			if (id == R.id.showFromSD) {
				getFromSD();
			} else {
				String spString = getFromSharedPreferences();
				String isString = getFromInternalStorage();
				String userString = getFromJSONFile();
				Toast.makeText(MainActivity.this, spString + "\n" + isString 
						+ "\n" + userString, Toast.LENGTH_LONG).show();
			}
		}
		
		private String getFromSharedPreferences() {
			SharedPreferences sp = getSharedPreferences("user_data", Activity.MODE_PRIVATE);
			String userName = sp.getString("user_name", "");
			String userPwd = sp.getString("user_pwd", "");
			return "user_name:" + userName + ", user_pwd:" + userPwd;
		}
		
		private String getFromInternalStorage() {
			try {
				InputStream is = openFileInput("user_data.txt");
				BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
				String str = null;
				String retStr = "";
				while ((str = br.readLine()) != null) {
					retStr += str + " ";
				}
				return retStr;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "";
		}
		
		private void getFromSD() {
			try {
				File file = new File(android.os.Environment.getExternalStorageDirectory() + "/images/bmw.png");
				if (!file.exists()) {
					return;
				}
				BufferedInputStream bufIs = new BufferedInputStream(new FileInputStream(file));
				Bitmap bitmap = BitmapFactory.decodeStream(bufIs);
				ImageView logo = (ImageView) findViewById(R.id.logo);
				logo.setImageBitmap(bitmap);
				bufIs.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		private String getFromSQLite() {
			return "";
		}
		
		private String getFromJSONFile() {
			List<User> users = new ArrayList<User>();
			try {
				JsonReader jr = new JsonReader(new BufferedReader(new InputStreamReader(
						new BufferedInputStream(openFileInput("user_data.json")), "utf-8")));
				jr.beginArray();
				while(jr.hasNext()) {
					jr.beginObject();
					User user = new User();
					while(jr.hasNext()) {
						String name = jr.nextName();
						if (name.equals("name")) {
							user.setName(jr.nextString());
						} else if (name.equals("pwd")) {
							user.setPwd(jr.nextString());
						} else if (name.equals("books")) {
							List<String> books = new ArrayList<String>();
							jr.beginArray();
							while(jr.hasNext()) {
								jr.beginObject();
								while(jr.hasNext()) {
									String nameInBooks = jr.nextName();
									if (nameInBooks.equals("name")) {
										books.add(jr.nextString());
									} else {
										jr.skipValue();
									}
								}
								jr.endObject();
							}
							user.setBooks(books);
							jr.endArray();
						} else {
							jr.skipValue();
						}
					}
					users.add(user);
					jr.endObject();
				}
				jr.endArray();
				jr.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return users.get(0).toString();
		}
	}
	
}

class User {
	private String name;
	private String pwd;
	private List<String> books;
	
	public User() {
		
	}
	
	public User(String name, String pwd, List<String> books) {
		this.name = name;
		this.pwd = pwd;
		this.books = books;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public void setBooks(List<String> books) {
		this.books = books;
	}
	public List<String> getBooks() {
		return books;
	}
	
	@Override 
	public String toString() {
		String books = "";
		for (String book : this.books) {
			books += book + " ";
		}
		return "user_name:" + name + ", user_pwd:" + pwd + ", books:" + books;
	}
	
}
