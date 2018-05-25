package com.lyz.code.infinity.core;

public interface Writeable extends Comparable<Writeable>{
	public long getSerial();
	public String getContent();
	public String getContentWithSerial();
	public String getStatement(long pos);
	int compareTo(Writeable o);
}
