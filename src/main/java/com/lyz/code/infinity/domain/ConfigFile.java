package com.lyz.code.infinity.domain;

public abstract class ConfigFile implements Comparable<ConfigFile>{
	protected String standardName;
	protected boolean isPutInsideSrcAndClasses = false;
	protected boolean topLevel = false;
	protected boolean settingParamFile = false;
	
	public boolean isSettingParamFile() {
		return settingParamFile;
	}

	public void setSettingParamFile(boolean settingParamFile) {
		this.settingParamFile = settingParamFile;
	}

	public boolean isTopLevel() {
		return topLevel;
	}

	public void setTopLevel(boolean topLevel) {
		this.topLevel = topLevel;
	}

	public boolean isPutInsideSrcAndClasses() {
		return isPutInsideSrcAndClasses;
	}

	public void setPutInsideSrcAndClasses(boolean isPutInsideSrcAndClasses) {
		this.isPutInsideSrcAndClasses = isPutInsideSrcAndClasses;
	}

	public abstract String generateConfigFileString() throws Exception;

	public String getStandardName() {
		return standardName;
	}

	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}
	
	@Override
	public int compareTo(ConfigFile cf) {
		return this.standardName.compareTo(cf.getStandardName());
	}
}