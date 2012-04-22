package com.android.salesapp.bl;

import java.util.ArrayList;

import android.database.Cursor;

import com.android.salesapp.bean.FollowUpCallBean;
import com.android.salesapp.db.DataAccess;
import com.android.salesapp.utils.Log;

public class FollowUpCallBL 
{
	public ArrayList<FollowUpCallBean> List(FollowUpCallBean objBean)
	{
		DataAccess objDataAccess = new DataAccess();
		StringBuilder Query = new StringBuilder();
		ArrayList<FollowUpCallBean> objList = new ArrayList<FollowUpCallBean>();
		Cursor objCursor = null;
		
		try {

			if(objBean.FCID == 0)
				Query.append("SELECT ")
				 	 .append("FCID, ProjectID, Date, Time, Reminder ")
				 	 .append("FROM FollowUpCall ORDER BY ProjectID");
			else
				Query.append("SELECT ")
			 	 	 .append("FCID, ProjectID, Date, Time, Reminder ")
					 .append("FROM FollowUpCall ")
					 .append("WHERE FCID = ")
					 .append(objBean.FCID);

			objCursor = objDataAccess.query(Query.toString());
			objCursor.moveToPosition(-1);
			
			while (objCursor.moveToNext()) {
				
				FollowUpCallBean objBookList = new FollowUpCallBean();
				
				objBookList.FCID = objCursor.getInt(0);
				objBookList.ProjectID = objCursor.getInt(1);
				objBookList.Date = objCursor.getString(2);
				objBookList.Time = objCursor.getString(3);
				objBookList.Reminder = objCursor.getInt(4);
				
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
	
	public void Insert(FollowUpCallBean objBean)
	{
		DataAccess objDataAccess = new DataAccess();
		StringBuilder Query = new StringBuilder();
		try {

			Query.append("INSERT INTO ")
				 .append("FollowUpCall (ProjectID, Date, Time, Reminder) ")
				 .append("Values ( ")
				 .append(objBean.ProjectID)
				 .append(", '")
				 .append(objBean.Date)
				 .append("', '")
				 .append(objBean.Time)
				 .append("', ")
				 .append(objBean.Reminder)
				 .append(")");
			
			objDataAccess.execute(Query.toString());		
			
		} catch (Exception e) {
			Log.error(this.getClass() + " :: Insert() ", e);
		} finally {
			objDataAccess = null;
			Query = null;
		}
	}
	
	public void Update(FollowUpCallBean objBean)
	{
		DataAccess objDataAccess = new DataAccess();
		StringBuilder Query = new StringBuilder();
		
		try {
			
			Query.append("UPDATE FollowUpCall ")
				 .append("SET ")			
				 .append("ProjectID = ")
				 .append(objBean.ProjectID)
				 .append(", Date = '")
				 .append(objBean.Date)
				 .append("', Time = '")
				 .append(objBean.Time)
				 .append("', Reminder = ")
				 .append(objBean.Reminder)
				 .append(" WHERE FCID = ")
				 .append(objBean.FCID);
			
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
			objDataAccess.execute("DELETE FROM FollowUpCall WHERE FCID = " + ID);
			
		} catch (Exception e) {
			Log.error(this.getClass() + " :: Delete() ", e);
		} finally {
			objDataAccess = null;
		}
	}
}
