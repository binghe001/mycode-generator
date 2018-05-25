package test.lyz.code.infinity.domain;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.lyz.code.infinity.core.Verb;
import com.lyz.code.infinity.domain.Controller;
import com.lyz.code.infinity.domain.Dao;
import com.lyz.code.infinity.domain.DaoImpl;
import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Field;
import com.lyz.code.infinity.domain.Method;
import com.lyz.code.infinity.domain.Prism;
import com.lyz.code.infinity.domain.Service;
import com.lyz.code.infinity.domain.ServiceImpl;
import com.lyz.code.infinity.domain.Signature;
import com.lyz.code.infinity.domain.Type;
import com.lyz.code.infinity.domain.ValidateInfo;
import com.lyz.code.infinity.exception.ValidateException;
import com.lyz.code.infinity.generator.DBDefinitionFactory;
import com.lyz.code.infinity.generator.DBDefinitionGenerator;
import com.lyz.code.infinity.generator.JspTemplate;
import com.lyz.code.infinity.generator.JspTemplateFactory;
import com.lyz.code.infinity.utils.StringUtil;
import com.lyz.code.infinity.verb.ListAll;

public class PrismTest extends TestCase {
	@Test
	public void testGeneratePrismFiles()  throws Exception{
		try {
		Prism prism = new Prism();
		
		Domain domain = new Domain();
		domain.setStandardName("Employee");
		domain.setPlural("Employee");
		domain.addField("EmployeeId","long");
		domain.addField("Name", "String");
		domain.addField("Gender", "String");
		domain.addField("Age", "int");
		domain.addField("EmployeeDescription", "String");
		domain.addField("EmployeeComment","String");
		
		Field f = new Field("EmployeeId","long");
		domain.setDomainId(f);
		
		Method promotion = new Method();
		promotion.setStandardName("Promotion");
		List<Signature> signatures = new ArrayList<Signature>();
		signatures.add(new Signature(1,"employeeId",new Type("long")));
		signatures.add(new Signature(1,"positionId",new Type("long")));
		promotion.setSignatures(signatures);
		//promotion.setReturnType("void");
		domain.addMethod(promotion);
		
		prism.setDomain(domain);
		
		Dao dao = new Dao();
		dao.setDomain(domain);
		DaoImpl daoimpl = new DaoImpl();
		daoimpl.setDomain(domain);
		daoimpl.setDao(dao);
		
		Service service = new Service();
		service.setDomain(domain);
		ServiceImpl serviceimpl = new ServiceImpl(domain);
		serviceimpl.setDomain(domain);
		serviceimpl.setService(service);
		
		Verb listAll = new ListAll(domain);
	
		Controller c = new Controller(listAll,domain);
			
		prism.setDao(dao);
		prism.setDaoImpl(daoimpl);
		prism.setService(service);
		prism.setServiceImpl(serviceimpl);
		prism.addController(c);
		
		DBDefinitionGenerator dbg = DBDefinitionFactory.getInstance("mysql");
		dbg.setDbName("employee");
		prism.setDbDefinitionGenerator(dbg);	
		
		JspTemplate jt = JspTemplateFactory.getInstance("grid",domain);
		jt.setDomain(domain);
		jt.setStandardName("employee");
		
		prism.addJspTemplate(jt);
		
		prism.generatePrismFiles();
		} catch(ValidateException e){
			ValidateInfo info = e.getValidateInfo();
			for (String s : info.getCompileErrors()){
				System.out.println(s);
			}
		}
	}
}
