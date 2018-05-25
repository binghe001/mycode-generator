package com.lyz.code.infinity.s2sh.core;

import java.util.ArrayList;
import java.util.List;

import com.lyz.code.infinity.domain.ConfigFile;
import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Statement;


public class ApplicationContextXml extends ConfigFile{
	protected List<Statement> contents = new ArrayList<Statement>();
	protected String xmlDefinition = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	protected String beansDefinition = "<beans xmlns=\"http://www.springframework.org/schema/beans\"\n" +
								  "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
								  "xmlns:aop=\"http://www.springframework.org/schema/aop\"\n" +
								  "xmlns:context=\"http://www.springframework.org/schema/context\"\n" +
								  "xmlns:tx=\"http://www.springframework.org/schema/tx\"\n" +
								  "xmlns:p=\"http://www.springframework.org/schema/p\"\n" +
								  "xsi:schemaLocation=\"http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd\n" +
								  "http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.0.xsd\n" +
								  "http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.0.xsd\n" +
								  "http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-4.0.xsd\">";
	protected String transactionManager = "<bean id=\"transactionManager\" class=\"org.springframework.orm.hibernate4.HibernateTransactionManager\">\n" +
										  "\t<property name=\"sessionFactory\" ref=\"sessionFactory\"></property>\n" +
										  "</bean>";
	
	protected String txAdvice = "<tx:advice id=\"txAdvice\" transaction-manager=\"transactionManager\">\n" +
								"<tx:attributes>\n" +
								"\t<tx:method name=\"list*\" read-only=\"true\"/>\n" +
								"\t<tx:method name=\"nameIsValid\" read-only=\"true\"/>\n" +
								"\t<tx:method name=\"*\"/>\n" +
								"</tx:attributes>\n" +
								"</tx:advice>";
	
	protected String aopConfig = "<aop:config>\n" +
								 "\t<aop:pointcut expression=\"execution(* org.asdmp.serviceimpl.*.*(..))\" id=\"txPointcut\"/>\n" +
								 "\t<aop:advisor advice-ref=\"txAdvice\" pointcut-ref=\"txPointcut\"/>\n" +
								 "</aop:config>	";
	
	protected List<Domain> domainList = new ArrayList<Domain>();
	protected List<Action> actionList = new ArrayList<Action>();
	protected List<Struts2Facade> facadeList = new ArrayList<Struts2Facade>();
	protected List<String> packagesToScanList = new ArrayList<String>();
	protected String dbname = "database";
	protected String dbUsername = "root";	
	protected String dbPassword = "";
	protected boolean emptypassword = false;
			
	public boolean isEmptypassword() {
		return emptypassword;
	}

	public void setEmptypassword(boolean emptypassword) {
		this.emptypassword = emptypassword;
	}

	public List<Action> getActionList() {
		return actionList;
	}

	public void setActionList(List<Action> actionList) {
		this.actionList = actionList;
	}	

	public List<Struts2Facade> getFacadeList() {
		return facadeList;
	}

	public void setFacadeList(List<Struts2Facade> facadeList) {
		this.facadeList = facadeList;
	}
	
	public void addAction(Action action){
		this.actionList.add(action);
	}

	public List<String> getPackagesToScanList() {
		return packagesToScanList;
	}

	public void setPackagesToScanList(List<String> packagesToScanList) {
		this.packagesToScanList = packagesToScanList;
	}

	public List<Domain> getDomainList() {
		return domainList;
	}
	
	public void addDomain(Domain domain){
		this.domainList.add(domain);
	}

	public void setDomainList(List<Domain> domainList) {
		this.domainList = domainList;
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
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

	public String getXmlDefinition() {
		return xmlDefinition;
	}

	public void setXmlDefinition(String xmlDefinition) {
		this.xmlDefinition = xmlDefinition;
	}



	public ApplicationContextXml(){
		super();
		this.setStandardName("applicationContext.xml");
	}
	
	@Override
	public String generateConfigFileString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.xmlDefinition).append("\n").append(this.beansDefinition).append("\n");
		sb.append(S2SHXmlGenerator.generateDataSourceXMLUsingMySqlStatementList(1000L, 1, this.dbname, this.dbUsername, this.dbPassword, this.emptypassword).getContent());
		sb.append(S2SHXmlGenerator.generateSessionFactoryStatementList(1000L, 1, this.packagesToScanList).getContent());
		sb.append(transactionManager).append("\n");
		sb.append(txAdvice).append("\n");
		sb.append(aopConfig).append("\n");
		for (Domain d:this.domainList){
			sb.append(S2SHXmlGenerator.generateApplicationContentXmlDaoServiceBeansStatementList(1000L, 1, d).getContent());
		}
		for (Action ac:this.actionList){
			sb.append(S2SHXmlGenerator.generateApplicationContentXmlActionBeansStatementList(1000L, 1, ac).getContent());
		}
		for (Struts2Facade sf:this.facadeList){
			sb.append(S2SHXmlGenerator.generateApplicationContentXmlFacadeBeansStatementList(1000L, 1, sf).getContent());
		}
		sb.append("</beans>").append("\n");
		return sb.toString();
	}

}
