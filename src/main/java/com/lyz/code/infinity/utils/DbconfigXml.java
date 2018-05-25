package com.lyz.code.infinity.utils;

import com.lyz.code.infinity.domain.Util;

public class DbconfigXml extends Util{
	protected String dbName = "infinity";
	protected String dbUser = "root";
	protected String dbPassword = "";
	public DbconfigXml(){
		super();
		this.fileName = "dbconfig.xml";
		this.packageToken = "";
	}
	
	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public DbconfigXml(String dbName, String dbUser, String dbPassword, boolean emptypassword){
		super();
		this.fileName = "dbconfig.xml";
		this.packageToken = "";
		this.dbName = dbName;
		this.dbUser = dbUser;
		this.dbPassword = dbPassword;
		if (emptypassword) this.dbPassword = "";
	}
		
	@Override
	public void setPackageToken(String packageToken){
		this.packageToken = "";
	}
	
	@Override
	public String getPackageToken(){
		return "";
	}
	
	@Override
	public String generateUtilString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sb.append("<configenv>\n");
		sb.append("<datasource>\n");
		sb.append("<dburl>jdbc:mysql://localhost:3306/</dburl>\n");
		sb.append("<dbname>"+this.dbName+"</dbname>\n");
		sb.append("<username>"+this.dbUser+"</username>\n");
		sb.append("<password>"+this.dbPassword+"</password>\n");
		sb.append("\n");
		sb.append("<testdburl>jdbc:mysql://localhost:3306/</testdburl>\n");
		sb.append("<testdbname>"+this.dbName+"_test</testdbname>\n");
		sb.append("<testusername>"+this.dbUser+"</testusername>\n");
		sb.append("<testpassword>"+this.dbPassword+"</testpassword>\n");
		sb.append("\n");
		sb.append("<isnottest>true</isnottest>\n");
		sb.append("<isproductprotectlockoffline>true</isproductprotectlockoffline>\n");
		sb.append("<istestsuiteoffline>false</istestsuiteoffline>\n");
		sb.append("<isgpinterfaceoffline>false</isgpinterfaceoffline>\n");
		sb.append("\n");
		sb.append("</datasource>\n");
		sb.append("</configenv>\n");
		return sb.toString();
	}
}
