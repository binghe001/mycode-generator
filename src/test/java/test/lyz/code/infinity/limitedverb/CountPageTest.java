package test.lyz.code.infinity.limitedverb;

import org.junit.Test;

import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Field;
import com.lyz.code.infinity.limitedverb.CountPage;

public class CountPageTest {
	@Test
	public void testCountPage(){
		CountPage countPage = new CountPage();
		
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
		
		countPage.setDomain(domain);
		
		System.out.println(countPage.generateDaoImplMethodString());
		System.out.println(countPage.generateDaoImplMethodStringWithSerial());
		System.out.println("========================Dao Definintion===========");
		System.out.println(countPage.generateDaoMethodDefinitionString());
		System.out.println("========================ServiceImpl===========");
		System.out.println(countPage.generateServiceImplMethodStringWithSerial());
		System.out.println("========================Service===============");
		System.out.println(countPage.generateServiceMethodDefinitionString());
	}
}
