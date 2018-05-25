package com.lyz.code.infinity.core;

import com.lyz.code.infinity.domain.ConfigFile;

public class SettingEclipseSuperTypeName extends ConfigFile{
	protected String content = "Window";
	
	public SettingEclipseSuperTypeName(){
		super();
		this.topLevel= false;
		this.settingParamFile = true;
		this.standardName = "org.eclipse.wst.jsdt.ui.superType.name";
	}

	@Override
	public String generateConfigFileString() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(content);
		return sb.toString();
	}
}
