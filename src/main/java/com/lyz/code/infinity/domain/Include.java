package com.lyz.code.infinity.domain;

import java.util.ArrayList;
import java.util.List;

import com.lyz.code.infinity.verb.ListAll;
import com.lyz.code.infinity.verb.ListAllByPage;

public abstract class Include {
	protected String standardName;
	protected String fileName;
	protected Naming naming;
	protected String content;
	protected String packageToken;
	protected List<ListAllByPage> allListAllByPageList = new ArrayList<ListAllByPage>();
	protected List<Domain> allDomainList = new ArrayList<Domain>();
	
	public void addDomain(Domain domain){
		for (Domain d: this.allDomainList){
			if (d.getStandardName().equals(domain.getStandardName())) {
				return;
			}
		}
		allDomainList.add(domain);
		allListAllByPageList.add(new ListAllByPage(domain));
	}
	
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
	
	public abstract String generateIncludeString();
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
