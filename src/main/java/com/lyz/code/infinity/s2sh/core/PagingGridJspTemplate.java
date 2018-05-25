package com.lyz.code.infinity.s2sh.core;

import java.util.ArrayList;
import java.util.List;

import com.lyz.code.infinity.core.Verb;
import com.lyz.code.infinity.core.Writeable;
import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Field;
import com.lyz.code.infinity.domain.Method;
import com.lyz.code.infinity.domain.Statement;
import com.lyz.code.infinity.domain.StatementList;
import com.lyz.code.infinity.domain.Type;
import com.lyz.code.infinity.domain.Var;
import com.lyz.code.infinity.exception.ValidateException;
import com.lyz.code.infinity.generator.JspTemplate;
import com.lyz.code.infinity.s2sh.verb.Add;
import com.lyz.code.infinity.s2sh.verb.Delete;
import com.lyz.code.infinity.s2sh.verb.FindById;
import com.lyz.code.infinity.s2sh.verb.FindByName;
import com.lyz.code.infinity.s2sh.verb.ListActive;
import com.lyz.code.infinity.s2sh.verb.ListAll;
import com.lyz.code.infinity.s2sh.verb.ListAllByPage;
import com.lyz.code.infinity.s2sh.verb.S2SHVerbFactory;
import com.lyz.code.infinity.s2sh.verb.SearchByName;
import com.lyz.code.infinity.s2sh.verb.SoftDelete;
import com.lyz.code.infinity.s2sh.verb.Update;
import com.lyz.code.infinity.utils.InterVarUtil;
import com.lyz.code.infinity.utils.StringUtil;
import com.lyz.code.infinity.utils.WriteableUtil;


public class PagingGridJspTemplate extends JspTemplate {
	protected Action action;
	
	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}
	
	public PagingGridJspTemplate(Domain domain) throws Exception {
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
		this.action = new Action(this.verbs,this.domain);
	}
	
	public String generateJspString(){
		return generateStatementList().getContent();
	}
	
	@Override
	public StatementList generateStatementList() {
		try {
			Action action = new Action(this.verbs,this.domain);
			List<Writeable> sList =  new ArrayList<Writeable>();
			sList.add(new Statement(1000,0,"<%@page contentType=\"text/html\" pageEncoding=\"UTF-8\"%>"));
			sList.add(new Statement(2000,0,"<%@page import=\"java.util.List\" %>"));
			sList.add(new Statement(3000,0,"<%@page import=\""+this.getDomain().getPackageToken()+".domain."+this.getDomain().getStandardName()+"\"%>"));
			sList.add(new Statement(3100,0,"<%int pagenum = request.getAttribute(\"pagenum\")==null?1:(Integer)request.getAttribute(\"pagenum\");"));
			sList.add(new Statement(3200,0," int pagecount = request.getAttribute(\"pagecount\")==null?1:(Integer)request.getAttribute(\"pagecount\");"));
			sList.add(new Statement(3220,0," if (pagenum > pagecount) pagenum= pagecount;"));
			sList.add(new Statement(3240,0," if (pagenum < 1) pagenum= 1;"));
			sList.add(new Statement(3300,0,"int pagesize = request.getAttribute(\"pagesize\")==null?10:(Integer)request.getAttribute(\"pagesize\");%>"));
			sList.add(new Statement(6000,0,"<!DOCTYPE html>"));
			sList.add(new Statement(7000,0,"<html>"));
			sList.add(new Statement(8000,0,"<head>"));
			sList.add(new Statement(9000,0,"<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />"));
			sList.add(new Statement(10000,0,"<title>"+StringUtil.capFirst(this.getStandardName())+" Info.</title>"));
			sList.add(new Statement(11000,0,"<link href=\"../css/default.css\" rel=\"stylesheet\" type=\"text/css\" />"));
			sList.add(new Statement(11500,0,"<script type=\"text/javascript\" src=\"../js/jquery-1.11.1.min.js\"></script>"));
			sList.add(new Statement(12000,0,"</head>"));  
			sList.add(new Statement(13000,0,"<body>"));
			sList.add(new Statement(14000,1,"<div id=\"wrapper\">"));
			sList.add(new Statement(15000,1,"<jsp:include page=\"../include/header.jsp\" />"));
			sList.add(new Statement(16000,1,"<!-- end div#header -->"));
			sList.add(new Statement(17000,1,"<div id=\"page_wide\">"));
			sList.add(new Statement(18000,1,"<div id=\"mycontent\">"));
			sList.add(new Statement(19000,1,"<div id=\"welcome\">"));
			sList.add(new Statement(20000,1,"<!-- Fetch Rows -->"));
			Field searchByNameField = this.domain.getDomainName(); 
			Method searchMethod = action.findVerbMethodByName(StringUtil.lowerFirst(S2SHVerbFactory.getInstance("searchByName", this.domain).generateControllerMethod().getStandardName()));
			sList.add(S2SHJSPNamedStatementListGenerator.generateSearchByNameTableFormWithRequestVarStatementList(21000, 1,this.domain, searchByNameField, searchMethod));
		
			sList.add(new Statement(22000,1,"<table class=\"aatable\">"));
			sList.add(S2SHJSPNamedStatementGenerator.getTrStartStatement(23000, 1));
			sList.add(S2SHJSPNamedStatementListGenerator.generateTableColumnHeadsUsingIdRenameFieldListWithOperationColumnStatementList(24000, 1, this.domain));
			sList.add(S2SHJSPNamedStatementGenerator.getTrEndStatement(25000, 1));
			sList.add(S2SHJSPNamedStatementGenerator.getTrStartStatement(26000, 1));

			// paging header
			Method listAllByPage = action.findVerbMethodByName(StringUtil.lowerFirst(S2SHVerbFactory.getInstance("listAllByPage", this.domain).generateControllerMethod().getStandardName()));
			sList.add(new Statement(27000,1,"<td colspan=\""+(this.domain.getFields().size()+1)+"\" nowrap=\"nowrap\" align=\"right\"><span style=\"display:inline;font-size:14px;float:right\"><form action=\"../action/"+listAllByPage.getLowerFirstMethodName()+"\" method=\"post\" name=\"pageform0\">"));
			sList.add(new Statement(28000,1,"<input type=\"hidden\" name=\"pagesize\" id=\"pagesize\" value=\"<%=pagesize%>\"/>"));
			sList.add(new Statement(28500,1,"<input type=\"hidden\" name=\"pagenum\" id=\"pagenum\" value=\"<%=pagenum%>\"/>"));
			sList.add(new Statement(29000,1,"<input type=\"hidden\" name=\"pagecount\" id=\"pagecount\" value=\"<%=pagecount%>\"/>"));			
			sList.add(new Statement(30000,1,"<input type=\"text\" size=\"4\" name=\"jumppagenum0\" id=\"jumppagenum0\" value=\"<%=pagenum%>\"/><input type=\"button\" name=\"go1\" value=\"Go\" onclick=\"pageform0.pagenum.value=pageform0.jumppagenum0.value;pageform0.submit();\"/>&nbsp;<a href=\"#\" onclick=\"pageform0.pagenum.value=1; pageform0.submit()\">|<</a>"));
			sList.add(new Statement(31000,1,"&nbsp;<a href=\"#\" onclick=\"pageform0.pagenum.value=<%=pagenum-1<1?1:(pagenum-1)%>; pageform0.submit()\"><</a>&nbsp;<a href=\"#\" onclick=\"pageform0.pagenum.value=<%=pagenum + 1>pagecount?pagecount:(pagenum + 1)%>; pageform0.submit()\">></a>"));
			sList.add(new Statement(32000,1,"&nbsp;<a href=\"#\" onclick=\"pageform0.pagenum.value=<%=pagecount%>; pageform0.submit()\">>|</a>&nbsp;</form><span></td>"));
			sList.add(S2SHJSPNamedStatementGenerator.getTrStartStatement(33000, 1));
			
			// loop through data list
			sList.add(S2SHJSPNamedStatementGenerator.getJavaNotionStartStatement(34000,1));

			Var list = new Var("list", new Type("List<"+this.domain.getStandardName()+">","java.util"));
			Var request = InterVarUtil.Servlet.request;
			sList.add(NamedS2SHStatementGenerator.getDomainListFromRequestAttribute(35000, 1, this.domain, list, request));
			Var index = new Var("index", new Type("int"));
			sList.add(NamedS2SHStatementGenerator.getListForHead(36000, 1, this.domain,index, list));		
			sList.add(NamedS2SHStatementGenerator.getDomainFromListi(37000, 2, this.domain, list, index));
			sList.add(S2SHJSPNamedStatementGenerator.getJavaNotionEndStatement(38000,1));
			sList.add(S2SHJSPNamedStatementGenerator.getTrStartStatement(39000,1));
			
			Method updateMethod = action.findVerbMethodByName(StringUtil.lowerFirst(S2SHVerbFactory.getInstance("update", this.domain).generateControllerMethod().getStandardName()));
			sList.add(new Statement(40000,1,"<form action='../action/"+StringUtil.lowerFirst(updateMethod.getStandardName())+"' method='post' name='editform<%="+index.getVarName()+"%>' id='editform<%="+index.getVarName()+"%>'>"));
			sList.add(S2SHJSPNamedStatementListGenerator.generateTableColumnsUsingIdRenameFieldListStatementList(41000,1,this.domain));
			Var mydomain = new Var(StringUtil.lowerFirst(this.domain.getStandardName()),new Type(StringUtil.lowerFirst(this.domain.getStandardName()),this.domain.getPackageToken()));
			sList.add(S2SHJSPNamedStatementGenerator.getHideInputDomainIdStatement(42000, 1, this.domain,mydomain));
			sList.add(new Statement(43000L,1,"</form>"));
			sList.add(S2SHJSPNamedStatementGenerator.getTdStartStatement(43500, 1));			
			sList.add(new Statement(44000,1,"<input type='button' value='Edit' onclick=\"$('#editform<%="+index.getVarName()+"%>').submit();\"/>"));
			Method deleteMethod = action.findVerbMethodByName(S2SHVerbFactory.getInstance("delete",this.domain).generateControllerMethod().getStandardName());
			Method softDeleteMethod = action.findVerbMethodByName(S2SHVerbFactory.getInstance("softDelete",this.domain).generateControllerMethod().getStandardName());
			sList.add(S2SHJSPNamedStatementListGenerator.generateDeleteUsingHiddenIdStatementList(45000, 1, this.domain, deleteMethod));
			sList.add(S2SHJSPNamedStatementListGenerator.generateSoftDeleteUsingHiddenIdStatementList(46000, 1, this.domain, softDeleteMethod));
			sList.add(S2SHJSPNamedStatementGenerator.getTdEndStatement(47000, 1));
			sList.add(S2SHJSPNamedStatementGenerator.getTrEndStatement(47500, 1));
			sList.add(S2SHJSPNamedStatementGenerator.getLoopEndWithJavaNotionStatement(48000, 1));
			//paging footer
			sList.add(new Statement(49000,1,"<td colspan=\""+this.domain.getFields().size()+1+"\" nowrap=\"nowrap\" align=\"right\"><span style=\"display:inline;font-size:14px;float:right\"><form action=\"../action/"+StringUtil.lowerFirst(listAllByPage.getStandardName())+"\" method=\"post\" name=\"pageform1\">"));
			sList.add(new Statement(50000,1,"<input type=\"hidden\" name=\"pagesize\" id=\"pagesize\" value=\"<%=pagesize%>\"/>"));
			sList.add(new Statement(50500,1,"<input type=\"hidden\" name=\"pagenum\" id=\"pagenum\" value=\"<%=pagenum%>\"/>"));
			sList.add(new Statement(51000,1,"<input type=\"hidden\" name=\"pagecount\" id=\"pagecount\" value=\"<%=pagecount%>\"/>"));
			sList.add(new Statement(52000,1,"<input type=\"text\" size=\"4\" name=\"jumppagenum1\" id=\"jumppagenum1\" value=\"<%=pagenum%>\"/><input type=\"button\" name=\"go1\" value=\"Go\" onclick=\"pageform1.pagenum.value=pageform1.jumppagenum1.value;pageform1.submit();\"/>&nbsp;<a href=\"#\" onclick=\"pageform1.pagenum.value=1; pageform1.submit()\">|<</a>"));
			sList.add(new Statement(53000,1,"&nbsp;<a href=\"#\" onclick=\"pageform1.pagenum.value=<%=pagenum-1<1?1:(pagenum-1)%>; pageform1.submit()\"><</a>&nbsp;<a href=\"#\" onclick=\"pageform1.pagenum.value=<%=pagenum + 1>pagecount?pagecount:(pagenum + 1)%>; pageform1.submit()\">></a>"));
			sList.add(new Statement(54000,1,"&nbsp;<a href=\"#\" onclick=\"pageform1.pagenum.value=<%=pagecount%>; pageform1.submit()\">>|</a>&nbsp;</form><span></td>"));
			sList.add(S2SHJSPNamedStatementGenerator.getTrStartStatement(55000, 1));
			//add functional
			sList.add(S2SHJSPNamedStatementGenerator.getTrStartStatement(56000, 1));
			Method addMethod = action.findVerbMethodByName(S2SHVerbFactory.getInstance("add",this.domain).generateControllerMethod().getStandardName());
			sList.add(S2SHJSPNamedStatementGenerator.getSetAddFormStatement(57000,1, this.domain, addMethod));
			sList.add(S2SHJSPNamedStatementListGenerator.generateTableRowsUsingIdRenameFieldListWithoutSettingValueStatementList(58000,1,this.domain));
			sList.add(S2SHJSPNamedStatementGenerator.getTdStartStatement(59000, 2));
            sList.add(S2SHJSPNamedStatementGenerator.getSubmitButtonStatement(60000, 2,"Add")); 
            sList.add(S2SHJSPNamedStatementGenerator.getTdEndStatement(61000, 2));
            sList.add(S2SHJSPNamedStatementGenerator.getFormEndStatement(62000, 1));
            sList.add(S2SHJSPNamedStatementGenerator.getTrEndStatement(63000, 1));
            sList.add(S2SHJSPNamedStatementGenerator.getTableEndStatement(64000, 1));
            sList.add(S2SHJSPNamedStatementGenerator.getDivEndStatement(65000, 1));
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
			sList.add(S2SHJSPNamedStatementGenerator.getBodyEndStatement(87000, 0));
			sList.add(S2SHJSPNamedStatementGenerator.getHtmlEndStatement(88000, 0));

			StatementList mylist = WriteableUtil.merge(sList);
			return mylist;
		} catch (Exception e){
			return null;
		}
	}
}
