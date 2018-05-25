package com.lyz.code.infinity.dao;

import java.util.List;
import java.util.Map;

import com.lyz.code.infinity.domain.Method;
import com.lyz.code.infinity.domain.Naming;
import com.lyz.code.infinity.domain.Signature;

public interface MethodDao {
	public boolean validateMethodSignature(List<String> signature) throws Exception;

	public Method generateMethod(Naming naming,String standardName) throws Exception;
	public Method generateMethod(Naming naming, String standardName,Map<String, String> vars) throws Exception;
	public String generateMethodToString(Naming naming, String standardName) throws Exception;
	public String generateMethodSkeleton(Naming naming, String standardName) throws Exception;
	public String generateMethodContent(Naming naming, String standardName) throws Exception;
	public Method generateMethod(Naming naming, String standardName, String returnVal, List<Signature> signatures) throws Exception;
}
