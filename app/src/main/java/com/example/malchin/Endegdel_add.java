package com.example.malchin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Endegdel_add extends Activity
{
	Spinner spinner;
	DatePicker dpResult;
	SQLiteDatabase SQLITEDATABASE;
	EditText etTommal, etTulmal, et_shaltgaan;
	Button btnsubmit;
	static String malturul, tom_count, tul_count, date, shaltgaan;
	Boolean CheckEditTextEmpty;
	String SQLiteQuery;
	TextView tv_id;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_FULLSCREEN
						| WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.endegdel_burtgel);

		etTommal = (EditText) findViewById(R.id.et_tommal);
		etTulmal = (EditText) findViewById(R.id.et_tulmal);
		et_shaltgaan= (EditText) findViewById(R.id.et_shaltgaan);
		btnsubmit = (Button) findViewById(R.id.btn_burtgeh);
		
		ImageView iv_back = (ImageView) findViewById(R.id.iv_back);
		iv_back.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				finish();
			}
		});
		
		spinner = (Spinner) findViewById(R.id.spinner);
		List<String> list = new ArrayList<>();
		list.add("Хонь");
		list.add("Ямаа");
		list.add("Адуу");
		list.add("Үхэр");
		list.add("Тэмээ");

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(dataAdapter);

		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
			{
				// Toast.makeText(getApplicationContext(),
				// String.valueOf(spinner.getSelectedItem()), 0).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0)
			{
			}
		});

		dpResult = (DatePicker) findViewById(R.id.dp_date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		dpResult.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
				new DatePicker.OnDateChangedListener()
				{
					@Override
					public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth)
					{
						// Toast.makeText(
						// getApplicationContext(),
						// "Year=" + year + " Month=" + (month + 1)
						// + " day=" + dayOfMonth, 0).show();

						date = year + "/" + (month + 1) + "/" + dayOfMonth;
					}
				});
		date = calendar.get(Calendar.YEAR) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.DAY_OF_MONTH);
		
		btnsubmit.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				DBCreate();
				SubmitData2SQLiteDB();
				finish();
			}
		});
	}

	public void DBCreate()
	{
		SQLITEDATABASE = openOrCreateDatabase("Malchin", Context.MODE_PRIVATE, null);

		SQLITEDATABASE.execSQL("CREATE TABLE IF NOT EXISTS maliin_endegdel(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, malturul VARCHAR, tom_count VARCHAR, tul_count VARCHAR,  shaltgaan VARCHAR,date VARCHAR);");
	}

	public void SubmitData2SQLiteDB()
	{
		malturul = String.valueOf(spinner.getSelectedItem());
		tom_count = etTommal.getText().toString();
		shaltgaan = et_shaltgaan.getText().toString();
		tul_count = etTulmal.getText().toString();

		CheckEditTextIsEmptyOrNot(tom_count, tul_count);

		if (CheckEditTextEmpty == true)
		{
			SQLiteQuery = "INSERT INTO maliin_endegdel(malturul,tom_count,tul_count, shaltgaan, date) VALUES('"
					+ malturul
					+ "', '"
					+ tom_count
					+ "', '"
					+ tul_count
					+ "', '"
					+ shaltgaan
					+ "', '"
					+ date + "');";

			SQLITEDATABASE.execSQL(SQLiteQuery);

			Cursor cursor = SQLITEDATABASE.rawQuery("SELECT * FROM malchintable WHERE malturul = '" + malturul + "'", null);
			cursor.moveToFirst();
			int tom_mal = cursor.getInt(cursor.getColumnIndex("tom_count"));
			int tul_mal = cursor.getInt(cursor.getColumnIndex("tul_count"));

			tom_mal = tom_mal - Integer.parseInt(tom_count);
			tul_mal = tul_mal - Integer.parseInt(tul_count);

			ContentValues contentValues = new ContentValues();
			contentValues.put("tom_count", tom_mal);
			contentValues.put("tul_count", tul_mal);

			SQLITEDATABASE.update("malchintable", contentValues, "malturul = '" + malturul + "'", null);

			Toast.makeText(getApplicationContext(), "Амжилттай нэмэгдлээ.", Toast.LENGTH_LONG).show();
			ClearEditTextAfterDoneTask();

		}
		else
		{
			Toast.makeText(getApplicationContext(), "Та заавал бүх талбарыг бөглөх ёстой.", Toast.LENGTH_LONG).show();
		}
	}

	public void CheckEditTextIsEmptyOrNot(String tom_mal, String tul_mal)
	{
		if (TextUtils.isEmpty(tom_mal) || TextUtils.isEmpty(tul_mal))
		{
			CheckEditTextEmpty = false;
		}
		else
		{
			CheckEditTextEmpty = true;
		}
	}

	public void ClearEditTextAfterDoneTask()
	{
		etTommal.getText().clear();
		etTulmal.getText().clear();
	}
}
