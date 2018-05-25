package com.lyz.code.infinity.s2sh.verb;

import java.util.ArrayList;
import java.util.List;

import com.lyz.code.infinity.core.Verb;
import com.lyz.code.infinity.core.Writeable;
import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Method;
import com.lyz.code.infinity.domain.Signature;
import com.lyz.code.infinity.domain.Type;
import com.lyz.code.infinity.domain.Var;
import com.lyz.code.infinity.utils.StringUtil;
import com.lyz.code.infinity.utils.WriteableUtil;

public class AddAll extends Verb{

	@Override
	public Method generateDaoImplMethod() {
		this.method = new Method();
		this.method.setStandardName("addAll"+StringUtil.capFirst(this.domain.getStandardName()));
		this.method.setReturnType(new Type("boolean"));
		this.additionalImports.add("java.util.List");
		this.additionalImports.add(this.domain.getPackageToken()+"."+this.domain.getStandardName());
		this.method.setContent("");
		return this.method;
	}

	@Override
	public Method generateDaoMethodDefinition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generateDaoImplMethodString() {
		return generateDaoImplMethod().generateMethodString();
	}

	@Override
	public String generateDaoMethodDefinitionString() {
		return generateDaoMethodDefinition().generateMethodString();
	}

	@Override
	public String generateDaoImplMethodStringWithSerial() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Method generateServiceMethodDefinition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generateServiceMethodDefinitionString() {
		return generateServiceMethodDefinition().generateMethodString();
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
		method.addSignature(new Signature(1,"response",new Type("HttpServletResponse","javax.servlet.http")));
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generateServiceImplMethodString() {
		return generateServiceImplMethod().generateMethodString();
	}

	@Override
	public String generateServiceImplMethodStringWithSerial() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public AddAll(){
		super();
	}
	
	public AddAll(Domain domain){
		super();
		this.domain = domain;
		this.setVerbName("AddAll"+StringUtil.capFirst(this.domain.getPlural()));
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
