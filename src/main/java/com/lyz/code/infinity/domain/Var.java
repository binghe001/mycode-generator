package com.lyz.code.infinity.domain;

import com.lyz.code.infinity.utils.StringUtil;

public class Var {
	protected Type varType;
	protected String varName;
	protected String value;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Type getVarType() {
		return varType;
	}
	public void setVarType(Type varType) {
		this.varType = varType;
	}
	public String getVarName() {
		return varName;
	}
	public void setVarName(String varName) {
		this.varName = varName;
	}
	
	public String generateTypeVarString(){
		return this.varType.generateTypeString() + " " + StringUtil.lowerFirst(this.getVarName());
	}
	
	public Var(String varName,Type type){
		super();
		this.varType = type;
		this.varName = varName;
	}
	
	public Var(String varName,Type type,String value){
		super();
		this.varType = type;
		this.varName = varName;
		this.value = value;
	}
}
