package com.lyz.code.infinity.utils;

import com.lyz.code.infinity.domain.Util;

public class ParseXML extends Util{
	public ParseXML(){
		super();
		this.fileName = "ParseXML.java";
	}
	
	public ParseXML(String packageToken){
		super();
		if (packageToken!=null && !"".equals(packageToken) && packageToken.contains("database"))
			this.packageToken = packageToken;
		else if (packageToken!=null && !"".equals(packageToken))this.packageToken = packageToken+".database";
		else packageToken = "";
		this.fileName = "ParseXML.java";
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

		sb.append("import java.util.Properties;\n");
		sb.append("import javax.xml.parsers.ParserConfigurationException;\n");
		sb.append("import javax.xml.parsers.SAXParser;\n");
		sb.append("import javax.xml.parsers.SAXParserFactory;\n");
		sb.append("import org.xml.sax.SAXException;\n");
		sb.append("import java.net.URL;\n");
		sb.append("\n");
		sb.append("public class ParseXML {\n");
		sb.append("	private Properties props;\n");
		sb.append("\n");
		sb.append("	public Properties getProps() {\n");
		sb.append("		return this.props;\n");
		sb.append("	}\n");
		sb.append("\n");
		sb.append("	public void parse(String filename) {\n");
		sb.append("		ConfigParser handler = new ConfigParser();\n");
		sb.append("		SAXParserFactory factory = SAXParserFactory.newInstance();\n");
		sb.append("		factory.setNamespaceAware(false);\n");
		sb.append("		factory.setValidating(false);\n");
		sb.append("		SAXParser parser = null;\n");
		sb.append("		try {\n");
		sb.append("			parser = factory.newSAXParser();\n");
		sb.append("		} catch (Exception e1) {\n");
		sb.append("			e1.printStackTrace();\n");
		sb.append("		}\n");
		sb.append("		URL confURL = null;\n");
		sb.append("		try {\n");
		sb.append("			confURL = ParseXML.class.getClassLoader().getResource(filename);\n");
		sb.append("		} catch (Exception e) {\n");
		sb.append("			System.out.print(e.toString());\n");
		sb.append("		}\n");
		sb.append("		try { \n");
		sb.append("			parser.parse(confURL.toString(), handler);\n");
		sb.append("			props = handler.getProps();\n");
		sb.append("		} catch (Exception e) {\n");
		sb.append("			System.out.println(e.toString());\n");
		sb.append("		} finally {\n");
		sb.append("			factory = null;\n");
		sb.append("			parser = null;\n");
		sb.append("			handler = null;\n");
		sb.append("		}\n");
		sb.append("	}\n");
		sb.append("}\n");
		sb.append("\n");
		return sb.toString();
	}
}
