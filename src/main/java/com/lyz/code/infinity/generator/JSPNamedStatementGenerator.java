package com.lyz.code.infinity.generator;

import com.lyz.code.infinity.domain.Controller;
import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Field;
import com.lyz.code.infinity.domain.JavascriptMethod;
import com.lyz.code.infinity.domain.Method;
import com.lyz.code.infinity.domain.Statement;
import com.lyz.code.infinity.domain.StatementList;
import com.lyz.code.infinity.domain.Var;
import com.lyz.code.infinity.utils.FieldUtil;
import com.lyz.code.infinity.utils.SqlReflector;
import com.lyz.code.infinity.utils.StringUtil;

public class JSPNamedStatementGenerator {
	public static Statement getJSPStartTag(long serial,int indent){
		try {
			Statement statement = new Statement(serial,indent,"<%");
			return statement;
		} catch (Exception e){
			return null;
		}
	}
	
	public static Statement getJSPEndTag(long serial,int indent){
		try {
			Statement statement = new Statement(serial,indent,"%>");
			return statement;
		} catch (Exception e){
			return null;
		}
	}

	public static Statement getSetContentUTF8(long serial,int indent){
		try {
			Statement statement = new Statement(serial,indent,"<%@page contentType=\"text/html\" pageEncoding=\"UTF-8\"%>");
			return statement;
		} catch (Exception e){
			return null;
		}
	}
	
	public static Statement getHideInputDomainIdStatement(long serial,int indent,Domain domain, Var mydomain){
		try {
			Statement statement = new Statement(serial,indent,"<input type='hidden' name='"+domain.getDomainId().getFieldName()+"' value='<%="+mydomain.getVarName()+".get"+StringUtil.capFirst(domain.getDomainId().getFieldName())+"()%>'/>");
			return statement;
		} catch (Exception e){
			return null;
		}
	}
	
	public static Statement getSetImportStatement(long serial,int indent, Var clazz){
		try {
			Statement statement = new Statement(serial,indent,"<%@page import=\""+clazz.getVarType().getPackageToken()+"."+clazz.getVarType().getTypeName()+"\" %>");
			return statement;
		} catch (Exception e){
			return null;
		}
	}
	
	public static Statement getSetAddFormStatement(long serial,int indent,Domain domain,Controller addController){
		try {
			Statement statement = new Statement(serial,indent,"<form action='../controller/"+StringUtil.lowerFirst(addController.getStandardName())+"' method='post'>");
			return statement;
		} catch (Exception e){
			return null;
		}
	}
	
	public static Statement getFormEndStatement(long serial,int indent){
		Statement statement = new Statement(serial,indent,"</form>");
		return statement;
	}
	
	public static Statement getTdStartStatement(long serial,int indent){
		Statement statement = new Statement(serial,indent,"<td>");
		return statement;
	}
	
	public static Statement getTdEndStatement(long serial,int indent){
		Statement statement = new Statement(serial,indent,"</td>");
		return statement;
	}
	
	public static Statement getTrStartStatement(long serial,int indent){
		Statement statement = new Statement(serial,indent,"<tr>");
		return statement;
	}
	
	public static Statement getTrStartWithIdStatement(long serial,int indent,Var rowid){
		Statement statement = new Statement(serial,indent,"<tr id=\""+rowid.getVarName()+"\">");
		return statement;
	}
	
	public static Statement getTrEndStatement(long serial,int indent){
		Statement statement = new Statement(serial,indent,"</tr>");
		return statement;
	}
	
	public static Statement getTableEndStatement(long serial,int indent){
		Statement statement = new Statement(serial,indent,"</table>");
		return statement;
	}
	
	public static Statement getTableStartStatement(long serial,int indent){
		Statement statement = new Statement(serial,indent,"<table>");
		return statement;
	}
	
	public static Statement getTableWithAAClassStartStatement(long serial,int indent){
		Statement statement = new Statement(serial,indent,"<table class=\"aatable\">\n");
		return statement;
	}
	
	public static Statement getDivEndStatement(long serial,int indent){
		Statement statement = new Statement(serial,indent,"</div>");
		return statement;
	}
	
	public static Statement getDivStartStatement(long serial,int indent){
		Statement statement = new Statement(serial,indent,"<div>");
		return statement;
	}	
	
	public static Statement getSpanEndStatement(long serial,int indent){
		Statement statement = new Statement(serial,indent,"</span>");
		return statement;
	}
	
	public static Statement getSpanStartStatement(long serial,int indent){
		Statement statement = new Statement(serial,indent,"<span>");
		return statement;
	}
	
	public static Statement getBodyEndStatement(long serial,int indent){
		Statement statement = new Statement(serial,indent,"</body>");
		return statement;
	}
	
	public static Statement getBodyStartStatement(long serial,int indent){
		Statement statement = new Statement(serial,indent,"<body>");
		return statement;
	}
	
	public static Statement getHtmlEndStatement(long serial,int indent){
		Statement statement = new Statement(serial,indent,"</html>");
		return statement;
	}
	
	public static Statement getHtmlStartStatement(long serial,int indent){
		Statement statement = new Statement(serial,indent,"<html>");
		return statement;
	}
	
	public static Statement getJavaNotionEndStatement(long serial,int indent){
		Statement statement = new Statement(serial,indent,"%>");
		return statement;
	}
	
	public static Statement getJavaNotionStartStatement(long serial,int indent){
		Statement statement = new Statement(serial,indent,"<%");
		return statement;
	}
	
	public static Statement getLoopEndWithJavaNotionStatement(long serial,int indent){
		Statement statement = new Statement(serial,indent,"<% } %>");
		return statement;
	}
	
	public static Statement getSubmitButtonStatement(long serial,int indent,String buttonValue){
		try {
			Statement statement = new Statement(serial,indent,"<input type='submit' value='"+buttonValue+"' />");
			return statement;
		} catch (Exception e){
			return null;
		}
	}
	
	public static Statement getButtonWithJSMethodStatement(long serial,int indent,String buttonValue, JavascriptMethod method){
		try {
			Statement statement = new Statement(serial,indent,"<input type='button' value='"+buttonValue+"' onclick='"+method.generateStandardMethodCallString()+"'/>");
			return statement;
		} catch (Exception e){
			return null;
		}
	}
	
	public static Statement getBrStatement(long serial,int indent){
		try {
			Statement statement = new Statement(serial,indent,"<br />");
			return statement;
		} catch (Exception e){
			return null;
		}
	}
}

