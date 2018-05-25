package com.lyz.code.infinity.s2sh.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.lyz.code.infinity.core.Verb;
import com.lyz.code.infinity.domain.Dao;
import com.lyz.code.infinity.domain.DaoImpl;
import com.lyz.code.infinity.domain.Method;
import com.lyz.code.infinity.domain.Prism;
import com.lyz.code.infinity.domain.Service;
import com.lyz.code.infinity.domain.ServiceImpl;
import com.lyz.code.infinity.domain.Type;
import com.lyz.code.infinity.domain.ValidateInfo;
import com.lyz.code.infinity.exception.ValidateException;
import com.lyz.code.infinity.generator.JspTemplate;
import com.lyz.code.infinity.generator.JspTemplateFactory;
import com.lyz.code.infinity.generator.NamedUtilMethodGenerator;
import com.lyz.code.infinity.limitedverb.DaoOnlyVerb;
import com.lyz.code.infinity.limitedverb.NoControllerVerb;
import com.lyz.code.infinity.s2sh.verb.Add;
import com.lyz.code.infinity.s2sh.verb.CountAllPage;
import com.lyz.code.infinity.s2sh.verb.Delete;
import com.lyz.code.infinity.s2sh.verb.DeleteAll;
import com.lyz.code.infinity.s2sh.verb.FindById;
import com.lyz.code.infinity.s2sh.verb.FindByName;
import com.lyz.code.infinity.s2sh.verb.ListActive;
import com.lyz.code.infinity.s2sh.verb.ListAll;
import com.lyz.code.infinity.s2sh.verb.ListAllByPage;
import com.lyz.code.infinity.s2sh.verb.SearchByName;
import com.lyz.code.infinity.s2sh.verb.SoftDelete;
import com.lyz.code.infinity.s2sh.verb.SoftDeleteAll;
import com.lyz.code.infinity.s2sh.verb.Update;
import com.lyz.code.infinity.utils.StringUtil;

public class S2SHPrism extends Prism {
	protected Action action;

	protected Struts2Facade facade;
	
	public Struts2Facade getFacade() {
		return facade;
	}

	public void setFacade(Struts2Facade facade) {
		this.facade = facade;
	}
	
	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}
	
	private void writeToFile(File f, String content){
		try (FileWriter fw = new FileWriter(f)){     

	        fw.write(content,0,content.length());    
	        fw.flush();
	        fw.close();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void generatePrismFromDomain() throws Exception{
		if (this.domain != null){
			if (this.getPackageToken() != null){
				this.domain.setPackageToken(packageToken);
			}
			
			this.domain = S2SHDomainDecorater.generateDecroatedDomain(this.domain);
				
			this.dao = new Dao();
			this.dao.setDomain(this.domain);
			this.dao.setPackageToken(this.domain.getPackageToken());
			this.daoimpl = new DaoImpl();
			this.daoimpl.setDomain(this.domain);
			this.daoimpl.setPackageToken(this.domain.getPackageToken());
			this.daoimpl.setExtendedType(new Type("HibernateDaoSupport","org.springframework.orm.hibernate4.support"));
			this.daoimpl.setDao(this.dao);
			
			this.service = new Service();
			this.service.setDomain(this.domain);
			this.service.setPackageToken(this.domain.getPackageToken());
			this.serviceimpl = new ServiceImpl(this.domain);
			this.serviceimpl.setPackageToken(this.domain.getPackageToken());
			this.serviceimpl.getDao().addAnnotation("Autowired");
			Method daoSetter = NamedUtilMethodGenerator.generateSetter("dao", new Type(this.domain.getCapFirstDomainName()+"Dao"));
			this.serviceimpl.addMethod(daoSetter);
			this.serviceimpl.addClassImports("org.springframework.beans.factory.annotation.Autowired");
			this.serviceimpl.setDomain(this.domain);
			this.serviceimpl.setService(this.service);
			
			Verb listAll = new ListAll(this.domain);
			Verb update = new Update(this.domain);
			Verb delete = new Delete(this.domain);
			Verb add = new Add(this.domain);
			Verb softdelete = new SoftDelete(this.domain);
			Verb findbyid = new FindById(this.domain);
			Verb findbyname = new FindByName(this.domain);
			Verb searchbyname = new SearchByName(this.domain);
			Verb listactive = new ListActive(this.domain);
			Verb listAllByPage = new ListAllByPage(this.domain);
			Verb deleteAll = new DeleteAll(this.domain);
			Verb softDeleteAll = new SoftDeleteAll(this.domain);
			
			CountAllPage countAllPage = new CountAllPage(this.domain);
			
			this.addVerb(listAll);
			this.addVerb(update);
			this.addVerb(delete);
			this.addVerb(add);
			this.addVerb(softdelete);
			this.addVerb(findbyid);
			this.addVerb(findbyname);
			this.addVerb(searchbyname);
			this.addVerb(listactive);
			this.addVerb(listAllByPage);
			this.addVerb(deleteAll);
			this.addVerb(softDeleteAll);
			
			this.noControllerVerbs.add(countAllPage);
			this.action = new Action(this.verbs,this.domain);
			this.action.setPackageToken(this.packageToken);
			this.facade = new Struts2Facade(this.verbs,this.domain);
			this.facade.setPackageToken(this.packageToken);
			
			
			for (Verb v: this.verbs){
				v.setDomain(this.domain);
				service.addMethod(v.generateServiceMethodDefinition());
				serviceimpl.addMethod(v.generateServiceImplMethod());
				dao.addMethod(v.generateDaoMethodDefinition());
				daoimpl.addMethod(v.generateDaoImplMethod());
			}
			
			for (NoControllerVerb nVerb: this.noControllerVerbs){
				nVerb.setDomain(this.domain);
				service.addMethod(nVerb.generateServiceMethodDefinition());
				serviceimpl.addMethod(nVerb.generateServiceImplMethod());
				dao.addMethod(nVerb.generateDaoMethodDefinition());
				daoimpl.addMethod(nVerb.generateDaoImplMethod());				
			}
			
			for (DaoOnlyVerb oVerb: this.daoOnlyVerbs){
				oVerb.setDomain(this.domain);
				dao.addMethod(oVerb.generateDaoMethodDefinition());
				daoimpl.addMethod(oVerb.generateDaoImplMethod());				
			}
			
//			DBDefinitionGenerator dbg = DBDefinitionFactory.getInstance("mysql");
//			dbg.addDomain(this.domain);
//			dbg.setDbName(this.domain.getStandardName());
//			this.setDbDefinitionGenerator(dbg);	
			
			JspTemplate jt = S2SHJspTemplateFactory.getInstance("pagingGrid",domain);
			//jt.setDomain(this.domain);
			jt.setStandardName(this.domain.getStandardName().toLowerCase());
			this.addJspTemplate(jt);
			
			JspTemplate jsonjt = JspTemplateFactory.getInstance("s2shJsonPagingGrid",domain);
			jsonjt.setDomain(this.domain);
			jsonjt.setStandardName(this.domain.getStandardName().toLowerCase());
			this.addJsonJspTemplate(jsonjt);
		}
	}
	
	public void generateS2SHCPrismFromDomain() throws Exception{
		if (this.domain != null){
			if (this.getPackageToken() != null){
				this.domain.setPackageToken(packageToken);
			}
			
			this.domain = S2SHDomainDecorater.generateDecroatedDomain(this.domain);
				
			this.dao = new Dao();
			this.dao.setDomain(this.domain);
			this.dao.setPackageToken(this.domain.getPackageToken());
			this.daoimpl = new DaoImpl();
			this.daoimpl.setDomain(this.domain);
			this.daoimpl.setPackageToken(this.domain.getPackageToken());
			this.daoimpl.setExtendedType(new Type("HibernateDaoSupport","org.springframework.orm.hibernate4.support"));
			this.daoimpl.setDao(this.dao);
			
			this.service = new Service();
			this.service.setDomain(this.domain);
			this.service.setPackageToken(this.domain.getPackageToken());
			this.serviceimpl = new ServiceImpl(this.domain);
			this.serviceimpl.setPackageToken(this.domain.getPackageToken());
			this.serviceimpl.getDao().addAnnotation("Autowired");
			Method daoSetter = NamedUtilMethodGenerator.generateSetter("dao", new Type(this.domain.getCapFirstDomainName()+"Dao"));
			this.serviceimpl.addMethod(daoSetter);
			this.serviceimpl.addClassImports("org.springframework.beans.factory.annotation.Autowired");
			this.serviceimpl.setDomain(this.domain);
			this.serviceimpl.setService(this.service);
			
			Verb listAll = new com.lyz.code.infinity.s2shc.verb.ListAll(this.domain);
			Verb update = new com.lyz.code.infinity.s2shc.verb.Update(this.domain);
			Verb delete = new com.lyz.code.infinity.s2shc.verb.Delete(this.domain);
			Verb add = new com.lyz.code.infinity.s2shc.verb.Add(this.domain);
			Verb softdelete = new com.lyz.code.infinity.s2shc.verb.SoftDelete(this.domain);
			Verb findbyid = new com.lyz.code.infinity.s2shc.verb.FindById(this.domain);
			Verb findbyname = new com.lyz.code.infinity.s2shc.verb.FindByName(this.domain);
			Verb searchbyname = new com.lyz.code.infinity.s2shc.verb.SearchByName(this.domain);
			Verb listactive = new com.lyz.code.infinity.s2shc.verb.ListActive(this.domain);
			Verb listAllByPage = new com.lyz.code.infinity.s2shc.verb.ListAllByPage(this.domain);
			Verb deleteAll = new com.lyz.code.infinity.s2shc.verb.DeleteAll(this.domain);
			Verb softDeleteAll = new com.lyz.code.infinity.s2shc.verb.SoftDeleteAll(this.domain);
			
			com.lyz.code.infinity.s2shc.verb.CountAllPage countAllPage = new com.lyz.code.infinity.s2shc.verb.CountAllPage(this.domain);
			
			this.addVerb(listAll);
			this.addVerb(update);
			this.addVerb(delete);
			this.addVerb(add);
			this.addVerb(softdelete);
			this.addVerb(findbyid);
			this.addVerb(findbyname);
			this.addVerb(searchbyname);
			this.addVerb(listactive);
			this.addVerb(listAllByPage);
			this.addVerb(deleteAll);
			this.addVerb(softDeleteAll);
			
			this.noControllerVerbs.add(countAllPage);
			this.action = new Action(this.verbs,this.domain);
			this.action.setPackageToken(this.packageToken);
			this.facade = new Struts2Facade(this.verbs,this.domain);
			this.facade.setPackageToken(this.packageToken);
			
			
			for (Verb v: this.verbs){
				v.setDomain(this.domain);
				service.addMethod(v.generateServiceMethodDefinition());
				serviceimpl.addMethod(v.generateServiceImplMethod());
				dao.addMethod(v.generateDaoMethodDefinition());
				daoimpl.addMethod(v.generateDaoImplMethod());
			}
			
			for (NoControllerVerb nVerb: this.noControllerVerbs){
				nVerb.setDomain(this.domain);
				service.addMethod(nVerb.generateServiceMethodDefinition());
				serviceimpl.addMethod(nVerb.generateServiceImplMethod());
				dao.addMethod(nVerb.generateDaoMethodDefinition());
				daoimpl.addMethod(nVerb.generateDaoImplMethod());				
			}
			
			for (DaoOnlyVerb oVerb: this.daoOnlyVerbs){
				oVerb.setDomain(this.domain);
				dao.addMethod(oVerb.generateDaoMethodDefinition());
				daoimpl.addMethod(oVerb.generateDaoImplMethod());				
			}
					
			JspTemplate jt = S2SHJspTemplateFactory.getInstance("pagingGrid",domain);
			//jt.setDomain(this.domain);
			jt.setStandardName(this.domain.getStandardName().toLowerCase());
			this.addJspTemplate(jt);
			
			JspTemplate jsonjt = JspTemplateFactory.getInstance("s2shJsonPagingGrid",domain);
			jsonjt.setDomain(this.domain);
			jsonjt.setStandardName(this.domain.getStandardName().toLowerCase());
			this.addJsonJspTemplate(jsonjt);
		}
	}
	
	@Override
	public void generatePrismFiles() throws ValidateException{
		ValidateInfo info = this.validate();
		if (info.success() == false) {
			ValidateException e = new ValidateException(info);
			throw e;
		}
		try {
			String srcfolderPath = folderPath;
			if (this.packageToken != null && !"".equals(this.packageToken))
				srcfolderPath = folderPath + "src/" + packagetokenToFolder(this.packageToken);
			File f= new File(srcfolderPath+"domain/"+StringUtil.capFirst(this.getDomain().getStandardName())+".java");
			if (!f.getParentFile().exists()) {
				f.getParentFile().mkdirs();
			}
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			writeToFile(f, this.getDomain().generateClassString());
	        
	        if (this.getDao()!=null){
	        	f = new File(srcfolderPath+"dao/"+StringUtil.capFirst(this.getDomain().getStandardName())+"Dao.java");
	    		if (!f.getParentFile().exists()) {
	    			f.getParentFile().mkdirs();
	    		}
	    		try {
	    			f.createNewFile();
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
	    		writeToFile(f, this.getDao().generateDaoString());
	        }
	        
	        if (this.getDaoImpl() != null) {
	        	f = new File(srcfolderPath+"daoimpl/"+StringUtil.capFirst(this.getDomain().getStandardName())+"DaoImpl.java");
	    		if (!f.getParentFile().exists()) {
	    			f.getParentFile().mkdirs();
	    		}
	    		try {
	    			f.createNewFile();
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
	    		writeToFile(f, this.getDaoImpl().generateDaoImplString());
	        }
	        
	        if (this.getService() != null) {
	        	f = new File(srcfolderPath+"service/"+StringUtil.capFirst(this.getDomain().getStandardName())+"Service.java");
	    		if (!f.getParentFile().exists()) {
	    			f.getParentFile().mkdirs();
	    		}
	    		try {
	    			f.createNewFile();
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
	    		writeToFile(f, this.getService().generateServiceString());
	        }
	        
	        if (this.getServiceImpl() != null) {
	        	f = new File(srcfolderPath+"serviceimpl/"+StringUtil.capFirst(this.getDomain().getStandardName())+"ServiceImpl.java");
	        	if (!f.getParentFile().exists()) {
	    			f.getParentFile().mkdirs();
	    		}
	    		try {
	    			f.createNewFile();
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
	    		writeToFile(f, this.getServiceImpl().generateServiceImplString());
	        }	        
	        
	        if (this.action != null) {
	        	f = new File(srcfolderPath+"action/"+StringUtil.capFirst(this.domain.getCapFirstDomainName())+"Action.java");
	        	if (!f.getParentFile().exists()) {
	    			f.getParentFile().mkdirs();
	    		}
	    		try {
	    			f.createNewFile();
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
	    		writeToFile(f, this.action.generateActionString());
	        }
	        
	        /*if (dbDefinitionGenerator != null) {
	        	 fw = new FileWriter(folderPath+StringUtil.capFirst(this.getDomain().getStandardName().toLowerCase())+".sql");    
	        	 dbDefinitionGenerator.addDomain(domain); 
			     s = dbDefinitionGenerator.generateDBSql();
		        fw.write(s,0,s.length());    
		        fw.flush();
	        }*/
	        
	        if (this.facade != null) {
	        	f = new File(srcfolderPath+"facade/"+StringUtil.capFirst(this.domain.getCapFirstDomainName())+"Facade.java");
	        	if (!f.getParentFile().exists()) {
	    			f.getParentFile().mkdirs();
	    		}
	    		try {
	    			f.createNewFile();
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
	    		writeToFile(f, this.facade.generateFacadeString());
	        }
	        
	        for (JspTemplate tp : this.jsptemplates){
	        	f = new File(folderPath+"WebContent/jsp/"+this.domain.getPlural().toLowerCase()+".jsp");
	    		if (!f.getParentFile().exists()) {
	    			f.getParentFile().mkdirs();
	    		}
	    		try {
	    			f.createNewFile();
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
	    		writeToFile(f, tp.generateJspString());
	        }       

        for (JspTemplate jsontp : this.jsonjsptemplates){
        	f = new File(folderPath+"WebContent/jsonjsp/"+this.domain.getPlural().toLowerCase()+".jsp");
    		if (!f.getParentFile().exists()) {
    			f.getParentFile().mkdirs();
    		}
    		try {
    			f.createNewFile();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		try (FileWriter fw = new FileWriter(f)){        
		        String s = jsontp.generateJspString();    
		        fw.write(s,0,s.length());    
		        fw.flush();
		        fw.close();
    		}
        }
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
