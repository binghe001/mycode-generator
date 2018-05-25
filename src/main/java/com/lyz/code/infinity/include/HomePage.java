package com.lyz.code.infinity.include;

import com.lyz.code.infinity.domain.Include;

public class HomePage extends Include{
	protected String headTitile = "Home Page";
	protected String pageTitile = "Welcome to the System.";
	public HomePage(){
		super();
		this.fileName = "index.jsp";
		this.packageToken = "";
	}
	
	public HomePage(String headTitile, String pageTitile){
		super();
		this.fileName = "index.jsp";
		this.packageToken = "";
		this.headTitile = headTitile;
		this.pageTitile = pageTitile;
	}

	@Override
	public String generateIncludeString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<%@page contentType=\"text/html\" pageEncoding=\"UTF-8\"%>\n");
		sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n");
		sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
		sb.append("<head>\n");
		sb.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />\n");
        sb.append("<title>"+this.headTitile+"</title>\n");
        sb.append("<link href=\"css/default.css\" rel=\"stylesheet\" type=\"text/css\" />\n");
        sb.append("</head>\n");
        sb.append("<body>\n");
        sb.append("<div id=\"wrapper\">\n");
        sb.append("<jsp:include page=\"include/header.jsp\" />\n");
        sb.append("<!-- end div#header -->\n");
        sb.append("<div id=\"page_wide\">\n");
        sb.append("<div id=\"content\">\n");
        sb.append("<div id=\"welcome\">\n");
        sb.append("&nbsp;&nbsp;"+this.pageTitile+".\n");
        sb.append("</div>\n");
        sb.append("<!-- end div#welcome -->\n");
    	sb.append("</div>\n");
        sb.append("<!-- end div#content -->\n");
        sb.append("<div id=\"sidebar\">\n");
        sb.append("<ul>\n");
        sb.append("<%if (request.getSession().getAttribute(\"isadmin\")!=null && (Boolean)request.getSession().getAttribute(\"isadmin\")){%>\n");
        sb.append("<jsp:include page=\"include/adminnav.jsp\"/>\n");
        sb.append("<%} else { %>\n");
        sb.append("<jsp:include page=\"include/homepagenav.jsp\"/>\n");
        sb.append("<%} %>\n");
        sb.append("<!-- end navigation -->\n");
        sb.append("<jsp:include page=\"include/updates.jsp\"/>\n");
        sb.append("</ul>\n");
        sb.append("</div>\n");
        sb.append("<!-- end div#sidebar -->\n");
        sb.append("<div style=\"clear: both; height: 1px\"></div>\n");
        sb.append("</div>\n");
        sb.append("<jsp:include page=\"include/footer.jsp\" />\n");
        sb.append("</div>\n");
        sb.append("<!-- end div#wrapper -->\n");
        sb.append("</body>\n");
        sb.append("</html>\n");

		return sb.toString();
	}

	public String getHeadTitile() {
		return headTitile;
	}

	public void setHeadTitile(String headTitile) {
		this.headTitile = headTitile;
	}

	public String getPageTitile() {
		return pageTitile;
	}

	public void setPageTitile(String pageTitile) {
		this.pageTitile = pageTitile;
	}
}
