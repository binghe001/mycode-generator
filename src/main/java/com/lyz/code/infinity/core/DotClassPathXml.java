package com.lyz.code.infinity.core;

import com.lyz.code.infinity.domain.ConfigFile;

public class DotClassPathXml extends ConfigFile{
	protected String xmlDefinition = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";

	protected String classPathContent = "<classpath>\n" +
		"<classpathentry kind=\"src\" path=\"src\"/>\n" +
		"<classpathentry kind=\"con\" path=\"org.eclipse.jst.j2ee.internal.web.container\"/>\n" +
		"<classpathentry kind=\"con\" path=\"org.eclipse.jst.j2ee.internal.module.container\"/>\n" +
		"<classpathentry kind=\"con\" path=\"org.eclipse.jst.server.core.container/org.eclipse.jst.server.tomcat.runtimeTarget/Apache Tomcat v8.0\">\n" +
		"\t<attributes>\n" +
		"\t\t<attribute name=\"owner.project.facets\" value=\"jst.web\"/>\n" +
		"\t</attributes>\n" +
		"</classpathentry>\n" +
		"<classpathentry kind=\"con\" path=\"org.eclipse.jdt.launching.JRE_CONTAINER/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/jdk1.7.0_67\">\n" +
		"\t<attributes>\n" +
		"\t\t<attribute name=\"owner.project.facets\" value=\"java\"/>\n" +
		"\t</attributes>\n" +
		"</classpathentry>\n" +
		"<classpathentry kind=\"output\" path=\"build/classes\"/>\n" +
		"</classpath>\n";
	
	public DotClassPathXml(){
		super();
		this.topLevel= true;
		this.standardName = ".classpath";
	}

	@Override
	public String generateConfigFileString() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(xmlDefinition).append(classPathContent);
		return sb.toString();
	}
	

}
