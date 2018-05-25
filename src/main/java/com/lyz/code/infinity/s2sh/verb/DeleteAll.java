package com.lyz.code.infinity.s2sh.verb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.lyz.code.infinity.core.Verb;
import com.lyz.code.infinity.core.VerbFactory;
import com.lyz.code.infinity.core.Writeable;
import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Method;
import com.lyz.code.infinity.domain.Signature;
import com.lyz.code.infinity.domain.Statement;
import com.lyz.code.infinity.domain.Type;
import com.lyz.code.infinity.domain.Var;
import com.lyz.code.infinity.generator.NamedStatementGenerator;
import com.lyz.code.infinity.s2sh.core.NamedS2SHStatementGenerator;
import com.lyz.code.infinity.s2sh.core.NamedS2SHStatementListGenerator;
import com.lyz.code.infinity.utils.InterVarUtil;
import com.lyz.code.infinity.utils.StringUtil;
import com.lyz.code.infinity.utils.WriteableUtil;

public class DeleteAll extends Verb{

	@Override
	public Method generateDaoImplMethod() throws Exception{
		Method method = new Method();
		method.setStandardName("deleteAll"+StringUtil.capFirst(this.domain.getPlural()));
		method.setReturnType(new Type("void"));
		method.setThrowException(true);
		method.addAdditionalImport("java.util.List");
		method.addAdditionalImport("org.springframework.transaction.annotation.Transactional");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addAdditionalImport(this.domain.getPackageToken()+".dao."+this.domain.getStandardName()+"Dao");
		method.addSignature(new Signature(1,"ids",new Type("String")));
		method.addMetaData("Override");
		method.addMetaData("Transactional");
		
		Var ids = new Var("ids",new Type("String"));
		Var idArr = new Var("idArr",new Type("String []"));
		Var idString = new Var("idString", new Type("String"));
		Var id = new Var(this.domain.getDomainId().getFieldName(),new Type(this.domain.getDomainId().getFieldType()));
		Method delete = S2SHVerbFactory.getInstance("delete", this.domain).generateDaoImplMethod();
		List<Writeable> list = new ArrayList<Writeable>();
		list.add(new Statement(1000L,2,idArr.generateTypeVarString() + " = " +ids.getVarName()+".split(\",\");"));
		list.add(new Statement(2000L,2,"for ("+idString.generateTypeVarString() + " : "+idArr.getVarName()+"){"));
		if (this.domain.getDomainId().getRawType().isLong()){
			list.add(new Statement(3000L,3,id.generateTypeVarString()+" = Long.valueOf("+ idString.getVarName()+");"));
		} else if (this.domain.getDomainId().getRawType().isInt()){
			list.add(new Statement(3000L,3,id.generateTypeVarString()+" = Integer.valueOf("+ idString.getVarName()+");"));
		}
		list.add(new Statement(3000L,3,delete.generateStandardCallString()+";"));
		list.add(new Statement(4000L,2,"}"));
		method.setMethodStatementList(WriteableUtil.merge(list));
		return method;
	}

	@Override
	public Method generateDaoMethodDefinition() {
		Method method = new Method();
		method.setStandardName("deleteAll"+StringUtil.capFirst(this.domain.getPlural()));
		method.setReturnType(new Type("void"));
		method.setThrowException(true);
		method.addAdditionalImport("java.util.List");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addSignature(new Signature(1,"ids",new Type("String")));
		return method;
	}

	@Override
	public String generateDaoImplMethodString() throws Exception {
		return generateDaoImplMethod().generateMethodString();
	}

	@Override
	public String generateDaoMethodDefinitionString() {
		return generateDaoMethodDefinition().generateMethodDefinition();
	}

	@Override
	public String generateDaoImplMethodStringWithSerial() throws Exception{
		Method m = this.generateDaoImplMethod();
		m.setContent(m.generateMethodContentStringWithSerial());
		m.setMethodStatementList(null);
		return m.generateMethodString();
	}

	@Override
	public Method generateServiceMethodDefinition() {
		Method method = new Method();
		method.setStandardName("deleteAll"+StringUtil.capFirst(this.domain.getPlural()));
		method.setReturnType(new Type("void"));
		method.setThrowException(true);
		method.addAdditionalImport("java.util.List");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addSignature(new Signature(1,"ids",new Type("String")));
		return method;
	}

	@Override
	public String generateServiceMethodDefinitionString() {
		return generateServiceMethodDefinition().generateMethodDefinition();
	}

	@Override
	public Method generateControllerMethod() {
		Method method = new Method();
		method.setStandardName("deleteAll"+StringUtil.capFirst(this.domain.getPlural()));
		method.setReturnType(new Type("void"));
		method.setThrowException(true);
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addAdditionalImport(this.domain.getPackageToken()+".service."+this.domain.getStandardName()+"Service");
		
		List<Writeable> wlist = new ArrayList<Writeable>();
		Var service = new Var("service", new Type(this.domain.getStandardName()+"Service",this.domain.getPackageToken()));
		Var ids = new Var("ids", new Type("String"));
		wlist.add(NamedS2SHStatementGenerator.getActionCallServiceMethodByIds(1000L, 2,this.domain, service, generateServiceMethodDefinition(),ids));
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
		method.setStandardName("deleteAll"+StringUtil.capFirst(this.domain.getPlural()));
		method.setReturnType(new Type("void"));
		method.setThrowException(true);
		method.addAdditionalImport("java.util.List");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addAdditionalImport(this.domain.getPackageToken()+".dao."+this.domain.getStandardName()+"Dao");
		method.addAdditionalImport(this.domain.getPackageToken()+".daoimpl."+this.domain.getStandardName()+"DaoImpl");
		method.addAdditionalImport(this.domain.getPackageToken()+".service."+this.domain.getStandardName()+"Service");
		method.addSignature(new Signature(1,"ids",new Type("String")));
		method.addMetaData("Override");
		
		//Service method
		Method daomethod = generateDaoMethodDefinition();
				
		List<Writeable> list = new ArrayList<Writeable>();
		list.add(NamedS2SHStatementListGenerator.generateServiceImplVoid(1000L, 2, InterVarUtil.DB.dao, daomethod));
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

	public DeleteAll(){
		super();
	}
	public DeleteAll(Domain domain){
		super();
		this.domain = domain;
		this.setVerbName("DeleteAll"+StringUtil.capFirst(this.domain.getPlural()));
	}
	
	@Override
	public void setDomain(Domain domain){
		super.setDomain(domain);
		this.setVerbName("deleteAll"+StringUtil.capFirst(this.domain.getPlural()));
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
		method.setStandardName("deleteAll"+StringUtil.capFirst(this.domain.getPlural()));
		method.setReturnType(new Type("String"));
		method.setThrowException(true);
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addAdditionalImport(this.domain.getPackageToken()+".service."+this.domain.getStandardName()+"Service");
		
		List<Writeable> wlist = new ArrayList<Writeable>();
		Var service = new Var("service", new Type(this.domain.getStandardName()+"Service",this.domain.getPackageToken()));
		Var resultMap = new Var("result", new Type("TreeMap<String,Object>","java.util"));
		Var ids = new Var("ids",new Type("String"));
		wlist.add(NamedS2SHStatementGenerator.getJsonResultMap(1000L, 2, resultMap));
		wlist.add(NamedS2SHStatementGenerator.getFacadeCallServiceMethodByIds(2000L, 2,this.domain, service, generateServiceMethodDefinition(),ids));
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
