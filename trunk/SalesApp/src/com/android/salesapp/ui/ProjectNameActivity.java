package com.android.salesapp.ui;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.salesapp.BaseDashBoardActivity;
import com.android.salesapp.R;
import com.android.salesapp.bean.ProjectDetailFieldBean;
import com.android.salesapp.bl.ProjectDetailFieldBL;
import com.android.salesapp.uc.ApplicationTop;

public class ProjectNameActivity extends BaseDashBoardActivity implements
		OnClickListener {

	private static final int DIALOG_TEXT_ENTRY = 1;
	
	
	private ApplicationTop applicationTop;
	private String strProjectName;
	private ArrayList<ProjectDetailFieldBean> arrayListProjectField = null;
	public static ArrayList<ProjectDetailFieldBean> arrayListFinal = null;
	private ListView listview_customfield;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.projectname);

		strProjectName = getIntent().getExtras().getString("ProjeectName");

		applicationTop = (ApplicationTop) findViewById(R.id.view_projectname_topbar);
		applicationTop.setTitle(strProjectName);
		applicationTop.isSaveLeft();
		applicationTop.setLeftButtonTitle("+");
		applicationTop.btnLeft.setOnClickListener(this);
		applicationTop.btnRight.setOnClickListener(this);
		
		listview_customfield = (ListView)findViewById(R.id.listview_customfield);
		
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		ProjectDetailFieldBean mBean = new ProjectDetailFieldBean();
		mBean.setPDFID(0);
		if(arrayListProjectField == null)
		arrayListProjectField = new ProjectDetailFieldBL().List(mBean);  
		listview_customfield.setAdapter(new FieldAdapter(ProjectNameActivity.this));
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
			showDialog(DIALOG_TEXT_ENTRY);
			break;
		case R.id.btnRight:
			
			if(arrayListProjectField != null){
				
				arrayListFinal = new ArrayList<ProjectDetailFieldBean>();
				for (ProjectDetailFieldBean iterable_element : arrayListProjectField) {
					
					System.out.println("|***| TAG  |****| ] " + iterable_element.TAG);
					if(iterable_element.TAG == 1){
						arrayListFinal.add(iterable_element);
					}
				}
			}

			Intent mIntent = new Intent(ProjectNameActivity.this,InputInfoActivity.class);
			mIntent.putExtra("ProjeectName", strProjectName);
			startActivity(mIntent);
			
			
			break;
		default:
			break;
		}
	}

	private class FieldAdapter extends BaseAdapter {

		LayoutInflater inflaterLayout;

		public FieldAdapter(Context context) {
			inflaterLayout = LayoutInflater.from(context);
		}
		
		public int getCount() {
			return arrayListProjectField.size();
		}

		public Object getItem(int arg0) {
			return null;
		}

		public long getItemId(int position) {
			return 0;
		}

		private class ViewHolder {

			private TextView vh_txtFieldName;
			private CheckBox ckbox;
		}

		
		public View getView(final int position, View convertView, ViewGroup parent) {

			final ViewHolder HolderView;
			if (convertView == null) {

				HolderView = new ViewHolder();
				convertView = inflaterLayout.inflate(R.layout.row_projectname, null);
				HolderView.vh_txtFieldName = (TextView) convertView.findViewById(R.id.txtView_row_fieldName);
				HolderView.ckbox = (CheckBox) convertView.findViewById(R.id.cbox_row_projectname);
				convertView.setTag(HolderView);

			} else {
				HolderView = (ViewHolder) convertView.getTag();
			}

			HolderView.vh_txtFieldName.setText(arrayListProjectField.get(position).getFieldName());
			HolderView.vh_txtFieldName.setTag(arrayListProjectField.get(position).getPDFID());
			HolderView.ckbox.setChecked(false);
			HolderView.ckbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					
					if(isChecked){
						arrayListProjectField.get(position).setTAG(1);
					}else{
						arrayListProjectField.get(position).setTAG(0);
					}
				}
			});

			return convertView;
		}

	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		
		switch (id) {
		
		case DIALOG_TEXT_ENTRY:
			
		    LayoutInflater factory = LayoutInflater.from(this);
		    final EditText editTextField;
            final View textEntryView = factory.inflate(R.layout.alert_dialog_text_entry, null);
            editTextField =(EditText)textEntryView.findViewById(R.id.editText_FieldValue);
            return new AlertDialog.Builder(ProjectNameActivity.this)
                .setTitle(R.string.alert_dialog_fieldName)
                .setView(textEntryView)
                .setPositiveButton(R.string.alert_dialog_btnSave, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    	
                    	ProjectDetailFieldBL blProjectField = new ProjectDetailFieldBL();
                    	ProjectDetailFieldBean mBean = new ProjectDetailFieldBean();
                    	mBean.setFieldName(editTextField.getText().toString());
                    	mBean.setPDFID(0);
                    	blProjectField.Insert(mBean);
                    	if(arrayListProjectField != null)
                    	arrayListProjectField.clear();
                    	arrayListProjectField = new ProjectDetailFieldBL().List(mBean);
                    	listview_customfield.setAdapter(new FieldAdapter(ProjectNameActivity.this));
                    	editTextField.setText("");
                	}
                })
                .setNegativeButton(R.string.alert_dialog_btnCancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    	dismissDialog(DIALOG_TEXT_ENTRY);
                    }
                })
                .create();
            
		default:
			break;
		}
		return null;
	}
}
