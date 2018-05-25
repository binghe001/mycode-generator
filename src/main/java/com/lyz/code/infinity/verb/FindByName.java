package com.lyz.code.infinity.verb;

import java.util.ArrayList;
import java.util.List;

import com.lyz.code.infinity.core.Verb;
import com.lyz.code.infinity.core.Writeable;
import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Method;
import com.lyz.code.infinity.domain.Signature;
import com.lyz.code.infinity.domain.Statement;
import com.lyz.code.infinity.domain.StatementList;
import com.lyz.code.infinity.domain.Type;
import com.lyz.code.infinity.domain.Var;
import com.lyz.code.infinity.generator.NamedStatementGenerator;
import com.lyz.code.infinity.generator.NamedStatementListGenerator;
import com.lyz.code.infinity.utils.InterVarUtil;
import com.lyz.code.infinity.utils.StringUtil;
import com.lyz.code.infinity.utils.WriteableUtil;

public class FindByName extends Verb{

	@Override
	public Method generateDaoImplMethod(){
		try {
			Method method = new Method();
			method.setStandardName("find"+this.domain.getStandardName()+"By"+StringUtil.capFirst(this.domain.getDomainName().getFieldName()));	
			method.setReturnType(new Type(StringUtil.lowerFirst(this.domain.getStandardName()),this.domain.getPackageToken()));
			method.setReturnType(new Type(this.domain.getStandardName(),this.domain.getPackageToken()));
			method.addAdditionalImport("java.sql.Connection");
			method.addAdditionalImport("java.util.List");
			method.addAdditionalImport("java.util.ArrayList");
			method.addAdditionalImport("java.sql.PreparedStatement");
			method.addAdditionalImport("java.sql.ResultSet");
			method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
			method.addAdditionalImport(this.domain.getPackageToken()+".dao."+this.domain.getStandardName()+"Dao");
			method.addSignature(new Signature(1,"connection",new Type("Connection","java.sql"),"java.sql"));
			method.addSignature(new Signature(2,this.domain.getDomainName().getFieldName(),new Type("String")));
			method.setThrowException(true);
			
			List<Writeable> list = new ArrayList<Writeable>();
			list.add(NamedStatementGenerator.getTryHead(1000L,2));
			list.add(NamedStatementListGenerator.generateFindByNameStatementList(2000L,3,this.domain));
			list.add(NamedStatementListGenerator.generateCatchExceptionPrintStackReturnNullFooter(3000L,2));
			method.setMethodStatementList(WriteableUtil.merge(list));
			return method;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public String generateDaoImplMethodString(){
		Method m = this.generateDaoImplMethod();
		String s = m.generateMethodString();
		return s;
	}

	@Override
	public Method generateDaoMethodDefinition() {
		Method method = new Method();
		method.setStandardName("find"+this.domain.getStandardName()+"By"+StringUtil.capFirst(this.domain.getDomainName().getFieldName()));
		method.setReturnType(new Type(this.domain.getStandardName(),this.domain.getPackageToken()));
		method.addAdditionalImport("java.sql.Connection");
		method.addAdditionalImport("java.util.List");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addSignature(new Signature(1,"connection",new Type("Connection","java.sql"),"java.sql"));
		method.addSignature(new Signature(2,this.domain.getDomainName().getFieldName(),new Type("String")));
		method.setThrowException(true);
		
		return method;
	}

	@Override
	public String generateDaoMethodDefinitionString() {
		return generateDaoMethodDefinition().generateMethodDefinition();
	}

	@Override
	public String generateDaoImplMethodStringWithSerial() {
		Method m = this.generateDaoImplMethod();
		m.setContent(m.generateMethodContentStringWithSerial());
		m.setMethodStatementList(null);
		return m.generateMethodString();
	}

	@Override
	public Method generateServiceMethodDefinition() {
		Method method = new Method();
		method.setStandardName("find"+this.domain.getStandardName()+"By"+StringUtil.capFirst(this.domain.getDomainName().getFieldName()));
		method.setReturnType(new Type(this.domain.getStandardName(),this.domain.getPackageToken()));
		method.addAdditionalImport("java.util.List");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addSignature(new Signature(1,this.domain.getDomainName().getFieldName(),new Type("String")));
		method.setThrowException(true);
		
		return method;
	}

	@Override
	public String generateServiceMethodDefinitionString() {
		return generateServiceMethodDefinition().generateMethodDefinition();
	}

	@Override
	public Method generateControllerMethod() {
		Method method = new Method();
		method.setStandardName("processRequest");
		method.setReturnType(new Type("void"));
		method.setThrowException(true);
		List<String> list = new ArrayList<String>();
		list.add("ServletException");
		list.add("IOException");
		method.setIsprotected(true);
		method.setOtherExceptions(list);
		method.addSignature(new Signature(1,"request",new Type("HttpServletRequest","javax.servlet.http")));
		method.addSignature(new Signature(2,"response",new Type("HttpServletResponse","javax.servlet.http")));
		method.addAdditionalImport("java.io.IOException");
		//method.addAdditionalImport("java.io.PrintWriter");
		method.addAdditionalImport("javax.servlet.ServletException");
		method.addAdditionalImport("javax.servlet.http.HttpServlet");
		method.addAdditionalImport("javax.servlet.http.HttpServletRequest");
		method.addAdditionalImport("javax.servlet.http.HttpServletResponse");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addAdditionalImport(this.domain.getPackageToken()+".service."+this.domain.getStandardName()+"Service");
		method.addAdditionalImport(this.domain.getPackageToken()+".serviceimpl."+this.domain.getStandardName()+"ServiceImpl");
		
		List<Writeable> wlist = new ArrayList<Writeable>();
		Var service = new Var("service", new Type(this.domain.getStandardName()+"Service",this.domain.getPackageToken()));
		Method serviceMethod = this.generateServiceMethodDefinition();
		wlist.add(NamedStatementGenerator.getControllerSetContentType(1000L, 2, InterVarUtil.Servlet.response, InterVarUtil.SimpleJEE.UTF8.getVarName()));
		wlist.add(NamedStatementGenerator.getTryHead(2000L,2));
		wlist.add(NamedStatementGenerator.getSetDomainNameFromRequest(3000L, 3, this.domain, InterVarUtil.Servlet.request));
		wlist.add(new Statement(4000,3,service.generateTypeVarString() + " = new " + service.getVarType().getTypeName()+"Impl();"));
		wlist.add(new Statement(5000,3, this.domain.generateTypeVarString() + " = " + service.getVarName()+"."+serviceMethod.generateStandardCallString()+";"));
		wlist.add(new Statement(6000,3,InterVarUtil.Servlet.request.getVarName()+".setAttribute(\""+StringUtil.lowerFirst(this.domain.getStandardName())+"\","+StringUtil.lowerFirst(domain.getStandardName())+");"));
		wlist.add(NamedStatementGenerator.getResponseRedirectUrl(7000L, 3, InterVarUtil.Servlet.response, "../controller/listAll"+this.domain.getPlural()+"Controller"));
		wlist.add(NamedStatementListGenerator.generateCatchExceptionPrintStackFooter(8000L, 2));
		method.setMethodStatementList(WriteableUtil.merge(wlist));
		
		return method;
	}

	@Override
	public String generateControllerMethodString() {
		return generateControllerMethod().generateMethodString();
	}

	@Override
	public Method generateServiceImplMethod() {
		Method method = new Method();
		method.setStandardName("find"+this.domain.getStandardName()+"By"+StringUtil.capFirst(this.domain.getDomainName().getFieldName()));	method.setReturnType(new Type(StringUtil.lowerFirst(this.domain.getStandardName()),this.domain.getPackageToken()));
		method.setReturnType(new Type(this.domain.getStandardName(),this.domain.getPackageToken()));
		method.addAdditionalImport("java.sql.Connection");
		method.addAdditionalImport("java.util.List");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addAdditionalImport(this.domain.getPackageToken()+".database.DBConf");
		method.addAdditionalImport(this.domain.getPackageToken()+".dao."+this.domain.getStandardName()+"Dao");
		method.addAdditionalImport(this.domain.getPackageToken()+".daoimpl."+this.domain.getStandardName()+"DaoImpl");
		method.addAdditionalImport(this.domain.getPackageToken()+".service."+this.domain.getStandardName()+"Service");
		method.addSignature(new Signature(1,this.domain.getDomainName().getFieldName(),new Type("String")));
		method.setThrowException(true);
		
		Method daomethod = this.generateDaoMethodDefinition();
		
		List<Writeable> list = new ArrayList<Writeable>();
		list.add(NamedStatementListGenerator.generateServiceImplReturnDomain(1000L, 2, InterVarUtil.DB.connection, InterVarUtil.DB.dbconf, this.domain, InterVarUtil.DB.dao, daomethod));
		method.setMethodStatementList(WriteableUtil.merge(list));

		return method;
	}

	@Override
	public String generateServiceImplMethodString() {
		return generateServiceImplMethod().generateMethodString();
	}

	@Override
	public String generateServiceImplMethodStringWithSerial() {
		Method m = this.generateServiceImplMethod();
		m.setContent(m.generateMethodContentStringWithSerial());
		m.setMethodStatementList(null);
		return m.generateMethodString();
	}
	
	public FindByName(){
		super();
		if (this.domain != null) this.setVerbName("Find"+this.domain.getStandardName()+"By"+StringUtil.capFirst(this.domain.getStandardName()+"Name"));
		else this.setVerbName("FindByName");
	}
	
	public FindByName(Domain domain){
		super();
		this.domain = domain;
		this.setVerbName("Find"+this.domain.getStandardName()+"By"+StringUtil.capFirst(this.domain.getStandardName()+"Name"));
	}

	@Override
	public String generateControllerMethodStringWithSerial() {
		Method m = this.generateControllerMethod();
		m.setContent(m.generateMethodContentStringWithSerial());
		m.setMethodStatementList(null);
		return m.generateMethodString();
	}

	@Override
	public Method generateFacadeMethod() {
		Method method = new Method();
		method.setStandardName("processRequest");
		method.setReturnType(new Type("void"));
		method.setThrowException(true);
		List<String> list = new ArrayList<String>();
		list.add("ServletException");
		list.add("IOException");
		method.setIsprotected(true);
		method.setOtherExceptions(list);
		method.addSignature(new Signature(1,"request",new Type("HttpServletRequest","javax.servlet.http")));
		method.addSignature(new Signature(2,"response",new Type("HttpServletResponse","javax.servlet.http")));
		method.addAdditionalImport("java.io.IOException");
		method.addAdditionalImport("java.io.PrintWriter");
		method.addAdditionalImport("javax.servlet.ServletException");
		method.addAdditionalImport("javax.servlet.http.HttpServlet");
		method.addAdditionalImport("javax.servlet.http.HttpServletRequest");
		method.addAdditionalImport("javax.servlet.http.HttpServletResponse");
		method.addAdditionalImport("java.util.Map");
		method.addAdditionalImport("java.util.TreeMap");
		method.addAdditionalImport("net.sf.json.JSONObject");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addAdditionalImport(this.domain.getPackageToken()+".service."+this.domain.getStandardName()+"Service");
		method.addAdditionalImport(this.domain.getPackageToken()+".serviceimpl."+this.domain.getStandardName()+"ServiceImpl");
		
		List<Writeable> wlist = new ArrayList<Writeable>();
		Var service = new Var("service", new Type(this.domain.getStandardName()+"Service",this.domain.getPackageToken()));
		Var resultMap = new Var("result", new Type("TreeMap<String,Object>","java.util"));
		Var domainVar = new Var(this.domain.getLowerFirstDomainName(), new Type(this.domain.getCapFirstDomainName(),this.domain.getPackageToken()+".domain."+this.domain.getCapFirstDomainName()));
		Method serviceMethod = this.generateServiceMethodDefinition();
		wlist.add(NamedStatementGenerator.getFacadeSetContentType(1000L, 2, InterVarUtil.Servlet.response, InterVarUtil.SimpleJEE.UTF8.getVarName()));
		wlist.add(NamedStatementGenerator.getJsonResultMap(2000L, 2, resultMap));
		wlist.add(NamedStatementGenerator.getControllerPrintWriterOut(3000L, 2, InterVarUtil.Servlet.response, InterVarUtil.Servlet.out));
		wlist.add(NamedStatementGenerator.getTryHead(4000L,2));
		wlist.add(NamedStatementGenerator.getSetDomainNameFromRequest(5000L, 3, this.domain, InterVarUtil.Servlet.request));
		wlist.add(new Statement(6000,3,service.generateTypeVarString() + " = new " + service.getVarType().getTypeName()+"Impl();"));
		wlist.add(new Statement(7000,3, domainVar.generateTypeVarString() + " = " + service.getVarName()+"."+serviceMethod.generateStandardCallString()+";"));
		wlist.add(NamedStatementListGenerator.getPutJsonResultMapWithSuccessAndDomainVar(8000L, 3, resultMap,domainVar));
		wlist.add(NamedStatementGenerator.getEncodeMapToJsonResultMap(9000L, 3, resultMap,InterVarUtil.Servlet.out));
		wlist.add(NamedStatementListGenerator.generateCatchExceptionPrintStackPrintJsonMapFinallyCloseOutFooter(10000L, 2, InterVarUtil.Servlet.response,resultMap, InterVarUtil.Servlet.out));
		method.setMethodStatementList(WriteableUtil.merge(wlist));
		
		return method;
	}

	@Override
	public String generateFacadeMethodString() {
		Method m = this.generateFacadeMethod();
		return m.generateMethodString();
	}

	@Override
	public String generateFacadeMethodStringWithSerial() {
		Method m = this.generateFacadeMethod();
		m.setContent(m.generateMethodContentStringWithSerial());
		m.setMethodStatementList(null);
		return m.generateMethodString();
	}
}
