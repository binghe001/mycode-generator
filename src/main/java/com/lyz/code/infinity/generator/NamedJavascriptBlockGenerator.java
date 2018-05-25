package com.lyz.code.infinity.generator;

import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.JavascriptBlock;
import com.lyz.code.infinity.domain.Statement;
import com.lyz.code.infinity.domain.StatementList;
import com.lyz.code.infinity.domain.Var;
import com.lyz.code.infinity.utils.StringUtil;


public class NamedJavascriptBlockGenerator {
	public static JavascriptBlock documentReadyListDomainList(Domain domain, Var pagesize,Var pagenum){
		JavascriptBlock block = new JavascriptBlock();
		block.setSerial(100);
		block.setStandardName("documentReadyList"+domain.getCapFirstDomainName()+"List");
		StatementList sl = new StatementList();
		sl.add(new Statement(1000,0, "$(document).ready(function(){"));
		sl.add(new Statement(2000,1, "listAll"+StringUtil.capFirst(domain.getPlural())+"ByPage("+pagesize.getVarName()+","+pagenum.getVarName()+");"));
		sl.add(new Statement(3000,0, "});"));
		block.setMethodStatementList(sl);
		return block;
	}
}
