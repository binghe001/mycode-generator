package com.lyz.code.infinity.core;

import com.lyz.code.infinity.domain.ConfigFile;

public class Log4jProperties extends ConfigFile{
	protected String content = "view plain copy print?\n"+
		"log4j.rootLogger=DEBUG, Console\n\n"+    
		"#Console\n"+
		"log4j.appender.Console=org.apache.log4j.ConsoleAppender\n"+  
		"log4j.appender.Console.layout=org.apache.log4j.PatternLayout\n"+ 
		"log4j.appender.Console.layout.ConversionPattern=%d [%t] %-5p [%c] - %m%n\n\n"+  
		"log4j.logger.java.sql.ResultSet=INFO\n"+ 
		"log4j.logger.org.apache=INFO\n"+
		"log4j.logger.java.sql.Connection=DEBUG\n"+  
		"log4j.logger.java.sql.Statement=DEBUG\n"+
		"log4j.logger.java.sql.PreparedStatement=DEBUG\n";
	
	public Log4jProperties(){
		super();
		this.standardName = "log4j.properties";
	}
	
	@Override 
	public void setStandardName(String standardName){
	}
	
	@Override
	public String generateConfigFileString() {
		StringBuilder sb = new StringBuilder();
		sb.append(content);
		return sb.toString();
	}
}