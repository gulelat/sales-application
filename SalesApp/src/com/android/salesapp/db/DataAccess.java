package com.android.salesapp.db;



import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.salesapp.utils.Const;
import com.android.salesapp.utils.Log;





public class DataAccess extends SQLiteOpenHelper {

	public DataAccess() {
		super(Const.CONTEXT, Const.DB_NAME, null, 33);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public void execute(String Statment)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		
		try {
			db.execSQL(Statment);
		} 
		catch (Exception e) {
			Log.error("DataBaseHelper - ExecuteQuery", e);
		} finally {
			db.close();
			db = null;
		}
	}
	
	public Cursor query(String Statment)
	{
		Cursor cur = null;
		SQLiteDatabase db = this.getWritableDatabase();
		
		try {
			System.out.println("STARTEMENT" + Statment);
			cur = db.rawQuery(Statment, null);
			cur.moveToPosition(-1);
		} 
		catch (Exception e) {
			Log.error("DataBaseHelper - ExecuteCursor", e);
		} finally {
			
			db.close();
			db = null;
		}
		
		return cur;
	}
	
	
	
}
