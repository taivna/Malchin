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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class mal_toollogo extends Activity {

	SQLiteDatabase SQLITEDATABASE;
	Cursor cursor;
	SQLiteListAdapter ListAdapter;
	ImageView iv_add;
	String GetSQliteQuery;
	ArrayList<String> ID_ArrayList = new ArrayList<>();
	ArrayList<String> MALSUREG_ArrayList = new ArrayList<>();
	ArrayList<String> TOMMAL_ArrayList = new ArrayList<>();
	ArrayList<String> TULMAL_ArrayList = new ArrayList<>();
	ArrayList<String> DATE_ArrayList = new ArrayList<>();
	ListView LISTVIEW;
	String TABLE_NAME = "malchintable"; 
	int mal_count, s;
	TextView tv_niit_mal;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
				| WindowManager.LayoutParams.FLAG_FULLSCREEN
				| WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mal_tollogo);

		
		LISTVIEW = (ListView) findViewById(R.id.listView1);

		ImageView iv_back = (ImageView) findViewById(R.id.iv_back);
		iv_back.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				finish();
			}
		});

		iv_add = (ImageView) findViewById(R.id.iv_add);
		iv_add.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				Intent i0 = new Intent(getApplicationContext(), Toollogo_add.class);
				startActivity(i0);
			}
		});

		tv_niit_mal = (TextView) findViewById(R.id.tv_niit_mal);
			
	}

	public boolean exists(String table)
	{
	    try
		{
	    	SQLITEDATABASE = openOrCreateDatabase("Malchin", Context.MODE_PRIVATE, null);
			SQLITEDATABASE.rawQuery("SELECT * from " + table, null);
	         return true;
	    }
	    catch (SQLException e)
		{
			return false;
	    }
	}

	@Override
	protected void onResume()
	{
		if (!exists(TABLE_NAME))
		{
			Toast.makeText(getApplicationContext(), "Бүртгэгдсэн мал одоогоор байхгүй байна.", Toast.LENGTH_SHORT).show();
		}
		else
		{
			Show_Main();
			ShowSQLiteDBdata();
			tv_niit_mal.setText("Нийт малын тоо = " + String.valueOf(mal_count));
		}
		super.onResume();
	}

	public void Show_Main()
	{
		GetSQliteQuery = "SELECT * FROM malchintable";
		SQLITEDATABASE = openOrCreateDatabase("Malchin", Context.MODE_PRIVATE, null);
		SQLITEDATABASE.execSQL("CREATE TABLE IF NOT EXISTS toollogoadd(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
				"malturul VARCHAR, tom_count VARCHAR, tul_count VARCHAR, date VARCHAR);");

		cursor = SQLITEDATABASE.rawQuery(GetSQliteQuery, null);
		cursor.moveToFirst();

		Cursor cur = SQLITEDATABASE.rawQuery(
						"SELECT (SUM(tom_count) + SUM(tul_count)) as count FROM malchintable",
						null);

		if (cur.moveToFirst())
		{
			mal_count = cur.getInt(0);
		}
		
		LISTVIEW.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Intent i0 = new Intent(getApplicationContext(), malsureg_toollogo.class);
				i0.putExtra("malsureg", SQLiteListAdapter.User_malsureg.get(position));
				startActivity(i0);
			}
		});

		LISTVIEW.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
		{
			public boolean onItemLongClick(AdapterView<?> arg0, View v, final int index, long arg3)
			{
				AlertDialog alertDialog = new AlertDialog.Builder(mal_toollogo.this).create();
				alertDialog.setTitle("Устгах");
				alertDialog.setMessage("Та итгэлтэй байна уу !!!");

				alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Үгүй",
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialog, int which)
							{
								dialog.dismiss();
							}
						});

				alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Тийм",
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialog, int which)
							{
								String DeleteQuery = "DELETE FROM malchintable"
										+ " WHERE id="
										+ SQLiteListAdapter.User_id.get(index)
										+ ";";
								SQLITEDATABASE.execSQL(DeleteQuery);
								cursor = SQLITEDATABASE.rawQuery(GetSQliteQuery, null);
								ShowSQLiteDBdata();
							}
						});
				alertDialog.show();
				return true;
			}
		});
	}

	private void ShowSQLiteDBdata()
	{
		SQLITEDATABASE = openOrCreateDatabase("Malchin", Context.MODE_PRIVATE, null);

		cursor = SQLITEDATABASE.rawQuery("SELECT * FROM malchintable", null);

		ID_ArrayList.clear();
		MALSUREG_ArrayList.clear();
		TOMMAL_ArrayList.clear();
		TULMAL_ArrayList.clear();
		DATE_ArrayList.clear();

		if (cursor.moveToFirst())
		{
			do
			{
				ID_ArrayList.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_id)));

				MALSUREG_ArrayList.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_Malsureg)));

				TOMMAL_ArrayList.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_Maltoo)));

				TULMAL_ArrayList.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_Tulmal)));

				DATE_ArrayList.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_Date)));

			}
			while (cursor.moveToNext());
		}

		ListAdapter = new SQLiteListAdapter(getApplicationContext(), ID_ArrayList, MALSUREG_ArrayList,
				TOMMAL_ArrayList, TULMAL_ArrayList, DATE_ArrayList);

		LISTVIEW.setAdapter(ListAdapter);
		cursor.close();
	}
}
