package com.android.salesapp.ui;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.android.salesapp.BaseDashBoardActivity;
import com.android.salesapp.R;
import com.android.salesapp.adapter.ProjectListAdapter;
import com.android.salesapp.bean.ProjectBean;
import com.android.salesapp.bl.ProjectBL;
import com.android.salesapp.uc.ApplicationTop;

public class ProjectListActivity extends BaseDashBoardActivity
{
	private ListView ProjectList;

	private ProjectBL objBL;
	private ProjectBean Bean;

	ArrayList<ProjectBean>  objList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.project_list);
		
		objBL = new ProjectBL();	

		ProjectList = (ListView) findViewById(R.id.ProjectList);
		ProjectList.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View view, int index, long arg3) 
			{
				Intent objIntent = new Intent(getApplicationContext(), NewProjectActivity.class);
				objIntent.putExtra("PROJECT_ID", String.valueOf(objList.get(index).ProjectID));
				startActivity(objIntent);
			}
		});
		
		ApplicationTop objHeader = (ApplicationTop) findViewById(R.id.topbar);
		objHeader.setTitle("Project");
		objHeader.btnLeft.setVisibility(View.GONE);
		objHeader.btnRight.setVisibility(View.GONE);
		
		Bean = new ProjectBean();
		Bean.ProjectID = 0;
		
		objList = objBL.List(Bean); 
		
		ProjectList.setAdapter(new ProjectListAdapter(ProjectListActivity.this, objList));
	}
}