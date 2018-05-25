package com.lyz.code.infinity.core;

import com.lyz.code.infinity.domain.ConfigFile;

public class SettingEclipsePrefs extends ConfigFile{
	protected String jsdtScopeContent = "eclipse.preferences.version=1\n"+
			"encoding/<project>=UTF-8";
	
	public SettingEclipsePrefs(){
		super();
		this.topLevel= false;
		this.settingParamFile = true;
		this.standardName = "org.eclipse.core.resources.prefs";
	}

	@Override
	public String generateConfigFileString() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(jsdtScopeContent);
		return sb.toString();
	}
}
