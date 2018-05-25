package com.lyz.code.infinity.domain;

public class Agent {
	protected Method verbMethod;
	protected Domain nounDomain;
	
	public String generateAgentString(){
		return null;
	}

	public Method getVerbMethod() {
		return verbMethod;
	}

	public void setVerbMethod(Method verbMethod) {
		this.verbMethod = verbMethod;
	}

	public Domain getNounDomain() {
		return nounDomain;
	}

	public void setNounDomain(Domain nounDomain) {
		this.nounDomain = nounDomain;
	}
}
