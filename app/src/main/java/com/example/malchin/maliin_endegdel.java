package com.example.malchin;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class maliin_endegdel extends Activity {

	SQLiteDatabase SQLITEDATABASE;
	Cursor cursor;
	SQLiteListAdapter_endegdel ListAdapter;
	ImageView iv_add;
	String GetSQliteQuery;
	ArrayList<String> ID_ArrayList = new ArrayList<String>();
	ArrayList<String> MALSUREG_ArrayList = new ArrayList<String>();
	ArrayList<String> TOMMAL_ArrayList = new ArrayList<String>();
	ArrayList<String> TULMAL_ArrayList = new ArrayList<String>();
	ArrayList<String> SHALTGAAN_ArrayList = new ArrayList<String>();
	ArrayList<String> DATE_ArrayList = new ArrayList<String>();
	ListView LISTVIEW;
	static String TABLE_NAME = "maliin_endegdel";
	int mal_count;
	TextView tv_niit_mal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(
				WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
						| WindowManager.LayoutParams.FLAG_FULLSCREEN
						| WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.maliin_endegdel);

		ImageView iv_back = (ImageView) findViewById(R.id.iv_back);
		iv_back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		iv_add = (ImageView) findViewById(R.id.iv_add);
		iv_add.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i0 = new Intent(getApplicationContext(),
						Endegdel_add.class);
				startActivity(i0);
			}
		});

	}
	public boolean exists(String table) {
	    try {
	    	SQLITEDATABASE = openOrCreateDatabase("Malchin", Context.MODE_PRIVATE,
					null);
			SQLITEDATABASE.rawQuery("SELECT * from " + table,
					null);
	         return true;
	    } catch (SQLException e) {
	    	
	         return false;
	    }
	}
	
	@Override
	protected void onResume() {
		if (!exists(TABLE_NAME)) {
			Toast.makeText(getApplicationContext(),
					"Эндэгдэлтэй мал одоогоор бүртгэгдээгүй байна.", 0).show();
		} else {
			Show_Main();
			ShowSQLiteDBdata();
		}
		super.onResume();
	}

	public void Show_Main() {
		GetSQliteQuery = "SELECT * FROM " + TABLE_NAME;

		cursor = SQLITEDATABASE.rawQuery(GetSQliteQuery, null);
		cursor.moveToFirst();

		Cursor cur = SQLITEDATABASE
				.rawQuery(
						"SELECT (SUM(tom_count) + SUM(tul_count)) as count FROM "+TABLE_NAME,
						null);
		if (cur.moveToFirst()) {
			mal_count = cur.getInt(0);
		}
		LISTVIEW = (ListView) findViewById(R.id.listView1);

		tv_niit_mal = (TextView) findViewById(R.id.tv_niit_mal);
		tv_niit_mal.setText("Нийт эндэгдлийн тоо = "
				+ String.valueOf(mal_count));

		LISTVIEW.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> arg0, View v,
					final int index, long arg3) {
				AlertDialog alertDialog = new AlertDialog.Builder(
						maliin_endegdel.this).create();
				alertDialog.setTitle("Устгах");
				alertDialog.setMessage("Та итгэлтэй байна уу !!!");
				alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Үгүй",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						});
				alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Тийм",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								String DeleteQuery = "DELETE FROM "+TABLE_NAME
										+ " WHERE id="
										+ SQLiteListAdapter_endegdel.User_id.get(index)
										+ ";";
								SQLITEDATABASE.execSQL(DeleteQuery);
								cursor = SQLITEDATABASE.rawQuery(
										GetSQliteQuery, null);
								
								ShowSQLiteDBdata();
							}
						});
				alertDialog.show();
				return true;
			}
		});
	}

	private void ShowSQLiteDBdata() {
		tv_niit_mal.setText("Нийт эндэгдлийн тоо = "
				+ String.valueOf(mal_count));
		SQLITEDATABASE = openOrCreateDatabase("Malchin", Context.MODE_PRIVATE,
				null);

		cursor = SQLITEDATABASE.rawQuery("SELECT * FROM maliin_endegdel", null);

		ID_ArrayList.clear();
		MALSUREG_ArrayList.clear();
		TOMMAL_ArrayList.clear();
		TULMAL_ArrayList.clear();
		SHALTGAAN_ArrayList.clear();
		DATE_ArrayList.clear();

		if (cursor.moveToFirst()) {
			do {
				ID_ArrayList.add(cursor.getString(cursor
						.getColumnIndex(SQLiteHelper_endegdel.KEY_id)));

				MALSUREG_ArrayList.add(cursor.getString(cursor
						.getColumnIndex(SQLiteHelper_endegdel.KEY_Malsureg)));

				TOMMAL_ArrayList.add(cursor.getString(cursor
						.getColumnIndex(SQLiteHelper_endegdel.KEY_Maltoo)));

				TULMAL_ArrayList.add(cursor.getString(cursor
						.getColumnIndex(SQLiteHelper_endegdel.KEY_Tulmal)));
				SHALTGAAN_ArrayList.add(cursor.getString(cursor
						.getColumnIndex(SQLiteHelper_endegdel.KEY_shaltgaan)));

				DATE_ArrayList.add(cursor.getString(cursor
						.getColumnIndex(SQLiteHelper_endegdel.KEY_Date)));

			} while (cursor.moveToNext());
		}

		ListAdapter = new SQLiteListAdapter_endegdel(getApplicationContext(),

		ID_ArrayList, MALSUREG_ArrayList, TOMMAL_ArrayList, TULMAL_ArrayList,
				SHALTGAAN_ArrayList, DATE_ArrayList

		);

		LISTVIEW.setAdapter(ListAdapter);

		cursor.close();
	}

}
