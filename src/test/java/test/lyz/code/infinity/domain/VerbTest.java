package test.lyz.code.infinity.domain;

import org.junit.Test;

import com.lyz.code.infinity.core.Verb;
import com.lyz.code.infinity.core.VerbFactory;
import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Field;
import com.lyz.code.infinity.s2sh.verb.S2SHVerbFactory;

public class VerbTest {
	@Test
	public void testListAll()  throws Exception{
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
		
		listAll.setDomain(domain);
		
		System.out.println(listAll.generateDaoImplMethodStringWithSerial());
		System.out.println("========================Dao Definintion===========");
		System.out.println(listAll.generateDaoMethodDefinitionString());
		System.out.println("========================ServiceImpl===========");
		System.out.println(listAll.generateServiceImplMethodStringWithSerial());
		System.out.println("========================Service===============");
		System.out.println(listAll.generateServiceMethodDefinitionString());
		System.out.println("=======================Controller=============");
		System.out.println(listAll.generateControllerMethodString());
		System.out.println("========================ControllerWithSerial===========");
		System.out.println(listAll.generateControllerMethodStringWithSerial());
		System.out.println("========================Facade===========");
		System.out.println(listAll.generateFacadeMethodString());
		System.out.println("========================FacadeWithSerial===========");
		System.out.println(listAll.generateFacadeMethodStringWithSerial());
	}
	
	@Test
	public void testListActive() throws Exception{	
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
		domain.setActive(new Field("active","boolean"));
		
		Verb listActive = VerbFactory.getInstance("listActive",domain);
		
		System.out.println("========================Dao===============");
		System.out.println(listActive.generateDaoMethodDefinitionString());
		System.out.println("========================DaoImpl===========");
		System.out.println(listActive.generateDaoImplMethodString());
		System.out.println("========================Service===========");
		System.out.println(listActive.generateServiceMethodDefinitionString());
		System.out.println("========================ServiceImpl===========");
		System.out.println(listActive.generateServiceImplMethodString());
		System.out.println("========================ServiceImplWithSerial===========");
		System.out.println(listActive.generateServiceImplMethodStringWithSerial());
		System.out.println("========================Controller===========");
		System.out.println(listActive.generateControllerMethodString());
		System.out.println("========================ControllerWithSerial===========");
		System.out.println(listActive.generateControllerMethodStringWithSerial());
		System.out.println("========================Facade===========");
		System.out.println(listActive.generateFacadeMethodString());
		System.out.println("========================FacadeWithSerial===========");
		System.out.println(listActive.generateFacadeMethodStringWithSerial());
	}
	
	@Test
	public void testUpdateDaoString() throws Exception{
		Verb update = VerbFactory.getInstance("update");
		
		Domain domain = new Domain();
		domain.setPackageToken("com.lyz.code.infinity");
		domain.setStandardName("Employee");
		domain.setDomainId(new Field("employeeId","long"));
		domain.addField("name", "String");
		domain.addField("gender", "String");
		domain.addField("age", "int");
		domain.addField("employeeDescription", "String");
		domain.addField("employeeComment","String");
		domain.addField("updateTime","Timestamp", "java.sql");
		
		update.setDomain(domain);
		
		System.out.println(update.generateDaoMethodDefinitionString());
	}
	

	@Test
	public void testUpdate() throws Exception{
		Verb update = VerbFactory.getInstance("update");
		
		Domain domain = new Domain();
		domain.setPackageToken("com.lyz.code.infinity");
		domain.setStandardName("Employee");
		domain.setDomainId(new Field("employeeId","long"));
		domain.addField("name", "String");
		domain.addField("gender", "String");
		domain.addField("age", "int");
		domain.addField("employeeDescription", "String");
		domain.addField("employeeComment","String");
		domain.addField("updateTime","Timestamp", "java.sql");
		
		update.setDomain(domain);
		
		System.out.println(update.generateDaoImplMethodString());
		System.out.println("========================ServiceImpl===========");
		System.out.println(update.generateServiceImplMethodStringWithSerial());
		System.out.println("========================Service===========");
		System.out.println(update.generateServiceMethodDefinitionString());
		System.out.println("========================ServiceImpl===========");
		System.out.println(update.generateServiceImplMethodString());
		System.out.println("========================Controller===========");
		System.out.println(update.generateControllerMethodString());
		System.out.println("========================ControllerWithSerial===========");
		System.out.println(update.generateControllerMethodStringWithSerial());
		System.out.println("========================Facade===========");
		System.out.println(update.generateFacadeMethodString());
		System.out.println("========================FacadeWithSerial===========");
		System.out.println(update.generateFacadeMethodStringWithSerial());
	}
	
	@Test
	public void testDelete() throws Exception{
		Verb delete = VerbFactory.getInstance("delete");
		
		Domain domain = new Domain();
		domain.setPackageToken("com.lyz.code.infinity");
		domain.setStandardName("Employee");
		domain.addField("employeeId","long");
		domain.addField("name", "String");
		domain.addField("gender", "String");
		domain.addField("age", "int");
		domain.addField("employeeDescription", "String");
		domain.addField("employeeComment","String");
		domain.addField("updateTime","Timestamp", "java.sql");
		domain.setDomainId(new Field("employeeId","long"));
		
		delete.setDomain(domain);
		
		System.out.println(delete.generateDaoMethodDefinitionString());
		System.out.println("========================DaoImpl===========");
		System.out.println(delete.generateDaoImplMethodString());
		System.out.println("========================DaoImplWithSerial===========");
		System.out.println(delete.generateDaoImplMethodStringWithSerial());
		System.out.println("========================Service===========");
		System.out.println(delete.generateServiceMethodDefinitionString());
		System.out.println("========================ServiceImpl===========");
		System.out.println(delete.generateServiceImplMethodString());
		System.out.println("========================ServiceImplWithSerial===========");
		System.out.println(delete.generateServiceImplMethodStringWithSerial());
		System.out.println("=======================Controller=============");
		System.out.println(delete.generateControllerMethodString());
		System.out.println("========================ControllerWithSerial===========");
		System.out.println(delete.generateControllerMethodStringWithSerial());
		System.out.println("========================Facade===========");
		System.out.println(delete.generateFacadeMethodString());
		System.out.println("========================FacadeWithSerial===========");
		System.out.println(delete.generateFacadeMethodStringWithSerial());
		
	}
	
	@Test
	public void testSoftDelete() throws Exception{
		Verb softdelete = VerbFactory.getInstance("softDelete");
		
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
		domain.setActive(new Field("active", "boolean"));
		domain.setDomainId(new Field("employeeId","long"));
		
		softdelete.setDomain(domain);
		
		System.out.println("========================Dao===============");
		System.out.println(softdelete.generateDaoMethodDefinitionString());
		System.out.println("========================DaoImpl===========");
		System.out.println(softdelete.generateDaoImplMethodString());
		System.out.println("=======================Controller=============");
		System.out.println(softdelete.generateControllerMethodString());
		System.out.println("========================DaoImplWithSerial===========");
		System.out.println(softdelete.generateDaoImplMethodStringWithSerial());
		System.out.println("========================Service===========");
		System.out.println(softdelete.generateServiceMethodDefinitionString());
		System.out.println("========================ServiceImpl===========");
		System.out.println(softdelete.generateServiceImplMethodString());
		System.out.println("========================ServiceImplWithSerial===========");
		System.out.println(softdelete.generateServiceImplMethodStringWithSerial());
		System.out.println("========================ControllerWithSerial===========");
		System.out.println(softdelete.generateControllerMethodStringWithSerial());
		System.out.println("========================Facade===========");
		System.out.println(softdelete.generateFacadeMethodString());
		System.out.println("========================FacadeWithSerial===========");
		System.out.println(softdelete.generateFacadeMethodStringWithSerial());
		
	}
	
	@Test
	public void testAdd() throws Exception{

		
		Domain domain = new Domain();
		domain.setPackageToken("com.lyz.code.infinity");
		domain.setStandardName("Employee");
		domain.addField("employeeId","long");
		domain.addField("name", "String");
		domain.addField("gender", "String");
		domain.addField("age", "int");
		domain.addField("employeeDescription", "String");
		domain.addField("employeeComment","String");
		domain.addField("updateTime","Timestamp", "java.sql");
		domain.setDomainId(new Field("employeeId","long"));
		
		Verb add = VerbFactory.getInstance("add",domain);
		
		System.out.println(add.generateDaoMethodDefinitionString());
		System.out.println("========================DaoImpl===========");
		System.out.println(add.generateDaoImplMethodString());
		System.out.println("========================DaoImplWithSerial===========");
		System.out.println(add.generateDaoImplMethodStringWithSerial());
		System.out.println("========================Service===========");
		System.out.println(add.generateServiceMethodDefinitionString());
		System.out.println("========================ServiceImpl===========");
		System.out.println(add.generateServiceImplMethodString());
		System.out.println("========================ServiceImplWithSerial===========");
		System.out.println(add.generateServiceImplMethodStringWithSerial());
		System.out.println("========================Controller===========");
		System.out.println(add.generateControllerMethodString());
		System.out.println("========================ControllerWithSerial===========");
		System.out.println(add.generateControllerMethodStringWithSerial());
		System.out.println("========================Facade===========");
		System.out.println(add.generateFacadeMethodString());
		System.out.println("========================FacadeWithSerial===========");
		System.out.println(add.generateFacadeMethodStringWithSerial());
		
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
		domain.setDomainId(new Field("employeeId","long"));
		
		Verb findbyid = VerbFactory.getInstance("findById",domain);
		
		System.out.println("========================Dao===============");
		System.out.println(findbyid.generateDaoMethodDefinitionString());
		System.out.println("========================DaoImpl===========");
		System.out.println(findbyid.generateDaoImplMethodString());
		System.out.println("========================DaoImplWithSerial===========");
		System.out.println(findbyid.generateDaoImplMethodStringWithSerial());
		System.out.println("========================Service===========");
		System.out.println(findbyid.generateServiceMethodDefinitionString());
		System.out.println("========================ServiceImpl===========");
		System.out.println(findbyid.generateServiceImplMethodString());
		System.out.println("========================ServiceImplWithSerial===========");
		System.out.println(findbyid.generateServiceImplMethodStringWithSerial());
		System.out.println("========================Controller===========");
		System.out.println(findbyid.generateControllerMethodString());	
		System.out.println("========================ControllerWithSerial===========");
		System.out.println(findbyid.generateControllerMethodStringWithSerial());
		System.out.println("========================Facade===========");
		System.out.println(findbyid.generateFacadeMethodString());
		System.out.println("========================FacadeWithSerial===========");
		System.out.println(findbyid.generateFacadeMethodStringWithSerial());
	}
	
	@Test
	public void testFindByName() throws Exception{
		
		Domain domain = new Domain();
		domain.setPackageToken("com.lyz.code.infinity");
		domain.setStandardName("Employee");
		domain.setPlural("Employee");
		domain.addField("gender", "String");
		domain.addField("age", "int");
		domain.addField("employeeDescription", "String");
		domain.addField("employeeComment","String");
		domain.addField("updateTime","Timestamp", "java.sql");
		domain.setDomainId(new Field("employeeId","long"));
		domain.setDomainName(new Field("employeeName","String"));
		
		Verb findbyname = VerbFactory.getInstance("findByName",domain);
		
		System.out.println("========================Dao===============");
		System.out.println(findbyname.generateDaoMethodDefinitionString());
		System.out.println("========================DaoImpl===========");
		System.out.println(findbyname.generateDaoImplMethodString());
		System.out.println("========================Service===========");
		System.out.println(findbyname.generateServiceMethodDefinitionString());
		System.out.println("========================ServiceImpl===========");
		System.out.println(findbyname.generateServiceImplMethodString());
		System.out.println("========================ServiceImplWithSerial===========");
		System.out.println(findbyname.generateServiceImplMethodStringWithSerial());
		System.out.println("========================Controller===========");
		System.out.println(findbyname.generateControllerMethodString());
		System.out.println("========================ControllerWithSerial===========");
		System.out.println(findbyname.generateControllerMethodStringWithSerial());
		System.out.println("========================Facade===========");
		System.out.println(findbyname.generateFacadeMethodString());
		System.out.println("========================FacadeWithSerial===========");
		System.out.println(findbyname.generateFacadeMethodStringWithSerial());
	}
	
	@Test
	public void testSearchByName() throws Exception{
		
		Domain domain = new Domain();
		domain.setPackageToken("com.lyz.code.infinity");
		domain.setStandardName("Employee");
		domain.setPlural("Employee");
		domain.addField("gender", "String");
		domain.addField("age", "int");
		domain.addField("employeeDescription", "String");
		domain.addField("employeeComment","String");
		domain.addField("updateTime","Timestamp", "java.sql");
		domain.setDomainId(new Field("employeeId","long"));
		domain.setDomainName(new Field("employeeName","String"));
		
		Verb searchbyname = VerbFactory.getInstance("searchByName",domain);
		
		System.out.println("========================Dao===============");
		System.out.println(searchbyname.generateDaoMethodDefinitionString());
		System.out.println("========================DaoImpl===========");
		System.out.println(searchbyname.generateDaoImplMethodString());
		System.out.println("========================Service===========");
		System.out.println(searchbyname.generateServiceMethodDefinitionString());
		System.out.println("========================ServiceImpl===========");
		System.out.println(searchbyname.generateServiceImplMethodString());
		System.out.println("========================ServiceImplWithSerial===========");
		System.out.println(searchbyname.generateServiceImplMethodStringWithSerial());
		System.out.println("========================Controller===========");
		System.out.println(searchbyname.generateControllerMethodString());
		System.out.println("========================ControllerWithSerial===========");
		System.out.println(searchbyname.generateControllerMethodStringWithSerial());
		System.out.println("========================Facade===========");
		System.out.println(searchbyname.generateFacadeMethodString());
		System.out.println("========================FacadeWithSerial===========");
		System.out.println(searchbyname.generateFacadeMethodStringWithSerial());
	}
	
	@Test
	public void testSearchByNameByPage() throws Exception{
		
		Domain domain = new Domain();
		domain.setPackageToken("com.lyz.code.infinity");
		domain.setStandardName("Employee");
		domain.setPlural("Employee");
		domain.addField("gender", "String");
		domain.addField("age", "int");
		domain.addField("employeeDescription", "String");
		domain.addField("employeeComment","String");
		domain.addField("updateTime","Timestamp", "java.sql");
		domain.setDomainId(new Field("employeeId","long"));
		domain.setDomainName(new Field("employeeName","String"));
		
		Verb searchBynameByPage = VerbFactory.getInstance("searchByNameByPage",domain);
		
		System.out.println("========================Dao===============");
		System.out.println(searchBynameByPage.generateDaoMethodDefinitionString());
		System.out.println("========================DaoImpl===========");
		System.out.println(searchBynameByPage.generateDaoImplMethodString());
		System.out.println("========================Service===========");
		System.out.println(searchBynameByPage.generateServiceMethodDefinitionString());
		System.out.println("========================ServiceImpl===========");
		System.out.println(searchBynameByPage.generateServiceImplMethodString());
		System.out.println("========================ServiceImplWithSerial===========");
		System.out.println(searchBynameByPage.generateServiceImplMethodStringWithSerial());
		System.out.println("========================Controller===========");
		System.out.println(searchBynameByPage.generateControllerMethodString());
		System.out.println("========================ControllerWithSerial===========");
		System.out.println(searchBynameByPage.generateControllerMethodStringWithSerial());
		System.out.println("========================Facade===========");
		System.out.println(searchBynameByPage.generateFacadeMethodString());
		System.out.println("========================FacadeWithSerial===========");
		System.out.println(searchBynameByPage.generateFacadeMethodStringWithSerial());
	}
	
	@Test
	public void testSearchByDescription() throws Exception{
		
		Domain domain = new Domain();
		domain.setPackageToken("com.lyz.code.infinity");
		domain.setStandardName("Employee");
		domain.setPlural("Employee");
		domain.addField("gender", "String");
		domain.addField("age", "int");
		domain.addField("employeeDescription", "String");
		domain.addField("employeeComment","String");
		domain.addField("updateTime","Timestamp", "java.sql");
		domain.setDomainId(new Field("employeeId","long"));
		domain.setDomainName(new Field("employeeName","String"));
		
		Verb searchbydescription = VerbFactory.getInstance("searchByDescription",domain,"employeeDescription");
		
		System.out.println("========================Dao===============");
		System.out.println(searchbydescription.generateDaoMethodDefinitionString());
		System.out.println("========================DaoImpl===========");
		System.out.println(searchbydescription.generateDaoImplMethodString());
		System.out.println("========================Service===========");
		System.out.println(searchbydescription.generateServiceMethodDefinitionString());
		System.out.println("========================ServiceImpl===========");
		System.out.println(searchbydescription.generateServiceImplMethodString());
		System.out.println("========================ServiceImplWithSerial===========");
		System.out.println(searchbydescription.generateServiceImplMethodStringWithSerial());
		System.out.println("========================Controller===========");
		System.out.println(searchbydescription.generateControllerMethodString());
	}
	
	@Test
	public void testSearchByComment() throws Exception{
		
		Domain domain = new Domain();
		domain.setPackageToken("com.lyz.code.infinity");
		domain.setStandardName("Employee");
		domain.setPlural("Employee");
		domain.addField("gender", "String");
		domain.addField("age", "int");
		domain.addField("employeeDescription", "String");
		domain.addField("employeeComment","String");
		domain.addField("updateTime","Timestamp", "java.sql");
		domain.setDomainId(new Field("employeeId","long"));
		domain.setDomainName(new Field("employeeName","String"));
		
		Verb searchbycomment = VerbFactory.getInstance("searchByComment",domain,"employeeComment");
		
		System.out.println("========================Dao===============");
		System.out.println(searchbycomment.generateDaoMethodDefinitionString());
		System.out.println("========================DaoImpl===========");
		System.out.println(searchbycomment.generateDaoImplMethodString());
		System.out.println("========================Service===========");
		System.out.println(searchbycomment.generateServiceMethodDefinitionString());
		System.out.println("========================ServiceImpl===========");
		System.out.println(searchbycomment.generateServiceImplMethodString());
		System.out.println("========================ServiceImplWithSerial===========");
		System.out.println(searchbycomment.generateServiceImplMethodStringWithSerial());
		System.out.println("========================Controller===========");
		System.out.println(searchbycomment.generateControllerMethodString());
	}
	
	@Test
	public void testSearchByField() throws Exception{
		
		Domain domain = new Domain();
		domain.setPackageToken("com.lyz.code.infinity");
		domain.setStandardName("Employee");
		domain.setPlural("Employee");
		domain.addField("gender", "String");
		domain.addField("age", "int");
		domain.addField("employeeDescription", "String");
		domain.addField("employeeComment","String");
		domain.addField("updateTime","Timestamp", "java.sql");
		domain.addField("content","String");
		domain.setDomainId(new Field("employeeId","long"));
		domain.setDomainName(new Field("employeeName","String"));
		
		Verb searchbyfield = VerbFactory.getInstance("searchByField",domain,"content");
		
		System.out.println("========================Dao===============");
		System.out.println(searchbyfield.generateDaoMethodDefinitionString());
		System.out.println("========================DaoImpl===========");
		System.out.println(searchbyfield.generateDaoImplMethodString());
		System.out.println("========================Service===========");
		System.out.println(searchbyfield.generateServiceMethodDefinitionString());
		System.out.println("========================ServiceImpl===========");
		System.out.println(searchbyfield.generateServiceImplMethodString());
		System.out.println("========================ServiceImplWithSerial===========");
		System.out.println(searchbyfield.generateServiceImplMethodStringWithSerial());
		System.out.println("========================Controller===========");
		System.out.println(searchbyfield.generateControllerMethodString());
	}
	
	@Test
	public void testListMy() throws Exception{
		
		Domain domain = new Domain();
		domain.setPackageToken("com.lyz.code.infinity");
		domain.setStandardName("Employee");
		domain.setPlural("Employee");
		domain.addField("gender", "String");
		domain.addField("age", "int");
		domain.addField("employeeDescription", "String");
		domain.addField("employeeComment","String");
		domain.addField("updateTime","Timestamp", "java.sql");
		domain.addField("content","String");
		domain.addField("userId","long");
		domain.setDomainId(new Field("employeeId","long"));
		domain.setDomainName(new Field("employeeName","String"));
		
		Verb listMy = VerbFactory.getInstance("listMy",domain,"userId");
		
		System.out.println("========================Dao===============");
		System.out.println(listMy.generateDaoMethodDefinitionString());
		System.out.println("========================DaoImpl===========");
		System.out.println(listMy.generateDaoImplMethodString());
		System.out.println("========================Service===========");
		System.out.println(listMy.generateServiceMethodDefinitionString());
		System.out.println("========================ServiceImpl===========");
		System.out.println(listMy.generateServiceImplMethodString());
		System.out.println("========================ServiceImplWithSerial===========");
		System.out.println(listMy.generateServiceImplMethodStringWithSerial());
		System.out.println("========================Controller===========");
		System.out.println(listMy.generateControllerMethodString());
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
		
		Verb listAllByPage = VerbFactory.getInstance("listAllByPage",domain);
		
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
	public void testDeleteAll() throws Exception{		
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
		
		Verb deleteAll = VerbFactory.getInstance("deleteAll",domain);
		
		System.out.println("========================Dao Implement===========");
		System.out.println(deleteAll.generateDaoImplMethodStringWithSerial());
		System.out.println(deleteAll.generateDaoImplMethodString());
		System.out.println("========================Dao Definintion===========");
		System.out.println(deleteAll.generateDaoMethodDefinitionString());
		System.out.println("========================ServiceImpl===========");
		System.out.println(deleteAll.generateServiceImplMethodStringWithSerial());
		System.out.println(deleteAll.generateServiceImplMethodString());
		System.out.println("========================Service===============");
		System.out.println(deleteAll.generateServiceMethodDefinitionString());
		System.out.println("=======================Controller=============");
		System.out.println(deleteAll.generateControllerMethodString());
		System.out.println("=======================ControllerWithSerial===========");
		System.out.println(deleteAll.generateControllerMethodStringWithSerial());
		System.out.println("========================Facade===========");
		System.out.println(deleteAll.generateFacadeMethodString());
		System.out.println("========================FacadeWithSerial===========");
		System.out.println(deleteAll.generateFacadeMethodStringWithSerial());
	}
	
	@Test
	public void testSoftDeleteAll() throws Exception{		
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
		
		Verb softDeleteAll = VerbFactory.getInstance("softDeleteAll",domain);
		
		System.out.println("========================Dao Implement===========");
		System.out.println(softDeleteAll.generateDaoImplMethodStringWithSerial());
		System.out.println(softDeleteAll.generateDaoImplMethodString());
		System.out.println("========================Dao Definintion===========");
		System.out.println(softDeleteAll.generateDaoMethodDefinitionString());
		System.out.println("========================ServiceImpl===========");
		System.out.println(softDeleteAll.generateServiceImplMethodStringWithSerial());
		System.out.println(softDeleteAll.generateServiceImplMethodString());
		System.out.println("========================Service===============");
		System.out.println(softDeleteAll.generateServiceMethodDefinitionString());
		System.out.println("=======================Controller=============");
		System.out.println(softDeleteAll.generateControllerMethodString());
		System.out.println("=======================ControllerWithSerial===========");
		System.out.println(softDeleteAll.generateControllerMethodStringWithSerial());
		System.out.println("========================Facade===========");
		System.out.println(softDeleteAll.generateFacadeMethodString());
		System.out.println("========================FacadeWithSerial===========");
		System.out.println(softDeleteAll.generateFacadeMethodStringWithSerial());
	}
}
