package com.lyz.code.infinity.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Interface {
	protected List<Method> methods = new ArrayList<Method>();
	protected String token;
	protected String interfaceComment;
	protected List<Field> fields = new ArrayList<Field>();
	protected String standardName;
	protected String packageToken;
	
	public int maxMethodSerial(){
		int maxserial = 0;
		for (Method f:this.methods){
			if (f.getSerial() > maxserial) maxserial = f.getSerial();
		}
		return maxserial;
	}
	
	public String getStandardName() {
		return standardName;
	}
	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}
	public List<Method> getMethods() {
		return methods;
	}
	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getInterfaceComment() {
		return interfaceComment;
	}
	public void setInterfaceComment(String interfaceComment) {
		this.interfaceComment = interfaceComment;
	}
	public List<Field> getFields() {
		return fields;
	}
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	public void addField(Field field){
		this.fields.add(field);
	}
	
	public void addMethod(Method method){
		int serial = this.maxMethodSerial() + 100;
		method.setSerial(serial);
		this.methods.add(method);
	}
	
	public void addMethod(Method method,int serial){
		method.setSerial(serial);
		this.methods.add(method);
	}
	
	public String generateInterfaceString(){
		StringBuilder sb = new StringBuilder();
		if (this.packageToken !=null && !"".equalsIgnoreCase(this.packageToken)) sb.append("package ").append(this.packageToken).append(";\n\n");
		Set<String> imports = this.generateImportStrings();
		for (String s: imports){
			sb.append("import ").append(s).append(";\n");
		}
		sb.append("\n");
		
		sb.append("public interface ").append(this.getStandardName()).append("{\n");
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
	
	public Set<String> generateImportStrings(){
		Set<String> imports = new TreeSet<String>();
//		for (Field f:this.fields){
//			if (f.getPackageToken()!=null && !"".equals(f.getPackageToken())){
//				imports.add(f.getPackageToken()+"."+f.getFieldType());
//			}
//		}
		for (Method m:this.methods){
//			for (Signature s : m.signatures){
//				if (s.getPackageToken()!=null && !"".equals(s.getPackageToken())){
//					imports.add(s.getPackageToken()+"."+s.getType());
//				}
//			}
//			if (m.getReturnTypePackageToken()!=null && !"".equals(m.getReturnTypePackageToken())){
//				imports.add(m.getReturnTypePackageToken()+"."+m.getReturnType());
//			}
			imports.addAll(m.getAdditionalImports());
		}
		return imports;
	}
	
	public String getPackageToken() {
		return packageToken;
	}
	public void setPackageToken(String packageToken) {
		this.packageToken = packageToken;
	}
}
