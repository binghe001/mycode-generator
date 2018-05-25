package com.lyz.code.infinity.utils;

import javax.servlet.http.HttpServletRequest;

import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Type;
import com.lyz.code.infinity.domain.Var;

public class InterVarUtil {
		public static class DB {
			public static final Var query = new Var("query",new Type("String"));
			public static final Var query0 = new Var("query0",new Type("String"));
			public static final Var query1 = new Var("query1",new Type("String"));
			public static final Var query2 = new Var("query2",new Type("String"));
			public static final Var sql = new Var("sql",new Type("String"));
			public static final Var sql0 = new Var("sql0",new Type("String"));
			public static final Var connection = new Var("connection",new Type("Connection","java.sql"));
			public static final Var ps = new Var("ps",new Type("PreparedStatement","java.sql"));
			public static final Var result = new Var("result",new Type("ResultSet","java.sql"));
			public static final Var rs = new Var("rs",new Type("ResultSet","java.sql"));
			public static final Var dbconf = new Var("DBConf",new Type("DBConf","com.lyz.code.infinity.database"));
			public static final Var dao = new Var("dao", new Type("Dao"));
			public static final Var service = new Var("service", new Type("Service"));
			public static final Var ids = new Var("ids",new Type("String"));
		}
		
		public static class Hibernate {
			public static final Var hql = new Var("hql",new Type("String"));
		}
		
		public static class Loop {
			public static final Var i = new Var("i",new Type("int"));
			public static final Var j = new Var("j",new Type("int"));
			public static final Var k = new Var("k",new Type("int"));
			public static final Var ii = new Var("ii",new Type("long"));
			public static final Var jj = new Var("jj",new Type("long"));
			public static final Var kk = new Var("kk",new Type("long"));
		}
		
		public static class Servlet {
			public static final Var response = new Var("response",new Type("HttpServletRequest","javax.servlet.http"));
			public static final Var request = new Var("request",new Type("HttpServletRequest","javax.servlet.http"));
			public static final Var out = new Var("out", new Type("PrintWriter","java.io"));
		}
		
		public static class SimpleJEE {
			public static final Var UTF8 = new Var("UTF-8",new Type("String"));
		}
		
		public static class Container {
			public static Var getList(Domain domain) {
				Var list = new Var("list",new Type("List",domain, "java.util"));
				return list;
			}			
		}
		
		public static class Common {
			public static Var service(Domain domain) {
				Var service = new Var("service",new Type(domain.getStandardName()+"Service",domain, domain.getPackageToken()+"."+domain.getStandardName()+"Service"));
				return service;
			}
		}
}
