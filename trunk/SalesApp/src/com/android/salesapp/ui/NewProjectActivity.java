package com.android.salesapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.android.salesapp.BaseDashBoardActivity;
import com.android.salesapp.R;
import com.android.salesapp.uc.ApplicationTop;
import com.android.salesapp.utils.Const;

public class NewProjectActivity extends BaseDashBoardActivity implements OnClickListener{

	
	private EditText editText_newproject_name;
	private ApplicationTop applicationTop;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newproject_activity);
		Const.CONTEXT = NewProjectActivity.this;
		editText_newproject_name = (EditText) findViewById(R.id.editText_newproject_name);
		applicationTop = (ApplicationTop)findViewById(R.id.view_newproject_topbar);
		applicationTop.setTitle("");
		applicationTop.btnLeft.setText("Back");
		applicationTop.btnRight.setText("Next");
		applicationTop.btnLeft.setOnClickListener(this);
		applicationTop.btnRight.setOnClickListener(this);
		
	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnLeft:
			finish();
			break;
		case R.id.btnRight:
			
			if (!editText_newproject_name.getText().toString().equals("")) {

				Intent mIntent = new Intent(NewProjectActivity.this,
						ProjectNameActivity.class);
				mIntent.putExtra("ProjeectName", editText_newproject_name
						.getText().toString());
				startActivity(mIntent);

			}

			break;

		default:
			break;
		}
	}
}
