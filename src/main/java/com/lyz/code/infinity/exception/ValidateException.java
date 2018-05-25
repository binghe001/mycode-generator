package com.lyz.code.infinity.exception;

import java.util.ArrayList;
import java.util.List;

import com.lyz.code.infinity.domain.ValidateInfo;

public class ValidateException extends Exception{
	private static final long serialVersionUID = 2506542517173893138L;
	protected ValidateInfo validateInfo = new ValidateInfo();

	public ValidateInfo getValidateInfo() {
		return validateInfo;
	}

	public void setValidateInfo(ValidateInfo validateInfo) {
		this.validateInfo = validateInfo;
	}
	
	public ValidateException(ValidateInfo info){
		super();
		this.validateInfo = info;
	}
	
	public ValidateException(String info){
		super();
		ValidateInfo validateInfo = new ValidateInfo();
		List<String> msgs = new ArrayList<String>();
		msgs.add(info);
		validateInfo.setCompileErrors(msgs);
		this.validateInfo = validateInfo;
	}
}
