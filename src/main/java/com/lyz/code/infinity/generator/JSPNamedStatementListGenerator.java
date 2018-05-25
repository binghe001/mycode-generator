package com.lyz.code.infinity.generator;

import java.util.ArrayList;
import java.util.List;

import com.lyz.code.infinity.core.Writeable;
import com.lyz.code.infinity.domain.Controller;
import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Facade;
import com.lyz.code.infinity.domain.Field;
import com.lyz.code.infinity.domain.JavascriptMethod;
import com.lyz.code.infinity.domain.Statement;
import com.lyz.code.infinity.domain.StatementList;
import com.lyz.code.infinity.domain.Type;
import com.lyz.code.infinity.domain.Var;
import com.lyz.code.infinity.utils.StringUtil;
import com.lyz.code.infinity.utils.WriteableUtil;

public class JSPNamedStatementListGenerator {
	public static StatementList generateTableColumnsUsingIdRenameFieldListStatementList(long serial,int indent,Domain domain){
		try {
			List<Writeable> list = new ArrayList<Writeable>();
			Var mydomain = new Var(StringUtil.lowerFirst(domain.getStandardName()),new Type(StringUtil.lowerFirst(domain.getStandardName()),domain.getPackageToken()));
			list.add(new Statement(100,2,"<td><input type='text' name='"+domain.getDomainId().getFieldName()+"1' id='"+domain.getDomainId().getFieldName()+"1' size='8' value='<%= "+mydomain.getVarName()+".get"+StringUtil.capFirst(domain.getDomainId().getFieldName())+"()%>'/></td>"));
			int myserial = 200;
			for (Field f:domain.getFieldsWithoutId()){
				list.add(new Statement(myserial,2,"<td><input type='text' name='"+f.getFieldName()+"' id='"+f.getFieldName()+"' size='8' value='<%= "+mydomain.getVarName()+".get"+StringUtil.capFirst(f.getFieldName())+"()%>'/></td>"));
				myserial += 100;
			}
			StatementList sList = WriteableUtil.merge(list);
			sList.setSerial(serial);
			return sList;
		} catch (Exception e){
			return null;
		}
	}
	
	public static StatementList generateTableColumnHeadsUsingIdRenameFieldListStatementList(long serial,int indent,Domain domain){
		try {
			List<Writeable> list = new ArrayList<Writeable>();
			Var mydomain = new Var(StringUtil.lowerFirst(domain.getStandardName()),new Type(StringUtil.lowerFirst(domain.getStandardName()),domain.getPackageToken()));
			list.add(new Statement(100,2,"<th>"+StringUtil.capFirst(domain.getDomainId().getFieldName()+"</th>")));
			int myserial = 200;
			for (Field f:domain.getFieldsWithoutId()){
				list.add(new Statement(myserial,2,"<th>"+StringUtil.capFirst(f.getFieldName())+"</th>"));
				myserial += 100;
			}
			StatementList sList = WriteableUtil.merge(list);
			sList.setSerial(serial);
			return sList;
		} catch (Exception e){
			return null;
		}
	}
	
	public static StatementList generateTableColumnHeadsUsingIdRenameFieldListWithOperationColumnStatementList(long serial,int indent,Domain domain){
		try {
			List<Writeable> list = new ArrayList<Writeable>();
			Var mydomain = new Var(StringUtil.lowerFirst(domain.getStandardName()),new Type(StringUtil.lowerFirst(domain.getStandardName()),domain.getPackageToken()));
			list.add(new Statement(100,2,"<th>"+StringUtil.capFirst(domain.getDomainId().getFieldName()+"</th>")));
			int myserial = 200;
			for (Field f:domain.getFieldsWithoutId()){
				list.add(new Statement(myserial,2,"<th>"+StringUtil.capFirst(f.getFieldName())+"</th>"));
				myserial += 100;
			}
			list.add(new Statement(myserial,2,"<th>Operation</th>"));
			StatementList sList = WriteableUtil.merge(list);
			sList.setSerial(serial);
			return sList;
		} catch (Exception e){
			return null;
		}
	}
	
	public static StatementList generateTableColumnHeadsUsingIdRenameFieldListWithOperationColumnWithCheckAllBoxStatementList(long serial,int indent,Domain domain,Var checkAllBox, JavascriptMethod checkAllFunction){
		try {
			List<Writeable> list = new ArrayList<Writeable>();
			Var mydomain = new Var(StringUtil.lowerFirst(domain.getStandardName()),new Type(StringUtil.lowerFirst(domain.getStandardName()),domain.getPackageToken()));
			list.add(new Statement(100,2,"<th><input type=\"checkbox\" name=\""+checkAllBox.getVarName()+"\" id=\""+checkAllBox.getVarName()+"\" onclick=\""+checkAllFunction.getStandardName()+"()\"/></th>"));
			list.add(new Statement(100,2,"<th>"+StringUtil.capFirst(domain.getDomainId().getFieldName()+"</th>")));
			int myserial = 200;
			for (Field f:domain.getFieldsWithoutId()){
				list.add(new Statement(myserial,2,"<th>"+StringUtil.capFirst(f.getFieldName())+"</th>"));
				myserial += 100;
			}
			list.add(new Statement(myserial,2,"<th>Operation</th>"));
			StatementList sList = WriteableUtil.merge(list);
			sList.setSerial(serial);
			return sList;
		} catch (Exception e){
			return null;
		}
	}
	
	public static StatementList generateTableRowsUsingIdRenameFieldListWithoutSettingValueStatementList(long serial,int indent,Domain domain){
		try {
			List<Writeable> list = new ArrayList<Writeable>();
			list.add(new Statement(100L,2,"<td>&nbsp;</td>"));
			Var mydomain = new Var(StringUtil.lowerFirst(domain.getStandardName()),new Type(StringUtil.lowerFirst(domain.getStandardName()),domain.getPackageToken()));
			list.add(new Statement(200L,2,"<td><input type='text' name='"+domain.getDomainId().getFieldName()+"1' id='"+domain.getDomainId().getFieldName()+"1' size='8' value=''/></td>"));
			long myserial = 300L;
			for (Field f:domain.getFieldsWithoutId()){
				list.add(new Statement(myserial,2,"<td><input type='text' name='"+f.getFieldName()+"' id='"+f.getFieldName()+"' size='8' value=''/></td>"));
				myserial += 100L;
			}
			StatementList sList = WriteableUtil.merge(list);
			sList.setSerial(serial);
			return sList;
		} catch (Exception e){
			return null;
		}
	}
	
	public static StatementList generateTableRowsUsingIdRenameFieldListWithoutSettingValueWithoutNbspStatementList(long serial,int indent,Domain domain){
		try {
			List<Writeable> list = new ArrayList<Writeable>();
			Var mydomain = new Var(StringUtil.lowerFirst(domain.getStandardName()),new Type(StringUtil.lowerFirst(domain.getStandardName()),domain.getPackageToken()));
			list.add(new Statement(100L,2,"<td><input type='text' name='"+domain.getDomainId().getFieldName()+"1' id='"+domain.getDomainId().getFieldName()+"1' size='8' value=''/></td>"));
			long myserial = 200L;
			for (Field f:domain.getFieldsWithoutId()){
				list.add(new Statement(myserial,2,"<td><input type='text' name='"+f.getFieldName()+"' id='"+f.getFieldName()+"' size='8' value=''/></td>"));
				myserial += 100L;
			}
			StatementList sList = WriteableUtil.merge(list);
			sList.setSerial(serial);
			return sList;
		} catch (Exception e){
			return null;
		}
	}
	
	public static StatementList generateDeleteUsingHiddenIdStatementList(long serial,int indent,Domain domain,Controller deleteController){
		try {
			List<Writeable> list = new ArrayList<Writeable>();
			Var mydomain = new Var(StringUtil.lowerFirst(domain.getStandardName()),new Type(StringUtil.lowerFirst(domain.getStandardName()),domain.getPackageToken()));
			list.add(new Statement(100,2,"<form action='../controller/"+StringUtil.lowerFirst(deleteController.getStandardName())+"' method='post'>"));
			list.add(new Statement(200,2,"<input type='hidden' name='"+StringUtil.lowerFirst(domain.getDomainId().getFieldName())+"' value='<%="+mydomain.getVarName()+".get"+StringUtil.capFirst(domain.getDomainId().getFieldName())+"()"+"%>'/>"));
			list.add(new Statement(200,2,"<input type='submit' value='Delete' /></form>"));

			StatementList sList = WriteableUtil.merge(list);
			sList.setSerial(serial);
			return sList;
		} catch (Exception e){
			return null;
		}
	}
	
	public static StatementList generateSoftDeleteUsingHiddenIdStatementList(long serial,int indent,Domain domain,Controller softDeleteController){
		try {
			List<Writeable> list = new ArrayList<Writeable>();
			Var mydomain = new Var(StringUtil.lowerFirst(domain.getStandardName()),new Type(StringUtil.lowerFirst(domain.getStandardName()),domain.getPackageToken()));
			list.add(new Statement(100,2,"<form action='../controller/"+StringUtil.lowerFirst(softDeleteController.getStandardName())+"' method='post'>"));
			list.add(new Statement(200,2,"<input type='hidden' name='"+StringUtil.lowerFirst(domain.getDomainId().getFieldName())+"' value='<%="+mydomain.getVarName()+".get"+StringUtil.capFirst(domain.getDomainId().getFieldName())+"()"+"%>'/>"));
			list.add(new Statement(200,2,"<input type='submit' value='Soft Delete' /></form>"));

			StatementList sList = WriteableUtil.merge(list);
			sList.setSerial(serial);
			return sList;
		} catch (Exception e){
			return null;
		}
	}
	
	public static StatementList generateSearchByNameTableFormWithRequestVarStatementList(long serial,int indent,Field field,Controller searchController){
		try {
			List<Writeable> list = new ArrayList<Writeable>();
			list.add(new Statement(100,2,"<form action='../controller/"+StringUtil.lowerFirst(searchController.getStandardName()) +"' method='post'>\n"));
			list.add(JSPNamedStatementGenerator.getTableWithAAClassStartStatement(200, 2));
			list.add(JSPNamedStatementGenerator.getTrStartStatement(300, 2));
			list.add(JSPNamedStatementGenerator.getTdStartStatement(400, 2));
			list.add(new Statement(500,2,StringUtil.capFirst(field.getFieldName())));
			list.add(JSPNamedStatementGenerator.getTdEndStatement(600, 2));		
			list.add(JSPNamedStatementGenerator.getTdStartStatement(700, 2));
			list.add(new Statement(800,2,"<input type=\"text\" size=\"16\" value=\"<%=request.getParameter(\""+StringUtil.lowerFirst(field.getFieldName())+"\")==null?\"\":request.getParameter(\""+StringUtil.lowerFirst(field.getFieldName())+"\")%>\" name=\""+StringUtil.lowerFirst(field.getFieldName())+"\" id=\""+StringUtil.lowerFirst(field.getFieldName())+"\"/>"));
			list.add(JSPNamedStatementGenerator.getTdEndStatement(900, 2));
			list.add(JSPNamedStatementGenerator.getTdStartStatement(1000, 2));
			list.add(new Statement(1100,2,"<input type=\"submit\" value=\"Search\"/>"));
			list.add(JSPNamedStatementGenerator.getTdEndStatement(1200, 2));
			list.add(JSPNamedStatementGenerator.getTrEndStatement(1300, 2));
			list.add(JSPNamedStatementGenerator.getTableEndStatement(1400, 2));
			list.add(JSPNamedStatementGenerator.getFormEndStatement(1500, 2));
			list.add(JSPNamedStatementGenerator.getBrStatement(1600, 2));
			list.add(JSPNamedStatementGenerator.getBrStatement(1700, 2));
			
			StatementList sList = WriteableUtil.merge(list);
			sList.setSerial(serial);
			return sList;
		} catch (Exception e){
			return null;
		}
	}
	
	public static StatementList generateSearchByNameTableFormSearchByFacadeStatementList(long serial,int indent,Field field,Facade searchFacade, Var searchForm, JavascriptMethod searchByNameJSMethod, Var myDomainNameField){
		try {
			List<Writeable> list = new ArrayList<Writeable>();
			list.add(new Statement(100,2,"<form action='' name='"+searchForm.getVarName()+"' id='"+searchForm.getVarName()+"' method='post'>\n"));
			list.add(JSPNamedStatementGenerator.getTableWithAAClassStartStatement(200, 2));
			list.add(JSPNamedStatementGenerator.getTrStartStatement(300, 2));
			list.add(JSPNamedStatementGenerator.getTdStartStatement(400, 2));
			list.add(new Statement(500,2,StringUtil.capFirst(field.getFieldName())));
			list.add(JSPNamedStatementGenerator.getTdEndStatement(600, 2));		
			list.add(JSPNamedStatementGenerator.getTdStartStatement(700, 2));
			list.add(new Statement(800,2,"<input type=\"text\" size=\"16\" value=\"\" name=\""+myDomainNameField.getVarName()+"\" id=\""+myDomainNameField.getVarName()+"\"/>"));
			list.add(JSPNamedStatementGenerator.getTdEndStatement(900, 2));
			list.add(JSPNamedStatementGenerator.getTdStartStatement(1000, 2));
			list.add(new Statement(1100,2,"<input type=\"button\" onclick=\""+searchByNameJSMethod.getLowerFirstMethodName()+"()\" value=\"Search\"/>"));
			list.add(JSPNamedStatementGenerator.getTdEndStatement(1200, 2));
			list.add(JSPNamedStatementGenerator.getTrEndStatement(1300, 2));
			list.add(JSPNamedStatementGenerator.getTableEndStatement(1400, 2));
			list.add(JSPNamedStatementGenerator.getFormEndStatement(1500, 2));
			list.add(JSPNamedStatementGenerator.getBrStatement(1600, 2));
			list.add(JSPNamedStatementGenerator.getBrStatement(1700, 2));
			
			StatementList sList = WriteableUtil.merge(list);
			sList.setSerial(serial);
			return sList;
		} catch (Exception e){
			return null;
		}
	}
}
