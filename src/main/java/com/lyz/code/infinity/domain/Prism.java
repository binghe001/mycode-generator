package com.lyz.code.infinity.domain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.lyz.code.infinity.core.Verb;
import com.lyz.code.infinity.exception.ValidateException;
import com.lyz.code.infinity.generator.DBDefinitionFactory;
import com.lyz.code.infinity.generator.DBDefinitionGenerator;
import com.lyz.code.infinity.generator.JspTemplate;
import com.lyz.code.infinity.generator.JspTemplateFactory;
import com.lyz.code.infinity.limitedverb.CountPage;
import com.lyz.code.infinity.limitedverb.DaoOnlyVerb;
import com.lyz.code.infinity.limitedverb.NoControllerVerb;
import com.lyz.code.infinity.utils.StringUtil;
import com.lyz.code.infinity.verb.Add;
import com.lyz.code.infinity.verb.Delete;
import com.lyz.code.infinity.verb.DeleteAll;
import com.lyz.code.infinity.verb.FindById;
import com.lyz.code.infinity.verb.FindByName;
import com.lyz.code.infinity.verb.ListActive;
import com.lyz.code.infinity.verb.ListAll;
import com.lyz.code.infinity.verb.ListAllByPage;
import com.lyz.code.infinity.verb.SearchByName;
import com.lyz.code.infinity.verb.SoftDelete;
import com.lyz.code.infinity.verb.SoftDeleteAll;
import com.lyz.code.infinity.verb.Update;

public class Prism implements Comparable{
	protected long prismId;
	protected String standardName;
	protected long namingId;
	protected Naming naming;
	protected long domainClassId;
	protected Domain domain;
	protected long daoimplClassId;
	protected long serviceimplClassId;
	protected long daoId;
	protected Dao dao;
	protected DaoImpl daoimpl;
	protected long serviceId;
	protected Service service;
	protected ServiceImpl serviceimpl;
	protected String prismComment;
	protected List<Class> classes = new ArrayList<Class>(); 
	protected List<Util> utils = new ArrayList<Util>();
	protected List<Controller> controllers = new ArrayList<Controller>();
	protected List<Facade> facades = new ArrayList<Facade>();
	protected String folderPath = "D:/JerryWork/Infinity/testFiles/";
	protected DBDefinitionGenerator dbDefinitionGenerator; 
	protected List<JspTemplate> jsptemplates = new ArrayList<JspTemplate>();
	protected List<JspTemplate> jsonjsptemplates = new ArrayList<JspTemplate>();
	protected String packageToken;
	protected TestSuite prismTestSuite;
	protected TestCase daoImplTestCase;
	protected TestCase serviceImplTestCase;
	protected List<Verb> verbs = new ArrayList<Verb>();
	protected List<NoControllerVerb> noControllerVerbs = new ArrayList<NoControllerVerb>();
	protected List<DaoOnlyVerb> daoOnlyVerbs = new ArrayList<DaoOnlyVerb>();
	
	public List<Facade> getFacades() {
		return facades;
	}
	public void setFacades(List<Facade> facades) {
		this.facades = facades;
	}
	
	public List<NoControllerVerb> getNoControllerVerbs() {
		return noControllerVerbs;
	}
	public void setNoControllerVerbs(List<NoControllerVerb> noControllerVerbs) {
		this.noControllerVerbs = noControllerVerbs;
	}
	public List<DaoOnlyVerb> getDaoOnlyVerbs() {
		return daoOnlyVerbs;
	}
	public void setDaoOnlyVerbs(List<DaoOnlyVerb> daoOnlyVerbs) {
		this.daoOnlyVerbs = daoOnlyVerbs;
	}
	
	public List<Util> getUtils() {
		return utils;
	}
	public void setUtils(List<Util> utils) {
		this.utils = utils;
	}
	public void addUtil(Util util){
		this.utils.add(util);
	}
	public long getPrismId() {
		return prismId;
	}
	public List<Class> getClasses() {
		return classes;
	}
	public void setClasses(List<Class> classes) {
		this.classes = classes;
	}
	public void addClass(Class clazz){
		this.classes.add(clazz);
	}
	public void setPrismId(long prismId) {
		this.prismId = prismId;
	}
	public String getStandardName() {
		return standardName;
	}
	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}
	public long getNamingId() {
		return namingId;
	}
	public void setNamingId(long namingId) {
		this.namingId = namingId;
	}
	public Naming getNaming() {
		return naming;
	}
	public void setNaming(Naming naming) {
		this.naming = naming;
	}
	public long getDomainClassId() {
		return domainClassId;
	}
	public void setDomainClassId(long domainClassId) {
		this.domainClassId = domainClassId;
	}
	public Domain getDomain() {
		return domain;
	}
	public void setDomain(Domain domain) {
		this.domain = domain;
	}
	public long getDaoimplClassId() {
		return daoimplClassId;
	}
	public void setDaoimplClassId(long daoimplClassId) {
		this.daoimplClassId = daoimplClassId;
	}
	public DaoImpl getDaoimpl() {
		return this.daoimpl;
	}
	public void setDaoimpl(DaoImpl daoimpl) {
		this.daoimpl = daoimpl;
	}
	public long getServiceImplClassId() {
		return serviceimplClassId;
	}
	public void setServiceImplClassId(long serviceimplClassId) {
		this.serviceimplClassId = serviceimplClassId;
	}
	public ServiceImpl getServiceImpl() {
		return serviceimpl;
	}
	public void setServiceImpl(ServiceImpl serviceimpl) {
		this.serviceimpl = serviceimpl;
	}
	public long getDaoId() {
		return daoId;
	}
	public void setDaoId(long daoId) {
		this.daoId = daoId;
	}
	public Dao getDao() {
		return dao;
	}
	public void setDao(Dao dao) {
		this.dao = dao;
	}
	public long getServiceId() {
		return serviceId;
	}
	public void setServiceId(long serviceId) {
		this.serviceId = serviceId;
	}
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	public String getPrismComment() {
		return prismComment;
	}
	public void setPrismComment(String prismComment) {
		this.prismComment = prismComment;
	}
	
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
			try (FileWriter fw = new FileWriter(f)){    
		        String s = this.getDomain().generateClassString();    
		        fw.write(s,0,s.length());    
		        fw.flush();
			}
	        
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
	    		try (FileWriter fw = new FileWriter(f)){      
			        String s = this.getDao().generateDaoString();    
			        fw.write(s,0,s.length());    
			        fw.flush(); 
			        fw.close();
	    		}
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
	    		try (FileWriter fw = new FileWriter(f)){        
			        String s = this.getDaoImpl().generateDaoImplString();    
			        fw.write(s,0,s.length());    
			        fw.flush(); 
			        fw.close();
	    		}
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
	    		try (FileWriter fw = new FileWriter(f)){       
			        String s = this.getService().generateServiceString();    
			        fw.write(s,0,s.length());    
			        fw.flush();
			        fw.close();
	    		}
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
	    		try (FileWriter fw = new FileWriter(f)){    
		        	String s = this.getServiceImpl().generateServiceImplString();    
		        	fw.write(s,0,s.length());    
		        	fw.flush();
		        	fw.close();
	    		}
	        }
	        
	        for (Controller c : this.controllers){
	        	f = new File(srcfolderPath+"controller/"+StringUtil.capFirst(c.getStandardName())+".java");
	    		if (!f.getParentFile().exists()) {
	    			f.getParentFile().mkdirs();
	    		}
	    		try {
	    			f.createNewFile();
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
	    		try (FileWriter fw = new FileWriter(f)){      
			        String s = c.generateControllerString();    
			        fw.write(s,0,s.length());    
			        fw.flush();
			        fw.close();
	    		}
	        }
	        
	        for (Facade facade : this.facades){
	        	f = new File(srcfolderPath+"facade/"+StringUtil.capFirst(facade.getStandardName())+".java");
	    		if (!f.getParentFile().exists()) {
	    			f.getParentFile().mkdirs();
	    		}
	    		try {
	    			f.createNewFile();
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
	    		try (FileWriter fw = new FileWriter(f)){      
			        String s = facade.generateFacadeString();    
			        fw.write(s,0,s.length());    
			        fw.flush();
			        fw.close();
	    		}
	        }
	        
	        /*if (dbDefinitionGenerator != null) {
	        	 fw = new FileWriter(folderPath+StringUtil.capFirst(this.getDomain().getStandardName().toLowerCase())+".sql");    
	        	 dbDefinitionGenerator.addDomain(domain); 
			     s = dbDefinitionGenerator.generateDBSql();
		        fw.write(s,0,s.length());    
		        fw.flush();
	        }*/
	        
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
	    		try (FileWriter fw = new FileWriter(f)){        
			        String s = tp.generateJspString();    
			        fw.write(s,0,s.length());    
			        fw.flush();
			        fw.close();
	    		}
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
	
	public String getFolderPath() {
		return folderPath;
	}
	public void setFolderPath(String folderPath) {
		this.folderPath = folderPath;
	}
	public DaoImpl getDaoImpl() {
		return daoimpl;
	}
	public void setDaoImpl(DaoImpl daoImpl) {
		this.daoimpl = daoImpl;
	}
	
	public void addController(Controller c){
		this.controllers.add(c);
	}
	public long getServiceimplClassId() {
		return serviceimplClassId;
	}
	public void setServiceimplClassId(long serviceimplClassId) {
		this.serviceimplClassId = serviceimplClassId;
	}
	public ServiceImpl getServiceimpl() {
		return serviceimpl;
	}
	public void setServiceimpl(ServiceImpl serviceimpl) {
		this.serviceimpl = serviceimpl;
	}
	public List<Controller> getControllers() {
		return controllers;
	}
	public void setControllers(List<Controller> controllers) {
		this.controllers = controllers;
	}
	public DBDefinitionGenerator getDbDefinitionGenerator() {
		return dbDefinitionGenerator;
	}
	public void setDbDefinitionGenerator(DBDefinitionGenerator dbDefinitionGenerator) {
		this.dbDefinitionGenerator = dbDefinitionGenerator;
	}
	public List<JspTemplate> getJsptemplates() {
		return jsptemplates;
	}
	public void setJsptemplates(List<JspTemplate> jsptemplates) {
		this.jsptemplates = jsptemplates;
	}
	
	public void addJspTemplate(JspTemplate template){
		this.jsptemplates.add(template);
	}
	
	public void addJsonJspTemplate(JspTemplate template){
		this.jsonjsptemplates.add(template);
	}
	
	public String getPackageToken() {
		return packageToken;
	}
	public void setPackageToken(String packagetoken) {
		if (packagetoken != null){
			this.packageToken = packagetoken;
		}
	}
	
	public void generatePrismFromDomain() throws Exception{
		if (this.domain != null){
			if (this.getPackageToken() != null){
				this.domain.setPackageToken(packageToken);
			}
				
			this.dao = new Dao();
			this.dao.setDomain(this.domain);
			this.dao.setPackageToken(this.packageToken);
			this.daoimpl = new DaoImpl();
			this.daoimpl.setPackageToken(this.packageToken);
			this.daoimpl.setDomain(this.domain);
			this.daoimpl.setDao(this.dao);
			
			this.service = new Service();
			this.service.setDomain(this.domain);
			this.service.setPackageToken(this.packageToken);
			this.serviceimpl = new ServiceImpl(this.domain);
			this.serviceimpl.setDomain(this.domain);
			this.serviceimpl.setPackageToken(this.packageToken);
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
			
			CountPage countPage = new CountPage(this.domain);
			
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
			
			this.noControllerVerbs.add(countPage);
			
			int serial = 100;
			for (Verb v: this.verbs){
				v.setDomain(domain);
				Controller c = new Controller(v,domain);
				c.setPackageToken(this.getPackageToken());
				this.controllers.add(c);
				Facade facade = new Facade(v,domain);
				facade.setPackageToken(this.getPackageToken());
				this.facades.add(facade);
				service.addMethod(v.generateServiceMethodDefinition());
				serviceimpl.addMethod(v.generateServiceImplMethod(),serial);
				dao.addMethod(v.generateDaoMethodDefinition());
				daoimpl.addMethod(v.generateDaoImplMethod(),serial);
				serial += 100;
			}
			
			for (NoControllerVerb nVerb: this.noControllerVerbs){
				nVerb.setDomain(domain);
				service.addMethod(nVerb.generateServiceMethodDefinition());
				serviceimpl.addMethod(nVerb.generateServiceImplMethod());
				dao.addMethod(nVerb.generateDaoMethodDefinition());
				daoimpl.addMethod(nVerb.generateDaoImplMethod());				
			}
			
			for (DaoOnlyVerb oVerb: this.daoOnlyVerbs){
				oVerb.setDomain(domain);
				dao.addMethod(oVerb.generateDaoMethodDefinition());
				daoimpl.addMethod(oVerb.generateDaoImplMethod());				
			}
			
			DBDefinitionGenerator dbg = DBDefinitionFactory.getInstance("mysql");
			dbg.addDomain(this.domain);
			dbg.setDbName(this.domain.standardName);
			this.setDbDefinitionGenerator(dbg);	
			
			JspTemplate jt = JspTemplateFactory.getInstance("pagingGrid",domain);
			jt.setDomain(this.domain);
			jt.setStandardName(this.domain.getStandardName().toLowerCase());
			this.addJspTemplate(jt);
			
			JspTemplate jsonjt = JspTemplateFactory.getInstance("jsonPagingGrid",domain);
			jsonjt.setDomain(this.domain);
			jsonjt.setStandardName(this.domain.getStandardName().toLowerCase());
			this.addJsonJspTemplate(jsonjt);
		}
	}

	public static String packagetokenToFolder(String packageToken){
		String folder = packageToken.replace('.','/');
		folder += "/";
		return folder;
	}
	
	public static String folderToPackageToken(String folder){
		String packagetoken = folder.replace('/', '.');
		if (packagetoken.charAt(packagetoken.length()-1) == '.')
			packagetoken = packagetoken.substring(0, packagetoken.length() -1);
		return packagetoken;
	}

	public TestSuite getPrismTestSuite() {
		return prismTestSuite;
	}
	public void setPrismTestSuite(TestSuite prismTestSuite) {
		this.prismTestSuite = prismTestSuite;
	}
	public TestCase getDaoImplTestCase() {
		return daoImplTestCase;
	}
	public void setDaoImplTestCase(TestCase daoImplTestCase) {
		this.daoImplTestCase = daoImplTestCase;
	}
	public TestCase getServiceImplTestCase() {
		return serviceImplTestCase;
	}
	public void setServiceImplTestCase(TestCase serviceImplTestCase) {
		this.serviceImplTestCase = serviceImplTestCase;
	}
	
	public ValidateInfo validate(){
		List <ValidateInfo> vl = new ArrayList<ValidateInfo>();
		ValidateInfo info = this.getDomain().validate();
		vl.add(info);
		for (Controller c: this.controllers){
			ValidateInfo info2 = c.validate();
			vl.add(info2);
		}
		return ValidateInfo.mergeValidateInfo(vl);
	}
	
	public void expandPackageToken(){
		if (this.packageToken!=null && !"".equals(this.packageToken)){
			if (this.domain != null) this.domain.setPackageToken(this.packageToken);
			if (this.dao != null) this.dao.setPackageToken(this.packageToken);
			if (this.daoimpl != null) this.daoimpl.setPackageToken(this.packageToken);
			if (this.service != null) this.service.setPackageToken(this.packageToken);
			if (this.serviceimpl != null) this.serviceimpl.setPackageToken(this.packageToken);

			for (Class c : this.classes) {
				c.setPackageToken(this.packageToken);
			}
			for (Controller ctl: this.controllers){
				ctl.setPackageToken(this.packageToken);
			}
			
			if (this.prismTestSuite != null) this.prismTestSuite.setPackageToken(this.packageToken);
			if (this.daoImplTestCase != null) this.daoImplTestCase.setPackageToken(this.packageToken);
			if (this.serviceImplTestCase != null) this.serviceImplTestCase.setPackageToken(this.packageToken);
		}
	}
	@Override
	public int compareTo(Object o) {
		String myName = this.getStandardName();
		String otherName = ((Prism)o).getStandardName();
		return myName.compareTo(otherName);
	}

	
	@Override
	public boolean equals(Object o){
		return (this.compareTo((Prism)o)==0);
	}
	public List<Verb> getVerbs() {
		return verbs;
	}
	public void setVerbs(List<Verb> verbs) {
		this.verbs = verbs;
	}
	
	public void addVerb(Verb verb){
		this.verbs.add(verb);
	}
	public List<JspTemplate> getJsonjsptemplates() {
		return jsonjsptemplates;
	}
	public void setJsonjsptemplates(List<JspTemplate> jsonjsptemplates) {
		this.jsonjsptemplates = jsonjsptemplates;
	}

}
