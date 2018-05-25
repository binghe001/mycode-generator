package com.lyz.code.infinity.utils;

import com.lyz.code.infinity.domain.Var;

public class VarUtil {
	public static String generateRequestGetParameterString(Var var, Var request){
		String type = var.getVarType().getTypeName();
		switch (type) {
		case "long": return "Long.parseLong(" + request.getVarName() + ".getParameter(\""+var.getVarName()+"\"))";
		case "int": return "Integer.parseInt(" + request.getVarName() + ".getParameter(\""+var.getVarName()+"\"))";
		case "float": return "Float.parseFloat(" + request.getVarName() + ".getParameter(\""+var.getVarName()+"\"))";
		case "double": return "Double.parseDouble(" + request.getVarName() + ".getParameter(\""+var.getVarName()+"\"))";
		case "String": return request.getVarName() + ".getParameter(\""+var.getVarName()+"\")";
		case "boolean": return "Boolean.parseBoolean("+request.getVarName()+ ".getParameter(\""+var.getVarName()+"\"))";
		case "BigDecimal": return "new BigDecimal("+request.getVarName() + ".getParameter(\""+var.getVarName()+"\"))";
		case "Timestamp": return "new Timestamp("+request.getVarName() + ".getParameter(\""+var.getVarName()+"\"))";
		case "Date": return "new Date("+request.getVarName() + ".getParameter(\""+var.getVarName()+"\"))";
		default : return null;
		}
	}

}