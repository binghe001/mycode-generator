package test.lyz.code.infinity.generator;

import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.generator.MysqlDBDefinitionGenerator;

import junit.framework.Assert;
import junit.framework.TestCase;

public class MysqlDBDefinitionGeneratorTest extends TestCase {
	public void testGenerateDBSql() throws Exception{
		Domain d = new Domain();
		d.setStandardName("Leave");
		d.addField( "id","long");
		d.addField("name", "String");
		d.addField("comment","String");
		d.addField("description", "String");
		d.addField("price", "double");
		d.addField("amount", "int");
		
		Domain d2 = new Domain();
		d2.setStandardName("LeaveLimit");
		d2.addField("leaveLimitId","long");
		d2.addField("leaveLimitName", "String");
		d2.addField("leaveLimitComment","String");
		d2.addField("leaveLimitDescription", "String");
		
		MysqlDBDefinitionGenerator generator = new MysqlDBDefinitionGenerator();
		generator.setDbName("myleave");
		generator.addDomain(d);
		generator.addDomain(d2);
		
		System.out.println(generator.generateDBSql());

	}
	
	public void testCompareDomain(){
		Domain d = new Domain();
		d.setStandardName("Leave");
		d.addField( "id","long");
		d.addField("name", "String");
		d.addField("comment","String");
		d.addField("description", "String");
		d.addField("price", "double");
		d.addField("amount", "int");
		
		Domain d2 = new Domain();
		d2.setStandardName("LeaveLimit");
		d2.addField("leaveLimitId","long");
		d2.addField("leaveLimitName", "String");
		d2.addField("leaveLimitComment","String");
		d2.addField("leaveLimitDescription", "String");
		
		Assert.assertTrue(d.compareTo(d2)!= 0);
		
	}
}
