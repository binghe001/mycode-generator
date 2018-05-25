package com.lyz.code.infinity.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.lyz.code.infinity.domain.Method;
import com.lyz.code.infinity.domain.Signature;
import com.lyz.code.infinity.domain.Statement;
import com.lyz.code.infinity.domain.StatementList;
import com.lyz.code.infinity.domain.Type;
import com.lyz.code.infinity.domain.Var;
import com.lyz.code.infinity.utils.StringUtil;

public class NamedUtilMethodGenerator {
	public static Method generateServletDoGetCallProcessRequestMethod(int indent, Var request, Var response){
		Method method = new Method();
		method.setStandardName("doGet");
		method.setReturnType(new Type("void"));
		method.setIsprotected(true);
		Set<String> set = new TreeSet<String>();
		set.add("java.io.IOException");
		set.add("javax.servlet.ServletException");
		set.add("javax.servlet.http.HttpServlet");
		set.add("javax.servlet.http.HttpServletRequest");
		set.add("javax.servlet.http.HttpServletResponse");
		method.setAdditionalImports(set);
		method.setThrowException(true);
		List<String> elist = new ArrayList<String>();
		elist.add("ServletException");
		elist.add("IOException");
		method.setOtherExceptions(elist);
		method.addSignature(new Signature(1,"request",new Type("HttpServletRequest","javax.servlet.http")));
		method.addSignature(new Signature(2,"response",new Type("HttpServletResponse","javax.servlet.http")));
		
		StatementList sl = new StatementList();
		sl.add(new Statement(100L,indent,"processRequest("+request.getVarName() +", "+response.getVarName()+");"));
		method.setMethodStatementList(sl);
		return method;
	}
	
	public static Method generateServletDoPostCallProcessRequestMethod(int indent, Var request, Var response){
		Method method = new Method();
		method.setStandardName("doPost");
		method.setReturnType(new Type("void"));
		method.setIsprotected(true);
		Set<String> set = new TreeSet<String>();
		set.add("java.io.IOException");
		set.add("javax.servlet.ServletException");
		set.add("javax.servlet.http.HttpServlet");
		set.add("javax.servlet.http.HttpServletRequest");
		set.add("javax.servlet.http.HttpServletResponse");
		method.setAdditionalImports(set);
		method.setThrowException(true);
		List<String> elist = new ArrayList<String>();
		elist.add("ServletException");
		elist.add("IOException");
		method.setOtherExceptions(elist);
		method.addSignature(new Signature(1,"request",new Type("HttpServletRequest","javax.servlet.http")));
		method.addSignature(new Signature(2,"response",new Type("HttpServletResponse","javax.servlet.http")));
		
		StatementList sl = new StatementList();
		sl.add(new Statement(100L,indent,"processRequest("+request.getVarName() +", "+response.getVarName()+");"));
		method.setMethodStatementList(sl);
		return method;
	}
	
	public static Method generateGetServletInfoMethod(int indent, String message){
		Method method = new Method();
		method.setStandardName("getServletInfo");
		method.setReturnType(new Type("String"));
		
		StatementList sl = new StatementList();
		sl.add(new Statement(100L,indent,"return \""+message+"\";"));
		method.setMethodStatementList(sl);
		return method;
	}
	
	public static Method generateSetter(String name, Type type){
		Method setter = new Method();
		setter.setStandardName("set"+StringUtil.capFirst(name));
		setter.setReturnType(new Type("void"));
		setter.addSignature(new Signature(1,name,type));
		setter.setContent("\t\tthis."+StringUtil.lowerFirst(name)+" = "+StringUtil.lowerFirst(name)+";\n");
		return setter;
	}
	
	public static Method generateGetter(String name, Type type){	
		Method getter = new Method();
		getter.setStandardName("get"+StringUtil.capFirst(name));
		getter.setReturnType(type);
		getter.setContent("\t\treturn this."+StringUtil.lowerFirst(name)+";\n");
		return getter;
	}
}
