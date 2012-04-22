package com.android.salesapp.ui;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.salesapp.BaseDashBoardActivity;
import com.android.salesapp.R;
import com.android.salesapp.bean.QuetionBean;
import com.android.salesapp.bl.QuetionBL;
import com.android.salesapp.uc.ApplicationTop;
import com.android.salesapp.utils.Const;

public class PreviewQualifierActivity extends BaseDashBoardActivity implements OnClickListener{

	
	
	public ArrayList<QuetionBean> arrayList_TempQuestion = null;
	public ListView listview_preview_question;
	private ApplicationTop applicationTop;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qualifier_preview);
		Const.CONTEXT = PreviewQualifierActivity.this;
		
		applicationTop = (ApplicationTop)findViewById(R.id.view_qualifier_topbar);
		applicationTop.setTitle("Qualifier");
		applicationTop.btnLeft.setText("Back");
		applicationTop.btnRight.setText("Save");
		applicationTop.isSaveRight();
		applicationTop.btnLeft.setOnClickListener(this);
		applicationTop.btnRight.setOnClickListener(this);
		
		listview_preview_question = (ListView)findViewById(R.id.listview_preview_question);
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		arrayList_TempQuestion = QualifierActivity.arryList_TempQuestion;
		if(arrayList_TempQuestion != null)
		listview_preview_question.setAdapter(new QuesionPreviewAdapter(PreviewQualifierActivity.this));
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
		
		case R.id.btnRight:
			if(arrayList_TempQuestion != null){
				
				
				for (QuetionBean  objQuestion : arrayList_TempQuestion) {
					new QuetionBL().Insert(objQuestion);
				}
				Toast.makeText(PreviewQualifierActivity.this,"New Question is added !!!", Toast.LENGTH_LONG).show();
			}
			
			break;
		case R.id.btnLeft:
			finish();
			break;
		default:
			break;
		}
	}

	private class QuesionPreviewAdapter extends BaseAdapter {

		LayoutInflater inflaterLayout;

		public QuesionPreviewAdapter(Context context) {
			inflaterLayout = LayoutInflater.from(context);
		}
		
		public int getCount() {
			return arrayList_TempQuestion.size();
		}

		public Object getItem(int arg0) {
			return null;
		}

		public long getItemId(int position) {
			return 0;
		}
		

		private class ViewHolder {

			private TextView txtView_question;
			private ImageView imageView_delete;
		}

		
		public View getView(final int position, View convertView, ViewGroup parent) {

			final ViewHolder HolderView;
			if (convertView == null) {

				HolderView = new ViewHolder();
				if(arrayList_TempQuestion.get(position).getAnsID() == 1){
					
					convertView = inflaterLayout.inflate(R.layout.row_question_checkbox, null);
				}
				if(arrayList_TempQuestion.get(position).getAnsID() == 2){
					
					convertView = inflaterLayout.inflate(R.layout.row_question_textbox, null);
				}
				if(arrayList_TempQuestion.get(position).getAnsID() == 3){
					
					convertView = inflaterLayout.inflate(R.layout.row_question_radiogroup, null);
				}
				HolderView.txtView_question = (TextView)convertView.findViewById(R.id.txtView_question);
				HolderView.imageView_delete = (ImageView)convertView.findViewById(R.id.imageView_delete);
				
			} else {
				HolderView = (ViewHolder) convertView.getTag();
			}
			
			
			if(arrayList_TempQuestion.size() > 0){
				
				HolderView.txtView_question.setText(arrayList_TempQuestion.get(position).getQuetion());
				HolderView.imageView_delete.setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						
						arrayList_TempQuestion.remove(position);
						mHandler.sendEmptyMessage(1);
					}
				});
			}
			return convertView;
		}

	}
	
	private Handler mHandler = new Handler(){
	
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				if(msg.what == 1){
					listview_preview_question.setAdapter(new QuesionPreviewAdapter(PreviewQualifierActivity.this));
				}
			}
	};
}
