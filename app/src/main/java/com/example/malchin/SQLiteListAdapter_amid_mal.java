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

public class SQLiteListAdapter_amid_mal extends BaseAdapter {

	Context context;

	static ArrayList<String> User_id;
	static ArrayList<String> User_malsureg;
	ArrayList<String> User_tommal;
	ArrayList<String> User_niit_jin;
	ArrayList<String> User_orlogo;
	ArrayList<String> User_date;

	public SQLiteListAdapter_amid_mal(Context context2, ArrayList<String> id,
			ArrayList<String> malsureg, ArrayList<String> tommal,
			ArrayList<String> niit_jin, ArrayList<String> orlogo,
			ArrayList<String> date) {

		this.context = context2;
		this.User_id = id;
		this.User_malsureg = malsureg;
		this.User_tommal = tommal;
		this.User_niit_jin = niit_jin;
		this.User_orlogo = orlogo;
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
			child = layoutInflater.inflate(R.layout.listviewdatalayout_amid_mal,
					null);

			holder = new Holder();

			holder.tv_all = (TextView) child.findViewById(R.id.tv_all);
			holder.tv_tommal = (TextView) child.findViewById(R.id.tv_tommal);
			holder.tv_niit_jin = (TextView) child
					.findViewById(R.id.tv_niit_jin);
			holder.tv_orlogo = (TextView) child
					.findViewById(R.id.tv_orlogo);
			holder.tv_date = (TextView) child.findViewById(R.id.tv_date);
			holder.iv_malsureg_pic = (ImageView) child
					.findViewById(R.id.iv_malsureg_pic);
			child.setTag(holder);

		} else {

			holder = (Holder) child.getTag();
		}
		holder.tv_tommal.setText(User_tommal.get(position));
		holder.tv_niit_jin.setText("\n" + User_niit_jin.get(position) +" кг");
		holder.tv_orlogo.setText(User_orlogo.get(position)
				+ "₮");
		holder.tv_date.setText("\n" + User_date.get(position));
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
		TextView tv_niit_jin;
		TextView tv_orlogo;
		TextView tv_date;
		ImageView iv_malsureg_pic;
	}

}