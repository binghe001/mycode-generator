package com.lyz.code.infinity.domain;

public class Type {
	protected String typeName;
	protected Domain domain;
	protected boolean templated = false;
	protected String packageToken;
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Domain getDomain() {
		return domain;
	}
	public void setDomain(Domain domain) {
		this.domain = domain;
	}
	
	public String generateTypeString(){
		if (this.isTemplated())
			return (this.typeName + "<" + this.domain.getStandardName() + ">");
		else 
			return this.typeName;
	}
	public boolean isTemplated() {
		return templated;
	}
	public void setTemplated(boolean templated) {
		this.templated = templated;
	}
	public Type(){
		super();
	}
	public Type(String typeName, Domain domain,String packageToken){
		super();
		this.typeName = typeName;
		if (domain != null){
			this.domain = domain;
			this.templated = true;
			this.packageToken = packageToken;
		}
	}
	
	public Type(String typeName,String packageToken){
		super();
		this.typeName = typeName;
		this.packageToken = packageToken;
	}
	public String getPackageToken() {
		return packageToken;
	}
	public void setPackageToken(String packageToken) {
		this.packageToken = packageToken;
	}
	public Type(String typeName){
		super();
		this.typeName = typeName;
	}
	public boolean equals(Object o){
		Type rightType = (Type)o;
		if (this.typeName.equals(rightType.typeName) && this.domain.equals(rightType.getDomain()) && this.packageToken.equals(rightType.getPackageToken())){
			return true;
		}
		return false;
	}
	
	public String toString(){
		if (this.domain == null){
			return this.typeName;
		} else {
			if (this.typeName == null || "".equals(this.typeName)){
				return this.domain.getStandardName();
			}else {
				return this.typeName + "<" +this.domain.getStandardName() + ">";
			}
		}
	}
	
	public String toFullString(){
		if (this.domain == null){
			return this.typeName;
		} else {
			String ptoken = this.domain.getPackageToken() == null ?"":this.domain.getPackageToken();
			return (ptoken + this.domain.getStandardName());
		}
	}
	
	public boolean isLong(){
		return this.typeName.equalsIgnoreCase("long");
	}
	
	public boolean isInt(){
		return this.typeName.equals("int") || this.typeName.equals("Integer");
	}
}
