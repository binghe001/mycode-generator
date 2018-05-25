package com.lyz.code.infinity.core;

import com.lyz.code.infinity.domain.ConfigFile;

public class SettingWstComponentXml extends ConfigFile{
	protected String xmlDefinition = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
	protected String projectName = "PeaceWingGenerated";
	protected String content() { return  "<project-modules id=\"moduleCoreId\" project-version=\"1.5.0\">\n"+
		    "<wb-module deploy-name=\""+this.getProjectName()+"\">\n"+
		        "\t<wb-resource deploy-path=\"/\" source-path=\"/WebContent\" tag=\"defaultRootSource\"/>\n"+
		        "\t<wb-resource deploy-path=\"/WEB-INF/classes\" source-path=\"/src\"/>\n"+
		        "\t<property name=\"context-root\" value=\""+this.getProjectName()+"\"/>\n"+
		        "\t<property name=\"java-output-path\" value=\"/"+this.getProjectName()+"/build/classes\"/>\n"+
		    "</wb-module>\n"+
		    "</project-modules>";
	}
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public SettingWstComponentXml(){
		super();
		this.topLevel= false;
		this.settingParamFile = true;
		this.standardName = "org.eclipse.wst.common.component";
	}

	@Override
	public String generateConfigFileString() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(xmlDefinition).append(content());
		return sb.toString();
	}
}
