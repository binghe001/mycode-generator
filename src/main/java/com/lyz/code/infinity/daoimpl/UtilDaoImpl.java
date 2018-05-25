package com.lyz.code.infinity.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.lyz.code.infinity.dao.UtilDao;
import com.lyz.code.infinity.database.DBConf;
import com.lyz.code.infinity.domain.Naming;
import com.lyz.code.infinity.domain.Util;

public class UtilDaoImpl implements UtilDao{

	@Override
	public String generateUtilString(Naming naming, String standardName) throws Exception {
		 	Connection connection = DBConf.initDB();
	        String query = "SELECT * FROM utils where naming_id = ? and standard_name=?";
	        
	        PreparedStatement ps = connection.prepareStatement(query);
	        
	        ps.setLong(1,naming.getNamingid());
	        ps.setString(2, standardName);
	        String content = "";
	        ResultSet result = ps.executeQuery();

	        while(result.next()) {            
	        	content = result.getString("content");	            
	        }
	        DBConf.closeDB(connection);
	        return content;
	}

}
