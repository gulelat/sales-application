package com.android.salesapp.bl;

import java.util.ArrayList;

import android.database.Cursor;

import com.android.salesapp.bean.AnswereBean;
import com.android.salesapp.db.DataAccess;
import com.android.salesapp.utils.Log;

public class AnswereBL 
{
	public ArrayList<AnswereBean> List(AnswereBean objBean)
	{
		DataAccess objDataAccess = new DataAccess();
		StringBuilder Query = new StringBuilder();
		ArrayList<AnswereBean> objList = new ArrayList<AnswereBean>();
		Cursor objCursor = null;
		
		try {

			if(objBean.AnsID == 0)
				Query.append("SELECT ")
				 	 .append("AnsID, AnsName ")
				 	 .append("FROM Answere ORDER BY AnsID");
			else
				Query.append("SELECT ")
			 	 	 .append("AnsID, AnsName ")
					 .append("FROM Answere ")
					 .append("WHERE AnsID = ")
					 .append(objBean.AnsID);

			objCursor = objDataAccess.query(Query.toString());
			objCursor.moveToPosition(-1);
			
			while (objCursor.moveToNext()) {
				
				AnswereBean objBookList = new AnswereBean();
				
				objBookList.AnsID = objCursor.getInt(0);
				objBookList.AnsName = objCursor.getString(1);
				
				objList.add(objBookList);
			}
			
		} catch (Exception e) {
			Log.error(this.getClass() + " :: List() ", e);
		} finally {
			objDataAccess = null;
			Query = null;
			objCursor.close();
			objCursor = null;
		}
		
		return objList;
	}
	
	public void Insert(AnswereBean objBean)
	{
		DataAccess objDataAccess = new DataAccess();
		StringBuilder Query = new StringBuilder();
		try {

			Query.append("INSERT INTO ")
				 .append("Answere (AnsName) ")
				 .append("Values ( '")
				 .append(objBean.AnsName)
				 .append("')");
			
			objDataAccess.execute(Query.toString());		
			
		} catch (Exception e) {
			Log.error(this.getClass() + " :: Insert() ", e);
		} finally {
			objDataAccess = null;
			Query = null;
		}
	}
	
	public void Update(AnswereBean objBean)
	{
		DataAccess objDataAccess = new DataAccess();
		StringBuilder Query = new StringBuilder();
		
		try {
			
			Query.append("UPDATE Answere ")
				 .append("SET ")			
				 .append("AnsName = '")
				 .append(objBean.AnsName)
				 .append("' WHERE AnsID = ")
				 .append(objBean.AnsID);
			
			objDataAccess.execute(Query.toString());
			
		} catch (Exception e) {
			Log.error(this.getClass() + " :: Update() ", e);
			e.getStackTrace();
		} finally {
			objDataAccess = null;
			Query = null;
		}
	}
	
	public void Delete(int ID)
	{
		DataAccess objDataAccess = new DataAccess();
		
		try {
			objDataAccess.execute("DELETE FROM Answere WHERE AnsID = " + ID);
			
		} catch (Exception e) {
			Log.error(this.getClass() + " :: Delete() ", e);
		} finally {
			objDataAccess = null;
		}
	}
}
