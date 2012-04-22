package com.android.salesapp.ui;

import java.util.Date;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.salesapp.BaseDashBoardActivity;
import com.android.salesapp.R;
import com.android.salesapp.bl.ActivityReportBL;
import com.android.salesapp.uc.ApplicationTop;
import com.android.salesapp.utils.DateTimePicker;
import com.android.salesapp.utils.Utils;
import com.android.salesapp.utils.DateTimePicker.DialogType;

public class ReportActivity extends BaseDashBoardActivity implements OnClickListener
{
	private EditText txtFrom;
	private EditText txtTo;

	private ImageView chkToDay;
	
	private TextView txtEmailSend;
	private TextView txtSMSSend;
	
	private RelativeLayout ShowDetailFrame;
	private LinearLayout DetailFrame;
	
	private ActivityReportBL objBL;
	
	private DateTimePicker objDateTimePicker;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report);
		
		objBL = new ActivityReportBL();		

		txtFrom = (EditText) findViewById(R.id.txtFrom);
		txtTo = (EditText) findViewById(R.id.txtTo);
		
		txtEmailSend = (TextView) findViewById(R.id.txtEmailSend);
		txtSMSSend = (TextView) findViewById(R.id.txtSMSSend);
		
		DetailFrame = (LinearLayout) findViewById(R.id.DetailFrame);
		
		ShowDetailFrame = (RelativeLayout) findViewById(R.id.ShowDetailFrame);
		ShowDetailFrame.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) 
			{
				if(ShowDetailFrame.getTag().equals("0"))
				{
					ShowDetailFrame.setTag("1");
					DetailFrame.setVisibility(View.GONE);
				}
				else if(ShowDetailFrame.getTag().equals("1"))
				{
					ShowDetailFrame.setTag("0");
					DetailFrame.setVisibility(View.VISIBLE);
				}
			}
		});
		
		ApplicationTop objHeader = (ApplicationTop) findViewById(R.id.topbar);
		objHeader.setTitle("Activity Report");
		objHeader.btnLeft.setVisibility(View.GONE);
		objHeader.btnRight.setVisibility(View.GONE);
		

		chkToDay = (ImageView) findViewById(R.id.chkToDay);
		chkToDay.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v)
			{
				if(chkToDay.getTag().equals("0"))
				{					
					String str = Utils.convertDateToString(new Date(), "dd/MM/yyyy");

					txtFrom.setText(str);
					txtTo.setText(str);
					
					String strCount[] = objBL.Count(str, str);
					txtEmailSend.setText(strCount[0]);
					txtSMSSend.setText(strCount[1]);
					
					chkToDay.setTag("1");
					chkToDay.setBackgroundResource(R.drawable.ic_ckbox_check);
				}
				else if(chkToDay.getTag().equals("1"))
				{
					String strCount[] = objBL.Count("", "");
					txtEmailSend.setText(strCount[0]);
					txtSMSSend.setText(strCount[1]);
					
					chkToDay.setTag("0");
					chkToDay.setBackgroundResource(R.drawable.ic_ckbox_uncheck);
				}
			}
		});
		
		String strCount[] = objBL.Count("", "");
		txtEmailSend.setText(strCount[0]);
		txtSMSSend.setText(strCount[1]);

		txtFrom.setOnClickListener(this);
		txtTo.setOnClickListener(this);

		txtFrom.addTextChangedListener(textChangeListner);
		txtTo.addTextChangedListener(textChangeListner);
	}

	TextWatcher textChangeListner = new TextWatcher() 
	{
		public void onTextChanged(CharSequence s, int start, int before, int count) 
		{
			String strCount[] = objBL.Count(txtFrom.getText().toString(), txtTo.getText().toString());
			txtEmailSend.setText(strCount[0]);
			txtSMSSend.setText(strCount[1]);
		}
		
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
		
		public void afterTextChanged(Editable s) {}
	};
	
	public void onClick(View v) 
	{
		if(chkToDay.getTag().equals("1"))
			return;
		
		switch (v.getId()) 
		{
			case R.id.txtFrom:

		    	objDateTimePicker = new DateTimePicker(ReportActivity.this, txtFrom);
				if(!txtFrom.getText().toString().equals(""))
				{
					String str[] = txtFrom.getText().toString().split("/");
					objDateTimePicker.setDate(Integer.parseInt(str[0]), Integer.parseInt(str[1]) - 1, Integer.parseInt(str[2]));
				}
				objDateTimePicker.showDateTimePicker(DialogType.DATE_DIALOG_ID).show();
				break;

			case R.id.txtTo:

		    	objDateTimePicker = new DateTimePicker(ReportActivity.this, txtTo);
				if(!txtTo.getText().toString().equals(""))
				{
					String str[] = txtTo.getText().toString().split("/");
					objDateTimePicker.setDate(Integer.parseInt(str[0]), Integer.parseInt(str[1]) - 1, Integer.parseInt(str[2]));
				}
				objDateTimePicker.showDateTimePicker(DialogType.DATE_DIALOG_ID).show();
				break;
		}
	}
}
