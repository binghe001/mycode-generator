package com.lyz.code.infinity.core;

import com.lyz.code.infinity.domain.ConfigFile;

public class SettingEclipseJdtPrefs extends ConfigFile{
	protected String jdtPrefsContent = "eclipse.preferences.version=1\n"+
			"org.eclipse.jdt.core.compiler.codegen.inlineJsrBytecode=enabled\n"+
			"org.eclipse.jdt.core.compiler.codegen.targetPlatform=1.7\n"+
			"org.eclipse.jdt.core.compiler.compliance=1.7\n"+
			"org.eclipse.jdt.core.compiler.problem.assertIdentifier=error\n"+
			"org.eclipse.jdt.core.compiler.problem.enumIdentifier=error\n"+
			"org.eclipse.jdt.core.compiler.source=1.7";
	
	public SettingEclipseJdtPrefs(){
		super();
		this.topLevel= false;
		this.settingParamFile = true;
		this.standardName = "org.eclipse.jdt.core.prefs";
	}

	@Override
	public String generateConfigFileString() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(jdtPrefsContent);
		return sb.toString();
	}
}
