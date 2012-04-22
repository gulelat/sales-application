package com.android.salesapp.bl;

import java.util.ArrayList;

import android.database.Cursor;

import com.android.salesapp.bean.QuetionBean;
import com.android.salesapp.db.DataAccess;
import com.android.salesapp.utils.Log;

public class QuetionBL 
{
	public ArrayList<QuetionBean> List(QuetionBean objBean)
	{
		DataAccess objDataAccess = new DataAccess();
		StringBuilder Query = new StringBuilder();
		ArrayList<QuetionBean> objList = new ArrayList<QuetionBean>();
		Cursor objCursor = null;
		
		try {

			if(objBean.QuetionID == 0)
				Query.append("SELECT ")
				 	 .append("QuetionID, Quetion, AnsID ")
				 	 .append("FROM Quetions ORDER BY PDFID");
			else
				Query.append("SELECT ")
			 	 	 .append("QuetionID, Quetion, AnsID ")
					 .append("FROM Quetions ")
					 .append("WHERE QuetionID = ")
					 .append(objBean.QuetionID);

			objCursor = objDataAccess.query(Query.toString());
			objCursor.moveToPosition(-1);
			
			while (objCursor.moveToNext()) {
				
				QuetionBean objBookList = new QuetionBean();
				
				objBookList.QuetionID = objCursor.getInt(0);
				objBookList.Quetion = objCursor.getString(1);
				objBookList.AnsID = objCursor.getInt(2);
				
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
	
	public void Insert(QuetionBean objBean)
	{
		DataAccess objDataAccess = new DataAccess();
		StringBuilder Query = new StringBuilder();
		try {

			Query.append("INSERT INTO ")
				 .append("Quetions (Quetion, AnsID) ")
				 .append("Values ( '")
				 .append(objBean.Quetion)
				 .append("', ")
				 .append(objBean.AnsID)
				 .append(")");
			
			objDataAccess.execute(Query.toString());		
			
		} catch (Exception e) {
			Log.error(this.getClass() + " :: Insert() ", e);
		} finally {
			objDataAccess = null;
			Query = null;
		}
	}
	
	public void Update(QuetionBean objBean)
	{
		DataAccess objDataAccess = new DataAccess();
		StringBuilder Query = new StringBuilder();
		
		try {
			
			Query.append("UPDATE Quetions ")
				 .append("SET ")			
				 .append("Quetion = '")
				 .append(objBean.Quetion)
				 .append("', AnsID = ")
				 .append(objBean.Quetion)
				 .append(" WHERE QuetionID = ")
				 .append(objBean.QuetionID);
			
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
			objDataAccess.execute("DELETE FROM Quetions WHERE QuetionID = " + ID);
			
		} catch (Exception e) {
			Log.error(this.getClass() + " :: Delete() ", e);
		} finally {
			objDataAccess = null;
		}
	}
}
