package com.lyz.code.infinity.service;

import java.util.List;

import com.lyz.code.infinity.domain.Method;
import com.lyz.code.infinity.domain.Naming;

public interface MethodService {
	public boolean validateMethodSignature(List<String> signature) throws Exception;

	public Method generateMethod(Naming naming,String standardName) throws Exception;
	public String generateMethodToString(Naming naming, String standardName) throws Exception;
	public String generateMethodSkeleton(Naming naming, String standardName) throws Exception;
	public String generateMethodContent(Naming naming, String standardName) throws Exception;

}
