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

public class SQLiteListAdapter_tuuhii_ed_borluulalt extends BaseAdapter {

	Context context;

	static ArrayList<String> User_id;
	static ArrayList<String> User_malsureg;
	ArrayList<String> User_hemjee;
	ArrayList<String> User_too;
	ArrayList<String> User_date;

	public SQLiteListAdapter_tuuhii_ed_borluulalt(Context context2, ArrayList<String> id,
			ArrayList<String> malsureg, ArrayList<String> hemjee,
			ArrayList<String> too, ArrayList<String> date) {

		this.context = context2;
		this.User_id = id;
		this.User_malsureg = malsureg;
		this.User_hemjee = hemjee;
		this.User_too = too;
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
			child = layoutInflater.inflate(R.layout.listviewdatalayout_tuuhii_ed_borluulalt,
					null);

			holder = new Holder();

			holder.tv_all = (TextView) child.findViewById(R.id.tv_all);
			holder.tv_niit_hemjee = (TextView) child.findViewById(R.id.tv_niit_hemjee);
			holder.tv_niit_too = (TextView) child
					.findViewById(R.id.tv_niit_too);
			holder.tv_date = (TextView) child.findViewById(R.id.tv_date);
			holder.iv_malsureg_pic = (ImageView) child
					.findViewById(R.id.iv_malsureg_pic);
			child.setTag(holder);

		} else {

			holder = (Holder) child.getTag();
		}
		holder.tv_niit_hemjee.setText(User_hemjee.get(position) +" кг");
		holder.tv_niit_too.setText(User_too.get(position) +" ₮");
		holder.tv_date.setText(User_date.get(position));
		holder.tv_all.setText(User_malsureg.get(position));

		if (User_malsureg.get(position).contentEquals("Хонины ноос")) {
			holder.iv_malsureg_pic.setImageResource(R.drawable.sheep);
		} else if (User_malsureg.get(position).contentEquals("Ямааны ноолуур самналт")) {
			holder.iv_malsureg_pic.setImageResource(R.drawable.goat);
		} else if (User_malsureg.get(position).contentEquals("Тэмээний ноос авалт")) {
			holder.iv_malsureg_pic.setImageResource(R.drawable.camel);
		} else if (User_malsureg.get(position).contentEquals("Сарлагын хөөвөр авалт")) {
			holder.iv_malsureg_pic.setImageResource(R.drawable.sarlag);
		} 
		return child;
	}

	public class Holder {
		TextView tv_all;
		TextView tv_niit_hemjee;
		TextView tv_niit_too;
		TextView tv_date;
		ImageView iv_malsureg_pic;
	}

}