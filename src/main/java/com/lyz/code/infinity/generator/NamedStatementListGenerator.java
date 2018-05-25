package com.lyz.code.infinity.generator;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lyz.code.infinity.core.Writeable;
import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Field;
import com.lyz.code.infinity.domain.Method;
import com.lyz.code.infinity.domain.Statement;
import com.lyz.code.infinity.domain.StatementList;
import com.lyz.code.infinity.domain.Type;
import com.lyz.code.infinity.domain.Var;
import com.lyz.code.infinity.limitedverb.CountPage;
import com.lyz.code.infinity.utils.FieldUtil;
import com.lyz.code.infinity.utils.InterVarUtil;
import com.lyz.code.infinity.utils.StringUtil;
import com.lyz.code.infinity.utils.WriteableUtil;

public class NamedStatementListGenerator {
	public static StatementList generateSelectAllQueryStatementList(long serial,int indent,Domain domain){
		try {
			List<Writeable> list = new ArrayList<Writeable>();
			list.add(NamedStatementGenerator.getSelectAllSqlStatement(100L,indent,domain,InterVarUtil.DB.query));
			list.add(NamedStatementGenerator.getPrepareStatement(200L,indent,InterVarUtil.DB.ps, InterVarUtil.DB.query, InterVarUtil.DB.connection));
			list.add(NamedStatementGenerator.getPrepareStatementExcuteQuery(300L,indent, InterVarUtil.DB.result, InterVarUtil.DB.ps));
			list.add(NamedStatementGenerator.getPrepareDaomainArrayList(400L,indent, domain));
			list.add(NamedStatementGenerator.getResultSetWhileNextLoopHead(500L,indent, InterVarUtil.DB.result));  
			list.add(NamedStatementGenerator.getSingleLineComment(600L,indent+1,"Build the list object."));
			list.add(NamedStatementGenerator.getPrepareDomainVarInit(700L,indent+1, domain));
			list.add(NamedStatementListGenerator.generateSetDomainDataFromResultSet(800L,indent+1,domain, InterVarUtil.DB.result));
			list.add(NamedStatementGenerator.getSingleLineComment(900L,indent+1, "Build the object list."));			
			list.add(NamedStatementGenerator.getAddDomaintoList(1000L,indent+1, domain, InterVarUtil.Container.getList(domain)));
			list.add(NamedStatementGenerator.getLoopFooter(1100L,indent));
			list.add(NamedStatementGenerator.getReturnDomainList(1200,indent, domain, InterVarUtil.Container.getList(domain)));

			StatementList sList = WriteableUtil.merge(list);
			sList.setSerial(serial);
			return sList;
		} catch (Exception e){
			return null;
		}
	}
	
	public static StatementList generateSelectAllByPageQueryStatementList(long serial,int indent,Domain domain,Var pagesize, Var pagenum, Var service, CountPage countPage){
		try {
			List<Writeable> list = new ArrayList<Writeable>();
			list.add(NamedStatementGenerator.getSelectAllByPageSqlStatement(1000L,indent,domain,InterVarUtil.DB.query));
			Var limitstart = new Var("limitstart",new Type("int"));
			Var limitcount = new Var("limitcount",new Type("int"));
			Var pagecount = new Var("pagecount",new Type("int"));
			countPage.setDomain(domain);
			list.add(new Statement(2000L,indent, limitstart.generateTypeVarString() + " = ("+ pagenum.getVarName() +"-1)*"+pagesize.getVarName()+";"));
			list.add(new Statement(3000L,indent, limitcount.generateTypeVarString() + " = " + pagesize.getVarName() + ";"));

			list.add(NamedStatementGenerator.getPrepareStatement(4000L,indent,InterVarUtil.DB.ps, InterVarUtil.DB.query, InterVarUtil.DB.connection));
			list.add(new Statement(5000L,indent, pagecount.generateTypeVarString() + " = " + "this."+countPage.getVerbName()+"("+InterVarUtil.DB.connection.getVarName()+","+pagesize.getVarName()+");"));
			list.add(new Statement(6000L,indent, "if ("+pagenum.getVarName()+ " <= 1) " +pagenum.getVarName() +" = 1;"));
			list.add(new Statement(7000L,indent, "if ("+pagenum.getVarName()+ " >= "+pagecount.getVarName()+ ") "+pagenum.getVarName() + " = " +pagecount.getVarName()+";"));

			list.add(NamedStatementListGenerator.generatePsSetDomainPagingFields(8000L, indent, domain, InterVarUtil.DB.ps,limitstart,limitcount));
			list.add(NamedStatementGenerator.getPrepareStatementExcuteQuery(9000L,indent, InterVarUtil.DB.result, InterVarUtil.DB.ps));
			list.add(NamedStatementGenerator.getPrepareDaomainArrayList(10000L,indent, domain));
			list.add(NamedStatementGenerator.getResultSetWhileNextLoopHead(11000L,indent, InterVarUtil.DB.result));  
			list.add(NamedStatementGenerator.getSingleLineComment(12000L,indent+1,"Build the list object."));
			list.add(NamedStatementGenerator.getPrepareDomainVarInit(13000L,indent+1, domain));
			list.add(NamedStatementListGenerator.generateSetDomainDataFromResultSet(14000L,indent+1,domain, InterVarUtil.DB.result));
			list.add(NamedStatementGenerator.getSingleLineComment(15000L,indent+1, "Build the object list."));			
			list.add(NamedStatementGenerator.getAddDomaintoList(16000L,indent+1, domain, InterVarUtil.Container.getList(domain)));
			list.add(NamedStatementGenerator.getLoopFooter(17000L,indent));
			list.add(NamedStatementGenerator.getReturnDomainList(18000,indent, domain, InterVarUtil.Container.getList(domain)));

			StatementList sList = WriteableUtil.merge(list);
			sList.setSerial(serial);
			return sList;
		} catch (Exception e){
			return null;
		}
	}
	
	public static StatementList generateCountPageStatementList(long serial,int indent,Domain domain, Var pageSize){
		try {
			List<Writeable> list = new ArrayList<Writeable>();
			Var recordCount = new Var("recordcount",new Type("double"));
			Var pageCount = new Var("pagecount",new Type("int"));
			Var countNum = new Var("countNum",new Type("String"));
			list.add(new Statement(10L,indent,pageCount.getVarType() + " "+ pageCount.getVarName() + " = 1;"));
			list.add(new Statement(20L,indent,recordCount.getVarType() + " "+ recordCount.getVarName() + " = 0;"));
			list.add(NamedStatementGenerator.getCountRecordSqlStatement(100L, indent, domain, InterVarUtil.DB.query, recordCount, countNum));
			list.add(NamedStatementGenerator.getPrepareStatement(200L,indent,InterVarUtil.DB.ps, InterVarUtil.DB.query, InterVarUtil.DB.connection));
			list.add(NamedStatementGenerator.getPrepareStatementExcuteQuery(300L,indent, InterVarUtil.DB.result, InterVarUtil.DB.ps));
			list.add(NamedStatementGenerator.getResultSetWhileNextLoopHead(500L,indent, InterVarUtil.DB.result));  
			list.add(NamedStatementGenerator.getRecordCountDoubleFromResultSet(800L,indent+1,domain, InterVarUtil.DB.result,recordCount,countNum));
			list.add(new Statement(900L,indent+1, pageCount.getVarName() + " = (int)Math.ceil("+recordCount.getVarName()+"/"+pageSize.getVarName()+");"));
			list.add(new Statement(900L,indent+1, "if (" +pageCount.getVarName() + " <= 1)"+pageCount.getVarName()+" = 1;"));
			list.add(NamedStatementGenerator.getLoopFooter(1100L,indent));
			list.add(NamedStatementGenerator.getReturnInt(1200,indent, pageCount));
			StatementList sList = WriteableUtil.merge(list);
			sList.setSerial(serial);
			return sList;
		} catch (Exception e){
			return null;
		}
	}

	public static StatementList generateSelectActiveStatementList(long serial,int indent,Domain domain){
		try {
			List<Writeable> list = new ArrayList<Writeable>();
			list.add(NamedStatementGenerator.getSelectActiveSqlStatement(100L,indent,domain,InterVarUtil.DB.query));
			list.add(NamedStatementGenerator.getPrepareStatement(200L,indent,InterVarUtil.DB.ps, InterVarUtil.DB.query, InterVarUtil.DB.connection));
			list.add(NamedStatementGenerator.getPrepareStatementExcuteQuery(300L,indent, InterVarUtil.DB.result, InterVarUtil.DB.ps));
			list.add(NamedStatementGenerator.getPrepareDaomainArrayList(400L,indent, domain));
			list.add(NamedStatementGenerator.getResultSetWhileNextLoopHead(500L,indent, InterVarUtil.DB.result));  
			list.add(NamedStatementGenerator.getSingleLineComment(600L,indent+1,"Build the list object."));
			list.add(NamedStatementGenerator.getPrepareDomainVarInit(700L,indent+1, domain));
			list.add(NamedStatementListGenerator.generateSetDomainDataFromResultSet(800L,indent+1,domain, InterVarUtil.DB.result));
			list.add(NamedStatementGenerator.getSingleLineComment(900L,indent+1, "Build the object list."));			
			list.add(NamedStatementGenerator.getAddDomaintoList(1000L,indent+1, domain, InterVarUtil.Container.getList(domain)));
			list.add(NamedStatementGenerator.getLoopFooter(1100L,indent));
			list.add(NamedStatementGenerator.getReturnDomainList(1200,indent, domain, InterVarUtil.Container.getList(domain)));

			StatementList sList = WriteableUtil.merge(list);
			sList.setSerial(serial);
			return sList;
		} catch (Exception e){
			return null;
		}
	}
	
	public static StatementList generateFindByIdStatementList(long serial,int indent,Domain domain){
		try {
			List<Writeable> list = new ArrayList<Writeable>();
			list.add(NamedStatementGenerator.getFindByIdStatement(100L,indent,domain,InterVarUtil.DB.query));
			list.add(NamedStatementGenerator.getPrepareStatement(200L,indent,InterVarUtil.DB.ps, InterVarUtil.DB.query, InterVarUtil.DB.connection));
			list.add(NamedStatementGenerator.getPrepareStatementSetDomainIdStatement(300L, indent,domain, InterVarUtil.DB.ps));
			list.add(NamedStatementGenerator.getPrepareStatementExcuteQuery(400L,indent, InterVarUtil.DB.result, InterVarUtil.DB.ps));
			list.add(NamedStatementGenerator.getPrepareDaomain(500L,indent, domain));
			list.add(NamedStatementGenerator.getResultSetLast(600L,indent, InterVarUtil.DB.result));  
			list.add(NamedStatementGenerator.getSingleLineComment(700L,indent,"Build the object."));
			list.add(NamedStatementListGenerator.generateSetDomainDataFromResultSet(800L,indent,domain, InterVarUtil.DB.result));
			list.add(NamedStatementGenerator.getReturnDomain(900,indent, domain));

			StatementList sList = WriteableUtil.merge(list);
			sList.setSerial(serial);
			return sList;
		} catch (Exception e){
			return null;
		}	
	}
	
	public static StatementList generateFindByNameStatementList(long serial,int indent,Domain domain){
		try {
			List<Writeable> list = new ArrayList<Writeable>();
			list.add(NamedStatementGenerator.getFindByNameStatement(100L,indent,domain,InterVarUtil.DB.query));
			list.add(NamedStatementGenerator.getPrepareStatement(200L,indent,InterVarUtil.DB.ps, InterVarUtil.DB.query, InterVarUtil.DB.connection));
			list.add(NamedStatementGenerator.getPrepareStatementSetDomainNameStatement(300L, indent,domain, InterVarUtil.DB.ps));
			list.add(NamedStatementGenerator.getPrepareStatementExcuteQuery(400L,indent, InterVarUtil.DB.result, InterVarUtil.DB.ps));
			list.add(NamedStatementGenerator.getPrepareDaomain(500L,indent, domain));
			list.add(NamedStatementGenerator.getResultSetLast(600L,indent, InterVarUtil.DB.result));  
			list.add(NamedStatementGenerator.getSingleLineComment(700L,indent,"Build the object."));
			list.add(NamedStatementListGenerator.generateSetDomainDataFromResultSet(800L,indent,domain, InterVarUtil.DB.result));
			list.add(NamedStatementGenerator.getReturnDomain(900,indent, domain));

			StatementList sList = WriteableUtil.merge(list);
			sList.setSerial(serial);
			return sList;
		} catch (Exception e){
			return null;
		}	
	}
	

	public static StatementList generateSearchByNameStatementList(long serial,int indent,Domain domain){
		try {
			List<Writeable> list = new ArrayList<Writeable>();
			list.add(NamedStatementGenerator.getSearchByNameStatement(100L,indent,domain,InterVarUtil.DB.query));
			list.add(NamedStatementGenerator.getPrepareStatement(200L,indent,InterVarUtil.DB.ps, InterVarUtil.DB.query, InterVarUtil.DB.connection));
			list.add(NamedStatementGenerator.getPrepareStatementSetDomainNameWithPercentStatement(300L, indent,domain, InterVarUtil.DB.ps));
			list.add(NamedStatementGenerator.getPrepareStatementExcuteQuery(350, indent, InterVarUtil.DB.result, InterVarUtil.DB.ps));
			list.add(NamedStatementGenerator.getPrepareDaomainArrayList(400L,indent, domain));
			list.add(NamedStatementGenerator.getResultSetWhileNextLoopHead(500L,indent, InterVarUtil.DB.result));  
			list.add(NamedStatementGenerator.getSingleLineComment(600L,indent+1,"Build the list object."));
			list.add(NamedStatementGenerator.getPrepareDomainVarInit(700L,indent+1, domain));
			list.add(NamedStatementListGenerator.generateSetDomainDataFromResultSet(800L,indent+1,domain, InterVarUtil.DB.result));
			list.add(NamedStatementGenerator.getSingleLineComment(900L,indent+1, "Build the object list."));			
			list.add(NamedStatementGenerator.getAddDomaintoList(1000L,indent+1, domain, InterVarUtil.Container.getList(domain)));
			list.add(NamedStatementGenerator.getLoopFooter(1100L,indent));
			list.add(NamedStatementGenerator.getReturnDomainList(1200,indent, domain, InterVarUtil.Container.getList(domain)));

			StatementList sList = WriteableUtil.merge(list);
			sList.setSerial(serial);
			return sList;
		} catch (Exception e){
			return null;
		}	
	}
	
	public static StatementList generateCatchExceptionPrintStackReturnNullFooter(long serial, int indent){
		StatementList list = new StatementList();
		list.add(new Statement(100L,indent,"} catch (Exception e){"));
		list.add(new Statement(200L,indent+1,"e.printStackTrace();"));
		list.add(new Statement(300L,indent+1,"return null;"));
		list.add(new Statement(400L,indent,"}"));
		
		list.setSerial(serial);
		return list;
	}
	
	public static StatementList generateCatchExceptionPrintStackReturnEmptyListFooter(long serial, int indent,Domain domain){
		StatementList list = new StatementList();
		list.add(new Statement(100L,indent,"} catch (Exception e){"));
		list.add(new Statement(200L,indent+1,"e.printStackTrace();"));
		list.add(new Statement(300L,indent+1,"return new ArrayList<"+domain.getStandardName()+">();"));
		list.add(new Statement(400L,indent,"}"));
		
		list.setSerial(serial);
		return list;
	}
	
	public static StatementList generateCatchExceptionPrintStackReturnOneFooter(long serial, int indent,Domain domain){
		StatementList list = new StatementList();
		list.add(new Statement(100L,indent,"} catch (Exception e){"));
		list.add(new Statement(200L,indent+1,"e.printStackTrace();"));
		list.add(new Statement(300L,indent+1,"return 1;"));
		list.add(new Statement(400L,indent,"}"));
		
		list.setSerial(serial);
		return list;
	}
	
	public static StatementList generateCatchExceptionPrintStackFooter(long serial, int indent){
		StatementList list = new StatementList();
		list.add(new Statement(100L,indent,"} catch (Exception e){"));
		list.add(new Statement(200L,indent+1,"e.printStackTrace();"));
		list.add(new Statement(300L,indent,"}"));
		
		list.setSerial(serial);
		return list;
	}
	
	public static StatementList generateCatchExceptionPrintStackRedirectUrlFinallyCloseOutFooter(long serial, int indent, Var response, String url,Var out){
		StatementList list = new StatementList();
		list.setSerial(serial);
		list.add(new Statement(100L,indent,"} catch (Exception e){"));
		list.add(new Statement(200L,indent+1,"e.printStackTrace();"));
		list.add(new Statement(300L,indent+1,response.getVarName()+".sendRedirect(\""+url+"\");"));
		list.add(new Statement(400L,indent,"} finally {"));
		list.add(new Statement(500L,indent+1,out.getVarName()+".close();"));
		list.add(new Statement(600L,indent,"}"));
		return list;
	}
	
	public static StatementList generateCatchExceptionPrintStackPrintJsonMapFinallyCloseOutFooter(long serial, int indent, Var response, Var resultMap,Var out){
		StatementList list = new StatementList();
		list.setSerial(serial);
		list.add(new Statement(100L,indent,"} catch (Exception e){"));
		list.add(new Statement(200L,indent+1,"e.printStackTrace();"));
		list.add(new Statement(300L,indent+1,resultMap.getVarName()+".put(\"success\",false);"));
		list.add(new Statement(400L,indent+1,resultMap.getVarName()+".put(\"reason\",e.getMessage());"));
		list.add(new Statement(500L,indent+1,out.getVarName()+".print(JSONObject.fromObject("+resultMap.getVarName()+").toString());"));
		list.add(new Statement(600L,indent,"} finally {"));
		list.add(new Statement(700L,indent+1,out.getVarName()+".close();"));
		list.add(new Statement(800L,indent,"}"));
		return list;
	}
	
	public static StatementList generateSetDomainDataFromResultSet(long serial, int indent, Domain domain, Var resultSet){
		StatementList list = new StatementList();
		list.setSerial(serial);
		long fieldSerial = 100L;
		for (Field f : domain.getFields()){
			list.add(new Statement(fieldSerial,indent,StringUtil.lowerFirst(domain.getStandardName())+".set"+StringUtil.capFirst(f.getFieldName())+"("+resultSet.getVarName()+".get"+StringUtil.capFirst(f.getFieldType())+"(\""+f.getFieldTableColumName()+"\"));"));
			fieldSerial += 100L;
		}
		return list;
	}
	
	public static StatementList generateSetDomainDataFromRequest(long serial, int indent, Domain domain, Var request){
		StatementList list = new StatementList();
		list.setSerial(serial);
		long fieldSerial = 100L;
		for (Field f : domain.getFields()){
			list.add(new Statement(fieldSerial,indent,StringUtil.lowerFirst(domain.getStandardName())+".set"+StringUtil.capFirst(f.getFieldName())+"("+FieldUtil.generateRequestGetParameterString(f, request)+");"));
			fieldSerial += 100L;
		}
		return list;
	}
	
	public static StatementList generateSetDomainDataFromRequestWithoutDomainId(long serial, int indent, Domain domain, Var request){
		StatementList list = new StatementList();
		list.setSerial(serial);
		long fieldSerial = 100L;
		for (Field f : domain.getFieldsWithoutId()){
			list.add(new Statement(fieldSerial,indent,StringUtil.lowerFirst(domain.getStandardName())+".set"+StringUtil.capFirst(f.getFieldName())+"("+FieldUtil.generateRequestGetParameterString(f, request)+");"));
			fieldSerial += 100L;
		}
		return list;
	}

	public static StatementList generatePsSetDomainFields(long serial, int indent, Domain domain, Var ps){
		StatementList list = new StatementList();
		list.setSerial(serial);
		long fieldSerial = 100L;
		int psFieldSerial = 1;
		List<Field> fieldList = new ArrayList<Field>();
		fieldList.addAll(domain.getFields());
		Collections.sort(fieldList);
		for (Field f : fieldList){
			list.add(new Statement(fieldSerial,indent,StringUtil.lowerFirst(ps.getVarName())+".set"+StringUtil.capFirst(f.getFieldType())+"("+psFieldSerial+","+StringUtil.lowerFirst(domain.getStandardName())+".get"+StringUtil.capFirst(f.getFieldName())+"());"));
			fieldSerial += 100L;
			psFieldSerial += 1;
		}
		return list;
	}
	
	public static StatementList generatePsSetDomainFieldsWithoutId(long serial, int indent, Domain domain, Var ps){
		StatementList list = new StatementList();
		list.setSerial(serial);
		long fieldSerial = 100L;
		int psFieldSerial = 1;
		List<Field> fieldList = new ArrayList<Field>();
		fieldList.addAll(domain.getFieldsWithoutId());
		Collections.sort(fieldList);
		for (Field f : fieldList){
			list.add(new Statement(fieldSerial,indent,StringUtil.lowerFirst(ps.getVarName())+".set"+StringUtil.capFirst(f.getFieldType())+"("+psFieldSerial+","+StringUtil.lowerFirst(domain.getStandardName())+".get"+StringUtil.capFirst(f.getFieldName())+"());"));
			fieldSerial += 100L;
			psFieldSerial += 1;
		}
		return list;
	}
	
	public static StatementList generatePsSetDomainPagingFields(long serial, int indent, Domain domain, Var ps, Var limitstart, Var limitcount){
		StatementList list = new StatementList();
		list.setSerial(serial);
		list.add(new Statement(1000L,indent,StringUtil.lowerFirst(ps.getVarName())+".setInt(1,"+limitstart.getVarName()+");"));
		list.add(new Statement(2000L,indent,StringUtil.lowerFirst(ps.getVarName())+".setInt(2,"+limitcount.getVarName()+");"));
		return list;
	}
	
	public static StatementList generateResultReturnSuccess(long serial, int indent, Var result){
		StatementList list = new StatementList();
		list.setSerial(serial);
		list.add(new Statement(100L,indent, "if ("+result.getVarName()+" > 0) {"));
		list.add(new Statement(200L,indent+1, "return true;"));
		list.add(new Statement(300L,indent, "}"));
		list.add(new Statement(400L,indent, "return false;"));
		return list;
	}
	
	public static StatementList generateResultReturnSuccessUpdatedDomainId(long serial, int indent, Var result, Var ps, Domain domain){
		StatementList list = new StatementList();
		list.setSerial(serial);
		list.add(new Statement(100L,indent, "ResultSet rsNewId = "+ps.getVarName()+".getGeneratedKeys();"));
		list.add(new Statement(200L,indent, "if (rsNewId.next()) { "));
		if (domain.getDomainId().getRawType().isLong()){
			list.add(new Statement(300L,indent+1, "long newId = rsNewId.getLong(1);"));
		} else if (domain.getDomainId().getRawType().isInt()){
			list.add(new Statement(300L,indent+1, "int newId = rsNewId.getInt(1);"));			
		}
		list.add(new Statement(400L,indent+1, domain.getLowerFirstDomainName()+"."+domain.getDomainId().getSetterCallName()+"(newId);"));
		list.add(new Statement(500L,indent, "}"));
		list.add(new Statement(600L,indent, "if ("+result.getVarName()+" > 0) {"));
		list.add(new Statement(700L,indent+1, "return true;"));
		list.add(new Statement(800L,indent, "}"));
		list.add(new Statement(900L,indent, "return false;"));
		return list;
	}
	
	public static StatementList generateServiceImplReturnBoolean(long serial,int indent, Var connection, Var dbconf, Var dao, Method daoBooleanFunction){
		StatementList list = new StatementList();
		list.setSerial(serial);
		list.add(NamedStatementGenerator.getDBConfInitDBAutoRelease(100L, indent, connection, dbconf));
		list.add(new Statement(200L,indent,"\treturn "+daoBooleanFunction.generateStandardDaoImplCallString(dao.getVarName())+";"));
		list.add(NamedStatementGenerator.getLoopFooter(300L, indent));
		return list;
	}
	
	public static StatementList generateServiceImplVoid(long serial,int indent, Var connection, Var dbconf, Var dao, Method daoBooleanFunction){
		StatementList list = new StatementList();
		list.setSerial(serial);
		list.add(NamedStatementGenerator.getDBConfInitDBAutoRelease(100L, indent, connection, dbconf));
		list.add(new Statement(200L,indent+1,daoBooleanFunction.generateStandardDaoImplCallString(dao.getVarName())+";"));
		list.add(NamedStatementGenerator.getLoopFooter(300L, indent));
		return list;
	}
	
	public static StatementList generateServiceImplReturnList(long serial,int indent, Var connection, Var dbconf, Domain domain, Var dao, Method daoBooleanFunction){
		StatementList list = new StatementList();
		list.setSerial(serial);
		
		Var interList = new Var("list", new Type("List",domain,domain.getPackageToken()));
		list.add(NamedStatementGenerator.getDBConfInitDBAutoRelease(100L, indent, connection, dbconf));
		list.add(new Statement(200L,indent,"\treturn " + daoBooleanFunction.generateStandardServiceImplCallString(dao.getVarName())+";"));
		list.add(NamedStatementGenerator.getLoopFooter(300L, indent));
		return list;
	}
	
	public static StatementList generateServiceImplReturnDomain(long serial,int indent, Var connection, Var dbconf, Domain domain, Var dao, Method daoBooleanFunction){
		StatementList list = new StatementList();
		list.setSerial(serial);

		list.add(NamedStatementGenerator.getDBConfInitDBAutoRelease(100L, indent, connection, dbconf));
		list.add(new Statement(200L,indent, "\treturn " + daoBooleanFunction.generateStandardServiceImplCallString(dao.getVarName())+";"));
		list.add(NamedStatementGenerator.getLoopFooter(300L, indent));
		return list;
	}
	
	public static StatementList generateServiceImplReturnInt(long serial,int indent, Var connection, Var dbconf, Domain domain, Var dao, Method daoReturnIntFunction){
		StatementList list = new StatementList();
		list.setSerial(serial);

		list.add(NamedStatementGenerator.getDBConfInitDBAutoRelease(100L, indent, connection, dbconf));
		list.add(new Statement(200L,indent, "\treturn " + daoReturnIntFunction.generateStandardServiceImplCallString(dao.getVarName())+";"));
		list.add(NamedStatementGenerator.getLoopFooter(300L, indent));
		return list;
	}
	
	public static StatementList getPutJsonResultMapWithSuccessAndNull(long serial,int indent, Var map){
		StatementList list = new StatementList();
		list.setSerial(serial);
		list.add(new Statement(100L,indent,map.getVarName() +".put(\"success\",true);"));
		list.add(new Statement(200L,indent,map.getVarName() +".put(\"data\",null);"));
		return list;
	}
	
	public static StatementList getPutJsonResultMapWithSuccessAndDomain(long serial,int indent, Var map, Domain domain){
		StatementList list = new StatementList();
		list.setSerial(serial);
		list.add(new Statement(100L,indent,map.getVarName() +".put(\"success\",success);"));
		list.add(new Statement(200L,indent,map.getVarName() +".put(\"data\","+domain.getLowerFirstDomainName()+");"));
		return list;
	}
	
	public static StatementList getPutJsonResultMapWithSuccessAssignAndNull(long serial,int indent, Var map){
		StatementList list = new StatementList();
		list.setSerial(serial);
		list.add(new Statement(100L,indent,map.getVarName() +".put(\"success\",success);"));
		list.add(new Statement(200L,indent,map.getVarName() +".put(\"data\",null);"));
		return list;
	}
	
	public static StatementList getPutJsonResultMapWithSuccessAndDomainVar(long serial,int indent, Var map,Var domainVar){
		StatementList list = new StatementList();
		list.setSerial(serial);
		list.add(new Statement(100L,indent,map.getVarName() +".put(\"success\",true);"));
		list.add(new Statement(200L,indent,map.getVarName() +".put(\"data\","+domainVar.getVarName()+");"));
		return list;
	}
	
	public static StatementList getPutJsonResultMapWithSuccessAndDomainListPaging(long serial,int indent, Var map,Var domainList, Var pagesize, Var pagenum, Var pagecount){
		StatementList list = new StatementList();
		list.setSerial(serial);
		list.add(new Statement(100L,indent,map.getVarName() +".put(\"success\",true);"));
		list.add(new Statement(200L,indent,map.getVarName() +".put(\"data\","+domainList.getVarName()+");"));
		list.add(new Statement(300L,indent,map.getVarName() +".put(\""+pagesize.getVarName()+"\","+pagesize.getVarName()+");"));
		list.add(new Statement(400L,indent,map.getVarName() +".put(\""+pagenum.getVarName()+"\","+pagenum.getVarName()+");"));
		list.add(new Statement(500L,indent,map.getVarName() +".put(\""+pagecount.getVarName()+"\","+pagecount.getVarName()+");"));
		return list;
	}

	public static StatementList getPutJsonResultMapWithSuccessAndDomainList(long serial,int indent, Var map,Var domainList){
		StatementList list = new StatementList();
		list.setSerial(serial);
		list.add(new Statement(100L,indent,map.getVarName() +".put(\"success\",true);"));
		list.add(new Statement(100L,indent,map.getVarName() +".put(\"data\","+domainList.getVarName()+");"));
		return list;
	}
}
