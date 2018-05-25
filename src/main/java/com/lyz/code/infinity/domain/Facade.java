package com.lyz.code.infinity.domain;

import java.util.Iterator;
import java.util.Set;

import com.lyz.code.infinity.core.Verb;
import com.lyz.code.infinity.exception.ValidateException;
import com.lyz.code.infinity.generator.NamedUtilMethodGenerator;
import com.lyz.code.infinity.utils.InterVarUtil;
import com.lyz.code.infinity.utils.StringUtil;

public class Facade extends Class{
	protected Verb verb;
	protected Domain domain;
	
	public String generateFacadeString()  throws ValidateException{
		this.verb.setDomain(domain);
		//Method verbMethod = this.verb.generateFacadeMethod();
		//this.addMethod(verbMethod);
		StringBuilder sb = new StringBuilder();
		if (this.packageToken !=null && !"".equalsIgnoreCase(this.packageToken)) sb.append("package ").append(this.packageToken).append(".").append("facade").append(";\n\n");
		Set<String> imports = this.generateImportStrings();
		for (String s: imports){
			sb.append("import ").append(s).append(";\n");
		}
		sb.append("\n");
	
		sb.append("public class ").append(this.getStandardName()).append(" extends HttpServlet{\n");		
		
		//generate fields notions
		Iterator it = this.getFields().iterator();
		  while (it.hasNext()) {	
		        Field field = (Field) it.next();
		        String fieldName = field.getFieldName();
		        String fieldType = field.getFieldType();
		        sb.append("\tprotected ").append(fieldType).append(" ").append(fieldName).append(";\n");
	        }
		  sb.append("\n");
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
			  sb.append(((Method)it2.next()).generateMethodString()).append("\n");
		  }
		  sb.append("}\n");
		return sb.toString();
	}

	public Facade(Verb  verb, Domain domain) throws Exception{
		super();
		verb.setDomain(domain);
		this.verb = verb;
		this.domain = domain;
		this.standardName = verb.getVerbName() + "Facade";
		
		ValidateInfo info = this.validate();
		if (info.success){
			Method processRequest = this.verb.generateFacadeMethod();
			Method doGet = NamedUtilMethodGenerator.generateServletDoGetCallProcessRequestMethod(2, InterVarUtil.Servlet.request, InterVarUtil.Servlet.response);
			Method doPost =  NamedUtilMethodGenerator.generateServletDoPostCallProcessRequestMethod(2, InterVarUtil.Servlet.request, InterVarUtil.Servlet.response);
			Method servletInfo = NamedUtilMethodGenerator.generateGetServletInfoMethod(2, "Powered by Mind Rules.");
			this.addMethod(processRequest);
			this.addMethod(servletInfo);
			this.addMethod(doGet);
			this.addMethod(doPost);
		}else {
			ValidateException e = new ValidateException(info);
			throw e;
		}
	}
	
	public Facade(){super();}
}
