package com.android.salesapp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.android.salesapp.BaseDashBoardActivity;
import com.android.salesapp.R;
import com.android.salesapp.adapter.ContactDetailListAdapter;
import com.android.salesapp.bl.ProjectDetailBL;
import com.android.salesapp.uc.ApplicationTop;

public class ContactEditDetailsActivity extends BaseDashBoardActivity
{
	private ListView ContactDetailList;

	private ProjectDetailBL objBL;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contacts_details_edit);
		
		objBL = new ProjectDetailBL();	

		ApplicationTop objHeader = (ApplicationTop) findViewById(R.id.topbar);
		objHeader.setTitle("Contacts Details");
		objHeader.btnLeft.setVisibility(View.GONE);
		objHeader.btnRight.setVisibility(View.GONE);
		
		ContactDetailList = (ListView) findViewById(R.id.ContactDetailList);
		ContactDetailList.setAdapter(new ContactDetailListAdapter(ContactEditDetailsActivity.this, objBL.ContactDetail(Integer.parseInt(getIntent().getStringExtra("PROJECT_ID")))));
	}
}