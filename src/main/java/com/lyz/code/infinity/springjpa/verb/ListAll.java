package com.lyz.code.infinity.springjpa.verb;

import java.util.ArrayList;
import java.util.List;

import com.lyz.code.infinity.core.Verb;
import com.lyz.code.infinity.core.Writeable;
import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Method;
import com.lyz.code.infinity.domain.Statement;
import com.lyz.code.infinity.domain.StatementList;
import com.lyz.code.infinity.domain.Type;
import com.lyz.code.infinity.domain.Var;
import com.lyz.code.infinity.noun.User;
import com.lyz.code.infinity.s2sh.core.NamedS2SHStatementGenerator;
import com.lyz.code.infinity.s2sh.core.NamedS2SHStatementListGenerator;
import com.lyz.code.infinity.utils.InterVarUtil;
import com.lyz.code.infinity.utils.StringUtil;
import com.lyz.code.infinity.utils.WriteableUtil;

public class ListAll extends Verb{
	@Override
	public Method generateDaoImplMethod(){
		try {
			Method method = new Method();
			method.setStandardName("listAll"+StringUtil.capFirst(this.domain.getPlural()));
			method.setReturnType(new Type("List",this.domain, this.domain.getPackageToken()));
			method.addAdditionalImport("java.util.List");
			method.addAdditionalImport("org.springframework.orm.hibernate4.support.HibernateDaoSupport");
			method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
			method.addAdditionalImport(this.domain.getPackageToken()+".dao."+this.domain.getStandardName()+"Dao");
			method.setThrowException(true);
			method.addMetaData("Override");
			
			List<Writeable> list = new ArrayList<Writeable>();
			StatementList sl = NamedS2SHStatementListGenerator.generateSelectAllQueryStatementList(1000L,2,this.domain);
			list.add(sl);
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
		method.setStandardName("listAll"+StringUtil.capFirst(this.domain.getPlural()));
		method.setReturnType(new Type("List",this.domain, this.domain.getPackageToken()));
		method.addAdditionalImport("java.util.List");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.setThrowException(true);
		method.addMetaData("Override");
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
		method.setStandardName("listAll"+StringUtil.capFirst(this.domain.getPlural()));
		method.setReturnType(new Type("List",this.domain, this.domain.getPackageToken()));
		method.addAdditionalImport("java.util.List");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.setThrowException(true);
		method.addMetaData("Override");
		
		return method;
	}

	@Override
	public String generateServiceMethodDefinitionString() {
		return generateServiceMethodDefinition().generateMethodDefinition();
	}
	
	@Override
	public Method generateControllerMethod() {
		Method method = new Method();
		method.setStandardName(StringUtil.lowerFirst(this.getVerbName()));
		method.setReturnType(new Type("String"));
		method.setThrowException(true);
		method.setIsprotected(false);
		method.addAdditionalImport("java.util.List");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addAdditionalImport(this.domain.getPackageToken()+".service."+this.domain.getStandardName()+"Service");

		List<Writeable> wlist = new ArrayList<Writeable>();
		Var service = new Var("service", new Type(this.domain.getStandardName()+"Service",this.domain.getPackageToken()));
		Var vlist = new Var("list", new Type("List",this.domain,this.domain.getPackageToken()));
		Method serviceMethod = this.generateServiceMethodDefinition();
		wlist.add(new Statement(1000,2, vlist.generateTypeVarString() + " = " + service.getVarName()+"."+serviceMethod.getStandardName()+"();"));
		wlist.add(new Statement(2000,2,this.domain.getLowerFirstDomainName()+StringUtil.capFirst(InterVarUtil.Servlet.request.getVarName())+".put(\""+StringUtil.lowerFirst(this.domain.getStandardName())+"List\","+vlist.getVarName()+");"));
		wlist.add(NamedS2SHStatementGenerator.getActionForward(3000L,2, serviceMethod.getStandardName()));

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
		method.setStandardName("listAll"+StringUtil.capFirst(this.domain.getPlural()));
		method.setReturnType(new Type("List",this.domain, this.domain.getPackageToken()));
		method.addAdditionalImport("java.util.List");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addAdditionalImport(this.domain.getPackageToken()+".dao."+this.domain.getStandardName()+"Dao");
		method.addAdditionalImport(this.domain.getPackageToken()+".daoimpl."+this.domain.getStandardName()+"DaoImpl");
		method.addAdditionalImport(this.domain.getPackageToken()+".service."+this.domain.getStandardName()+"Service");
		method.setThrowException(true);
		method.addMetaData("Override");
		
		Method daomethod = this.generateDaoMethodDefinition();
		
		List<Writeable> list = new ArrayList<Writeable>();
		list.add(NamedS2SHStatementListGenerator.generateServiceImplReturnList(1000L, 2, this.domain, InterVarUtil.DB.dao, daomethod));
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
	
	public ListAll(){
		super();
		if (this.domain != null) this.setVerbName("ListAll"+StringUtil.capFirst(this.domain.getPlural()));
		else this.setVerbName("ListAll");
	}
	
	public ListAll(Domain domain){
		super();
		this.domain = domain;
		this.setVerbName("ListAll"+StringUtil.capFirst(this.domain.getPlural()));
	}
	
	@Override
	public void setDomain(Domain domain){
		super.setDomain(domain);
		this.setVerbName("ListAll"+StringUtil.capFirst(domain.getPlural()));
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
		// TODO Auto-generated method stub
		return null;
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
