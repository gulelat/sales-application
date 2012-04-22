package com.android.salesapp.ui;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.android.salesapp.BaseDashBoardActivity;
import com.android.salesapp.R;
import com.android.salesapp.adapter.ContactListAdapter;
import com.android.salesapp.bean.ProjectDetailBean;
import com.android.salesapp.bl.ProjectDetailBL;
import com.android.salesapp.uc.ApplicationTop;

public class ContactListActivity extends BaseDashBoardActivity
{
	private ListView ContactList;

	public static boolean objList[];
	
	private ProjectDetailBL objBL;
	
	private ArrayList<ProjectDetailBean> objContactList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contacts_list);
		
		objBL = new ProjectDetailBL();	
		objList = null;

		ContactList = (ListView) findViewById(R.id.ContactList);
		ContactList.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View view, int index, long arg3) 
			{
				Intent objIntent = new Intent(getApplicationContext(), ContactEditDetailsActivity.class);
				objIntent.putExtra("PROJECT_ID", String.valueOf(objContactList.get(index).ProjectID));
				startActivity(objIntent);
				finish();
			}
		});
		
		ApplicationTop objHeader = (ApplicationTop) findViewById(R.id.topbar);
		objHeader.setTitle("Contacts");
		objHeader.btnLeft.setVisibility(View.GONE);
		objHeader.btnRight.setVisibility(View.GONE);
		
		objContactList = objBL.ContactList();
		
		ContactList.setAdapter(new ContactListAdapter(ContactListActivity.this, objContactList));
	}
}