package com.lyz.code.infinity.domain;

public class ProjectUtil extends Util{
	public ProjectUtil(){
		super();
		super.fileName = ".project";
	}
	@Override
	public String generateUtilString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sb.append("<projectDescription>\n");
		sb.append("<name>ShipManagement3</name>\n");
		sb.append("<comment></comment>\n");
		sb.append("<projects>\n");
		sb.append("</projects>\n");
		sb.append("<buildSpec>\n");
		sb.append("<buildCommand>\n");
		sb.append("<name>org.eclipse.jdt.core.javabuilder</name>\n");
		sb.append("<arguments>\n");
		sb.append("</arguments>\n");
		sb.append("</buildCommand>\n");
		sb.append("<buildCommand>\n");
		sb.append("<name>org.eclipse.wst.common.project.facet.core.builder</name>\n");
		sb.append("<arguments>\n");
		sb.append("</arguments>\n");
		sb.append("</buildCommand>\n");
		sb.append("<buildCommand>\n");
		sb.append("<name>org.eclipse.wst.validation.validationbuilder</name>\n");
		sb.append("<arguments>\n");
		sb.append("</arguments>\n");
		sb.append("</buildCommand>\n");
		sb.append("</buildSpec>\n");
		sb.append("<natures>\n");
		sb.append("<nature>org.eclipse.jem.workbench.JavaEMFNature</nature>\n");
		sb.append("<nature>org.eclipse.wst.common.modulecore.ModuleCoreNature</nature>\n");
		sb.append("<nature>org.eclipse.wst.common.project.facet.core.nature</nature>\n");
		sb.append("<nature>org.eclipse.jdt.core.javanature</nature>\n");
		sb.append("</natures>\n");
		sb.append("</projectDescription>\n");

		return sb.toString();
	}

}
