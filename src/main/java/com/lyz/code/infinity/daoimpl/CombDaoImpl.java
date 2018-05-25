package com.lyz.code.infinity.daoimpl;

import java.util.Iterator;
import java.util.Set;

import com.lyz.code.infinity.domain.Bo;
import com.lyz.code.infinity.domain.Class;
import com.lyz.code.infinity.domain.Comb;
import com.lyz.code.infinity.domain.Dao;
import com.lyz.code.infinity.domain.Field;
import com.lyz.code.infinity.domain.Method;
import com.lyz.code.infinity.domain.ValidateInfo;
import com.lyz.code.infinity.utils.StringUtil;

public class CombDaoImpl extends Class {
	protected Bo bo;
	protected Comb comb;
	protected Dao dao;
	
	public String generateDaoImplString(){
		StringBuilder sb = new StringBuilder();
		if (this.packageToken !=null && !"".equalsIgnoreCase(this.packageToken)) sb.append("package ").append(this.packageToken).append(";\n\n");
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

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}
}
