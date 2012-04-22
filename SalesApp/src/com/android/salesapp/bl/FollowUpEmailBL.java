package com.android.salesapp.bl;

import java.util.ArrayList;

import android.database.Cursor;

import com.android.salesapp.bean.FollowUpEmailBean;
import com.android.salesapp.db.DataAccess;
import com.android.salesapp.utils.Log;

public class FollowUpEmailBL 
{
	public ArrayList<FollowUpEmailBean> List(FollowUpEmailBean objBean)
	{
		DataAccess objDataAccess = new DataAccess();
		StringBuilder Query = new StringBuilder();
		ArrayList<FollowUpEmailBean> objList = new ArrayList<FollowUpEmailBean>();
		Cursor objCursor = null;
		
		try {

			if(objBean.FEID == 0)
				Query.append("SELECT ")
				 	 .append("FEID, ProjectID, Date, Time, Subject, Body ")
				 	 .append("FROM FollowUpEmail ORDER BY ProjectID");
			else
				Query.append("SELECT ")
			 	 	 .append("FEID, ProjectID, Date, Time, Subject, Body ")
					 .append("FROM FollowUpEmail ")
					 .append("WHERE FEID = ")
					 .append(objBean.FEID);

			objCursor = objDataAccess.query(Query.toString());
			objCursor.moveToPosition(-1);
			
			while (objCursor.moveToNext()) {
				
				FollowUpEmailBean objBookList = new FollowUpEmailBean();
				
				objBookList.FEID = objCursor.getInt(0);
				objBookList.ProjectID = objCursor.getInt(1);
				objBookList.Date = objCursor.getString(2);
				objBookList.Time = objCursor.getString(3);
				objBookList.Subject = objCursor.getString(4);
				objBookList.Body = objCursor.getString(5);
				
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
	
	public void Insert(FollowUpEmailBean objBean)
	{
		DataAccess objDataAccess = new DataAccess();
		StringBuilder Query = new StringBuilder();
		try {

			Query.append("INSERT INTO ")
				 .append("FollowUpEmail (ProjectID, Date, Time, Subject, Body) ")
				 .append("Values ( ")
				 .append(objBean.ProjectID)
				 .append(", '")
				 .append(objBean.Date)
				 .append("', '")
				 .append(objBean.Time)
				 .append("', '")
				 .append(objBean.Subject)
				 .append("', '")
				 .append(objBean.Body)
				 .append("')");
			
			objDataAccess.execute(Query.toString());		
			
		} catch (Exception e) {
			Log.error(this.getClass() + " :: Insert() ", e);
		} finally {
			objDataAccess = null;
			Query = null;
		}
	}
	
	public void Update(FollowUpEmailBean objBean)
	{
		DataAccess objDataAccess = new DataAccess();
		StringBuilder Query = new StringBuilder();
		
		try {
			
			Query.append("UPDATE FollowUpEmail ")
				 .append("SET ")			
				 .append("ProjectID = ")
				 .append(objBean.ProjectID)
				 .append(", Date = '")
				 .append(objBean.Date)
				 .append("', Time = '")
				 .append(objBean.Time)
				 .append("', Subject = '")
				 .append(objBean.Subject)
				 .append("', Body = '")
				 .append(objBean.Body)
				 .append("' WHERE FEID = ")
				 .append(objBean.FEID);
			
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
			objDataAccess.execute("DELETE FROM FollowUpEmail WHERE FEID = " + ID);
			
		} catch (Exception e) {
			Log.error(this.getClass() + " :: Delete() ", e);
		} finally {
			objDataAccess = null;
		}
	}
}
