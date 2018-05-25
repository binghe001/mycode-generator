package com.lyz.code.infinity.generator;

import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Field;
import com.lyz.code.infinity.domain.JavascriptMethod;
import com.lyz.code.infinity.domain.Signature;
import com.lyz.code.infinity.domain.Statement;
import com.lyz.code.infinity.domain.StatementList;
import com.lyz.code.infinity.domain.Type;
import com.lyz.code.infinity.domain.Var;
import com.lyz.code.infinity.utils.StringUtil;

public class NamedJavascriptMethodGenerator {
	public static JavascriptMethod generateCheckAllMethod(Var checkAllBoxes , Var checkboxes,Var checkBoxClass){
		JavascriptMethod method = new JavascriptMethod();
		method.setSerial(200);
		method.setStandardName("checkAll");
		StatementList sl = new StatementList();
		sl.add(new Statement(1000L,1, "if ($(\"#"+checkAllBoxes.getVarName()+"\").get(0).checked) {"));
		sl.add(new Statement(2000L,2, "var "+checkboxes.getVarName() +" = $(\"."+checkBoxClass.getVarName()+"\").each(function(i){"));
		sl.add(new Statement(3000L,3, "$(this)[0].checked = true;"));
		sl.add(new Statement(4000L,2, "});"));
		sl.add(new Statement(5000L,1, "} else {"));
		sl.add(new Statement(6000L,2, "var "+checkboxes.getVarName() +" = $(\"."+checkBoxClass.getVarName()+"\").each(function(i){"));
		sl.add(new Statement(7000L,3, "$(this)[0].checked = false;"));
		sl.add(new Statement(8000L,2, "});"));
		sl.add(new Statement(9000L,1, "}"));
		method.setMethodStatementList(sl);
		return method;
	}
	
	public static JavascriptMethod generateGenerateDataRowsMethod(Domain domain,Var data,JavascriptMethod generateOpAllRow,Var datarow, Var domainIdClass,JavascriptMethod updateMethod, JavascriptMethod deleteMethod, JavascriptMethod softDeleteMethod, Var containerId){
		JavascriptMethod method = new JavascriptMethod();
		method.setSerial(200);
		method.setStandardName("generateDataRows");
		Signature s = new Signature();
		s.setName(data.getVarName());
		s.setPosition(1);
		s.setType(new Type("var"));
		method.addSignature(s);
		
		StatementList sl = new StatementList();
		sl.add(new Statement(1000L,1, "var tableStr;"));
		sl.add(new Statement(2000L,1, "var opAllRowStr = "+generateOpAllRow.generateStandardMethodCallString()+";"));
		sl.add(new Statement(3000L,1, "$.each(data, function(i, item) {"));
		sl.add(new Statement(4000L,2, "tableStr += \"<tr id='"+datarow.getVarName()+"\"+i+\"'><td><input type='checkbox' name='"+domain.getDomainId().getLowerFirstFieldName()+"Checkbox' id='"+domain.getDomainId().getLowerFirstFieldName()+"Checkbox' class='"+domainIdClass.getVarName()+"' value='\" + item."+domain.getDomainId().getLowerFirstFieldName() + "+ \"'/></td>\" +"));
		sl.add(new Statement(5000L,2, "\"<td><input type='hidden' name='"+domain.getDomainId().getLowerFirstFieldName()+"' id='"+domain.getDomainId().getLowerFirstFieldName()+"' value='\" + item."+domain.getDomainId().getLowerFirstFieldName()+" + \"'/><input type='text' name='"+domain.getDomainId().getLowerFirstFieldName()+"1' id='"+domain.getDomainId().getLowerFirstFieldName()+"1' size='8' value='\" + item."+domain.getDomainId().getLowerFirstFieldName() + "+\"'/></td>\" +"));
		long fieldSerial = 6000L;
		for (Field f: domain.getFieldsWithoutId()) {
			sl.add(new Statement(fieldSerial,2, "\"<td><input type='text' name='"+f.getLowerFirstFieldName()+"' id='"+f.getLowerFirstFieldName()+"' size='8' value='\" + item."+f.getLowerFirstFieldName()+" + \"'/></td>\" +"));
			fieldSerial += 1000L;
		}
		sl.add(new Statement(fieldSerial,2, "\"<td><input type='button' value='Edit' onclick='"+updateMethod.getLowerFirstMethodName()+"(\"+i+\")'/><input type='button' value='Delete' onclick='"+deleteMethod.getLowerFirstMethodName()+"(\"+item."+domain.getDomainId().getLowerFirstFieldName() + "+ \")'/><input type='button' value='Soft Delete' onclick='"+softDeleteMethod.getLowerFirstMethodName()+"(\"+item."+domain.getDomainId().getLowerFirstFieldName() + "+\")'/></td>\"+\"</tr>\";"));
		sl.add(new Statement(fieldSerial+1000L,1, "});"));
		sl.add(new Statement(fieldSerial+2000L,1, ""));	
		sl.add(new Statement(fieldSerial+3000L,1, "$(\"#"+containerId.getVarName()+"\").html(tableStr+opAllRowStr);"));
		
		method.setMethodStatementList(sl);
		return method;
	}
	
	public static JavascriptMethod generateGenerateOpAllRowMethod(int colspan,Var opAllRowId, JavascriptMethod updateAllMethod, JavascriptMethod deleteAllMethod, JavascriptMethod softDeleteAllMethod){
		JavascriptMethod method = new JavascriptMethod();
		method.setSerial(200);
		method.setStandardName("generateOpAllRow");
		StatementList sl = new StatementList();
		sl.add(new Statement(1000L,1, "var rowstr = \"<tr id='opAllRow'> \"+"));
		sl.add(new Statement(2000L,2, "\"<td colspan='"+colspan+"' style='text-align:center'> \" +"));
		sl.add(new Statement(3000L,2, "\"<input type='button' value='Update All' onclick='"+updateAllMethod.getLowerFirstMethodName()+"()'/> &nbsp;&nbsp; \"+"));
		sl.add(new Statement(4000L,2, "\"<input type='button' value='Soft Delete All' onclick='"+softDeleteAllMethod.getLowerFirstMethodName()+"()'/> &nbsp;&nbsp; \"+"));
		sl.add(new Statement(5000L,2, "\"<input type='button' value='Delete All' onclick='"+deleteAllMethod.getLowerFirstMethodName()+"()'/></td>\"+"));
		sl.add(new Statement(6000L,2, "\"</tr>\";"));
		sl.add(new Statement(7000L,1, "return rowstr;"));
		
		method.setMethodStatementList(sl);
		return method;
	}
	
	public static JavascriptMethod generateSearchByNameMethod(Domain domain,Var searchForm, Var myDomainNameField,JavascriptMethod generateDataRowsMethod){
		JavascriptMethod method = new JavascriptMethod();
		method.setSerial(200);
		method.setStandardName("search"+domain.getPlural()+"By"+domain.getDomainName().getCapFirstFieldName());
		StatementList sl = new StatementList();
		
		sl.add(new Statement(1000L,1, "$.ajax({"));
		sl.add(new Statement(2000L,2, "type: \"post\","));
		sl.add(new Statement(3000L,2, "url: \"../facade/search"+domain.getPlural()+"By"+domain.getDomainName().getCapFirstFieldName()+"Facade\","));
		sl.add(new Statement(4000L,2, "data: {"));
		sl.add(new Statement(5000L,3,  domain.getDomainName().getLowerFirstFieldName()+":$(\"#"+searchForm.getVarName()+"\").find(\"#"+myDomainNameField.getVarName()+"\").val()"));
		sl.add(new Statement(6000L,2, "},"));
		sl.add(new Statement(7000L,2, "dataType: 'json',"));
		sl.add(new Statement(8000L,2, "success: function(data, textStatus) {"));
		sl.add(new Statement(9000L,3, "if (data.success) "+generateDataRowsMethod.getStandardName()+"(data.data)"));  
		sl.add(new Statement(10000L,2, "},"));
		sl.add(new Statement(11000L,2, "complete : function(XMLHttpRequest, textStatus) {"));
		sl.add(new Statement(12000L,2, "},"));
		sl.add(new Statement(13000L,2, "error : function(XMLHttpRequest,textStatus,errorThrown) {"));
		sl.add(new Statement(14000L,3, "alert(\"Error:\"+textStatus);"));
		sl.add(new Statement(15000L,3, "alert(errorThrown.toString());"));
		sl.add(new Statement(16000L,2, "}"));
		sl.add(new Statement(17000L,1, "});"));
		
		method.setMethodStatementList(sl);
		return method;
	}
	
	public static JavascriptMethod generateListAllByPageMethod(Domain domain,Var pagesize, Var pagenum, Var last, Var jumppagenum1, Var jumppagenum2, Var pagecount, JavascriptMethod generateDataRowsMethod){
		JavascriptMethod method = new JavascriptMethod();
		method.setSerial(200);
		method.setStandardName("listAll"+StringUtil.capFirst(domain.getPlural())+"ByPage");
		Signature s1 = new Signature();
		s1.setName(pagesize.getVarName());
		s1.setPosition(1);
		s1.setType(new Type("var"));
		Signature s2 = new Signature();
		s2.setName(pagenum.getVarName());
		s2.setPosition(2);
		s2.setType(new Type("var"));
		Signature s3 = new Signature();
		s3.setName(last.getVarName());
		s3.setPosition(3);
		s3.setType(new Type("var"));
		method.addSignature(s1);
		method.addSignature(s2);
		method.addSignature(s3);
		
		StatementList sl = new StatementList();
		sl.add(new Statement(200L,1, "var myurl =\"../facade/listAll"+StringUtil.capFirst(domain.getPlural())+"ByPageFacade\";"));
		sl.add(new Statement(400L,1, "if ("+last.getVarName()+") myurl += \"?last=true\";")); 
		sl.add(new Statement(1000L,1, "if ("+pagesize.getVarName()+" == undefined || "+pagesize.getVarName()+" == null || "+pagesize.getVarName()+" <= 0) "+pagesize.getVarName()+"=10;"));
		sl.add(new Statement(2000L,1, "if ("+pagenum.getVarName()+" == undefined || "+pagenum.getVarName()+" == null || "+pagenum.getVarName()+" <= 0) "+pagenum.getVarName()+"=1;"));		
		sl.add(new Statement(3000L,1, "$.ajax({"));
		sl.add(new Statement(4000L,2, "type: \"post\","));
		sl.add(new Statement(5000L,2, "url: myurl,"));
		sl.add(new Statement(6000L,2, "data: {"));
		sl.add(new Statement(7000L,3, pagesize.getVarName()+":"+pagesize.getVarName()+","));
		sl.add(new Statement(8000L,3, pagenum.getVarName()+":"+pagenum.getVarName()+","));
		sl.add(new Statement(9000L,2, "},"));
		sl.add(new Statement(10000L,2, "dataType: 'json',"));
		sl.add(new Statement(11000L,2, "success: function(data, textStatus) {"));
		sl.add(new Statement(12000L,3, "if (data.success) {"));
		sl.add(new Statement(12100L,4, generateDataRowsMethod.getStandardName()+"(data.data);"));
		sl.add(new Statement(12200L,4, "$(\"#"+pagenum.getVarName()+"\").val(data."+pagenum.getVarName()+");"));
		sl.add(new Statement(12300L,4, "$(\"#"+jumppagenum1.getVarName()+"\").val(data."+pagenum.getVarName()+");"));
		sl.add(new Statement(12400L,4, "$(\"#"+jumppagenum2.getVarName()+"\").val(data."+pagenum.getVarName()+");"));
		sl.add(new Statement(12500L,4, "$(\"#"+pagecount.getVarName()+"\").val(data."+pagecount.getVarName()+");"));
		sl.add(new Statement(12600L,4, "$(\"#"+pagesize.getVarName()+"\").val(data."+pagesize.getVarName()+");"));
		sl.add(new Statement(13000L,3, "}"));
		sl.add(new Statement(13000L,2, "},"));
		sl.add(new Statement(14000L,2, "complete : function(XMLHttpRequest, textStatus) {"));
		sl.add(new Statement(15000L,2, "},"));
		sl.add(new Statement(16000L,2, "error : function(XMLHttpRequest,textStatus,errorThrown) {"));
		sl.add(new Statement(17000L,3, "alert(\"Error:\"+textStatus);"));
		sl.add(new Statement(18000L,3, "alert(errorThrown.toString());"));
		sl.add(new Statement(19000L,2, "}"));
		sl.add(new Statement(20000L,1, "});"));
		
		method.setMethodStatementList(sl);
		return method;
	}
	
	public static JavascriptMethod generateAddMethod(Domain domain,Var addDomainRow,Var pagesize,JavascriptMethod listAllByPage){
		JavascriptMethod method = new JavascriptMethod();
		method.setSerial(200);
		method.setStandardName("add"+domain.getCapFirstDomainName());
		StatementList sl = new StatementList();
		
		sl.add(new Statement(1000L,1, "$.ajax({"));
		sl.add(new Statement(2000L,2, "type: \"post\","));
		sl.add(new Statement(3000L,2, "url: \"../facade/add"+domain.getCapFirstDomainName()+"Facade\","));
		sl.add(new Statement(4000L,2, "data: {"));
		long rowIndex = 5000L;
		for (Field f : domain.getFieldsWithoutId()){
			sl.add(new Statement(rowIndex, 3,f.getLowerFirstFieldName() + ":$(\"#"+addDomainRow.getVarName()+"\").find(\"#"+f.getLowerFirstFieldName() +"\").val(),"));
			rowIndex += 1000L;
		}
		sl.add(new Statement(rowIndex,2, "},"));
		sl.add(new Statement(rowIndex + 1000L,2, "dataType: 'json',"));
		sl.add(new Statement(rowIndex + 2000L,2, "success: function(data, textStatus) {"));
		sl.add(new Statement(rowIndex + 3000L,3, "if (data.success) "+listAllByPage.getStandardName()+"("+pagesize.getVarName()+",1,true);"));
		rowIndex += 4000L;
		for (Field f : domain.getFieldsWithoutId()){
			sl.add(new Statement(rowIndex, 4, "$(\"#"+addDomainRow.getVarName()+"\").find(\"#"+f.getLowerFirstFieldName() +"\").val(\"\");"));
			rowIndex += 1000L;
		}
		sl.add(new Statement(rowIndex,3, "},"));
		sl.add(new Statement(rowIndex + 1000L,2, "complete : function(XMLHttpRequest, textStatus) {"));
		sl.add(new Statement(rowIndex + 2000L,2, "},"));
		sl.add(new Statement(rowIndex + 3000L,2, "error : function(XMLHttpRequest,textStatus,errorThrown) {"));
		sl.add(new Statement(rowIndex + 4000L,3, "alert(\"Error:\"+textStatus);"));
		sl.add(new Statement(rowIndex + 5000L,3, "alert(errorThrown.toString());"));
		sl.add(new Statement(rowIndex + 6000L,2, "}"));
		sl.add(new Statement(rowIndex + 7000L,1, "});"));
		
		method.setMethodStatementList(sl);
		return method;
	}
	

	public static JavascriptMethod generateDeleteMethod(Domain domain,Var domainId,Var pagesize,JavascriptMethod listAllByPage){
		JavascriptMethod method = new JavascriptMethod();
		method.setSerial(200);
		method.setStandardName("delete"+domain.getCapFirstDomainName());
		Signature did = new Signature(1,domainId.getVarName(),"var");
		method.addSignature(did);
		StatementList sl = new StatementList();
		
		sl.add(new Statement(1000L,1, "$.ajax({"));
		sl.add(new Statement(2000L,2, "type: \"post\","));
		sl.add(new Statement(3000L,2, "url: \"../facade/delete"+domain.getCapFirstDomainName()+"Facade\","));
		sl.add(new Statement(4000L,2, "data: {"));
		sl.add(new Statement(5000L,3,domain.getDomainId().getLowerFirstFieldName()+":"+domain.getDomainId().getLowerFirstFieldName()));
		sl.add(new Statement(6000L,2, "},"));
		sl.add(new Statement(7000L,2, "dataType: 'json',"));
		sl.add(new Statement(8000L,2, "success: function(data, textStatus) {"));
		sl.add(new Statement(9000L,3, "if (data.success) "+listAllByPage.getStandardName()+"("+pagesize.getVarName()+",1);"));
		sl.add(new Statement(10000L,2, "},"));		
		sl.add(new Statement(11000L,2, "complete : function(XMLHttpRequest, textStatus) {"));
		sl.add(new Statement(12000L,2, "},"));
		sl.add(new Statement(13000L,2, "error : function(XMLHttpRequest,textStatus,errorThrown) {"));
		sl.add(new Statement(14000L,3, "alert(\"Error:\"+textStatus);"));
		sl.add(new Statement(15000L,3, "alert(errorThrown.toString());"));
		sl.add(new Statement(16000L,2, "}"));
		sl.add(new Statement(17000L,1, "});"));
		
		method.setMethodStatementList(sl);
		return method;
	}
	
	public static JavascriptMethod generateSoftDeleteMethod(Domain domain,Var domainId,Var pagesize,JavascriptMethod listAllByPage){
		JavascriptMethod method = new JavascriptMethod();
		method.setSerial(200);
		method.setStandardName("softDelete"+domain.getCapFirstDomainName());
		Signature did = new Signature(1,domainId.getVarName(),"var");
		method.addSignature(did);
		StatementList sl = new StatementList();
		
		sl.add(new Statement(1000L,1, "$.ajax({"));
		sl.add(new Statement(2000L,2, "type: \"post\","));
		sl.add(new Statement(3000L,2, "url: \"../facade/softDelete"+domain.getCapFirstDomainName()+"Facade\","));
		sl.add(new Statement(4000L,2, "data: {"));
		sl.add(new Statement(5000L,3,domain.getDomainId().getLowerFirstFieldName()+":"+domain.getDomainId().getLowerFirstFieldName()));
		sl.add(new Statement(6000L,2, "},"));
		sl.add(new Statement(7000L,2, "dataType: 'json',"));
		sl.add(new Statement(8000L,2, "success: function(data, textStatus) {"));
		sl.add(new Statement(9000L,3, "if (data.success) "+listAllByPage.getStandardName()+"("+pagesize.getVarName()+",1);"));
		sl.add(new Statement(10000L,2, "},"));		
		sl.add(new Statement(11000L,2, "complete : function(XMLHttpRequest, textStatus) {"));
		sl.add(new Statement(12000L,2, "},"));
		sl.add(new Statement(13000L,2, "error : function(XMLHttpRequest,textStatus,errorThrown) {"));
		sl.add(new Statement(14000L,3, "alert(\"Error:\"+textStatus);"));
		sl.add(new Statement(15000L,3, "alert(errorThrown.toString());"));
		sl.add(new Statement(16000L,2, "}"));
		sl.add(new Statement(17000L,1, "});"));
		
		method.setMethodStatementList(sl);
		return method;
	}
	
	public static JavascriptMethod generateDeleteAllMethod(Domain domain, Var domainCheckboxGroup, Var checkAllBox, Var pagesize,JavascriptMethod listAllByPage){
		JavascriptMethod method = new JavascriptMethod();
		method.setSerial(200);
		method.setStandardName("deleteAll"+domain.getPlural());
		StatementList sl = new StatementList();
		
		sl.add(new Statement(1000L,1, "var idArr=$(\"input[name='"+domainCheckboxGroup.getVarName()+"']:checked\").val([]);"));  
		sl.add(new Statement(2000L,1, "var ids = \"\";"));
		sl.add(new Statement(3000L,1, "for(var i=0;i<idArr.length;i++){"));  
		sl.add(new Statement(4000L,2, "ids += idArr[i].value;"));
		sl.add(new Statement(5000L,2, "if (i < idArr.length-1) ids += \",\";"));  
		sl.add(new Statement(6000L,1, "}"));		
		sl.add(new Statement(7000L,1, "$.ajax({"));
		sl.add(new Statement(8000L,2, "type: \"post\","));
		sl.add(new Statement(9000L,2, "url: \"../facade/deleteAll"+domain.getPlural()+"Facade\","));
		sl.add(new Statement(10000L,2, "data: {"));
		sl.add(new Statement(11000L,3, "ids:ids"));
		sl.add(new Statement(12000L,2, "},"));
		sl.add(new Statement(13000L,2, "dataType: 'json',"));
		sl.add(new Statement(14000L,2, "success: function(data, textStatus) {"));
		sl.add(new Statement(15000L,3, "if (data.success) "+listAllByPage.getStandardName()+"("+pagesize.getVarName()+",1);"));
		sl.add(new Statement(16000L,2, "},"));		
		sl.add(new Statement(17000L,2, "complete : function(XMLHttpRequest, textStatus) {"));
		sl.add(new Statement(18000L,2, "},"));
		sl.add(new Statement(19000L,2, "error : function(XMLHttpRequest,textStatus,errorThrown) {"));
		sl.add(new Statement(20000L,3, "alert(\"Error:\"+textStatus);"));
		sl.add(new Statement(21000L,3, "alert(errorThrown.toString());"));
		sl.add(new Statement(22000L,2, "}"));
		sl.add(new Statement(23000L,1, "});"));
		sl.add(new Statement(24000L,1, "$(\"input[name='"+checkAllBox.getVarName()+"']\").get(0).checked = false;"));
		
		method.setMethodStatementList(sl);
		return method;
	}
	
	public static JavascriptMethod generateSoftDeleteAllMethod(Domain domain, Var domainCheckboxGroup, Var checkAllBox,Var pagesize, JavascriptMethod listAllByPage){
		JavascriptMethod method = new JavascriptMethod();
		method.setSerial(200);
		method.setStandardName("softDeleteAll"+domain.getPlural());
		StatementList sl = new StatementList();
		
		sl.add(new Statement(1000L,1, "var idArr=$(\"input[name='"+domainCheckboxGroup.getVarName()+"']:checked\").val([]);"));  
		sl.add(new Statement(2000L,1, "var ids = \"\";"));
		sl.add(new Statement(3000L,1, "for(var i=0;i<idArr.length;i++){"));  
		sl.add(new Statement(4000L,2, "ids += idArr[i].value;"));
		sl.add(new Statement(5000L,2, "if (i < idArr.length-1) ids += \",\";"));  
		sl.add(new Statement(6000L,1, "}"));		
		sl.add(new Statement(7000L,1, "$.ajax({"));
		sl.add(new Statement(8000L,2, "type: \"post\","));
		sl.add(new Statement(9000L,2, "url: \"../facade/softDeleteAll"+domain.getPlural()+"Facade\","));
		sl.add(new Statement(10000L,2, "data: {"));
		sl.add(new Statement(11000L,3, "ids:ids"));
		sl.add(new Statement(12000L,2, "},"));
		sl.add(new Statement(13000L,2, "dataType: 'json',"));
		sl.add(new Statement(14000L,2, "success: function(data, textStatus) {"));
		sl.add(new Statement(15000L,3, "if (data.success) "+listAllByPage.getStandardName()+"("+pagesize.getVarName()+",1);"));
		sl.add(new Statement(16000L,2, "},"));		
		sl.add(new Statement(17000L,2, "complete : function(XMLHttpRequest, textStatus) {"));
		sl.add(new Statement(18000L,2, "},"));
		sl.add(new Statement(19000L,2, "error : function(XMLHttpRequest,textStatus,errorThrown) {"));
		sl.add(new Statement(20000L,3, "alert(\"Error:\"+textStatus);"));
		sl.add(new Statement(21000L,3, "alert(errorThrown.toString());"));
		sl.add(new Statement(22000L,2, "}"));
		sl.add(new Statement(23000L,1, "});"));
		sl.add(new Statement(24000L,1, "$(\"input[name='"+checkAllBox.getVarName()+"']\").get(0).checked = false;"));
		
		method.setMethodStatementList(sl);
		return method;
	}
	
	public static JavascriptMethod generateUpdateMethod(Domain domain,Var domainForm, Var dataRow, Var pagesize, JavascriptMethod listAllByPage){
		JavascriptMethod method = new JavascriptMethod();
		method.setSerial(200);
		method.setStandardName("update"+domain.getCapFirstDomainName());
		Signature myindex = new Signature();
		myindex.setName("myindex");
		myindex.setPosition(1);
		myindex.setType(new Type("var"));
		method.addSignature(myindex);
		StatementList sl = new StatementList();
		
		sl.add(new Statement(1000L,1, "$.ajax({"));
		sl.add(new Statement(2000L,2, "type: \"post\","));
		sl.add(new Statement(3000L,2, "url: \"../facade/update"+domain.getCapFirstDomainName()+"Facade\","));
		sl.add(new Statement(4000L,2, "data: {"));		
		sl.add(new Statement(5000L, 3,domain.getDomainId().getLowerFirstFieldName()+ ":$(\"#"+domainForm.getVarName()+"\").find(\"#"+dataRow.getVarName()+"\"+"+myindex.getName()+").find(\"#"+domain.getDomainId().getLowerFirstFieldName() +"\").val(),"));
		long rowIndex = 6000L;
		for (Field f : domain.getFieldsWithoutId()){
			sl.add(new Statement(rowIndex, 3,f.getLowerFirstFieldName() + ":$(\"#"+domainForm.getVarName()+"\").find(\"#"+dataRow.getVarName()+"\"+"+myindex.getName()+").find(\"#"+f.getLowerFirstFieldName() +"\").val(),"));
			rowIndex += 1000L;
		}
		sl.add(new Statement(rowIndex,2, "},"));
		sl.add(new Statement(rowIndex + 1000L,2, "dataType: 'json',"));
		sl.add(new Statement(rowIndex + 2000L,2, "success: function(data, textStatus) {"));
		sl.add(new Statement(rowIndex + 3000L,3, "if (data.success) "+listAllByPage.getStandardName()+"("+pagesize.getVarName()+",1);"));
		sl.add(new Statement(rowIndex + 4000L,2, "},"));
		sl.add(new Statement(rowIndex + 5000L,2, "complete : function(XMLHttpRequest, textStatus) {"));
		sl.add(new Statement(rowIndex + 6000L,2, "},"));
		sl.add(new Statement(rowIndex + 7000L,2, "error : function(XMLHttpRequest,textStatus,errorThrown) {"));
		sl.add(new Statement(rowIndex + 8000L,3, "alert(\"Error:\"+textStatus);"));
		sl.add(new Statement(rowIndex + 9000L,3, "alert(errorThrown.toString());"));
		sl.add(new Statement(rowIndex + 10000L,2, "}"));
		sl.add(new Statement(rowIndex + 11000L,1, "});"));
		
		method.setMethodStatementList(sl);
		return method;
	}
	
	public static JavascriptMethod generateUpdateAllMethod(Domain domain,Var domainForm, Var dataRow,JavascriptMethod listAllByPage){
		JavascriptMethod method = new JavascriptMethod();
		method.setSerial(200);
		method.setStandardName("updateAll"+domain.getPlural());

		StatementList sl = new StatementList();
		sl.add(new Statement(1000,1, "alert(\"update all\");"));
		method.setMethodStatementList(sl);
		return method;
	}
	

	public static JavascriptMethod generateJump1Method(Var pagesize, Var pagenum,Var jumppagenum1,JavascriptMethod listAllByPage){
		JavascriptMethod method = new JavascriptMethod();
		method.setSerial(200);
		method.setStandardName("jump1");

		StatementList sl = new StatementList();
		sl.add(new Statement(1000L,1, "var "+pagesize.getVarName() + " = $(\"#"+pagesize.getVarName() +"\").val();"));
		sl.add(new Statement(2000L,1, "var "+pagenum.getVarName() + " = $(\"#"+jumppagenum1.getVarName() +"\").val();"));
		sl.add(new Statement(3000L,1, StringUtil.lowerFirst(listAllByPage.getStandardName())+"("+pagesize.getVarName()+","+pagenum.getVarName() +")"));
		method.setMethodStatementList(sl);
		return method;
	}
	
	public static JavascriptMethod generateJump2Method(Var pagesize, Var pagenum,Var jumppagenum2,JavascriptMethod listAllByPage){
		JavascriptMethod method = new JavascriptMethod();
		method.setSerial(200);
		method.setStandardName("jump2");

		StatementList sl = new StatementList();
		sl.add(new Statement(1000L,1, "var "+pagesize.getVarName() + " = $(\"#"+pagesize.getVarName() +"\").val();"));
		sl.add(new Statement(2000L,1, "var "+pagenum.getVarName() + " = $(\"#"+jumppagenum2.getVarName() +"\").val();"));
		sl.add(new Statement(3000L,1, StringUtil.lowerFirst(listAllByPage.getStandardName())+"("+pagesize.getVarName()+","+pagenum.getVarName() +")"));
		method.setMethodStatementList(sl);
		return method;
	}

	public static JavascriptMethod generateFirstMethod(Var pagesize, JavascriptMethod listAllByPage){
		JavascriptMethod method = new JavascriptMethod();
		method.setSerial(200);
		method.setStandardName("first");

		StatementList sl = new StatementList();
		sl.add(new Statement(1000L,1, "var "+pagesize.getVarName() + " = $(\"#"+pagesize.getVarName() +"\").val();"));
		sl.add(new Statement(2000L,1, StringUtil.lowerFirst(listAllByPage.getStandardName())+"("+pagesize.getVarName()+",1)"));
		method.setMethodStatementList(sl);
		return method;
	}
	
	public static JavascriptMethod generatePrevMethod(Var pagesize,Var pagenum, JavascriptMethod listAllByPage){
		JavascriptMethod method = new JavascriptMethod();
		method.setSerial(200);
		method.setStandardName("prev");

		StatementList sl = new StatementList();
		sl.add(new Statement(1000L,1, "var "+pagesize.getVarName() + " = $(\"#"+pagesize.getVarName() +"\").val();"));
		sl.add(new Statement(2000L,1, "var "+pagenum.getVarName() + " = parseInt($(\"#"+pagenum.getVarName() +"\").val()) - 1;"));
		sl.add(new Statement(3000L,1, StringUtil.lowerFirst(listAllByPage.getStandardName())+"("+pagesize.getVarName()+","+pagenum.getVarName() +")"));
		method.setMethodStatementList(sl);
		return method;
	}
	
	public static JavascriptMethod generateNextMethod(Var pagesize,Var pagenum, JavascriptMethod listAllByPage){
		JavascriptMethod method = new JavascriptMethod();
		method.setSerial(200);
		method.setStandardName("next");

		StatementList sl = new StatementList();
		sl.add(new Statement(1000L,1, "var "+pagesize.getVarName() + " = $(\"#"+pagesize.getVarName() +"\").val();"));
		sl.add(new Statement(2000L,1, "var "+pagenum.getVarName() + " = parseInt($(\"#"+pagenum.getVarName() +"\").val()) + 1;"));
		sl.add(new Statement(3000L,1, StringUtil.lowerFirst(listAllByPage.getStandardName())+"("+pagesize.getVarName()+","+pagenum.getVarName() +")"));
		method.setMethodStatementList(sl);
		return method;
	}

	public static JavascriptMethod generateLastMethod(Var pagesize,Var pagecount, JavascriptMethod listAllByPage){
		JavascriptMethod method = new JavascriptMethod();
		method.setSerial(200);
		method.setStandardName("last");

		StatementList sl = new StatementList();
		sl.add(new Statement(1000L,1, "var "+pagesize.getVarName() + " = $(\"#"+pagesize.getVarName() +"\").val();"));
		sl.add(new Statement(2000L,1, "var "+pagecount.getVarName() + " = $(\"#"+pagecount.getVarName() +"\").val();"));
		sl.add(new Statement(3000L,1, StringUtil.lowerFirst(listAllByPage.getStandardName())+"("+pagesize.getVarName()+","+pagecount.getVarName() +")"));
		method.setMethodStatementList(sl);
		return method;
	}
}
