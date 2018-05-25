package com.lyz.code.infinity.utils;

import java.util.Iterator;
import java.util.Map;

import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Field;

public class Reflector{
	public String domainSetValue(Domain domain) throws Exception {
		return null;
	}

	public String resultSetDomainValue(Domain domain,String resultSetName) throws Exception {
		String result = domain.getStandardName() +" "+StringUtil.lowerFirst(domain.getStandardName())+" = new " + domain.getStandardName() + "();\n";
		Iterator it = domain.getFields().iterator();
		
		result += "while (" +resultSetName + ".hasnext()) {\n";
        while (it.hasNext()) {	
	        Field f = (Field) it.next();
	        String fieldName = f.getFieldName();
	        String fieldType = f.getFieldType();
	        result += "\t"+domain.getStandardName()+".set"+StringUtil.capFirst(fieldName)+"("+resultSetName+".get"+StringUtil.capFirst(fieldType)+"(\""+StringUtil.changeDomainFieldtoTableColum(fieldName)+"\"));\n";
        }
        result += "}\n";
        return result;
	}
	
	public String setDomainFromHttpPost(Domain domain) throws Exception {
		String result =  "\t" +domain.getStandardName() + " "+StringUtil.lowerFirst(domain.getStandardName()) + "();\n";
		Iterator it = domain.getFields().iterator();
        while (it.hasNext()) {	
            Field f = (Field) it.next();
	        String fieldName = f.getFieldName();
	        String fieldType = f.getFieldType();
	        if ("String".equalsIgnoreCase(fieldType)) {
	        	result += "\t"+StringUtil.lowerFirst(domain.getStandardName())+".set"+StringUtil.capFirst(fieldName)+"(request.getParameter(\""+fieldName+"\"));\n";
	        } else if ("int".equalsIgnoreCase(fieldType) || "Integer".equalsIgnoreCase(fieldType)) {
	        	result += "\t"+StringUtil.lowerFirst(domain.getStandardName())+".set"+StringUtil.capFirst(fieldName)+"(Integer.parseInt(request.getParameter(\""+fieldName+"\")));\n";
	        } else if ("long".equalsIgnoreCase(fieldType) || "Long".equalsIgnoreCase(fieldType)) {
	        	result += "\t"+StringUtil.lowerFirst(domain.getStandardName())+".set"+StringUtil.capFirst(fieldName)+"(Long.parseLong(request.getParameter(\""+fieldName+"\")));\n";
		    } else if ("double".equalsIgnoreCase(fieldType) || "Double".equalsIgnoreCase(fieldType)) {
	        	result += "\t"+StringUtil.lowerFirst(domain.getStandardName())+".set"+StringUtil.capFirst(fieldName)+"(Double.parseDouble(request.getParameter(\""+fieldName+"\")));\n";
		    }
        }
        return result;
	}

}
