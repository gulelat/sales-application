package com.android.salesapp.utils;

import android.content.Context;
import android.os.Environment;

public class Const {

	public static Context CONTEXT;

	public static String DB_NAME = "SalesApp_DB";
	public static final String PREF_FILE = "SalesApp_PREF";

	public static String APP_HOME = Environment.getExternalStorageDirectory()
			.getPath() + "/SalesApp";

	public static String LOG_DIR = APP_HOME + "/Log";
	public static String LOG_ZIP = APP_HOME + "/SalesAppLog.zip";

	public static String SOURCE_DB = "/data/data/com.esp.smsreminder/databases/SalesApp_DB";

	// Activity Report Type
	public static int EMAIL_SEND = 1;
	public static int SMS_SEND = 2;
	
	// Reminder List
	public static String ReminderName[] = {"After 5 Minit", "After 10 Minit"};
	public static long ReminderValue[] = {300000, 600000};
}