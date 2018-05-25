package com.lyz.code.infinity.verb;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.lyz.code.infinity.core.Verb;
import com.lyz.code.infinity.core.Writeable;
import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Method;
import com.lyz.code.infinity.domain.Signature;
import com.lyz.code.infinity.domain.Type;
import com.lyz.code.infinity.domain.Var;
import com.lyz.code.infinity.generator.NamedStatementGenerator;
import com.lyz.code.infinity.generator.NamedStatementListGenerator;
import com.lyz.code.infinity.utils.InterVarUtil;
import com.lyz.code.infinity.utils.StringUtil;
import com.lyz.code.infinity.utils.WriteableUtil;

public class Add extends Verb{

	@Override
	public Method generateDaoImplMethod() {
		Method method = new Method();
		method.setStandardName("add"+StringUtil.capFirst(this.domain.getStandardName()));
		method.setReturnType(new Type("boolean"));
		method.setThrowException(true);
		method.addAdditionalImport("java.sql.Connection");
		method.addAdditionalImport("java.util.List");
		method.addAdditionalImport("java.util.ArrayList");
		method.addAdditionalImport("java.sql.PreparedStatement");
		method.addAdditionalImport("java.sql.ResultSet");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addAdditionalImport(this.domain.getPackageToken()+".dao."+this.domain.getStandardName()+"Dao");
		method.addSignature(new Signature(1,InterVarUtil.DB.connection.getVarName(),InterVarUtil.DB.connection.getVarType()));
		method.addSignature(new Signature(2, StringUtil.lowerFirst(this.domain.getStandardName()), this.domain.getType()));
		
		List<Writeable> list = new ArrayList<Writeable>();
		list.add(NamedStatementGenerator.getAddSqlStatement(1000L,2, this.domain,InterVarUtil.DB.query));
		list.add(NamedStatementGenerator.getPrepareStatementWithReturnGeneratedKeys(2000L,2, InterVarUtil.DB.ps, InterVarUtil.DB.query, InterVarUtil.DB.connection));
		list.add(NamedStatementListGenerator.generatePsSetDomainFieldsWithoutId(3000L, 2,this.domain, InterVarUtil.DB.ps));
		list.add(NamedStatementGenerator.getPrepareStatementExcuteUpdate(4000L,2, InterVarUtil.DB.result, InterVarUtil.DB.ps));
		list.add(NamedStatementListGenerator.generateResultReturnSuccessUpdatedDomainId(5000L,2, InterVarUtil.DB.result,InterVarUtil.DB.ps,this.domain));
		method.setMethodStatementList(WriteableUtil.merge(list));
		return method;
	}
	
	

	@Override
	public Method generateDaoMethodDefinition() {
		Method method = new Method();
		method.setStandardName("add"+StringUtil.capFirst(this.domain.getStandardName()));
		method.setReturnType(new Type("boolean"));
		method.setThrowException(true);
		method.addAdditionalImport("java.sql.Connection");
		method.addAdditionalImport("java.util.List");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		
		method.addSignature(new Signature(1,InterVarUtil.DB.connection.getVarName(),InterVarUtil.DB.connection.getVarType()));
		method.addSignature(new Signature(2, StringUtil.lowerFirst(this.domain.getStandardName()), this.domain.getType()));
		return method;
	}

	@Override
	public String generateDaoImplMethodString() {
		return generateDaoImplMethod().generateMethodString();
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
		method.setStandardName("add"+StringUtil.capFirst(this.domain.getStandardName()));
		method.setReturnType(new Type("boolean"));
		method.setThrowException(true);
		method.addAdditionalImport("java.util.List");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addSignature(new Signature(1, StringUtil.lowerFirst(this.domain.getStandardName()), this.domain.getType()));
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
		method.addAdditionalImport("java.io.PrintWriter");
		method.addAdditionalImport("javax.servlet.ServletException");
		method.addAdditionalImport("javax.servlet.http.HttpServlet");
		method.addAdditionalImport("javax.servlet.http.HttpServletRequest");
		method.addAdditionalImport("javax.servlet.http.HttpServletResponse");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addAdditionalImport(this.domain.getPackageToken()+".service."+this.domain.getStandardName()+"Service");
		method.addAdditionalImport(this.domain.getPackageToken()+".serviceimpl."+this.domain.getStandardName()+"ServiceImpl");
		
		List<Writeable> wlist = new ArrayList<Writeable>();
		Var service = new Var("service", new Type(this.domain.getStandardName()+"Service",this.domain.getPackageToken()));
		wlist.add(NamedStatementGenerator.getControllerSetContentType(1000L, 2, InterVarUtil.Servlet.response, InterVarUtil.SimpleJEE.UTF8.getVarName()));
		wlist.add(NamedStatementGenerator.getControllerPrintWriterOut(2000L, 2, InterVarUtil.Servlet.response, InterVarUtil.Servlet.out));
		wlist.add(NamedStatementGenerator.getTryHead(3000L, 2));
		wlist.add(NamedStatementGenerator.getPrepareDomainVarInit(4000L, 3, this.domain));
		wlist.add(NamedStatementListGenerator.generateSetDomainDataFromRequestWithoutDomainId(5000L, 3, this.domain, InterVarUtil.Servlet.request));
		wlist.add(NamedStatementGenerator.getPrepareService(7000L,3, service));
		wlist.add(NamedStatementGenerator.getCallServiceMethod(8000L, 3, service, generateServiceMethodDefinition()));
		wlist.add(NamedStatementGenerator.getResponseRedirectUrl(9000L, 3, InterVarUtil.Servlet.response, "../controller/listAll"+this.domain.getPlural()+"ByPageController?last=true"));
		wlist.add(NamedStatementListGenerator.generateCatchExceptionPrintStackRedirectUrlFinallyCloseOutFooter(10000L, 2, InterVarUtil.Servlet.response,"../controller/listAll"+this.domain.getPlural()+"ByPageController?last=true", InterVarUtil.Servlet.out));
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
		method.setStandardName("add"+StringUtil.capFirst(this.domain.getStandardName()));
		method.setReturnType(new Type("boolean"));
		method.setThrowException(true);
		method.addAdditionalImport("java.sql.Connection");
		method.addAdditionalImport("java.util.List");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addAdditionalImport(this.domain.getPackageToken()+".database.DBConf");
		method.addAdditionalImport(this.domain.getPackageToken()+".dao."+this.domain.getStandardName()+"Dao");
		method.addAdditionalImport(this.domain.getPackageToken()+".daoimpl."+this.domain.getStandardName()+"DaoImpl");
		method.addAdditionalImport(this.domain.getPackageToken()+".service."+this.domain.getStandardName()+"Service");
	
		
		method.addSignature(new Signature(1, StringUtil.lowerFirst(this.domain.getStandardName()), this.domain.getType()));
		
		//Service method
		Method daomethod = this.generateDaoMethodDefinition();
				
		List<Writeable> list = new ArrayList<Writeable>();
		list.add(NamedStatementListGenerator.generateServiceImplReturnBoolean(1000L, 2, InterVarUtil.DB.connection, InterVarUtil.DB.dbconf, InterVarUtil.DB.dao, daomethod));
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
	
	public Add(){
		super();
	}
	
	public Add(Domain domain){
		super();
		this.domain = domain;
		this.setVerbName("Add"+StringUtil.capFirst(this.domain.getStandardName()));
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
		wlist.add(NamedStatementGenerator.getFacadeSetContentType(1000L, 2, InterVarUtil.Servlet.response, InterVarUtil.SimpleJEE.UTF8.getVarName()));
		wlist.add(NamedStatementGenerator.getControllerPrintWriterOut(2000L, 2, InterVarUtil.Servlet.response, InterVarUtil.Servlet.out));
		wlist.add(NamedStatementGenerator.getJsonResultMap(2500L, 2, resultMap));
		wlist.add(NamedStatementGenerator.getTryHead(3000L, 2));
		wlist.add(NamedStatementGenerator.getPrepareDomainVarInit(4000L, 3, this.domain));
		wlist.add(NamedStatementListGenerator.generateSetDomainDataFromRequestWithoutDomainId(5000L, 3, this.domain, InterVarUtil.Servlet.request));
		wlist.add(NamedStatementGenerator.getPrepareService(7000L,3, service));
		wlist.add(NamedStatementGenerator.getCallServiceMethodAssignSuccess(8000L, 3, service, generateServiceMethodDefinition()));
		wlist.add(NamedStatementListGenerator.getPutJsonResultMapWithSuccessAndDomain(8500L, 3, resultMap,this.domain));
		wlist.add(NamedStatementGenerator.getEncodeMapToJsonResultMap(9000L, 3, resultMap,InterVarUtil.Servlet.out));
		wlist.add(NamedStatementListGenerator.generateCatchExceptionPrintStackPrintJsonMapFinallyCloseOutFooter(10000L, 2, InterVarUtil.Servlet.response, resultMap, InterVarUtil.Servlet.out));
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
