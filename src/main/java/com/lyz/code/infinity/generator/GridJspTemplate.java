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
import com.lyz.code.infinity.verb.SearchByName;
import com.lyz.code.infinity.verb.SoftDelete;
import com.lyz.code.infinity.verb.Update;

public class GridJspTemplate extends JspTemplate {
	public GridJspTemplate(Domain domain) throws Exception{
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
			sList.add(new Statement(6000,0,"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">"));
			sList.add(new Statement(7000,0,"<html xmlns=\"http://www.w3.org/1999/xhtml\">"));
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
			sList.add(JSPNamedStatementListGenerator.generateSearchByNameTableFormWithRequestVarStatementList(20500, 1, searchByNameField, searchController));
		
			sList.add(new Statement(21000,1,"<table class=\"aatable\">"));
			sList.add(JSPNamedStatementGenerator.getTrStartStatement(22000, 1));
			sList.add(JSPNamedStatementListGenerator.generateTableColumnHeadsUsingIdRenameFieldListWithOperationColumnStatementList(23000, 1, this.domain));
			sList.add(JSPNamedStatementGenerator.getTrEndStatement(28000, 1));
			
			// loop through data list
			sList.add(JSPNamedStatementGenerator.getJavaNotionStartStatement(29000,1));

			Var list = new Var("list", new Type("List<"+this.domain.getStandardName()+">","java.util"));
			Var request = InterVarUtil.Servlet.request;
			sList.add(NamedStatementGenerator.getDomainListFromRequestAttribute(30000, 1, this.domain, list, request));
			Var index = new Var("index", new Type("int"));
			sList.add(NamedStatementGenerator.getListForHead(31000, 1, this.domain,index, list));		
			sList.add(NamedStatementGenerator.getDomainFromListi(31500, 2, domain, list, index));
			sList.add(JSPNamedStatementGenerator.getJavaNotionEndStatement(32000,1));
			sList.add(JSPNamedStatementGenerator.getTrStartStatement(33000,1));
			Controller editController = new Controller(this.verbs.get(7),this.domain);
			sList.add(new Statement(34000,1,"<form action='../controller/"+StringUtil.lowerFirst(editController.getStandardName())+"' method='post'>"));
			sList.add(JSPNamedStatementListGenerator.generateTableColumnsUsingIdRenameFieldListStatementList(35000,1,this.domain));
			Var mydomain = new Var(StringUtil.lowerFirst(domain.getStandardName()),new Type(StringUtil.lowerFirst(domain.getStandardName()),domain.getPackageToken()));         
			sList.add(new Statement(35000,1,"<td>"));
			sList.add(JSPNamedStatementGenerator.getHideInputDomainIdStatement(36000, 1, this.domain,mydomain));
			sList.add(new Statement(37000,1,"<input type='submit' value='Edit' /></form>"));
			sList.add(JSPNamedStatementListGenerator.generateDeleteUsingHiddenIdStatementList(38000, 1, this.domain, this.controllers.get(2)));
			sList.add(JSPNamedStatementListGenerator.generateSoftDeleteUsingHiddenIdStatementList(38500, 1, this.domain, this.controllers.get(6)));
			sList.add(JSPNamedStatementGenerator.getTrEndStatement(39000, 1));
			sList.add(JSPNamedStatementGenerator.getLoopEndWithJavaNotionStatement(39500, 1));
			//add functional
			sList.add(JSPNamedStatementGenerator.getTrStartStatement(39700, 1));
			sList.add(JSPNamedStatementGenerator.getSetAddFormStatement(40000,1, this.domain, this.controllers.get(8)));
			sList.add(JSPNamedStatementListGenerator.generateTableRowsUsingIdRenameFieldListWithoutSettingValueStatementList(41000,1,this.domain));
			sList.add(JSPNamedStatementGenerator.getTdStartStatement(42000, 2));
            sList.add(JSPNamedStatementGenerator.getSubmitButtonStatement(43000, 2,"Add")); 
            sList.add(JSPNamedStatementGenerator.getTdEndStatement(44000, 2));
            sList.add(JSPNamedStatementGenerator.getFormEndStatement(45000, 1));
            sList.add(JSPNamedStatementGenerator.getTrEndStatement(46000, 1));
            sList.add(JSPNamedStatementGenerator.getTableEndStatement(47000, 1));
            sList.add(JSPNamedStatementGenerator.getDivEndStatement(48000, 1));
			sList.add(new Statement(54000,1,"<!-- end div#welcome -->"));
			sList.add(new Statement(55000,1,"</div>"));
			sList.add(new Statement(56000,1,"<!-- end div#content -->"));
			sList.add(new Statement(57000,1,"<div id=\"sidebar\">"));   
			sList.add(new Statement(58000,1,"<ul>"));
			sList.add(new Statement(59000,1,"<%if (request.getSession().getAttribute(\"isadmin\")!=null && (Boolean)request.getSession().getAttribute(\"isadmin\")){%>"));
			sList.add(new Statement(60000,1,"<jsp:include page=\"../include/adminnav.jsp\"/>"));
			sList.add(new Statement(61000,1,"<%} else { %>"));
			sList.add(new Statement(62000,1,"<jsp:include page=\"../include/usernav.jsp\"/>"));
			sList.add(new Statement(63000,1,"<%} %>"));
			sList.add(new Statement(64000,1,"<!-- end navigation -->"));
			sList.add(new Statement(65000,1,"<jsp:include page=\"../include/updates.jsp\"/>"));
			sList.add(new Statement(66000,1,"<!-- end updates -->"));
			sList.add(new Statement(67000,1,"</ul>"));                   
			sList.add(new Statement(68000,1,"</div>"));
			sList.add(new Statement(69000,1,"<!-- end div#sidebar -->"));
			sList.add(new Statement(70000,1,"<div style=\"clear: both; height: 1px\"></div>"));
			sList.add(new Statement(71000,1,"</div>"));
			sList.add(new Statement(72000,1,"<jsp:include page=\"../include/footer.jsp\"/>"));
			sList.add(new Statement(73000,1,"</div>"));        
			sList.add(new Statement(74000,1,"<!-- end div#wrapper -->"));
			sList.add(JSPNamedStatementGenerator.getBodyEndStatement(75000, 0));
			sList.add(JSPNamedStatementGenerator.getHtmlEndStatement(76000, 0));

			StatementList mylist = WriteableUtil.merge(sList);
			return mylist;
		} catch (Exception e){
			return null;
		}
	}
}
