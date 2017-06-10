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

public class tuuhii_ed_borluulalt_add extends Activity {

	Spinner spinner;
	DatePicker dpResult;
	SQLiteDatabase SQLITEDATABASE;
	EditText et_Too, et_hemjee;
	Button btnsubmit;
	static String malturul, date, too, hemjee;
	Boolean CheckEditTextEmpty;
	String SQLiteQuery;
	TextView tv_id, tv_hemjee, tv_too, tv_ognoo;

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
		setContentView(R.layout.tuuhii_ed_borluulalt_burtgel);

		et_Too = (EditText) findViewById(R.id.et_too);
		et_hemjee= (EditText) findViewById(R.id.et_hemjee);
		tv_hemjee= (TextView) findViewById(R.id.tv_hemjee);
		tv_too= (TextView) findViewById(R.id.tv_Too);
		tv_ognoo= (TextView) findViewById(R.id.tv_ognoo);
		
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
		list.add("Хонины ноос");
		list.add("Ямааны ноолуур самналт");
		list.add("Тэмээний ноос авалт");
		list.add("Сарлагын хөөвөр авалт");
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

				switch (String.valueOf(spinner.getSelectedItem())) {
				case "Хонины ноос":
					tv_hemjee.setText("Ноосны нийт хэмжээ : ");
					tv_too.setText("Орлого : ");
					tv_ognoo.setText("Ноос авсан огноо : ");
					break;
				case "Ямааны ноолуур самналт":
					tv_hemjee.setText("Ноолуурын нийт хэмжээ : ");
					tv_too.setText("Орлого : ");
					tv_ognoo.setText("Ямаа самнасан огноо : ");
					break;
				case "Тэмээний ноос авалт":
					tv_hemjee.setText("Ноосны нийт хэмжээ : ");
					tv_too.setText("Орлого : ");
					tv_ognoo.setText("Ноос авсан огноо : ");
					break;
				case "Сарлагын хөөвөр авалт":
					tv_hemjee.setText("Хөөврийн нийт хэмжээ : ");
					tv_too.setText("Орлого : ");
					tv_ognoo.setText("Хөөвөр авсан огноо : ");
					break;
				default:
					break;
				}
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
				.execSQL("CREATE TABLE IF NOT EXISTS borluulalt(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, malturul VARCHAR, hemjee VARCHAR, count VARCHAR, date VARCHAR);");
	}

	public void SubmitData2SQLiteDB() {
		
		
		
		malturul = String.valueOf(spinner.getSelectedItem());		
		hemjee = et_hemjee.getText().toString();
		too = et_Too.getText().toString();

		CheckEditTextIsEmptyOrNot(hemjee, too);

		if (CheckEditTextEmpty == true) {

			SQLiteQuery = "INSERT INTO borluulalt(malturul, hemjee, count, date) VALUES('"
					+ malturul
					+ "', '"
					+ hemjee
					+ "', '"
					+ too
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

	public void CheckEditTextIsEmptyOrNot(String hemjee, String too) {

		if (TextUtils.isEmpty(hemjee) || TextUtils.isEmpty(too)) {

			CheckEditTextEmpty = false;

		} else {
			CheckEditTextEmpty = true;
		}
	}

	public void ClearEditTextAfterDoneTask() {

		et_hemjee.getText().clear();
		et_Too.getText().clear();
	}

}
