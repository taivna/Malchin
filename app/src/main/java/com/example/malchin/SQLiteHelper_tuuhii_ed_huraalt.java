package com.example.malchin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper_tuuhii_ed_huraalt extends SQLiteOpenHelper {
	
    static String DATABASE_NAME="Malchin";

    public static final String KEY_id="id";
    
    public static final String KEY_Malsureg="malturul";
    
    public static final String KEY_niit_hemjee="hemjee";
    
    public static final String KEY_Maltoo="count";
    
    public static final String KEY_Date="date";
    
    public SQLiteHelper_tuuhii_ed_huraalt(Context context) {
    	
        super(context, DATABASE_NAME, null, 1);
        
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
    	
        String CREATE_TABLE="CREATE TABLE "+tuuhii_ed_huraalt.TABLE_NAME+" ("+KEY_id+" INTEGER PRIMARY KEY,"+KEY_Malsureg+" VARCHAR "+KEY_niit_hemjee+" VARCHAR "+KEY_Maltoo+" VARCHAR,"+KEY_Date+" VARCHAR)";
        database.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+tuuhii_ed_huraalt.TABLE_NAME);
        onCreate(db);

    }

}