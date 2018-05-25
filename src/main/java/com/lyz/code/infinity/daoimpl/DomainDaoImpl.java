package com.lyz.code.infinity.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lyz.code.infinity.dao.DomainDao;
import com.lyz.code.infinity.database.DBConf;
import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Field;
import com.lyz.code.infinity.domain.Naming;

public class DomainDaoImpl implements DomainDao {

	@Override
	public String generateDomainString(Naming naming, String standardName)
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
        Domain domain = new Domain();
        ResultSet result = ps.executeQuery();

        while(result.next()) {
        	domain.setDomainId(new Field(result.getString("domain_id"),"long"));
        	domain.setStandardName(result.getString("domain_name"));
        	domain.addField(result.getString("domain_field_type"), result.getString("domain_field_name"));        	
        }
        DBConf.closeDB(connection);
        return domain.generateClassString();
	}

	@Override
	public String generateDomainString(long namingid) {
		// TODO Auto-generated method stub
		return null;
	}

}
