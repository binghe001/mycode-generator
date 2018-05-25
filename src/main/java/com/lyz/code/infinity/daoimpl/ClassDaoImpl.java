package com.lyz.code.infinity.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lyz.code.infinity.dao.ClassDao;
import com.lyz.code.infinity.database.DBConf;
import com.lyz.code.infinity.domain.Class;
import com.lyz.code.infinity.domain.Method;
import com.lyz.code.infinity.domain.Naming;

public class ClassDaoImpl implements ClassDao {

	@Override
	public String generateClassString(Naming naming, String standardName)
			throws Exception {
		Class clazz = this.generateClass(naming, standardName);
		return clazz.generateClassString();
	}
	
	@Override
	public Class generateClass(Naming naming, String standardName)
			throws Exception {
		Connection connection = DBConf.initDB();
        String query = "SELECT * FROM domain,domain_fields,naming " +
        		"where domain.domain_id = domain_fields.domain_id " + 
        		"and naming.naming_id=domain.naming_id " +
        		"and naming.naming_id = ? " +
        		"and domain.domain_name=? ";
        
        PreparedStatement ps = connection.prepareStatement(query);
        
        ps.setLong(1,naming.getNamingid());
        ps.setString(2, standardName);
        Class domain = new Class();
        ResultSet result = ps.executeQuery();

        while(result.next()) {
   	
        }
        DBConf.closeDB(connection);     
		return null;
	}

}
