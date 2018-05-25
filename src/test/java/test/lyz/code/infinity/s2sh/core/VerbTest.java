package test.lyz.code.infinity.s2sh.core;

import org.junit.Test;

import com.lyz.code.infinity.core.Verb;
import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Field;
import com.lyz.code.infinity.limitedverb.NoControllerVerb;
import com.lyz.code.infinity.s2sh.verb.CountAllPage;
import com.lyz.code.infinity.s2sh.verb.S2SHVerbFactory;

public class VerbTest {
	@Test
	public void testListAll() throws Exception{
		Verb listAll = S2SHVerbFactory.getInstance("listAll");
		
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
		
		listAll.setDomain(domain);
		
		System.out.println("========================Dao ImplementWithSerial===========");
		System.out.println(listAll.generateDaoImplMethodStringWithSerial());		
		System.out.println("========================Dao Implement===========");
		System.out.println(listAll.generateDaoImplMethodString());
		System.out.println("========================Dao Definintion===========");
		System.out.println(listAll.generateDaoMethodDefinitionString());
		System.out.println("========================ServiceImplWithSerial===========");
		System.out.println(listAll.generateServiceImplMethodStringWithSerial());
		System.out.println("========================ServiceImpl===========");
		System.out.println(listAll.generateServiceImplMethodString());
		System.out.println("========================Service===============");
		System.out.println(listAll.generateServiceMethodDefinitionString());
		System.out.println("=======================Action=============");
		System.out.println(listAll.generateControllerMethodString());
		System.out.println("=======================ActionWithSerial===========");
		System.out.println(listAll.generateControllerMethodStringWithSerial());
		System.out.println("========================Facade===========");
		System.out.println(listAll.generateFacadeMethodString());
		System.out.println("========================FacadeWithSerial===========");
		System.out.println(listAll.generateFacadeMethodStringWithSerial());
	}
	
	
	@Test
	public void testDelete() throws Exception{
		Verb delete = S2SHVerbFactory.getInstance("delete");
		
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
		
		delete.setDomain(domain);
		
		System.out.println("========================Dao Implement===========");
		System.out.println(delete.generateDaoImplMethodStringWithSerial());
		System.out.println(delete.generateDaoImplMethodString());
		System.out.println("========================Dao Definintion===========");
		System.out.println(delete.generateDaoMethodDefinitionString());
		System.out.println("========================ServiceImpl===========");
		System.out.println(delete.generateServiceImplMethodStringWithSerial());
		System.out.println(delete.generateServiceImplMethodString());
		System.out.println("========================Service===============");
		System.out.println(delete.generateServiceMethodDefinitionString());
		System.out.println("=======================Action=============");
		System.out.println(delete.generateControllerMethodString());
		System.out.println("=======================ActionWithSerial===========");
		System.out.println(delete.generateControllerMethodStringWithSerial());
		System.out.println("========================Facade===========");
		System.out.println(delete.generateFacadeMethodString());
		System.out.println("========================FacadeWithSerial===========");
		System.out.println(delete.generateFacadeMethodStringWithSerial());
	}
	
	@Test
	public void testSoftDelete() throws Exception{
		Verb softdelete = S2SHVerbFactory.getInstance("softDelete");
		
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
		domain.setActive(new Field("active", "boolean"));
		
		softdelete.setDomain(domain);
		
		System.out.println("========================Dao Implement===========");
		System.out.println(softdelete.generateDaoImplMethodStringWithSerial());
		System.out.println(softdelete.generateDaoImplMethodString());
		System.out.println("========================Dao Definintion===========");
		System.out.println(softdelete.generateDaoMethodDefinitionString());
		System.out.println("========================ServiceImplWithSerial===========");
		System.out.println(softdelete.generateServiceImplMethodStringWithSerial());
		System.out.println("========================ServiceImpl===========");
		System.out.println(softdelete.generateServiceImplMethodString());
		System.out.println("========================Service===============");
		System.out.println(softdelete.generateServiceMethodDefinitionString());
		System.out.println("=======================Action=============");
		System.out.println(softdelete.generateControllerMethodString());
		System.out.println("=======================ActionWithSerial===========");
		System.out.println(softdelete.generateControllerMethodStringWithSerial());
		System.out.println("========================Facade===========");
		System.out.println(softdelete.generateFacadeMethodString());
		System.out.println("========================FacadeWithSerial===========");
		System.out.println(softdelete.generateFacadeMethodStringWithSerial());
	}
	
	@Test
	public void testAdd() throws Exception{
		Verb add = S2SHVerbFactory.getInstance("add");
		
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
		domain.setActive(new Field("active", "boolean"));
		
		add.setDomain(domain);
		
		System.out.println("========================Dao Implement===========");
		System.out.println(add.generateDaoImplMethodStringWithSerial());
		System.out.println(add.generateDaoImplMethodString());
		System.out.println("========================Dao Definintion===========");
		System.out.println(add.generateDaoMethodDefinitionString());
		System.out.println("========================ServiceImpl===========");
		System.out.println(add.generateServiceImplMethodStringWithSerial());
		System.out.println(add.generateServiceImplMethodString());
		System.out.println("========================Service===============");
		System.out.println(add.generateServiceMethodDefinitionString());
		System.out.println("=======================Action=============");
		System.out.println(add.generateControllerMethodString());
		System.out.println("=======================ActionWithSerial===========");
		System.out.println(add.generateControllerMethodStringWithSerial());
		System.out.println("========================Facade===========");
		System.out.println(add.generateFacadeMethodString());
		System.out.println("========================FacadeWithSerial===========");
		System.out.println(add.generateFacadeMethodStringWithSerial());
	}
	
	@Test
	public void testListActive() throws Exception{
		Verb listActive = S2SHVerbFactory.getInstance("listActive");
		
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
		domain.setActive(new Field("active", "boolean"));
		
		listActive.setDomain(domain);
		
		System.out.println("========================Dao Implement===========");
		System.out.println(listActive.generateDaoImplMethodStringWithSerial());
		System.out.println(listActive.generateDaoImplMethodString());
		System.out.println("========================Dao Definintion===========");
		System.out.println(listActive.generateDaoMethodDefinitionString());
		System.out.println("========================ServiceImpl===========");
		System.out.println(listActive.generateServiceImplMethodStringWithSerial());
		System.out.println(listActive.generateServiceImplMethodString());
		System.out.println("========================Service===============");
		System.out.println(listActive.generateServiceMethodDefinitionString());
		System.out.println("=======================Action=============");
		System.out.println(listActive.generateControllerMethodString());
		System.out.println("=======================ActionWithSerial===========");
		System.out.println(listActive.generateControllerMethodStringWithSerial());
		System.out.println("========================Facade===========");
		System.out.println(listActive.generateFacadeMethodString());
		System.out.println("========================FacadeWithSerial===========");
		System.out.println(listActive.generateFacadeMethodStringWithSerial());
	}
	
	@Test
	public void testUpdate() throws Exception{
		Verb update = S2SHVerbFactory.getInstance("update");
		
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
		domain.setDomainName(new Field("employeeName","String"));
		domain.setActive(new Field("active", "boolean"));
		
		update.setDomain(domain);
		
		System.out.println("========================Dao Implement===========");
		System.out.println(update.generateDaoImplMethodStringWithSerial());
		System.out.println(update.generateDaoImplMethodString());
		System.out.println("========================Dao Definintion===========");
		System.out.println(update.generateDaoMethodDefinitionString());
		System.out.println("========================ServiceImpl===========");
		System.out.println(update.generateServiceImplMethodStringWithSerial());
		System.out.println(update.generateServiceImplMethodString());
		System.out.println("========================Service===============");
		System.out.println(update.generateServiceMethodDefinitionString());
		System.out.println(update.generateServiceMethodDefinitionString());
		System.out.println("=======================Action=============");
		System.out.println(update.generateControllerMethodString());
		System.out.println("=======================ActionWithSerial===========");
		System.out.println(update.generateControllerMethodStringWithSerial());
		System.out.println("========================Facade===========");
		System.out.println(update.generateFacadeMethodString());
		System.out.println("========================FacadeWithSerial===========");
		System.out.println(update.generateFacadeMethodStringWithSerial());
	}
	
	@Test
	public void testSearchByName() throws Exception{
		Verb searchByName = S2SHVerbFactory.getInstance("searchByName");
		
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
		domain.setDomainName(new Field("employeeName","String"));
		domain.setActive(new Field("active", "boolean"));
		
		searchByName.setDomain(domain);
		
		System.out.println("========================Dao Implement===========");
		System.out.println(searchByName.generateDaoImplMethodStringWithSerial());
		System.out.println(searchByName.generateDaoImplMethodString());
		System.out.println("========================Dao Definintion===========");
		System.out.println(searchByName.generateDaoMethodDefinitionString());
		System.out.println("========================ServiceImpl===========");
		System.out.println(searchByName.generateServiceImplMethodStringWithSerial());
		System.out.println(searchByName.generateServiceImplMethodString());
		System.out.println("========================Service===============");
		System.out.println(searchByName.generateServiceMethodDefinitionString());
		System.out.println(searchByName.generateServiceMethodDefinitionString());
		System.out.println("=======================Action=============");
		System.out.println(searchByName.generateControllerMethodString());
		System.out.println("=======================ActionWithSerial===========");
		System.out.println(searchByName.generateControllerMethodStringWithSerial());
		System.out.println("========================Facade===========");
		System.out.println(searchByName.generateFacadeMethodString());
		System.out.println("========================FacadeWithSerial===========");
		System.out.println(searchByName.generateFacadeMethodStringWithSerial());
	}
	
	@Test
	public void testCountAllPage() throws Exception{
		NoControllerVerb countAllPage = new CountAllPage();
		
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
		domain.setDomainName(new Field("employeeName","String"));
		domain.setActive(new Field("active", "boolean"));
		
		countAllPage.setDomain(domain);
		System.out.println("========================Dao Implement===========");
		System.out.println(countAllPage.generateDaoImplMethodStringWithSerial());
		System.out.println(countAllPage.generateDaoImplMethodString());
		System.out.println("========================Dao Definintion===========");
		System.out.println(countAllPage.generateDaoMethodDefinitionString());
		System.out.println("========================ServiceImpl===========");
		System.out.println(countAllPage.generateServiceImplMethodStringWithSerial());
		System.out.println(countAllPage.generateServiceImplMethodString());
		System.out.println("========================Service===============");
		System.out.println(countAllPage.generateServiceMethodDefinitionString());
		System.out.println(countAllPage.generateServiceMethodDefinitionString());
	}
	
	@Test
	public void testListAllByPage() throws Exception{		
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
		
		Verb listAllByPage = S2SHVerbFactory.getInstance("listAllByPage",domain);
		
		System.out.println("========================Dao Implement===========");
		System.out.println(listAllByPage.generateDaoImplMethodStringWithSerial());
		System.out.println(listAllByPage.generateDaoImplMethodString());
		System.out.println("========================Dao Definintion===========");
		System.out.println(listAllByPage.generateDaoMethodDefinitionString());
		System.out.println("========================ServiceImpl===========");
		System.out.println(listAllByPage.generateServiceImplMethodStringWithSerial());
		System.out.println(listAllByPage.generateServiceImplMethodString());
		System.out.println("========================Service===============");
		System.out.println(listAllByPage.generateServiceMethodDefinitionString());
		System.out.println("=======================Action=============");
		System.out.println(listAllByPage.generateControllerMethodString());
		System.out.println("=======================ActionWithSerial===========");
		System.out.println(listAllByPage.generateControllerMethodStringWithSerial());
		System.out.println("========================Facade===========");
		System.out.println(listAllByPage.generateFacadeMethodString());
		System.out.println("========================FacadeWithSerial===========");
		System.out.println(listAllByPage.generateFacadeMethodStringWithSerial());
	}
	
	@Test
	public void testFindById() throws Exception{		
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
		
		Verb findById = S2SHVerbFactory.getInstance("findById",domain);
		
		System.out.println("========================Dao Implement===========");
		System.out.println(findById.generateDaoImplMethodStringWithSerial());
		System.out.println(findById.generateDaoImplMethodString());
		System.out.println("========================Dao Definintion===========");
		System.out.println(findById.generateDaoMethodDefinitionString());
		System.out.println("========================ServiceImpl===========");
		System.out.println(findById.generateServiceImplMethodStringWithSerial());
		System.out.println(findById.generateServiceImplMethodString());
		System.out.println("========================Service===============");
		System.out.println(findById.generateServiceMethodDefinitionString());
		System.out.println("=======================Action=============");
		System.out.println(findById.generateControllerMethodString());
		System.out.println("=======================ActionWithSerial===========");
		System.out.println(findById.generateControllerMethodStringWithSerial());
		System.out.println("========================Facade===========");
		System.out.println(findById.generateFacadeMethodString());
		System.out.println("========================FacadeWithSerial===========");
		System.out.println(findById.generateFacadeMethodStringWithSerial());
	}
	
	@Test
	public void testFindByName() throws Exception{		
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
		
		Verb findByName = S2SHVerbFactory.getInstance("findByName",domain);
		
		System.out.println("========================Dao Implement===========");
		System.out.println(findByName.generateDaoImplMethodStringWithSerial());
		System.out.println(findByName.generateDaoImplMethodString());
		System.out.println("========================Dao Definintion===========");
		System.out.println(findByName.generateDaoMethodDefinitionString());
		System.out.println("========================ServiceImpl===========");
		System.out.println(findByName.generateServiceImplMethodStringWithSerial());
		System.out.println(findByName.generateServiceImplMethodString());
		System.out.println("========================Service===============");
		System.out.println(findByName.generateServiceMethodDefinitionString());
		System.out.println("=======================Action=============");
		System.out.println(findByName.generateControllerMethodString());
		System.out.println("=======================ActionWithSerial===========");
		System.out.println(findByName.generateControllerMethodStringWithSerial());
		System.out.println("========================Facade===========");
		System.out.println(findByName.generateFacadeMethodString());
		System.out.println("========================FacadeWithSerial===========");
		System.out.println(findByName.generateFacadeMethodStringWithSerial());
	}
}
