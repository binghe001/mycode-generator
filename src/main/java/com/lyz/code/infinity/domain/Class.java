package com.lyz.code.infinity.domain;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.lyz.code.infinity.utils.StringUtil;

public class Class implements Comparable {
	protected String standardName;
	protected Set<Method> methods = new TreeSet<Method>();
	protected List<String> tokens;
	protected String classComment;
	protected Set<Field> fields = new TreeSet<Field>();
	protected String packageToken;
	protected Set<String> classAnnotations = new  TreeSet<String>();
	protected Set<String> classImports = new  TreeSet<String>();
	
	public int maxMethodSerial(){
		int maxserial = 0;
		for (Method f:this.methods){
			if (f.getSerial() > maxserial) maxserial = f.getSerial();
		}
		return maxserial;
	}
	
	public int maxFieldSerial(){
		int maxserial = 0;
		for (Field f:this.fields){
			if (f.getSerial() > maxserial) maxserial = f.getSerial();
		}
		return maxserial;
	}

	public Set<String> getClassImports() {
		return classImports;
	}
	public void setClassImports(Set<String> classImports) {
		this.classImports = classImports;
	}
	
	public void addClassImports(String str){
		this.classImports.add(str);
	}
	public Set<String> getClassAnnotations() {
		return classAnnotations;
	}
	
	public void addClassAnnotation(String annotation){
		this.classAnnotations.add(annotation);
	}
	public void setClassAnnotations(Set<String> classAnnotations) {
		this.classAnnotations = classAnnotations;
	}
	public Set<Method> getMethods() {
		return methods;
	}
	public void setMethods(Set<Method> methods) {
		this.methods = methods;
	}
	public List<String> getTokens() {
		return tokens;
	}
	public void setTokens(List<String> tokens) {
		this.tokens = tokens;
	}
	public String getClassComment() {
		return classComment;
	}
	public void setClassComment(String classComment) {
		this.classComment = classComment;
	}
	public Set<Field> getFields() {
		return fields;
	}
	public void setFields(Set<Field> fields) {
		this.fields = fields;
	}
	public void addField(Field field){
		int serial = this.maxFieldSerial() + 100;
		field.setSerial(serial);
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
	
	public String getStandardName() {
		return StringUtil.capFirst(standardName);
	}
	public void setStandardName(String standardName) {
		this.standardName = StringUtil.capFirst(standardName);
	}
	
	public String generateClassString(){
		StringBuilder sb = new StringBuilder();
		if (this.packageToken !=null && !"".equalsIgnoreCase(this.packageToken)) sb.append("package ").append(this.packageToken).append(";\n\n");
		this.classImports.addAll(this.generateImportStrings());
		for (String s: this.classImports){
			sb.append("import ").append(s).append(";\n");
		}
		sb.append("\n");
	
		for (String s: classAnnotations){
			sb.append("@").append(s).append("\n");
		}
		sb.append("public class ").append(this.getStandardName()).append(" {\n");		
		
		//generate fields notions
		Iterator it = this.getFields().iterator();
		  while (it.hasNext()) {	
		        Field field = (Field) it.next();
		        String fieldName = field.getFieldName();
		        String fieldType = field.getFieldType();
		        Set<String> annoSet = field.getAnnotations(); 
		        if (annoSet!=null &&annoSet.size()>0){
		        	for(String str : annoSet){
		        		sb.append("@").append(str).append("\n");
		        	}
		        	if (annoSet.size() > 0) sb.substring(0, sb.length()-1);
		        }
		        sb.append("\tprotected ").append(fieldType).append(" ").append(fieldName).append(";\n");
	        }
		  //generate getter setter notions
		  it = this.getFields().iterator();
		  while (it.hasNext()) {	
		        Field field = (Field) it.next();
		        String fieldName = field.getFieldName();
		        String fieldType = field.getFieldType();
		        // generate setters
		        sb.append("\tpublic void set").append(StringUtil.capFirst(fieldName)).append("(").append(fieldType).append(" ").append(StringUtil.lowerFirst(fieldName)).append("){\n");
		        sb.append("\t\tthis.").append(StringUtil.lowerFirst(fieldName)).append(" = ").append(StringUtil.lowerFirst(fieldName)).append(";\n");
		        sb.append("\t}\n\n");
		        // generate getters
		        sb.append("\tpublic ").append(fieldType).append(" get").append(StringUtil.capFirst(fieldName)).append("(){;\n");
		        sb.append("\t\treturn this.").append(StringUtil.lowerFirst(fieldName)).append(";\n");
		        sb.append("\t}\n\n");
	        }
		  
		  Iterator it2 = this.getMethods().iterator();
		  while (it2.hasNext()){
			  sb.append(((Method)it2.next()).generateMethodString());
		  }
		  sb.append("}\n");
		return sb.toString();
	}

	public ValidateInfo validate(){
		ValidateInfo info = new ValidateInfo();
		info.setSuccess(true);
		for (Field f: this.fields){
			String type = f.getFieldType();
			if (!"int".equals(type)&&!"long".equalsIgnoreCase(type)&&!"byte".equals(type)&&!"String".equals(type)&&
				!"float".equalsIgnoreCase(type)&&!"double".equalsIgnoreCase(type) &&!"boolean".equalsIgnoreCase(type)&&!"double".equalsIgnoreCase(type)){
				if (f.getPackageToken()==null || "".equals(f.getPackageToken())){
					info.addCompileError("Field "+f.getFieldName()+ " lack packagetoken.");
					info.setSuccess(false);
				}
			}
		}	
		return info;
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
	
	@Override
	public int compareTo(Object o) {
		String myName = this.getStandardName();
		String otherName = ((Class)o).getStandardName();
		return myName.compareTo(otherName);
	}
}
