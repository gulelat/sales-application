package com.android.salesapp.ui;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.salesapp.BaseDashBoardActivity;
import com.android.salesapp.R;
import com.android.salesapp.bean.ProjectBean;
import com.android.salesapp.bean.ProjectDetailBean;
import com.android.salesapp.bean.ProjectDetailFieldBean;
import com.android.salesapp.bl.ProjectBL;
import com.android.salesapp.bl.ProjectDetailBL;
import com.android.salesapp.uc.ApplicationTop;

public class InputInfoActivity extends BaseDashBoardActivity implements OnClickListener{

	private ApplicationTop applicationTop;
	private ListView listview_inputInfoField;
	public ArrayList<ProjectDetailFieldBean> arrayListProjectDetails = ProjectNameActivity.arrayListFinal;
	private String strProjectName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inputinformation);

		strProjectName = getIntent().getExtras().getString("ProjeectName");
		applicationTop = (ApplicationTop) findViewById(R.id.view_inputinfo_topbar);
		applicationTop.setTitle("Input Information");
		applicationTop.setRightButtonTitle("Save");
		applicationTop.isSaveRight();
		applicationTop.btnLeft.setOnClickListener(this);
		applicationTop.btnRight.setOnClickListener(this);

		listview_inputInfoField = (ListView) findViewById(R.id.listview_inputInfoField);

	}

	@Override
	protected void onResume() {
		super.onResume();
		
		listview_inputInfoField.setAdapter(new DetailsAdapter(InputInfoActivity.this));
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
			// Save All info In to db
			
			new Thread(new Runnable() {
				
				public void run() {
					
					ProjectBean mBeanProject = new ProjectBean();
					mBeanProject.setName(strProjectName);
					int intProjectID = new ProjectBL().Insert(mBeanProject);
					System.out.print(" |***| Project ID  : |***| " + intProjectID);
					
					for (ProjectDetailFieldBean iterable_element : arrayListProjectDetails) {
						
						ProjectDetailBean mBeanPD = new ProjectDetailBean();
							
						// Project ID 
						mBeanPD.setProjectID(intProjectID);
						// Field ID Not Name 
						mBeanPD.setPDFID(iterable_element.getPDFID());
						//EditText Value 
						mBeanPD.setFieldValue(iterable_element.getEditValue());
						new ProjectDetailBL().Insert(mBeanPD);
						
					}
					
					
				}
			}).start();
			Intent mIntent = new Intent(InputInfoActivity.this,DashBoardActivity.class);
			mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(mIntent);
			finish();
			break;
		default:
			break;
		}
	}

	private class DetailsAdapter extends BaseAdapter {

		LayoutInflater inflaterLayout;

		public DetailsAdapter(Context context) {
			inflaterLayout = LayoutInflater.from(context);
		}

		public int getCount() {
			return arrayListProjectDetails.size();
		}

		public Object getItem(int arg0) {
			return null;
		}

		public long getItemId(int position) {
			return 0;
		}

		private class ViewHolder {

			private TextView txtView_row_inputInfo;
			private EditText editView_row_inputInfo ;
		}

		public View getView(final int position, View convertView, ViewGroup parent) {

			final ViewHolder HolderView;
			if (convertView == null) {

				HolderView = new ViewHolder();
				convertView = inflaterLayout.inflate(R.layout.row_input_info,
						null);
				HolderView.txtView_row_inputInfo = (TextView) convertView
						.findViewById(R.id.txtView_row_inputInfo);
				HolderView.editView_row_inputInfo = (EditText) convertView
						.findViewById(R.id.editView_row_inputInfo);
				convertView.setTag(HolderView);

			} else {
				
				HolderView = (ViewHolder) convertView.getTag();
			}

			HolderView.txtView_row_inputInfo.setText(arrayListProjectDetails.get(
					position).getFieldName());
			
			HolderView.editView_row_inputInfo.setText("");
			HolderView.editView_row_inputInfo.addTextChangedListener(new TextWatcher() {
				
				public void onTextChanged(CharSequence s, int start, int before, int count) {
				}
				
				public void beforeTextChanged(CharSequence s, int start, int count,
						int after) {
				}
				
				public void afterTextChanged(Editable s) {
					
					arrayListProjectDetails.get(position).setEditValue(HolderView.editView_row_inputInfo.getText().toString());
					
				}
			});

			return convertView;
		}

	}
	

}
