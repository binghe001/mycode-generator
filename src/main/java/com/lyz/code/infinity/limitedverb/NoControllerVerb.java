package com.lyz.code.infinity.limitedverb;

import java.util.ArrayList;
import java.util.List;

import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Method;
import com.lyz.code.infinity.domain.Naming;

public abstract class NoControllerVerb {
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
	
	public abstract Method generateDaoImplMethod();
	public abstract String generateDaoImplMethodString();
	public abstract String generateDaoImplMethodStringWithSerial();
	public abstract Method generateDaoMethodDefinition();
	public abstract String generateDaoMethodDefinitionString();
	public abstract Method generateServiceMethodDefinition();
	public abstract String generateServiceMethodDefinitionString();
	public abstract Method generateServiceImplMethod();
	public abstract String generateServiceImplMethodString();
	public abstract String generateServiceImplMethodStringWithSerial();
	
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
	
	public NoControllerVerb(Domain domain){
		super();
		this.domain = domain;
	}
	
	public NoControllerVerb(){
		super();
	}
}
