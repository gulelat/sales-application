package com.android.salesapp.bl;

import java.util.ArrayList;

import android.database.Cursor;

import com.android.salesapp.bean.ProjectDetailFieldBean;
import com.android.salesapp.db.DataAccess;
import com.android.salesapp.utils.Log;

public class ProjectDetailFieldBL 
{
	public ArrayList<ProjectDetailFieldBean> List(ProjectDetailFieldBean objBean)
	{
		DataAccess objDataAccess = new DataAccess();
		StringBuilder Query = new StringBuilder();
		ArrayList<ProjectDetailFieldBean> objList = new ArrayList<ProjectDetailFieldBean>();
		Cursor objCursor = null;
		
		try {

			if(objBean.PDFID == 0)
				Query.append("SELECT ")
				 	 .append("PDFID, FieldName ")
				 	 .append("FROM ProjectDetailField ORDER BY PDFID");
			else
				Query.append("SELECT ")
			 	 	 .append("PDFID, FieldName ")
					 .append("FROM ProjectDetailField ")
					 .append("WHERE PDFID = ")
					 .append(objBean.PDFID);

			objCursor = objDataAccess.query(Query.toString());
			objCursor.moveToPosition(-1);
			
			while (objCursor.moveToNext()) {
				
				ProjectDetailFieldBean objBookList = new ProjectDetailFieldBean();
				
				objBookList.PDFID = objCursor.getInt(0);
				objBookList.FieldName = objCursor.getString(1);
				objBookList.TAG = 0;
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
	
	public void Insert(ProjectDetailFieldBean objBean)
	{
		DataAccess objDataAccess = new DataAccess();
		StringBuilder Query = new StringBuilder();
		try {

			Query.append("INSERT INTO ")
				 .append("ProjectDetailField (FieldName) ")
				 .append("Values ( '")
				 .append(objBean.FieldName)
				 .append("')");
			
			objDataAccess.execute(Query.toString());		
			
		} catch (Exception e) {
			Log.error(this.getClass() + " :: Insert() ", e);
		} finally {
			objDataAccess = null;
			Query = null;
		}
	}
	
	public void Update(ProjectDetailFieldBean objBean)
	{
		DataAccess objDataAccess = new DataAccess();
		StringBuilder Query = new StringBuilder();
		
		try {
			
			Query.append("UPDATE ProjectDetailField ")
				 .append("SET ")			
				 .append("FieldName = '")
				 .append(objBean.FieldName)
				 .append("' WHERE PDFID = ")
				 .append(objBean.PDFID);
			
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
			objDataAccess.execute("DELETE FROM ProjectDetailField WHERE PDFID = " + ID);
			
		} catch (Exception e) {
			Log.error(this.getClass() + " :: Delete() ", e);
		} finally {
			objDataAccess = null;
		}
	}
}
