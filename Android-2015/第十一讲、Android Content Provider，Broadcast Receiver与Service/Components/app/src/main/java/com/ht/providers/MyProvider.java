package com.ht.providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.ht.modal.DBHelper;

public class MyProvider extends ContentProvider {

	private static final String AUTHORITY = "com.ht.providers.myprovider";
	private static UriMatcher uriMatcher;
	private static final int ALL = 1;
	private static final int ID = 2;
	private static final int NAME = 3;
	private DBHelper dbHelper;
	
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(AUTHORITY, "all", ALL);
		uriMatcher.addURI(AUTHORITY, "id/#", ID);
		uriMatcher.addURI(AUTHORITY, "name/*", NAME);
	}
	
	@Override
	public boolean onCreate() {
		Log.d("MyProvider", "创建 ContentProvider");
		dbHelper = new DBHelper(getContext());
		return true;
	}
	
	
	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		Cursor cursor = null;
		SQLiteDatabase db = null;
		switch(uriMatcher.match(uri)) {
		case ALL:
			db = dbHelper.getReadableDatabase();
			cursor = db.query("t_user", projection, selection, selectionArgs, null, null, sortOrder);
			break;
		case ID:
			db = dbHelper.getReadableDatabase();
			int id = Integer.valueOf(uri.getPathSegments().get(1));
			if (selection == null) {
				selection = "id = " + id;
			} else {
				selection += " and id = " + id;
			}
			cursor = db.query("t_user", projection, selection, selectionArgs, null, null, sortOrder);
			break;
		case NAME:
			db = dbHelper.getReadableDatabase();
			String name = uri.getPathSegments().get(1);
			if (selection == null) {
				selection = "name = '" + name + "'";
			} else {
				selection += " and name = '" + name + "'";
			}
			cursor = db.query("t_user", projection, selection, selectionArgs, null, null, sortOrder);
			break;
			default:throw new IllegalArgumentException(uri + "格式不正确");
		}
		return cursor;
	}
	
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
