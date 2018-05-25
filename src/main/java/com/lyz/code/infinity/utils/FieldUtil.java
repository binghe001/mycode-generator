package com.lyz.code.infinity.utils;

import java.math.BigDecimal;

import com.lyz.code.infinity.domain.Field;
import com.lyz.code.infinity.domain.Var;

public class FieldUtil {
	public static String generateRequestGetParameterString(Field field, Var request){
		String type = field.getFieldType();
		switch (type) {
		case "long": return "Long.parseLong(" + request.getVarName() + ".getParameter(\""+field.getFieldName()+"\"))";
		case "int": return "Integer.parseInt(" + request.getVarName() + ".getParameter(\""+field.getFieldName()+"\"))";
		case "float": return "Float.parseFloat(" + request.getVarName() + ".getParameter(\""+field.getFieldName()+"\"))";
		case "double": return "Double.parseDouble(" + request.getVarName() + ".getParameter(\""+field.getFieldName()+"\"))";
		case "String": return request.getVarName() + ".getParameter(\""+field.getFieldName()+"\")";
		case "boolean": return "Boolean.parseBoolean("+request.getVarName()+ ".getParameter(\""+field.getFieldName()+"\"))";
		case "BigDecimal": return "new BigDecimal("+request.getVarName() + ".getParameter(\""+field.getFieldName()+"\"))";
		case "Timestamp": return "new Timestamp("+request.getVarName() + ".getParameter(\""+field.getFieldName()+"\"))";
		case "Date": return "new Date("+request.getVarName() + ".getParameter(\""+field.getFieldName()+"\"))";
		default : return null;
		}
	}

}