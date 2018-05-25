package com.lyz.code.infinity.core;

import com.lyz.code.infinity.domain.ConfigFile;

public class SettingEclipseWstFacetXml extends ConfigFile{
	protected String xmlDefinition = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
	protected String eclipseWstFacetContent =  "<faceted-project>\n"+
		  "<runtime name=\"Apache Tomcat v8.0\"/>\n"+
		  "<fixed facet=\"wst.jsdt.web\"/>\n"+
		  "<fixed facet=\"jst.web\"/>\n"+
		  "<fixed facet=\"java\"/>\n"+
		  "<installed facet=\"jst.web\" version=\"3.1\"/>\n"+
		  "<installed facet=\"wst.jsdt.web\" version=\"1.0\"/>\n"+
		  "<installed facet=\"java\" version=\"1.7\"/>\n"+
		  "</faceted-project>\n";
	public SettingEclipseWstFacetXml(){
		super();
		this.topLevel= false;
		this.settingParamFile = true;
		this.standardName = "org.eclipse.wst.common.project.facet.core.xml";
	}

	@Override
	public String generateConfigFileString() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(xmlDefinition).append(eclipseWstFacetContent);
		return sb.toString();
	}
	

}
