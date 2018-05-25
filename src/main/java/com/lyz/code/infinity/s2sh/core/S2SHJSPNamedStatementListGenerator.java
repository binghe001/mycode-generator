package com.lyz.code.infinity.s2sh.core;

import java.util.ArrayList;
import java.util.List;

import com.lyz.code.infinity.core.Writeable;
import com.lyz.code.infinity.domain.Controller;
import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Field;
import com.lyz.code.infinity.domain.Method;
import com.lyz.code.infinity.domain.Statement;
import com.lyz.code.infinity.domain.StatementList;
import com.lyz.code.infinity.domain.Type;
import com.lyz.code.infinity.domain.Var;
import com.lyz.code.infinity.utils.StringUtil;
import com.lyz.code.infinity.utils.WriteableUtil;

public class S2SHJSPNamedStatementListGenerator {
	public static StatementList generateTableColumnsUsingIdRenameFieldListStatementList(long serial,int indent,Domain domain){
		try {
			List<Writeable> list = new ArrayList<Writeable>();
			Var mydomain = new Var(StringUtil.lowerFirst(domain.getStandardName()),domain.getType());
			list.add(new Statement(100,2,"<td><input type='text' name='"+domain.getLowerFirstDomainName()+"."+domain.getDomainId().getFieldName()+"1' id='"+domain.getDomainId().getFieldName()+"1' size='8' value='<%= "+mydomain.getVarName()+".get"+StringUtil.capFirst(domain.getDomainId().getFieldName())+"()%>'/></td>"));
			int myserial = 200;
			for (Field f:domain.getFieldsWithoutId()){
				list.add(new Statement(myserial,2,"<td><input type='text' name='"+domain.getLowerFirstDomainName()+"."+f.getFieldName()+"' id='"+f.getFieldName()+"' size='8' value='<%= "+mydomain.getVarName()+".get"+StringUtil.capFirst(f.getFieldName())+"()%>'/></td>"));
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
	
	public static StatementList generateTableRowsUsingIdRenameFieldListWithoutSettingValueStatementList(long serial,int indent,Domain domain){
		try {
			List<Writeable> list = new ArrayList<Writeable>();
			Var mydomain = new Var(StringUtil.lowerFirst(domain.getStandardName()),new Type(StringUtil.lowerFirst(domain.getStandardName()),domain.getPackageToken()));
			list.add(new Statement(100,2,"<td><input type='text' name='"+domain.getLowerFirstDomainName()+"."+domain.getDomainId().getFieldName()+"1' id='"+domain.getDomainId().getFieldName()+"1' size='8' value=''/></td>"));
			int myserial = 200;
			for (Field f:domain.getFieldsWithoutId()){
				list.add(new Statement(myserial,2,"<td><input type='text' name='"+domain.getLowerFirstDomainName()+"."+f.getFieldName()+"' id='"+f.getFieldName()+"' size='8' value=''/></td>"));
				myserial += 100;
			}
			StatementList sList = WriteableUtil.merge(list);
			sList.setSerial(serial);
			return sList;
		} catch (Exception e){
			return null;
		}
	}
	
	public static StatementList generateDeleteUsingHiddenIdStatementList(long serial,int indent,Domain domain,Method deleteMethod){
		try {
			List<Writeable> list = new ArrayList<Writeable>();
			Var mydomain = new Var(StringUtil.lowerFirst(domain.getStandardName()),new Type(StringUtil.lowerFirst(domain.getStandardName()),domain.getPackageToken()));
			list.add(new Statement(100,2,"<form action='../action/"+StringUtil.lowerFirst(deleteMethod.getStandardName())+"' method='post'>"));
			list.add(new Statement(200,2,"<input type='hidden' name='"+StringUtil.lowerFirst(domain.getStandardName())+"."+StringUtil.lowerFirst(domain.getDomainId().getFieldName())+"' value='<%="+mydomain.getVarName()+".get"+StringUtil.capFirst(domain.getDomainId().getFieldName())+"()"+"%>'/>"));
			list.add(new Statement(200,2,"<input type='submit' value='Delete' /></form>"));

			StatementList sList = WriteableUtil.merge(list);
			sList.setSerial(serial);
			return sList;
		} catch (Exception e){
			return null;
		}
	}
	
	public static StatementList generateSoftDeleteUsingHiddenIdStatementList(long serial,int indent,Domain domain,Method softDeleteMethod){
		try {
			List<Writeable> list = new ArrayList<Writeable>();
			Var mydomain = new Var(StringUtil.lowerFirst(domain.getStandardName()),new Type(StringUtil.lowerFirst(domain.getStandardName()),domain.getPackageToken()));
			list.add(new Statement(100,2,"<form action='../action/"+StringUtil.lowerFirst(softDeleteMethod.getStandardName())+"' method='post'>"));
			list.add(new Statement(200,2,"<input type='hidden' name='"+StringUtil.lowerFirst(domain.getStandardName())+"."+StringUtil.lowerFirst(domain.getDomainId().getFieldName())+"' value='<%="+mydomain.getVarName()+".get"+StringUtil.capFirst(domain.getDomainId().getFieldName())+"()"+"%>'/>"));
			list.add(new Statement(200,2,"<input type='submit' value='Soft Delete' /></form>"));

			StatementList sList = WriteableUtil.merge(list);
			sList.setSerial(serial);
			return sList;
		} catch (Exception e){
			return null;
		}
	}
	
	public static StatementList generateSearchByNameTableFormWithRequestVarStatementList(long serial,int indent,Domain domain,Field field,Method searchMethod){
		try {
			List<Writeable> list = new ArrayList<Writeable>();
			list.add(new Statement(100,2,"<form action='../action/"+StringUtil.lowerFirst(searchMethod.getStandardName()) +"' method='post'>\n"));
			list.add(S2SHJSPNamedStatementGenerator.getTableWithAAClassStartStatement(200, 2));
			list.add(S2SHJSPNamedStatementGenerator.getTrStartStatement(300, 2));
			list.add(S2SHJSPNamedStatementGenerator.getTdStartStatement(400, 2));
			list.add(new Statement(500,2,StringUtil.capFirst(field.getFieldName())));
			list.add(S2SHJSPNamedStatementGenerator.getTdEndStatement(600, 2));		
			list.add(S2SHJSPNamedStatementGenerator.getTdStartStatement(700, 2));
			list.add(new Statement(800,2,"<input type=\"text\" size=\"16\" value=\"<%=request.getParameter(\""+StringUtil.lowerFirst(field.getFieldName())+"\")==null?\"\":request.getParameter(\""+StringUtil.lowerFirst(field.getFieldName())+"\")%>\" name=\""+domain.getLowerFirstDomainName()+"."+StringUtil.lowerFirst(field.getFieldName())+"\" id=\""+StringUtil.lowerFirst(field.getFieldName())+"\"/>"));
			list.add(S2SHJSPNamedStatementGenerator.getTdEndStatement(900, 2));
			list.add(S2SHJSPNamedStatementGenerator.getTdStartStatement(1000, 2));
			list.add(new Statement(1100,2,"<input type=\"submit\" value=\"Search\"/>"));
			list.add(S2SHJSPNamedStatementGenerator.getTdEndStatement(1200, 2));
			list.add(S2SHJSPNamedStatementGenerator.getTrEndStatement(1300, 2));
			list.add(S2SHJSPNamedStatementGenerator.getTableEndStatement(1400, 2));
			list.add(S2SHJSPNamedStatementGenerator.getFormEndStatement(1500, 2));
			list.add(S2SHJSPNamedStatementGenerator.getBrStatement(1600, 2));
			list.add(S2SHJSPNamedStatementGenerator.getBrStatement(1700, 2));
			
			StatementList sList = WriteableUtil.merge(list);
			sList.setSerial(serial);
			return sList;
		} catch (Exception e){
			return null;
		}
	}
}
