package com.lyz.code.infinity.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * DBConf must owned by the Project Manager
 * You can change the configure to fit your situation.
 * 
 * This class should not change others except the databasename,
 * username and password
 *  
 * @author liuyazhuang
 *
 */
public final class DBConf{
    private static String databaseURL = "";
    private static String databaseUName = "";
    private static String databasePWord = "";
    private static String databaseName = "";
    
    private static String testDatabaseURL = "";
    private static String testDatabaseUName = "";
    private static String testDatabasePWord = "";
    private static String testDatabaseName = "";
    
    private static boolean isNotTest = false;					// Do not change this value, change them in dbconfig.xml	
    private static boolean isProductProtectLockOffline = false;  	// In production: set true ; In test: set false
    private static boolean isTestsuiteOffline = false;			// Test suite offline: set true ; Test suite online: set false
    private static boolean isGpinterfaceOffline = false;	    // Testing gpinterface offline: set true ; Test suite online: set false
    private static Connection connection;
    
    static {
    	ReadConfigXml reader = new ReadConfigXml("dbconfig.xml"); // ��ȡxml�ļ������ݿ������Ϣ
    	databaseURL = reader.getUrl();
    	databaseName = reader.getDataBase();
    	databaseUName  = reader.getUserName();
    	databasePWord = reader.getPassWord();
    	
    	testDatabaseURL = reader.getTestUrl();
    	testDatabaseName = reader.getTestDataBase();
    	testDatabaseUName  = reader.getTestUserName();
    	testDatabasePWord = reader.getTestPassWord();
    	
    	isNotTest = reader.isNotTest();
    	isTestsuiteOffline = reader.isTestsuiteOffline();
    	isProductProtectLockOffline = reader.isProductProtectLockOffline();
    	isGpinterfaceOffline = reader.isGpinterfaceOffline();    	
    }

	public static Connection initDB() throws IOException{
        try {
			String tdatabaseURL ="";
		    String tdatabaseUName ="";
		    String tdatabasePWord = "";
		    String tdatabaseName = "";
			if (isNotTest && isProductProtectLockOffline){
				tdatabaseURL = databaseURL;
			    tdatabaseUName = databaseUName;
			    tdatabasePWord = databasePWord;
			    tdatabaseName = databaseName;
			} else {
				tdatabaseURL = testDatabaseURL;
			    tdatabaseUName = testDatabaseUName;
			    tdatabasePWord = testDatabasePWord;
			    tdatabaseName = testDatabaseName;
			}		

            Class.forName("com.mysql.jdbc.Driver");
            String url = tdatabaseURL + tdatabaseName;
            connection = DriverManager.getConnection(url,tdatabaseUName,tdatabasePWord);
        } catch (Exception e){
            throw new IOException(e.getMessage());
        }

        DBConf.setConnection(connection);

        return connection;
    }

     public static void closeDB(Connection connection) {
         try {
            connection.close();
            connection = null;
         } catch (Exception e){
        }
    }
     
     public static void switchToTest(){
    	isNotTest = false;
     }
     
     public  static void switchToProduction(){
    	 isNotTest = true;
    }

	public static  Connection getConnection() {
		try {
			return initDB();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public static void setConnection(Connection connection) {
		DBConf.connection = connection;
	}

	public static boolean isTestsuiteOffline() {
		return isTestsuiteOffline;
	}

	public static boolean isGpinterfaceOffline() {
		return isGpinterfaceOffline;
	}
}
