package com.lyz.code.infinity.utils;

import com.lyz.code.infinity.domain.Util;

public class ReadConfigXml extends Util{
	public ReadConfigXml(){
		super();
		this.fileName = "ReadConfigXml.java";
	}
	
	public ReadConfigXml(String packageToken){
		super();
		if (packageToken!=null && !"".equals(packageToken) && packageToken.contains("database"))
			this.packageToken = packageToken;
		else if (packageToken!=null && !"".equals(packageToken))this.packageToken = packageToken+".database";
		else packageToken = "";
		this.fileName = "ReadConfigXml.java";
	}
	
	@Override
	public void setPackageToken(String packageToken){
		if (packageToken.contains("database"))
			this.packageToken = packageToken;
		else this.packageToken = packageToken+".database";
	}
	
	@Override
	public String generateUtilString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("package ").append(this.packageToken).append(";\n\n");

		sb.append("import java.util.Properties;\n");

		sb.append("public class ReadConfigXml {\n");
		sb.append("private Properties props;\n");
		sb.append("\n");
		sb.append("public ReadConfigXml(String url) {\n");
		sb.append("	ParseXML myRead = new ParseXML();\n");
		sb.append("	try {\n");
		sb.append("		myRead.parse(url);\n");
		sb.append("		props = new Properties();\n");
		sb.append("		props = myRead.getProps();\n");
		sb.append("	} catch (Exception e) {\n");
		sb.append("		e.printStackTrace();\n");
		sb.append("	}\n");
		sb.append("}\n");
		sb.append("\n");
		sb.append("public String getUrl() {\n");
		sb.append("	return props.getProperty(\"dburl\");\n");
		sb.append("}\n");
		sb.append("\n");
		sb.append("public String getDataBase() {\n");
		sb.append("	return props.getProperty(\"dbname\");\n");
		sb.append("}\n");
		sb.append("\n");
		sb.append("public String getUserName() {\n");
		sb.append("	return props.getProperty(\"username\");\n");
		sb.append("}\n");
		sb.append("\n");
		sb.append("public String getPassWord() {\n");
		sb.append("	return props.getProperty(\"password\");\n");
		sb.append("}\n");
		sb.append("\n");
		sb.append("public String getTestUrl() {\n");
		sb.append("	return props.getProperty(\"testdburl\");\n");
		sb.append("}\n");
		sb.append("\n");
		sb.append("public String getTestDataBase() {\n");
		sb.append("	return props.getProperty(\"testdbname\");\n");
		sb.append("}\n");
		sb.append("\n");
		sb.append("public String getTestUserName() {\n");
		sb.append("	return props.getProperty(\"testusername\");\n");
		sb.append("}\n");
		sb.append("\n");
		sb.append("public String getTestPassWord() {\n");
		sb.append("	return props.getProperty(\"testpassword\");\n");
		sb.append("}\n");
		sb.append("\n");
		sb.append("public boolean isNotTest() {\n");
		sb.append("	String isNotTestStr = props.getProperty(\"isnottest\");\n");
		sb.append("	if (isNotTestStr != null && !\"\".equals(isNotTestStr)&&\"true\".equalsIgnoreCase(isNotTestStr)){\n");
		sb.append("		return true;\n");
		sb.append("	}\n");
		sb.append("	return false;\n");
		sb.append("}\n");
		sb.append("\n");
		sb.append("public boolean isTestsuiteOffline() {\n");
		sb.append("	String isTestsuiteOfflineStr = props.getProperty(\"istestsuiteoffline\");\n");
		sb.append("	if (isTestsuiteOfflineStr != null && !\"\".equals(isTestsuiteOfflineStr)&&\"true\".equalsIgnoreCase(isTestsuiteOfflineStr)){\n");
		sb.append("		return true;\n");
		sb.append("	}\n");
		sb.append("	return false;\n");
		sb.append("}\n");
		sb.append("\n");
		sb.append("public boolean isGpinterfaceOffline() {\n");
		sb.append("	String isGpinterfaceOfflineStr = props.getProperty(\"isgpinterfaceoffline\");\n");
		sb.append("	if (isGpinterfaceOfflineStr != null && !\"\".equals(isGpinterfaceOfflineStr)&&\"true\".equalsIgnoreCase(isGpinterfaceOfflineStr)){\n");
		sb.append("		return true;\n");
		sb.append("	}\n");
		sb.append("	return false;\n");
		sb.append("}\n");
		sb.append("\n");
		sb.append("public boolean isProductProtectLockOffline() {\n");
		sb.append("	String isProductProtectLockOfflineStr = props.getProperty(\"isproductprotectlockoffline\");\n");
		sb.append("	if (isProductProtectLockOfflineStr != null && !\"\".equals(isProductProtectLockOfflineStr)&&\"true\".equalsIgnoreCase(isProductProtectLockOfflineStr)){\n");
		sb.append("		return true;\n");
		sb.append("	}\n");
		sb.append("	return false;\n");
		sb.append("}\n");
		sb.append("}\n");
		return sb.toString();
	}
}
