package com.lyz.code.infinity.generator;

import java.util.ArrayList;
import java.util.List;

import com.lyz.code.infinity.core.Verb;
import com.lyz.code.infinity.core.Writeable;
import com.lyz.code.infinity.domain.Controller;
import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Field;
import com.lyz.code.infinity.domain.Statement;
import com.lyz.code.infinity.domain.StatementList;
import com.lyz.code.infinity.domain.Type;
import com.lyz.code.infinity.domain.Var;
import com.lyz.code.infinity.exception.ValidateException;
import com.lyz.code.infinity.utils.InterVarUtil;
import com.lyz.code.infinity.utils.StringUtil;
import com.lyz.code.infinity.utils.WriteableUtil;
import com.lyz.code.infinity.verb.Add;
import com.lyz.code.infinity.verb.Delete;
import com.lyz.code.infinity.verb.FindById;
import com.lyz.code.infinity.verb.FindByName;
import com.lyz.code.infinity.verb.ListActive;
import com.lyz.code.infinity.verb.ListAll;
import com.lyz.code.infinity.verb.ListAllByPage;
import com.lyz.code.infinity.verb.SearchByName;
import com.lyz.code.infinity.verb.SoftDelete;
import com.lyz.code.infinity.verb.Update;

public class PagingGridJspTemplate extends JspTemplate {
	public PagingGridJspTemplate(Domain domain) throws Exception{
		super();
		this.domain = domain;
		this.standardName = StringUtil.lowerFirst(domain.getPlural());
		this.verbs = new ArrayList<Verb>();
		this.verbs.add(new ListAll(domain));
		this.verbs.add(new ListActive(domain));
		this.verbs.add(new Delete(domain));
		this.verbs.add(new FindById(domain));
		this.verbs.add(new FindByName(domain));
		this.verbs.add(new SearchByName(domain));
		this.verbs.add(new SoftDelete(domain));
		this.verbs.add(new Update(domain));
		this.verbs.add(new Add(domain));
		this.verbs.add(new ListAllByPage(domain));
		for (Verb v : this.verbs){
			try {
				this.controllers.add(new Controller(v, this.domain));
			} catch (ValidateException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String generateJspString(){
		return generateStatementList().getContent();
	}
	
	@Override
	public StatementList generateStatementList() {
		try {
			List<Writeable> sList =  new ArrayList<Writeable>();
			sList.add(new Statement(1000,0,"<%@page contentType=\"text/html\" pageEncoding=\"UTF-8\"%>"));
			sList.add(new Statement(2000,0,"<%@page import=\"java.util.List\" %>"));
			sList.add(new Statement(3000,0,"<%@page import=\""+this.getDomain().getPackageToken()+".domain."+this.getDomain().getStandardName()+"\"%>"));
			sList.add(new Statement(3100,0,"<%int pagenum = request.getAttribute(\"pagenum\")==null?1:(Integer)request.getAttribute(\"pagenum\");%>"));
			sList.add(new Statement(3200,0,"<%int pagecount = request.getAttribute(\"pagecount\")==null?1:(Integer)request.getAttribute(\"pagecount\");%>"));
			sList.add(new Statement(3300,0,"<%int pagesize = request.getAttribute(\"pagesize\")==null?10:(Integer)request.getAttribute(\"pagesize\");%>"));
			sList.add(new Statement(6000,0,"<!DOCTYPE html>"));
			sList.add(new Statement(7000,0,"<html>"));
			sList.add(new Statement(8000,0,"<head>"));
			sList.add(new Statement(9000,0,"<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />"));
			sList.add(new Statement(10000,0,"<title>"+StringUtil.capFirst(this.getStandardName())+" Info.</title>"));
			sList.add(new Statement(11000,0,"<link href=\"../css/default.css\" rel=\"stylesheet\" type=\"text/css\" />"));
			sList.add(new Statement(12000,0,"</head>"));  
			sList.add(new Statement(13000,0,"<body>"));
			sList.add(new Statement(14000,1,"<div id=\"wrapper\">"));
			sList.add(new Statement(15000,1,"<jsp:include page=\"../include/header.jsp\" />"));
			sList.add(new Statement(16000,1,"<!-- end div#header -->"));
			sList.add(new Statement(17000,1,"<div id=\"page_wide\">"));
			sList.add(new Statement(18000,1,"<div id=\"mycontent\">"));
			sList.add(new Statement(19000,1,"<div id=\"welcome\">"));
			sList.add(new Statement(20000,1,"<!-- Fetch Rows -->"));
			Controller searchController = new Controller(this.verbs.get(5),this.domain);
			Field searchByNameField = this.domain.getDomainName();
			sList.add(JSPNamedStatementListGenerator.generateSearchByNameTableFormWithRequestVarStatementList(21000, 1, searchByNameField, searchController));
		
			sList.add(new Statement(22000,1,"<table class=\"aatable\">"));
			sList.add(JSPNamedStatementGenerator.getTrStartStatement(23000, 1));
			sList.add(JSPNamedStatementListGenerator.generateTableColumnHeadsUsingIdRenameFieldListWithOperationColumnStatementList(24000, 1, this.domain));
			sList.add(JSPNamedStatementGenerator.getTrEndStatement(25000, 1));
			sList.add(JSPNamedStatementGenerator.getTrStartStatement(26000, 1));
			
			// paging header
			Controller listAllByPageController = new Controller(this.verbs.get(9),this.domain);
			sList.add(new Statement(27000,1,"<td colspan=\""+this.domain.getFields().size()+1+"\" nowrap=\"nowrap\" align=\"right\"><span style=\"display:inline;font-size:14px;float:right\"><form action=\"../controller/"+StringUtil.lowerFirst(listAllByPageController.getStandardName())+"\" method=\"post\" name=\"pageform0\">"));
			sList.add(new Statement(28000,1,"<input type=\"hidden\" name=\"pagesize\" id=\"pagesize\" value=\"<%=pagesize%>\"/>"));
			sList.add(new Statement(28500,1,"<input type=\"hidden\" name=\"pagenum\" id=\"pagenum\" value=\"<%=pagenum%>\"/>"));
			sList.add(new Statement(29000,1,"<input type=\"hidden\" name=\"pagecount\" id=\"pagecount\" value=\"<%=pagecount%>\"/>"));
			sList.add(new Statement(30000,1,"<input type=\"text\" size=\"4\" name=\"jumppagenum0\" id=\"jumppagenum0\" value=\"<%=pagenum%>\"/><input type=\"button\" name=\"go1\" value=\"Go\" onclick=\"pageform0.pagenum.value=pageform0.jumppagenum0.value; pageform0.submit()\"/>&nbsp;<a href=\"#\" onclick=\"pageform0.pagenum.value=1; pageform0.submit()\">|<</a>"));
			sList.add(new Statement(31000,1,"&nbsp;<a href=\"#\" onclick=\"pageform0.pagenum.value=<%=pagenum-1<1?1:(pagenum-1)%>; pageform0.submit()\"><</a>&nbsp;<a href=\"#\" onclick=\"pageform0.pagenum.value=<%=pagenum + 1>pagecount?pagecount:(pagenum + 1)%>; pageform0.submit()\">></a>"));
			sList.add(new Statement(32000,1,"&nbsp;<a href=\"#\" onclick=\"pageform0.pagenum.value=<%=pagecount%>; pageform0.submit()\">>|</a>&nbsp;</form><span></td>"));
			sList.add(JSPNamedStatementGenerator.getTrStartStatement(33000, 1));
			
			// loop through data list
			sList.add(JSPNamedStatementGenerator.getJavaNotionStartStatement(34000,1));

			Var list = new Var("list", new Type("List<"+this.domain.getStandardName()+">","java.util"));
			Var request = InterVarUtil.Servlet.request;
			sList.add(NamedStatementGenerator.getDomainListFromRequestAttribute(35000, 1, this.domain, list, request));
			Var index = new Var("index", new Type("int"));
			sList.add(NamedStatementGenerator.getListForHead(36000, 1, this.domain,index, list));		
			sList.add(NamedStatementGenerator.getDomainFromListi(37000, 2, domain, list, index));
			sList.add(JSPNamedStatementGenerator.getJavaNotionEndStatement(38000,1));
			sList.add(JSPNamedStatementGenerator.getTrStartStatement(39000,1));
			Controller editController = new Controller(this.verbs.get(7),this.domain);
			sList.add(new Statement(40000,1,"<form action='../controller/"+StringUtil.lowerFirst(editController.getStandardName())+"' method='post'>"));
			sList.add(JSPNamedStatementListGenerator.generateTableColumnsUsingIdRenameFieldListStatementList(41000,1,this.domain));
			Var mydomain = new Var(StringUtil.lowerFirst(domain.getStandardName()),new Type(StringUtil.lowerFirst(domain.getStandardName()),domain.getPackageToken()));
			sList.add(JSPNamedStatementGenerator.getTdStartStatement(42000, 1));
			sList.add(JSPNamedStatementGenerator.getHideInputDomainIdStatement(43000, 1, this.domain,mydomain));
			sList.add(new Statement(44000,1,"<input type='submit' value='Edit' /></form>"));
			sList.add(JSPNamedStatementListGenerator.generateDeleteUsingHiddenIdStatementList(45000, 1, this.domain, this.controllers.get(2)));
			sList.add(JSPNamedStatementListGenerator.generateSoftDeleteUsingHiddenIdStatementList(46000, 1, this.domain, this.controllers.get(6)));
			sList.add(JSPNamedStatementGenerator.getTdEndStatement(47000, 1));
			sList.add(JSPNamedStatementGenerator.getTrEndStatement(47500, 1));
			sList.add(JSPNamedStatementGenerator.getLoopEndWithJavaNotionStatement(48000, 1));
			//paging footer
			sList.add(new Statement(49000,1,"<td colspan=\""+this.domain.getFields().size()+1+"\" nowrap=\"nowrap\" align=\"right\"><span style=\"display:inline;font-size:14px;float:right\"><form action=\"../controller/"+StringUtil.lowerFirst(listAllByPageController.getStandardName())+"\" method=\"post\" name=\"pageform1\">"));
			sList.add(new Statement(50000,1,"<input type=\"hidden\" name=\"pagesize\" id=\"pagesize\" value=\"<%=pagesize%>\"/>"));
			sList.add(new Statement(50500,1,"<input type=\"hidden\" name=\"pagenum\" id=\"pagenum\" value=\"<%=pagenum%>\"/>"));
			sList.add(new Statement(51000,1,"<input type=\"hidden\" name=\"pagecount\" id=\"pagecount\" value=\"<%=pagecount%>\"/>"));
			sList.add(new Statement(52000,1,"<input type=\"text\" size=\"4\" name=\"jumppagenum1\" id=\"jumppagenum1\" value=\"<%=pagenum%>\"/><input type=\"button\" name=\"go1\" value=\"Go\" onclick=\"pageform1.pagenum.value=pageform1.jumppagenum1.value; pageform1.submit()\"/>&nbsp;<a href=\"#\" onclick=\"pageform1.pagenum.value=1; pageform1.submit()\">|<</a>"));
			sList.add(new Statement(53000,1,"&nbsp;<a href=\"#\" onclick=\"pageform1.pagenum.value=<%=pagenum-1<1?1:(pagenum-1)%>; pageform1.submit()\"><</a>&nbsp;<a href=\"#\" onclick=\"pageform1.pagenum.value=<%=pagenum + 1>pagecount?pagecount:(pagenum + 1)%>; pageform1.submit()\">></a>"));
			sList.add(new Statement(54000,1,"&nbsp;<a href=\"#\" onclick=\"pageform1.pagenum.value=<%=pagecount%>; pageform1.submit()\">>|</a>&nbsp;</form><span></td>"));
			sList.add(JSPNamedStatementGenerator.getTrStartStatement(55000, 1));
			//add functional
			sList.add(JSPNamedStatementGenerator.getTrStartStatement(56000, 1));
			sList.add(JSPNamedStatementGenerator.getSetAddFormStatement(57000,1, this.domain, this.controllers.get(8)));
			sList.add(JSPNamedStatementListGenerator.generateTableRowsUsingIdRenameFieldListWithoutSettingValueWithoutNbspStatementList(58000,1,this.domain));
			sList.add(JSPNamedStatementGenerator.getTdStartStatement(59000, 2));
            sList.add(JSPNamedStatementGenerator.getSubmitButtonStatement(60000, 2,"Add")); 
            sList.add(JSPNamedStatementGenerator.getTdEndStatement(61000, 2));
            sList.add(JSPNamedStatementGenerator.getFormEndStatement(62000, 1));
            sList.add(JSPNamedStatementGenerator.getTrEndStatement(63000, 1));
            sList.add(JSPNamedStatementGenerator.getTableEndStatement(64000, 1));
            sList.add(JSPNamedStatementGenerator.getDivEndStatement(65000, 1));
			sList.add(new Statement(66000,1,"<!-- end div#welcome -->"));
			sList.add(new Statement(67000,1,"</div>"));
			sList.add(new Statement(68000,1,"<!-- end div#content -->"));
			sList.add(new Statement(69000,1,"<div id=\"sidebar\">"));   
			sList.add(new Statement(70000,1,"<ul>"));
			sList.add(new Statement(71000,1,"<%if (request.getSession().getAttribute(\"isadmin\")!=null && (Boolean)request.getSession().getAttribute(\"isadmin\")){%>"));
			sList.add(new Statement(72000,1,"<jsp:include page=\"../include/adminnav.jsp\"/>"));
			sList.add(new Statement(73000,1,"<%} else { %>"));
			sList.add(new Statement(74000,1,"<jsp:include page=\"../include/usernav.jsp\"/>"));
			sList.add(new Statement(75000,1,"<%} %>"));
			sList.add(new Statement(76000,1,"<!-- end navigation -->"));
			sList.add(new Statement(77000,1,"<jsp:include page=\"../include/updates.jsp\"/>"));
			sList.add(new Statement(78000,1,"<!-- end updates -->"));
			sList.add(new Statement(79000,1,"</ul>"));                   
			sList.add(new Statement(80000,1,"</div>"));
			sList.add(new Statement(81000,1,"<!-- end div#sidebar -->"));
			sList.add(new Statement(82000,1,"<div style=\"clear: both; height: 1px\"></div>"));
			sList.add(new Statement(83000,1,"</div>"));
			sList.add(new Statement(84000,1,"<jsp:include page=\"../include/footer.jsp\"/>"));
			sList.add(new Statement(85000,1,"</div>"));        
			sList.add(new Statement(86000,1,"<!-- end div#wrapper -->"));
			sList.add(JSPNamedStatementGenerator.getBodyEndStatement(87000, 0));
			sList.add(JSPNamedStatementGenerator.getHtmlEndStatement(88000, 0));

			StatementList mylist = WriteableUtil.merge(sList);
			return mylist;
		} catch (Exception e){
			return null;
		}
	}
}
