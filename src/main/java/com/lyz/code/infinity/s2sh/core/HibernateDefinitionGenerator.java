package com.lyz.code.infinity.s2sh.core;

import java.util.ArrayList;
import java.util.List;

import com.lyz.code.infinity.domain.Domain;

public abstract class HibernateDefinitionGenerator {
	protected String dbName;
	protected List<Domain> domains = new ArrayList<Domain>();
	private List<String> contents = new ArrayList<String>();
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName2) {
		dbName = dbName2;
	}
	public List<Domain> getDomains() {
		return domains;
	}
	public void setDomains(List<Domain> domains2) {
		domains = domains2;
	}
	public void addDomain(Domain domain){
		domains.add(domain);
	}
	public List<String> getContents() {
		return contents;
	}
	public void setContents(List<String> contents2) {
		contents = contents2;
	}
	public abstract String generateDBSql() throws Exception;
}
