package com.lyz.code.infinity.domain;

import java.util.Iterator;
import java.util.Set;

public class Comb extends Interface {
	protected Bo bo;
	
	public String generateCombString(){
		StringBuilder sb = new StringBuilder();
		if (this.packageToken !=null && !"".equalsIgnoreCase(this.packageToken)) sb.append("package ").append(this.packageToken).append(".comb;\n\n");
		Set<String> imports = this.generateImportStrings();
		for (String s: imports){
			sb.append("import ").append(s).append(";\n");
		}
		sb.append("\n");
		
		sb.append("public interface ").append(this.getBo().getStandardName()).append("Comb{\n");
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

	
	public boolean validate(){
		return true;
	}


	public Bo getBo() {
		return bo;
	}


	public void setBo(Bo bo) {
		this.bo = bo;
	}
}
