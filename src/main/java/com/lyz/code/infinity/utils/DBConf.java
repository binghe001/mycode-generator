package com.lyz.code.infinity.utils;

import com.lyz.code.infinity.domain.Util;

public class DBConf extends Util{
	public DBConf(){
		super();
		this.fileName = "DBConf.java";
	}
	
	public DBConf(String packageToken){
		super();
		if (packageToken!=null && !"".equals(packageToken) && packageToken.contains("database"))
			this.packageToken = packageToken;
		else if (packageToken!=null && !"".equals(packageToken))this.packageToken = packageToken+".database";
		else packageToken = "";
		this.fileName = "DBConf.java";
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

		sb.append("import java.io.IOException;\n");
		sb.append("import java.sql.Connection;\n");
		sb.append("import java.sql.DriverManager;\n");
		sb.append("import java.sql.SQLException;\n");
		
		sb.append("/**\n");
		sb.append(" * DBConf must owned by the Project Manager\n");
		sb.append(" * You can change the configure to fit your situation.\n");
		sb.append(" * \n");
		sb.append(" * This class should not change others except the databasename,\n");
		sb.append(" * username and password\n");
		sb.append(" * \n");
		sb.append(" * @author Jerry Shen\n");
		sb.append(" * @release under GPLv2\n");
		sb.append(" * @email 2711098650@qq.com\n");
		sb.append(" *\n");
		sb.append(" */\n");
		sb.append("public final class DBConf{\n");
		sb.append("    private static String databaseURL = \"\";\n");
		sb.append("    private static String databaseUName = \"\";\n");
		sb.append("    private static String databasePWord = \"\";\n");
		sb.append("    private static String databaseName = \"\";\n");
		sb.append("    \n");
		sb.append("    private static String testDatabaseURL = \"\";\n");
		sb.append("    private static String testDatabaseUName = \"\";\n");
		sb.append("    private static String testDatabasePWord = \"\";\n");
		sb.append("    private static String testDatabaseName = \"\";\n");
		sb.append("    private static boolean isNotTest = false;					// Do not change this value, change them in dbconfig.xml\n");	
		sb.append("    private static boolean isProductProtectLockOffline = false;  	// In production: set true ; In test: set false\n");
		sb.append("    private static boolean isTestsuiteOffline = false;			// Test suite offline: set true ; Test suite online: set false\n");
		sb.append("    private static boolean isGpinterfaceOffline = false;	    // Testing gpinterface offline: set true ; Test suite online: set false\n");
		sb.append("    private static boolean multiclause = false;\n");
		sb.append("    private static Connection connection;\n");
		sb.append("        \n");
		sb.append("    static {\n");
		sb.append("    	ReadConfigXml reader = new ReadConfigXml(\"dbconfig.xml\");\n"); 
		sb.append("    	databaseURL = reader.getUrl();\n");
		sb.append("    	databaseName = reader.getDataBase();\n");
		sb.append("    	databaseUName  = reader.getUserName();\n");
		sb.append("    	databasePWord = reader.getPassWord();\n");
		sb.append("    	\n");
		sb.append("    	testDatabaseURL = reader.getTestUrl();\n");
		sb.append("    	testDatabaseName = reader.getTestDataBase();\n");
		sb.append("    	testDatabaseUName  = reader.getTestUserName();\n");
		sb.append("    	testDatabasePWord = reader.getTestPassWord();\n");
		sb.append("    	\n");
		sb.append("    	isNotTest = reader.isNotTest();\n");
		sb.append("    	isTestsuiteOffline = reader.isTestsuiteOffline();\n");
		sb.append("    	isProductProtectLockOffline = reader.isProductProtectLockOffline();\n");
		sb.append("    	isGpinterfaceOffline = reader.isGpinterfaceOffline();  \n");  	
		sb.append("    }\n");
		sb.append("\n");
		sb.append("	public synchronized static Connection initDB() throws Exception{\n");
		sb.append("        try {\n");
		sb.append("			String tdatabaseURL =\"\";\n");
		sb.append("		    String tdatabaseUName =\"\";\n");
		sb.append("		    String tdatabasePWord = \"\";\n");
		sb.append("		    String tdatabaseName = \"\";\n");
		sb.append("			if (isNotTest && isProductProtectLockOffline){\n");
		sb.append("				tdatabaseURL = databaseURL;\n");
		sb.append("			    tdatabaseUName = databaseUName;\n");
		sb.append("			    tdatabasePWord = databasePWord;\n");
		sb.append("			    tdatabaseName = databaseName;\n");
		sb.append("			} else {\n");
		sb.append("				tdatabaseURL = testDatabaseURL;\n");
		sb.append("			    tdatabaseUName = testDatabaseUName;\n");
		sb.append("			    tdatabasePWord = testDatabasePWord;\n");
		sb.append("			    tdatabaseName = testDatabaseName;\n");
		sb.append("			}	\n");	
		sb.append("\n");
		sb.append("            Class.forName(\"com.mysql.jdbc.Driver\");\n");
		sb.append("            String url = tdatabaseURL + tdatabaseName + \"?useUnicode=true&characterEncoding=utf-8\";\n");
		sb.append("            System.out.println(\"JerryDebugMysql:\"+ url);\n");
		sb.append("            if (tdatabasePWord == null || \"\".equals(tdatabasePWord)){\n");
		sb.append("            	url = tdatabaseURL + tdatabaseName + \"?user=\"+tdatabaseUName+\"&useUnicode=true&characterEncoding=utf-8\";\n");
		sb.append("            	connection = DriverManager.getConnection(url);\n");
		sb.append("            }else {\n");
		sb.append("            	connection = DriverManager.getConnection(url,tdatabaseUName,tdatabasePWord);\n");
		sb.append("            }\n");
		sb.append("        } catch (Exception e){\n");
		sb.append("            throw new IOException(e.getMessage());\n");
		sb.append("        }\n");
		sb.append("\n");
		sb.append("        DBConf.setConnection(connection);\n");
		sb.append("\n");
		sb.append("        return connection;\n");
		sb.append("    }\n");
		sb.append("\n");
		sb.append("	public synchronized static Connection initDB(boolean multiclause) throws Exception{\n");
		sb.append("        try {\n");
		sb.append("			String tdatabaseURL =\"\";\n");
		sb.append("		    String tdatabaseUName =\"\";\n");
		sb.append("		    String tdatabasePWord = \"\";\n");
		sb.append("		    String tdatabaseName = \"\";\n");
		sb.append("			if (isNotTest && isProductProtectLockOffline){\n");
		sb.append("				tdatabaseURL = databaseURL;\n");
		sb.append("			    tdatabaseUName = databaseUName;\n");
		sb.append("			    tdatabasePWord = databasePWord;\n");
		sb.append("			    tdatabaseName = databaseName;\n");
		sb.append("			} else {\n");
		sb.append("				tdatabaseURL = testDatabaseURL;\n");
		sb.append("			    tdatabaseUName = testDatabaseUName;\n");
		sb.append("			    tdatabasePWord = testDatabasePWord;\n");
		sb.append("			    tdatabaseName = testDatabaseName;\n");
		sb.append("			}\n");
		sb.append("\n");
		sb.append("			DBConf.multiclause = multiclause;\n");
		sb.append("			if (multiclause == false){\n");
		sb.append("	            Class.forName(\"com.mysql.jdbc.Driver\");\n");
		sb.append("		            String url = tdatabaseURL + tdatabaseName + \"?useUnicode=true&characterEncoding=utf-8\";\n");
		sb.append("	            System.out.println(\"JerryDebugMysql:\"+ url);\n");
		sb.append("	            connection = DriverManager.getConnection(url,tdatabaseUName,tdatabasePWord);\n");
		sb.append("			}else {\n");
		sb.append("				if (connection != null) return connection;\n");
		sb.append("				else {\n");
		sb.append("					Class.forName(\"com.mysql.jdbc.Driver\");\n");
		sb.append("		            String url = tdatabaseURL + tdatabaseName + \"?useUnicode=true&characterEncoding=utf-8\";\n");
		sb.append("		            System.out.println(\"JerryDebugMysql:\"+ url);\n");
		sb.append("		            connection = DriverManager.getConnection(url,tdatabaseUName,tdatabasePWord);\n");
		sb.append("		            connection.setAutoCommit(false);\n");
		sb.append("				}\n");
		sb.append("			}\n");
		sb.append("        } catch (Exception e){\n");
		sb.append("            throw new IOException(e.getMessage());\n");
		sb.append("        }\n");
		sb.append("\n");
		sb.append("        DBConf.setConnection(connection);\n");
		sb.append("\n");
		sb.append("        return connection;\n");
		sb.append("    }\n");
		sb.append("\n");	
		sb.append("    public static synchronized void closeDB(Connection connection) {\n");
		sb.append("        try {\n");
		sb.append("        	if (multiclause==false){\n");
		sb.append("	           connection.close();\n");
		sb.append("	           connection = null;\n");
		sb.append("        	}\n");
		sb.append("        } catch (Exception e){\n");
		sb.append("       }\n");
		sb.append("   }\n");
		sb.append("\n");
		sb.append("    public static synchronized void closeDB(Connection connection,boolean closemulti) {\n");
		sb.append("        try {\n");
		sb.append("           if (multiclause==false){\n");
		sb.append("        	   connection.close();\n");
		sb.append("        	   connection = null;\n");
		sb.append("           } else if (multiclause==true&&closemulti==true){\n");
		sb.append("        	   connection.commit();\n");
		sb.append("        	   connection.close();\n");
		sb.append("        	   connection = null;\n");
		sb.append("           }\n");
		sb.append("        } catch (Exception e){\n");
		sb.append("//		           connection.rollback();\n");
		sb.append("//		     	   connection.close();\n");
		sb.append("     	   connection = null;\n");
		sb.append("       }\n");
		sb.append("   }\n");
		sb.append("\n");
		sb.append("     public static void switchToTest(){\n");
		sb.append("    	isNotTest = false;\n");
		sb.append("     }\n");
		sb.append("\n");
		sb.append("     public  static void switchToProduction(){\n");
		sb.append("    	 isNotTest = true;\n");
		sb.append("    }\n");
		sb.append("\n");
		sb.append("	public static void setConnection(Connection connection) {\n");
		sb.append("		DBConf.connection = connection;\n");
		sb.append("	}\n");
		sb.append("\n");
		sb.append("	public static boolean isTestsuiteOffline() {\n");
		sb.append("		return isTestsuiteOffline;\n");
		sb.append("	}\n");
		sb.append("\n");
		sb.append("	public static boolean isGpinterfaceOffline() {\n");
		sb.append("		return isGpinterfaceOffline;\n");
		sb.append("	}	\n");
		sb.append("}\n");
		sb.append("\n");
		return sb.toString();
	}
	


}
