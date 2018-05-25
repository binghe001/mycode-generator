package com.lyz.code.infinity.include;

import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Include;

public class JsonUserNav extends Include{	
	public JsonUserNav(){
		super();
		this.fileName = "jsonusernav.jsp";
		this.packageToken = "";
	}

	@Override
	public String generateIncludeString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<!-- Common Navigation Panel for our site -->\n");
		sb.append("<li id=\"submenu\">\n");
		sb.append("\t<h2><a href=\"../index.jsp\">Homepage</a></h2><br/>\n");
		sb.append("\t<h2><a href=\"../jsp/index.jsp\">Jsp Interface</a></h2><br/>\n");
		sb.append("\t<h2><a>Select an option</a></h2>\n");
		sb.append("\t<ul>\n");
		for (Domain d: this.allDomainList){
			sb.append("\t\t<li><a href=\""+"../jsonjsp/"+d.getPlural().toLowerCase()+".jsp\">"+d.getCapFirstDomainName()+"</a></li>\n");   
		}
		sb.append("\t</ul>\n");
		sb.append("</li>\n");
		return sb.toString();
	}
}
