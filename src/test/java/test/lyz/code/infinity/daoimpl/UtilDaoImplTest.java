package test.lyz.code.infinity.daoimpl;

import java.sql.Connection;
import java.util.List;

import junit.framework.Assert;
import test.lyz.code.infinity.database.DataSafeTestCase;

import com.lyz.code.infinity.dao.UtilDao;
import com.lyz.code.infinity.daoimpl.UtilDaoImpl;
import com.lyz.code.infinity.database.DBConf;
import com.lyz.code.infinity.domain.Naming;
import com.lyz.code.infinity.domain.Util;

public class UtilDaoImplTest extends DataSafeTestCase {

	private UtilDao utilDao = new UtilDaoImpl();
     
	public UtilDao getUtilDao() {
		return utilDao;
	}

	public void setUtilDao(UtilDao utilDao) {
		this.utilDao = utilDao;
	}

	public void testGetUtilInfo() throws Exception
	{
		// prepare data
		Connection con = DBConf.getConnection();
		con.prepareStatement("delete from utils;").executeUpdate();
		con.prepareStatement("INSERT INTO `utils` (`util_id`, `naming_id`, `standard_name`, `content`, `comment`, `token`) VALUES (1, 10, 'DBConf', 'package com.lyz.code.database;\r\n\r\nimport java.io.IOException;\r\nimport java.sql.Connection;\r\nimport java.sql.DriverManager;\r\nimport java.sql.PreparedStatement;\r\nimport java.sql.ResultSet;\r\nimport java.sql.SQLException;\r\nimport java.sql.Statement;\r\n\r\n/**\r\n * DBConf must owned by the Project Manager\r\n * You can change the configure to fit your situation.\r\n * \r\n * This class should not change others except the databasename,\r\n * username and password\r\n * \r\n * @author Rocketship\r\n * @release under GPLv2\r\n * @email 2711098650@qq.com\r\n *\r\n */\r\npublic final class DBConf{\r\n    private static String databaseURL = \"\";\r\n    private static String databaseUName = \"\";\r\n    private static String databasePWord = \"\";\r\n    private static String databaseName = \"\";\r\n    \r\n    private static String testDatabaseURL = \"\";\r\n    private static String testDatabaseUName = \"\";\r\n    private static String testDatabasePWord = \"\";\r\n    private static String testDatabaseName = \"\";\r\n    \r\n    private static boolean isNotTest = false;					// Do not change this value, change them in dbconfig.xml	\r\n    private static boolean isProductProtectLockOffline = false;  	// In production: set true ; In test: set false\r\n    private static boolean isTestsuiteOffline = false;			// Test suite offline: set true ; Test suite online: set false\r\n    private static boolean isGpinterfaceOffline = false;	    // Testing gpinterface offline: set true ; Test suite online: set false\r\n    private static Connection connection;\r\n    \r\n    private Statement  stmt;\r\n	private ResultSet  rs;\r\n	private PreparedStatement pstmt;\r\n	private static int CON_COUNT=0;\r\n    \r\n    static {\r\n    	ReadConfigXml reader = new ReadConfigXml(\"dbconfig.xml\"); \r\n    	databaseURL = reader.getUrl();\r\n    	databaseName = reader.getDataBase();\r\n    	databaseUName  = reader.getUserName();\r\n    	databasePWord = reader.getPassWord();\r\n    	\r\n    	testDatabaseURL = reader.getTestUrl();\r\n    	testDatabaseName = reader.getTestDataBase();\r\n    	testDatabaseUName  = reader.getTestUserName();\r\n    	testDatabasePWord = reader.getTestPassWord();\r\n    	\r\n    	isNotTest = reader.isNotTest();\r\n    	isTestsuiteOffline = reader.isTestsuiteOffline();\r\n    	isProductProtectLockOffline = reader.isProductProtectLockOffline();\r\n    	isGpinterfaceOffline = reader.isGpinterfaceOffline();    	\r\n    }\r\n\r\n	public static Connection initDB() throws IOException{\r\n        try {\r\n			String tdatabaseURL =\"\";\r\n		    String tdatabaseUName =\"\";\r\n		    String tdatabasePWord = \"\";\r\n		    String tdatabaseName = \"\";\r\n			if (isNotTest && isProductProtectLockOffline){\r\n				tdatabaseURL = databaseURL;\r\n			    tdatabaseUName = databaseUName;\r\n			    tdatabasePWord = databasePWord;\r\n			    tdatabaseName = databaseName;\r\n			} else {\r\n				tdatabaseURL = testDatabaseURL;\r\n			    tdatabaseUName = testDatabaseUName;\r\n			    tdatabasePWord = testDatabasePWord;\r\n			    tdatabaseName = testDatabaseName;\r\n			}		\r\n\r\n            Class.forName(\"com.mysql.jdbc.Driver\");\r\n            String url = tdatabaseURL + tdatabaseName + \"?useUnicode=true&characterEncoding=utf-8\";\r\n            System.out.println(\"JerryDebugMysql:\"+ url);\r\n            connection = DriverManager.getConnection(url,tdatabaseUName,tdatabasePWord);\r\n        } catch (Exception e){\r\n            throw new IOException(e.getMessage());\r\n        }\r\n\r\n        DBConf.setConnection(connection);\r\n\r\n        return connection;\r\n    }\r\n\r\n     public static void closeDB(Connection connection) {\r\n         try {\r\n            connection.close();\r\n            connection = null;\r\n         } catch (Exception e){\r\n        }\r\n    }\r\n     \r\n     public static void switchToTest(){\r\n    	isNotTest = false;\r\n     }\r\n     \r\n     public  static void switchToProduction(){\r\n    	 isNotTest = true;\r\n    }\r\n\r\n	public static  Connection getConnection() {\r\n		try {\r\n			return initDB();\r\n		}catch(Exception e){\r\n			e.printStackTrace();\r\n			return null;\r\n		}\r\n	}\r\n\r\n	public static void setConnection(Connection connection) {\r\n		DBConf.connection = connection;\r\n	}\r\n\r\n	public static boolean isTestsuiteOffline() {\r\n		return isTestsuiteOffline;\r\n	}\r\n\r\n	public static boolean isGpinterfaceOffline() {\r\n		return isGpinterfaceOffline;\r\n	}\r\n	\r\n	public static synchronized Connection getCon()throws Exception{\r\n		return getConnection();\r\n	}\r\n	\r\n	public Statement getStmtread(){\r\n		try{\r\n			connection=getConnection();\r\n			stmt=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);\r\n			return stmt;\r\n		}catch(Exception e){\r\n			System.err.println(e.getMessage());\r\n			e.printStackTrace();\r\n		}\r\n		return null;\r\n	}\r\n	\r\n	public ResultSet getRs(String sql){\r\n		try{\r\n			stmt=getStmtread();\r\n			rs=stmt.executeQuery(sql);\r\n			return rs;\r\n		}catch(Exception e){\r\n			System.err.println(e.getMessage());\r\n			e.printStackTrace();\r\n		}\r\n		return null;\r\n	}\r\n	\r\n	public Statement getStmt(){\r\n		try{\r\n			connection=getConnection();\r\n			stmt=connection.createStatement();\r\n			return stmt;\r\n		}catch(Exception e){\r\n			System.err.println(e.getMessage());\r\n			e.printStackTrace();\r\n		}\r\n		return null;\r\n	}\r\n	\r\n	public int getConcount(){\r\n		return CON_COUNT;\r\n	}\r\n	\r\n	public PreparedStatement getPstmt(String sql){\r\n		try{\r\n			connection=getConnection();\r\n			pstmt=connection.prepareStatement(sql);\r\n			return pstmt;\r\n		}catch(Exception e){\r\n			System.err.println(e.getMessage());\r\n			e.printStackTrace();\r\n		}\r\n		return null;\r\n	}\r\n	\r\n	public synchronized void close(){\r\n		try{\r\n			if(rs!=null){\r\n				rs.close(); rs=null;\r\n			}\r\n		}catch(Exception e){\r\n			System.err.println(e.getMessage());\r\n			e.printStackTrace();\r\n		}\r\n		try{\r\n			if(stmt!=null){\r\n				stmt.close(); stmt=null;\r\n			}\r\n		}catch(Exception e){\r\n			System.err.println(e.getMessage());\r\n			e.printStackTrace();\r\n		}\r\n		try{\r\n			if(connection!=null){\r\n				connection.close(); connection=null; CON_COUNT--;\r\n			}\r\n		}catch(Exception e){\r\n			System.err.println(e.getMessage());\r\n			e.printStackTrace();\r\n		}\r\n	}\r\n\r\n	public ResultSet executeQuery(String s)\r\n	        throws SQLException\r\n	    {\r\n			if( connection == null){\r\n				DBConf.getConnection();\r\n			}\r\n			stmt = connection.createStatement();\r\n	        rs = stmt.executeQuery(s);\r\n	        return rs;\r\n	    }\r\n\r\n	    public void executeUpdate(String s)\r\n	        throws SQLException\r\n	    {\r\n			if( connection == null){\r\n				DBConf.getConnection();\r\n			}\r\n	        stmt = connection.createStatement();\r\n	        stmt.executeUpdate(s);\r\n	        if(stmt != null)\r\n	            stmt.close();\r\n	    }\r\n	    \r\n	    public int update(String s)\r\n		        throws SQLException\r\n		    {\r\n				if( connection == null){\r\n					DBConf.getConnection();\r\n				}\r\n				int retval = 0;\r\n		        stmt = connection.createStatement();\r\n		        retval = stmt.executeUpdate(s);\r\n		        if(stmt != null)\r\n		            stmt.close();\r\n		        return retval;\r\n		    }\r\n\r\n	    public Connection openConnection(){\r\n	    	return getConnection();\r\n	    }\r\n}', NULL, NULL);").executeUpdate();
		
		// Test begin
		Naming naming = new Naming();
		naming.setNamingid(10L);
		String content = getUtilDao().generateUtilString(naming, "DBConf");	
		System.out.println(content);

		Assert.assertTrue(content.length()>0);
		
		// Clean
		con.prepareStatement("delete from utils;").executeUpdate();
		
	}
}
