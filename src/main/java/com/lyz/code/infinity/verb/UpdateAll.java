package com.lyz.code.infinity.verb;

import java.util.ArrayList;
import java.util.List;

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

public class UpdateAll extends Verb{

	@Override
	public Method generateDaoImplMethod() {
		Method method = new Method();
		method.setStandardName("updateAll"+StringUtil.capFirst(this.domain.getPlural()));
		method.setReturnType(new Type("boolean"));
		method.setThrowException(true);
		this.additionalImports.add(this.domain.getPackageToken()+"."+this.domain.getStandardName());
		method.addSignature(new Signature(1,InterVarUtil.DB.connection.getVarName(),InterVarUtil.DB.connection.getVarType()));
		method.addSignature(new Signature(2, StringUtil.lowerFirst(this.domain.getStandardName()), this.domain.getType()));
		
		List<Writeable> list = new ArrayList<Writeable>();
		list.add(NamedStatementGenerator.getUpdateSqlStatement(1000L,2, this.domain,InterVarUtil.DB.query));
		list.add(NamedStatementGenerator.getPrepareStatement(2000L,2, InterVarUtil.DB.ps, InterVarUtil.DB.query, InterVarUtil.DB.connection));
		list.add(NamedStatementListGenerator.generatePsSetDomainFields(3000L, 2,this.domain, InterVarUtil.DB.ps));
		list.add(NamedStatementGenerator.getPrepareStatementExcuteUpdate(4000L,2, InterVarUtil.DB.result, InterVarUtil.DB.ps));
		list.add(NamedStatementListGenerator.generateResultReturnSuccess(5000L,2, InterVarUtil.DB.result));
		method.setMethodStatementList(WriteableUtil.merge(list));
		return method;
	}

	@Override
	public Method generateDaoMethodDefinition() {
		Method method = new Method();
		method.setStandardName("updateAll"+StringUtil.capFirst(this.domain.getPlural()));
		method.setReturnType(new Type("boolean"));
		method.setThrowException(true);
		this.additionalImports.add(this.domain.getPackageToken()+"."+this.domain.getStandardName());
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
		return generateDaoImplMethod().generateMethodContentStringWithSerial();
	}

	@Override
	public Method generateServiceMethodDefinition() {
		Method method = new Method();
		method.setStandardName("updateAll"+StringUtil.capFirst(this.domain.getPlural()));
		method.setReturnType(new Type("boolean"));
		method.setThrowException(true);
		this.additionalImports.add(this.domain.getPackageToken()+"."+this.domain.getStandardName());
		method.addSignature(new Signature(2, StringUtil.lowerFirst(this.domain.getStandardName()), this.domain.getType()));
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
		this.additionalImports.add("java.io.IOException");
		this.additionalImports.add("javax.servlet.ServletException");
		this.additionalImports.add("javax.servlet.http.HttpServlet");
		this.additionalImports.add("javax.servlet.http.HttpServletRequest");
		this.additionalImports.add("javax.servlet.http.HttpServletResponse");
		this.additionalImports.add("java.util.List");
		
		List<Writeable> wlist = new ArrayList<Writeable>();
		Var service = new Var("service", new Type(this.domain.getStandardName()+"Service",this.domain.getPackageToken()));
		Var domain = new Var(StringUtil.lowerFirst(this.domain.getStandardName()), new Type(this.domain.getStandardName(),this.domain,this.domain.getPackageToken()));
		wlist.add(NamedStatementGenerator.getControllerSetContentType(1000L, 2, InterVarUtil.Servlet.response, InterVarUtil.SimpleJEE.UTF8.getVarName()));
		wlist.add(NamedStatementGenerator.getControllerPrintWriterOut(2000L, 2, InterVarUtil.Servlet.response, InterVarUtil.Servlet.out));
		wlist.add(NamedStatementGenerator.getTryHead(3000L, 2));
		wlist.add(NamedStatementGenerator.getPrepareDomainVarInit(4000L, 2, this.domain));
		wlist.add(NamedStatementListGenerator.generateSetDomainDataFromRequest(5000L, 2, this.domain, InterVarUtil.Servlet.request));
		wlist.add(NamedStatementGenerator.getPrepareService(7000L,2, service));
		wlist.add(NamedStatementGenerator.getCallServiceMethod(8000L, 2, service, generateServiceMethodDefinition()));
		wlist.add(NamedStatementGenerator.getResponseRedirectUrl(9000L, 2, InterVarUtil.Servlet.response, "../controller/listAll"+this.domain.getStandardName()+"Controller"));
		wlist.add(NamedStatementListGenerator.generateCatchExceptionPrintStackRedirectUrlFinallyCloseOutFooter(10000L, 2, InterVarUtil.Servlet.response,"../controller/listAll"+this.domain.getStandardName()+"Controller", InterVarUtil.Servlet.out));
		// TODO Auto-generated method stub
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
		method.setStandardName("update"+StringUtil.capFirst(this.domain.getStandardName()));
		method.setReturnType(new Type("boolean"));
		method.setThrowException(true);
		this.additionalImports.add(this.domain.getPackageToken()+"."+this.domain.getStandardName());
		method.addSignature(new Signature(1, StringUtil.lowerFirst(this.domain.getStandardName()), this.domain.getType()));
		
		//Service method
		Method daomethod = this.generateServiceMethodDefinition();
				
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
	
	public UpdateAll(Domain domain){
		super();
		this.domain = domain;
		this.setVerbName("UpdateAll"+StringUtil.capFirst(this.domain.getPlural()));
	}
	
	public UpdateAll(){
		super();
	}

	@Override
	public String generateControllerMethodStringWithSerial() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Method generateFacadeMethod() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generateFacadeMethodString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generateFacadeMethodStringWithSerial() {
		// TODO Auto-generated method stub
		return null;
	}
}
