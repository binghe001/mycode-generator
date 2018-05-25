package com.lyz.code.infinity.s2sh.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.lyz.code.infinity.core.Verb;
import com.lyz.code.infinity.core.Writeable;
import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Facade;
import com.lyz.code.infinity.domain.Field;
import com.lyz.code.infinity.domain.JavascriptMethod;
import com.lyz.code.infinity.domain.Statement;
import com.lyz.code.infinity.domain.StatementList;
import com.lyz.code.infinity.domain.Type;
import com.lyz.code.infinity.domain.Var;
import com.lyz.code.infinity.exception.ValidateException;
import com.lyz.code.infinity.generator.JSPNamedStatementGenerator;
import com.lyz.code.infinity.generator.JSPNamedStatementListGenerator;
import com.lyz.code.infinity.generator.JspTemplate;
import com.lyz.code.infinity.generator.NamedJavascriptBlockGenerator;
import com.lyz.code.infinity.generator.NamedJavascriptMethodGenerator;
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

public class JsonPagingGridJspTemplate extends JspTemplate {
	protected Set<Facade> facades = new TreeSet<Facade>();
	
	public Set<Facade> getFacades() {
		return facades;
	}

	public void setFacades(Set<Facade> facades) {
		this.facades = facades;
	}

	public JsonPagingGridJspTemplate(Domain domain) throws Exception{
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
				this.facades.add(new Facade(v, this.domain));
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
			sList.add(new Statement(1000L,0,"<%@page contentType=\"text/html\" pageEncoding=\"UTF-8\"%>"));
			sList.add(new Statement(6000L,0,"<!DOCTYPE html>"));
			sList.add(new Statement(7000L,0,"<html>"));
			sList.add(new Statement(8000L,0,"<head>"));
			sList.add(new Statement(9000L,0,"<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />"));
			sList.add(new Statement(10000L,0,"<title>"+StringUtil.capFirst(this.getStandardName())+" Info.</title>"));
			sList.add(new Statement(11000L,0,"<link href=\"../css/default.css\" rel=\"stylesheet\" type=\"text/css\" />"));
			sList.add(new Statement(11500L,0,"<script type=\"text/javascript\" src=\"../js/jquery-1.11.1.min.js\"></script>"));
			sList.add(new Statement(12000L,0,"</head>"));  
			sList.add(new Statement(13000L,0,"<body>"));
			sList.add(new Statement(14000L,1,"<div id=\"wrapper\">"));
			sList.add(new Statement(15000L,1,"<jsp:include page=\"../jsoninclude/header.jsp\" />"));
			sList.add(new Statement(16000L,1,"<!-- end div#header -->"));
			sList.add(new Statement(17000L,1,"<div id=\"page_wide\">"));
			sList.add(new Statement(18000L,1,"<div id=\"mycontent\">"));
			sList.add(new Statement(19000L,1,"<div id=\"welcome\">"));
			sList.add(new Statement(20000L,1,"<!-- Fetch Rows -->"));
			Facade searchFacade = new Facade(this.verbs.get(5),this.domain);
			Field searchByNameField = this.domain.getDomainName();
			//SearchByName js method and searchForm
			JavascriptMethod searchByNameJSMethod = new JavascriptMethod();
			Var searchForm = new Var("searchForm",new Type("var"));
			Var myDomainNameField = new Var("my"+domain.getDomainName().getCapFirstFieldName(),new Type("var"));
			searchByNameJSMethod.setStandardName("search"+domain.getPlural()+"By"+domain.getDomainName().getCapFirstFieldName());
			sList.add(JSPNamedStatementListGenerator.generateSearchByNameTableFormSearchByFacadeStatementList(21000L, 1, searchByNameField, searchFacade,searchForm, searchByNameJSMethod,myDomainNameField));
			
			// domainform the main form for multipal operations
			Var domainform = new Var(domain.getLowerFirstDomainName()+"form",new Type("var"));
			sList.add(new Statement(21500L,1,"<form action='' method='post' name=\""+domainform.getVarName()+"\" id=\""+domainform.getVarName()+"\">"));
			sList.add(new Statement(22000L,1,"<table class=\"aatable\">"));
			sList.add(JSPNamedStatementGenerator.getTrStartStatement(23000L, 1));
			
			Var checkAllBox = new Var("checkAllBox",new Type("var"));
			JavascriptMethod checkAllFunction = new JavascriptMethod();
			checkAllFunction.setStandardName("checkAll");
			sList.add(JSPNamedStatementListGenerator.generateTableColumnHeadsUsingIdRenameFieldListWithOperationColumnWithCheckAllBoxStatementList(24000L, 1, this.domain,checkAllBox, checkAllFunction));
			sList.add(JSPNamedStatementGenerator.getTrEndStatement(25000L, 1));
			sList.add(JSPNamedStatementGenerator.getTrStartStatement(26000L, 1));
			
			// paging header
			Facade listAllByPageFacade = new Facade(this.verbs.get(9),this.domain);
			int colspanCount = this.domain.getFields().size()+2;
			sList.add(new Statement(27000L,1,"<td colspan=\""+ colspanCount +"\" nowrap=\"nowrap\" align=\"right\"><span style=\"display:inline;font-size:14px;float:right\">"));
			sList.add(new Statement(28000L,1,"<input type=\"hidden\" name=\"pagesize\" id=\"pagesize\" value=\"10\"/>"));
			sList.add(new Statement(28500L,1,"<input type=\"hidden\" name=\"pagenum\" id=\"pagenum\" value=\"1\"/>"));
			sList.add(new Statement(29000L,1,"<input type=\"hidden\" name=\"pagecount\" id=\"pagecount\" value=\"1\"/>"));
			sList.add(new Statement(30000L,1,"<input type=\"text\" size=\"4\" name=\"jumppagenum1\" id=\"jumppagenum1\" value=\"\"/><input type=\"button\" name=\"go1\" value=\"Go\" onclick=\"jump1()\"/>&nbsp;<a href=\"#\" onclick=\"first()\">|<</a>"));
			sList.add(new Statement(31000L,1,"&nbsp;<a href=\"#\" onclick=\"prev()\"><</a>&nbsp;<a href=\"#\" onclick=\"next()\">></a>"));
			sList.add(new Statement(32000L,1,"&nbsp;<a href=\"#\" onclick=\"last()\">>|</a>&nbsp;<span></td>"));
			sList.add(JSPNamedStatementGenerator.getTrStartStatement(33000L, 1));
			
			// tableContent
			sList.add(new Statement(34000L,1,"<tbody id=\"tableContent\"></tbody>"));

			//paging footer
			sList.add(new Statement(49000L,1,"<td colspan=\""+ colspanCount +"\" nowrap=\"nowrap\" align=\"right\"><span style=\"display:inline;font-size:14px;float:right\">"));
			sList.add(new Statement(52000L,1,"<input type=\"text\" size=\"4\" name=\"jumppagenum2\" id=\"jumppagenum2\" value=\"\" /><input type=\"button\" name=\"go1\" value=\"Go\" onclick=\"jump2()\"/>&nbsp;<a href=\"#\" onclick=\"first()\">|<</a>"));
			sList.add(new Statement(53000L,1,"&nbsp;<a href=\"#\" onclick=\"prev()\"><</a>&nbsp;<a href=\"#\" onclick=\"next()\">></a>"));
			sList.add(new Statement(54000,1,"&nbsp;<a href=\"#\" onclick=\"last()\">>|</a>&nbsp;<span></td>"));
			sList.add(JSPNamedStatementGenerator.getTrStartStatement(55000L, 1));
						
			//add functional
			Var addDomainRow = new Var("add"+domain.getCapFirstDomainName()+"Row",new Type("var"));
			sList.add(JSPNamedStatementGenerator.getTrStartWithIdStatement(56000L, 1, addDomainRow));
			//sList.add(JSPNamedStatementGenerator.getSetAddFormStatement(57000,1, this.domain, this.facades.get(8)));
			sList.add(JSPNamedStatementListGenerator.generateTableRowsUsingIdRenameFieldListWithoutSettingValueStatementList(58000L,1,this.domain));
			sList.add(JSPNamedStatementGenerator.getTdStartStatement(59000L, 2));
			JavascriptMethod addMethod = new JavascriptMethod();
			addMethod.setStandardName("add"+domain.getCapFirstDomainName());
            sList.add(JSPNamedStatementGenerator.getButtonWithJSMethodStatement(60000L, 2,"Add",addMethod)); 
            sList.add(JSPNamedStatementGenerator.getTdEndStatement(61000L, 2));
            sList.add(JSPNamedStatementGenerator.getFormEndStatement(62000L, 1));
            sList.add(JSPNamedStatementGenerator.getTrEndStatement(63000L, 1));
            sList.add(JSPNamedStatementGenerator.getTableEndStatement(64000L, 1));
            sList.add(new Statement(64500L,1,"</form>"));
            sList.add(JSPNamedStatementGenerator.getDivEndStatement(65000L, 1));
			sList.add(new Statement(66000L,1,"<!-- end div#welcome -->"));
			sList.add(new Statement(67000L,1,"</div>"));
			sList.add(new Statement(68000L,1,"<!-- end div#content -->"));
			sList.add(new Statement(69000L,1,"<div id=\"sidebar\">"));   
			sList.add(new Statement(70000L,1,"<ul>"));
			sList.add(new Statement(74000L,1,"<jsp:include page=\"../jsoninclude/jsonusernav.jsp\"/>"));
			sList.add(new Statement(76000L,1,"<!-- end navigation -->"));
			sList.add(new Statement(77000L,1,"<jsp:include page=\"../jsoninclude/updates.jsp\"/>"));
			sList.add(new Statement(78000L,1,"<!-- end updates -->"));
			sList.add(new Statement(79000L,1,"</ul>"));                   
			sList.add(new Statement(80000L,1,"</div>"));
			sList.add(new Statement(81000L,1,"<!-- end div#sidebar -->"));
			sList.add(new Statement(82000L,1,"<div style=\"clear: both; height: 1px\"></div>"));
			sList.add(new Statement(83000L,1,"</div>"));
			sList.add(new Statement(84000L,1,"<jsp:include page=\"../jsoninclude/footer.jsp\"/>"));
			sList.add(new Statement(85000L,1,"</div>"));        
			sList.add(new Statement(86000L,1,"<!-- end div#wrapper -->"));
			sList.add(new Statement(87000L,1,"<script type=\"text/javascript\">"));
			Var pagesize = new Var("pagesize",new Type("var"),"10");
			Var pagenum = new Var("pagenum",new Type("var"),"1");
			sList.add(new Statement(87200L,1, pagesize.generateTypeVarString() +" = " + pagesize.getValue()));
			sList.add(new Statement(87400L,1, pagenum.generateTypeVarString() +" = " + pagenum.getValue()));
			sList.add(NamedJavascriptBlockGenerator.documentReadyListDomainList(this.domain, pagesize, pagenum).getMethodStatementList(88000L));
			sList.add(new Statement(89000L,1,""));
			Var checkboxes = new Var("checkboxes",new Type("var"));
			Var checkBoxClass = new Var(domain.getDomainId().getLowerFirstFieldName()+"CheckBox",new Type("var"));
			sList.add(NamedS2SHJavascriptMethodGenerator.generateCheckAllMethod(checkAllBox, checkboxes, checkBoxClass).generateMethodStatementList(90000L));
			
			sList.add(new Statement(91000L,1,""));
			JavascriptMethod generateOpAllRow = new JavascriptMethod();
			generateOpAllRow.setStandardName("generateOpAllRow");
			Var datarow = new Var("datarow",new Type("var"));
			Var domainIdClass = new Var(domain.getDomainId().getLowerFirstFieldName()+"CheckBox", new Type("var"));
			JavascriptMethod updateMethod = new JavascriptMethod();
			updateMethod.setStandardName("update"+ domain.getCapFirstDomainName());
			JavascriptMethod deleteMethod = new JavascriptMethod();
			deleteMethod.setStandardName("delete"+ domain.getCapFirstDomainName());
			JavascriptMethod softDeleteMethod = new JavascriptMethod();
			softDeleteMethod.setStandardName("softDelete"+ domain.getCapFirstDomainName());
			Var containerId = new Var("tableContent", new Type("var"));
			Var data = new Var("data",new Type("var"));
			sList.add(NamedS2SHJavascriptMethodGenerator.generateGenerateDataRowsMethod(domain, data, generateOpAllRow, datarow, domainIdClass, updateMethod, deleteMethod, softDeleteMethod, containerId).generateMethodStatementList(92000L));
			
			sList.add(new Statement(93000L,1,""));
			JavascriptMethod updateAllMethod = new JavascriptMethod();
			updateAllMethod.setStandardName("updateAll"+domain.getPlural());
			JavascriptMethod deleteAllMethod = new JavascriptMethod();
			deleteAllMethod.setStandardName("deleteAll"+domain.getPlural());
			JavascriptMethod softDeleteAllMethod = new JavascriptMethod();
			softDeleteAllMethod.setStandardName("softDeleteAll"+domain.getPlural());
			Var opAllRowId = new Var("opAllRow",new Type("var"));
			sList.add(NamedS2SHJavascriptMethodGenerator.generateGenerateOpAllRowMethod(colspanCount, opAllRowId, updateAllMethod, deleteAllMethod, softDeleteAllMethod).generateMethodStatementList(94000L));
			
			sList.add(new Statement(95000L,1,""));			
			JavascriptMethod generateDataRowsMethod = new JavascriptMethod();
			generateDataRowsMethod.setStandardName("generateDataRows");
			sList.add(NamedS2SHJavascriptMethodGenerator.generateSearchByNameMethod(domain, searchForm, myDomainNameField, generateDataRowsMethod).generateMethodStatementList(96000L));
			
			Var jumppagenum1 = new Var("jumppagenum1",new Type("var"));
			Var jumppagenum2 = new Var("jumppagenum2",new Type("var"));
			Var pagecount = new Var("pagecount", new Type("var"));
			Var last = new Var("last", new Type("var"));
			sList.add(new Statement(97000L,1,""));
			sList.add(NamedS2SHJavascriptMethodGenerator.generateListAllByPageMethod(domain, pagesize, pagenum,last, jumppagenum1, jumppagenum2, pagecount, generateDataRowsMethod).generateMethodStatementList(98000L));
			
			sList.add(new Statement(99000L,1,""));
			JavascriptMethod listAllByPage = new JavascriptMethod();
			listAllByPage.setStandardName("listAll"+domain.getPlural()+"ByPage");
			
			sList.add(NamedS2SHJavascriptMethodGenerator.generateAddMethod(domain, addDomainRow,pagesize, listAllByPage).generateMethodStatementList(100000L));
			
			sList.add(new Statement(101000L,1,""));
			Var dataRow = new Var("datarow",new Type("var"));

			sList.add(NamedS2SHJavascriptMethodGenerator.generateUpdateMethod(domain, domainform, dataRow,pagesize, listAllByPage).generateMethodStatementList(102000L));
			
			sList.add(new Statement(103000L,1,""));
			sList.add(NamedS2SHJavascriptMethodGenerator.generateUpdateAllMethod(domain, domainform, dataRow, listAllByPage).generateMethodStatementList(104000L));
			
			sList.add(new Statement(105000L,1,""));
			Var domainId = new Var(domain.getDomainId().getLowerFirstFieldName(),new Type("var"));
			sList.add(NamedS2SHJavascriptMethodGenerator.generateDeleteMethod(domain,domainId,pagesize, listAllByPage).generateMethodStatementList(106000L));
			
			sList.add(new Statement(107000L,1,""));
			Var domainCheckboxGroup = new Var(StringUtil.lowerFirst(domain.getDomainId().getFieldName())+"Checkbox",new Type("var"));
			sList.add(NamedS2SHJavascriptMethodGenerator.generateDeleteAllMethod(domain, domainCheckboxGroup, checkAllBox, pagesize,listAllByPage).generateMethodStatementList(108000L));
			
			sList.add(new Statement(109000L,1,""));
			sList.add(NamedS2SHJavascriptMethodGenerator.generateSoftDeleteMethod(domain,domainId,pagesize, listAllByPage).generateMethodStatementList(110000L));
			sList.add(new Statement(111000L,1,""));
			sList.add(NamedS2SHJavascriptMethodGenerator.generateSoftDeleteAllMethod(domain, domainCheckboxGroup,checkAllBox,pagesize, listAllByPage).generateMethodStatementList(112000L));
			
			sList.add(new Statement(113000L,1,""));
			
			sList.add(NamedS2SHJavascriptMethodGenerator.generateJump1Method(pagesize, pagenum, jumppagenum1,listAllByPage).generateMethodStatementList(114000L));
			
			sList.add(new Statement(115000L,1,""));
			
			sList.add(NamedS2SHJavascriptMethodGenerator.generateJump2Method(pagesize, pagenum, jumppagenum2,listAllByPage).generateMethodStatementList(116000L));
			
			sList.add(new Statement(1117000L,1,""));
			sList.add(NamedS2SHJavascriptMethodGenerator.generateFirstMethod(pagesize, listAllByPage).generateMethodStatementList(118000L));
			
			sList.add(new Statement(119000L,1,""));
			sList.add(NamedS2SHJavascriptMethodGenerator.generatePrevMethod(pagesize,pagenum, listAllByPage).generateMethodStatementList(120000L));
			
			sList.add(new Statement(121000L,1,""));
			sList.add(NamedS2SHJavascriptMethodGenerator.generateNextMethod(pagesize,pagenum, listAllByPage).generateMethodStatementList(122000L));
			
			sList.add(new Statement(123000L,1,""));
			
			sList.add(NamedS2SHJavascriptMethodGenerator.generateLastMethod(pagesize,pagecount, listAllByPage).generateMethodStatementList(124000L));
			
			sList.add(new Statement(125000L,1,"</script>"));
			sList.add(JSPNamedStatementGenerator.getBodyEndStatement(126000L, 0));
			sList.add(JSPNamedStatementGenerator.getHtmlEndStatement(127000L, 0));

			StatementList mylist = WriteableUtil.merge(sList);
			return mylist;
		} catch (Exception e){
			return null;
		}
	}
}
