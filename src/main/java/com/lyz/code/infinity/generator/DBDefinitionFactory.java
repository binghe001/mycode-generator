package com.lyz.code.infinity.generator;

import com.lyz.code.infinity.utils.SqlReflector;

public class DBDefinitionFactory {
	public static DBDefinitionGenerator getInstance(String type){
		switch (type) {
		case "mysql" : return new MysqlDBDefinitionGenerator();
		default : return null;
		}
	}
}
