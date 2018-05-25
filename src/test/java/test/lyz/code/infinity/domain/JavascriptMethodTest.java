package test.lyz.code.infinity.domain;

import org.junit.Test;

import com.lyz.code.infinity.core.Verb;
import com.lyz.code.infinity.core.VerbFactory;
import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Field;
import com.lyz.code.infinity.domain.JavascriptMethod;
import com.lyz.code.infinity.domain.Type;
import com.lyz.code.infinity.domain.Var;
import com.lyz.code.infinity.generator.NamedJavascriptMethodGenerator;

public class JavascriptMethodTest {
	@Test
	public void testGenerateCheckAllMethod(){
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
		
		Var checkAllBoxes = new Var("checkAllBoxes",new Type("var"));
		Var checkboxes = new Var("checkboxes",new Type("var"));
		Var checkBoxClass = new Var(domain.getDomainId().getLowerFirstFieldName()+"CheckBox",new Type("var"));
		JavascriptMethod method = NamedJavascriptMethodGenerator.generateCheckAllMethod(checkAllBoxes,checkboxes,checkBoxClass);

		System.out.println("========================testGenerateCheckAllMethod===========");
		System.out.println(method.generateMethodString());
		System.out.println("======================testGenerateCheckAllMethodWithSerial====");
		System.out.println(method.generateMethodStringWithSerial());

	}
	
	@Test
	public void testGenerateGenerateDataRowsMethod(){
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
		
		JavascriptMethod generateOpAllRow = new JavascriptMethod();
		generateOpAllRow.setStandardName("generateOpAllRow");
		Var datarow = new Var("datarow",new Type("var"));
		Var domainIdClass = new Var("employeeIdCheckBox", new Type("var"));
		JavascriptMethod updateMethod = new JavascriptMethod();
		updateMethod.setStandardName("updateEmployee");
		JavascriptMethod deleteMethod = new JavascriptMethod();
		deleteMethod.setStandardName("deleteEmployee");
		JavascriptMethod softDeleteMethod = new JavascriptMethod();
		softDeleteMethod.setStandardName("softDeleteEmployee");
		Var data = new Var("data", new Type("var"));
		Var containerId = new Var("tableContent", new Type("var"));
		JavascriptMethod method = NamedJavascriptMethodGenerator.generateGenerateDataRowsMethod(domain, data,generateOpAllRow, datarow, domainIdClass, updateMethod, deleteMethod, softDeleteMethod, containerId);

		System.out.println("========================testGeneratGenerateDataRowsMethod===========");
		System.out.println(method.generateMethodString());
		System.out.println("======================testGeneratGenerateDataRowsMethodWithSerial====");
		System.out.println(method.generateMethodStringWithSerial());

	}
	
	@Test
	public void testGenerateOpAllRowMethod(){
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
		
		JavascriptMethod updateAllMethod = new JavascriptMethod();
		updateAllMethod.setStandardName("updateEmployee");
		JavascriptMethod deleteAllMethod = new JavascriptMethod();
		deleteAllMethod.setStandardName("deleteEmployee");
		JavascriptMethod softDeleteAllMethod = new JavascriptMethod();
		softDeleteAllMethod.setStandardName("softDeleteEmployee");
		Var opAllRowId = new Var("opAllRow",new Type("var"));
		JavascriptMethod method = NamedJavascriptMethodGenerator.generateGenerateOpAllRowMethod(9, opAllRowId, updateAllMethod, deleteAllMethod, softDeleteAllMethod);

		System.out.println("========================testGenerateOpAllRowMethod===========");
		System.out.println(method.generateMethodString());
		System.out.println("======================testGenerateOpAllRowMethodWithSerial====");
		System.out.println(method.generateMethodStringWithSerial());

	}
	
	@Test
	public void testGenerateSearchByNameMethod(){
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

		Var searchForm = new Var("searchForm",new Type("var"));
		Var myDomainNameField = new Var("my"+domain.getDomainName().getCapFirstFieldName(),new Type("var"));
		JavascriptMethod generateDataRowsMethod = new JavascriptMethod();
		generateDataRowsMethod.setStandardName("generateDataRows");
		JavascriptMethod method = NamedJavascriptMethodGenerator.generateSearchByNameMethod(domain, searchForm, myDomainNameField, generateDataRowsMethod);

		System.out.println("========================testGenerateSearchByNameMethod===========");
		System.out.println(method.generateMethodString());
		System.out.println("======================testGenerateSearchByNameMethodWithSerial====");
		System.out.println(method.generateMethodStringWithSerial());

	}
	
	@Test
	public void testGenerateListAllByPageMethod(){
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

		Var pagesize = new Var("pagesize",new Type("var"));
		Var pagenum = new Var("pagenum", new Type("var"));
		Var jumppagenum1 = new Var("jumppagenum1",new Type("var"));
		Var jumppagenum2 = new Var("jumppagenum2",new Type("var"));
		Var pagecount = new Var("pagecount", new Type("var"));
		Var last = new Var("last",new Type("var"));
		JavascriptMethod generateDataRowsMethod = new JavascriptMethod();
		generateDataRowsMethod.setStandardName("generateDataRows");
		JavascriptMethod method = NamedJavascriptMethodGenerator.generateListAllByPageMethod(domain, pagesize, pagenum,last, jumppagenum1, jumppagenum2, pagecount,generateDataRowsMethod);

		System.out.println("========================testGenerateListAllByPageMethod===========");
		System.out.println(method.generateMethodString());
		System.out.println("======================testGenerateListAllByPageMethodWithSerial====");
		System.out.println(method.generateMethodStringWithSerial());

	}
	

	@Test
	public void testGenerateAddMethod(){
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
		domain.setActive(new Field("active","boolean"));


		JavascriptMethod listAllBonusByPage = new JavascriptMethod();
		listAllBonusByPage.setStandardName("listAll"+domain.getPlural()+"ByPage");
		Var addDomainRow = new Var("add"+domain.getCapFirstDomainName()+"Row",new Type("var"));
		Var jumppagenum1 = new Var("jumppagenum1",new Type("var"));
		Var jumppagenum2 = new Var("jumppagenum2",new Type("var"));
		Var pagecount = new Var("pagecount", new Type("var"));
		Var pagesize = new Var("pagesize", new Type("var"));
		JavascriptMethod method = NamedJavascriptMethodGenerator.generateAddMethod(domain, addDomainRow,pagesize, listAllBonusByPage);

		System.out.println("========================testGenerateAddMethod===========");
		System.out.println(method.generateMethodString());
		System.out.println("======================testGenerateAddMethodWithSerial====");
		System.out.println(method.generateMethodStringWithSerial());

	}
	
	@Test
	public void testGenerateUpdateMethod(){
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
		domain.setActive(new Field("active","boolean"));


		JavascriptMethod listAllBonusByPage = new JavascriptMethod();
		listAllBonusByPage.setStandardName("listAll"+domain.getPlural()+"ByPage");
		Var dataRow = new Var("datarow",new Type("var"));
		Var domainform = new Var(domain.getLowerFirstDomainName()+"form",new Type("var"));
		Var jumppagenum1 = new Var("jumppagenum1",new Type("var"));
		Var jumppagenum2 = new Var("jumppagenum2",new Type("var"));
		Var pagecount = new Var("pagecount", new Type("var"));
		Var pagesize = new Var("pagesize", new Type("var"));
		
		JavascriptMethod method = NamedJavascriptMethodGenerator.generateUpdateMethod(domain, domainform, dataRow, pagesize,listAllBonusByPage);

		System.out.println("========================testGenerateUpdateMethod===========");
		System.out.println(method.generateMethodString());
		System.out.println("======================testGenerateUpdateMethodWithSerial====");
		System.out.println(method.generateMethodStringWithSerial());

	}
	
	@Test
	public void testGenerateDeleteMethod(){
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
		domain.setActive(new Field("active","boolean"));


		JavascriptMethod listAllByPage = new JavascriptMethod();
		listAllByPage.setStandardName("listAll"+domain.getPlural()+"ByPage");
		Var domainId = new Var(domain.getDomainId().getLowerFirstFieldName(),new Type("var"));
		Var jumppagenum1 = new Var("jumppagenum1",new Type("var"));
		Var jumppagenum2 = new Var("jumppagenum2",new Type("var"));
		Var pagecount = new Var("pagecount", new Type("var"));
		Var pagesize = new Var("pagesize", new Type("var"));
		
		JavascriptMethod method = NamedJavascriptMethodGenerator.generateDeleteMethod(domain, domainId,pagesize, listAllByPage);

		System.out.println("========================testGenerateDeleteMethod===========");
		System.out.println(method.generateMethodString());
		System.out.println("======================testGenerateDeleteMethodWithSerial====");
		System.out.println(method.generateMethodStringWithSerial());

	}
	
	@Test
	public void testGenerateSoftDeleteMethod(){
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
		domain.setActive(new Field("active","boolean"));


		JavascriptMethod listAllByPage = new JavascriptMethod();
		listAllByPage.setStandardName("listAll"+domain.getPlural()+"ByPage");
		Var domainId = new Var(domain.getDomainId().getLowerFirstFieldName(),new Type("var"));
		Var jumppagenum1 = new Var("jumppagenum1",new Type("var"));
		Var jumppagenum2 = new Var("jumppagenum2",new Type("var"));
		Var pagecount = new Var("pagecount", new Type("var"));
		Var pagesize = new Var("pagesize", new Type("var"));
		
		JavascriptMethod method = NamedJavascriptMethodGenerator.generateSoftDeleteMethod(domain,domainId, pagesize,listAllByPage);

		System.out.println("========================testGeneratSoftDeleteMethod===========");
		System.out.println(method.generateMethodString());
		System.out.println("======================testGeneratSoftDeleteMethodWithSerial====");
		System.out.println(method.generateMethodStringWithSerial());

	}
	
	@Test
	public void testGenerateDeleteAllMethod(){
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
		domain.setActive(new Field("active","boolean"));


		JavascriptMethod listAllByPage = new JavascriptMethod();
		listAllByPage.setStandardName("listAll"+domain.getPlural()+"ByPage");
		Var domainCheckboxGroup = new Var("employeeIdCheckbox",new Type("var"));
		Var checkAllBox = new Var("checkAllBox", new Type("var"));
		Var jumppagenum1 = new Var("jumppagenum1",new Type("var"));
		Var jumppagenum2 = new Var("jumppagenum2",new Type("var"));
		Var pagecount = new Var("pagecount", new Type("var"));
		Var pagesize = new Var("pagesize", new Type("var"));
		
		JavascriptMethod method = NamedJavascriptMethodGenerator.generateDeleteAllMethod(domain, domainCheckboxGroup, checkAllBox, pagesize, listAllByPage);
		System.out.println("========================testGenerateDeleteAllMethod===========");
		System.out.println(method.generateMethodString());
		System.out.println("======================testGenerateDeleteAllMethodWithSerial====");
		System.out.println(method.generateMethodStringWithSerial());
	}
	
	@Test
	public void testGenerateSoftDeleteAllMethod(){
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
		domain.setActive(new Field("active","boolean"));


		JavascriptMethod listAllByPage = new JavascriptMethod();
		listAllByPage.setStandardName("listAll"+domain.getPlural()+"ByPage");
		Var domainCheckboxGroup = new Var("employeeIdCheckbox",new Type("var"));
		Var checkAllBox = new Var("checkAllBox", new Type("var"));
		Var jumppagenum1 = new Var("jumppagenum1",new Type("var"));
		Var jumppagenum2 = new Var("jumppagenum2",new Type("var"));
		Var pagecount = new Var("pagecount", new Type("var"));
		Var pagesize = new Var("pagesize", new Type("var"));
		
		JavascriptMethod method = NamedJavascriptMethodGenerator.generateSoftDeleteAllMethod(domain, domainCheckboxGroup, checkAllBox, pagesize, listAllByPage);
		System.out.println("========================testGenerateSoftDeleteAllMethod===========");
		System.out.println(method.generateMethodString());
		System.out.println("======================testGenerateSoftDeleteAllMethodWithSerial====");
		System.out.println(method.generateMethodStringWithSerial());
	}

}


