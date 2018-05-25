package com.lyz.code.infinity.s2sh.core;

import java.util.List;

import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Statement;
import com.lyz.code.infinity.domain.StatementList;

public class S2SHXmlGenerator {
	
	public static StatementList generateDataSourceXMLUsingMySqlStatementList(long serial,int indent,String dbname,String username,String password,boolean emptypassword){
		StatementList list = new StatementList();
		list.add(new Statement(1000L,indent,"<bean id=\"dataSource\" class=\"org.springframework.jdbc.datasource.DriverManagerDataSource\">"));
		list.add(new Statement(2000L,indent+1,"<property name=\"driverClassName\" value=\"com.mysql.jdbc.Driver\"></property>"));
		list.add(new Statement(3000L,indent+1,"<property name=\"url\" value=\"jdbc:mysql://localhost:3306/"+dbname+"?useUnicode=true&amp;characterEncoding=utf-8\"></property>"));
		list.add(new Statement(4000L,indent+1,"<property name=\"username\" value=\""+username+"\"></property>"));
		long myserial = 5000L;
		if (!emptypassword){
			list.add(new Statement(myserial,indent+1,"<property name=\"password\" value=\""+password+"\"></property>"));
		}
		list.add(new Statement(myserial+1000L,indent,"</bean>"));
		list.setSerial(serial);
		return list;
	}
	
	public static StatementList generateSessionFactoryStatementList(long serial,int indent,List<String> packagesToScan){
		StatementList list = new StatementList();
		list.add(new Statement(1000L,indent,"<bean id=\"sessionFactory\" class=\"org.springframework.orm.hibernate4.LocalSessionFactoryBean\">"));
		list.add(new Statement(2000L,indent+1,"<property name=\"dataSource\" ref=\"dataSource\"></property>"));
		list.add(new Statement(3000L,indent+1,"<property name=\"configLocation\" value=\"classpath:hibernate.cfg.xml\"></property>"));
		list.add(new Statement(4000L,indent+1,"<property name=\"packagesToScan\">"));
		list.add(new Statement(5000L,indent+2,"<list>"));
		long myserial = 5000L;
		for (String packstr : packagesToScan){
			list.add(new Statement(myserial,indent+3,"<value>"+packstr+"</value>"));
			myserial += 1000L;
		}
		list.add(new Statement(myserial+1000L,indent+2,"</list>"));
		list.add(new Statement(myserial+2000L,indent+1,"</property>"));
		list.add(new Statement(myserial+3000L,indent,"</bean>"));
		list.setSerial(serial);
		return list;
	}
	
	public static StatementList generateApplicationContentXmlDaoServiceBeansStatementList(long serial,int indent,Domain domain){
		StatementList list = new StatementList();
		list.add(new Statement(1000L,indent,"<bean id=\""+domain.getLowerFirstDomainName()+"Dao\" class=\""+domain.getPackageToken()+".daoimpl."+domain.getCapFirstDomainName()+"DaoImpl\">"));
		list.add(new Statement(2000L,indent+1,"<property name=\"sessionFactory\" ref=\"sessionFactory\"></property>"));
		list.add(new Statement(3000L,indent,"</bean>\n"));
		list.add(new Statement(4000L,indent,"<bean id=\""+domain.getLowerFirstDomainName()+"Service\" class=\""+domain.getPackageToken()+".serviceimpl."+domain.getCapFirstDomainName()+"ServiceImpl\">"));
		list.add(new Statement(5000L,indent+1,"<property name=\"dao\" ref=\""+domain.getLowerFirstDomainName()+"Dao\"></property>"));
		list.add(new Statement(6000L,indent,"</bean>\n"));
		list.setSerial(serial);
		return list;
	}
	
	public static StatementList generateApplicationContentXmlActionBeansStatementList(long serial,int indent,Action action){
		StatementList list = new StatementList();
		list.add(new Statement(1000L,indent,"<bean id=\""+action.getLowerFirstActionName()+"\" class=\""+action.getPackageToken()+".action."+action.getCapFirstActionName()+"\" scope=\"prototype\">"));
		list.add(new Statement(2000L,indent+1,"<property name=\"service\" ref=\""+action.getDomain().getLowerFirstDomainName()+"Service\"></property>"));
		list.add(new Statement(3000L,indent,"</bean>\n"));
		list.setSerial(serial);
		return list;
	}
	
	public static StatementList generateApplicationContentXmlFacadeBeansStatementList(long serial,int indent,Struts2Facade facade){
		StatementList list = new StatementList();
		list.add(new Statement(1000L,indent,"<bean id=\""+facade.getLowerFirstFacadeName()+"\" class=\""+facade.getPackageToken()+".facade."+facade.getCapFirstFacadeName()+"\" scope=\"prototype\">"));
		list.add(new Statement(2000L,indent+1,"<property name=\"service\" ref=\""+facade.getDomain().getLowerFirstDomainName()+"Service\"></property>"));
		list.add(new Statement(3000L,indent,"</bean>\n"));
		list.setSerial(serial);
		return list;
	}

}
