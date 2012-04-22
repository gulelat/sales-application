package com.android.salesapp.bean;

public class ProjectDetailBean 
{
	public int PDID;
	public int ProjectID;
	public int PDFID;
	public String FieldName;
	public String FieldValue;
	
	public int getPDID() {
		return PDID;
	}
	public void setPDID(int pDID) {
		PDID = pDID;
	}
	public int getProjectID() {
		return ProjectID;
	}
	public void setProjectID(int projectID) {
		ProjectID = projectID;
	}
	public int getPDFID() {
		return PDFID;
	}
	public void setPDFID(int pDFID) {
		PDFID = pDFID;
	}
	public String getFieldValue() {
		return FieldValue;
	}
	public void setFieldValue(String fieldValue) {
		FieldValue = fieldValue;
	}
	
}
