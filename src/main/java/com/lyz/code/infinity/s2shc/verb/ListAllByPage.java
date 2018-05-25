package com.lyz.code.infinity.s2shc.verb;

import java.util.ArrayList;
import java.util.List;

import javax.management.Query;

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

public class ListAllByPage extends Verb{
	protected CountAllPage countAllPage;
	

	@Override
	public Method generateDaoImplMethod(){
		try {
			Method method = new Method();
			method.setStandardName("listAll"+StringUtil.capFirst(this.domain.getPlural())+"ByPage");
			method.setReturnType(new Type("List",this.domain, this.domain.getPackageToken()));
			method.addAdditionalImport("java.util.List");
			method.addAdditionalImport("org.hibernate.Criteria");
			method.addAdditionalImport("org.springframework.orm.hibernate4.support.HibernateDaoSupport");
			method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
			method.addSignature(new Signature(1,"pagesize",new Type("int")));
			method.addSignature(new Signature(2,"pagenum",new Type("int")));
			method.setThrowException(true);
			method.addMetaData("Override");
			
			List<Writeable> list = new ArrayList<Writeable>();
			Var limitstart = new Var("limitstart",new Type("int"));
			Var limitcount = new Var("limitcount",new Type("int"));
			Var myquery = new Var("myquery",new Type("Query","org.hibernate"));
			Var vlist = new Var("list", new Type("List",this.domain,this.domain.getPackageToken()));
			list.add(new Statement(1000L,2,limitstart.generateTypeVarString() +" = (pagenum-1)*pagesize;" ));
			list.add(new Statement(2000L,2,limitcount.generateTypeVarString() +" = pagesize;" ));
			list.add(new Statement(3000L,2, "Session s = this.getHibernateTemplate().getSessionFactory().getCurrentSession();"));
			list.add(new Statement(4000L,2,"Criteria criteria = s.createCriteria("+this.domain.getStandardName()+".class);"));
			list.add(new Statement(5000L,2, "criteria.setFirstResult("+limitstart.getVarName()+");"));
			list.add(new Statement(6000L,2, "criteria.setMaxResults("+limitcount.getVarName()+");"));
			list.add(new Statement(7000L,2,"List<"+this.domain.getStandardName()+"> "+StringUtil.lowerFirst(this.domain.getPlural())+" = (List<"+this.domain.getStandardName()+">)criteria.list();"));
			list.add(new Statement(8000L,2,"return "+StringUtil.lowerFirst(this.domain.getPlural())+";"));
			
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
		method.addAdditionalImport("java.util.List");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addSignature(new Signature(1,"pagesize",new Type("int")));
		method.addSignature(new Signature(2,"pagenum",new Type("int")));
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
		method.setStandardName("listAll"+StringUtil.capFirst(this.domain.getPlural())+"ByPage");
		method.setReturnType(new Type("String"));
		method.setThrowException(true);
		method.addAdditionalImport("java.util.List");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addAdditionalImport(this.domain.getPackageToken()+".service."+this.domain.getStandardName()+"Service");
		//method.addAdditionalImport(this.domain.getPackageToken()+".serviceimpl."+this.domain.getStandardName()+"ServiceImpl");

		List<Writeable> wlist = new ArrayList<Writeable>();
		Var service = new Var("service", new Type(this.domain.getStandardName()+"Service",this.domain.getPackageToken()));
		Var vlist = new Var(StringUtil.lowerFirst(this.domain.getStandardName())+"List", new Type("List",this.domain,this.domain.getPackageToken()));
		Method serviceMethod = this.generateServiceMethodDefinition();		
		Var pagesize = new Var("pagesize",new Type("int"),"10");
		Var pagenum = new Var("pagenum",new Type("int"),"1");
		Var pagecount = new Var("pagecount",new Type("int"));
		Var last = new Var("last",new Type("boolean"));
		this.countAllPage.setDomain(domain);
		wlist.add(new Statement(1000L,2,"if ("+pagesize.getVarName()+" <= 0) "+pagesize.getVarName()+" = 10;"));
		wlist.add(new Statement(2000L,2, pagecount.generateTypeVarString() + " = " + service.getVarName() + "."+ StringUtil.lowerFirst(this.countAllPage.getVerbName()) + "("+pagesize.getVarName()+ ");"));
		wlist.add(new Statement(3000L,2, "if (" + pagenum.getVarName() +" > "+pagecount.getVarName() +") "+pagenum.getVarName()+" = "+ pagecount.getVarName() +";"));
		wlist.add(new Statement(3500L,2, "if ("+ last.getVarName() + ") "+pagenum.getVarName()+" = "+ pagecount.getVarName() +";"));
		wlist.add(new Statement(4000L,2, vlist.generateTypeVarString() + " = " +serviceMethod.generateStandardServiceImplCallString(InterVarUtil.DB.service.getVarName())+";"));
		wlist.add(new Statement(5000L,2,this.domain.getLowerFirstDomainName()+StringUtil.capFirst(InterVarUtil.Servlet.request.getVarName())+".put(\""+StringUtil.lowerFirst(this.domain.getStandardName())+"List\","+vlist.getVarName()+");"));		
		wlist.add(new Statement(6000L,2,this.domain.getLowerFirstDomainName()+StringUtil.capFirst(InterVarUtil.Servlet.request.getVarName())+".put(\""+pagesize.getVarName()+"\","+pagesize.getVarName()+");"));
		wlist.add(new Statement(7000L,2,this.domain.getLowerFirstDomainName()+StringUtil.capFirst(InterVarUtil.Servlet.request.getVarName())+".put(\""+pagenum.getVarName()+"\","+pagenum.getVarName()+");"));
		wlist.add(new Statement(8000L,2,this.domain.getLowerFirstDomainName()+StringUtil.capFirst(InterVarUtil.Servlet.request.getVarName())+".put(\""+pagecount.getVarName()+"\","+pagecount.getVarName()+");"));
		wlist.add(NamedS2SHStatementGenerator.getActionForward(9000L,2, serviceMethod.getStandardName()));
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
		method.addAdditionalImport("java.util.List");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addAdditionalImport(this.domain.getPackageToken()+".dao."+this.domain.getStandardName()+"Dao");
		method.addAdditionalImport(this.domain.getPackageToken()+".daoimpl."+this.domain.getStandardName()+"DaoImpl");
		method.addAdditionalImport(this.domain.getPackageToken()+".service."+this.domain.getStandardName()+"Service");
		method.setThrowException(true);
		method.addMetaData("Override");
		
		Method daomethod = this.generateDaoMethodDefinition();
		
		List<Writeable> list = new ArrayList<Writeable>();
		list.add(new Statement(1000L,2,"return " + daomethod.generateStandardServiceImplCallString(InterVarUtil.DB.dao.getVarName())+";"));
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
		this.countAllPage = new CountAllPage(this.domain);
		if (this.noControllerVerbs.size() == 0)
			this.noControllerVerbs.add(this.countAllPage);
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
		method.setStandardName("listAll"+StringUtil.capFirst(this.domain.getPlural())+"ByPage");
		method.setReturnType(new Type("String"));
		method.setThrowException(true);
		method.addAdditionalImport("java.util.List");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addAdditionalImport(this.domain.getPackageToken()+".service."+this.domain.getStandardName()+"Service");
		//method.addAdditionalImport(this.domain.getPackageToken()+".serviceimpl."+this.domain.getStandardName()+"ServiceImpl");

		List<Writeable> wlist = new ArrayList<Writeable>();
		Var service = new Var("service", new Type(this.domain.getStandardName()+"Service",this.domain.getPackageToken()));
		Var vlist = new Var(StringUtil.lowerFirst(this.domain.getStandardName())+"List", new Type("List",this.domain,this.domain.getPackageToken()));
		Method serviceMethod = this.generateServiceMethodDefinition();		
		Var pagesize = new Var("pagesize",new Type("int"),"10");
		Var pagenum = new Var("pagenum",new Type("int"),"1");
		Var pagecount = new Var("pagecount",new Type("int"));
		Var resultMap = new Var("result", new Type("TreeMap<String,Object>","java.util"));
		Var last = new Var("last",new Type("boolean"));		
		wlist.add(new Statement(1000L,2,"if ("+pagesize.getVarName()+" <= 0) "+pagesize.getVarName()+" = 10;"));
		wlist.add(NamedS2SHStatementGenerator.getJsonResultMap(2000L, 2, resultMap));
		wlist.add(new Statement(3000L,2,pagecount.generateTypeVarString() + " = " + service.getVarName()+"."+this.countAllPage.generateServiceMethodDefinition().getStandardCallString()+";"));
		wlist.add(new Statement(4000L,2,"if ("+pagenum.getVarName() +" <= "+pagenum.getValue() +") " +pagenum.getVarName() +" = " + pagenum.getValue() +";"));
		wlist.add(new Statement(5000L,2,"if ("+pagenum.getVarName() +" >= "+pagecount.getVarName() +") " +pagenum.getVarName() +" = " + pagecount.getVarName() +";"));
		wlist.add(new Statement(5500L,2, "if ("+ last.getVarName() + ") "+pagenum.getVarName()+" = "+ pagecount.getVarName() +";"));
		wlist.add(new Statement(6000L,2, vlist.generateTypeVarString() + " = " +serviceMethod.generateStandardServiceImplCallString(InterVarUtil.DB.service.getVarName())+";"));
		wlist.add(NamedS2SHStatementListGenerator.getPutJsonResultMapWithSuccessAndDomainListPaging(7000L, 2, resultMap,vlist,pagesize,pagenum,pagecount));
		wlist.add(NamedS2SHStatementGenerator.getEncodeMapToJsonResultMap(8000L, 2, resultMap));
		wlist.add(NamedS2SHStatementGenerator.getJumpToActionSuccess(9000L, 2));
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
