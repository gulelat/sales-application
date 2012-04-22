package com.android.salesapp.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.salesapp.R;
import com.android.salesapp.bean.ProjectBean;

public class ProjectListAdapter extends BaseAdapter 
{
	ArrayList<ProjectBean>  objList;
	LayoutInflater inflater;
	
	public ProjectListAdapter(Context context, ArrayList<ProjectBean> _objList)
	{
		inflater = LayoutInflater.from(context);
		objList = _objList;
	}

	public int getCount() {
		return objList.size();
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

	public View getView(final int position, View convertView, ViewGroup parent) 
	{
		
		final ViewHolder HolderView;
		
		if(convertView == null)
		{
			HolderView = new ViewHolder();
			convertView = inflater.inflate(R.layout.project_list_row_file, null);

			HolderView.txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
			
			convertView.setTag(HolderView);
		}
		else
		{
			HolderView = (ViewHolder) convertView.getTag();
		}

		HolderView.txtTitle.setText(objList.get(position).Name);
		
		return convertView;
	}

	class ViewHolder
	{	
		TextView txtTitle;
	}

}
