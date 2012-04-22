package com.android.salesapp.db;


public class DDL {

	public static void upgrade(int level) {
		switch (level) {
		case 0:
			doUpdate0();
		case 1:
			//doUpdate1();
		case 2:
			//doUpdate2();
		case 3:
			//doUpdate3();

		}
	}


	private static void doUpdate0() {
		DataAccess da = new DataAccess();
		
		da.execute("CREATE TABLE ProjectDetailField" + 
				"(PDFID INTEGER PRIMARY KEY  AUTOINCREMENT, "
				+ "FieldName TEXT)");
		
		da.execute("CREATE TABLE Answere" + 
				"(AnsID INTEGER PRIMARY KEY  AUTOINCREMENT, "
				+ "AnsName TEXT)");

		da.execute("CREATE TABLE Project" + 
				"(ProjectID INTEGER PRIMARY KEY  AUTOINCREMENT, "
				+ "Name TEXT)");
		
		da.execute("CREATE TABLE ProjectDetail" + 
				"(PDID INTEGER PRIMARY KEY  AUTOINCREMENT, "
				+ "ProjectID INTEGER, "
				+ "PDFID INTEGER, "
				+ "FieldValue TEXT)");
		
		da.execute("CREATE TABLE Quetions" + 
				"(QuetionID INTEGER PRIMARY KEY  AUTOINCREMENT, "
				+ "Quetion TEXT, "
				+ "AnsID INTEGER)");
		
		da.execute("CREATE TABLE ActivityReport" + 
				"(ActivityID INTEGER PRIMARY KEY  AUTOINCREMENT, "
				+ "ActivityType INTEGER, "
				+ "Date TEXT)");
		
		da.execute("CREATE TABLE FollowUpMessage" + 
				"(FMID INTEGER PRIMARY KEY  AUTOINCREMENT, "
				+ "ProjectID INTEGER, "
				+ "Date TEXT, "
				+ "Time TEXT, "
				+ "Message TEXT)");
		
		da.execute("CREATE TABLE FollowUpEmail" + 
				"(FEID INTEGER PRIMARY KEY  AUTOINCREMENT, "
				+ "ProjectID INTEGER, "
				+ "Date TEXT, "
				+ "Time TEXT, "
				+ "Subject TEXT, "
				+ "Body TEXT)");
		
		da.execute("CREATE TABLE FollowUpCall" + 
				"(FCID INTEGER PRIMARY KEY  AUTOINCREMENT, "
				+ "ProjectID INTEGER, "
				+ "Date TEXT, "
				+ "Time TEXT, "
				+ "Reminder INTEGER)");
		
		da.execute("CREATE TABLE FollowUpBirthDate" + 
				"(FBDID INTEGER PRIMARY KEY  AUTOINCREMENT, "
				+ "ProjectID INTEGER, "
				+ "Date TEXT, "
				+ "Body TEXT)");
		
		da.execute("CREATE TABLE Campaign" + 
				"(CampID INTEGER PRIMARY KEY  AUTOINCREMENT, "
				+ "CampName TEXT)");

		da.execute("INSERT INTO Project(Name) VALUES('Mehul')");
		da.execute("INSERT INTO ProjectDetail(ProjectID, PDFID, FieldValue) VALUES(1, 1, 'Mehul Patel')");
		da.execute("INSERT INTO ProjectDetail(ProjectID, PDFID, FieldValue) VALUES(1, 1, 'Bhavadip Patel')");
		da.execute("INSERT INTO ProjectDetailField(FieldName) VALUES('Name')");
		
		da.execute("INSERT INTO Answere(AnsName) VALUES('Check Box')");
		da.execute("INSERT INTO Answere(AnsName) VALUES('Text Box')");
		da.execute("INSERT INTO Answere(AnsName) VALUES('Radio Button')");
	}
	

}
