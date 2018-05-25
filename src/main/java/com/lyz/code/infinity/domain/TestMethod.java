package com.lyz.code.infinity.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestMethod {
	protected long testmethodid;
	protected Method methodToTest;
	protected String standardName;
	protected String methodToken;
	protected String content = "";
	protected String testMethodComment;

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


	public String getStandardName() {
		return standardName;
	}

	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}

	public String generateTestMethodString() {
		return null;
	}
	
	public String generateTestMethodDefinition(){
		return null;
	}

	public long getTestmethodid() {
		return testmethodid;
	}

	public void setTestmethodid(long testmethodid) {
		this.testmethodid = testmethodid;
	}

	public Method getMethodToTest() {
		return methodToTest;
	}

	public void setMethodToTest(Method methodToTest) {
		this.methodToTest = methodToTest;
	}

	public String getTestMethodComment() {
		return testMethodComment;
	}

	public void setTestMethodComment(String testMethodComment) {
		this.testMethodComment = testMethodComment;
	}
}
