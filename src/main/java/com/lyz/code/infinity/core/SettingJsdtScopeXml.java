package com.lyz.code.infinity.core;

import com.lyz.code.infinity.domain.ConfigFile;

public class SettingJsdtScopeXml extends ConfigFile{
	protected String xmlDefinition = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
	protected String content =  "<classpath>\n"+
			"<classpathentry excluding=\"**/bower_components/*|**/node_modules/*|**/*.min.js\" kind=\"src\" path=\"WebContent\"/>\n"+
			"<classpathentry kind=\"con\" path=\"org.eclipse.wst.jsdt.launching.JRE_CONTAINER\"/>\n"+
			"<classpathentry kind=\"con\" path=\"org.eclipse.wst.jsdt.launching.WebProject\">\n"+
				"\t<attributes>\n"+
					"\t\t<attribute name=\"hide\" value=\"true\"/>\n"+
				"\t</attributes>\n"+
			"</classpathentry>\n"+
			"<classpathentry kind=\"con\" path=\"org.eclipse.wst.jsdt.launching.baseBrowserLibrary\"/>\n"+
			"<classpathentry kind=\"output\" path=\"\"/>\n"+
			"</classpath>\n";
	
	public SettingJsdtScopeXml(){
		super();
		this.topLevel= false;
		this.settingParamFile = true;
		this.standardName = ".jsdtscope";
	}

	@Override
	public String generateConfigFileString() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(xmlDefinition).append(content);
		return sb.toString();
	}
	

}
