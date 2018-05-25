package com.lyz.code.infinity.domain;

import java.util.Iterator;
import java.util.Set;

import com.lyz.code.infinity.utils.StringUtil;

public class DaoImpl extends Class {
	protected Domain domain;
	protected Dao dao;
	protected Type extendedType;
	
	public Type getExtendedType() {
		return extendedType;
	}


	public void setExtendedType(Type extendedType) {
		this.extendedType = extendedType;
	}


	public String generateDaoImplString(){
		StringBuilder sb = new StringBuilder();
		if (this.packageToken !=null && !"".equalsIgnoreCase(this.packageToken)) sb.append("package ").append(this.packageToken).append(".daoimpl;\n\n");
		Set<String> imports = this.generateImportStrings();
		for (String s: imports){
			sb.append("import ").append(s).append(";\n");
		}
		sb.append("\n");
		
		sb.append("public class ").append(StringUtil.capFirst(this.getDomain().getStandardName())).append("DaoImpl");
		if (extendedType != null) sb.append(" extends ").append(extendedType.getTypeName());
		sb.append(" implements ").append(StringUtil.capFirst(this.getDomain().getStandardName())).append("Dao").append("{\n");
		Iterator it = this.fields.iterator();
		while(it.hasNext()){
			Field f = (Field)it.next();
			sb.append("\tpublic ").append(f.getFieldType()).append(" ").append(f.getFieldName()).append(";\n");
		}
		Iterator it2 = this.methods.iterator();
		while(it2.hasNext()){
			Method m = (Method)it2.next();
			sb.append(m.generateMethodString()).append("\n");
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
	
	public ValidateInfo validate(){
		ValidateInfo info = new ValidateInfo();
		info.setSuccess(true);
		return info;
	}


	public Dao getDao() {
		return dao;
	}


	public void setDao(Dao dao) {
		this.dao = dao;
	}
}
