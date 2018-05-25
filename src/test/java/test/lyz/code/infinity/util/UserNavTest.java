package test.lyz.code.infinity.util;

import junit.framework.TestCase;

import org.junit.Test;

import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Field;
import com.lyz.code.infinity.include.UserNav;

public class UserNavTest extends TestCase{
	
	@Test
	public void testGenerateJSPString() throws Exception{
		Domain domain = new Domain();
		domain.setPackageToken("com.lyz.code.infinity");
		domain.setStandardName("Employee");
		domain.setPlural("Employee");
		domain.addField("EmployeeId","long");
		domain.addField("Name", "String");
		domain.addField("Gender", "String");
		domain.addField("Age", "int");
		domain.addField("EmployeeDescription", "String");
		domain.addField("EmployeeComment","String");
		domain.addField("updateTime","Timestamp", "java.sql");
		
		Domain user = new Domain();
		user.setPackageToken("com.lyz.code.infinity");
		user.setStandardName("User");
		user.setDomainName(new Field("userName","String"));
		user.setDomainId(new Field("userId","long"));
		user.addField("userId", "long");
		user.addField("gender","String");
		user.addField("age","int");
		user.addField("userDescription","String");
		user.addField("userComment","String");
		user.addField("content", "String");
		//user.addField("active","boolean")
		user.setActive(new Field("active","boolean"));
		user.addField("userName","String");
		
		UserNav usernav = new UserNav();
		usernav.addDomain(domain);
		usernav.addDomain(user);
		
		System.out.println(usernav.generateIncludeString());
	}
	
}
