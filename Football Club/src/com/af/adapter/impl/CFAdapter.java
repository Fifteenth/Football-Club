package com.af.adapter.impl;

import java.util.List;

import com.cf.to.MenuTO;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

public class CFAdapter implements ListAdapter{
	
	
	private Context context;
	private List<?>list;
	
	public CFAdapter(Context context, List<?> list){
		this.context = context;
		this.list = list;
	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return convertView;
	}

	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean areAllItemsEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled(int position) {
		// TODO Auto-generated method stub
		return true;
	}

}
