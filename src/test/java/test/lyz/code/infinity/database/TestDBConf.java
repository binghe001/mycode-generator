package test.lyz.code.infinity.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

public class TestDBConf{
    private static String databaseURL = "localhost";
    private static String databaseUName = "root";
    private static String databasePWord = "jerry";
    private static String databaseName = "jiangnan_taste";
    private static Connection connection;

    public static String getDatabaseURL(){
        return databaseURL;
    }

    public static String  getDatabaseUName(){
        return databaseUName;
    }

    public static String  getDatabasePWord(){
        return databasePWord;
    }

    public static String  getDatabaseName(){
        return databaseName;
    }
    
    public static Connection initDB() throws IOException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/"+TestDBConf.getDatabaseName()+"?user="+TestDBConf.getDatabaseUName()+"&password="+TestDBConf.getDatabasePWord();
            connection = DriverManager.getConnection(url);
        } catch (Exception e){
            throw new IOException(e.getMessage());
        }
        return connection;
    }
}
