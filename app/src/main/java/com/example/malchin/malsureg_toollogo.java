package com.example.malchin;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class malsureg_toollogo extends Activity {

	SQLiteDatabase SQLITEDATABASE;
	Cursor cursor;
	SQLiteListAdapter_toollogo ListAdapter;
	ImageView iv_home;

	ArrayList<String> ID_ArrayList = new ArrayList<String>();
	ArrayList<String> TOMMAL_ArrayList = new ArrayList<String>();
	ArrayList<String> TULMAL_ArrayList = new ArrayList<String>();
	ArrayList<String> DATE_ArrayList = new ArrayList<String>();
	ListView LISTVIEW;
	String malsureg;

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
		setContentView(R.layout.mal_tollogo);

		LinearLayout ll_footer = (LinearLayout) findViewById(R.id.ll_footer);
		ll_footer.setVisibility(View.GONE);
		
		LISTVIEW = (ListView) findViewById(R.id.listView1);

		ImageView iv_back = (ImageView) findViewById(R.id.iv_back);
		iv_back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		iv_home = (ImageView) findViewById(R.id.iv_add);
		iv_home.setImageResource(R.drawable.home);
		iv_home.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent i0 = new Intent(getApplicationContext(),
						MainActivity.class);
				i0.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
						| Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i0);
			}
		});

		Bundle extras = getIntent().getExtras();
		malsureg = extras.getString("malsureg");
		TextView tv_toollogo_ig = (TextView) findViewById(R.id.tv_toollogo_ig);
		tv_toollogo_ig.setText(malsureg + " тооллого");
	}

	@Override
	protected void onResume() {
		ShowSQLiteDBdata();
		super.onResume();
	}

	private void ShowSQLiteDBdata() {

		SQLITEDATABASE = openOrCreateDatabase("Malchin", Context.MODE_PRIVATE,
				null);

		cursor = SQLITEDATABASE
				.rawQuery("SELECT * FROM toollogoadd WHERE malturul = '"
						+ malsureg + "'", null);

		ID_ArrayList.clear();
		TOMMAL_ArrayList.clear();
		TULMAL_ArrayList.clear();
		DATE_ArrayList.clear();

		if (cursor.moveToFirst()) {
			do {
				ID_ArrayList.add(cursor.getString(cursor
						.getColumnIndex(SQLiteHelper_toollogo.KEY_id)));

				TOMMAL_ArrayList.add(cursor.getString(cursor
						.getColumnIndex(SQLiteHelper_toollogo.KEY_Maltoo)));

				TULMAL_ArrayList.add(cursor.getString(cursor
						.getColumnIndex(SQLiteHelper_toollogo.KEY_Tulmal)));

				DATE_ArrayList.add(cursor.getString(cursor
						.getColumnIndex(SQLiteHelper_toollogo.KEY_Date)));

			} while (cursor.moveToNext());
		}

		ListAdapter = new SQLiteListAdapter_toollogo(getApplicationContext(),

		ID_ArrayList, TOMMAL_ArrayList, TULMAL_ArrayList, DATE_ArrayList

		);

		LISTVIEW.setAdapter(ListAdapter);

		cursor.close();
	}

}
