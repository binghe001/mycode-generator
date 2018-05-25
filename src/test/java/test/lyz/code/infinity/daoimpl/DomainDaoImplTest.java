package test.lyz.code.infinity.daoimpl;

import java.sql.Connection;

import junit.framework.Assert;
import test.lyz.code.infinity.database.DataSafeTestCase;

import com.lyz.code.infinity.dao.DomainDao;
import com.lyz.code.infinity.daoimpl.DomainDaoImpl;
import com.lyz.code.infinity.database.DBConf;
import com.lyz.code.infinity.domain.Naming;

public class DomainDaoImplTest extends DataSafeTestCase {

	private DomainDao domainDao = new DomainDaoImpl();
     
	public DomainDao getDomainDao() {
		return domainDao;
	}

	public void setDomainDao(DomainDao domainDao) {
		this.domainDao = domainDao;
	}

	public void testGenerateDomainString() throws Exception
	{
		// prepare data
		Connection con = DBConf.getConnection();
		//con.prepareStatement("delete from domain;").executeUpdate();
		// Test begin
		Naming naming = new Naming();
		naming.setNamingid(8L);
		String domainStr = getDomainDao().generateDomainString(naming, "Leave");	
		System.out.println(domainStr);

		Assert.assertTrue(domainStr.length()>0);
		
		// Clean
		//con.prepareStatement("delete from domain;").executeUpdate();
		
	}
	

}
