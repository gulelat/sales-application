package com.android.salesapp.bl;

import java.util.ArrayList;

import android.database.Cursor;

import com.android.salesapp.bean.CampaignBean;
import com.android.salesapp.db.DataAccess;
import com.android.salesapp.utils.Log;

public class CampaignBL 
{
	public ArrayList<CampaignBean> List(CampaignBean objBean)
	{
		DataAccess objDataAccess = new DataAccess();
		StringBuilder Query = new StringBuilder();
		ArrayList<CampaignBean> objList = new ArrayList<CampaignBean>();
		Cursor objCursor = null;
		
		try {

			if(objBean.CampID == 0)
				Query.append("SELECT ")
				 	 .append("CampID, CampName ")
				 	 .append("FROM Campaign ORDER BY Name");
			else
				Query.append("SELECT ")
			 	 	 .append("CampID, CampName ")
					 .append("FROM Campaign ")
					 .append("WHERE CampID = ")
					 .append(objBean.CampID);

			objCursor = objDataAccess.query(Query.toString());
			objCursor.moveToPosition(-1);
			
			while (objCursor.moveToNext()) {
				
				CampaignBean objBookList = new CampaignBean();
				
				objBookList.CampID = objCursor.getInt(0);
				objBookList.CampName = objCursor.getString(1);
				
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
	
	public void Insert(CampaignBean objBean)
	{
		DataAccess objDataAccess = new DataAccess();
		StringBuilder Query = new StringBuilder();
		try {

			Query.append("INSERT INTO ")
				 .append("Campaign (CampName) ")
				 .append("Values ( '")
				 .append(objBean.CampName)
				 .append("')");
			
			objDataAccess.execute(Query.toString());		
			
		} catch (Exception e) {
			Log.error(this.getClass() + " :: Insert() ", e);
		} finally {
			objDataAccess = null;
			Query = null;
		}
	}
	
	public void Update(CampaignBean objBean)
	{
		DataAccess objDataAccess = new DataAccess();
		StringBuilder Query = new StringBuilder();
		
		try {
			
			Query.append("UPDATE Campaign ")
				 .append("SET ")			
				 .append("CampName = '")
				 .append(objBean.CampName)
				 .append("' WHERE CampID = ")
				 .append(objBean.CampID);
			
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
			objDataAccess.execute("DELETE FROM Campaign WHERE CampID = " + ID);
			
		} catch (Exception e) {
			Log.error(this.getClass() + " :: Delete() ", e);
		} finally {
			objDataAccess = null;
		}
	}
	
}
