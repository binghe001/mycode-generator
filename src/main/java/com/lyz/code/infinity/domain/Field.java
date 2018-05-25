package com.lyz.code.infinity.domain;

import java.util.ArrayList;
import java.util.TreeSet;

import com.lyz.code.infinity.utils.StringUtil;

import java.util.List;
import java.util.Set;

public class Field implements Comparable<Object>  {
	protected int serial = 0;
	protected Type fieldType = new Type();
	protected String fieldName;
	protected String fieldComment;
	protected String fieldValue;
	protected List<String> tokens = new ArrayList<String>();
	protected Set<String> annotations = new TreeSet<String>();	
	
	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public Set<String> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(Set<String> annotations) {
		this.annotations = annotations;
	}
	
	public void addAnnotation(String annotation){
		this.annotations.add(annotation);
	}

	public void setFieldType(Type fieldType) {
		this.fieldType = fieldType;
	}


	public String getFieldComment() {
		return fieldComment;
	}

	public void setFieldComment(String fieldComment) {
		this.fieldComment = fieldComment;
	}

	public List<String> getTokens() {
		return tokens;
	}

	public void setTokens(List<String> tokens) {
		this.tokens = tokens;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Field() {
		super();
	}

	public Field(String fieldName, Type type) {
		super();
		this.fieldName = fieldName;
		this.fieldType = type;
	}
	
	public Field(String fieldName, String typeString) {
		super();
		this.fieldName = fieldName;
		this.fieldType = new Type(typeString);
	}
	
	public Field(int serial,String fieldName, String typeString) {
		super();
		this.serial = serial;
		this.fieldName = fieldName;
		this.fieldType = new Type(typeString);
	}
	
	public Field(int serial,String fieldName, Type type) {
		super();
		this.serial = serial;
		this.fieldName = fieldName;
		this.fieldType = type;
	}
	
	public Field(String fieldName,String typeString,String packageToken){
		super();
		this.fieldName = fieldName;
		this.fieldType = new Type(typeString, packageToken);
	}
	
	public Field(String fieldName,String typeString,String packageToken, String fieldValue){
		super();
		this.fieldName = fieldName;
		this.fieldType = new Type(typeString, packageToken);
		this.fieldValue = fieldValue;
	}
	
	
	public Field(int serial,String fieldName, Type type,String packageToken) {
		super();
		this.serial = serial;
		this.fieldName = fieldName;
		this.fieldType = type;
		this.setPackageToken(packageToken);
	}
	
	@Override
	public int compareTo(Object o) {
		if (this.getSerial() > ((Field)o).getSerial()) return 1;
		else if (this.getSerial() == ((Field)o).getSerial()) return 0;
		else return -1;
	}
	
	@Override
	public boolean equals(Object o){
		return this.getFieldName().equals(((Field)o).getFieldName());
	}
	
	public String getFieldTableColumName(){
		StringBuilder sb = new StringBuilder(this.fieldName);
		StringBuilder sb0 = new StringBuilder("");
		boolean continueCap = false;
		for(int i=0; i < sb.length(); i++){
			char ch = sb.charAt(i);
			if (ch<='Z'&& ch>='A'&&i>0&&!continueCap){
				sb0.append("_").append((""+ch).toLowerCase());
				continueCap = true;
			}else if (ch<='Z'&& ch>='A'&&i==0){
				sb0.append((""+ch).toLowerCase());
				continueCap = true;
			} else if (ch<='Z'&& ch>='A'&&continueCap){
				sb0.append((""+ch).toLowerCase());
			}else if (ch<='z'&& ch>='a') {
				sb0.append(ch);
				continueCap = false;
			}else {
				sb0.append(ch);
			}
		}
		return sb0.toString();
	}
	
	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

	public String getFieldType() {
		return fieldType.getTypeName();
	}

	public void setFieldType(String fieldType) {
		this.fieldType = new Type(fieldType);
	}
	
	public void setPackageToken(String packageToken){
		this.fieldType.setPackageToken(packageToken);
	}
	
	public String getPackageToken(){
		return this.fieldType.getPackageToken();
	}
	
	public String getGetterCall(){
		if (fieldType.getTypeName().equalsIgnoreCase("boolen")) return "is"+StringUtil.capFirst(this.getFieldName())+"()";
		else return "get"+StringUtil.capFirst(this.getFieldName())+"()";
	}
	
	public String getSetterCallName(){
		return "set"+StringUtil.capFirst(this.getFieldName());
	}
	
	public String getLowerFirstFieldName(){
		return StringUtil.lowerFirst(this.getFieldName());
	}
	
	public String getCapFirstFieldName(){
		return StringUtil.capFirst(this.getFieldName());
	}

	public Type getRawType() {
		return this.fieldType;
	}
}
