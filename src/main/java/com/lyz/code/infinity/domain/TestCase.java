package com.lyz.code.infinity.domain;

import java.util.ArrayList;
import java.util.List;

public class TestCase {
	protected String standardName;
	protected List<Method> toTestMethods = new ArrayList<Method>();
	protected Class toTestClass;
	protected List<TestMethod> testMethods = new ArrayList<TestMethod>();
	protected List<String> tokens;
	protected String comment;
	protected String packageToken;

	public List<String> getTokens() {
		return tokens;
	}
	public void setTokens(List<String> tokens) {
		this.tokens = tokens;
	}
	
	public String getClassName() {
		return standardName;
	}
	public void setClassName(String className) {
		this.standardName = className;
	}
	
	public String generateTestCaseString(){
		return null;
	}
	public String getStandardName() {
		return standardName;
	}
	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}
	public List<Method> getToTestMethods() {
		return toTestMethods;
	}
	public void setToTestMethods(List<Method> toTestMethods) {
		this.toTestMethods = toTestMethods;
	}
	public Class getToTestClass() {
		return toTestClass;
	}
	public void setToTestClass(Class toTestClass) {
		this.toTestClass = toTestClass;
	}
	public List<TestMethod> getTestMethods() {
		return testMethods;
	}
	public void setTestMethods(List<TestMethod> testMethods) {
		this.testMethods = testMethods;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getPackageToken() {
		return packageToken;
	}
	public void setPackageToken(String packageToken) {
		this.packageToken = packageToken;
	}
	
}
