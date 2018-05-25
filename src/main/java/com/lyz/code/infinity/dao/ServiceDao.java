package com.lyz.code.infinity.dao;

import java.util.List;

import com.lyz.code.infinity.domain.Class;
import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Interface;
import com.lyz.code.infinity.domain.Method;
import com.lyz.code.infinity.domain.Naming;

public interface ServiceDao {
	public Interface generateService(Naming naming, Domain domain,List<Method> methods) throws Exception;
	public Class generateServiceImpl(Naming naming, Domain domain,List<Method> methods) throws Exception;
}
