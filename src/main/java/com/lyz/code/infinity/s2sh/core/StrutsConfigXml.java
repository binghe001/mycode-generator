package com.lyz.code.infinity.s2sh.core;

import java.util.ArrayList;
import java.util.List;

import com.lyz.code.infinity.core.Verb;
import com.lyz.code.infinity.domain.ConfigFile;
import com.lyz.code.infinity.utils.StringUtil;


public class StrutsConfigXml extends ConfigFile{
	protected String xmlDefinition = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	protected String definition = "<!DOCTYPE struts PUBLIC \"-//Apache Software Foundation//DTD Struts Configuration 2.0//EN\" \"http://struts.apache.org/dtds/struts-2.0.dtd\">";
	protected String projectName;
	protected String packageToken;
	protected List<Action> actions = new ArrayList<Action>();
	protected List<Struts2Facade> facades = new ArrayList<Struts2Facade>();
	protected String devMode = "<constant name=\"struts.devMode\" value=\"true\" />";
	protected String packagenotion = "<package name=\"action\" namespace=\"/action\" extends=\"struts-default\">";
	protected String packagefacadenotion = "<package name=\"facade\" namespace=\"/facade\" extends=\"json-default\">";
	protected String defaultInterceptorStack = "\t<interceptors>\n"+
											   "\t\t\t<interceptor-stack name=\"sshStack\">\n"+
											   "\t\t\t<interceptor-ref name=\"paramsPrepareParamsStack\">\n" +
											   "\t\t\t<param name=\"prepare.alwaysInvokePrepare\">false</param>\n" +
											   "\t\t\t</interceptor-ref>\n"+
											   "\t\t\t</interceptor-stack>\n" +
											   "\t\t</interceptors>\n";
	protected String defaultStackRef = "\t<default-interceptor-ref name=\"sshStack\"></default-interceptor-ref>";
		
	public List<Struts2Facade> getFacades() {
		return facades;
	}

	public void setFacades(List<Struts2Facade> facades) {
		this.facades = facades;
	}
	
	public void addFacade(Struts2Facade facade){
		this.facades.add(facade);
	}
	
	public String getXmlDefinition() {
		return xmlDefinition;
	}

	public void setXmlDefinition(String xmlDefinition) {
		this.xmlDefinition = xmlDefinition;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getPackageToken() {
		return packageToken;
	}

	public void setPackageToken(String packageToken) {
		this.packageToken = packageToken;
	}

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}
	
	public void addAction(Action action){
		this.actions.add(action);
	}

	public StrutsConfigXml(){
		super();
		this.setStandardName("struts.xml");
	}
	
	@Override
	public String generateConfigFileString() throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append(this.xmlDefinition).append("\n").append(this.definition).append("\n");
		sb.append("<struts>").append("\n");
		sb.append("\t").append(this.devMode).append("\n");
		if (this.actions!=null && this.actions.size()>0){			
			sb.append("\t").append(packagenotion).append("\n");
			sb.append("\t").append(this.defaultInterceptorStack).append("\n");
			sb.append("\t").append(this.defaultStackRef).append("\n");
			for (Action a : this.actions){
				for (Verb vb : a.getVerbs()){
					if (vb.generateControllerMethod().getStandardName().indexOf("list")>-1||vb.generateControllerMethod().getStandardName().indexOf("search")>-1||vb.generateControllerMethod().getStandardName().indexOf("find")>-1) {
						sb.append("\t\t<action name=\""+vb.generateControllerMethod().getLowerFirstMethodName()+"\" class=\""+a.getLowerFirstActionName()+"\" method=\""+vb.generateControllerMethod().getLowerFirstMethodName()+"\">").append("\n");
						sb.append("\t\t<result name=\""+vb.generateControllerMethod().getLowerFirstMethodName()+"\">/jsp/"+a.getDomain().getPlural().toLowerCase()+".jsp</result>").append("\n");
						sb.append("\t\t</action>").append("\n\n");
					}else if (vb.generateControllerMethod().getStandardName().indexOf("add")>-1||vb.generateControllerMethod().getStandardName().indexOf("update")>-1|| vb.generateControllerMethod().getStandardName().indexOf("delete")>-1 ||vb.generateControllerMethod().getStandardName().indexOf("softDelete")>-1 ||vb.generateControllerMethod().getStandardName().indexOf("edit")>-1) {
						sb.append("\t\t<action name=\""+vb.generateControllerMethod().getLowerFirstMethodName()+"\" class=\""+a.getLowerFirstActionName()+"\" method=\""+vb.generateControllerMethod().getLowerFirstMethodName()+"\">").append("\n");
						sb.append("\t\t<result name=\""+vb.generateControllerMethod().getLowerFirstMethodName()+"\" type=\"redirectAction\">listAll"+StringUtil.capFirst(a.getDomain().getPlural())+"ByPage?last=true</result>").append("\n");
						sb.append("\t\t</action>").append("\n\n");
					}
				}
				
			}
			sb.append("\t</package>\n");
		}
		
		if (this.facades!=null && this.facades.size()>0){			
			sb.append("\t").append(packagefacadenotion).append("\n");
			for (Struts2Facade sf : this.facades){
				for (Verb vb : sf.getVerbs()){
					sb.append("\t\t<action name=\""+vb.generateFacadeMethod().getLowerFirstMethodName()+"Facade\" class=\""+sf.getLowerFirstFacadeName()+"\" method=\""+vb.generateControllerMethod().getLowerFirstMethodName()+"\">").append("\n");
					sb.append("\t\t<result type=\"json\">\n\t\t\t<param name=\"root\">responseJson</param>\n\t\t</result>\n");
					sb.append("\t\t</action>").append("\n\n");
				}
				
			}
			sb.append("\t</package>\n");
		}
 
		sb.append("</struts>").append("\n");
		return sb.toString();
	}

}
