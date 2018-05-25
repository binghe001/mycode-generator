package com.lyz.code.infinity.core;

import java.util.ArrayList;
import java.util.List;

import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Method;
import com.lyz.code.infinity.domain.Naming;
import com.lyz.code.infinity.limitedverb.DaoOnlyVerb;
import com.lyz.code.infinity.limitedverb.NoControllerVerb;

public abstract class Verb {
	protected long verbId;
	protected long methodId;
	protected String verbName;
	protected Method method;
	protected long namingId;
	protected Naming naming;
	protected String verbToken;
	protected String verbComment;
	protected String verbContent;
	protected Domain domain;
	protected String verbReturnType;
	protected String verbReturnTypePackageToken;
	protected List<String> additionalImports = new ArrayList<String>();
	protected List<NoControllerVerb> noControllerVerbs = new ArrayList<NoControllerVerb>();
	protected List<DaoOnlyVerb> daoOnlyVerbs = new ArrayList<DaoOnlyVerb>();
	public List<NoControllerVerb> getNoControllerVerbs() {
		return noControllerVerbs;
	}
	public void setNoControllerVerbs(List<NoControllerVerb> noControllerVerbs) {
		this.noControllerVerbs = noControllerVerbs;
	}
	public List<DaoOnlyVerb> getDaoOnlyVerbs() {
		return daoOnlyVerbs;
	}
	public void setDaoOnlyVerbs(List<DaoOnlyVerb> daoOnlyVerbs) {
		this.daoOnlyVerbs = daoOnlyVerbs;
	}
	public long getVerbId() {
		return verbId;
	}
	public void setVerbId(long verbId) {
		this.verbId = verbId;
	}
	public long getMethodId() {
		return methodId;
	}
	public void setMethodId(long methodId) {
		this.methodId = methodId;
	}
	public String getVerbName() {
		return verbName;
	}
	public void setVerbName(String verbName) {
		this.verbName = verbName;
	}
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	public long getNamingId() {
		return namingId;
	}
	public void setNamingId(long namingId) {
		this.namingId = namingId;
	}
	public Naming getNaming() {
		return naming;
	}
	public void setNaming(Naming naming) {
		this.naming = naming;
	}
	public String getVerbToken() {
		return verbToken;
	}
	public void setVerbToken(String verbToken) {
		this.verbToken = verbToken;
	}
	public String getVerbComment() {
		return verbComment;
	}
	public void setVerbComment(String verbComment) {
		this.verbComment = verbComment;
	}
	public String getVerbContent() {
		return verbContent;
	}
	public void setVerbContent(String verbContent) {
		this.verbContent = verbContent;
	}
	
	public abstract Method generateDaoImplMethod()  throws Exception;
	public abstract String generateDaoImplMethodString() throws Exception;
	public abstract String generateDaoImplMethodStringWithSerial() throws Exception;
	public abstract Method generateDaoMethodDefinition() throws Exception;
	public abstract String generateDaoMethodDefinitionString() throws Exception;
	public abstract Method generateServiceMethodDefinition() throws Exception;
	public abstract String generateServiceMethodDefinitionString() throws Exception;
	public abstract Method generateServiceImplMethod() throws Exception;
	public abstract String generateServiceImplMethodString() throws Exception;
	public abstract String generateServiceImplMethodStringWithSerial() throws Exception;
	public abstract Method generateControllerMethod() throws Exception;
	public abstract String generateControllerMethodString() throws Exception;
	public abstract String generateControllerMethodStringWithSerial() throws Exception;
	public abstract Method generateFacadeMethod() throws Exception;
	public abstract String generateFacadeMethodString() throws Exception;
	public abstract String generateFacadeMethodStringWithSerial() throws Exception;
	
	public Domain getDomain() {
		return domain;
	}
	public void setDomain(Domain domain) {
		this.domain = domain;
	}
	public String getVerbReturnType() {
		return verbReturnType;
	}
	public void setVerbReturnType(String verbReturnType) {
		this.verbReturnType = verbReturnType;
	}
	public String getVerbReturnTypePackageToken() {
		return verbReturnTypePackageToken;
	}
	public void setVerbReturnTypePackageToken(String verbReturnTypePackageToken) {
		this.verbReturnTypePackageToken = verbReturnTypePackageToken;
	}
	public List<String> getAdditionalImports() {
		return additionalImports;
	}
	public void setAdditionalImports(List<String> additionalImports) {
		this.additionalImports = additionalImports;
	}
	
	public Verb(Domain domain){
		super();
		this.domain = domain;
	}
	
	public Verb(){
		super();
	}
}
