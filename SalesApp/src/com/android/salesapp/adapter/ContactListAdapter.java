package com.android.salesapp.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.salesapp.R;
import com.android.salesapp.bean.ProjectDetailBean;
import com.android.salesapp.ui.ContactListActivity;

public class ContactListAdapter extends BaseAdapter 
{
	ArrayList<ProjectDetailBean>  objList;
	LayoutInflater inflater;
	
	public ContactListAdapter(Context context, ArrayList<ProjectDetailBean> _objList)
	{
		inflater = LayoutInflater.from(context);
		objList = _objList;
		ContactListActivity.objList = new boolean[objList.size()];
		for(int index=0; index<ContactListActivity.objList.length; index++)
		{
			ContactListActivity.objList[index] = false;
		}
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
			convertView = inflater.inflate(R.layout.contacts_list_row_file, null);

			HolderView.txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
			HolderView.chkBox = (ImageView) convertView.findViewById(R.id.chkBox);
			
			convertView.setTag(HolderView);
		}
		else
		{
			HolderView = (ViewHolder) convertView.getTag();
		}

		HolderView.txtTitle.setText(objList.get(position).FieldValue);
		if(HolderView.chkBox.getTag().equals("0"))
			HolderView.chkBox.setBackgroundResource(R.drawable.ic_ckbox_uncheck);
		else
			HolderView.chkBox.setBackgroundResource(R.drawable.ic_ckbox_check);
		
		HolderView.chkBox.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v)
			{
				if(HolderView.chkBox.getTag().equals("0"))
				{					
					ContactListActivity.objList[position] = true;
					HolderView.chkBox.setTag("1");
					HolderView.chkBox.setBackgroundResource(R.drawable.ic_ckbox_check);
				}
				else if(HolderView.chkBox.getTag().equals("1"))
				{
					ContactListActivity.objList[position] = false;
					HolderView.chkBox.setTag("0");
					HolderView.chkBox.setBackgroundResource(R.drawable.ic_ckbox_uncheck);
				}
			}
		});
		
		return convertView;
	}

	class ViewHolder
	{	
		TextView txtTitle;
		ImageView chkBox;
	}

}
