package com.ht.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.ht.modal.DBHelper;

public class UserListActivity extends ActionBarActivity {
	
	private ListView userList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_list);
		
		userList = (ListView) findViewById(R.id.userList);
		DBHelper dbHelper = new DBHelper(this);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select id as _id, name, password from t_user", null);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.user_list_item, cursor, 
				new String[]{"_id", "name", "password"}, new int[]{R.id.userId, R.id.userName, R.id.userPwd}, 0);
		userList.setAdapter(adapter);
		db.close();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.user_list, menu);
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
