package com.lyz.code.infinity.include;

import com.lyz.code.infinity.domain.Include;
import com.lyz.code.infinity.utils.StringUtil;
import com.lyz.code.infinity.verb.ListAll;
import com.lyz.code.infinity.verb.ListAllByPage;

public class AdminNav extends Include{
	public AdminNav(){
		super();
		this.fileName = "adminnav.jsp";
		this.packageToken = "";
	}

	@Override
	public String generateIncludeString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<!-- Common Navigation Panel for our site -->\n");
		sb.append("<li id=\"submenu\">\n");
		sb.append("\t<h2>Select an option</h2>\n");
		sb.append("\t<ul>\n");
		for (ListAllByPage lsa : this.allListAllByPageList){
			sb.append("\t\t<li><a href=\""+"../controller/"+StringUtil.lowerFirst(lsa.getVerbName())+"Controller\">"+lsa.getVerbName()+"</a></li>\n");   
		}
		sb.append("\t</ul>\n");
		sb.append("</li>\n");
		return sb.toString();
	}
}
