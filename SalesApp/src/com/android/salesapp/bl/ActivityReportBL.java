package com.android.salesapp.bl;

import java.util.ArrayList;

import android.database.Cursor;

import com.android.salesapp.bean.ActivityReportBean;
import com.android.salesapp.db.DataAccess;
import com.android.salesapp.utils.Const;
import com.android.salesapp.utils.Log;
import com.android.salesapp.utils.Utils;

public class ActivityReportBL 
{
	public ArrayList<ActivityReportBean> List(ActivityReportBean objBean)
	{
		DataAccess objDataAccess = new DataAccess();
		StringBuilder Query = new StringBuilder();
		ArrayList<ActivityReportBean> objList = new ArrayList<ActivityReportBean>();
		Cursor objCursor = null;
		
		try {

			if(objBean.ActivityID == 0)
				Query.append("SELECT ")
				 	 .append("ActivityID, ActivityType, Date ")
				 	 .append("FROM ActivityReport ORDER BY ActivityType");
			else
				Query.append("SELECT ")
			 	 	 .append("ActivityID, ActivityType, Date ")
					 .append("FROM ActivityReport ")
					 .append("WHERE ActivityID = ")
					 .append(objBean.ActivityID);

			objCursor = objDataAccess.query(Query.toString());
			objCursor.moveToPosition(-1);
			
			while (objCursor.moveToNext()) {
				
				ActivityReportBean objBookList = new ActivityReportBean();

				objBookList.ActivityID = objCursor.getInt(0);
				objBookList.ActivityType = objCursor.getInt(1);
				objBookList.Date = objCursor.getString(2);
				
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
	
	public void Insert(ActivityReportBean objBean)
	{
		DataAccess objDataAccess = new DataAccess();
		StringBuilder Query = new StringBuilder();
		try {

			Query.append("INSERT INTO ")
				 .append("Quetions (ActivityType, Date) ")
				 .append("Values ( ")
				 .append(objBean.ActivityType)
				 .append(", '")
				 .append(objBean.Date)
				 .append("')");
			
			objDataAccess.execute(Query.toString());		
			
		} catch (Exception e) {
			Log.error(this.getClass() + " :: Insert() ", e);
		} finally {
			objDataAccess = null;
			Query = null;
		}
	}
	
	public void Update(ActivityReportBean objBean)
	{
		DataAccess objDataAccess = new DataAccess();
		StringBuilder Query = new StringBuilder();
		
		try {
			
			Query.append("UPDATE ActivityReport ")
				 .append("SET ")			
				 .append("ActivityType = ")
				 .append(objBean.ActivityType)
				 .append(", Date = '")
				 .append(objBean.Date)
				 .append("' WHERE ActivityID = ")
				 .append(objBean.ActivityID);
			
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
			objDataAccess.execute("DELETE FROM ActivityReport WHERE ActivityID = " + ID);
			
		} catch (Exception e) {
			Log.error(this.getClass() + " :: Delete() ", e);
		} finally {
			objDataAccess = null;
		}
	}

	public String[] Count(String from, String to)
	{
		DataAccess objDataAccess = new DataAccess();
		StringBuilder Query_1 = new StringBuilder();
		StringBuilder Query_2 = new StringBuilder();
		String objList[] = new String[2];
		Cursor objCursor_1 = null;
		Cursor objCursor_2 = null;
		
		try {

			Query_1.append("SELECT ")
			 	 .append("COUNT(ActivityID) ")
			 	 .append("FROM ActivityReport WHERE ActivityType = ")
			 	 .append(Const.EMAIL_SEND);			

			Query_2.append("SELECT ")
			 	 .append("COUNT(ActivityID) ")
			 	 .append("FROM ActivityReport WHERE ActivityType = ")
			 	 .append(Const.SMS_SEND);

			if(!from.equals(""))
			{
				from = String.valueOf(Utils.dateToMilisec(from, "dd/MM/yyyy"));
				Query_1.append(" AND Date >= ")
					.append(from);
				Query_2.append(" AND Date >= ")
					.append(from);
			}
			
			if(!to.equals(""))
			{
				to = String.valueOf(Utils.dateToMilisec(to, "dd/MM/yyyy"));
				Query_1.append(" AND Date <= ")
					.append(to);
				Query_2.append(" AND Date <= ")
					.append(to);
			}
			
			objCursor_1 = objDataAccess.query(Query_1.toString());
			objCursor_2 = objDataAccess.query(Query_2.toString());

			if(objCursor_1.getCount() > 0)
			{
				objCursor_1.moveToPosition(0);
				objList[0] = String.valueOf(objCursor_1.getInt(0));
			}
			
			if(objCursor_2.getCount() > 0)
			{
				objCursor_2.moveToPosition(0);
				objList[1] = String.valueOf(objCursor_2.getInt(0));
			}
			
		} catch (Exception e) {
			Log.error(this.getClass() + " :: List() ", e);
		} finally {
			objDataAccess = null;
			Query_1 = null;
			Query_2 = null;
			if(objCursor_1 != null)
				objCursor_1.close();
			if(objCursor_2 != null)
				objCursor_2.close();
			objCursor_1 = null;
			objCursor_2 = null;
		}
		
		return objList;
	}
	
}
