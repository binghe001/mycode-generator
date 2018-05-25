package com.lyz.code.infinity.utils;

import com.lyz.code.infinity.domain.Util;

public class ConfigParser extends Util{
	public ConfigParser(){
		super();
		this.fileName = "ConfigParser.java";
	}
	
	public ConfigParser(String packageToken){
		super();
		if (packageToken!=null && !"".equals(packageToken) && packageToken.contains("database"))
			this.packageToken = packageToken;
		else if (packageToken!=null && !"".equals(packageToken))this.packageToken = packageToken+".database";
		else packageToken = "";
		this.fileName = "ConfigParser.java";
	}
	
	@Override
	public void setPackageToken(String packageToken){
		if (packageToken.contains("database"))
			this.packageToken = packageToken;
		else this.packageToken = packageToken+".database";
	}
	
	@Override
	public String generateUtilString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("package ").append(this.packageToken).append(";\n\n");

		sb.append("import org.xml.sax.Attributes;\n");
		sb.append("import org.xml.sax.helpers.DefaultHandler;\n");
		sb.append("import org.xml.sax.SAXException;\n");
		sb.append("import java.util.Properties;\n");

		sb.append("public class ConfigParser extends DefaultHandler {\n");
		sb.append("private Properties props;\n");
		sb.append("String currentSet;\n");
		sb.append("\n");
		sb.append("String currentName;\n");
		sb.append("private StringBuffer currentValue = new StringBuffer();\n");
		sb.append("\n");
		sb.append("public ConfigParser() {\n");
		sb.append("	this.props = new Properties();\n");
		sb.append("}\n");
		sb.append("\n");
		sb.append("public Properties getProps() {\n");
		sb.append("	return this.props;\n");
		sb.append("}\n");
		sb.append("\n");
		sb.append("public void startElement(String uri, String localName, String qName,\n");
		sb.append("		Attributes attributes) throws SAXException {\n");
		sb.append("	currentValue.delete(0, currentValue.length());\n");
		sb.append("	this.currentName = qName;\n");
		sb.append("}\n");
		sb.append("\n");
		sb.append("public void characters(char[] ch, int start, int length)\n");
		sb.append("		throws SAXException {\n");
		sb.append("	currentValue.append(ch, start, length);\n");
		sb.append("}\n");
		sb.append("\n");
		sb.append("public void endElement(String uri, String localName, String qName)");
		sb.append("		throws SAXException {");
		sb.append("	props.put(qName.toLowerCase(), currentValue.toString().trim());");
		sb.append("}");
		sb.append("}");
		return sb.toString();
	}
}
