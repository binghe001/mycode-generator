package com.lyz.code.infinity.domain;

public class Noun extends Domain{
	protected long nounId;
	protected long domainId;
	protected String nounName;
	protected Domain domain;
	protected long namingId;
	protected Naming naming;
	protected String nounToken;
	protected String nounComment;
	protected String nounContent;
	public long getNounId() {
		return nounId;
	}
	public void setNounId(long nounId) {
		this.nounId = nounId;
	}

	public String getNounName() {
		return nounName;
	}
	public void setNounName(String nounName) {
		this.nounName = nounName;
	}
	public Domain getDomain() {
		return domain;
	}
	public void setDomain(Domain domain) {
		this.domain = domain;
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
	public String getNounToken() {
		return nounToken;
	}
	public void setNounToken(String nounToken) {
		this.nounToken = nounToken;
	}
	public String getNounComment() {
		return nounComment;
	}
	public void setNounComment(String nounComment) {
		this.nounComment = nounComment;
	}
	public String getNounContent() {
		return nounContent;
	}
	public void setNounContent(String nounContent) {
		this.nounContent = nounContent;
	}
}
