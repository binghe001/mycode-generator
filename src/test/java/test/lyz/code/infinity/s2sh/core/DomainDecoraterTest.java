package test.lyz.code.infinity.s2sh.core;

import org.junit.Test;

import com.lyz.code.infinity.core.Verb;
import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Field;
import com.lyz.code.infinity.limitedverb.NoControllerVerb;
import com.lyz.code.infinity.s2sh.core.S2SHDomainDecorater;
import com.lyz.code.infinity.s2sh.verb.CountAllPage;
import com.lyz.code.infinity.s2sh.verb.S2SHVerbFactory;

public class DomainDecoraterTest {
	@Test
	public void testDomainDecorater() throws Exception{

		
		Domain domain = new Domain();
		domain.setPackageToken("com.lyz.code.infinity");
		domain.setStandardName("Employee");
		domain.setPlural("Employee");
		domain.addField("employeeId","long");
		domain.addField("name", "String");
		domain.addField("gender", "String");
		domain.addField("age", "int");
		domain.addField("employeeDescription", "String");
		domain.addField("employeeComment","String");
		domain.addField("updateTime","Timestamp", "java.sql");
		domain.setDomainId(new Field("employeeId", "long"));
		domain.setDomainName(new Field("employeeName", "String"));
		domain.setActive(new Field("active", "boolean"));
		
		System.out.println(S2SHDomainDecorater.generateDecroatedDomainString(domain));
	}
	
	
}
