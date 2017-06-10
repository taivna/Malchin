package com.example.malchin;

import java.io.File;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

public class SQLiteHelper extends SQLiteOpenHelper {

	static String DATABASE_NAME = "Malchin";

	public static final String KEY_id = "id";

	public static final String KEY_Malsureg = "malturul";

	public static final String TABLE_NAME = "malchintable";

	public static final String KEY_Maltoo = "tom_count";

	public static final String KEY_Tulmal = "tul_count";

	public static final String KEY_Date = "date";
	public static String data_sdcard = "/sdcard";

	public SQLiteHelper(Context context) {

		super(context, Environment.getExternalStorageDirectory()
				+ File.separator + data_sdcard + DATABASE_NAME, null, 1);
		SQLiteDatabase.openOrCreateDatabase(
				Environment.getExternalStorageDirectory() + File.separator
						+ data_sdcard + DATABASE_NAME, null);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {

		String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + KEY_id
				+ " INTEGER PRIMARY KEY," + KEY_Malsureg + " VARCHAR "
				+ KEY_Maltoo + " VARCHAR, " + KEY_Tulmal + " VARCHAR, "
				+ KEY_Date + " VARCHAR)";
		database.execSQL(CREATE_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);

	}

}