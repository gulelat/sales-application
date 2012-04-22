package com.android.salesapp.bl;

import java.util.ArrayList;

import android.database.Cursor;

import com.android.salesapp.bean.FollowUpMessageBean;
import com.android.salesapp.db.DataAccess;
import com.android.salesapp.utils.Log;

public class FollowUpMessageBL 
{
	public ArrayList<FollowUpMessageBean> List(FollowUpMessageBean objBean)
	{
		DataAccess objDataAccess = new DataAccess();
		StringBuilder Query = new StringBuilder();
		ArrayList<FollowUpMessageBean> objList = new ArrayList<FollowUpMessageBean>();
		Cursor objCursor = null;
		
		try {

			if(objBean.FMID == 0)
				Query.append("SELECT ")
				 	 .append("FMID, ProjectID, Date, Time, Message ")
				 	 .append("FROM FollowUpMessage ORDER BY ProjectID");
			else
				Query.append("SELECT ")
			 	 	 .append("FMID, ProjectID, Date, Time, Message ")
					 .append("FROM FollowUpMessage ")
					 .append("WHERE FMID = ")
					 .append(objBean.FMID);

			objCursor = objDataAccess.query(Query.toString());
			objCursor.moveToPosition(-1);
			
			while (objCursor.moveToNext()) {
				
				FollowUpMessageBean objBookList = new FollowUpMessageBean();
				
				objBookList.FMID = objCursor.getInt(0);
				objBookList.ProjectID = objCursor.getInt(1);
				objBookList.Date = objCursor.getString(2);
				objBookList.Time = objCursor.getString(3);
				objBookList.Message = objCursor.getString(4);
				
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
	
	public void Insert(FollowUpMessageBean objBean)
	{
		DataAccess objDataAccess = new DataAccess();
		StringBuilder Query = new StringBuilder();
		try {

			Query.append("INSERT INTO ")
				 .append("FollowUpMessage (ProjectID, Date, Time, Message) ")
				 .append("Values ( ")
				 .append(objBean.ProjectID)
				 .append(", '")
				 .append(objBean.Date)
				 .append("', '")
				 .append(objBean.Time)
				 .append("', '")
				 .append(objBean.Message)
				 .append("')");
			
			objDataAccess.execute(Query.toString());		
			
		} catch (Exception e) {
			Log.error(this.getClass() + " :: Insert() ", e);
		} finally {
			objDataAccess = null;
			Query = null;
		}
	}
	
	public void Update(FollowUpMessageBean objBean)
	{
		DataAccess objDataAccess = new DataAccess();
		StringBuilder Query = new StringBuilder();
		
		try {
			
			Query.append("UPDATE FollowUpMessage ")
				 .append("SET ")			
				 .append("ProjectID = ")
				 .append(objBean.ProjectID)
				 .append(", Date = '")
				 .append(objBean.Date)
				 .append("', Time = '")
				 .append(objBean.Time)
				 .append("', Message = '")
				 .append(objBean.Message)
				 .append("' WHERE FMID = ")
				 .append(objBean.FMID);
			
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
			objDataAccess.execute("DELETE FROM FollowUpMessage WHERE FMID = " + ID);
			
		} catch (Exception e) {
			Log.error(this.getClass() + " :: Delete() ", e);
		} finally {
			objDataAccess = null;
		}
	}
}
