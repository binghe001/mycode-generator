package com.lyz.code.infinity.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.lyz.code.infinity.utils.StringUtil;

public class Service extends Interface {
	protected Domain domain;
	protected List<Method> verbMethods = new ArrayList<Method>();
	
	public Service(){
		super();
	}
	
	public Service(Domain domain, List<Method> verbMethods){
		this();
		this.domain = domain;
		this.verbMethods = verbMethods;
		Method addMethod = new Method();
		addMethod.setStandardName("add"+StringUtil.capFirst(domain.getStandardName()));
		addMethod.setReturnType(new Type("boolean"));
		addMethod.addSignature(new Signature(1,StringUtil.lowerFirst(domain.getStandardName()),domain.getType()));
		this.verbMethods.add(addMethod);
		
		Method updateMethod = new Method();
		updateMethod.setStandardName("update"+StringUtil.capFirst(domain.getStandardName()));
		updateMethod.setReturnType(new Type("boolean"));
		updateMethod.addSignature(new Signature(1,StringUtil.lowerFirst(domain.getStandardName()),domain.getType()));
		this.verbMethods.add(updateMethod);
		
		Method deleteMethod = new Method();
		deleteMethod.setStandardName("delete"+StringUtil.capFirst(domain.getStandardName()));
		deleteMethod.setReturnType(new Type("boolean"));
		deleteMethod.addSignature(new Signature(1,StringUtil.lowerFirst(domain.getDomainId().getFieldName()),domain.getType()));
		this.verbMethods.add(deleteMethod);
		
		Method listAllMethod = new Method();
		listAllMethod.setStandardName("delete"+StringUtil.capFirst(domain.getStandardName()));
		listAllMethod.setReturnType(new Type("List",domain,domain.getPackageToken()));
		this.verbMethods.add(listAllMethod);		
	}
	
	public String generateServiceString(){
		StringBuilder sb = new StringBuilder();
		if (this.packageToken !=null && !"".equalsIgnoreCase(this.packageToken)) sb.append("package ").append(this.packageToken).append(".service;\n\n");
		Set<String> imports = this.generateImportStrings();
		for (String s: imports){
			sb.append("import ").append(s).append(";\n");
		}
		sb.append("\n");
		
		sb.append("public interface ").append(StringUtil.capFirst(this.getDomain().getStandardName())).append("Service{\n");
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
	
	public Method getVerbMethodByStandardName(String standardName){
		for (Method m : verbMethods){
			if (m.getStandardName().equalsIgnoreCase(standardName)){
				return m;
			}
		}
		return null;
	}
}
