package com.example.malchin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper_amid_mal extends SQLiteOpenHelper {
	
    static String DATABASE_NAME="Malchin";

    public static final String KEY_id="id";
    
    public static final String KEY_Malsureg="malturul";
    
    public static final String KEY_Maltoo="tom_count";
    
    public static final String KEY_niit_jin="jin";
    
    public static final String KEY_orlogo="orlogo";
    
    public static final String KEY_Date="date";
    
    public SQLiteHelper_amid_mal(Context context) {
    	
        super(context, DATABASE_NAME, null, 1);
        
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
    	
        String CREATE_TABLE="CREATE TABLE "+amid_mal_bor.TABLE_NAME+" ("+KEY_id+" INTEGER PRIMARY KEY,"+KEY_Malsureg+" VARCHAR "+KEY_Maltoo+" VARCHAR, "+KEY_niit_jin+" VARCHAR, "+KEY_orlogo+" VARCHAR, "+KEY_Date+" VARCHAR)";
        database.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+amid_mal_bor.TABLE_NAME);
        onCreate(db);

    }

}