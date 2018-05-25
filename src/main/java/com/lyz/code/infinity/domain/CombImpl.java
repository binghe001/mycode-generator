package com.lyz.code.infinity.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.lyz.code.infinity.utils.StringUtil;

public class CombImpl extends Class {
	protected Bo bo;
	protected Comb comb;
	protected List<Service> services = new ArrayList<Service>();
	
	public String generateDaoImplString(){
		StringBuilder sb = new StringBuilder();
		if (this.packageToken !=null && !"".equalsIgnoreCase(this.packageToken)) sb.append("package ").append(this.packageToken).append(".combimpl;\n\n");
		Set<String> imports = this.generateImportStrings();
		for (String s: imports){
			sb.append("import ").append(s).append(";\n");
		}
		sb.append("\n");
		
		sb.append("public class ").append(StringUtil.capFirst(this.getBo().getStandardName())).append("CombImpl{\n");
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

	public ValidateInfo validate(){
		ValidateInfo info = new ValidateInfo();
		info.setSuccess(true);
		return info;
	}

	public Bo getBo() {
		return bo;
	}

	public void setBo(Bo bo) {
		this.bo = bo;
	}

	public Comb getComb() {
		return comb;
	}

	public void setComb(Comb comb) {
		this.comb = comb;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}


}
