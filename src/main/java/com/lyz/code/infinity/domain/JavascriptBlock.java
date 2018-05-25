package com.lyz.code.infinity.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lyz.code.infinity.utils.StringUtil;

public class JavascriptBlock implements Comparable{
	protected int serial = 1;
	protected List<Signature> signatures = new ArrayList<Signature>();
	protected String standardName;
	protected String content = "";
	protected String blockComment;
	protected StatementList blockStatementList = new StatementList();

	public List<Signature> getSignatures() {
		return signatures;
	}

	public void setSignatures(List<Signature> signatures) {
		this.signatures = signatures;
	}

	public String getBlockComment() {
		return blockComment;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setBlockComment(String blockComment) {
		this.blockComment = blockComment;
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

	public String generateMethodString() {
		return null;
	}
	
	public String generateMethodDefinition(){
		return null;
	}

	public String getThrowException() {
		return "Exception";
	}

	public StatementList getMethodStatementList() {
		return blockStatementList;
	}
	
	public StatementList getMethodStatementList(long serial) {
		blockStatementList.setSerial(serial);
		return blockStatementList;
	}

	public void setMethodStatementList(StatementList blockStatementList) {
		this.blockStatementList = blockStatementList;
	}
	
	public String generateBlockContentStringWithSerial() {
		if (this.blockStatementList != null){
			StringBuilder sb = new StringBuilder();
			Collections.sort(this.blockStatementList);
			for (Statement s : this.blockStatementList){
				sb.append("\t\t"+s.getSerial()+"\t:\t"+s.getContent()+"\n");
			}
			return sb.toString();
		}
		else return this.content;
	}
	
	public String generateBlockContentString() {
		if (this.blockStatementList != null){
			StringBuilder sb = new StringBuilder();
			Collections.sort(this.blockStatementList);
			for (Statement s : this.blockStatementList){
				for (int i=0;i < s.getIndent();i++) sb.append("\t");
				sb.append(s.getContent()).append("\n");
			}
			return sb.toString();
		}
		else return this.content;
	}

	
	@Override
	public int compareTo(Object o) {
		if (this.getSerial() > ((JavascriptBlock)o).getSerial()) return 1;
		else if (this.getSerial() == ((JavascriptBlock)o).getSerial()) return 0;
		else return -1;
	}
	
	@Override
	public boolean equals(Object o){
		return this.getStandardName().equals(((JavascriptBlock)o).getStandardName());
	}	
}
