package com.lyz.code.infinity.domain;

import com.lyz.code.infinity.core.Writeable;

public class Statement implements Writeable{
	protected long serial = 0;
	protected String statement;
	protected int indent = 0;

	public Statement(long serial, String statement){
		super();
		this.serial = serial;
		this.indent = 0;
		this.statement = statement;
	}
	
	public Statement(long serial, int intent, String statement){
		super();
		this.serial = serial;
		this.statement = statement;
		this.indent = intent;
	}
	public Statement(){
		this(0,"");
	}
	
	@Override
	public long getSerial() {
		return this.serial;
	}
	
	public void setSerial(long serial){
		this.serial = serial;
	}

	@Override
	public String getContent() {
		StringBuilder sb = new StringBuilder();
		for (int i=0 ; i < this.indent; i++){
			sb.append("\t");
		}
		return sb.append(this.statement).toString();
	}

	@Override
	public String getStatement(long pos) {
		return this.statement;
	}
	 
	public void setStatement(String statement){
		this.statement = statement;
	}

	@Override
	public int compareTo(Writeable o) {
		if (this.getSerial() < o.getSerial()) return -1;
		else if (this.serial == o.getSerial()) return 0;
		else return 1;
	}
	@Override
	public String getContentWithSerial() {
		StringBuilder sb = new StringBuilder();		
		sb.append(this.serial).append("\t\t：");
		for (int i=0 ; i < this.indent; i++) sb.append("\t");
		sb.append(this.statement);
		return sb.toString();
	}
	public int getIndent() {
		return indent;
	}
	public void setIndent(int indent) {
		this.indent = indent;
	}
	public String getStatement() {
		return statement;
	}

}
