package com.android.salesapp.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.salesapp.R;
import com.android.salesapp.bean.ProjectDetailBean;
import com.android.salesapp.bl.ProjectDetailBL;

public class ContactDetailListAdapter extends BaseAdapter 
{
	ArrayList<ProjectDetailBean>  objList;
	LayoutInflater inflater;
	Context objContext;
	
	public ContactDetailListAdapter(Context context, ArrayList<ProjectDetailBean> _objList)
	{
		objContext = context;
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
			convertView = inflater.inflate(R.layout.contacts_details_edit_row_file, null);

			HolderView.txtFieldName = (TextView) convertView.findViewById(R.id.txtCaption);
			HolderView.txtValue = (EditText) convertView.findViewById(R.id.txtValue);
			HolderView.btnEdit = (ImageView) convertView.findViewById(R.id.btnEdit);
			HolderView.btnDelete = (ImageView) convertView.findViewById(R.id.btnDelete);
			
			convertView.setTag(HolderView);
		}
		else
		{
			HolderView = (ViewHolder) convertView.getTag();
		}

		HolderView.txtFieldName.setText(objList.get(position).FieldName);
		HolderView.txtValue.setText(objList.get(position).FieldValue);

		HolderView.btnEdit.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v)
			{
				ProjectDetailBL objBL = new ProjectDetailBL();
				ProjectDetailBean objBean = objList.get(position);
				objBean.FieldValue = HolderView.txtValue.getText().toString();
				objBL.Update(objBean);
				Toast.makeText(objContext, "Contact Detail Edit Successfuly", Toast.LENGTH_LONG).show();
			}
		});
		HolderView.btnDelete.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v)
			{
				ProjectDetailBL objBL = new ProjectDetailBL();
				objBL.Delete(objList.get(position).PDID);
				objList.remove(position);
				notifyDataSetChanged();
			}
		});
		
		return convertView;
	}

	class ViewHolder
	{	
		TextView txtFieldName;
		EditText txtValue;
		ImageView btnEdit;
		ImageView btnDelete;
	}

}
