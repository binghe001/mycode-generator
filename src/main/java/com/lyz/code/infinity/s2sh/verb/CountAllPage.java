package com.lyz.code.infinity.s2sh.verb;

import java.util.ArrayList;
import java.util.List;

import com.lyz.code.infinity.core.Writeable;
import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Method;
import com.lyz.code.infinity.domain.Signature;
import com.lyz.code.infinity.domain.StatementList;
import com.lyz.code.infinity.domain.Type;
import com.lyz.code.infinity.domain.Var;
import com.lyz.code.infinity.generator.NamedStatementListGenerator;
import com.lyz.code.infinity.limitedverb.NoControllerVerb;
import com.lyz.code.infinity.s2sh.core.NamedS2SHStatementGenerator;
import com.lyz.code.infinity.s2sh.core.NamedS2SHStatementListGenerator;
import com.lyz.code.infinity.utils.InterVarUtil;
import com.lyz.code.infinity.utils.StringUtil;
import com.lyz.code.infinity.utils.WriteableUtil;

public class CountAllPage extends NoControllerVerb {

	@Override
	public Method generateDaoImplMethod() {		
			try {
				Method method = new Method();
				method.setStandardName("countAll"+StringUtil.capFirst(this.domain.getPlural())+"Page");
				method.setReturnType(new Type("int"));
				method.addAdditionalImport("org.hibernate.Criteria");
				method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
				method.addSignature(new Signature(1, "pagesize", new Type("int")));
				method.setThrowException(true);
				method.addMetaData("Override");
				
				List<Writeable> list = new ArrayList<Writeable>();
				Var pageSize = new Var("pagesize",new Type("int"));
				StatementList sl = NamedS2SHStatementListGenerator.generateCountPageStatementList(2000L,2,this.domain,pageSize);
				list.add(sl);
				method.setMethodStatementList(WriteableUtil.merge(list));
				return method;
			} catch (Exception e){
				return null;
			}
	}

	@Override
	public String generateDaoImplMethodString() {
		Method m = this.generateDaoImplMethod();
		String s = m.generateMethodString();
		return s;
	}

	@Override
	public String generateDaoImplMethodStringWithSerial() {
		Method m = this.generateDaoImplMethod();
		m.setContent(m.generateMethodContentStringWithSerial());
		m.setMethodStatementList(null);
		return m.generateMethodString();
	}

	@Override
	public Method generateDaoMethodDefinition() {
		Method method = new Method();
		method.setStandardName("countAll"+StringUtil.capFirst(this.domain.getPlural())+"Page");
		method.setReturnType(new Type("int"));
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addSignature(new Signature(1, "pagesize", new Type("int")));
		method.setThrowException(true);
		return method;
	}

	@Override
	public String generateDaoMethodDefinitionString() {
		return generateDaoMethodDefinition().generateMethodDefinition();
	}

	@Override
	public Method generateServiceMethodDefinition() {
		Method method = new Method();
		method.setStandardName("countAll"+StringUtil.capFirst(this.domain.getPlural())+"Page");
		method.setReturnType(new Type("int"));
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addSignature(new Signature(1, "pagesize", new Type("int")));
		method.setThrowException(true);
		
		return method;
	}

	@Override
	public String generateServiceMethodDefinitionString() {
		return generateServiceMethodDefinition().generateMethodDefinition();
	}

	@Override
	public Method generateServiceImplMethod() {
		Method method = new Method();
		method.setStandardName("countAll"+StringUtil.capFirst(this.domain.getPlural())+"Page");
		method.setReturnType(new Type("int"));
		method.addSignature(new Signature(1, "pagesize", new Type("int")));
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addAdditionalImport(this.domain.getPackageToken()+".dao."+this.domain.getStandardName()+"Dao");
		method.addAdditionalImport(this.domain.getPackageToken()+".daoimpl."+this.domain.getStandardName()+"DaoImpl");
		method.addAdditionalImport(this.domain.getPackageToken()+".service."+this.domain.getStandardName()+"Service");
		method.setThrowException(true);
		method.addMetaData("Override");
		
		Method daomethod = this.generateDaoMethodDefinition();
		
		List<Writeable> list = new ArrayList<Writeable>();
		list.add(NamedS2SHStatementGenerator.generateServiceImplReturnInt(1000L, 2, InterVarUtil.DB.dao, daomethod));
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
	
	public CountAllPage(Domain domain){
		super();
		this.domain = domain;
		this.verbName = "countAll"+this.domain.getPlural()+"Page";
	}
	
	public CountAllPage(){
		super();
	}

}
