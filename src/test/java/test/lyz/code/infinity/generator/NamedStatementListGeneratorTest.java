package test.lyz.code.infinity.generator;

import junit.framework.TestCase;

import org.junit.Test;

import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Field;
import com.lyz.code.infinity.domain.StatementList;
import com.lyz.code.infinity.generator.NamedStatementListGenerator;
import com.lyz.code.infinity.utils.InterVarUtil;

public class NamedStatementListGeneratorTest extends TestCase{

	@Test
	public void testGenerateSelectAllQueryStatementList() {
		Domain domain = new Domain();
		domain.setPackageToken("com.lyz.code.infinity");
		domain.setStandardName("Employee");
		domain.addField("EmployeeId","long");
		domain.addField("Name", "String");
		domain.addField("Gender", "String");
		domain.addField("Age", "int");
		domain.addField("EmployeeDescription", "String");
		domain.addField("EmployeeComment","String");
		domain.addField("updateTime","Timestamp", "java.sql");
		
		StatementList sl = NamedStatementListGenerator.generateSelectAllQueryStatementList( 100L,2,domain);
		System.out.println(sl.getContentWithSerial());
	}
	

	@Test
	public void testGeneratePsSetDomainFields(){
		Domain domain = new Domain();
		domain.setPackageToken("com.lyz.code.infinity");
		domain.setStandardName("Employee");
		domain.addField("EmployeeId","long");
		domain.addField("Name", "String");
		domain.addField("Gender", "String");
		domain.addField("Age", "int");
		domain.addField("EmployeeDescription", "String");
		domain.addField("EmployeeComment","String");
		domain.addField("updateTime","Timestamp", "java.sql");
		
		System.out.println(NamedStatementListGenerator.generatePsSetDomainFields(1000L,2, domain, InterVarUtil.DB.query).getContentWithSerial());
		
	}
	
	@Test
	public void testFindById(){
		Domain domain = new Domain();
		domain.setPackageToken("com.lyz.code.infinity");
		domain.setStandardName("Employee");
		domain.setPlural("Employee");
		domain.addField("name", "String");
		domain.addField("gender", "String");
		domain.addField("age", "int");
		domain.addField("employeeDescription", "String");
		domain.addField("employeeComment","String");
		domain.addField("updateTime","Timestamp", "java.sql");
		domain.setDomainId(new Field("employeeId","long"));
		
		System.out.println(NamedStatementListGenerator.generateFindByIdStatementList(1000L,2, domain).getContentWithSerial());
		
	}
	
	@Test
	public void testGenerateSelectActiveStatementList(){
			Domain domain = new Domain();
			domain.setPackageToken("com.lyz.code.infinity");
			domain.setStandardName("Employee");
			domain.setPlural("Employee");
			domain.addField("name", "String");
			domain.addField("gender", "String");
			domain.addField("age", "int");
			domain.addField("employeeDescription", "String");
			domain.addField("employeeComment","String");
			domain.addField("updateTime","Timestamp", "java.sql");
			domain.setDomainId(new Field("employeeId","long"));
			domain.setActive(new Field("active","boolean"));
			
			System.out.println(NamedStatementListGenerator.generateSelectActiveStatementList(1000L,2, domain).getContentWithSerial());
			
	}
}
