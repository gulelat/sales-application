package com.android.salesapp.ui;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.android.salesapp.BaseDashBoardActivity;
import com.android.salesapp.R;
import com.android.salesapp.bean.CampaignBean;
import com.android.salesapp.bl.CampaignBL;
import com.android.salesapp.uc.ApplicationTop;

public class CreateCampaignActivity extends BaseDashBoardActivity 
{
	private EditText txtCampaignName;
	
	private CampaignBL objBL;
	private CampaignBean objBean;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.campaign_create);
		
		objBL = new CampaignBL();		
		
		txtCampaignName = (EditText) findViewById(R.id.txtCampaignName);
		
		ApplicationTop objHeader = (ApplicationTop) findViewById(R.id.topbar);
		objHeader.setTitle("Create Campaign");
		objHeader.isSaveRight();
		objHeader.setRightButtonTitle("Save");
		objHeader.btnLeft.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) 
			{
				finish();
			}
		});
		objHeader.btnRight.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) 
			{
				if(txtCampaignName.getText().toString().trim().equals(""))
				{
					Toast.makeText(CreateCampaignActivity.this, "Please Enter Campaign Name", Toast.LENGTH_LONG).show();
					return;
				}
				
				objBean = new CampaignBean();
				objBean.CampID = 0;
				objBean.CampName = txtCampaignName.getText().toString();
				objBL.Insert(objBean);
				finish();
			}
		});
	}
	
}
