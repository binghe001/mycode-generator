package com.lyz.code.infinity.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.lyz.code.infinity.core.Writeable;
import com.lyz.code.infinity.utils.StringUtil;
import com.lyz.code.infinity.utils.WriteableUtil;

public class JavascriptMethod implements Comparable{
	protected int serial = 1;
	protected List<Signature> signatures = new ArrayList<Signature>();
	protected String standardName;
	protected String content = "";
	protected String methodComment;
	protected StatementList methodStatementList = new StatementList();

	public List<Signature> getSignatures() {
		return signatures;
	}

	public void setSignatures(List<Signature> signatures) {
		this.signatures = signatures;
	}

	public String getMethodComment() {
		return methodComment;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setMethodComment(String methodComment) {
		this.methodComment = methodComment;
	}

	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

	public String getStandardName() {
		return standardName;
	}

	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}
	
	public String getCapFirstMethodName() {
		return StringUtil.capFirst(standardName);
	}
	
	public String getLowerFirstMethodName() {
		return StringUtil.lowerFirst(standardName);
	}

	public void addSignature(Signature signature) {
		this.signatures.add(signature);
	}
	
	public String generateMethodDefinition(){
		return null;
	}

	public String getThrowException() {
		return "Exception";
	}

	public StatementList getMethodStatementList() {
		return methodStatementList;
	}
	
	public StatementList getMethodStatementList(long serial) {
		methodStatementList.setSerial(serial);
		return methodStatementList;
	}
	
	public StatementList generateMethodStatementList(long serial) {
		List<Writeable> sl = new ArrayList<Writeable>();
		sl.add(this.generateMethodHeaderStatement(serial, 0));
		sl.add(this.getMethodStatementList(serial+10L));
		sl.add(new Statement(serial+20L,0,"}"));
		
		StatementList mysl = WriteableUtil.merge(sl);
		mysl.setSerial(serial);
		return mysl;
	}

	public void setMethodStatementList(StatementList methodStatementList) {
		this.methodStatementList = methodStatementList;
	}
	
	public String generateMethodContentStringWithSerial() {
		if (this.methodStatementList != null){
			StringBuilder sb = new StringBuilder();
			Collections.sort(this.methodStatementList);
			for (Statement s : this.methodStatementList){
				sb.append("\t\t"+s.getSerial()+"\t:\t"+s.getContent()+"\n");
			}
			return sb.toString();
		}
		else return this.content;
	}
	
	public Statement generateMethodHeaderStatement(long serial,int indent) {
		StringBuilder sb = new StringBuilder("function "+ this.standardName+"(");
		Iterator it = this.getSignatures().iterator();
		boolean hasSig = false;
		while (it.hasNext()) {
			hasSig = true;
			Signature signature = (Signature) it.next();
			String name = signature.getName();
			Type type = signature.getType();
			sb.append(name).append(",");
		}
		if (hasSig) {
			sb = new StringBuilder(sb.substring(0, sb.length() - 1));
		}
		sb.append("){\n");
		return new Statement(serial,indent, sb.toString());
	}
	
	public String generateMethodContentString() {
		if (this.methodStatementList != null){
			StringBuilder sb = new StringBuilder();
			Collections.sort(this.methodStatementList);
			for (Statement s : this.methodStatementList){
				for (int i=0;i < s.getIndent();i++) sb.append("\t");
				sb.append(s.getContent()).append("\n");
			}
			return sb.toString();
		}
		else return this.content;
	}
	
	public String generateMethodString() {
		StringBuilder sb = new StringBuilder("function "+ this.standardName+"(");
		Iterator it = this.getSignatures().iterator();
		boolean hasSig = false;
		while (it.hasNext()) {
			hasSig = true;
			Signature signature = (Signature) it.next();
			String name = signature.getName();
			Type type = signature.getType();
			sb.append(name).append(",");
		}
		if (hasSig) {
			sb = new StringBuilder(sb.substring(0, sb.length() - 1));
		}
		sb.append("){\n");
		sb.append(this.generateMethodContentString());
		sb.append("}\n");
		return sb.toString();
	}
	
	public String generateMethodStringWithSerial() {
		StringBuilder sb = new StringBuilder("function "+ this.standardName+"(");
		Iterator it = this.getSignatures().iterator();
		boolean hasSig = false;
		while (it.hasNext()) {
			hasSig = true;
			Signature signature = (Signature) it.next();
			String name = signature.getName();
			Type type = signature.getType();
			sb.append(name).append(",");
		}
		if (hasSig) {
			sb = new StringBuilder(sb.substring(0, sb.length() - 1));
		}
		sb.append("){\n");
		sb.append(this.generateMethodContentStringWithSerial());
		sb.append("}\n");
		return sb.toString();
	}
	
	public String generateStandardMethodCallString() {
		StringBuilder sb = new StringBuilder(this.standardName+"(");
		Iterator it = this.getSignatures().iterator();
		boolean hasSig = false;
		while (it.hasNext()) {
			hasSig = true;
			Signature signature = (Signature) it.next();
			String name = signature.getName();
			Type type = signature.getType();
			sb.append(name).append(",");
		}
		if (hasSig) {
			sb = new StringBuilder(sb.substring(0, sb.length() - 1));
		}
		sb.append(")");
		return sb.toString();
	}

	
	@Override
	public int compareTo(Object o) {
		if (this.getSerial() > ((JavascriptMethod)o).getSerial()) return 1;
		else if (this.getSerial() == ((JavascriptMethod)o).getSerial()) return 0;
		else return -1;
	}
	
	@Override
	public boolean equals(Object o){
		return this.getStandardName().equals(((JavascriptMethod)o).getStandardName());
	}	
}
