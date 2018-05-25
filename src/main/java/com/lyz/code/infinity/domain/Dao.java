package com.lyz.code.infinity.domain;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Dao extends Interface {
	protected Domain domain;
	protected Set<String> annotations = new TreeSet<String>();	
	
	public Set<String> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(Set<String> annotations) {
		this.annotations = annotations;
	}
	
	public void addAnnotation(String annotation){
		this.annotations.add(annotation);
	}
	
	public String generateDaoString(){
		StringBuilder sb = new StringBuilder();
		if (this.packageToken !=null && !"".equalsIgnoreCase(this.packageToken)) sb.append("package ").append(this.packageToken).append(".dao;\n\n");
		Set<String> imports = this.generateImportStrings();
		for (String s: imports){
			sb.append("import ").append(s).append(";\n");
		}
		sb.append("\n");
		
		sb.append("public interface ").append(this.getDomain().getStandardName()).append("Dao{\n");
		Iterator it = this.fields.iterator();
		while(it.hasNext()){
			Field f = (Field)it.next();
			sb.append("\tpublic ").append(f.getFieldType()).append(" ").append(f.getFieldName()).append(";\n");
		}
		Iterator it2 = this.methods.iterator();
		while(it2.hasNext()){
			Method m = (Method)it2.next();
			sb.append(m.generateMethodDefinition()).append("\n");
		}			
		sb.append("}\n");
		
		return sb.toString();
	}


	public Domain getDomain() {
		return domain;
	}


	public void setDomain(Domain domain) {
		this.domain = domain;
	}
	
	public boolean validate(){
		return true;
	}
	
	public Dao(){
		super();
	}
	
	public Dao(Domain domain){
		super();
		this.domain = domain;
	}
}
