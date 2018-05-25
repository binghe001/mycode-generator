package com.lyz.code.infinity.core;

import com.lyz.code.infinity.domain.ConfigFile;

public class SettingEclipseSuperTypeContainer extends ConfigFile{
	protected String content = "org.eclipse.wst.jsdt.launching.baseBrowserLibrary\n";
	
	public SettingEclipseSuperTypeContainer(){
		super();
		this.topLevel= false;
		this.settingParamFile = true;
		this.standardName = "org.eclipse.wst.jsdt.ui.superType.container";
	}

	@Override
	public String generateConfigFileString() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(content);
		return sb.toString();
	}
}
