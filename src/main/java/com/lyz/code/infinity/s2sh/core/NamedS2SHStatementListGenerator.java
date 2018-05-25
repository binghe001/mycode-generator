package com.lyz.code.infinity.s2sh.core;

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

public class NamedS2SHStatementListGenerator {
	
	public static StatementList getHibernateUpdateStatementList(long serial, int indent,Domain domain){
		StatementList list = new StatementList();
		list.add(new Statement(100L,indent, "Session s= this.getHibernateTemplate().getSessionFactory().getCurrentSession();"));
		list.add(new Statement(200L,indent,	"s.update("+domain.getLowerFirstDomainName()+");"));
		list.add(new Statement(200L,indent, "s.flush();"));
		list.setSerial(serial);
		return list;
	}

	public static StatementList generateSelectAllQueryStatementList(long serial,int indent,Domain domain){
		try {
			List<Writeable> list = new ArrayList<Writeable>();
			list.add(NamedS2SHStatementGenerator.getReturnListSelectAllSqlStatement(100L,indent,domain,InterVarUtil.Container.getList(domain)));
			list.add(NamedS2SHStatementGenerator.getReturnDomainList(200,indent, domain, InterVarUtil.Container.getList(domain)));

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
			list.add(NamedS2SHStatementGenerator.getSelectAllByPageSqlStatement(1000L,indent,domain,InterVarUtil.DB.query));
			Var limitstart = new Var("limitstart",new Type("int"));
			Var limitcount = new Var("limitcount",new Type("int"));
			Var pagecount = new Var("pagecount",new Type("int"));
			countPage.setDomain(domain);
			list.add(new Statement(2000L,indent, limitstart.generateTypeVarString() + " = ("+ pagenum.getVarName() +"-1)*"+pagesize.getVarName()+";"));
			list.add(new Statement(3000L,indent, limitcount.generateTypeVarString() + " = " + pagesize.getVarName() + ";"));

			list.add(NamedS2SHStatementGenerator.getPrepareStatement(4000L,indent,InterVarUtil.DB.ps, InterVarUtil.DB.query, InterVarUtil.DB.connection));
			list.add(new Statement(5000L,indent, pagecount.generateTypeVarString() + " = " + "this."+countPage.getVerbName()+"("+InterVarUtil.DB.connection.getVarName()+","+pagesize.getVarName()+");"));
			list.add(new Statement(6000L,indent, "if ("+pagenum.getVarName()+ " <= 1) " +pagenum.getVarName() +" = 1;"));
			list.add(new Statement(7000L,indent, "if ("+pagenum.getVarName()+ " >= "+pagecount.getVarName()+ ") "+pagenum.getVarName() + " = " +pagecount.getVarName()+";"));

			list.add(NamedS2SHStatementListGenerator.generatePsSetDomainPagingFields(8000L, indent, domain, InterVarUtil.DB.ps,limitstart,limitcount));
			list.add(NamedS2SHStatementGenerator.getPrepareStatementExcuteQuery(9000L,indent, InterVarUtil.DB.result, InterVarUtil.DB.ps));
			list.add(NamedS2SHStatementGenerator.getPrepareDaomainArrayList(10000L,indent, domain));
			list.add(NamedS2SHStatementGenerator.getResultSetWhileNextLoopHead(11000L,indent, InterVarUtil.DB.result));  
			list.add(NamedS2SHStatementGenerator.getSingleLineComment(12000L,indent,"Build the list object."));
			list.add(NamedS2SHStatementGenerator.getPrepareDomainVarInit(13000L,indent, domain));
			list.add(NamedS2SHStatementListGenerator.generateSetDomainDataFromResultSet(14000L,indent,domain, InterVarUtil.DB.result));
			list.add(NamedS2SHStatementGenerator.getSingleLineComment(15000L,indent, "Build the object list."));			
			list.add(NamedS2SHStatementGenerator.getAddDomaintoList(16000L,indent, domain, InterVarUtil.Container.getList(domain)));
			list.add(NamedS2SHStatementGenerator.getLoopFooter(17000L,indent));
			list.add(NamedS2SHStatementGenerator.getReturnDomainList(18000,indent, domain, InterVarUtil.Container.getList(domain)));

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
			Var recordCount = new Var("recordcount",new Type("int"));
			Var pageCount = new Var("pagecount",new Type("int"));
			Var countNum = new Var("countNum",new Type("String"));
			list.add(new Statement(1000L,indent,"if ("+pageSize.getVarName()+" <= 0) "+pageSize.getVarName()+" = 10;"));
			list.add(new Statement(2000L,indent,pageCount.getVarType() + " "+ pageCount.getVarName() + " = 1;"));
			list.add(new Statement(3000L,indent,recordCount.getVarType() + " "+ recordCount.getVarName() + " = 0;"));
			list.add(NamedS2SHStatementGenerator.getSelectAllHqlStatement(4000L, indent, domain, InterVarUtil.DB.query));
			list.add(NamedS2SHStatementGenerator.getExecutiveHqlQueryReturnSizeStatement(5000L, indent, recordCount, InterVarUtil.DB.query));
			list.add(new Statement(6000L,indent,pageCount.getVarName()+" = (int)Math.ceil((double)"+ recordCount.getVarName() + "/"+pageSize.getVarName()+");"));
			list.add(new Statement(7000L,indent,"if ("+ pageCount.getVarName() + " <= 1)"+ pageCount.getVarName() +" = 1;"));
			list.add(new Statement(8000L,indent,"return "+pageCount.getVarName() +";"));
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
			list.add(NamedS2SHStatementGenerator.getReturnListSelectAllSqlStatement(100L,indent,domain,InterVarUtil.Container.getList(domain)));
			list.add(NamedS2SHStatementGenerator.getReturnDomainList(200,indent, domain, InterVarUtil.Container.getList(domain)));

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
			list.add(NamedS2SHStatementGenerator.getFindByNameStatement(100L,indent,domain,InterVarUtil.DB.query));
			list.add(NamedS2SHStatementGenerator.getPrepareStatement(200L,indent,InterVarUtil.DB.ps, InterVarUtil.DB.query, InterVarUtil.DB.connection));
			list.add(NamedS2SHStatementGenerator.getPrepareStatementSetDomainNameStatement(300L, indent,domain, InterVarUtil.DB.ps));
			list.add(NamedS2SHStatementGenerator.getPrepareStatementExcuteQuery(400L,indent, InterVarUtil.DB.result, InterVarUtil.DB.ps));
			list.add(NamedS2SHStatementGenerator.getPrepareDaomain(500L,indent, domain));
			list.add(NamedS2SHStatementGenerator.getResultSetLast(600L,indent, InterVarUtil.DB.result));  
			list.add(NamedS2SHStatementGenerator.getSingleLineComment(700L,indent,"Build the object."));
			list.add(NamedS2SHStatementListGenerator.generateSetDomainDataFromResultSet(800L,indent,domain, InterVarUtil.DB.result));
			list.add(NamedS2SHStatementGenerator.getReturnDomain(900,indent, domain));

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
			Var vlist = new Var("list", new Type("List",domain,domain.getPackageToken()));
			list.add(NamedS2SHStatementGenerator.getSearchByNameStatement(1000L,indent,domain,InterVarUtil.DB.query));
			list.add(NamedS2SHStatementGenerator.getHibernateStatementReturnDomainList(2000L,indent,domain,InterVarUtil.DB.query,vlist));
			list.add(NamedS2SHStatementGenerator.getReturnDomainList(3000L,indent, domain, InterVarUtil.Container.getList(domain)));

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
		list.add(new Statement(200L,indent,"e.printStackTrace();"));
		list.add(new Statement(300L,indent,"return null;"));
		list.add(new Statement(400L,indent,"}"));
		
		list.setSerial(serial);
		return list;
	}
	
	public static StatementList generateCatchExceptionPrintStackReturnEmptyListFooter(long serial, int indent,Domain domain){
		StatementList list = new StatementList();
		list.add(new Statement(100L,indent,"} catch (Exception e){"));
		list.add(new Statement(200L,indent,"e.printStackTrace();"));
		list.add(new Statement(300L,indent,"return new ArrayList<"+domain.getStandardName()+">();"));
		list.add(new Statement(400L,indent,"}"));
		
		list.setSerial(serial);
		return list;
	}
	
	public static StatementList generateCatchExceptionPrintStackReturnOneFooter(long serial, int indent,Domain domain){
		StatementList list = new StatementList();
		list.add(new Statement(100L,indent,"} catch (Exception e){"));
		list.add(new Statement(200L,indent,"e.printStackTrace();"));
		list.add(new Statement(300L,indent,"return 1;"));
		list.add(new Statement(400L,indent,"}"));
		
		list.setSerial(serial);
		return list;
	}
	
	public static StatementList generateCatchExceptionPrintStackFooter(long serial, int indent){
		StatementList list = new StatementList();
		list.add(new Statement(100L,indent,"} catch (Exception e){"));
		list.add(new Statement(200L,indent,"e.printStackTrace();"));
		list.add(new Statement(300L,indent,"}"));
		
		list.setSerial(serial);
		return list;
	}
	
	public static StatementList generateCatchExceptionPrintStackRedirectUrlFinallyCloseOutFooter(long serial, int indent, Var response, String url,Var out){
		StatementList list = new StatementList();
		list.setSerial(serial);
		list.add(new Statement(100L,indent,"} catch (Exception e){"));
		list.add(new Statement(200L,indent,"e.printStackTrace();"));
		list.add(new Statement(300L,indent,response.getVarName()+".sendRedirect(\""+url+"\");"));
		list.add(new Statement(400L,indent,"} finally {"));
		list.add(new Statement(500L,indent,out.getVarName()+".close();"));
		list.add(new Statement(400L,indent,"}"));
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
		list.add(new Statement(100L,indent, "return true;"));
		list.add(new Statement(100L,indent, "}"));
		list.add(new Statement(100L,indent, "return false;"));
		return list;
	}
	
	public static StatementList generateServiceImplVoid(long serial,int indent, Var dao, Method daoVoidFunction){
		StatementList list = new StatementList();
		list.setSerial(serial);
		list.add(new Statement(200L,indent,daoVoidFunction.generateStandardDaoImplCallString(dao.getVarName())+";"));
		return list;
	}
	
	public static StatementList generateServiceImplReturnList(long serial,int indent,  Domain domain, Var dao, Method daoReturnListFunction){
		StatementList list = new StatementList();
		list.setSerial(serial);

		list.add(new Statement(100L,indent,"return " + daoReturnListFunction.generateStandardServiceImplCallString(dao.getVarName())+";"));
		return list;
	}

	public static StatementList generateServiceImplReturnDomain(long serial,int indent, Var connection, Var dbconf, Domain domain, Var dao, Method daoBooleanFunction){
		StatementList list = new StatementList();
		list.setSerial(serial);

		list.add(NamedS2SHStatementGenerator.getDBConfInitDBAutoRelease(100L, indent, connection, dbconf));
		list.add(new Statement(200L,indent, "\treturn " + daoBooleanFunction.generateStandardServiceImplCallString(dao.getVarName())+";"));
		list.add(NamedS2SHStatementGenerator.getLoopFooter(300L, indent));
		return list;
	}
	
	public static StatementList generateServiceImplReturnInt(long serial,int indent, Var connection, Var dbconf, Domain domain, Var dao, Method daoReturnIntFunction){
		StatementList list = new StatementList();
		list.setSerial(serial);

		list.add(NamedS2SHStatementGenerator.getDBConfInitDBAutoRelease(100L, indent, connection, dbconf));
		list.add(new Statement(200L,indent, "return " + daoReturnIntFunction.generateStandardServiceImplCallString(dao.getVarName())+";"));
		list.add(NamedS2SHStatementGenerator.getLoopFooter(300L, indent));
		return list;
	}
	
	
	public static StatementList getReturnListSelectActiveHqlStatementList(long serial,int indent, Domain domain){
		try {
			StatementList list = new StatementList();
			list.setSerial(serial);
			Var hql = new Var("hql",new Type("String"));
			Statement s0 = new Statement(1000L,indent, hql.generateTypeVarString() + " = \""+ HibernateReflector.generateSelectActiveHqlStatement(domain) +"\";");
			Statement s1 = NamedS2SHStatementGenerator.getReturnListSelectActiveStatement(2000L,indent,domain,hql,InterVarUtil.Container.getList(domain));
			Statement s2 = NamedS2SHStatementGenerator.getReturnDomainList(3000L,indent, domain, InterVarUtil.Container.getList(domain));
			list.add(s0);
			list.add(s1);
			list.add(s2);
			return list;
		} catch (Exception e){
			return null;
		}
	}
	
	public static StatementList getPutJsonResultMapWithSuccessAndNull(long serial,int indent, Var map){
		StatementList list = new StatementList();
		list.setSerial(serial);
		list.add(new Statement(100L,indent,map.getVarName() +".put(\"success\",true);"));
		list.add(new Statement(200L,indent,map.getVarName() +".put(\"data\",null);"));
		return list;
	}
	
	public static StatementList getPutJsonResultMapWithSuccessAndDomainList(long serial,int indent, Var map,Var domainList){
		StatementList list = new StatementList();
		list.setSerial(serial);
		list.add(new Statement(100L,indent,map.getVarName() +".put(\"success\",true);"));
		list.add(new Statement(100L,indent,map.getVarName() +".put(\"data\","+domainList.getVarName()+");"));
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
}
