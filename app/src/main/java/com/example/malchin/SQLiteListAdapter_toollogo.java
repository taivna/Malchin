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

public class SQLiteListAdapter_toollogo extends BaseAdapter {

	Context context;

	ArrayList<String> User_id;
	ArrayList<String> User_tommal;
	ArrayList<String> User_tulmal;
	ArrayList<String> User_date;

	public SQLiteListAdapter_toollogo(Context context2, ArrayList<String> id, ArrayList<String> tommal,
			ArrayList<String> tulmal, ArrayList<String> date) {

		this.context = context2;
		this.User_id = id;
		this.User_tommal = tommal;
		this.User_tulmal = tulmal;
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
			child = layoutInflater.inflate(R.layout.listviewdatalayout, null);

			holder = new Holder();

			
			holder.tv_tommal = (TextView) child.findViewById(R.id.tv_tommal);
			holder.tv_tulmal = (TextView) child.findViewById(R.id.tv_tulmal);
			holder.tv_date = (TextView) child.findViewById(R.id.tv_date);
			holder.ll_malsureg = (LinearLayout) child.findViewById(R.id.ll_malsureg);
			child.setTag(holder);

		} else {

			holder = (Holder) child.getTag();
		}
		holder.tv_tommal.setText(User_tommal.get(position));
		holder.tv_tulmal.setText(User_tulmal.get(position));
		holder.tv_date.setText(User_date.get(position));
		holder.ll_malsureg.setVisibility(View.GONE);
		
		return child;
	}

	public class Holder {
		TextView tv_tommal;
		TextView tv_tulmal;
		TextView tv_date;
		LinearLayout ll_malsureg;
	}

}