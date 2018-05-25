package com.lyz.code.infinity.utils;

import com.lyz.code.infinity.domain.Util;
public class EncodingFilter extends Util{
	public EncodingFilter(){
		super();
		this.fileName = "EncodingFilter.java";
		this.standardName = "EncodingFilter";
	}
	
	public EncodingFilter(String packageToken){
		super();
		if (packageToken!=null && !"".equals(packageToken) && packageToken.contains("filter"))
			this.packageToken = packageToken;
		else if (packageToken!=null && !"".equals(packageToken))this.packageToken = packageToken+".filter";
		else packageToken = "";
		this.fileName = "EncodingFilter.java";
		this.standardName = "EncodingFilter";
	}
	
	@Override
	public void setPackageToken(String packageToken){
		if (packageToken.contains("filter"))
			this.packageToken = packageToken;
		else this.packageToken = packageToken+".filter";
	}
	
	@Override
	public String generateUtilString() {
		StringBuilder sb = new StringBuilder("");
		sb.append("package ").append(this.packageToken).append(";\n\n");
		sb.append("import java.io.IOException;\n");
		sb.append("import javax.servlet.Filter;\n");
		sb.append("import javax.servlet.FilterChain;\n");
		sb.append("import javax.servlet.FilterConfig;\n");
		sb.append("import javax.servlet.ServletException;\n");
		sb.append("import javax.servlet.ServletRequest;\n");
		sb.append("import javax.servlet.ServletResponse;\n\n");

		sb.append("public class EncodingFilter implements Filter {\n\n");

		sb.append("\tpublic void destroy() {\n\n");
		sb.append("\t}\n\n");

		sb.append("\tpublic void doFilter(ServletRequest request, ServletResponse response,\n");
		sb.append("\t\t\tFilterChain chain) throws IOException, ServletException {\n");
		sb.append("\t\trequest.setCharacterEncoding(\"utf-8\");\n");
		sb.append("\t\tresponse.setCharacterEncoding(\"utf-8\");\n");
		sb.append("\t\tchain.doFilter(request, response);\n");
		sb.append("\t}\n\n");

		sb.append("\tpublic void init(FilterConfig arg0) throws ServletException {\n\n");
		sb.append("\t}\n\n");
		sb.append("}\n");
		
		return sb.toString();
	}
}