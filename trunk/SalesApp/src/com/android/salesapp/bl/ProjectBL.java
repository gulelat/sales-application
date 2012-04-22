package com.android.salesapp.bl;

import java.util.ArrayList;

import android.database.Cursor;

import com.android.salesapp.bean.ProjectBean;
import com.android.salesapp.db.DataAccess;
import com.android.salesapp.utils.Log;

public class ProjectBL 
{
	public ArrayList<ProjectBean> List(ProjectBean objBean)
	{
		DataAccess objDataAccess = new DataAccess();
		StringBuilder Query = new StringBuilder();
		ArrayList<ProjectBean> objList = new ArrayList<ProjectBean>();
		Cursor objCursor = null;
		
		try {

			if(objBean.ProjectID == 0)
				Query.append("SELECT ")
				 	 .append("ProjectID, Name ")
				 	 .append("FROM Project ORDER BY Name");
			else
				Query.append("SELECT ")
			 	 	 .append("ProjectID, Name ")
					 .append("FROM Project ")
					 .append("WHERE ProjectID = ")
					 .append(objBean.ProjectID);

			objCursor = objDataAccess.query(Query.toString());
			objCursor.moveToPosition(-1);
			
			while (objCursor.moveToNext()) {
				
				ProjectBean objBookList = new ProjectBean();
				
				objBookList.ProjectID = objCursor.getInt(0);
				objBookList.Name = objCursor.getString(1);
				
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
	
	public int Insert(ProjectBean objBean)
	{
		int resultID = 0;
		DataAccess objDataAccess = new DataAccess();
		StringBuilder Query = new StringBuilder();
		Cursor objCursor = null;
		try {

			Query.append("INSERT INTO ")
				 .append("Project (Name) ")
				 .append("Values ( '")
				 .append(objBean.Name)
				 .append("')");
			
			objDataAccess.execute(Query.toString());
			objCursor = objDataAccess.query("SELECT MAX(ProjectID) FROM Project");
			objCursor.moveToPosition(0);
			
			resultID = objCursor.getInt(0);			
			
		} catch (Exception e) {
			Log.error(this.getClass() + " :: Insert() ", e);
		} finally {
			objDataAccess = null;
			Query = null;
			objCursor.close();
			objCursor = null;
		}
		
		return resultID;
	}
	
	public void Update(ProjectBean objBean)
	{
		DataAccess objDataAccess = new DataAccess();
		StringBuilder Query = new StringBuilder();
		
		try {
			
			Query.append("UPDATE Project ")
				 .append("SET ")			
				 .append("Name = '")
				 .append(objBean.Name)
				 .append("' WHERE ProjectID = ")
				 .append(objBean.ProjectID);
			
			objDataAccess.execute(Query.toString());
			
		} catch (Exception e) {
			Log.error(this.getClass() + " :: Update() ", e);
			e.getStackTrace();
		} finally {
			objDataAccess = null;
			Query = null;
		}
	}
	
	public void Delete(int ProjectID)
	{
		DataAccess objDataAccess = new DataAccess();
		
		try {
			objDataAccess.execute("DELETE FROM Project WHERE ProjectID = " + ProjectID);
			
		} catch (Exception e) {
			Log.error(this.getClass() + " :: Delete() ", e);
		} finally {
			objDataAccess = null;
		}
	}
	
	public int ProjectExists(String projectName)
	{
		int resultID = 0;
		DataAccess objDataAccess = new DataAccess();
		StringBuilder Query = new StringBuilder();
		Cursor objCursor = null;
		
		try {

			Query.append("SELECT ")
			 	 .append("ProjectID ")
			 	 .append("FROM Project ")
			 	 .append("WHERE UPPER(Name) = '")
			 	 .append(projectName.toUpperCase())
			 	 .append("'");
			
			objCursor = objDataAccess.query(Query.toString());
			objCursor.moveToPosition(0);
			
			resultID = objCursor.getInt(0);
			
		} catch (Exception e) {
			Log.error(this.getClass() + " :: ProjectExists() ", e);
		} finally {
			objDataAccess = null;
			Query = null;
			if(objCursor != null)
				objCursor.close();
			objCursor = null;
		}
		
		return resultID;
	}
}
