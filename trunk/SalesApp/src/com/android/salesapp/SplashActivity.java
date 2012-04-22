package com.android.salesapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.android.salesapp.ui.DashBoardActivity;
import com.android.salesapp.utils.Const;
import com.android.salesapp.utils.Utils;

public class SplashActivity extends Activity {
	
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spleash_activity);
        
        Const.CONTEXT = getApplicationContext();
		Utils.systemUpgrade();
        
        SplashHandler mHandler = new SplashHandler();
        Message msg = new Message();
        msg.what = 0;
        mHandler.sendMessageDelayed(msg,3000);
        
        
    }
    
    private class SplashHandler extends Handler{
    	
    	@Override
    	public void handleMessage(Message msg) {
    		super.handleMessage(msg);
    		switch (msg.what) {
			case 0:
				startActivity(new Intent(SplashActivity.this, DashBoardActivity.class));
				finish();
				break;

			default:
				break;
			}
    	}
    }
    
}