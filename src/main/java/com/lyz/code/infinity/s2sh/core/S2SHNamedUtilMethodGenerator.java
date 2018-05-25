package com.lyz.code.infinity.s2sh.core;

import com.lyz.code.infinity.domain.Method;
import com.lyz.code.infinity.domain.Signature;
import com.lyz.code.infinity.domain.Statement;
import com.lyz.code.infinity.domain.StatementList;
import com.lyz.code.infinity.domain.Type;
import com.lyz.code.infinity.domain.Var;

public class S2SHNamedUtilMethodGenerator {
	public static Method generateStruts2SetRequestMethod(int indent, Var request, Var domainRequest){
		Method method = new Method();
		method.setStandardName("setRequest");
		method.setReturnType(new Type("void"));
		method.addAdditionalImport("java.util.Map");
		method.setThrowException(false);
		method.addSignature(new Signature(1,"request",new Type("Map<String, Object>","java.util")));
		method.addMetaData("Override");
		
		StatementList sl = new StatementList();
		sl.add(new Statement(100L,indent, domainRequest.getVarName() +" = "+request.getVarName()+";"));
		method.setMethodStatementList(sl);
		return method;
	}
	
	public static Method generateSetResponseJsonMethod(int indent, Var domainRequest, Var jsonMap){
		Method method = new Method();
		method.setStandardName("setResponseJson");
		method.setReturnType(new Type("void"));
		method.addAdditionalImport("java.util.Map");
		method.setThrowException(false);
		method.addSignature(new Signature(1,"responseJson",new Type("Map","java.util")));
		
		StatementList sl = new StatementList();
		sl.add(new Statement(100L,indent, "this."+domainRequest.getVarName() +" = "+jsonMap.getVarName()+";"));
		method.setMethodStatementList(sl);
		return method;
	}
	
	public static Method generateGetResponseJsonMethod(int indent, Var domainRequest, Var jsonMap){
		Method method = new Method();
		method.setStandardName("getResponseJson");
		method.setReturnType(new Type("Map","java.util"));
		method.addAdditionalImport("java.util.Map");
		method.setThrowException(false);
		
		StatementList sl = new StatementList();
		sl.add(new Statement(100L,indent, "return this."+domainRequest.getVarName()+";"));
		method.setMethodStatementList(sl);
		return method;
	}
	
	public static Method generateStruts2PrepareMethod(int indent){
		Method method = new Method();
		method.setStandardName("prepare");
		method.setReturnType(new Type("void"));
		method.setThrowException(true);
		method.addMetaData("Override");
		
		StatementList sl = new StatementList();
		sl.add(new Statement(100L,indent, "// TODO Auto-generated method stub"));
		method.setMethodStatementList(sl);
		return method;
	}

}
