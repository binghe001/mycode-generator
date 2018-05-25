package com.lyz.code.infinity.domain;

import java.util.ArrayList;
import java.util.List;

public class TestSuite {
	protected List<TestSuite> testSuites = new ArrayList<TestSuite>();
	protected List<TestCase> testCases = new ArrayList<TestCase>();
	protected String packageToken;
	
	public void generateTestSuiteFiles(){
		
	}

	public List<TestSuite> getTestSuites() {
		return testSuites;
	}

	public void setTestSuites(List<TestSuite> testSuites) {
		this.testSuites = testSuites;
	}

	public List<TestCase> getTestCases() {
		return testCases;
	}

	public void setTestCases(List<TestCase> testCases) {
		this.testCases = testCases;
	}
	
	public void addTestCase(TestCase testCase){
		this.testCases.add(testCase);
	}
	
	public void addTestSuite(TestSuite testSuite){
		this.testSuites.add(testSuite);
	}

	public String getPackageToken() {
		return packageToken;
	}

	public void setPackageToken(String packageToken) {
		this.packageToken = packageToken;
	}
}
