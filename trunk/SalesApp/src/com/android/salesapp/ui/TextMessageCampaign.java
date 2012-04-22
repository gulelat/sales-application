package com.android.salesapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.android.salesapp.BaseDashBoardActivity;
import com.android.salesapp.R;
import com.android.salesapp.bean.FollowUpMessageBean;
import com.android.salesapp.bl.FollowUpMessageBL;
import com.android.salesapp.uc.ApplicationTop;
import com.android.salesapp.utils.DateTimePicker;
import com.android.salesapp.utils.Utils;
import com.android.salesapp.utils.DateTimePicker.DialogType;

public class TextMessageCampaign extends BaseDashBoardActivity implements OnClickListener
{
	private EditText txtDate;
	private EditText txtTime;
	private EditText txtMessage;

	private FollowUpMessageBL objBL;
	
	private DateTimePicker objDateTimePicker;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.textmessage_campaign);
		
		objBL = new FollowUpMessageBL();		
		
		txtDate = (EditText) findViewById(R.id.txtDate);
		txtTime = (EditText) findViewById(R.id.txtTime);
		txtMessage = (EditText) findViewById(R.id.txtMessage);
		
		ApplicationTop objHeader = (ApplicationTop) findViewById(R.id.topbar);
		objHeader.setTitle("Text Message Campaign");
		objHeader.isSaveLeft();
		objHeader.isSaveRight();
		objHeader.setLeftButtonTitle("Save");
		objHeader.setRightButtonTitle("Send");
		objHeader.btnLeft.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) 
			{
				if(SaveData())
					finish();
			}
		});
		
		objHeader.btnRight.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) 
			{
				if(SaveData())
					finish();
			}
		});

		txtDate.setOnClickListener(this);
		txtTime.setOnClickListener(this);
	}

	public void onClick(View v) 
	{
		switch (v.getId()) 
		{
			case R.id.txtDate:

		    	objDateTimePicker = new DateTimePicker(TextMessageCampaign.this, txtDate);
				if(!txtDate.getText().toString().equals(""))
				{
					String str[] = txtDate.getText().toString().split("/");
					objDateTimePicker.setDate(Integer.parseInt(str[0]), Integer.parseInt(str[1]) - 1, Integer.parseInt(str[2]));
				}
				objDateTimePicker.showDateTimePicker(DialogType.DATE_DIALOG_ID).show();
				break;

			case R.id.txtTime:

		    	objDateTimePicker = new DateTimePicker(TextMessageCampaign.this, txtTime);
				if(!txtTime.getText().toString().equals(""))
				{
					String str[] = txtTime.getText().toString().split(":");
					objDateTimePicker.setTime(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
				}
				objDateTimePicker.showDateTimePicker(DialogType.TIME_DIALOG_ID).show();
				break;
		}
	}
	
	private boolean SaveData()
	{
		if(txtDate.getText().toString().equals(""))
		{
			Toast.makeText(TextMessageCampaign.this, "Please Select Date", Toast.LENGTH_LONG).show();
			return false;
		}
		if(txtTime.getText().toString().equals(""))
		{
			Toast.makeText(TextMessageCampaign.this, "Please Select Time", Toast.LENGTH_LONG).show();
			return false;
		}
		if(txtMessage.getText().toString().equals(""))
		{
			Toast.makeText(TextMessageCampaign.this, "Please Enter Message", Toast.LENGTH_LONG).show();
			return false;
		}
		
		FollowUpMessageBean objBean = new FollowUpMessageBean();
		objBean.ProjectID = 1;
		objBean.Date = String.valueOf(Utils.dateToMilisec(txtDate.getText().toString(), "dd/MM/yyyy"));
		objBean.Time = String.valueOf(Utils.dateToMilisec(txtTime.getText().toString(), "HH:mm"));
		objBean.Message = txtMessage.getText().toString();
		objBL.Insert(objBean);

		if(FollowUpActivity.FollowUp != null)
		{
			if(FollowUpActivity.FollowUp[1] == 1)
			{
				FollowUpActivity.FollowUp[1] = 0;
				
				if(FollowUpActivity.FollowUp[2] == 1)
					startActivity(new Intent(getApplicationContext(), CallCampaignActivity.class));
			}
		}
		
		return true;
	}
}