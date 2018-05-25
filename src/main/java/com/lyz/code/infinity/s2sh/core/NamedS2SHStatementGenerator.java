package com.lyz.code.infinity.s2sh.core;

import com.lyz.code.infinity.core.Verb;
import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Field;
import com.lyz.code.infinity.domain.Method;
import com.lyz.code.infinity.domain.Statement;
import com.lyz.code.infinity.domain.StatementList;
import com.lyz.code.infinity.domain.Var;
import com.lyz.code.infinity.generator.NamedTokenGenerator;
import com.lyz.code.infinity.utils.FieldUtil;
import com.lyz.code.infinity.utils.SqlReflector;
import com.lyz.code.infinity.utils.StringUtil;

public class NamedS2SHStatementGenerator {
	public static Statement getReturnListSelectAllSqlStatement(long serial,int indent, Domain domain,  Var list){
		try {
			Statement statement = new Statement(serial,indent,NamedTokenGenerator.getDaomainList(domain, list)+ " = (List<"+domain.getStandardName()+">)this.getHibernateTemplate().find(\""+HibernateReflector.generateSelectAllStatement(domain)+"\");");
			return statement;
		} catch (Exception e){
			return null;
		}
	}
	
	public static Statement getSelectAllHqlStatement(long serial,int indent, Domain domain,  Var hql){
		try {
			Statement statement = new Statement(serial,indent, hql.generateTypeVarString() + " = \"" +HibernateReflector.generateSelectAllStatement(domain)+"\";");
			return statement;
		} catch (Exception e){
			return null;
		}
	}
	
	public static Statement getReturnListSelectActiveStatement(long serial,int indent, Domain domain,Var hql,  Var list){
		try {
			Statement statement = new Statement(serial,indent,NamedTokenGenerator.getDaomainList(domain, list)+ " = (List<"+domain.getStandardName()+">) this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("+hql.getVarName()+").list();");
			return statement;
		} catch (Exception e){
			return null;
		}
	}

	public static Statement getSelectAllByPageSqlStatement(long serial,int indent, Domain domain,  Var query){
		try {
			Statement statement = new Statement(serial,indent,query.generateTypeVarString() + " = \""+SqlReflector.generateSelectAllByPageStatement(domain)+"\";");
			return statement;
		} catch (Exception e){
			return null;
		}
	}
	
	public static Statement getCountRecordHqlStatement(long serial,int indent, Domain domain,  Var query, Var recordCount,Var countNum){
		try {
			Statement statement = new Statement(serial,indent, query.generateTypeVarString() + " = \""+HibernateReflector.generateCountAllRecordStatement(domain, countNum)+"\";");
			return statement;
		} catch (Exception e){
			return null;
		}
	}
	
	public static Statement getListForHead(long serial,int indent, Domain domain, Var index, Var list){
		try {
			//for(int index=0;index < privList.size();index++){
			Statement statement = new Statement(serial,indent, "for (int "+index.getVarName()+"=0;"+index.getVarName()+" < "+list.getVarName()+".size();"+index.getVarName()+"++){");
			return statement;
		} catch (Exception e){
			return null;
		}
	}
	
	public static Statement getSelectByFieldSqlStatement(long serial,int indent, Domain domain,  Var query, Field field){
		try {
			Statement statement = new Statement(serial,indent,query.generateTypeVarString() + " = \""+SqlReflector.generateSelectAllStatement(domain)+"\";");
			return statement;
		} catch (Exception e){
			return null;
		}
	}
	
	public static Statement getSelectActiveSqlStatement(long serial,int indent, Domain domain,  Var query){
		try {
			Statement statement = new Statement(serial,indent,query.generateTypeVarString() + " = \""+SqlReflector.generateSelectActiveStatement(domain)+"\";");
			return statement;
		} catch (Exception e){
			return null;
		}
	}
	
	public static Statement getFindByIdUsingHqlStatement(long serial,int indent, Domain domain,  Var query){
		try {
			Statement statement = new Statement(serial,indent,query.generateTypeVarString() + " = \""+HibernateReflector.generateFindByIdStatement(domain)+"\";");
			return statement;
		} catch (Exception e){
			return null;
		}
	}
	
	public static Statement getFindByNameUsingHqlStatement(long serial,int indent, Domain domain,  Var query){
		try {
			Statement statement = new Statement(serial,indent,query.generateTypeVarString() + " = \""+HibernateReflector.generateFindByNameStatement(domain)+"\";");
			return statement;
		} catch (Exception e){
			return null;
		}
	}

	public static Statement getFindByNameStatement(long serial,int indent, Domain domain,  Var query){
		try {
			Statement statement = new Statement(serial,indent,query.generateTypeVarString() + " = \""+SqlReflector.generateFindByNameStatement(domain)+"\";");
			return statement;
		} catch (Exception e){
			return null;
		}
	}
	
	public static Statement getSearchByNameStatement(long serial,int indent, Domain domain,  Var query){
		try {
			Statement statement = new Statement(serial,indent,"String " + query.getVarName() + " = \""+HibernateReflector.generateSearchByNameHibernateStatement(domain)+"\";");
			return statement;
		} catch (Exception e){
			return null;
		}
	}
	
	public static Statement getSearchByFieldStatement(long serial,int indent, Domain domain,  Var query, Field description){
		try {
			Statement statement = new Statement(serial,indent,query.generateTypeVarString() + " = \""+SqlReflector.generateSearchByDescriptionStatement(domain, description)+"\";");
			return statement;
		} catch (Exception e){
			return null;
		}
	}
	
	public static Statement getUpdateSqlStatement(long serial, int indent,Domain domain,  Var query){
		try {
			Statement statement = new Statement(serial,indent, query.generateTypeVarString() + " = \"" + SqlReflector.generateUpdateSqlWithQuestionMark(domain) + "\";");
			return statement;
		} catch(Exception e){
			return null;
		}
	}
	
	public static Statement getAddSqlStatement(long serial, int indent,Domain domain,  Var query){
		try {
			Statement statement = new Statement(serial,indent, query.generateTypeVarString() + " = \"" + SqlReflector.generateInsertSqlWithQuestionMark(domain) + "\";");
			return statement;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static Statement getDeleteHibernateHqlStatement(long serial, int indent,Domain domain,  Var hql){
		try {
			Statement statement = new Statement(serial,indent, "String "+hql.getVarName()+" = \"" + HibernateReflector.generateDeleteHibernateHqlStatement(domain) + "\";");
			return statement;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static Statement getExecutiveHqlStatement(long serial, int indent,Domain domain,  Var hql){
		try {
			if (domain.getDomainId().getRawType().isLong()){
				return  new Statement(serial,indent, "this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("+hql.getVarName()+").setLong(\""+domain.getDomainId().getFieldName()+"\","+domain.getDomainId().getFieldName()+").executeUpdate();");
			} else if(domain.getDomainId().getRawType().isInt()){
				return  new Statement(serial,indent, "this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("+hql.getVarName()+").setInteger(\""+domain.getDomainId().getFieldName()+"\","+domain.getDomainId().getFieldName()+").executeUpdate();");
			} else {
				return null;
			}
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static Statement getExecutiveUpdateHqlUsingDomainIdStatement(long serial, int indent,Domain domain,  Var hql){
		try {
			if (domain.getDomainId().getRawType().isLong()){
				return new Statement(serial,indent, "this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("+hql.getVarName()+").setLong(\""+domain.getDomainId().getLowerFirstFieldName()+"\","+domain.getDomainId().getLowerFirstFieldName()+").executeUpdate();");
			} else if(domain.getDomainId().getRawType().isInt()){
				return new Statement(serial,indent, "this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("+hql.getVarName()+").setInteger(\""+domain.getDomainId().getLowerFirstFieldName()+"\","+domain.getDomainId().getLowerFirstFieldName()+").executeUpdate();");
			} else {
				return null;
			}
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static Statement getExecutiveHqlQueryReturnIntStatement(long serial, int indent,Var intCount,  Var hql){
		try {
			Statement statement = new Statement(serial,indent, intCount.getVarName() + " = (Integer)this.getSessionFactory().openSession().createQuery("+hql.getVarName()+").uniqueResult();");
			return statement;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static Statement getExecutiveHqlQueryReturnLongStatement(long serial, int indent,Var intCount,  Var hql){
		try {
			Statement statement = new Statement(serial,indent, intCount.getVarName() + " = (Long)this.getSessionFactory().openSession().createQuery("+hql.getVarName()+").uniqueResult();");
			return statement;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static Statement getExecutiveHqlQueryReturnSizeStatement(long serial, int indent,Var intCount,  Var hql){
		try {
			Statement statement = new Statement(serial,indent, intCount.getVarName() + " = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("+hql.getVarName()+").list().size();");
			return statement;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static Statement getHibernateAddStatement(long serial, int indent,Domain domain){
		try {
			Statement statement = new Statement(serial,indent, "this.getHibernateTemplate().getSessionFactory().getCurrentSession().save("+domain.getLowerFirstDomainName()+");");
			return statement;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static Statement getHibernateStatementReturnDomainList(long serial, int indent,Domain domain, Var query, Var list){
		try {
			Statement statement = new Statement(serial,indent, "List<"+domain.getStandardName()+"> "+list.getVarName() + " = (List<"+domain.getStandardName()+">) this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("+query.getVarName()+").setString(\""+domain.getDomainName().getLowerFirstFieldName()+"\",\"%\"+"+domain.getDomainName().getLowerFirstFieldName()+"+\"%\").list();");
			return statement;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static Statement getSoftDeleteHqlStatement(long serial, int indent,Domain domain,  Var query){
		try {
			Statement statement = new Statement(serial,indent, query.generateTypeVarString() + " = \"" + HibernateReflector.generateSoftDeleteHqlWithLabel(domain) + "\";");
			return statement;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static Statement getPrepareStatementSetDomainIdStatement(long serial, int indent,Domain domain,  Var ps ){
		try {
			Statement statement = new Statement(serial,indent,StringUtil.lowerFirst(ps.getVarName())+".set"+StringUtil.capFirst(domain.getDomainId().getFieldType())+"(1," +StringUtil.lowerFirst(domain.getDomainId().getFieldName())+");");
			return statement;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static Statement getPrepareStatementSetFieldStatement(long serial, int indent,Domain domain,  Var ps, Field field){
		try {
			Statement statement = new Statement(serial,indent,StringUtil.lowerFirst(ps.getVarName())+".set"+StringUtil.capFirst(domain.getDomainId().getFieldType())+"(1,"+StringUtil.lowerFirst(domain.getStandardName())+".get"+StringUtil.capFirst(field.getFieldName())+"());");
			return statement;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static Statement getPrepareStatementSetDomainNameStatement(long serial, int indent,Domain domain,  Var ps){
		try {
			Statement statement = new Statement(serial,indent,StringUtil.lowerFirst(ps.getVarName())+".set"+StringUtil.capFirst(domain.getDomainName().getFieldType())+"(1,"+StringUtil.lowerFirst(domain.getDomainName().getFieldName())+");");
			return statement;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static Statement getPrepareStatementSetDomainNameWithPercentStatement(long serial, int indent,Domain domain,  Var ps){
		try {
			Statement statement = new Statement(serial,indent,StringUtil.lowerFirst(ps.getVarName())+".set"+StringUtil.capFirst(domain.getDomainName().getFieldType())+"(1,\"%\"+"+StringUtil.lowerFirst(domain.getDomainName().getFieldName())+"+\"%\");");
			return statement;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static Statement getPrepareStatementSetDomainFieldStatement(long serial, int indent,Domain domain,  Var ps, Field field){
		try {
			Statement statement = new Statement(serial,indent,StringUtil.lowerFirst(ps.getVarName())+".set"+StringUtil.capFirst(field.getFieldType())+"(1,"+StringUtil.lowerFirst(domain.getStandardName())+".get"+StringUtil.capFirst(field.getFieldName())+"());");
			return statement;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static Statement getPrepareStatement(long serial,int indent,Var ps,Var query,Var connection){
		try {
			Statement statement = new Statement(serial,indent, ps.generateTypeVarString() + " = " + connection.getVarName()+ ".prepareStatement("+query.getVarName()+");");
			return statement;
		} catch (Exception e){
			return null;
		}
	}
	
	public static Statement getPrepareStatementExcuteQuery(long serial, int indent,Var resultSet, Var ps){
		Statement statement = new Statement(serial,indent, resultSet.generateTypeVarString() +" = "+ ps.getVarName()+".executeQuery();");
		return statement;
	}
	
	public static Statement getTryHead(long serial,int indent){
		Statement statement = new Statement(serial,indent, "try {");
		return statement;
	}
	
	public static Statement getPrepareDaomainArrayList(long serial,int indent,Domain domain){
		Statement statement = new Statement(serial,indent,"ArrayList<"+domain.getStandardName()+"> list = new ArrayList<"+domain.getStandardName()+">();");
		return statement;
	}
	
	public static Statement getPrepareDaomain(long serial,int indent,Domain domain){
		Statement statement = new Statement(serial,indent,domain.getStandardName()+" " + StringUtil.lowerFirst(domain.getStandardName())  + " = new "+domain.getStandardName()+"();");
		return statement;
	}
	
	public static Statement getResultSetWhileNextLoopHead(long serial, int indent,Var resultSet){
		Statement statement = new Statement(serial,indent,"while("+resultSet.getVarName() + ".next()) {");
		return statement;
	}
	
	public static Statement getResultSetLast(long serial, int indent,Var resultSet){
		Statement statement = new Statement(serial,indent,resultSet.getVarName() + ".last();");
		return statement;
	}
	
	public static Statement getLoopFooter(long serial,int indent){
		Statement statement = new Statement(serial,indent,"}");
		return statement;
	}
	
	public static Statement getSingleLineComment(long serial,int indent, String comment){
		Statement statement = new Statement(serial,indent,"//"+comment);
		return statement;
	}
	
	public static Statement getPrepareDomainVarInit(long serial,int indent, Domain domain){
		Statement statement = new Statement(serial,indent, domain.getStandardName() + " "+ StringUtil.lowerFirst(domain.getStandardName()) +" = new "+domain.getStandardName()+"();");
		return statement;
	}
	
	public static Statement getAddDomaintoList(long serial,int indent, Domain domain, Var list){
		Statement statement = new Statement(serial,indent, list.getVarName()+".add("+StringUtil.lowerFirst(domain.getStandardName())+");");
		return statement;
	}
	
	public static Statement getReturnDomainList(long serial,int indent, Domain domain, Var list){
		Statement statement =new Statement(serial,indent,"return "+list.getVarName() +";");
		return statement;
	}
	
	public static Statement getReturnDomain(long serial,int indent, Domain domain){
		Statement statement =new Statement(serial,indent,"return "+ StringUtil.lowerFirst(domain.getStandardName()) +";");
		return statement;
	}
	
	public static Statement getReturnInt(long serial,int indent, Var intResult){
		Statement statement =new Statement(serial,indent,"return "+ intResult.getVarName() +";");
		return statement;
	}
	
	public static Statement getDomainFromListi(long serial,int indent, Domain domain, Var list, Var index){
		Statement statement =new Statement(serial,indent,domain.getStandardName()+" "+ StringUtil.lowerFirst(domain.getStandardName()) +" = "+list.getVarName()+".get("+index.getVarName()+");");
		return statement;
	}
	
	public static Statement getPrepareStatementExcuteUpdate(long serial,int indent, Var resultSet, Var ps){
		Statement statement =new Statement(serial,indent,"int "+resultSet.getVarName() + " = " + ps.getVarName() + ".executeUpdate();");
		return statement;
	}

	public static Statement getDBConfInitDB(long serial,int indent, Var connection, Var dbconf){
		Statement statement =new Statement(serial,indent, connection.generateTypeVarString()+" = " + dbconf.getVarName()+".initDB();");
		return statement;
	}
	
	public static Statement getDBConfInitDBAutoRelease(long serial,int indent, Var connection, Var dbconf){
		Statement statement =new Statement(serial,indent, "try (" + connection.generateTypeVarString()+" = " + dbconf.getVarName()+".initDB()) {");
		return statement;
	}
	
	public static Statement getDBConfCloseDB(long serial,int indent, Var connection, Var dbconf){
		Statement statement =new Statement(serial,indent, dbconf.getVarName() +".closeDB("+connection.getVarName()+");");
		return statement;
	}

	public static Statement getControllerSetContentType(long serial,int indent, Var response, String charset){
		Statement statement =new Statement(serial,indent, response.getVarName() +".setContentType(\"text/html;charset="+charset+"\");");
		return statement;
	}
	
	public static Statement getActionForward(long serial,int indent, String forwardName){
		Statement statement =new Statement(serial,indent, "return \""+forwardName+"\";");
		return statement;
	}
	
	public static Statement getControllerPrintWriterOut(long serial,int indent, Var response, Var out){
		Statement statement =new Statement(serial,indent,out.generateTypeVarString() +" = "+ response.getVarName()+".getWriter();");
		return statement;
	}
	
	public static Statement getPrepareService(long serial,int indent, Var service){
		Statement statement =new Statement(serial,indent,service.generateTypeVarString() + " = new " + service.getVarType() + "Impl();");
		return statement;
	}
	
	public static Statement getCallServiceMethod(long serial,int indent, Var service, Method toExeMethod){
		Statement statement =new Statement(serial,indent,service.getVarName() + "." + toExeMethod.generateStandardServiceImplCallString() + ";");
		return statement;
	}
	
	public static Statement getActionCallServiceMethodByDomainId(long serial,int indent, Domain domain, Var service, Method toExeMethod){
		Statement statement =new Statement(serial,indent,service.getVarName() + "." + toExeMethod.getStandardName() + "("+StringUtil.lowerFirst(domain.getStandardName())+"."+domain.getDomainId().getGetterCall()+");");
		return statement;
	}
	
	public static Statement getActionCallServiceMethodByIds(long serial,int indent, Domain domain, Var service, Method toExeMethod,Var ids){
		Statement statement =new Statement(serial,indent,service.getVarName() + "." + toExeMethod.getStandardName() + "("+ids.getVarName()+");");
		return statement;
	}
	
	public static Statement getFacadeCallServiceMethodByIds(long serial,int indent, Domain domain, Var service, Method toExeMethod,Var ids){
		Statement statement =new Statement(serial,indent,service.getVarName() + "." + toExeMethod.getStandardName() + "("+ids.getVarName()+");");
		return statement;
	}
	
	public static Statement getResponseRedirectUrl(long serial,int indent, Var response, String url){
		Statement statement = new Statement(serial,indent,response.getVarName() + ".sendRedirect(\"" + url + "\");");
		return statement;
	}
	
	public static Statement getStruts2ViewUrlStr(long serial,int indent,String url){
		Statement statement = new Statement(serial,indent, "return \"" + url + "\";");
		return statement;
	}
	
	public static Statement getStruts2ActionSuccessUrlStr(long serial,int indent){
		Statement statement = new Statement(serial,indent, "return Action.SUCCESS;");
		return statement;
	}
	
	public static Statement getSetDomainIdFromRequest(long serial, int indent, Domain domain, Var request){
		Statement statement = new Statement(serial,indent,"long " + StringUtil.lowerFirst(domain.getDomainId().getFieldName())+" = " + FieldUtil.generateRequestGetParameterString(domain.getDomainId(), request)+";");
		return statement;
	}
	
	public static Statement getSetFieldFromRequest(long serial, int indent, Domain domain, Var request, Field field){
		Statement statement = new Statement(serial,indent,"long " + StringUtil.lowerFirst(domain.getDomainId().getFieldName())+" = " + FieldUtil.generateRequestGetParameterString(field, request)+");");
		return statement;
	}
	
	public static Statement getSetDomainNameFromRequest(long serial, int indent, Domain domain, Var request){
		Statement statement = new Statement(serial,indent,"String " + StringUtil.lowerFirst(domain.getDomainName().getFieldName())+" = " + FieldUtil.generateRequestGetParameterString(domain.getDomainName(), request)+";");
		return statement;
	}
	
	public static Statement getSetDomainFieldFromRequest(long serial, int indent, Domain domain, Var request, Field field){
		Statement statement = new Statement(serial,indent,"String " + StringUtil.lowerFirst(field.getFieldName())+" = " + FieldUtil.generateRequestGetParameterString(field, request)+");");
		return statement;
	}
	
	public static Statement getPrepareServiceImplAsServiceStatement(long serial, int indent, Domain domain, Var service){
		Statement statement = new Statement(serial,indent, domain.getStandardName()+"Service "+service.getVarName() + " = new "+domain.getStandardName()+"ServiceImpl();");
		return statement;
	}
	
	public static Statement getCallServiceReturnDomainListStatement(long serial, int indent, Domain domain, Var list, Var service, Verb verb){
		verb.setDomain(domain);
		Statement statement = new Statement(serial,indent, "List<"+domain.getStandardName()+"> "+list.getVarName()+ " = " + service.getVarName() + "."+StringUtil.lowerFirst(verb.getVerbName())+"();");
		return statement;
	}
	
	public static Statement getDomainListFromRequestAttribute(long serial, int indent, Domain domain, Var list, Var request){
		Statement statement = new Statement(serial,indent, "List<"+ domain.getStandardName()+"> "+ list.getVarName() +" = (List<"+ domain.getStandardName()+">)" +request.getVarName()+".getAttribute(\"" + StringUtil.lowerFirst(domain.getStandardName())+"List\");");
		return statement;
	}
	
	public static Statement getRecordCountDoubleFromResultSet(long serial, int indent, Domain domain, Var resultSet, Var recordCount, Var countNum){
		Statement statement = new Statement(serial,indent,recordCount.getVarName() +" = "+ resultSet.getVarName()+".getDouble(\""+countNum.getVarName()+"\");");
		return statement;
	}
	
	public static Statement getRequestVarsWithDefaultValueStatement(long serial, int indent,Var request, Var param){
		Statement statement = new Statement(serial,indent,param.generateTypeVarString() +" = "+ request.getVarName()+".getParameter(\""+param.getVarName()+"\")==null?"+param.getValue()+":"+NamedTokenGenerator.getStringtoNumberToken(param.getVarType())+request.getVarName()+".getParameter(\""+param.getVarName()+"\"));");
		return statement;
	}
	
	public static Statement getSetRequestAttributeStatement(long serial, int indent, Var request, Var param){
		Statement statement = new Statement(serial,indent, request.getVarName()+".setAttribute(\""+ param.getVarName()+"\","+param.getVarName()+ ");");
		return statement;
	}
	
	public static Statement generateAddServiceImpl(long serial,int indent, Domain domain,Var dao){
		return new Statement(serial,indent,"\t"+dao.getVarName()+ ".add"+domain.getStandardName()+"("+domain.getLowerFirstDomainName()+");");
	}
	
	public static Statement generateServiceImplReturnInt(long serial,int indent, Var dao, Method daoReturnIntFunction){
		return new Statement(serial,indent, "return " + daoReturnIntFunction.generateStandardServiceImplCallString(dao.getVarName())+";");
	}
	
	public static Statement generateServiceImplReturnListByDomainName(long serial,int indent, Domain domain, Var dao, Method daoFunction){
		return new Statement(200L,indent,"\treturn " + dao.getVarName()+"."+daoFunction.generateStandardServiceImplCallString()+";");
	}
	
	
	public static Statement getEncodeMapToJsonResultMap(long serial,int indent, Var map){
		Statement statement =new Statement(serial,indent, "this.setResponseJson("+map.getVarName() +");");
		return statement;
	}	
	
	public static Statement getJumpToActionSuccess(long serial, int indent){
		Statement statement =new Statement(serial,indent, "return Action.SUCCESS;");
		return statement;
	}
	
	public static Statement getJsonResultMap(long serial,int indent, Var map){
		Statement statement =new Statement(serial,indent,"Map<String,Object> "+map.getVarName() +" = new TreeMap<String,Object>();");
		return statement;
	}
}
