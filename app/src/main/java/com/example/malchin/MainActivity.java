package com.example.malchin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button btnMToollogo, btnMEndegdel, btnVagtsin, btnTEHuraalt, btnTEBor,
			btnAmid, btnBRestore, btndar, btnmalburtgel;
	boolean btn_check = true;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_FULLSCREEN
						| WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		btnmalburtgel = (Button) findViewById(R.id.btnmalburtgel);
		btnMToollogo = (Button) findViewById(R.id.btnmaltoollogo);
		btnMEndegdel = (Button) findViewById(R.id.btnmaliinendegdel);
		btnVagtsin = (Button) findViewById(R.id.btnvagtsin);
		btnTEHuraalt = (Button) findViewById(R.id.btnhuraalt);
		btnTEBor = (Button) findViewById(R.id.btnbor);
		btnAmid = (Button) findViewById(R.id.btnamid);
		btnBRestore = (Button) findViewById(R.id.btnbackrestore);
		
		btnmalburtgel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i0 = new Intent(getApplicationContext(),
						mal_burtgel.class);
				startActivity(i0);
			}
		});
		
		btnMToollogo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i0 = new Intent(getApplicationContext(),
						mal_toollogo.class);
				startActivity(i0);
			}
		});
		btnMEndegdel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i0 = new Intent(getApplicationContext(),
						maliin_endegdel.class);
				startActivity(i0);
			}
		});
		btnVagtsin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i0 = new Intent(getApplicationContext(), vagtsin.class);
				startActivity(i0);
			}
		});
		btnTEHuraalt.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i0 = new Intent(getApplicationContext(),
						tuuhii_ed_huraalt.class);
				startActivity(i0);
			}
		});
		btnTEBor.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i0 = new Intent(getApplicationContext(),
						tuuhii_ed_borluulalt.class);
				startActivity(i0);
			}
		});
		btnAmid.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i0 = new Intent(getApplicationContext(),
						amid_mal_bor.class);
				startActivity(i0);
			}
		});
		btnBRestore.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
			}
		});
		btndar = (Button) findViewById(R.id.btndar);
		btndar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (btn_check == false) {
					btn_check = true;
					btnTEHuraalt.setVisibility(View.GONE);
					btnTEBor.setVisibility(View.GONE);
				} else if(btn_check == true){
					btn_check = false;
					btnTEHuraalt.setVisibility(View.VISIBLE);
					btnTEBor.setVisibility(View.VISIBLE);
				}
			}
		});
	}
	@Override
	protected void onResume() {
		btn_check = true;
		btnTEHuraalt.setVisibility(View.GONE);
		btnTEBor.setVisibility(View.GONE);
		super.onResume();
	}
}
