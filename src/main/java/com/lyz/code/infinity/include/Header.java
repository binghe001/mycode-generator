package com.lyz.code.infinity.include;

import com.lyz.code.infinity.domain.Include;

public class Header extends Include{
	protected String title = "InfinityGPG Generate Results";
	protected String year = "2014";
	
	public Header(){
		super();
		this.fileName = "header.jsp";
		this.packageToken = "";
	}
	
	public Header(String title, String year){
		super();
		this.fileName = "dbconfig.xml";
		this.packageToken = "";
		this.title = title;
		this.year = year;
	}

	@Override
	public String generateIncludeString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<div id=\"header_wide\">\n");
		sb.append("<div id=\"logo\">\n");
		sb.append("\t<h1><a href=\"../jsp/index.jsp\">"+this.title+"</a></h1>\n");
		sb.append("\t<h2><a href=\"../jsp/index.jsp\">Serving Guests Since "+this.year+"</a></h2>\n");
		sb.append("</div>\n");
		sb.append("<!-- end div#logo -->\n");
		sb.append("<!-- end div#menu -->\n");
		sb.append("</div>\n");
		return sb.toString();
	}
}
