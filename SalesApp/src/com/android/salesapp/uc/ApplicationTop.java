package com.android.salesapp.uc;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.salesapp.R;

public class ApplicationTop extends LinearLayout{

	private Context mContext;

	public Button btnLeft;
	public Button btnRight;
	public TextView txtTitle;
	
	public ApplicationTop(Context context) 
	{
		super(context);
		this.mContext = context;
		Initialization();
	}
	
	public ApplicationTop(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
		this.mContext = context;
		Initialization();		
	}
	
	public void Initialization()
	{
		View mView = LayoutInflater.from(mContext).inflate(R.layout.application_top,this,true);
		txtTitle = (TextView) mView.findViewById(R.id.txtTitle);
		btnLeft = (Button) mView.findViewById(R.id.btnLeft);
		btnRight = (Button) mView.findViewById(R.id.btnRight);

		btnLeft.setText("Back");
		btnRight.setText("Next");
	}
	
	public void isSaveLeft()
	{
		btnLeft.setBackgroundResource(R.drawable.ic_btn_plus);
	}
	
	public void isSaveRight()
	{
		btnRight.setBackgroundResource(R.drawable.ic_btn_plus);	
	}
	
	public void setLeftButtonTitle(String title)
	{
		btnLeft.setText(title);
	}

	public void setTitle(String title)
	{
		txtTitle.setText(title);
	}
	
	public void setRightButtonTitle(String title)
	{
		btnRight.setText(title);
	}
	
}
