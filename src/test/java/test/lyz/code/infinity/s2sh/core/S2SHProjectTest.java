package test.lyz.code.infinity.s2sh.core;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import com.lyz.code.infinity.core.Verb;
import com.lyz.code.infinity.core.WebXml;
import com.lyz.code.infinity.domain.Controller;
import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Field;
import com.lyz.code.infinity.domain.Method;
import com.lyz.code.infinity.domain.Prism;
import com.lyz.code.infinity.domain.Project;
import com.lyz.code.infinity.domain.ProjectUtil;
import com.lyz.code.infinity.domain.Signature;
import com.lyz.code.infinity.domain.Type;
import com.lyz.code.infinity.domain.UtilFactory;
import com.lyz.code.infinity.generator.DBDefinitionFactory;
import com.lyz.code.infinity.generator.DBDefinitionGenerator;
import com.lyz.code.infinity.generator.JspTemplate;
import com.lyz.code.infinity.generator.JspTemplateFactory;
import com.lyz.code.infinity.verb.Add;
import com.lyz.code.infinity.verb.Delete;
import com.lyz.code.infinity.verb.FindById;
import com.lyz.code.infinity.verb.FindByName;
import com.lyz.code.infinity.verb.ListActive;
import com.lyz.code.infinity.verb.ListAll;
import com.lyz.code.infinity.verb.ListMy;
import com.lyz.code.infinity.verb.SearchByComment;
import com.lyz.code.infinity.verb.SearchByDescription;
import com.lyz.code.infinity.verb.SearchByName;
import com.lyz.code.infinity.verb.SoftDelete;
import com.lyz.code.infinity.verb.Update;

public class S2SHProjectTest extends TestCase {
	@Test
	public void testGenerateProjectFiles() throws Exception{
		Project project = new Project();
		project.setStandardName("EmployeeTest");
		project.setPackageToken("org.rocketship.employeetest");		
		project.setDbPrefix("spt_");

		
		Prism prism = new Prism();
		
		Domain domain = new Domain();
		domain.setPackageToken(project.getPackageToken());
		domain.setDbPrefix(project.getDbPrefix());
		domain.setStandardName("Employee");
		domain.setPlural("Employee");
		domain.addField("gender", "String");
		domain.addField("age", "int");
		domain.addField("employeeDescription", "String");
		domain.addField("employeeComment","String");
		domain.addField("content","String");
		domain.addField("userId","long");
		domain.setActive(new Field("active","boolean"));
		domain.setDomainName(new Field("employeeName","String"));
		
		Field f = new Field("employeeId","long");
		domain.setDomainId(f);
		
		Method promotion = new Method();
		promotion.setStandardName("Promotion");
		List<Signature> signatures = new ArrayList<Signature>();
		signatures.add(new Signature(1,"employeeId",new Type("long")));
		signatures.add(new Signature(2,"positionId",new Type("long")));
		promotion.setSignatures(signatures);
		//promotion.setReturnType("void");
		domain.addMethod(promotion);
		
		prism.setDomain(domain);
		
		
		Verb listAll = new ListAll(domain);
		Verb update = new Update(domain);
		Verb delete = new Delete(domain);
		Verb add = new Add(domain);
		Verb softdelete = new SoftDelete(domain);
		Verb findbyid = new FindById(domain);
		Verb findbyname = new FindByName(domain);
		Verb searchbyname = new SearchByName(domain);
		Verb listactive = new ListActive(domain);
		Verb searchbydescription = new SearchByDescription(domain,"employeeDescription");
		Verb searchbycomment = new SearchByComment(domain,"employeeComment");
		Verb searchbyfield = new SearchByComment(domain,"content");
		Verb listmy = new ListMy(domain,"userId");		

		Controller c = new Controller(listAll);
		Controller c1 = new Controller(update);
		Controller c2 = new Controller(delete);
		Controller c3 = new Controller(add);
		Controller c4 = new Controller(softdelete);
		Controller c5 = new Controller(findbyid);
		Controller c6 = new Controller(findbyname);
		Controller c7 = new Controller(searchbyname);
		Controller c8 = new Controller(listactive);
		Controller c9 = new Controller(searchbydescription);
		Controller c10 = new Controller(searchbycomment);
		Controller c11 = new Controller(searchbyfield);
		Controller c12 = new Controller(listmy);
		
		prism.addController(c);
		prism.addController(c1);
		prism.addController(c2);
		prism.addController(c3);
		prism.addController(c4);
		prism.addController(c5);
		prism.addController(c6);
		prism.addController(c7);
		prism.addController(c8);
		prism.addController(c9);
		prism.addController(c10);
		prism.addController(c11);
		prism.addController(c12);
		
		prism.addVerb(listAll);
		prism.addVerb(update);
		prism.addVerb(delete);
		prism.addVerb(add);
		prism.addVerb(softdelete);
		prism.addVerb(findbyid);
		prism.addVerb(findbyname);
		prism.addVerb(searchbyname);
		prism.addVerb(listactive);
		prism.addVerb(searchbydescription);	
		prism.addVerb(searchbycomment);
		prism.addVerb(searchbyfield);
		prism.addVerb(listmy);
		
		prism.generatePrismFromDomain();
		
		DBDefinitionGenerator dbg = DBDefinitionFactory.getInstance("mysql");
		dbg.addDomain(domain);
		dbg.setDbName("employeetest");
		prism.setDbDefinitionGenerator(dbg);	
		
		JspTemplate jt = JspTemplateFactory.getInstance("grid",domain);
		jt.setDomain(domain);
		jt.setStandardName("employee");
		
		prism.addJspTemplate(jt);
		prism.setPackageToken(project.getPackageToken());
		
		Domain clockRecord = new Domain();
		clockRecord.setPackageToken(project.getPackageToken());
		clockRecord.setDbPrefix(project.getDbPrefix());
		clockRecord.setStandardName("ClockRecord");
		clockRecord.addField("ClockRecordId","long");
		clockRecord.addField("ClockRecordName", "String");
		//clockRecord.addField("Time", "Timestamp","java.util");
		clockRecord.addField("Serial", "int");
		clockRecord.addField("ClockRecordDescription", "String");
		clockRecord.addField("ClockRecordComment","String");
		
		Field f2 = new Field("ClockRecordId","long");
		clockRecord.setDomainId(f2);
		
		Prism clockRecordPrism = new Prism();
		clockRecordPrism.setDomain(clockRecord);
		
		DBDefinitionGenerator dbg2 = DBDefinitionFactory.getInstance("mysql");
		dbg.addDomain(clockRecord);
		clockRecordPrism.setDbDefinitionGenerator(dbg2);
		
		Domain leave = new Domain();
		leave.setPackageToken(project.getPackageToken());
		leave.setDbPrefix(project.getDbPrefix());
		leave.setStandardName("Leave");
		leave.addField("LeaveId","long");
		leave.addField("LeaveTime", "Timestamp","java.util");
		leave.addField("Serial", "int");
		leave.addField("LeaveDescription", "String");
		leave.addField("LeaveComment","String");
		
		Field f3 = new Field("ClockRecordId","long");
		leave.setDomainId(f3);
		leave.setDomainName(new Field("leaveName", "String"));
		leave.setActive(new Field("active","boolean"));
		
		Prism leavePrism = new Prism();
		leavePrism.setDomain(leave);
		leavePrism.addVerb(listAll);
		leavePrism.generatePrismFromDomain();
		
		project.setPackageToken("org.rocketship.employeetest");
		prism.setPackageToken(project.getPackageToken());
		prism.expandPackageToken();
		project.addPrism(prism);
		clockRecordPrism.setPackageToken(project.getPackageToken());
		clockRecordPrism.expandPackageToken();
		project.addPrism(clockRecordPrism);
		leavePrism.setPackageToken(project.getPackageToken());
		leavePrism.expandPackageToken();
		//project.addPrism(leavePrism);
		project.setDbName("employeetest");
		ProjectUtil pu = (ProjectUtil)UtilFactory.getInstance("project");
		project.addUtil(pu);
		
		WebXml webxml = new WebXml();
		webxml.setProjectName("EmployeeTest");
		webxml.addControllers(prism.getControllers());
		webxml.addControllers(clockRecordPrism.getControllers());
		webxml.addControllers(leavePrism.getControllers());

		project.generateProjectZip();
	}
	
	@Test
	public void testPackagetokenToFolder(){
		String packageToken = "org.rocketship.employeetest";
		String folder = Project.packagetokenToFolder(packageToken);
		System.out.println(folder);
		Assert.assertEquals("org/rocketship/employeetest/", folder);
	}
	
	@Test
	public void testFolderToPackagetoken(){
		String folder = "org/rocketship/employeetest/";
		String packageToken = Project.folderToPackageToken(folder);
		System.out.println(packageToken);
		Assert.assertEquals("org.rocketship.employeetest", packageToken);
	}
}
