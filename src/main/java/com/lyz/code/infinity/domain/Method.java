package com.lyz.code.infinity.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.lyz.code.infinity.utils.StringUtil;

public class Method implements Comparable{
	protected int serial = 1;
	protected List<Signature> signatures = new ArrayList<Signature>();
	protected String standardName;
	protected String methodToken;
	protected String content = "";
	protected String methodComment;
	protected Type returnType;
	protected String returnTypePackageToken;
	protected StatementList methodStatementList = new StatementList();
	protected boolean throwException;
	protected List<String> otherExceptions = new ArrayList<String>();
	protected boolean isprotected = false;
	protected Set<String> additionalImports = new TreeSet<String>(new StringComparator());
	protected Set<String> metaDatas = new TreeSet<String>(new StringComparator());

	public Type getReturnType() {
		return returnType;
	}

	public void setReturnType(Type returnType) {
		this.returnType = returnType;
	}

	public List<Signature> getSignatures() {
		return signatures;
	}

	public void setSignatures(List<Signature> signatures) {
		this.signatures = signatures;
	}

	public String getMethodComment() {
		return methodComment;
	}

	public String getMethodToken() {
		return methodToken;
	}

	public void setMethodToken(String methodToken) {
		this.methodToken = methodToken;
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

	public String generateMethodString() {
		Type returnType = this.returnType;
		StringBuilder sb = new StringBuilder();
		if (returnType == null ||  "".equals(returnType)) returnType = new Type("void");
		for (String metaData : this.getMetaDatas()){
			sb.append("\t@").append(metaData).append("\n");
		}
		if (!this.isIsprotected()) sb.append("\tpublic ");
		else sb.append("\tprotected ");
		sb.append(returnType).append(" ").append(this.standardName).append("(");
		Iterator it = this.getSignatures().iterator();
		boolean hasSig = false;
		while (it.hasNext()) {
			hasSig = true;
			Signature signature = (Signature) it.next();
			String name = signature.getName();
			Type type = signature.getType();
			sb.append(type).append(" ").append(name).append(",");
		}
		if (hasSig) {
			sb = new StringBuilder(sb.substring(0, sb.length() - 1));
		}
		sb.append(")");
		if (this.throwException && this.otherExceptions.size() == 0) sb.append(" throws Exception");
		else if (this.throwException && this.otherExceptions.size() > 0) {
			sb.append(" throws ");
			for (String exception : this.otherExceptions){
				sb.append(exception).append(",");
			}
			sb = new StringBuilder(sb.substring(0, sb.length() - 1));
		}
		sb.append("{\n");
		if (this.content != null && !"".equals(content)){
			sb.append(content);
		} else if (this.getMethodStatementList() != null && this.getMethodStatementList().size() > 0){
			Collections.sort(this.methodStatementList);
			for (Statement s: this.methodStatementList){
				sb.append(s.getContent()).append("\n");
			}
		}
		sb.append("\t}\n");
		return sb.toString();
	}
	
	public Set<String> getMetaDatas() {
		return metaDatas;
	}

	public void setMetaDatas(Set<String> metaDatas) {
		this.metaDatas = metaDatas;
	}
	
	public void addMetaData(String metaData){
		this.metaDatas.add(metaData);
	}

	public String generateMethodDefinition(){
		StringBuilder sb = new StringBuilder("\tpublic ").append(this.returnType)
				.append(" ").append(this.standardName).append("(");
		Iterator it = this.getSignatures().iterator();
		boolean hasSig = false;
		while (it.hasNext()) {
			hasSig = true;
			Signature signature = (Signature) it.next();
			String name = signature.getName();
			Type type = signature.getType();
			sb.append(type).append(" ").append(name).append(",");
		}
		if (hasSig) {
			sb = new StringBuilder(sb.substring(0, sb.length() - 1));
		}
		sb.append(")");
		if (this.throwException && this.otherExceptions.size() == 0) sb.append(" throws Exception");
		else if (this.throwException && this.otherExceptions.size() > 0) {
			sb.append(" throws ");
			for (String exception : this.otherExceptions){
				sb.append(exception).append(",");
			}
			sb = new StringBuilder(sb.substring(0, sb.length() - 1));
		}
		sb.append(";");
		return sb.toString();
	}

	public String getReturnTypePackageToken() {
		return returnTypePackageToken;
	}

	public void setReturnTypePackageToken(String returnTypePackageToken) {
		this.returnTypePackageToken = returnTypePackageToken;
	}

	public String getThrowException() {
		return "Exception";
	}

	public void setThrowException(boolean throwException) {
		this.throwException = throwException;
	}
	
	public boolean isThrowException(){
		return this.throwException;
	}

	public StatementList getMethodStatementList() {
		return methodStatementList;
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
	
	public String generateStandardServiceImplCallString(String daoString){
		StringBuilder sb = new StringBuilder(daoString + "." + this.getStandardName()).append("("); 
		Iterator it = this.getSignatures().iterator();
		boolean hasSig = false;
		while (it.hasNext()) {
			hasSig = true;
			Signature signature = (Signature) it.next();
			String name = signature.getName();			
			sb.append(name).append(",");
		}
		if (hasSig) {
			sb = new StringBuilder(sb.substring(0, sb.length() - 1));
		}
		sb.append(")");
		return sb.toString();
	}
	
	public String generateStandardServiceImplUsingDomainPrefixCallString(String daoString,Domain domain){
		StringBuilder sb = new StringBuilder(daoString + "." + this.getStandardName()).append("("); 
		Iterator it = this.getSignatures().iterator();
		boolean hasSig = false;
		while (it.hasNext()) {
			hasSig = true;
			Signature signature = (Signature) it.next();
			String name = domain.getLowerFirstDomainName()+".get"+StringUtil.capFirst(signature.getName())+"()";			
			sb.append(name).append(",");
		}
		if (hasSig) {
			sb = new StringBuilder(sb.substring(0, sb.length() - 1));
		}
		sb.append(")");
		return sb.toString();
	}
	
	public String generateStandardDaoImplCallString(String daoString){
		StringBuilder sb = new StringBuilder(daoString + "." + this.getStandardName()).append("("); 
		Iterator it = this.getSignatures().iterator();
		boolean hasSig = false;
		while (it.hasNext()) {
			hasSig = true;
			Signature signature = (Signature) it.next();
			String name = signature.getName();			
			sb.append(name).append(",");
		}
		if (hasSig) {
			sb = new StringBuilder(sb.substring(0, sb.length() - 1));
		}
		sb.append(")");
		return sb.toString();
	}
	
	public String generateStandardCallString(){
		StringBuilder sb = new StringBuilder( this.getStandardName()).append("("); 
		Iterator it = this.getSignatures().iterator();
		boolean hasSig = false;
		while (it.hasNext()) {
			hasSig = true;
			Signature signature = (Signature) it.next();
			String name = signature.getName();			
			sb.append(name).append(",");
		}
		if (hasSig) {
			sb = new StringBuilder(sb.substring(0, sb.length() - 1));
		}
		sb.append(")");
		return sb.toString();
	}
	
	public String generateStandardServiceImplCallString(){
		StringBuilder sb = new StringBuilder( this.getStandardName()).append("("); 
		Iterator it = this.getSignatures().iterator();
		boolean hasSig = false;
		while (it.hasNext()) {
			hasSig = true;
			Signature signature = (Signature) it.next();
			String name = signature.getName();			
			sb.append(name).append(",");
		}
		if (hasSig) {
			sb = new StringBuilder(sb.substring(0, sb.length() - 1));
		}
		sb.append(")");
		return sb.toString();
	}

	public List<String> getOtherExceptions() {
		return otherExceptions;
	}

	public void setOtherExceptions(List<String> otherExceptions) {
		this.otherExceptions = otherExceptions;
	}

	public boolean isIsprotected() {
		return isprotected;
	}

	public void setIsprotected(boolean isprotected) {
		this.isprotected = isprotected;
	}

	public Set<String> getAdditionalImports() {
		return additionalImports;
	}

	public void setAdditionalImports(Set<String> additionalImports) {
		this.additionalImports = additionalImports;
	}
	
	public void addAdditionalImport(String additionalImport) {
		this.additionalImports.add(additionalImport);
	}
	
	public String getStandardCallString(){
		boolean hasSignature = false;
		StringBuilder sb = new StringBuilder("");
		sb.append(this.getStandardName()).append("(");
		for (Signature s :this.signatures){
			sb.append(s.getName()).append(",");
			if (hasSignature == false) hasSignature = true;
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(")");
		return sb.toString();
	}
	
	@Override
	public int compareTo(Object o) {
		if (this.getSerial() > ((Method)o).getSerial()) return 1;
		else if (this.getSerial() == ((Method)o).getSerial()) return 0;
		else return -1;
	}
	
	@Override
	public boolean equals(Object o){
		return this.getStandardName().equals(((Method)o).getStandardName());
	}	
}
