package com.lyz.code.infinity.database;

import java.util.Properties;

public class ReadConfigXml {
	private Properties props;

	public ReadConfigXml(String url) {
		ParseXML myRead = new ParseXML();
		try {
			myRead.parse(url);
			props = new Properties();
			props = myRead.getProps();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getUrl() {
		return props.getProperty("dburl");
	}

	public String getDataBase() {
		return props.getProperty("dbname");
	}

	public String getUserName() {
		return props.getProperty("username");
	}

	public String getPassWord() {
		return props.getProperty("password");
	}
	
	public String getTestUrl() {
		return props.getProperty("testdburl");
	}

	public String getTestDataBase() {
		return props.getProperty("testdbname");
	}

	public String getTestUserName() {
		return props.getProperty("testusername");
	}

	public String getTestPassWord() {
		return props.getProperty("testpassword");
	}
	
	public boolean isNotTest() {
		String isNotTestStr = props.getProperty("isnottest");
		if (isNotTestStr != null && !"".equals(isNotTestStr)&&"true".equalsIgnoreCase(isNotTestStr)){
			return true;
		}
		return false;
	}
	
	public boolean isTestsuiteOffline() {
		String isTestsuiteOfflineStr = props.getProperty("istestsuiteoffline");
		if (isTestsuiteOfflineStr != null && !"".equals(isTestsuiteOfflineStr)&&"true".equalsIgnoreCase(isTestsuiteOfflineStr)){
			return true;
		}
		return false;
	}
	
	public boolean isGpinterfaceOffline() {
		String isGpinterfaceOfflineStr = props.getProperty("isgpinterfaceoffline");
		if (isGpinterfaceOfflineStr != null && !"".equals(isGpinterfaceOfflineStr)&&"true".equalsIgnoreCase(isGpinterfaceOfflineStr)){
			return true;
		}
		return false;
	}
	
	public boolean isProductProtectLockOffline() {
		String isProductProtectLockOfflineStr = props.getProperty("isproductprotectlockoffline");
		if (isProductProtectLockOfflineStr != null && !"".equals(isProductProtectLockOfflineStr)&&"true".equalsIgnoreCase(isProductProtectLockOfflineStr)){
			return true;
		}
		return false;
	}
}