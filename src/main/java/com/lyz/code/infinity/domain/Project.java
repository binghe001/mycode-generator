package com.lyz.code.infinity.domain;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.lyz.code.infinity.core.DotClassPathXml;
import com.lyz.code.infinity.core.DotProjectXml;
import com.lyz.code.infinity.core.Log4jProperties;
import com.lyz.code.infinity.core.SettingEclipseJdtPrefs;
import com.lyz.code.infinity.core.SettingEclipsePrefs;
import com.lyz.code.infinity.core.SettingEclipseSuperTypeContainer;
import com.lyz.code.infinity.core.SettingEclipseSuperTypeName;
import com.lyz.code.infinity.core.SettingEclipseWstFacetXml;
import com.lyz.code.infinity.core.SettingJsdtScopeXml;
import com.lyz.code.infinity.core.SettingWstComponentXml;
import com.lyz.code.infinity.core.WebXml;
import com.lyz.code.infinity.exception.ValidateException;
import com.lyz.code.infinity.generator.DBDefinitionGenerator;
import com.lyz.code.infinity.include.AdminNav;
import com.lyz.code.infinity.include.Footer;
import com.lyz.code.infinity.include.Header;
import com.lyz.code.infinity.include.HomePage;
import com.lyz.code.infinity.include.HomePageNav;
import com.lyz.code.infinity.include.JsonHomePage;
import com.lyz.code.infinity.include.JsonUserNav;
import com.lyz.code.infinity.include.JspHomePage;
import com.lyz.code.infinity.include.JumpHomePage;
import com.lyz.code.infinity.include.Updates;
import com.lyz.code.infinity.include.UserNav;
import com.lyz.code.infinity.s2sh.core.Action;
import com.lyz.code.infinity.s2sh.core.ApplicationContextXml;
import com.lyz.code.infinity.s2sh.core.HibernateCfgXml;
import com.lyz.code.infinity.s2sh.core.S2SHPrism;
import com.lyz.code.infinity.s2sh.core.S2SHUserNav;
import com.lyz.code.infinity.s2sh.core.Struts2Facade;
import com.lyz.code.infinity.s2sh.core.StrutsConfigXml;
import com.lyz.code.infinity.utils.ConfigParser;
import com.lyz.code.infinity.utils.DBConf;
import com.lyz.code.infinity.utils.DbconfigXml;
import com.lyz.code.infinity.utils.EncodingFilter;
import com.lyz.code.infinity.utils.ParseXML;
import com.lyz.code.infinity.utils.ReadConfigXml;
import com.lyz.code.infinity.utils.StringUtil;
import com.lyz.code.infinity.utils.ZipCompressor;

public class Project {
	protected long projectId;
	protected String standardName;
	protected String packageToken;
	protected String technicalstack;
	protected long namingId;
	protected Naming naming;
	protected String folderPath = "D:/JerryWork/Infinity/testFiles/";
	protected String sourceFolderPath = "D:/JerryLunaWorkspace/InfinityGPGenerator/WebContent/templates/";
	protected List<Prism> prisms = new ArrayList<Prism>();
	protected List<Util> utils = new ArrayList<Util>();
	protected List<Include> includes = new ArrayList<Include>();
	protected List<Include> jsonIncludes = new ArrayList<Include>();
	protected List<DBDefinitionGenerator> dbDefinitionGenerators = new ArrayList<DBDefinitionGenerator>();
	protected String dbName;
	protected TestSuite projectTestSuite = new TestSuite();
	protected List<ConfigFile> configFiles = new ArrayList<ConfigFile>();
	protected List<Domain> domains = new ArrayList<Domain>();
	protected String dbPrefix = "";
	protected HomePage homepage = new HomePage();
	protected JsonHomePage jsonhomepage = new JsonHomePage();
	protected JspHomePage jsphomepage = new JspHomePage();
	protected JumpHomePage jumphomepage = new JumpHomePage();
	protected String dbUsername = "root";
	protected String dbPassword = "";
	protected boolean emptypassword = false;
	protected String sgsSource;
	protected String sqlSource;
	
	public JsonHomePage getJsonhomepage() {
		return jsonhomepage;
	}

	public void setJsonhomepage(JsonHomePage jsonhomepage) {
		this.jsonhomepage = jsonhomepage;
	}

	public JspHomePage getJsphomepage() {
		return jsphomepage;
	}

	public void setJsphomepage(JspHomePage jsphomepage) {
		this.jsphomepage = jsphomepage;
	}

	public String getSgsSource() {
		return sgsSource;
	}

	public void setSgsSource(String sgsSource) {
		this.sgsSource = sgsSource;
	}

	public String getSqlSource() {
		return sqlSource;
	}

	public void setSqlSource(String sqlSource) {
		this.sqlSource = sqlSource;
	}
	
	public boolean isEmptypassword() {
		return emptypassword;
	}

	public void setEmptypassword(boolean emptypassword) {
		this.emptypassword = emptypassword;
	}

	public Project(){
		super();
		addUtil(new DBConf());
		addUtil(new ConfigParser());
		addUtil(new ParseXML());
		addUtil(new ReadConfigXml());
		addUtil(new DbconfigXml());
		addConfigFile(new WebXml());
		
		addInclude(new Header());
		addInclude(new Footer());
		addInclude(new Updates());
		addInclude(new AdminNav());
		addInclude(new UserNav());
		addInclude(new HomePageNav());
	}

	public List<Include> getJsonIncludes() {
		return jsonIncludes;
	}

	public void setJsonIncludes(List<Include> jsonIncludes) {
		this.jsonIncludes = jsonIncludes;
	}
	
	public Project(String projectName,String packageToken, String technicalStack,String dbUsername, String dbPassword, boolean emptyPassword, String dbname){
		super();
		this.standardName = projectName;
		this.packageToken = packageToken;
		this.technicalstack = technicalStack;
		this.emptypassword = emptyPassword;
		this.dbUsername = dbUsername;
		this.dbPassword = dbPassword;
		this.dbName = dbname;
		if (technicalStack==null || technicalStack.equals("")|| technicalStack.equalsIgnoreCase("clocksimplejee")|| technicalStack.equalsIgnoreCase("simplejee")|| technicalStack.equalsIgnoreCase("jsp")){
			this.setTechnicalstack("simplejee");
			addUtil(new DBConf(packageToken));
			addUtil(new ConfigParser(packageToken));
			addUtil(new ParseXML(packageToken));
			addUtil(new ReadConfigXml(packageToken));
			addUtil(new DbconfigXml(projectName,dbUsername,dbPassword,emptyPassword));
			EncodingFilter eFilter = new EncodingFilter(packageToken);
			addUtil(eFilter);
			WebXml webxml = new WebXml();
			webxml.setPackageToken(packageToken);
			webxml.setProjectName(this.getStandardName());
			webxml.addFilter(eFilter);
			addConfigFile(webxml);
			
			Log4jProperties log4j = new Log4jProperties();
			log4j.setPutInsideSrcAndClasses(true);
			addConfigFile(log4j);
			
			DotProjectXml dpxml = new DotProjectXml();
			dpxml.setProjectName(projectName);
			addConfigFile(dpxml);
			
			DotClassPathXml dcpxml = new DotClassPathXml();
			addConfigFile(dcpxml);
			
			SettingJsdtScopeXml sscopexml = new SettingJsdtScopeXml();
			addConfigFile(sscopexml);
			
			SettingEclipseJdtPrefs sejPrefs = new SettingEclipseJdtPrefs();
			addConfigFile(sejPrefs);
			
			SettingEclipsePrefs sePrefs = new SettingEclipsePrefs();
			addConfigFile(sePrefs);
			
			SettingEclipseSuperTypeContainer sContainer = new SettingEclipseSuperTypeContainer();
			addConfigFile(sContainer);
			
			SettingEclipseSuperTypeName sSuperTypeName = new  SettingEclipseSuperTypeName();
			addConfigFile(sSuperTypeName);
			
			SettingEclipseWstFacetXml sFacetXml = new SettingEclipseWstFacetXml();
			addConfigFile(sFacetXml);
			
			SettingWstComponentXml sComponentXml = new SettingWstComponentXml();
			sComponentXml.setProjectName(projectName);
			addConfigFile(sComponentXml);
			
			addInclude(new Header());
			addInclude(new Footer());
			addInclude(new Updates());
			addInclude(new AdminNav());
			addInclude(new UserNav());
			addInclude(new HomePageNav());
			
			addJsonInclude(new Header());
			addJsonInclude(new Footer());
			addJsonInclude(new Updates());
			addJsonInclude(new JsonUserNav());
		}else if (technicalStack.equalsIgnoreCase("s2sh")){
			this.setTechnicalstack("s2sh");
			com.lyz.code.infinity.s2sh.core.WebXml webxml = new com.lyz.code.infinity.s2sh.core.WebXml();
			webxml.setPackageToken(packageToken);
			webxml.setProjectName(this.getStandardName());
			addConfigFile(webxml);
			StrutsConfigXml sxml = new StrutsConfigXml();	
			List<Action> myActions = new ArrayList<Action>();
			for (Prism p:this.prisms){
				S2SHPrism sp = (S2SHPrism)p;
				myActions.add(sp.getAction());
			}
			List<Struts2Facade> myFacades = new ArrayList<Struts2Facade>();
			for (Prism p:this.prisms){
				S2SHPrism sp = (S2SHPrism)p;
				myFacades.add(sp.getFacade());
			}
			sxml.setPutInsideSrcAndClasses(true);
			sxml.setActions(myActions);
			sxml.setFacades(myFacades);
			addConfigFile(sxml);
			
			ApplicationContextXml axml = new ApplicationContextXml();
			axml.setDbname(dbName);
			axml.setDbUsername(dbUsername);
			axml.setDbPassword(dbPassword);
			axml.setEmptypassword(emptyPassword);
			axml.setDomainList(this.domains);
			axml.setActionList(myActions);
			axml.setFacadeList(myFacades);
			List<String> packageToScanList = new ArrayList<String>();
			packageToScanList.add(this.packageToken+".domain");
			axml.setPackagesToScanList(packageToScanList);
			axml.setPutInsideSrcAndClasses(true);
			addConfigFile(axml);
			
			HibernateCfgXml hxml = new HibernateCfgXml();
			hxml.setPutInsideSrcAndClasses(true);
			addConfigFile(hxml);
			
			Log4jProperties log4j = new Log4jProperties();
			log4j.setPutInsideSrcAndClasses(true);
			addConfigFile(log4j);
			
			DotProjectXml dpxml = new DotProjectXml();
			dpxml.setProjectName(projectName);
			addConfigFile(dpxml);
			
			DotClassPathXml dcpxml = new DotClassPathXml();
			addConfigFile(dcpxml);
			
			SettingJsdtScopeXml sscopexml = new SettingJsdtScopeXml();
			addConfigFile(sscopexml);
			
			SettingEclipseJdtPrefs sejPrefs = new SettingEclipseJdtPrefs();
			addConfigFile(sejPrefs);
			
			SettingEclipsePrefs sePrefs = new SettingEclipsePrefs();
			addConfigFile(sePrefs);
			
			SettingEclipseSuperTypeContainer sContainer = new SettingEclipseSuperTypeContainer();
			addConfigFile(sContainer);
			
			SettingEclipseSuperTypeName sSuperTypeName = new  SettingEclipseSuperTypeName();
			addConfigFile(sSuperTypeName);
			
			SettingEclipseWstFacetXml sFacetXml = new SettingEclipseWstFacetXml();
			addConfigFile(sFacetXml);
			
			SettingWstComponentXml sComponentXml = new SettingWstComponentXml();
			sComponentXml.setProjectName(projectName);
			addConfigFile(sComponentXml);
			
			addInclude(new Header());
			addInclude(new Footer());
			addInclude(new Updates());
			addInclude(new AdminNav());
			addInclude(new S2SHUserNav());
			addInclude(new HomePageNav());
			
			addJsonInclude(new Header());
			addJsonInclude(new Footer());
			addJsonInclude(new Updates());
			addJsonInclude(new JsonUserNav());
		} else if (technicalStack.equalsIgnoreCase("s2shc")){
			this.setTechnicalstack("s2shc");
			com.lyz.code.infinity.s2sh.core.WebXml webxml = new com.lyz.code.infinity.s2sh.core.WebXml();
			webxml.setPackageToken(packageToken);
			webxml.setProjectName(this.getStandardName());
			addConfigFile(webxml);
			StrutsConfigXml sxml = new StrutsConfigXml();	
			List<Action> myActions = new ArrayList<Action>();
			for (Prism p:this.prisms){
				S2SHPrism sp = (S2SHPrism)p;
				myActions.add(sp.getAction());
			}
			List<Struts2Facade> myFacades = new ArrayList<Struts2Facade>();
			for (Prism p:this.prisms){
				S2SHPrism sp = (S2SHPrism)p;
				myFacades.add(sp.getFacade());
			}
			sxml.setPutInsideSrcAndClasses(true);
			sxml.setActions(myActions);
			sxml.setFacades(myFacades);
			addConfigFile(sxml);
			
			ApplicationContextXml axml = new ApplicationContextXml();
			axml.setDbname(dbName);
			axml.setDbUsername(dbUsername);
			axml.setDbPassword(dbPassword);
			axml.setEmptypassword(emptyPassword);
			axml.setDomainList(this.domains);
			axml.setActionList(myActions);
			axml.setFacadeList(myFacades);
			List<String> packageToScanList = new ArrayList<String>();
			packageToScanList.add(this.packageToken+".domain");
			axml.setPackagesToScanList(packageToScanList);
			axml.setPutInsideSrcAndClasses(true);
			addConfigFile(axml);
			
			HibernateCfgXml hxml = new HibernateCfgXml();
			hxml.setPutInsideSrcAndClasses(true);
			addConfigFile(hxml);
			
			Log4jProperties log4j = new Log4jProperties();
			log4j.setPutInsideSrcAndClasses(true);
			addConfigFile(log4j);
			
			DotProjectXml dpxml = new DotProjectXml();
			dpxml.setProjectName(projectName);
			addConfigFile(dpxml);
			
			DotClassPathXml dcpxml = new DotClassPathXml();
			addConfigFile(dcpxml);
			
			SettingJsdtScopeXml sscopexml = new SettingJsdtScopeXml();
			addConfigFile(sscopexml);
			
			SettingEclipseJdtPrefs sejPrefs = new SettingEclipseJdtPrefs();
			addConfigFile(sejPrefs);
			
			SettingEclipsePrefs sePrefs = new SettingEclipsePrefs();
			addConfigFile(sePrefs);
			
			SettingEclipseSuperTypeContainer sContainer = new SettingEclipseSuperTypeContainer();
			addConfigFile(sContainer);
			
			SettingEclipseSuperTypeName sSuperTypeName = new  SettingEclipseSuperTypeName();
			addConfigFile(sSuperTypeName);
			
			SettingEclipseWstFacetXml sFacetXml = new SettingEclipseWstFacetXml();
			addConfigFile(sFacetXml);
			
			SettingWstComponentXml sComponentXml = new SettingWstComponentXml();
			sComponentXml.setProjectName(projectName);
			addConfigFile(sComponentXml);
			
			addInclude(new Header());
			addInclude(new Footer());
			addInclude(new Updates());
			addInclude(new AdminNav());
			addInclude(new S2SHUserNav());
			addInclude(new HomePageNav());
			
			addJsonInclude(new Header());
			addJsonInclude(new Footer());
			addJsonInclude(new Updates());
			addJsonInclude(new JsonUserNav());
		}
		

	}
	
	public ConfigFile findConfigFile(String standardName){
		if (this.configFiles != null && this.configFiles.size()>0){
			for (ConfigFile c : this.configFiles){
				if (c.getStandardName().equals(standardName)) return c;
			}
		}
		return null;
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
	
	public void addInclude(Include include){
		this.includes.add(include);
	}
	
	public void addJsonInclude(Include include){
		this.jsonIncludes.add(include);
	}
	
	public long getProjectId() {
		return projectId;
	}
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
	public String getStandardName() {
		return standardName;
	}
	public void setStandardName(String standardName) {
		this.standardName = standardName;
		this.homepage.setPageTitile("Welcome to the " + StringUtil.capFirst(standardName)+ " System.");
		WebXml webxml = (WebXml)findConfigFile("web.xml");
		if (webxml != null)
			webxml.setProjectName(standardName);
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
	public List<Prism> getPrisms() {
		return prisms;
	}
	public void setPrisms(List<Prism> prisms) {
		if (this.technicalstack == null || "".equals(this.technicalstack) ||"simplejee".equalsIgnoreCase(this.technicalstack)||"jsp".equalsIgnoreCase(this.technicalstack)){				
			this.prisms = prisms;
			if (this.technicalstack==null || this.technicalstack.equals("")|| this.technicalstack.equalsIgnoreCase("simplejee")|| this.technicalstack.equalsIgnoreCase("jsp")){
				WebXml webxml = (WebXml)this.findConfigFile("web.xml");
				for (Prism p: prisms){
					webxml.addControllers(p.getControllers());
					webxml.addFacades(p.getFacades());
					for (Include in: this.jsonIncludes){
						in.allDomainList.add(p.getDomain());
					}
				}
			}
		}else if ("s2sh".equalsIgnoreCase(this.technicalstack)){
			this.prisms = prisms;
			StrutsConfigXml sxml = (StrutsConfigXml)this.findConfigFile("struts.xml");
			for (Prism prism:prisms){
//				S2SHPrism s2shPrism = (S2SHPrism) prism;
//				if (s2shPrism.getDbDefinitionGenerator() != null){
//					this.dbDefinitionGenerators.add(s2shPrism.getDbDefinitionGenerator());
//				}
				
				if (sxml != null && prism instanceof S2SHPrism){
					sxml.addAction(((S2SHPrism)prism).getAction());
					sxml.addFacade(((S2SHPrism)prism).getFacade());
				}
				
				for (Include in: this.jsonIncludes){
					in.allDomainList.add(prism.getDomain());
				}
				
			}
		}else if ("s2shc".equalsIgnoreCase(this.technicalstack)){
			this.prisms = prisms;
			StrutsConfigXml sxml = (StrutsConfigXml)this.findConfigFile("struts.xml");
			for (Prism prism:prisms){
			
				if (sxml != null && prism instanceof S2SHPrism){
					sxml.addAction(((S2SHPrism)prism).getAction());
					sxml.addFacade(((S2SHPrism)prism).getFacade());
				}
				
				for (Include in: this.jsonIncludes){
					in.allDomainList.add(prism.getDomain());
				}
				
			}
		}
	}
	
	public void addPrism(Prism prism){	
		if (this.technicalstack == null || "".equals(this.technicalstack) ||"simplejee".equalsIgnoreCase(this.technicalstack)||"jsp".equalsIgnoreCase(this.technicalstack)){
			if (this.packageToken != null) prism.setPackageToken(this.packageToken);
			this.prisms.add(prism);
			if (prism.getDbDefinitionGenerator() != null){
				this.dbDefinitionGenerators.add(prism.getDbDefinitionGenerator());
			}
			WebXml webxml = (WebXml)this.findConfigFile("web.xml");
			if (webxml != null)
				webxml.addControllers(prism.getControllers());
		} else if ("s2sh".equalsIgnoreCase(this.technicalstack)){
			if (this.packageToken != null) prism.setPackageToken(this.packageToken);
			this.prisms.add(prism);
//			if (prism.getDbDefinitionGenerator() != null){
//				this.dbDefinitionGenerators.add(prism.getDbDefinitionGenerator());
//			}
			StrutsConfigXml sxml;
			if (findConfigFile("struts.xml")==null){
				sxml= new StrutsConfigXml();
				sxml.setPutInsideSrcAndClasses(true);
				this.addConfigFile(sxml);				
			}else {
				sxml= (StrutsConfigXml)this.findConfigFile("struts.xml");
			}
			if (sxml != null && prism instanceof S2SHPrism){
				sxml.addAction(((S2SHPrism)prism).getAction());
				sxml.addFacade(((S2SHPrism)prism).getFacade());
			}

			for (Include in: this.jsonIncludes){
				in.allDomainList.add(prism.getDomain());
			}
		} else if ("s2shc".equalsIgnoreCase(this.technicalstack)){
			if (this.packageToken != null) prism.setPackageToken(this.packageToken);
			this.prisms.add(prism);
			StrutsConfigXml sxml;
			if (findConfigFile("struts.xml")==null){
				sxml= new StrutsConfigXml();
				sxml.setPutInsideSrcAndClasses(true);
				this.addConfigFile(sxml);				
			}else {
				sxml= (StrutsConfigXml)this.findConfigFile("struts.xml");
			}
			if (sxml != null && prism instanceof S2SHPrism){
				sxml.addAction(((S2SHPrism)prism).getAction());
				sxml.addFacade(((S2SHPrism)prism).getFacade());
			}

			for (Include in: this.jsonIncludes){
				in.allDomainList.add(prism.getDomain());
			}
		}
		
	}
	public String getPackageToken() {
		return packageToken;
	}
	public void setPackageToken(String packageToken) {
		this.packageToken = packageToken;
		for (Util u: this.utils){
			u.setPackageToken(packageToken);
		}
		WebXml webxml = (WebXml)findConfigFile("web.xml");
		if (webxml != null)
			webxml.setPackageToken(packageToken);
	}
	
	public void generateProjectFiles() throws ValidateException{
		ValidateInfo info = this.validate();
		if (info.success() == false) {
			for (String s:info.getCompileErrors()){
				System.out.println(s);
			}
			return;
		}
		try {
		for (Domain d: this.domains){
			String srcfolderPath = this.getProjectFolderPath();
			if (this.packageToken != null && !"".equals(this.packageToken))
				srcfolderPath = folderPath + this.getStandardName() +"/src/" + packagetokenToFolder(this.packageToken)+"/domain/";
			String filePath = srcfolderPath+d.getStandardName()+".java";
			writeToFile(filePath, d.generateClassString());
		}
		for (Prism ps: this.prisms){
			ps.setFolderPath(this.getProjectFolderPath());
			ps.generatePrismFiles();
		}
		for (Util u:utils){
			String utilPath = this.getProjectFolderPath() + "src/" + packagetokenToFolder(u.getPackageToken());
			String utilFilePath = utilPath+u.getFileName();
			writeToFile(utilFilePath, u.generateUtilString());
		}
		
		for (Include include:this.includes){
			String includePath = this.getProjectFolderPath() + "WebContent/include/";
			String includeFilePath = includePath+include.getFileName();
			writeToFile(includeFilePath, include.generateIncludeString());
		}
		
		for (Include include:this.jsonIncludes){
			String includePath = this.getProjectFolderPath() + "WebContent/jsoninclude/";
			String includeFilePath = includePath+include.getFileName();
			writeToFile(includeFilePath, include.generateIncludeString());
		}
		
		String homePath = this.getProjectFolderPath() + "WebContent/";
		String homeFilePath = homePath+this.homepage.getFileName();
		writeToFile(homeFilePath, this.homepage.generateIncludeString());
		
		homePath = this.getProjectFolderPath() + "WebContent/jsonjsp/";
		homeFilePath = homePath+this.jsonhomepage.getFileName();
		writeToFile(homeFilePath, this.jsonhomepage.generateIncludeString());
		
		homePath = this.getProjectFolderPath() + "WebContent/jsp/";
		homeFilePath = homePath+this.jsphomepage.getFileName();
		writeToFile(homeFilePath, this.jsphomepage.generateIncludeString());

		File libto = new File(this.getProjectFolderPath() + "WebContent/WEB-INF/lib/");
		File libfrom = new File(this.getSourceFolderPath() + "lib/");
		
		FileCopyer copy = new FileCopyer();
		// 设置来源去向
		copy.dirFrom = libfrom;
		copy.dirTo = libto;
		copy.listFileInDir(libfrom);
		
		File cssfrom = new File(this.getSourceFolderPath() + "css/");
		File cssto = new File(this.getProjectFolderPath() + "WebContent/css/");
		
		copy.dirFrom = cssfrom;
		copy.dirTo = cssto;
		copy.listFileInDir(cssfrom);
		
		File jsfrom = new File(this.getSourceFolderPath() + "js/");
		File jsto = new File(this.getProjectFolderPath() + "WebContent/js/");
		
		copy.dirFrom = jsfrom;
		copy.dirTo = jsto;
		copy.listFileInDir(jsfrom);
		
		StringBuilder sql = new StringBuilder();			
		boolean createNew = true;
		for (DBDefinitionGenerator dbd:dbDefinitionGenerators){
			sql.append(dbd.generateDBSql(createNew)).append("\n");
			if (createNew) createNew = false;
       }
		
		String fPath = this.getProjectFolderPath()+"sql/"+this.dbName+".sql";
		writeToFile(fPath,sql.toString());
		
		if (this.getSgsSource()!= null && !this.getSgsSource().equals("")){
			String myfPath = this.getProjectFolderPath()+"sgs/"+this.getStandardName()+"_original.sgs";
			writeToFile(myfPath,this.getSgsSource());
		}
		
        for (ConfigFile cf : this.configFiles){
        	if (cf.isTopLevel()){
        		String myfPath0 = this.getProjectFolderPath()+cf.getStandardName();
        		writeToFile(myfPath0, cf.generateConfigFileString());
        	}else if (cf.isSettingParamFile()){
        		String myfPath1 = this.getProjectFolderPath()+".settings/"+cf.getStandardName();
        		writeToFile(myfPath1, cf.generateConfigFileString());
        	}else if (cf.isPutInsideSrcAndClasses == false){
	        	String myfPath2 = this.getProjectFolderPath()+"WebContent/WEB-INF/"+cf.getStandardName();
	    		writeToFile(myfPath2, cf.generateConfigFileString());
        	} else {
        		String myfPath3 = this.getProjectFolderPath()+"src/"+cf.getStandardName();
        		writeToFile(myfPath3, cf.generateConfigFileString());
	    		
	    		String myfPath4 = this.getProjectFolderPath()+"WebContent/WEB-INF/classes/"+cf.getStandardName();
	    		writeToFile(myfPath4, cf.generateConfigFileString());
        	}
        }
	} catch (Exception e){
		e.printStackTrace();
		throw new ValidateException("源码生成错误");
	}
	}
		
	public void writeToFile(String filePath, String content) throws Exception{
		File f = new File(filePath);
		if (!f.getParentFile().exists()) {
			f.getParentFile().mkdirs();
		}
		f.createNewFile();
		try (Writer fw = new BufferedWriter( new OutputStreamWriter(new FileOutputStream(f.getAbsolutePath()),"UTF-8"))){			
	        fw.write(content,0,content.length());
		}
	}
	
	public String getSourceFolderPath() {
		return sourceFolderPath;
	}

	public void setSourceFolderPath(String sourceFolderPath) {
		this.sourceFolderPath = sourceFolderPath;
	}

	public void generateProjectZip() throws ValidateException{
		delAllFile(this.folderPath+this.standardName+".zip");
		delFolder(this.getProjectFolderPath());
		File f = new File(this.getProjectFolderPath());
		if (!f.getParentFile().exists()) {
			f.getParentFile().mkdirs();
		}
				
		generateProjectFiles();
		ZipCompressor compressor = new ZipCompressor(this.folderPath+this.standardName+".zip");
		compressor.compressExe(this.getProjectFolderPath());
		delFolder(this.getProjectFolderPath());
	}
	
	public String getFolderPath() {
		return this.folderPath;
	}
	
	public String getProjectFolderPath(){
		if ( this.getStandardName()!= null && !"".equals(this.getStandardName())){
			return folderPath + this.getStandardName() + "/";
		}
		else return folderPath;
	}
	public void setFolderPath(String folderPath) {
		this.folderPath = folderPath;
	}
	
	public static String packagetokenToFolder(String packageToken){
		String folder = "";
		if (packageToken != null)
			 folder = packageToken.replace('.','/');
		folder += "/";
		return folder;
	}
	
	public static String folderToPackageToken(String folder){
		String packagetoken = folder.replace('/', '.');
		if (packagetoken.charAt(packagetoken.length()-1) == '.')
			packagetoken = packagetoken.substring(0, packagetoken.length() -1);
		return packagetoken;
	}
	public List<DBDefinitionGenerator> getDbDefinitionGenerators() {
		return dbDefinitionGenerators;
	}
	public void setDbDefinitionGenerators(
			List<DBDefinitionGenerator> dbDefinitionGenerators) {
		this.dbDefinitionGenerators = dbDefinitionGenerators;
	}
	
	public void addDBDefinitionGenerator(DBDefinitionGenerator generator){
		this.dbDefinitionGenerators.add(generator);
	}
	public String getDbName() {
		return dbName;
	}
	
	public void setDbName(String dbName) {
		this.dbName = dbName;
		if (this.technicalstack==null || "".equals(this.technicalstack) || this.technicalstack.equals("jsp") || this.technicalstack.equalsIgnoreCase("simplejee")) ((DbconfigXml)this.utils.get(4)).setDbName(dbName);
	}	
	
	public TestSuite getProjectTestSuite() {
		return projectTestSuite;
	}
	public void setProjectTestSuite(TestSuite projectTestSuite) {
		this.projectTestSuite = projectTestSuite;
	}
	
	public void addTestSuite(TestSuite testSuite){
		this.projectTestSuite.testSuites.add(testSuite);
	}
	
	public void addTestCase(TestCase testCase){
		this.projectTestSuite.testCases.add(testCase);
	}
	
	public void addTestCases(List<TestCase> testCases){
		this.projectTestSuite.testCases.addAll(testCases);
	}
	public List<ConfigFile> getConfigFiles() {
		return configFiles;
	}
	public void setConfigFiles(List<ConfigFile> configFiles) {
		this.configFiles = configFiles;
	}
	
	public void addConfigFile(ConfigFile configFile){
		this.configFiles.add(configFile);
	}
	
	public void addConfigFiles(List<ConfigFile> configFiles){
		this.configFiles.addAll(configFiles);
	}
	
	public static void zipFile(File inFile, ZipOutputStream zos, String dir) throws IOException {
		if (inFile.isDirectory()) {
			File[] files = inFile.listFiles();
			for (File file:files)
				zipFile(file, zos, dir + "/" + inFile.getName());
		} else {
			String entryName = null;
			if (!"".equals(dir))
				entryName = dir + "/" + inFile.getName();
			else
				entryName = inFile.getName();
			ZipEntry entry = new ZipEntry(entryName);
			zos.putNextEntry(entry);
			InputStream is = new FileInputStream(inFile);
			int len = 0;
			while ((len = is.read()) != -1)
				zos.write(len);
			is.close();
		}
	}

	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}
	
	public ValidateInfo validate(){
		ValidateInfo info = new ValidateInfo();
		info.setSuccess(true);
		for (Prism ps:this.prisms){
			ValidateInfo v = ps.validate();
			if (v.success() == false){
				info.setSuccess(false);
				info.addAllCompileErrors(v.getCompileErrors());
				info.addAllCompileWarnings(v.getCompileWarnings());
			}
		}
		return info;
	}
	public List<Domain> getDomains() {
		return domains;
	}
	public void setDomains(List<Domain> domains) {		
		for (Domain d: domains){
			this.addDomain(d);
			for (Include include:this.includes){
				include.addDomain(d);
			}
		}
	}
	
	public void addDomain(Domain domain){
		if (this.getDbPrefix() != null && !this.getDbPrefix().equals("")) {
			domain.setDbPrefix(this.getDbPrefix());	
		}
		this.domains.add(domain);
		for (Include include:this.includes){
			include.addDomain(domain);
		}
	}

	public String getDbPrefix() {
		return dbPrefix;
	}

	public void setDbPrefix(String dbPrefix) {
		this.dbPrefix = dbPrefix;
		for (Domain d:this.getDomains()){
			d.setDbPrefix(dbPrefix);
		}
		for (Prism p: this.getPrisms()){
			p.getDomain().setDbPrefix(dbPrefix);
		}
	
	}

	public String getTechnicalstack() {
		return technicalstack;
	}

	public void setTechnicalstack(String technicalstack) {
		this.technicalstack = technicalstack;
	}

	public List<Include> getIncludes() {
		return includes;
	}

	public void setIncludes(List<Include> includes) {
		this.includes = includes;
	}

	public HomePage getHomepage() {
		return homepage;
	}

	public void setHomepage(HomePage homepage) {
		this.homepage = homepage;
	}

	public JumpHomePage getJumphomepage() {
		return jumphomepage;
	}

	public void setJumphomepage(JumpHomePage jumphomepage) {
		this.jumphomepage = jumphomepage;
	}

	public String getDbUsername() {
		return dbUsername;
	}

	public void setDbUsername(String dbUsername) {
		this.dbUsername = dbUsername;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}
	
	public void replaceConfigFile(ConfigFile cf){
		if (cf!=null && this.configFiles != null){
			if (this.configFiles.size()== 0 || !this.configFiles.contains(cf.getStandardName())) {
				this.configFiles.add(cf);
				return;
			}
			for (int i=0;i<this.configFiles.size();i++){
				if (this.configFiles.get(i).getStandardName().equals(cf.getStandardName())) {
					this.configFiles.remove(i);
					this.configFiles.add(cf);
				}
			}
		}
	}
}
