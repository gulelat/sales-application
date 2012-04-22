package com.android.salesapp.ui;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.salesapp.BaseDashBoardActivity;
import com.android.salesapp.R;
import com.android.salesapp.bean.AnswereBean;
import com.android.salesapp.bean.QuetionBean;
import com.android.salesapp.bl.AnswereBL;
import com.android.salesapp.uc.ApplicationTop;
import com.android.salesapp.utils.Const;

public class QualifierActivity extends BaseDashBoardActivity implements OnClickListener{
	
	private ApplicationTop applicationTop;
	private ListView listview_qualifier;
	public ArrayList<AnswereBean> arrayList_Answer = null;
	
	private Button btn_qualifier_plus;
	private int intAnsID = -1;
	private EditText editText_Question;
	
	public ArrayList<QuetionBean> arryList_Question = new ArrayList<QuetionBean>();
	public static ArrayList<QuetionBean> arryList_TempQuestion = new ArrayList<QuetionBean>();
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qualifier);
		Const.CONTEXT = QualifierActivity.this;
		
		applicationTop = (ApplicationTop)findViewById(R.id.view_qualifier_topbar);
		applicationTop.setTitle("Qualifier");
		applicationTop.btnLeft.setText("Back");
		applicationTop.btnRight.setText("Next");
		applicationTop.btnLeft.setOnClickListener(this);
		applicationTop.btnRight.setOnClickListener(this);
		
		btn_qualifier_plus = (Button)findViewById(R.id.btn_qualifier_plus);
		btn_qualifier_plus.setText("+");
		btn_qualifier_plus.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				System.out.println(" [ Add Question  ] ");
				
				if(intAnsID != -1 && editText_Question != null && !editText_Question.getText().toString().equals("")){
					
					QuetionBean mBean = new QuetionBean();
					mBean.setQuetion(editText_Question.getText().toString().trim());
					mBean.setAnsID(intAnsID);
					arryList_TempQuestion.add(mBean);
					
					editText_Question.setText("");
					intAnsID = -1;
					
					AnswereBean mAnswerBean = new AnswereBean();
					mAnswerBean.setAnsID(0);
					arrayList_Answer = new AnswereBL().List(mAnswerBean);
					String arryField[] = new String[arrayList_Answer.size()];
					int index = 0;
					for (AnswereBean obj : arrayList_Answer) {
						arryField[index] = obj.getAnsName();
						index++;
					}

					listview_qualifier.setAdapter(new ArrayAdapter<String>(
							QualifierActivity.this,
							android.R.layout.simple_list_item_single_choice,
							arryField));
					listview_qualifier
							.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
					Toast.makeText(QualifierActivity.this,
							"Question add successfully !!!", Toast.LENGTH_SHORT)
							.show();
						
				}
				else{
					
					Toast.makeText(QualifierActivity.this,"Please select/type requied field !!!",Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		editText_Question = (EditText)findViewById(R.id.editText_Question);
		
		listview_qualifier = (ListView)findViewById(R.id.listview_qualifier);
		
		
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		if(arrayList_Answer == null){
			
			AnswereBean mBean = new AnswereBean();
			mBean.setAnsID(0);
			arrayList_Answer = new AnswereBL().List(mBean);
			String arryField [] = new String [arrayList_Answer.size()];
			int index = 0;
			for (AnswereBean obj : arrayList_Answer) {
				arryField[index] = obj.getAnsName();
				index++;
			}
			
			listview_qualifier.setAdapter(new ArrayAdapter<String>(QualifierActivity.this,android.R.layout.simple_list_item_single_choice,arryField));
			listview_qualifier.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			listview_qualifier.setOnItemClickListener(new OnItemClickListener() {

				public void onItemClick(AdapterView<?> arg0, View v,
						int position, long id) {
					
					intAnsID = arrayList_Answer.get(position).getAnsID();
					System.out.println(" [ SELECT ID ] " + intAnsID);

					
				}
			});
			
		}
		
		
		
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
			startActivity(new Intent(QualifierActivity.this,PreviewQualifierActivity.class));
			break;
		case R.id.btnLeft:
			finish();
			break;
			
			
		default:
			break;
		}
	}

	
	
	
}
