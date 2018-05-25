package com.lyz.code.infinity.domain;

public abstract class Util {
	protected String standardName;
	protected String fileName;
	protected Naming naming;
	protected String content;
	protected String packageToken;
	public String getStandardName() {
		return standardName;
	}
	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}
	public Naming getNaming() {
		return naming;
	}
	public void setNaming(Naming naming) {
		this.naming = naming;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getPackageToken() {
		return packageToken;
	}
	public void setPackageToken(String packageToken) {
		this.packageToken = packageToken;
	}
	
	public abstract String generateUtilString();
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
