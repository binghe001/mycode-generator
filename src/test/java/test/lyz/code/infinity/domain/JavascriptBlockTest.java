package test.lyz.code.infinity.domain;

import org.junit.Test;

import com.lyz.code.infinity.core.Verb;
import com.lyz.code.infinity.core.VerbFactory;
import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Field;
import com.lyz.code.infinity.domain.JavascriptBlock;
import com.lyz.code.infinity.domain.Type;
import com.lyz.code.infinity.domain.Var;
import com.lyz.code.infinity.generator.NamedJavascriptBlockGenerator;

public class JavascriptBlockTest {
	@Test
	public void testDocumentReadyListDomainList(){
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
		
		Var pagesize = new Var("pagesize",new Type("var"),"10");
		Var pagenum = new Var("pagenum",new Type("var"),"1");
		JavascriptBlock block = NamedJavascriptBlockGenerator.documentReadyListDomainList(domain, pagesize, pagenum);

		System.out.println("========================documentReadyListDomainList===========");
		System.out.println(block.generateBlockContentString());
		System.out.println("======================documentReadyListDomainListWithSerial====");
		System.out.println(block.generateBlockContentStringWithSerial());

	}

}
