package com.lyz.code.infinity.generator;

import java.sql.PreparedStatement;

import com.lyz.code.infinity.core.Verb;
import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Field;
import com.lyz.code.infinity.domain.Method;
import com.lyz.code.infinity.domain.Statement;
import com.lyz.code.infinity.domain.Var;
import com.lyz.code.infinity.utils.FieldUtil;
import com.lyz.code.infinity.utils.SqlReflector;
import com.lyz.code.infinity.utils.StringUtil;
import com.lyz.code.infinity.utils.VarUtil;

public class NamedStatementGenerator {
	public static Statement getSelectAllSqlStatement(long serial,int indent, Domain domain,  Var query){
		try {
			Statement statement = new Statement(serial,indent,query.generateTypeVarString() + " = \""+SqlReflector.generateSelectAllStatement(domain)+"\";");
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
	
	public static Statement getCountRecordSqlStatement(long serial,int indent, Domain domain,  Var query, Var recordCount,Var countNum){
		try {
			Statement statement = new Statement(serial,indent, query.generateTypeVarString() + " = \""+SqlReflector.generateCountRecordStatement(domain, countNum)+"\";");
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
	
	public static Statement getFindByIdStatement(long serial,int indent, Domain domain,  Var query){
		try {
			Statement statement = new Statement(serial,indent,query.generateTypeVarString() + " = \""+SqlReflector.generateFindByIdStatement(domain)+"\";");
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
			Statement statement = new Statement(serial,indent,query.generateTypeVarString() + " = \""+SqlReflector.generateSearchByNameStatement(domain)+"\";");
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
	
	public static Statement getDeleteSqlStatement(long serial, int indent,Domain domain,  Var query){
		try {
			Statement statement = new Statement(serial,indent, query.generateTypeVarString() + " = \"" + SqlReflector.generateDeleteSqlWithQuestionMark(domain) + "\";");
			return statement;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static Statement getSoftDeleteSqlStatement(long serial, int indent,Domain domain,  Var query){
		try {
			Statement statement = new Statement(serial,indent, query.generateTypeVarString() + " = \"" + SqlReflector.generateSoftDeleteSqlWithQuestionMark(domain) + "\";");
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
	
	public static Statement getPrepareStatementWithReturnGeneratedKeys(long serial,int indent,Var ps,Var query,Var connection){
		try {
			Statement statement = new Statement(serial,indent, ps.generateTypeVarString() + " = " + connection.getVarName()+ ".prepareStatement("+query.getVarName()+",PreparedStatement.RETURN_GENERATED_KEYS);");
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
	
	public static Statement getFacadeSetContentType(long serial,int indent, Var response, String charset){
		Statement statement =new Statement(serial,indent, response.getVarName() +".setContentType(\"application/json;charset="+charset+"\");");
		return statement;
	}
	
	public static Statement getControllerForward(long serial,int indent, Var response, Var request, String forwardUrl){
		Statement statement =new Statement(serial,indent, request.getVarName() +".getRequestDispatcher(\""+forwardUrl+"\").forward("+request.getVarName() +"," + response.getVarName()+");");
		return statement;
	}
	
	public static Statement getControllerPrintWriterOut(long serial,int indent, Var response, Var out){
		Statement statement =new Statement(serial,indent,out.generateTypeVarString() +" = "+ response.getVarName()+".getWriter();");
		return statement;
	}
	
	public static Statement getJsonResultMap(long serial,int indent, Var map){
		Statement statement =new Statement(serial,indent,"Map<String,Object> "+map.getVarName() +" = new TreeMap<String,Object>();");
		return statement;
	}
	
	public static Statement getEncodeMapToJsonResultMap(long serial,int indent, Var map, Var out){
		Statement statement =new Statement(serial,indent, out.getVarName() + ".print(JSONObject.fromObject("+map.getVarName() +").toString());");
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
	
	public static Statement getCallServiceMethodAssignSuccess(long serial,int indent, Var service, Method toExeMethod){
		Statement statement =new Statement(serial,indent, "boolean success = "+service.getVarName() + "." + toExeMethod.generateStandardServiceImplCallString() + ";");
		return statement;
	}
	
	public static Statement getResponseRedirectUrl(long serial,int indent, Var response, String url){
		Statement statement = new Statement(serial,indent,response.getVarName() + ".sendRedirect(\"" + url + "\");");
		return statement;
	}
	
	public static Statement getSetDomainIdFromRequest(long serial, int indent, Domain domain, Var request){
		Statement statement = new Statement(serial,indent,domain.getDomainId().getFieldType()+" " + StringUtil.lowerFirst(domain.getDomainId().getFieldName())+" = " + FieldUtil.generateRequestGetParameterString(domain.getDomainId(), request)+";");
		return statement;
	}
	
	public static Statement getIdsFromRequest(long serial, int indent, Domain domain,Var ids, Var request){
		Statement statement = new Statement(serial,indent, ids.generateTypeVarString() +" = " + VarUtil.generateRequestGetParameterString(ids, request)+";");
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
}
