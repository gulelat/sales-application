package com.android.salesapp.ui;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.android.salesapp.BaseDashBoardActivity;
import com.android.salesapp.R;
import com.android.salesapp.bean.FollowUpBirthDateBean;
import com.android.salesapp.bl.FollowUpBirthDateBL;
import com.android.salesapp.uc.ApplicationTop;
import com.android.salesapp.utils.DateTimePicker;
import com.android.salesapp.utils.Utils;
import com.android.salesapp.utils.DateTimePicker.DialogType;

public class BirthDayAnniversaryActivity extends BaseDashBoardActivity implements OnClickListener
{
	private EditText txtDate;
	private EditText txtMessage;

	private FollowUpBirthDateBL objBL;
	private FollowUpBirthDateBean objBean;
	
	private DateTimePicker objDateTimePicker;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.birthday_anniversary);
		
		objBL = new FollowUpBirthDateBL();		

		txtDate = (EditText) findViewById(R.id.txtDate);
		txtMessage = (EditText) findViewById(R.id.txtMessage);
		
		ApplicationTop objHeader = (ApplicationTop) findViewById(R.id.topbar);
		objHeader.setTitle("Birthday / Anniversary");
		objHeader.isSaveLeft();
		objHeader.isSaveRight();
		objHeader.setLeftButtonTitle("Save");
		objHeader.setRightButtonTitle("Send");
		objHeader.btnLeft.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) 
			{
				if(txtDate.getText().toString().equals(""))
				{
					Toast.makeText(BirthDayAnniversaryActivity.this, "Please Select Date", Toast.LENGTH_LONG).show();
					return;
				}
				if(txtMessage.getText().toString().equals(""))
				{
					Toast.makeText(BirthDayAnniversaryActivity.this, "Please Enter Message", Toast.LENGTH_LONG).show();
					return;
				}
				
				objBean = new FollowUpBirthDateBean();
				objBean.ProjectID = 1;
				objBean.Date = String.valueOf(Utils.dateToMilisec(txtDate.getText().toString(), "dd/MM/yyyy"));
				objBean.Body = txtMessage.getText().toString();
				objBL.Insert(objBean);
				finish();
			}
		});
		objHeader.btnRight.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) 
			{
				
			}
		});

		txtDate.setOnClickListener(this);
	}

	public void onClick(View v) 
	{
		objDateTimePicker = new DateTimePicker(BirthDayAnniversaryActivity.this, txtDate);
		if(!txtDate.getText().toString().equals(""))
		{
			String str[] = txtDate.getText().toString().split("/");
			objDateTimePicker.setDate(Integer.parseInt(str[0]), Integer.parseInt(str[1]) - 1, Integer.parseInt(str[2]));
		}
		objDateTimePicker.showDateTimePicker(DialogType.DATE_DIALOG_ID).show();
	}
}
