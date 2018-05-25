package com.lyz.code.infinity.verb;

import java.util.ArrayList;
import java.util.List;

import com.lyz.code.infinity.core.Verb;
import com.lyz.code.infinity.core.Writeable;
import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Field;
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

public class ListMy extends Verb{
	protected Field userId;
	@Override
	public Method generateDaoImplMethod() {
		try {
			Method method = new Method();
			method.setStandardName("listMy"+StringUtil.capFirst(this.domain.getPlural()));
			method.setReturnType(new Type("List",this.domain, this.domain.getPackageToken()));
			method.addAdditionalImport("java.util.List");
			method.addAdditionalImport(this.domain.getPackageToken()+"."+this.domain.getStandardName());
			method.addSignature(new Signature(1,"connection",new Type("Connection","java.sql"),"java.sql"));
			method.addSignature(new Signature(2,this.getUserId().getFieldName(),new Type("long")));
			method.setThrowException(true);
			
			List<Writeable> list = new ArrayList<Writeable>();
			list.add(NamedStatementGenerator.getTryHead(1000L,2));
			list.add(NamedStatementGenerator.getSelectByFieldSqlStatement(2000L,2,domain,InterVarUtil.DB.query,this.getUserId()));
			list.add(NamedStatementGenerator.getPrepareStatement(3000L,2,InterVarUtil.DB.ps, InterVarUtil.DB.query, InterVarUtil.DB.connection));
			list.add(NamedStatementGenerator.getPrepareStatementSetFieldStatement(4000L, 2,domain, InterVarUtil.DB.ps, this.getUserId()));
			list.add(NamedStatementGenerator.getPrepareStatementExcuteQuery(5000L,2, InterVarUtil.DB.result, InterVarUtil.DB.ps));
			list.add(NamedStatementGenerator.getPrepareDaomainArrayList(6000L,2, domain));
			list.add(NamedStatementGenerator.getResultSetWhileNextLoopHead(7000L,2, InterVarUtil.DB.result));  
			list.add(NamedStatementGenerator.getSingleLineComment(8000L,2,"Build the list object."));
			list.add(NamedStatementGenerator.getPrepareDomainVarInit(9000L,2, domain));
			list.add(NamedStatementListGenerator.generateSetDomainDataFromResultSet(10000L,2,domain, InterVarUtil.DB.result));
			list.add(NamedStatementGenerator.getSingleLineComment(11000L,2, "Build the object list."));			
			list.add(NamedStatementGenerator.getAddDomaintoList(12000L,2, domain, InterVarUtil.Container.getList(domain)));
			list.add(NamedStatementGenerator.getLoopFooter(13000L,2));
			list.add(NamedStatementGenerator.getReturnDomainList(14000,2, domain, InterVarUtil.Container.getList(domain)));
			list.add(NamedStatementListGenerator.generateCatchExceptionPrintStackReturnNullFooter(15000L,2));
			method.setMethodStatementList(WriteableUtil.merge(list));
			return method;
		} catch (Exception e){
			return null;
		}
	}

	@Override
	public Method generateDaoMethodDefinition() {
		Method method = new Method();
		method.setStandardName("listMy"+StringUtil.capFirst(this.domain.getPlural()));
		method.setReturnType(new Type("List",this.domain, this.domain.getPackageToken()));
		method.addAdditionalImport(this.domain.getPackageToken()+"."+this.domain.getStandardName());
		method.addSignature(new Signature(1,"connection",new Type("Connection","java.sql"),"java.sql"));
		method.addSignature(new Signature(2,this.getUserId().getFieldName(),new Type("long")));
		method.setThrowException(true);
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
		method.setStandardName("listMy"+StringUtil.capFirst(this.domain.getPlural()));
		method.setReturnType(new Type("List",this.domain, this.domain.getPackageToken()));
		method.addAdditionalImport("java.util.List");
		method.addAdditionalImport(this.domain.getPackageToken()+"."+this.domain.getStandardName());
		method.addSignature(new Signature(1,this.getUserId().getFieldName(),new Type("long")));
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
		this.additionalImports.add("java.io.IOException");
		this.additionalImports.add("javax.servlet.ServletException");
		this.additionalImports.add("javax.servlet.http.HttpServlet");
		this.additionalImports.add("javax.servlet.http.HttpServletRequest");
		this.additionalImports.add("javax.servlet.http.HttpServletResponse");
		this.additionalImports.add("java.util.List");
		
		List<Writeable> wlist = new ArrayList<Writeable>();
		Var service = new Var("service", new Type(this.domain.getStandardName()+"Service",this.domain.getPackageToken()));
		Var vlist = new Var("list", new Type("List",this.domain,this.domain.getPackageToken()));
		Method serviceMethod = this.generateServiceMethodDefinition();
		wlist.add(NamedStatementGenerator.getControllerSetContentType(1000L, 2, InterVarUtil.Servlet.response, InterVarUtil.SimpleJEE.UTF8.getVarName()));
		wlist.add(NamedStatementGenerator.getTryHead(2000L,2));
		wlist.add(NamedStatementGenerator.getSetFieldFromRequest(3000L, 2, this.domain, InterVarUtil.Servlet.request,this.getUserId()));
		wlist.add(new Statement(3000,2,service.generateTypeVarString() + " = new " + service.getVarType().getTypeName()+"Impl();"));
		wlist.add(new Statement(4000,2, vlist.generateTypeVarString() + " = " + service.getVarName()+"."+serviceMethod.generateStandardCallString()+";"));
		wlist.add(new Statement(5000,2,InterVarUtil.Servlet.request.getVarName()+".setAttribute(\""+StringUtil.lowerFirst(this.domain.getStandardName())+"List\","+vlist.getVarName()+");"));
		wlist.add(NamedStatementGenerator.getControllerForward(6000L,2, InterVarUtil.Servlet.response, InterVarUtil.Servlet.request, "../jsp/"+this.domain.getPlural().toLowerCase()+".jsp"));
		wlist.add(NamedStatementListGenerator.generateCatchExceptionPrintStackReturnNullFooter(7000L, 2));
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
		method.setStandardName("listMy"+StringUtil.capFirst(this.domain.getPlural()));
		method.setReturnType(new Type("List",this.domain, this.domain.getPackageToken()));
		method.addAdditionalImport("java.util.List");
		method.addAdditionalImport(this.domain.getPackageToken()+"."+this.domain.getStandardName());
		method.addSignature(new Signature(1,this.getUserId().getFieldName(),new Type("long")));
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
		
	public ListMy(){
		super();
		if (this.domain != null) this.setVerbName("ListMy"+StringUtil.capFirst(this.domain.getPlural()));
		else this.setVerbName("ListMy");
	}
	
	public ListMy(Domain domain, String searchFieldName){
		super();
		this.domain = domain;
		this.userId = domain.getField(searchFieldName);
		this.setVerbName("ListMy"+StringUtil.capFirst(this.domain.getPlural()));
	}

	public Field getUserId() {
		return userId;
	}

	public void setUserId(Field userId) {
		this.userId = userId;
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
