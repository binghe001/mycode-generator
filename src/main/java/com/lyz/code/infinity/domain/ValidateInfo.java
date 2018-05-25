package com.lyz.code.infinity.domain;

import java.util.ArrayList;
import java.util.List;

public class ValidateInfo {
	protected boolean success = true;
	protected List<String> compileErrors = new ArrayList<String>();
	protected List<String> compileWarnings = new ArrayList<String>();
	
	public boolean success(){
		if (compileErrors.size() > 0) return false;
		else return true;
	}
	
	public List<String> getCompileErrors(){
		return this.compileErrors;
	}

	public boolean isSuccess() {
		return success();
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<String> getCompileWarnings() {
		return compileWarnings;
	}

	public void setCompileWarnings(List<String> compileWarnings) {
		this.compileWarnings = compileWarnings;
	}

	public void setCompileErrors(List<String> compileErrors) {
		this.compileErrors = compileErrors;
	}
	
	public void clearCompileErrors(){
		this.compileErrors = new ArrayList<String>();
	}
	
	public void clearCompileWarnings(){
		this.compileWarnings = new ArrayList<String>();
	}
	
	public void addCompileError(String compileError){
		this.compileErrors.add(compileError);
	}
	
	public void addAllCompileErrors(List<String> compileErrors){
		this.compileErrors.addAll(compileErrors);
	}
	
	public void addCompileWarning(String compileWarning){
		this.compileWarnings.add(compileWarning);
	}
	
	public void addAllCompileWarnings(List<String> compileWarnings){
		this.compileWarnings.addAll(compileWarnings);
	}
	
	public static ValidateInfo mergeValidateInfo(List<ValidateInfo> list){
		ValidateInfo info = new ValidateInfo();
		info.setSuccess(true);
		for (ValidateInfo iinfo : list){
			info.addAllCompileErrors(iinfo.getCompileErrors());
			info.addAllCompileWarnings(iinfo.getCompileWarnings());
		}
		for (ValidateInfo iinfo : list){
			if (iinfo.isSuccess() == false) info.setSuccess(false);
		}
		return info;
	}
}
