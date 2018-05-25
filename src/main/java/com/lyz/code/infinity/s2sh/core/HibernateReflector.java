package com.lyz.code.infinity.s2sh.core;
import java.util.Iterator;

import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Field;
import com.lyz.code.infinity.domain.Var;
import com.lyz.code.infinity.utils.DomainTokenUtil;
import com.lyz.code.infinity.utils.StringUtil;
import com.lyz.code.infinity.utils.TableStringUtil;

public class HibernateReflector {
	public static String generateTableDefinition(Domain domain) throws Exception{
		String result = "create table " + domain.getDbPrefix() + TableStringUtil.domainNametoTableName(domain) +" (";
		Iterator it = domain.getFields().iterator();
        while (it.hasNext()) {	
	        Field f = (Field)it.next();
	        String fieldName = f.getFieldName();
	        String fieldType = f.getFieldType();
	        result += changeDomainFieldtoTableColumDefinitionToken(domain, fieldName, fieldType)+ ",";
        }
		String  ptoken = generatePrimaryKeySqlToken(domain);
		if (ptoken.length() >0 ){
			result += ptoken;
		}else {
			result = result.substring(0,result.length()-1);
		}
        result += ");";
		return result;
	}

	public static String generateInsertSqlWithQuestionMark(Domain domain) throws Exception{
		String result = "insert into " + domain.getDbPrefix() +TableStringUtil.domainNametoTableName(domain) +" ";
		Iterator it = domain.getFieldsWithoutId().iterator();
		
		result += "( ";
        while (it.hasNext()) {	
        	Field f = (Field)it.next();
 	        String fieldName = f.getFieldName();
 	        String fieldType = f.getFieldType();
	        result += StringUtil.changeDomainFieldtoTableColum(fieldName)+ ",";
        }
        result = result.substring(0,result.length()-1);
        result += ") values (";
		Iterator it2 = domain.getFieldsWithoutId().iterator();
        while (it2.hasNext()) {	
        	Field f = (Field)it2.next();
 	        String fieldName = f.getFieldName();
 	        String fieldType = f.getFieldType();
	        result += "?,";
        }
        result = result.substring(0,result.length()-1);
        result += ");";
        return result;
	}
	
	public static String generateUpdateSqlWithQuestionMark(Domain domain) throws Exception{
		String result = "update " +domain.getDbPrefix() + TableStringUtil.domainNametoTableName(domain) +" set ";
		Iterator it = domain.getFields().iterator();
        while (it.hasNext()) {	
        	Field f = (Field)it.next();
 	        String fieldName = f.getFieldName();
 	        String fieldType = f.getFieldType();
	        if (!isPrimaryKey(domain, fieldName, fieldType)) {
	        	result += StringUtil.changeDomainFieldtoTableColum(fieldName)+ " = ? ,";
	        }
        }
        result = result.substring(0,result.length()-1);
        result += generatePrimaryWhereParamSqlToken(domain);
        result += ";";
        return result;
	}
	
	public static String generateDeleteHibernateHqlStatement(Domain domain) throws Exception{
		String result = "delete from " +domain.getStandardName() + " " + StringUtil.lowerFirst(domain.getStandardName()).charAt(0) + " where " +
				StringUtil.lowerFirst(domain.getStandardName()).charAt(0) +"."+domain.getDomainId().getFieldName() + " = :"+domain.getDomainId().getFieldName() ;
		
        return result;
	}
	
	public static String generateSoftDeleteHqlWithLabel(Domain domain) throws Exception{
		String result = "update " +domain.getStandardName() + " " + domain.getLowerFirstChar() + " set " + domain.getLowerFirstChar() +"."+domain.getActive().getLowerFirstFieldName() + " = "+domain.getDomainDeletedStr()+" where ";
		result += domain.getLowerFirstChar()+"."+domain.getDomainId().getLowerFirstFieldName()+ " = :"+domain.getDomainId().getLowerFirstFieldName();
        return result;
	}
	
	public static String changeDomainFieldtoTableColumDefinitionToken(Domain domain, String fieldName, String fieldType){
		String result = StringUtil.changeDomainFieldtoTableColum(fieldName) + " ";
		result += lookupSqlType(fieldName,fieldType) + " ";
		if (isPrimaryKey(domain,fieldName,fieldType)){
			result += "not null auto_increment";
		} else {
			result += "null ";
		}
		return result;
	}
	
	public static boolean isPrimaryKey(Domain domain,String fieldName,String fieldType){
		boolean retVal = false;
		if ("long".equalsIgnoreCase(fieldType)||"bigint".equalsIgnoreCase(fieldType)){
			if ("id".equalsIgnoreCase(fieldName)||fieldName.equalsIgnoreCase(domain.getStandardName()+"id")){
				retVal =true;
			}
		}
		return retVal;
	}
	
	public static String generatePrimaryKeySqlToken(Domain domain){
		String result = "";
		Iterator it = domain.getFields().iterator();
        while (it.hasNext()) {	
        	Field f = (Field)it.next();
 	        String fieldName = f.getFieldName();
 	        String fieldType = f.getFieldType();
	        if (isPrimaryKey(domain, fieldName, fieldType)){
	        	result =  "primary key (" + StringUtil.changeDomainFieldtoTableColum(fieldName) +")";
	        }
        }
        return result;
	}
	
	public static String generatePrimaryWhereParamSqlToken(Domain domain){
		String result = "";
		Iterator it = domain.getFields().iterator();
        while (it.hasNext()) {	
        	Field f = (Field)it.next();
 	        String fieldName = f.getFieldName();
 	        String fieldType = f.getFieldType();
	        if (isPrimaryKey(domain, fieldName, fieldType)){
	        	result = "where " +  StringUtil.changeDomainFieldtoTableColum(fieldName) +" = ?";
	        }
        }
        return result;
	}
	
	public static String lookupSqlType(String fieldName, String fieldType){
		String result = "";
		if (fieldType.equalsIgnoreCase("long")) {
			result = "BigInt";
		}
		if  (fieldType.equalsIgnoreCase("int")||fieldType.equalsIgnoreCase("int")) {
			result = "Integer";
		}
		if  (fieldType.equalsIgnoreCase("float")||fieldType.equalsIgnoreCase("double")) {
			result = "Double";
		}
		if  (fieldType.equalsIgnoreCase("BigDecimal")||fieldType.equalsIgnoreCase("decimal")) {
			result = "Decimal";
		}
		if  (fieldType.equalsIgnoreCase("boolean")) {
			result = "bool";
		}
		if  (fieldType.equalsIgnoreCase("String")) {
			if (fieldName.toLowerCase().contains("comment")||fieldName.toLowerCase().contains("description")||fieldName.toLowerCase().contains("content")){
				result = "text";
			}else {
				result = "varchar(256)";
			}
		}
		return result; 
	}
	
	public static String generateSelectAllStatement(Domain domain) throws Exception{
		String result = "from "+ domain.getStandardName();
        return result;
	}
	
	public static String generateSelectActiveHqlStatement(Domain domain) throws Exception{
		String result = "FROM "+ domain.getStandardName() +" "+domain.getLowerFirstChar() +" where "+domain.getLowerFirstChar()+"."+domain.getActive().getLowerFirstFieldName()+ " = " +domain.getDomainActiveStr();
        return result;
	}

	public static String generateSelectAllByPageStatement(Domain domain) throws Exception{
		String result = "select "+ DomainTokenUtil.generateTableCommaFields(domain) + " from "+ domain.getDbPrefix() +TableStringUtil.domainNametoTableName(domain) +" limit ?,?;";
        return result;
	}
	
	public static String generateCountAllRecordStatement(Domain domain, Var countNum) throws Exception{
		String result = "select count("+ domain.getCapFirstDomainName() + ") as "+countNum.getVarName()+" from "+ domain.getCapFirstDomainName();
        return result;
	}
	
	public static String generateSelectByFieldStatement(Domain domain, Field field) throws Exception{
		String result = "select "+  DomainTokenUtil.generateTableCommaFields(domain) + " from "+ domain.getDbPrefix() +TableStringUtil.domainNametoTableName(domain) +" where "+ StringUtil.changeDomainFieldtoTableColum(field.getFieldName())+" = ?;";
        return result;
	}
	
	public static String generateSelectActiveStatement(Domain domain) throws Exception{
		String result = "select "+ DomainTokenUtil.generateTableCommaFields(domain) + " from "+ domain.getDbPrefix() +TableStringUtil.domainNametoTableName(domain) +" where "+StringUtil.changeDomainFieldtoTableColum(domain.getActive().getFieldName())+" = 'Y';";
        return result;
	}
	
	public static String generateFindByIdStatement(Domain domain) throws Exception{
		String result = "from "+ domain.getCapFirstDomainName() + " " + domain.getLowerFirstChar() +" where "+domain.getLowerFirstChar()+"."+domain.getDomainId().getLowerFirstFieldName()+" = :"+domain.getDomainId().getLowerFirstFieldName();
        return result;
	}
	
	public static String generateFindByNameStatement(Domain domain) throws Exception{
		String result = "from "+ domain.getCapFirstDomainName() + " " + domain.getLowerFirstChar() +" where "+domain.getLowerFirstChar()+"."+domain.getDomainName().getLowerFirstFieldName()+" = :"+domain.getDomainName().getLowerFirstFieldName();
        return result;
	}

	public static String generateSearchByNameHibernateStatement(Domain domain) throws Exception{
		String result = " from "+ domain.getStandardName()+ " "+ domain.getLowerFirstChar() +" where "+domain.getLowerFirstChar()+"." +domain.getDomainName().getLowerFirstFieldName()+" like :"+domain.getDomainName().getLowerFirstFieldName();
        return result;
	}
	
	public static String generateSearchByDescriptionStatement(Domain domain, Field description) throws Exception{
		String result = "select " +DomainTokenUtil.generateTableCommaFields(domain) + " from "+ domain.getDbPrefix() + TableStringUtil.domainNametoTableName(domain) +" where "+StringUtil.changeDomainFieldtoTableColum(description.getFieldName())+" like %?%;";
        return result;
	}
}
