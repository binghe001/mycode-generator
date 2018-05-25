package com.lyz.code.infinity.s2sh.core;

import com.lyz.code.infinity.domain.ConfigFile;

public class HibernateCfgXml extends ConfigFile{
	protected String xmlDefinition = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	protected String definition = "<!DOCTYPE hibernate-configuration PUBLIC\n"+
								  "\t\t\"-//Hibernate/Hibernate Configuration DTD 3.0//EN\"" +
								  "\t\t\"http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd\">";
	protected String content = "<hibernate-configuration>\n" +
							   "\t<session-factory>\n" +
							   "\t\t<property name=\"hibernate.dialect\">org.hibernate.dialect.MySQLInnoDBDialect</property>\n"+
							   "\t\t<property name=\"hibernate.show_sql\">true</property>\n"+
							   "\t\t<property name=\"hibernate.format_sql\">true</property>\n"+
							   "\t\t<property name=\"hibernate.hbm2ddl.auto\">update</property>\n"+
							   //"\t\t<property name=\"hibernate.current_session_context_class\">thread</property>\n"+
							   "\t</session-factory>\n"+
							   "</hibernate-configuration>\n";
				
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

	public HibernateCfgXml(){
		super();
		this.setStandardName("hibernate.cfg.xml");
	}
	
	@Override
	public String generateConfigFileString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.xmlDefinition).append("\n").append(this.definition).append("\n");
		sb.append(this.content);
		return sb.toString();
	}

}
