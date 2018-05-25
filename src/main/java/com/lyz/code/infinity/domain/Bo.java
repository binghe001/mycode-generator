package com.lyz.code.infinity.domain;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Bo extends Class implements Comparable {
	protected List<Method> setters;
	protected List<Method> getters;
	protected String boStr;
	protected List<Domain> domains;
	
	@Override
	public String generateClassString(){
		StringBuilder sb = new StringBuilder();
		if (this.packageToken !=null && !"".equalsIgnoreCase(this.packageToken)) sb.append("package ").append(this.packageToken).append(".bo;\n\n");
		Set<String> imports = this.generateImportStrings();
		for (String s: imports){
			sb.append("import ").append(s).append(";\n");
		}
		sb.append("\n");
		
		if (!this.getStandardName().endsWith("Bo")&&!this.getStandardName().endsWith("bo")&&!this.getStandardName().endsWith("BO")&&!this.getStandardName().endsWith("bO")){
			this.setStandardName(this.getStandardName() + "Bo");
		}
		else if (!this.getStandardName().endsWith("Bo")){
			this.setStandardName(this.getStandardName().substring(this.getStandardName().length(),-2) + "Bo");
		}
		sb.append("public class ");
		sb.append(this.getStandardName()).append(" {\n");
		Iterator it = this.getFields().iterator();

        while (it.hasNext()) {
        	Field f = (Field) it.next();
	        String name = f.getFieldName();
	        String type = f.getFieldType();
	        sb.append("protected ").append(type).append(" ").append(name).append(";\n");
        }
        
        it = this.getFields().iterator();
        while (it.hasNext()) {	
        	Field f = (Field) it.next();
	        String name = f.getFieldName();
	        String type = f.getFieldType();
	        sb.append("public ").append(type).append(" get").append(capFirst(name)).append("(){;\n");
	        sb.append("\t return this.").append(name).append(";\n");
	        sb.append("}\n\n");
	        sb.append("public void set").append(capFirst(name)).append("(").append(type).append(" ").append(name).append("){\n");
	        sb.append("\t this.").append(name).append(" = ").append(name).append(";\n");
	        sb.append("}\n\n");
        }
        sb.append("}");
		return sb.toString();
	}
	
	public String capFirst(String value){
		return value.substring(0, 1).toUpperCase()+value.substring(1);
	}
	
	@Override
	public int compareTo(Object o) {
		String myName = this.getStandardName();
		String otherName = ((Bo)o).getStandardName();
		return myName.compareTo(otherName);
	}
	
	@Override
	public boolean equals(Object o){
		return (equals((Bo)o));
	}
	
	public boolean  equals(Bo o){
		String myName = this.getStandardName();
		String otherName = o.getStandardName();
		return (myName.equalsIgnoreCase(otherName));
	}

	public List<Method> getSetters() {
		return setters;
	}

	public void setSetters(List<Method> setters) {
		this.setters = setters;
	}

	public List<Method> getGetters() {
		return getters;
	}

	public void setGetters(List<Method> getters) {
		this.getters = getters;
	}

	public String getBoStr() {
		return boStr;
	}

	public void setBoStr(String boStr) {
		this.boStr = boStr;
	}

	public List<Domain> getDomains() {
		return domains;
	}

	public void setDomains(List<Domain> domains) {
		this.domains = domains;
	}
	
	public void addField(String name,String type){
		this.fields.add(new Field(name,type));
	}
	
	public void addField(String name,String type,String packageToken){
		this.fields.add(new Field(name,type,packageToken));
	}
	
}
