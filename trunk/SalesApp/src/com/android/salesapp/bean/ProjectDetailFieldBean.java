package com.android.salesapp.bean;

public class ProjectDetailFieldBean 
{

	public int PDFID;
	public String FieldName;
	public int TAG;
	public String EditValue;
	
	public String getEditValue() {
		return EditValue;
	}
	public void setEditValue(String editValue) {
		EditValue = editValue;
	}
	public int getTAG() {
		return TAG;
	}
	public void setTAG(int tAG) {
		TAG = tAG;
	}
	public int getPDFID() {
		return PDFID;
	}
	public void setPDFID(int pDFID) {
		PDFID = pDFID;
	}
	public String getFieldName() {
		return FieldName;
	}
	public void setFieldName(String fieldName) {
		FieldName = fieldName;
	}
	
	
}
