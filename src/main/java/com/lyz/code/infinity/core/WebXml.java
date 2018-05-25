package com.lyz.code.infinity.core;

import java.util.ArrayList;
import java.util.List;

import com.lyz.code.infinity.domain.ConfigFile;
import com.lyz.code.infinity.domain.Controller;
import com.lyz.code.infinity.domain.Facade;
import com.lyz.code.infinity.domain.Util;
import com.lyz.code.infinity.utils.StringUtil;

public class WebXml extends ConfigFile{
	protected List<Controller> controllers = new ArrayList<Controller>();
	protected List<Facade> facades = new ArrayList<Facade>();
	protected List<Util> filters = new ArrayList<Util>();
	protected String xmlDefinition = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
	protected String webApp = "<web-app xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://java.sun.com/xml/ns/javaee\" xmlns:web=\"http://java.sun.com/xml/ns/javaee\" xmlns:jsp=\"http://java.sun.com/xml/ns/javaee/jsp\" xsi:schemaLocation=\"http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd\" version=\"2.5\">\n";
	protected String projectName;
	protected List<String> welcomeFileList = new ArrayList<String>();
	protected List<ErrPage> errPages = new ArrayList<ErrPage>();
	protected String packageToken;
	
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
		
		for (Util filter : filters){
			sb.append("<filter>\n");
			sb.append("\t<filter-name>").append(StringUtil.lowerFirst(filter.getStandardName())).append("</filter-name>\n");
			sb.append("\t<filter-class>").append(filter.getPackageToken()).append(".").append(filter.getStandardName()).append("</filter-class>\n");
			sb.append("</filter>\n\n");		
			sb.append("<filter-mapping>\n");	
			sb.append("\t<filter-name>").append(StringUtil.lowerFirst(filter.getStandardName())).append("</filter-name>\n");	
			sb.append("\t<url-pattern>*</url-pattern>\n");	
			sb.append("</filter-mapping>\n\n");	
		}

		for (Controller c : controllers){
			sb.append("<servlet>\n").append("<description></description>\n");
			sb.append("<display-name>").append(StringUtil.lowerFirst(c.getStandardName())).append("</display-name>\n");
			sb.append("<servlet-name>").append(StringUtil.lowerFirst(c.getStandardName())).append("</servlet-name>\n");
			sb.append("<servlet-class>").append(this.packageToken).append(".controller.").append(StringUtil.capFirst(c.getStandardName())).append("</servlet-class>\n");
			sb.append("</servlet>\n\n");
			sb.append("<servlet-mapping>\n");
			sb.append("<servlet-name>").append(StringUtil.lowerFirst(c.getStandardName())).append("</servlet-name>\n");
			sb.append("<url-pattern>/controller/").append(StringUtil.lowerFirst(c.getStandardName())).append("</url-pattern>\n");
			sb.append("</servlet-mapping>\n\n");
		}
		
		for (Facade fa : facades){
			sb.append("<servlet>\n").append("<description></description>\n");
			sb.append("<display-name>").append(StringUtil.lowerFirst(fa.getStandardName())).append("</display-name>\n");
			sb.append("<servlet-name>").append(StringUtil.lowerFirst(fa.getStandardName())).append("</servlet-name>\n");
			sb.append("<servlet-class>").append(this.packageToken).append(".facade.").append(StringUtil.capFirst(fa.getStandardName())).append("</servlet-class>\n");
			sb.append("</servlet>\n\n");
			sb.append("<servlet-mapping>\n");
			sb.append("<servlet-name>").append(StringUtil.lowerFirst(fa.getStandardName())).append("</servlet-name>\n");
			sb.append("<url-pattern>/facade/").append(StringUtil.lowerFirst(fa.getStandardName())).append("</url-pattern>\n");
			sb.append("</servlet-mapping>\n\n");
		}
		sb.append("</web-app>\n");
		return sb.toString();
	}

	public List<Controller> getControllers() {
		return controllers;
	}

	public void setControllers(List<Controller> controllers) {
		this.controllers = controllers;
	}
	
	public void addController(Controller controller){
		this.controllers.add(controller);
	}
	
	public void addControllers(List<Controller> controllers){
		this.controllers.addAll(controllers);
	}
	
	public void addFacades(List<Facade> facades){
		this.facades.addAll(facades);
	}
	
	public void addFacade(Facade facade){
		this.facades.add(facade);
	}
	
	public void addFilter(Util filter){
		this.filters.add(filter);
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