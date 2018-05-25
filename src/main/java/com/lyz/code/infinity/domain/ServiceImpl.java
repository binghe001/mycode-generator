package com.lyz.code.infinity.domain;

import java.util.Iterator;
import java.util.Set;

import com.lyz.code.infinity.utils.StringUtil;

public class ServiceImpl extends Class {
	protected Domain domain;
	protected Service service;
	protected Dao dao;
	public ServiceImpl(){
		super();
	}	
	
	public ServiceImpl(Domain domain){
		super();
		this.domain = domain;
		this.dao = new Dao(domain);
	}

	public String generateServiceImplString(){
		StringBuilder sb = new StringBuilder();
		if (this.packageToken !=null && !"".equalsIgnoreCase(this.packageToken)) sb.append("package ").append(this.packageToken).append(".serviceimpl;\n\n");
		Set<String> imports = this.generateImportStrings();
		imports.addAll(this.classImports);
		for (String s: imports){
			sb.append("import ").append(s).append(";\n");
		}
		sb.append("\n");

		sb.append("public class ").append(StringUtil.capFirst(this.getDomain().getStandardName())).append("ServiceImpl implements ").append(StringUtil.capFirst(this.getDomain().getStandardName())).append("Service").append("{\n");
		
		if (this.dao.getAnnotations()!=null && this.dao.getAnnotations().size()>0) {
			for (String annotation:this.dao.getAnnotations()){
				sb.append("\t@").append(annotation).append("\n");
			}
		}
		sb.append("\tprotected ").append(StringUtil.capFirst(this.getDomain().getStandardName())).append("Dao dao = new ").append(StringUtil.capFirst(this.getDomain().getStandardName())).append("DaoImpl();\n");
		
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


	public Service getService() {
		return service;
	}


	public void setService(Service service) {
		this.service = service;
	}

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}
	
}
