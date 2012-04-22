package com.android.salesapp.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.salesapp.BaseDashBoardActivity;
import com.android.salesapp.R;
import com.android.salesapp.bean.FollowUpCallBean;
import com.android.salesapp.bl.FollowUpCallBL;
import com.android.salesapp.uc.ApplicationTop;
import com.android.salesapp.utils.Const;
import com.android.salesapp.utils.DateTimePicker;
import com.android.salesapp.utils.Log;
import com.android.salesapp.utils.Utils;
import com.android.salesapp.utils.DateTimePicker.DialogType;

public class CallCampaignActivity extends BaseDashBoardActivity implements OnClickListener
{
	private EditText txtDate;
	private EditText txtTime;

	private TextView txtReminder;

	private FollowUpCallBL objBL;
	
	private DateTimePicker objDateTimePicker;
	
	private int ReminderIndex;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.call_campaign);
		
		objBL = new FollowUpCallBL();		
		ReminderIndex = 0;
		
		txtDate = (EditText) findViewById(R.id.txtDate);
		txtTime = (EditText) findViewById(R.id.txtTime);
		
		txtReminder = (TextView) findViewById(R.id.txtReminder);
		txtReminder.setText(Const.ReminderName[ReminderIndex]);
		
		ApplicationTop objHeader = (ApplicationTop) findViewById(R.id.topbar);
		objHeader.setTitle("Call Campaign");
		objHeader.isSaveLeft();
		objHeader.isSaveRight();
		objHeader.setLeftButtonTitle("");
		objHeader.setRightButtonTitle("+");
		objHeader.btnLeft.setBackgroundResource(R.drawable.ic_btn_call);
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
		txtReminder.setOnClickListener(this);
	}

	public void onClick(View v) 
	{
		switch (v.getId()) 
		{
			case R.id.txtDate:

		    	objDateTimePicker = new DateTimePicker(CallCampaignActivity.this, txtDate);
				if(!txtDate.getText().toString().equals(""))
				{
					String str[] = txtDate.getText().toString().split("/");
					objDateTimePicker.setDate(Integer.parseInt(str[0]), Integer.parseInt(str[1]) - 1, Integer.parseInt(str[2]));
				}
				objDateTimePicker.showDateTimePicker(DialogType.DATE_DIALOG_ID).show();
				break;

			case R.id.txtTime:

		    	objDateTimePicker = new DateTimePicker(CallCampaignActivity.this, txtTime);
				if(!txtTime.getText().toString().equals(""))
				{
					String str[] = txtTime.getText().toString().split(":");
					objDateTimePicker.setTime(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
				}
				objDateTimePicker.showDateTimePicker(DialogType.TIME_DIALOG_ID).show();
				break;

			case R.id.txtReminder:
				try
				{
					AlertDialog.Builder builder = new AlertDialog.Builder(CallCampaignActivity.this);
						
					builder.setTitle("Reminder");
	
					builder.setSingleChoiceItems(Const.ReminderName, ReminderIndex,
							new DialogInterface.OnClickListener() 
							{	
								public void onClick(DialogInterface dialog, int index) 
								{
									ReminderIndex = index;
									txtReminder.setText(Const.ReminderName[index]);
									dialog.dismiss();
								}
							});
	
					builder.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() 
							{
								public void onClick(DialogInterface dialog, int id) 
								{
									dialog.dismiss();
								}
							});
	
					AlertDialog alert = builder.create();
					alert.show();
				}
				catch (Exception e) 
				{
					Log.error(this.getClass()+" :: setOnClickListener", e);
					e.printStackTrace();
				}

				break;
		}
	}
	
	private boolean SaveData()
	{
		if(txtDate.getText().toString().equals(""))
		{
			Toast.makeText(CallCampaignActivity.this, "Please Select Date", Toast.LENGTH_LONG).show();
			return false;
		}
		if(txtTime.getText().toString().equals(""))
		{
			Toast.makeText(CallCampaignActivity.this, "Please Select Time", Toast.LENGTH_LONG).show();
			return false;
		}
		
		FollowUpCallBean objBean = new FollowUpCallBean();
		objBean.ProjectID = 1;
		objBean.Date = String.valueOf(Utils.dateToMilisec(txtDate.getText().toString(), "dd/MM/yyyy"));
		objBean.Time = String.valueOf(Utils.dateToMilisec(txtTime.getText().toString(), "HH:mm"));
		objBean.Reminder = ReminderIndex;
		objBL.Insert(objBean);
		
		if(FollowUpActivity.FollowUp != null)
				FollowUpActivity.FollowUp[2] = 0;
		
		return true;
	}
}