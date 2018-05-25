package com.lyz.code.infinity.generator;

import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.utils.SqlReflector;

public class MysqlDBDefinitionGenerator extends DBDefinitionGenerator{

	public String generateDBSql(boolean createDB) throws Exception{
		// set up the database;
		StringBuilder sb = new StringBuilder();
		if (createDB == true){
			sb.append("drop database if exists ").append(this.getDbName()).append(";\n");
			sb.append("create database ").append(this.getDbName()).append("/*!40100 COLLATE 'utf8_general_ci' */;\n");
			sb.append("use ").append(this.getDbName()).append(";\n\n");
		}
			
		for (int i=0; i < this.getDomains().size();i++){
			Domain domain = this.getDomains().get(i);
			sb.append(SqlReflector.generateTableDefinition(domain)).append("\n");
		}
		return sb.toString();
	}
	
	public String generateDBSql() throws Exception{
		return generateDBSql(false);
	}
	
}
