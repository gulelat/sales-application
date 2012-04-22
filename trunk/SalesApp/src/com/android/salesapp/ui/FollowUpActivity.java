package com.android.salesapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.salesapp.BaseDashBoardActivity;
import com.android.salesapp.R;
import com.android.salesapp.uc.ApplicationTop;

public class FollowUpActivity extends BaseDashBoardActivity implements OnClickListener
{
	private ImageView chkEmail;
	private ImageView chkTextMessage;
	private ImageView chkCall;
	
	public static int FollowUp[] = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.followup);

		ApplicationTop objHeader = (ApplicationTop) findViewById(R.id.topbar);
		objHeader.setTitle("Follow Up");
		objHeader.btnLeft.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) 
			{
				finish();
			}
		});
		
		objHeader.btnRight.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) 
			{
				if(FollowUp[0] == 0 && FollowUp[1] == 0 && FollowUp[2] == 0)
				{
					Toast.makeText(FollowUpActivity.this, "Please Select Atlist One Information", Toast.LENGTH_LONG).show();
					return;
				}
				
				if(FollowUp[0] == 1)
					startActivity(new Intent(getApplicationContext(), EmailCampaignActivity.class));
				else if(FollowUp[1] == 1)
					startActivity(new Intent(getApplicationContext(), TextMessageCampaign.class));
				else if(FollowUp[2] == 1)
					startActivity(new Intent(getApplicationContext(), CallCampaignActivity.class));
				finish();
			}
		});
		
		FollowUp = new int[]{0, 0, 0};

		chkEmail = (ImageView) findViewById(R.id.chkEmail);
		chkTextMessage = (ImageView) findViewById(R.id.chkTextMessage);
		chkCall = (ImageView) findViewById(R.id.chkCall);

		chkEmail.setOnClickListener(this);
		chkTextMessage.setOnClickListener(this);
		chkCall.setOnClickListener(this);
	}

	public void onClick(View v) 
	{
		if(v.getTag().equals("0"))
		{					
			v.setTag("1");
			v.setBackgroundResource(R.drawable.ic_ckbox_check);
			switch (v.getId()) 
			{
				case R.id.chkEmail:
					FollowUp[0] = 1;
					break;
				case R.id.chkTextMessage:
					FollowUp[1] = 1;					
					break;
				case R.id.chkCall:
					FollowUp[2] = 1;					
					break;
			}
		}
		else if(v.getTag().equals("1"))
		{
			v.setTag("0");
			v.setBackgroundResource(R.drawable.ic_ckbox_uncheck);
			switch (v.getId()) 
			{
				case R.id.chkEmail:
					FollowUp[0] = 0;
					break;
				case R.id.chkTextMessage:
					FollowUp[1] = 0;					
					break;
				case R.id.chkCall:
					FollowUp[2] = 0;					
					break;
			}
		}
	}
}