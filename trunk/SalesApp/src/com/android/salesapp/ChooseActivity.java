package com.android.salesapp;

import com.android.salesapp.utils.Const;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ChooseActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Const.CONTEXT = ChooseActivity.this;
        startActivity(new Intent(ChooseActivity.this, SplashActivity.class));
        finish();
	}
}
