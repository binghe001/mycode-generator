package com.lyz.code.infinity.utils;
import java.util.Iterator;

import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Field;
import com.lyz.code.infinity.domain.Var;

public class SqlReflector {
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
	
	public static String generateDeleteSqlWithQuestionMark(Domain domain) throws Exception{
		String result = "delete from " +domain.getDbPrefix() + TableStringUtil.domainNametoTableName(domain) + " ";
        result += generatePrimaryWhereParamSqlToken(domain);
        result += ";";
        return result;
	}
	
	public static String generateSoftDeleteSqlWithQuestionMark(Domain domain) throws Exception{
		String result = "update " +domain.getDbPrefix() + TableStringUtil.domainNametoTableName(domain) + " set " + domain.getActive().getFieldName() + " = "+domain.getDomainDeletedStr()+" where ";
		result += StringUtil.changeDomainFieldtoTableColum(domain.getDomainId().getFieldName())+ " = ? ";
        result += ";";
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
		if ("long".equalsIgnoreCase(fieldType)||"int".equalsIgnoreCase(fieldType)||"Integer".equalsIgnoreCase(fieldType)){
			if ("id".equalsIgnoreCase(fieldName)||fieldName.equalsIgnoreCase(domain.getStandardName()+"id")||fieldName.equalsIgnoreCase(domain.getDomainId().getFieldName())){
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
		if  (fieldType.equalsIgnoreCase("int")||fieldType.equalsIgnoreCase("Integer")) {
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
		String result = "select "+ DomainTokenUtil.generateTableCommaFields(domain) + " from "+ domain.getDbPrefix() +TableStringUtil.domainNametoTableName(domain) +";";
        return result;
	}
	
	public static String generateSelectAllByPageStatement(Domain domain) throws Exception{
		String result = "select "+ DomainTokenUtil.generateTableCommaFields(domain) + " from "+ domain.getDbPrefix() +TableStringUtil.domainNametoTableName(domain) +" limit ?,?;";
        return result;
	}
	
	public static String generateCountRecordStatement(Domain domain, Var countNum) throws Exception{
		String result = "select count("+ StringUtil.changeDomainFieldtoTableColum(domain.getDomainId().getFieldName()) + ") as "+countNum.getVarName()+" from "+ domain.getDbPrefix() +TableStringUtil.domainNametoTableName(domain) +";";
        return result;
	}
	
	public static String generateSelectByFieldStatement(Domain domain, Field field) throws Exception{
		String result = "select "+  DomainTokenUtil.generateTableCommaFields(domain) + " from "+ domain.getDbPrefix() +TableStringUtil.domainNametoTableName(domain) +" where "+ StringUtil.changeDomainFieldtoTableColum(field.getFieldName())+" = ?;";
        return result;
	}
	
	public static String generateSelectActiveStatement(Domain domain) throws Exception{
		String result = "select "+ DomainTokenUtil.generateTableCommaFields(domain) + " from "+ domain.getDbPrefix() +TableStringUtil.domainNametoTableName(domain) +" where "+StringUtil.changeDomainFieldtoTableColum(domain.getActive().getFieldName())+" = "+domain.getDomainActiveStr()+";";
        return result;
	}
	
	public static String generateFindByIdStatement(Domain domain) throws Exception{
		String result = "select "+DomainTokenUtil.generateTableCommaFields(domain) + " from "+ domain.getDbPrefix() + TableStringUtil.domainNametoTableName(domain) +" where "+StringUtil.changeDomainFieldtoTableColum(domain.getDomainId().getFieldName())+" = ?;";
        return result;
	}
	
	public static String generateFindByNameStatement(Domain domain) throws Exception{
		String result = "select "+ DomainTokenUtil.generateTableCommaFields(domain) + " from "+ domain.getDbPrefix() + TableStringUtil.domainNametoTableName(domain) +" where "+StringUtil.changeDomainFieldtoTableColum(domain.getDomainName().getFieldName())+" = ?;";
        return result;
	}
	
	public static String generateSearchByNameStatement(Domain domain) throws Exception{
		String result = "select "+ DomainTokenUtil.generateTableCommaFields(domain) + " from "+ domain.getDbPrefix() + TableStringUtil.domainNametoTableName(domain) +" where "+StringUtil.changeDomainFieldtoTableColum(domain.getDomainName().getFieldName())+" like ?";
        return result;
	}
	
	public static String generateSearchByDescriptionStatement(Domain domain, Field description) throws Exception{
		String result = "select " +DomainTokenUtil.generateTableCommaFields(domain) + " from "+ domain.getDbPrefix() + TableStringUtil.domainNametoTableName(domain) +" where "+StringUtil.changeDomainFieldtoTableColum(description.getFieldName())+" like %?%;";
        return result;
	}
}
