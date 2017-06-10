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

public class SQLiteListAdapter_endegdel extends BaseAdapter {

	Context context;

	static ArrayList<String> User_id;
	static ArrayList<String> User_malsureg;
	ArrayList<String> User_tommal;
	ArrayList<String> User_tulmal;
	ArrayList<String> User_shaltgaan;
	ArrayList<String> User_date;

	public SQLiteListAdapter_endegdel(Context context2, ArrayList<String> id, ArrayList<String> malsureg,ArrayList<String> tommal,
			ArrayList<String> tulmal, ArrayList<String> shaltgaan, ArrayList<String> date) {

		this.context = context2;
		this.User_id = id;
		this.User_malsureg = malsureg;
		this.User_tommal = tommal;
		this.User_tulmal = tulmal;
		this.User_shaltgaan= shaltgaan;
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
			child = layoutInflater.inflate(R.layout.listviewdatalayout_endegdel, null);

			holder = new Holder();

			holder.tv_malsureg = (TextView) child
					.findViewById(R.id.tv_malsureg);
			holder.tv_tommal = (TextView) child.findViewById(R.id.tv_tommal);
			holder.tv_tulmal = (TextView) child.findViewById(R.id.tv_tulmal);
			holder.tv_shalt = (TextView) child.findViewById(R.id.tv_shalt);
			holder.tv_date = (TextView) child.findViewById(R.id.tv_date);
			holder.iv_malsureg_pic = (ImageView) child
					.findViewById(R.id.iv_malsureg_pic);
			child.setTag(holder);

		} else {

			holder = (Holder) child.getTag();
		}
		holder.tv_malsureg.setText(User_malsureg.get(position));
		holder.tv_tommal.setText(User_tommal.get(position));
		holder.tv_tulmal.setText(User_tulmal.get(position));
		holder.tv_shalt.setText(User_shaltgaan.get(position));
		holder.tv_date.setText(User_date.get(position));
		
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
		TextView tv_malsureg;
		TextView tv_tommal;
		TextView tv_tulmal;
		TextView tv_shalt;
		TextView tv_date;
		ImageView iv_malsureg_pic;
	}

}