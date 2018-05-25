package com.lyz.code.infinity.s2sh.core;

import java.util.ArrayList;
import java.util.List;

import com.lyz.code.infinity.domain.ConfigFile;
import com.lyz.code.infinity.domain.Controller;
import com.lyz.code.infinity.utils.StringUtil;

public class WebXml extends ConfigFile{
	protected List<Action> actions = new ArrayList<Action>();
	protected String xmlDefinition = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
	protected String webApp = "<web-app xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://java.sun.com/xml/ns/javaee\" xmlns:web=\"http://java.sun.com/xml/ns/javaee\" xmlns:jsp=\"http://java.sun.com/xml/ns/javaee/jsp\" xsi:schemaLocation=\"http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd\" version=\"2.5\">\n";
	protected String projectName;
	protected List<String> welcomeFileList = new ArrayList<String>();
	protected List<ErrPage> errPages = new ArrayList<ErrPage>();
	protected String packageToken;
	protected String openSessionFilterContent = "<filter>\n"+
												  "\t<filter-name>SpringOpenSessionInViewFilter</filter-name>\n"+
												  "\t<filter-class>org.springframework.orm.hibernate4.support.OpenSessionInViewFilter</filter-class>\n"+
												  "</filter>\n\n"+
												  "<filter-mapping>\n"+
												  "\t<filter-name>SpringOpenSessionInViewFilter</filter-name>\n"+
												  "\t<url-pattern>/*</url-pattern>\n"+
												  "</filter-mapping>\n";
	
	protected String strutsSpringConfigContent = "<filter>\n" +
												 "<filter-name>struts-cleanup</filter-name>\n" +
												 "\t<filter-class>\n" +
												 "\t\torg.apache.struts2.dispatcher.ActionContextCleanUp\n"+
												 "\t</filter-class>\n" +
												 "</filter>\n\n" + 
												 "<filter-mapping>\n" +
												 "\t<filter-name>struts-cleanup</filter-name>\n" +
												 "\t<url-pattern>/*</url-pattern>\n" +
												 "</filter-mapping>\n\n" +      
												 "<filter>\n" +
												 "\t<filter-name>struts2</filter-name>\n" +
												 "\t<filter-class>org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter</filter-class>\n" +
												 "</filter>\n\n" +
												 "<filter-mapping>\n" +
												 "\t<filter-name>struts2</filter-name>\n" +
												 "\t<url-pattern>/*</url-pattern>\n" +
												 "</filter-mapping>\n\n" +
												 "<!-- needed for ContextLoaderListener -->\n" +
												 "<context-param>\n" +
												 "\t<param-name>contextConfigLocation</param-name>\n" +
												 "\t<param-value>classpath:applicationContext*.xml</param-value>\n" +
												 "</context-param>\n\n" +
												 "<!-- Bootstraps the root web application context before servlet initialization -->\n" +
												 "<listener>\n" +
												 "\t<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>\n" +
												 "</listener>\n\n";
	
	public WebXml(){
		super();
		this.standardName = "web.xml";
	}
	
	@Override 
	public void setStandardName(String standardName){
	}
	
	@Override
	public String generateConfigFileString() {
		StringBuilder sb = new StringBuilder();
		sb.append(xmlDefinition).append(webApp).append("<display-name>").append(this.getProjectName()).append("</display-name>\n");
		if (welcomeFileList.size() > 0){
			sb.append("<welcome-file-list>\n");
			for (String welcomeFile : welcomeFileList){
				sb.append("<welcome-file>").append(welcomeFile).append("</welcome-file>\n");
			}
			sb.append("</welcome-file-list>\n\n");
		}
		
		for (ErrPage ep : this.errPages){
			sb.append("<error-page>\n>").append("<error-code>").append(ep.getErrCode()).append("</error-code>\n")
					.append("<location>").append(ep.getErrCode()).append("</location>\n").append("</error-page>\n\n");
		}
		sb.append(openSessionFilterContent);
		sb.append(strutsSpringConfigContent);
		sb.append("</web-app>\n");
		return sb.toString();
	}


	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}
	
	public void addAction(Action action) {
		this.actions.add(action);
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public List<String> getWelcomeFileList() {
		return welcomeFileList;
	}

	public void setWelcomeFileList(List<String> welcomeFileList) {
		this.welcomeFileList = welcomeFileList;
	}

	public List<ErrPage> getErrPages() {
		return errPages;
	}

	public void setErrPages(List<ErrPage> errPages) {
		this.errPages = errPages;
	}
	
	public void addErrPage(ErrPage errPage){
		this.errPages.add(errPage);
	}
	
	public void addErrPages(List<ErrPage> errPageList) {
		this.errPages.addAll(errPageList);
	}
	
	public void addWelcome(String welcomePage){
		this.welcomeFileList.add(welcomePage);
	}
	
	public void addWelcomes(List<String> welcomePages){
		this.welcomeFileList.addAll(welcomePages);
	}

	public String getPackageToken() {
		return packageToken;
	}

	public void setPackageToken(String packageToken) {
		this.packageToken = packageToken;
	}
}

class ErrPage {
	protected String location;
	protected String errCode;
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
}