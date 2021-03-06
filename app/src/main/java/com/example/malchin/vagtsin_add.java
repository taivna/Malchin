package com.example.malchin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.content.Context;
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

public class vagtsin_add extends Activity {

	Spinner spinner;
	DatePicker dpResult;
	SQLiteDatabase SQLITEDATABASE;
	EditText etTommal, et_vagtsin_name, et_vagtsin_niit;
	Button btnsubmit;
	static String malturul, vagtsin_name, vagtsin_niit, date, Tommal;
	Boolean CheckEditTextEmpty;
	String SQLiteQuery;
	TextView tv_id;

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
		setContentView(R.layout.vagtsin_burtgel);

		etTommal = (EditText) findViewById(R.id.et_tommal);
		et_vagtsin_name = (EditText) findViewById(R.id.et_vagtsin_name);
		et_vagtsin_niit= (EditText) findViewById(R.id.et_vagtsin_niit);
		btnsubmit = (Button) findViewById(R.id.btn_burtgeh);
		
		ImageView iv_back = (ImageView) findViewById(R.id.iv_back);
		iv_back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		
		spinner = (Spinner) findViewById(R.id.spinner);
		List<String> list = new ArrayList<String>();
		list.add("Хонь");
		list.add("Ямаа");
		list.add("Адуу");
		list.add("Үхэр");
		list.add("Тэмээ");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(dataAdapter);

		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// Toast.makeText(getApplicationContext(),
				// String.valueOf(spinner.getSelectedItem()), 0).show();

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

		dpResult = (DatePicker) findViewById(R.id.dp_date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		dpResult.init(calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH),
				new DatePicker.OnDateChangedListener() {

					@Override
					public void onDateChanged(DatePicker datePicker, int year,
							int month, int dayOfMonth) {
						// Toast.makeText(
						// getApplicationContext(),
						// "Year=" + year + " Month=" + (month + 1)
						// + " day=" + dayOfMonth, 0).show();

						date = year + "/" + (month + 1) + "/" + dayOfMonth;
					}
				});
		date = calendar.get(Calendar.YEAR) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.DAY_OF_MONTH);
		
		btnsubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				DBCreate();
				SubmitData2SQLiteDB();
				finish();
			}
		});

	}

	public void DBCreate() {

		SQLITEDATABASE = openOrCreateDatabase("Malchin", Context.MODE_PRIVATE,
				null);

		SQLITEDATABASE
				.execSQL("CREATE TABLE IF NOT EXISTS maliin_vagtsin(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, malturul VARCHAR, tom_count VARCHAR, name VARCHAR, niit VARCHAR, date VARCHAR);");
	}

	public void SubmitData2SQLiteDB() {

		malturul = String.valueOf(spinner.getSelectedItem());

		Tommal = etTommal.getText().toString();
		vagtsin_name = et_vagtsin_name.getText().toString();
		vagtsin_niit = et_vagtsin_niit.getText().toString();

		CheckEditTextIsEmptyOrNot(Tommal, vagtsin_name, vagtsin_niit);

		if (CheckEditTextEmpty == true) {

			SQLiteQuery = "INSERT INTO maliin_vagtsin(malturul,tom_count,name, niit, date) VALUES('"
					+ malturul
					+ "', '"
					+ Tommal
					+ "', '"
					+ vagtsin_name
					+ "', '"
					+ vagtsin_niit
					+ "', '" + date + "');";

			SQLITEDATABASE.execSQL(SQLiteQuery);

			Toast.makeText(getApplicationContext(), "Амжилттай нэмэгдлээ.",
					Toast.LENGTH_LONG).show();

			ClearEditTextAfterDoneTask();

		} else {

			Toast.makeText(getApplicationContext(),
					"Та заавал бүх талбарыг бөглөх ёстой.", Toast.LENGTH_LONG)
					.show();
		}
	}

	public void CheckEditTextIsEmptyOrNot(String Tommal, String vagtsin_name, String vagtsin_niit) {

		if (TextUtils.isEmpty(Tommal) || TextUtils.isEmpty(vagtsin_name) || TextUtils.isEmpty(vagtsin_niit)) {

			CheckEditTextEmpty = false;

		} else {
			CheckEditTextEmpty = true;
		}
	}

	public void ClearEditTextAfterDoneTask() {

		etTommal.getText().clear();
		et_vagtsin_name.getText().clear();
		et_vagtsin_niit.getText().clear();
	}

}
