package com.lyz.code.infinity.core;

import com.lyz.code.infinity.domain.ConfigFile;

public class DotProjectXml extends ConfigFile{
	protected String xmlDefinition = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
	protected String projectName = "PeaceWingGenerated";
	protected String projectContent() {
		return "<projectDescription>\n"+
			"<name>"+this.getProjectName()+"</name>\n"+
			"<comment></comment>\n"+
			"<projects>\n"+
			"</projects>\n"+
			"<buildSpec>\n"+
				"\t<buildCommand>\n"+
					"\t\t<name>org.eclipse.wst.jsdt.core.javascriptValidator</name>\n"+
					"\t\t<arguments>\n"+
					"\t\t</arguments>\n"+
				"\t</buildCommand>\n"+
				"\t<buildCommand>\n"+
					"\t\t<name>org.eclipse.jdt.core.javabuilder</name>\n"+
					"\t\t<arguments>\n"+
					"\t\t</arguments>\n"+
				"\t</buildCommand>\n"+
				"\t<buildCommand>\n"+
					"\t\t<name>org.eclipse.wst.common.project.facet.core.builder</name>\n"+
					"\t\t<arguments>\n"+
					"\t\t</arguments>\n"+
				"\t</buildCommand>\n"+
				"\t<buildCommand>\n"+
					"\t\t<name>org.eclipse.wst.validation.validationbuilder</name>\n"+
					"\t\t<arguments>\n"+
					"\t\t</arguments>\n"+
				"\t</buildCommand>\n"+
			"</buildSpec>\n"+
			"<natures>\n"+
				"\t<nature>org.eclipse.jem.workbench.JavaEMFNature</nature>\n"+
				"\t<nature>org.eclipse.wst.common.modulecore.ModuleCoreNature</nature>\n"+
				"\t<nature>org.eclipse.wst.common.project.facet.core.nature</nature>\n"+
				"\t<nature>org.eclipse.jdt.core.javanature</nature>\n"+
				"\t<nature>org.eclipse.wst.jsdt.core.jsNature</nature>\n"+
			"</natures>\n"+
		"</projectDescription>\n";
	}
	
	public DotProjectXml(){
		super();
		this.topLevel = true;
		this.standardName = ".project";
	}
	
	@Override 
	public void setStandardName(String standardName){
	}
	
	@Override
	public String generateConfigFileString() {
		StringBuilder sb = new StringBuilder();
		sb.append(xmlDefinition).append(projectContent());
		return sb.toString();
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
}
