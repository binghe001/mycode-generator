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
import com.lyz.code.infinity.limitedverb.CountPage;
import com.lyz.code.infinity.limitedverb.NoControllerVerb;
import com.lyz.code.infinity.utils.InterVarUtil;
import com.lyz.code.infinity.utils.StringUtil;
import com.lyz.code.infinity.utils.WriteableUtil;

public class ListAllByPage extends Verb{
	protected CountPage countPage;
	

	@Override
	public Method generateDaoImplMethod(){
		try {
			Method method = new Method();
			method.setStandardName("listAll"+StringUtil.capFirst(this.domain.getPlural())+"ByPage");
			method.setReturnType(new Type("List",this.domain, this.domain.getPackageToken()));
			method.addAdditionalImport("java.sql.Connection");
			method.addAdditionalImport("java.util.List");
			method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
			method.addSignature(new Signature(1,"connection",new Type("Connection","java.sql"),"java.sql"));
			method.addSignature(new Signature(2,"pagesize",new Type("int")));
			method.addSignature(new Signature(3,"pagenum",new Type("int")));
			method.setThrowException(true);
			
			List<Writeable> list = new ArrayList<Writeable>();
			list.add(NamedStatementGenerator.getTryHead(1000L,2));
			Var service = InterVarUtil.Common.service(this.domain);
			StatementList sl = NamedStatementListGenerator.generateSelectAllByPageQueryStatementList(2000L,3,this.domain,method.getSignatures().get(1).getVar(),method.getSignatures().get(2).getVar(),service,countPage);
			list.add(sl);				
			list.add(NamedStatementListGenerator.generateCatchExceptionPrintStackReturnEmptyListFooter(3000L,2,this.domain));
			method.setMethodStatementList(WriteableUtil.merge(list));
			return method;
		} catch (Exception e){
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
		method.setStandardName("listAll"+StringUtil.capFirst(this.domain.getPlural())+"ByPage");
		method.setReturnType(new Type("List",this.domain, this.domain.getPackageToken()));
		method.addAdditionalImport("java.sql.Connection");
		method.addAdditionalImport("java.util.List");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addSignature(new Signature(1,"connection",new Type("Connection","java.sql"),"java.sql"));
		method.addSignature(new Signature(2,"pagesize",new Type("int")));
		method.addSignature(new Signature(3,"pagenum",new Type("int")));
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
		method.setStandardName("listAll"+StringUtil.capFirst(this.domain.getPlural())+"ByPage");
		method.setReturnType(new Type("List",this.domain, this.domain.getPackageToken()));
		method.addAdditionalImport("java.util.List");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addSignature(new Signature(1,"pagesize",new Type("int")));
		method.addSignature(new Signature(2,"pagenum",new Type("int")));
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
		method.addAdditionalImport("java.util.List");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addAdditionalImport(this.domain.getPackageToken()+".service."+this.domain.getStandardName()+"Service");
		method.addAdditionalImport(this.domain.getPackageToken()+".serviceimpl."+this.domain.getStandardName()+"ServiceImpl");

		List<Writeable> wlist = new ArrayList<Writeable>();
		Var service = new Var("service", new Type(this.domain.getStandardName()+"Service",this.domain.getPackageToken()));
		Var vlist = new Var(StringUtil.lowerFirst(this.domain.getStandardName())+"List", new Type("List",this.domain,this.domain.getPackageToken()));
		Method serviceMethod = this.generateServiceMethodDefinition();
		wlist.add(NamedStatementGenerator.getControllerSetContentType(1000L, 2, InterVarUtil.Servlet.response, InterVarUtil.SimpleJEE.UTF8.getVarName()));
		wlist.add(NamedStatementGenerator.getTryHead(2000L,2));
		
		Var pagesize = new Var("pagesize",new Type("int"),"10");
		Var pagenum = new Var("pagenum",new Type("int"),"1");
		Var pagecount = new Var("pagecount",new Type("int"));
		Var last = new Var("last", new Type("boolean"),"false");
		wlist.add(NamedStatementGenerator.getRequestVarsWithDefaultValueStatement(2020,3,InterVarUtil.Servlet.request,last));
		wlist.add(NamedStatementGenerator.getRequestVarsWithDefaultValueStatement(2100,3,InterVarUtil.Servlet.request,pagesize));
		wlist.add(NamedStatementGenerator.getRequestVarsWithDefaultValueStatement(2200,3,InterVarUtil.Servlet.request,pagenum));		
		wlist.add(new Statement(3000,3,service.generateTypeVarString() + " = new " + service.getVarType().getTypeName()+"Impl();"));
		wlist.add(new Statement(3100,3,pagecount.generateTypeVarString() + " = (Integer)" + service.getVarName()+"."+this.countPage.generateServiceMethodDefinition().getStandardCallString()+";"));
		wlist.add(new Statement(3200,3,"if ("+pagenum.getVarName() +" <= "+pagenum.getValue() +") " +pagenum.getVarName() +" = " + pagenum.getValue() +";"));
		wlist.add(new Statement(3300,3,"if ("+pagenum.getVarName() +" >= "+pagecount.getVarName() +") " +pagenum.getVarName() +" = " + pagecount.getVarName() +";"));
		wlist.add(new Statement(3400,3,"if ("+last.getVarName() +") " +pagenum.getVarName() +" = " + pagecount.getVarName() +";"));
		wlist.add(new Statement(4000,3, vlist.generateTypeVarString() + " = " + service.getVarName()+"."+serviceMethod.getStandardCallString()+";"));
		wlist.add(NamedStatementGenerator.getSetRequestAttributeStatement(4500, 3, InterVarUtil.Servlet.request, pagesize));
		wlist.add(NamedStatementGenerator.getSetRequestAttributeStatement(4600, 3, InterVarUtil.Servlet.request, pagenum));
		wlist.add(NamedStatementGenerator.getSetRequestAttributeStatement(4700, 3, InterVarUtil.Servlet.request, pagecount));
		wlist.add(NamedStatementGenerator.getSetRequestAttributeStatement(4800, 3, InterVarUtil.Servlet.request, vlist));
		wlist.add(NamedStatementGenerator.getControllerForward(6000L,3, InterVarUtil.Servlet.response, InterVarUtil.Servlet.request, "../jsp/"+this.domain.getPlural().toLowerCase()+".jsp"));
		wlist.add(NamedStatementListGenerator.generateCatchExceptionPrintStackFooter(7000L, 2));
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
		method.setStandardName("listAll"+StringUtil.capFirst(this.domain.getPlural())+"ByPage");		
		method.setReturnType(new Type("List",this.domain, this.domain.getPackageToken()));
		method.addSignature(new Signature(1,"pagesize",new Type("int")));
		method.addSignature(new Signature(2,"pagenum",new Type("int")));
		method.addAdditionalImport("java.sql.Connection");
		method.addAdditionalImport("java.util.List");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addAdditionalImport(this.domain.getPackageToken()+".database.DBConf");
		method.addAdditionalImport(this.domain.getPackageToken()+".dao."+this.domain.getStandardName()+"Dao");
		method.addAdditionalImport(this.domain.getPackageToken()+".daoimpl."+this.domain.getStandardName()+"DaoImpl");
		method.addAdditionalImport(this.domain.getPackageToken()+".service."+this.domain.getStandardName()+"Service");
		method.setThrowException(true);
		
		Method daomethod = this.generateDaoMethodDefinition();
		
		List<Writeable> list = new ArrayList<Writeable>();
		list.add(NamedStatementListGenerator.generateServiceImplReturnList(1000L, 2, InterVarUtil.DB.connection, InterVarUtil.DB.dbconf, this.domain, InterVarUtil.DB.dao, daomethod));
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
	
	public ListAllByPage(Domain domain){
		super();
		this.domain = domain;
		this.setVerbName("ListAll"+StringUtil.capFirst(this.domain.getPlural())+"ByPage");
		this.countPage = new CountPage(this.domain);
		if (this.noControllerVerbs.size() == 0)
			this.noControllerVerbs.add(this.countPage);
	}
	
	public ListAllByPage(){
		super();
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
		method.addAdditionalImport("java.util.List");
		method.addAdditionalImport("java.util.Map");
		method.addAdditionalImport("java.util.TreeMap");
		method.addAdditionalImport("net.sf.json.JSONObject");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addAdditionalImport(this.domain.getPackageToken()+".service."+this.domain.getStandardName()+"Service");
		method.addAdditionalImport(this.domain.getPackageToken()+".serviceimpl."+this.domain.getStandardName()+"ServiceImpl");

		List<Writeable> wlist = new ArrayList<Writeable>();
		Var resultMap = new Var("result", new Type("TreeMap<String,Object>","java.util"));
		Var pagesize = new Var("pagesize",new Type("int"),"10");
		Var pagenum = new Var("pagenum",new Type("int"),"1");
		Var pagecount = new Var("pagecount",new Type("int"));
		Var last = new Var("last", new Type("boolean"), "false");
		Var service = new Var("service", new Type(this.domain.getStandardName()+"Service",this.domain.getPackageToken()));
		Var vlist = new Var(StringUtil.lowerFirst(this.domain.getStandardName())+"List", new Type("List",this.domain,this.domain.getPackageToken()));
		Method serviceMethod = this.generateServiceMethodDefinition();
		wlist.add(NamedStatementGenerator.getFacadeSetContentType(1000L, 2, InterVarUtil.Servlet.response, InterVarUtil.SimpleJEE.UTF8.getVarName()));
		wlist.add(NamedStatementGenerator.getJsonResultMap(2000L, 2, resultMap));
		wlist.add(NamedStatementGenerator.getControllerPrintWriterOut(3000L, 2, InterVarUtil.Servlet.response, InterVarUtil.Servlet.out));
		wlist.add(NamedStatementGenerator.getTryHead(4000L,2));		
		wlist.add(NamedStatementGenerator.getRequestVarsWithDefaultValueStatement(4500,3,InterVarUtil.Servlet.request,last));
		wlist.add(NamedStatementGenerator.getRequestVarsWithDefaultValueStatement(5000,3,InterVarUtil.Servlet.request,pagesize));
		wlist.add(NamedStatementGenerator.getRequestVarsWithDefaultValueStatement(6000,3,InterVarUtil.Servlet.request,pagenum));		
		wlist.add(new Statement(7000,3,service.generateTypeVarString() + " = new " + service.getVarType().getTypeName()+"Impl();"));
		wlist.add(new Statement(8000,3,pagecount.generateTypeVarString() + " = (Integer)" + service.getVarName()+"."+this.countPage.generateServiceMethodDefinition().getStandardCallString()+";"));
		wlist.add(new Statement(9000,3,"if ("+pagenum.getVarName() +" <= "+pagenum.getValue() +") " +pagenum.getVarName() +" = " + pagenum.getValue() +";"));
		wlist.add(new Statement(10000,3,"if ("+pagenum.getVarName() +" >= "+pagecount.getVarName() +") " +pagenum.getVarName() +" = " + pagecount.getVarName() +";"));
		wlist.add(new Statement(10500L,3, "if ("+ last.getVarName() + ") "+pagenum.getVarName()+" = "+ pagecount.getVarName() +";"));
		wlist.add(new Statement(11000,3, vlist.generateTypeVarString() + " = " + service.getVarName()+"."+serviceMethod.getStandardCallString()+";"));
		
		wlist.add(NamedStatementListGenerator.getPutJsonResultMapWithSuccessAndDomainListPaging(12000L, 3, resultMap,vlist,pagesize,pagenum,pagecount));
		wlist.add(NamedStatementGenerator.getEncodeMapToJsonResultMap(13000L, 3, resultMap,InterVarUtil.Servlet.out));
		wlist.add(NamedStatementListGenerator.generateCatchExceptionPrintStackPrintJsonMapFinallyCloseOutFooter(14000L, 2, InterVarUtil.Servlet.response,resultMap, InterVarUtil.Servlet.out));
		
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
