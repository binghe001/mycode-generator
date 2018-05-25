package com.lyz.code.infinity.dao;

import java.util.List;

import com.lyz.code.infinity.core.Verb;
import com.lyz.code.infinity.domain.Class;
import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Interface;
import com.lyz.code.infinity.domain.Naming;

public interface DaoDao {
	public Interface generateDao(Domain domain, List<Verb> verbs) throws Exception;
	public Class generateDaoImpl(Domain domain, List<Verb> verbs) throws Exception;	
}
