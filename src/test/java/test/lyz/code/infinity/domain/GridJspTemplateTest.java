package test.lyz.code.infinity.domain;

import org.junit.Test;

import com.lyz.code.infinity.core.Verb;
import com.lyz.code.infinity.core.VerbFactory;
import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Field;
import com.lyz.code.infinity.generator.GridJspTemplate;

public class GridJspTemplateTest {
	@Test
	public void testGridJspTemplate() throws Exception{
		Verb listAll = VerbFactory.getInstance("listAll");
		
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
		GridJspTemplate grid = new GridJspTemplate(domain);
		System.out.println(grid.generateJspString());
		
		System.out.println(grid.generateStatementList().getContentWithSerial());
	}
	
	
}
