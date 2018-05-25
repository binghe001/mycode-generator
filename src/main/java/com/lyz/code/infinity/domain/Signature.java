package com.lyz.code.infinity.domain;

public class Signature {
	protected long signatureId;
	protected int position;
	protected Type type;
	protected String name;
	protected String packageToken;
	
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public Type getType() {
		return type;
	}
	public long getSignatureId() {
		return signatureId;
	}
	public void setSignatureId(long signatureId) {
		this.signatureId = signatureId;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Signature(int position,String name, Type type){
		this.position = position;
		this.type = type;
		this.name = name;
	}
	
	public Signature(long signatureId,int position,  String name, Type type){
		this.signatureId = signatureId;
		this.position = position;
		this.type = type;
		this.name = name;
	}
	
	public Signature(){
	}
	
	public boolean equals(Signature signature){
		if (this.type.equals(signature.getType()) && this.name.equals(signature.getName())){
			return true;
		}
		return false;
	}
	public String getPackageToken() {
		return packageToken;
	}
	public void setPackageToken(String packageToken) {
		this.packageToken = packageToken;
	}
	
	public Signature(int position, String name, Type type, String packageToken){
		super();
		this.position = position;
		this.name = name;
		this.type = type;
		this.packageToken = packageToken;
		
	}
	
	public Signature(int position, String name, String typeName){
		super();
		this.position = position;
		this.name = name;
		this.type = new Type(name,typeName);	
	}

	public Var getVar(){
		Var var = new Var(this.name,this.type);
		return var;
	}
}
