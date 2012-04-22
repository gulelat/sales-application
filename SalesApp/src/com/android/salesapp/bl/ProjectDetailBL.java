package com.android.salesapp.bl;

import java.util.ArrayList;

import android.database.Cursor;

import com.android.salesapp.bean.ProjectDetailBean;
import com.android.salesapp.db.DataAccess;
import com.android.salesapp.utils.Log;

public class ProjectDetailBL 
{
	public ArrayList<ProjectDetailBean> List(ProjectDetailBean objBean)
	{
		DataAccess objDataAccess = new DataAccess();
		StringBuilder Query = new StringBuilder();
		ArrayList<ProjectDetailBean> objList = new ArrayList<ProjectDetailBean>();
		Cursor objCursor = null;
		
		try {

			if(objBean.PDID == 0)
				Query.append("SELECT ")
				 	 .append("PDID, ProjectID, PDFID, FieldValue ")
				 	 .append("FROM ProjectDetail ORDER BY PDID");
			else
				Query.append("SELECT ")
			 	 	 .append("PDID, ProjectID, PDFID, FieldValue ")
					 .append("FROM ProjectDetail ")
					 .append("WHERE PDID = ")
					 .append(objBean.PDID);

			objCursor = objDataAccess.query(Query.toString());
			objCursor.moveToPosition(-1);
			
			while (objCursor.moveToNext()) {
				
				ProjectDetailBean objBookList = new ProjectDetailBean();

				objBookList.PDID = objCursor.getInt(0);
				objBookList.ProjectID = objCursor.getInt(1);
				objBookList.PDFID = objCursor.getInt(2);
				objBookList.FieldValue = objCursor.getString(3);
				
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
	
	public void Insert(ProjectDetailBean objBean)
	{
		DataAccess objDataAccess = new DataAccess();
		StringBuilder Query = new StringBuilder();
		try {

			Query.append("INSERT INTO ")
				 .append("ProjectDetail (ProjectID, PDFID, FieldValue) ")
				 .append("Values ( ")
				 .append(objBean.ProjectID)
				 .append(", ")
				 .append(objBean.PDFID)
				 .append(", '")
				 .append(objBean.FieldValue)
				 .append("')");
			
			objDataAccess.execute(Query.toString());		
			
		} catch (Exception e) {
			Log.error(this.getClass() + " :: Insert() ", e);
		} finally {
			objDataAccess = null;
			Query = null;
		}
	}
	
	public void Update(ProjectDetailBean objBean)
	{
		DataAccess objDataAccess = new DataAccess();
		StringBuilder Query = new StringBuilder();
		
		try {
			
			Query.append("UPDATE ProjectDetail ")
				 .append("SET ")			
				 .append("ProjectID = ")
				 .append(objBean.ProjectID)
				 .append(", PDFID = ")
				 .append(objBean.PDFID)
				 .append(", FieldValue = '")
				 .append(objBean.FieldValue)
				 .append("' WHERE PDID = ")
				 .append(objBean.PDID);
			
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
			objDataAccess.execute("DELETE FROM ProjectDetail WHERE PDID = " + ID);
			
		} catch (Exception e) {
			Log.error(this.getClass() + " :: Delete() ", e);
		} finally {
			objDataAccess = null;
		}
	}
	
	public ArrayList<ProjectDetailBean> ContactList()
	{
		DataAccess objDataAccess = new DataAccess();
		StringBuilder Query = new StringBuilder();
		ArrayList<ProjectDetailBean> objList = new ArrayList<ProjectDetailBean>();
		Cursor objCursor = null;
		
		try {
			Query.append("SELECT ")
		 	 	 .append("PDID, ProjectID, PDFID, FieldValue ")
				 .append("FROM ProjectDetail ")
				 .append("WHERE PDFID = 1");

			objCursor = objDataAccess.query(Query.toString());
			objCursor.moveToPosition(-1);
			
			while (objCursor.moveToNext()) {
				
				ProjectDetailBean objBookList = new ProjectDetailBean();

				objBookList.PDID = objCursor.getInt(0);
				objBookList.ProjectID = objCursor.getInt(1);
				objBookList.PDFID = objCursor.getInt(2);
				objBookList.FieldValue = objCursor.getString(3);
				
				objList.add(objBookList);
			}
			
		} catch (Exception e) {
			Log.error(this.getClass() + " :: ContactList() ", e);
		} finally {
			objDataAccess = null;
			Query = null;
			objCursor.close();
			objCursor = null;
		}
		
		return objList;
	}

	public ArrayList<ProjectDetailBean> ContactDetail(int ProjectID)
	{
		DataAccess objDataAccess = new DataAccess();
		StringBuilder Query = new StringBuilder();
		ArrayList<ProjectDetailBean> objList = new ArrayList<ProjectDetailBean>();
		Cursor objCursor = null;
		
		try {

			Query.append("SELECT ")
		 	 	 .append("ProjectDetail.PDID, ProjectDetail.ProjectID, ProjectDetail.PDFID, ProjectDetail.FieldValue, ProjectDetailField.FieldName ")
				 .append("FROM ProjectDetail ")
				 .append("INNER JOIN ProjectDetailField ON ProjectDetailField.PDFID = ProjectDetail.PDFID ")
				 .append("WHERE ProjectDetail.ProjectID = ")
				 .append(ProjectID);
			
			objCursor = objDataAccess.query(Query.toString());
			objCursor.moveToPosition(-1);
			
			while (objCursor.moveToNext()) {
				
				ProjectDetailBean objBookList = new ProjectDetailBean();

				objBookList.PDID = objCursor.getInt(0);
				objBookList.ProjectID = objCursor.getInt(1);
				objBookList.PDFID = objCursor.getInt(2);
				objBookList.FieldValue = objCursor.getString(3);
				objBookList.FieldName = objCursor.getString(4);
				
				objList.add(objBookList);
			}
			
		} catch (Exception e) {
			Log.error(this.getClass() + " :: ContactDetail() ", e);
		} finally {
			objDataAccess = null;
			Query = null;
			if(objCursor != null)
				objCursor.close();
			objCursor = null;
		}
		
		return objList;
	}
	
	

}
