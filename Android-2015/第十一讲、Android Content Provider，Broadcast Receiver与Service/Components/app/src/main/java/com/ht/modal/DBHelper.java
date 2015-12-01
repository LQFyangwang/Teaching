package com.ht.modal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "test.db";
	private static final int DATABASE_VERSION = 1;
	
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table t_user ("
				+ "id integer primary key autoincrement,"
				+ "name varchar(30) not null,"
				+ "password varchar(30) not null"
				+ ")";
		db.execSQL(sql);
		db.execSQL("insert into t_user(name, password) values(?, ?)", new Object[]{"Wgssmart", "123456"}); 
		db.execSQL("insert into t_user(name, password) values(?, ?)", new Object[]{"小李", "12345678"}); 
		ContentValues cv = new ContentValues();
		cv.put("name", "小张");
		cv.put("password", "1234567");
		db.insert("t_user", null, cv);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
	
}
