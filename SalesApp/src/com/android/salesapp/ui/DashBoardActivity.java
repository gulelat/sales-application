package com.android.salesapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.android.salesapp.BaseDashBoardActivity;
import com.android.salesapp.R;
import com.android.salesapp.uc.ApplicationTop;

public class DashBoardActivity extends BaseDashBoardActivity implements OnClickListener{

	private int int_arrayButton[] = { R.id.btn_dashboard_NewProject,
			R.id.btn_dashboard_EditProject, R.id.btn_dashboard_CreateCampaign,
			R.id.btn_dashboard_ActivityReports, R.id.btn_dashboard_Help,
			R.id.btn_dashboard_EditCampaign, R.id.btn_dashboard_AddContact,
			R.id.btn_dashboard_SearchContacts,
			R.id.btn_dashboard_FollowUp };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard_activity);

		ApplicationTop objHeader = (ApplicationTop) findViewById(R.id.topbar);
		objHeader.setTitle("");
		objHeader.btnLeft.setVisibility(View.GONE);
		objHeader.btnRight.setVisibility(View.GONE);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		for(int i=0 ;i < int_arrayButton.length ; i++){
				 findViewById(int_arrayButton[i]).setOnClickListener(this);
		}
	}
	
	@Override
	protected void onPause() {
		super.onPause();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.btn_dashboard_NewProject:
			startActivity(new Intent(getApplicationContext(), NewProjectActivity.class));
			break;
		case R.id.btn_dashboard_EditProject:
			startActivity(new Intent(getApplicationContext(), ProjectListActivity.class));
			break;
		case R.id.btn_dashboard_CreateCampaign:
			startActivity(new Intent(getApplicationContext(), CreateCampaignActivity.class));
			break;
		case R.id.btn_dashboard_ActivityReports:
			startActivity(new Intent(getApplicationContext(), ReportActivity.class));
			break;
		case R.id.btn_dashboard_Help:
			startActivity(new Intent(getApplicationContext(), BirthDayAnniversaryActivity.class));
			break;
		case R.id.btn_dashboard_EditCampaign:
			startActivity(new Intent(getApplicationContext(), CallCampaignActivity.class));
			break;	
		case R.id.btn_dashboard_AddContact:
			startActivity(new Intent(getApplicationContext(),QualifierActivity.class));
			break;	
		case R.id.btn_dashboard_SearchContacts:
			startActivity(new Intent(getApplicationContext(),ContactListActivity.class));
			break;
			
		case R.id.btn_dashboard_FollowUp:
			startActivity(new Intent(getApplicationContext(),FollowUpActivity.class));
			break;
		default:
			break;
		}
	}
	
	
}
