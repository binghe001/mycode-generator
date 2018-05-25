package com.lyz.code.infinity.s2sh.verb;

import java.util.ArrayList;
import java.util.List;

import com.lyz.code.infinity.core.Verb;
import com.lyz.code.infinity.core.Writeable;
import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Method;
import com.lyz.code.infinity.domain.Signature;
import com.lyz.code.infinity.domain.Statement;
import com.lyz.code.infinity.domain.Type;
import com.lyz.code.infinity.domain.Var;
import com.lyz.code.infinity.generator.NamedStatementGenerator;
import com.lyz.code.infinity.generator.NamedStatementListGenerator;
import com.lyz.code.infinity.s2sh.core.NamedS2SHStatementGenerator;
import com.lyz.code.infinity.s2sh.core.NamedS2SHStatementListGenerator;
import com.lyz.code.infinity.utils.InterVarUtil;
import com.lyz.code.infinity.utils.StringUtil;
import com.lyz.code.infinity.utils.WriteableUtil;

public class SoftDelete extends Verb{

	@Override
	public Method generateDaoImplMethod() {
		Method method = new Method();
		method.setStandardName("softDelete"+StringUtil.capFirst(this.domain.getStandardName()));
		method.setReturnType(new Type("void"));
		method.setThrowException(true);
		method.addAdditionalImport("java.util.List");
		method.addAdditionalImport("org.springframework.transaction.annotation.Transactional");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addAdditionalImport(this.domain.getPackageToken()+".dao."+this.domain.getStandardName()+"Dao");
		method.addSignature(new Signature(1,this.domain.getDomainId().getFieldName(),this.domain.getDomainId().getRawType()));
		method.addMetaData("Override");
		method.addMetaData("Transactional");
		
		List<Writeable> list = new ArrayList<Writeable>();
		list.add(NamedS2SHStatementGenerator.getSoftDeleteHqlStatement(1000L,2, this.domain,InterVarUtil.DB.query));
		list.add(NamedS2SHStatementGenerator.getExecutiveUpdateHqlUsingDomainIdStatement(2000L,2, this.domain, InterVarUtil.DB.query));
		method.setMethodStatementList(WriteableUtil.merge(list));
		return method;
	}

	@Override
	public Method generateDaoMethodDefinition() {
		Method method = new Method();
		method.setStandardName("softDelete"+StringUtil.capFirst(this.domain.getStandardName()));
		method.setReturnType(new Type("void"));
		method.setThrowException(true);
		method.addAdditionalImport("java.util.List");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addSignature(new Signature(1,this.domain.getDomainId().getFieldName(),this.domain.getDomainId().getRawType()));
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
		method.setStandardName("softDelete"+StringUtil.capFirst(this.domain.getStandardName()));
		method.setReturnType(new Type("void"));
		method.setThrowException(true);
		method.addAdditionalImport("java.util.List");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addSignature(new Signature(1,this.domain.getDomainId().getFieldName(),this.domain.getDomainId().getRawType()));
		return method;
	}

	@Override
	public String generateServiceMethodDefinitionString() {
		return generateServiceMethodDefinition().generateMethodDefinition();
	}

	@Override
	public Method generateControllerMethod() {
		Method method = new Method();
		method.setStandardName("softDelete"+StringUtil.capFirst(this.domain.getStandardName()));
		method.setReturnType(new Type("String"));
		method.setThrowException(true);
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addAdditionalImport(this.domain.getPackageToken()+".service."+this.domain.getStandardName()+"Service");
		
		List<Writeable> wlist = new ArrayList<Writeable>();
		Var service = new Var("service", new Type(this.domain.getStandardName()+"Service",this.domain.getPackageToken()));

		wlist.add(NamedS2SHStatementGenerator.getActionCallServiceMethodByDomainId(1000L, 2,this.domain, service, generateServiceMethodDefinition()));
		wlist.add(new Statement(2000L, 2, "return \""+method.getStandardName()+"\";"));
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
		method.setStandardName("softDelete"+StringUtil.capFirst(this.domain.getStandardName()));
		method.setReturnType(new Type("void"));
		method.setThrowException(true);
		method.addAdditionalImport("java.util.List");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addAdditionalImport(this.domain.getPackageToken()+".dao."+this.domain.getStandardName()+"Dao");
		method.addAdditionalImport(this.domain.getPackageToken()+".daoimpl."+this.domain.getStandardName()+"DaoImpl");
		method.addAdditionalImport(this.domain.getPackageToken()+".service."+this.domain.getStandardName()+"Service");
		method.addSignature(new Signature(1,this.domain.getDomainId().getFieldName(),this.domain.getDomainId().getRawType()));
		method.addMetaData("Override");
	
		//Service method
		Method daomethod = this.generateDaoMethodDefinition();
				
		List<Writeable> list = new ArrayList<Writeable>();
		list.add(new Statement(100L,2,daomethod.generateStandardDaoImplCallString(InterVarUtil.DB.dao.getVarName())+";"));
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

	public SoftDelete(){
		super();
	}
	
	public SoftDelete(Domain domain){
		super();
		this.domain = domain;
		this.setVerbName("SoftDelete"+StringUtil.capFirst(this.domain.getStandardName()));
	}
	
	@Override
	public void setDomain(Domain domain){
		super.setDomain(domain);
		this.setVerbName("SoftDelete"+StringUtil.capFirst(domain.getPlural()));
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
		method.setStandardName("softDelete"+StringUtil.capFirst(this.domain.getStandardName()));
		method.setReturnType(new Type("String"));
		method.setThrowException(true);
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addAdditionalImport(this.domain.getPackageToken()+".service."+this.domain.getStandardName()+"Service");
		
		List<Writeable> wlist = new ArrayList<Writeable>();
		Var service = new Var("service", new Type(this.domain.getStandardName()+"Service",this.domain.getPackageToken()));
		Var resultMap = new Var("result", new Type("TreeMap<String,Object>","java.util"));
		wlist.add(NamedS2SHStatementGenerator.getJsonResultMap(1000L, 2, resultMap));
		wlist.add(NamedS2SHStatementGenerator.getActionCallServiceMethodByDomainId(2000L, 2,this.domain, service, generateServiceMethodDefinition()));
		wlist.add(NamedS2SHStatementListGenerator.getPutJsonResultMapWithSuccessAndNull(3000L, 2, resultMap));
		wlist.add(NamedS2SHStatementGenerator.getEncodeMapToJsonResultMap(4000L, 2, resultMap));
		wlist.add(NamedS2SHStatementGenerator.getJumpToActionSuccess(5000L, 2));
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
