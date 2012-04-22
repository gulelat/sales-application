package com.android.salesapp.bl;

import java.util.ArrayList;

import android.database.Cursor;

import com.android.salesapp.bean.FollowUpBirthDateBean;
import com.android.salesapp.db.DataAccess;
import com.android.salesapp.utils.Log;

public class FollowUpBirthDateBL 
{
	public ArrayList<FollowUpBirthDateBean> List(FollowUpBirthDateBean objBean)
	{
		DataAccess objDataAccess = new DataAccess();
		StringBuilder Query = new StringBuilder();
		ArrayList<FollowUpBirthDateBean> objList = new ArrayList<FollowUpBirthDateBean>();
		Cursor objCursor = null;
		
		try {

			if(objBean.FBDID == 0)
				Query.append("SELECT ")
				 	 .append("FBDID, ProjectID, Date, Body ")
				 	 .append("FROM FollowUpBirthDate ORDER BY ProjectID");
			else
				Query.append("SELECT ")
			 	 	 .append("FEID, ProjectID, Date, Body ")
					 .append("FROM FollowUpBirthDate ")
					 .append("WHERE FBDID = ")
					 .append(objBean.FBDID);

			objCursor = objDataAccess.query(Query.toString());
			objCursor.moveToPosition(-1);
			
			while (objCursor.moveToNext()) {
				
				FollowUpBirthDateBean objBookList = new FollowUpBirthDateBean();
				
				objBookList.FBDID = objCursor.getInt(0);
				objBookList.ProjectID = objCursor.getInt(1);
				objBookList.Date = objCursor.getString(2);
				objBookList.Body = objCursor.getString(3);
				
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
	
	public void Insert(FollowUpBirthDateBean objBean)
	{
		DataAccess objDataAccess = new DataAccess();
		StringBuilder Query = new StringBuilder();
		try {

			Query.append("INSERT INTO ")
				 .append("FollowUpBirthDate (ProjectID, Date, Body) ")
				 .append("Values ( ")
				 .append(objBean.ProjectID)
				 .append(", '")
				 .append(objBean.Date)
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
	
	public void Update(FollowUpBirthDateBean objBean)
	{
		DataAccess objDataAccess = new DataAccess();
		StringBuilder Query = new StringBuilder();
		
		try {

			Query.append("UPDATE FollowUpBirthDate ")
				 .append("SET ")			
				 .append("ProjectID = ")
				 .append(objBean.ProjectID)
				 .append(", Date = '")
				 .append(objBean.Date)
				 .append("', Body = '")
				 .append(objBean.Body)
				 .append("' WHERE FBDID = ")
				 .append(objBean.FBDID);
			
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
			objDataAccess.execute("DELETE FROM FollowUpBirthDate WHERE FBDID = " + ID);
			
		} catch (Exception e) {
			Log.error(this.getClass() + " :: Delete() ", e);
		} finally {
			objDataAccess = null;
		}
	}
}
