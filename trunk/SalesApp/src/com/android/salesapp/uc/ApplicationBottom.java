package com.android.salesapp.uc;

import com.android.salesapp.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class ApplicationBottom extends LinearLayout {

	private Context mContext;
	
	public ApplicationBottom(Context context) {
		super(context);
		this.mContext = context;
		Initialization();
	}
	
	public ApplicationBottom(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
		Initialization();
	}
	
	private void Initialization(){
		
		View mView = LayoutInflater.from(mContext).inflate(R.layout.application_bottom,this,true);
//		mView.findViewById(R.id.);
	}
}
