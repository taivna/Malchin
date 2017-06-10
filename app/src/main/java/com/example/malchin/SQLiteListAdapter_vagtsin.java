package com.example.malchin;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SQLiteListAdapter_vagtsin extends BaseAdapter {

	Context context;

	static ArrayList<String> User_id;
	static ArrayList<String> User_malsureg;
	ArrayList<String> User_tommal;
	ArrayList<String> User_vagtsin_name;
	ArrayList<String> User_vagtsin_niit;
	ArrayList<String> User_date;

	public SQLiteListAdapter_vagtsin(Context context2, ArrayList<String> id, ArrayList<String> malsureg,ArrayList<String> tommal,
			ArrayList<String> vagtsin_name, ArrayList<String> vagtsin_niit, ArrayList<String> date) {

		this.context = context2;
		this.User_id = id;
		this.User_malsureg = malsureg;
		this.User_tommal = tommal;
		this.User_vagtsin_name = vagtsin_name;
		this.User_vagtsin_niit= vagtsin_niit;
		this.User_date = date;
	}

	public int getCount() {
		return User_id.size();
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

	public View getView(int position, View child, ViewGroup parent) {

		Holder holder;

		LayoutInflater layoutInflater;

		if (child == null) {
			layoutInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			child = layoutInflater.inflate(R.layout.listviewdatalayout_vagtsin, null);

			holder = new Holder();

			holder.tv_all = (TextView) child
					.findViewById(R.id.tv_all);
			holder.tv_tommal = (TextView) child.findViewById(R.id.tv_tommal);
			holder.tv_vagtsin_name = (TextView) child.findViewById(R.id.tv_vagtsin_name);
			holder.tv_vagtsin_niit = (TextView) child.findViewById(R.id.tv_vagstin_niit);
			holder.tv_date = (TextView) child.findViewById(R.id.tv_date);
			holder.iv_malsureg_pic = (ImageView) child
					.findViewById(R.id.iv_malsureg_pic);
			child.setTag(holder);

		} else {

			holder = (Holder) child.getTag();
		}
		holder.tv_tommal.setText(User_tommal.get(position));
		holder.tv_vagtsin_name.setText(User_vagtsin_name.get(position));
		holder.tv_vagtsin_niit.setText("\n"+User_vagtsin_niit.get(position)+"₮");
		holder.tv_date.setText("\n"+User_date.get(position));
		holder.tv_all.setText(User_malsureg.get(position));
		
		if (User_malsureg.get(position).contentEquals("Хонь")) {
			holder.iv_malsureg_pic.setImageResource(R.drawable.sheep);
		} else if (User_malsureg.get(position).contentEquals("Ямаа")) {
			holder.iv_malsureg_pic.setImageResource(R.drawable.goat);
		} else if (User_malsureg.get(position).contentEquals("Адуу")) {
			holder.iv_malsureg_pic.setImageResource(R.drawable.horse);
		} else if (User_malsureg.get(position).contentEquals("Үхэр")) {
			holder.iv_malsureg_pic.setImageResource(R.drawable.cattle);
		} else if (User_malsureg.get(position).contentEquals("Тэмээ")) {
			holder.iv_malsureg_pic.setImageResource(R.drawable.camel);
		}
		
		return child;
	}

	public class Holder {
		TextView tv_all;
		TextView tv_tommal;
		TextView tv_vagtsin_name;
		TextView tv_vagtsin_niit;
		TextView tv_date;
		ImageView iv_malsureg_pic;
	}

}