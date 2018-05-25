package com.lyz.code.infinity.domain;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import com.lyz.code.infinity.exception.ValidateException;
import com.lyz.code.infinity.utils.StringUtil;

public class Domain extends Class implements Comparable {
	protected Set<Method> setters = new TreeSet<Method>();
	protected Set<Method> getters = new TreeSet<Method>();
	protected String domainStr;
	protected Field domainId;
	protected Field domainName;
	protected Field active;
	protected String plural; 
	protected String dbPrefix = "";
	
	public int maxSerial(){
		int maxserial = 0;
		for (Field f:this.fields){
			if (f.getSerial() > maxserial) maxserial = f.getSerial();
		}
		return maxserial;
	}

	public Field getDomainId() {
		return domainId;
	}
	public void setDomainId(Field domainId) {
		this.domainId = domainId;
		this.domainId.setSerial(this.maxSerial() + 300);
	}
	
	public void addField(String name,String type){
		if ("active".equals(name)) {
			this.active = new Field(name, "boolean");
			this.active.setSerial(this.maxSerial() + 200);
			return;
		}
		if (name.equals(StringUtil.lowerFirst(this.getStandardName())+"Name")) {
			this.domainName = new Field(name, "String");
			this.domainName.setSerial(this.maxSerial() + 100);
			return;
		}
		if (name.equals(this.getDomainId().getFieldName())) {
			this.domainId = new Field(name, this.getDomainId().getRawType());
			this.domainId.setSerial(this.maxSerial() + 300);
			return;
		}
		this.fields.add(new Field(this.maxSerial()+100,name,type));
	}
	
	public boolean existsSetterByName(String methodName){
		for (Method mySetm:this.setters){
			if (mySetm.getStandardName().equalsIgnoreCase(methodName)) return true;
		}
		return false;
	}
	
	public boolean existsGetterByName(String methodName){
		for (Method mySetm:this.getters){
			if (mySetm.getStandardName().equalsIgnoreCase(methodName)) return true;
		}
		return false;
	}
	
	public void generateSetterGetter(Field f){
		String name = f.getFieldName();
		Type type = new Type(f.getFieldType());
		Method setter = new Method();
		setter.setStandardName("set"+StringUtil.capFirst(name));
		setter.setReturnType(new Type("void"));
		setter.addSignature(new Signature(1,name,type));
		setter.setContent("\t\tthis."+StringUtil.lowerFirst(name)+" = "+StringUtil.lowerFirst(name)+";\n");
		setter.setSerial(f.getSerial());
		if(!existsSetterByName(setter.getStandardName())) this.setters.add(setter);
		
		Method getter = new Method();
		getter.setStandardName("get"+StringUtil.capFirst(name));
		getter.setReturnType(type);
		getter.setContent("\t\treturn this."+StringUtil.lowerFirst(name)+";\n");
		getter.setSerial(f.getSerial()+50);
		if(!existsGetterByName(getter.getStandardName())) this.getters.add(getter);
	}
	
	public void addField(String name,String type,String packageToken){
		this.addField(new Field(name,type,packageToken));
	}
	
	@Override
	public void addField(Field f){
		if ("active".equals(f.getFieldName())) {
			this.active = new Field(f.getFieldName(), "boolean");
			this.active.setSerial(this.maxSerial() + 200);
			return;
		}
		if (f.getFieldName().equals(StringUtil.lowerFirst(this.getStandardName())+"Name")) {
			this.domainName = new Field(f.getFieldName(), "String");
			this.domainName.setSerial(this.maxSerial() + 100);
			return;
		}
		if (f.getFieldName().equals(StringUtil.lowerFirst(this.getStandardName())+"Id")) {
			this.domainId = new Field(f.getFieldName(), f.getRawType());
			this.domainId.setSerial(this.maxSerial() + 300);
			return;
		}
		this.fields.add(new Field(this.maxSerial()+100,f.getFieldName(),f.getFieldType()));
	}
	
	@Override
	public String generateClassString(){
		for (Field f:this.getFields()){
			generateSetterGetter(f);
		}
		StringBuilder sb = new StringBuilder();
		if (this.packageToken !=null && !"".equalsIgnoreCase(this.packageToken)) sb.append("package ").append(this.packageToken).append(".domain;\n\n");
		this.classImports.addAll(this.generateImportStrings());
		for (String s: this.classImports){
			sb.append("import ").append(s).append(";\n");
		}
		sb.append("\n");
		
		for (String s: this.classAnnotations){
			sb.append("@").append(s).append("\n");
		}
		sb.append("public class ");
		sb.append(capFirst(this.standardName) + " {\n");
		Iterator it =  this.getFields().iterator();

        while (it.hasNext()) {
        	Field f = (Field)it.next();
	        String name = f.getFieldName();
	        String type = f.getFieldType();
	        sb.append("\tprotected ").append(type).append(" ").append(lowerFirst(name) + ";\n");
        }
        sb.append("\n");
        
        for(Method m: this.getters){
        	sb.append(m.generateMethodString());
        	sb.append("\n");
        }
        
        for(Method m: this.setters){
        	sb.append(m.generateMethodString());
        	sb.append("\n");
        }        

        it = this.methods.iterator();
        while (it.hasNext()){
        	sb.append(((Method)it.next()).generateMethodString());
        	sb.append("\n");
        }
        sb.append("}\n");
		return sb.toString();
	}
	
	public String capFirst(String value){
		return value.substring(0, 1).toUpperCase()+value.substring(1);
	}
	
	public String lowerFirst(String value){
		return value.substring(0, 1).toLowerCase()+value.substring(1);
	}
	
	@Override
	public int compareTo(Object o) {
		String myName = this.getStandardName();
		String otherName = ((Domain)o).getStandardName();
		return myName.compareTo(otherName);
	}
	
	@Override
	public boolean equals(Object o){
		return (this.compareTo(o)==0);
	}
	public Set<Method> getSetters() {
		return setters;
	}
	public void setSetters(Set<Method> setters) {
		this.setters = setters;
	}
	public Set<Method> getGetters() {
		return getters;
	}
	public void setGetters(Set<Method> getters) {
		this.getters = getters;
	}
	public String getDomainStr() {
		return domainStr;
	}
	public void setDomainStr(String domainStr) {
		this.domainStr = domainStr;
	}
	public Field getDomainName() {
		return domainName;
	}
	public void setDomainName(Field domainName) {
		this.domainName = domainName;
	}
	public Field getActive() {
		return this.active;
	}
	public void setActive(Field active) {
		this.active = active;
	}
	public String getPlural() {
		if (this.plural == null || "".equals(this.plural))
			return StringUtil.capFirst(this.getStandardName()) + "s";
		else 
			return StringUtil.capFirst(plural);
	}
	public void setPlural(String plural) {
		this.plural = StringUtil.capFirst(plural);
	}
	
	public Type getType(){
		Type retType = new Type(this.getStandardName(),this.getPackageToken());
		return retType;
	}
	
	@Override
	public Set<Field> getFields(){
		Set<Field> set = new TreeSet<Field>();
		set.addAll(super.getFields());		
		if (this.domainName != null) {
			this.domainName.setSerial(this.maxSerial() + 100);
			set.add(this.domainName);
		}
		if (this.active != null) {
			this.active.setSerial(this.maxSerial() + 200);
			set.add(this.active);
		}
		if (this.domainId != null) {
			this.domainId.setSerial(this.maxSerial() + 300);
			set.add(this.domainId);
		}
		return set;
	}
	
	public Set<Field> getFieldsWithoutId(){
		Set<Field> set = new TreeSet<Field>();
		set.addAll(super.getFields());		
		if (this.domainName != null) {
			this.domainName.setSerial(this.maxSerial() + 100);
			set.add(this.domainName);
		}
		if (this.active != null) {
			this.active.setSerial(this.maxSerial() + 200);
			set.add(this.active);
		}
		return set;
	}
	
	public Set<Field> getFieldsWithoutIdAndActive(){
		Set<Field> set = new TreeSet<Field>();
		set.addAll(super.getFields());		
		if (this.domainName != null) {
			this.domainName.setSerial(this.maxSerial() + 100);
			set.add(this.domainName);
		}
		return set;
	}
	
	public String generateTypeVarString(){
		return StringUtil.capFirst(this.getStandardName()) + " " + StringUtil.lowerFirst(this.getStandardName());
	}
	
	public Field getField(String fieldName){
		for (Field f : this.getFields()){
			if (f.getFieldName().equals(fieldName)){
				return f;
			}
		}
		return null;
	}

	public String getDbPrefix() {
		if (dbPrefix == null && "".equals(dbPrefix)) return "";
		else return dbPrefix;
	}

	public void setDbPrefix(String dbPrefix) {
		this.dbPrefix = dbPrefix;
	}
	
	public String getLowerFirstDomainName(){
		return lowerFirst(this.getStandardName());
	}
	
	public String getLowerFirstChar(){
		return lowerFirst(this.getStandardName()).substring(0,1);
	}
	
	public String getCapFirstDomainName(){
		return capFirst(this.getStandardName());
	}
	
	public String getDomainActiveStr() throws ValidateException{
		if (this.getActive() == null ){
			ValidateException err = new ValidateException("域对象活跃字段设置错误");
			throw err;
		} else {
			if (this.getActive().getFieldName().toLowerCase().equals("deleted") ||this.getActive().getFieldName().toLowerCase().equals("Deleted") || this.getActive().getFieldName().toLowerCase().equals("delete")|| this.getActive().getFieldName().toLowerCase().equals("Delete")) return "false";
			else return "true";
		}
	}
	
	public String getDomainDeletedStr() throws ValidateException{
		if (this.getActive() == null){
			ValidateException err = new ValidateException("域对象活跃字段设置错误");
			throw err;
		} else {
			if (this.getActive().getFieldName().toLowerCase().equals("deleted") ||this.getActive().getFieldName().toLowerCase().equals("Deleted") || this.getActive().getFieldName().toLowerCase().equals("delete")|| this.getActive().getFieldName().toLowerCase().equals("Delete")) return "true";
			else return "false";
		}
	}
}
