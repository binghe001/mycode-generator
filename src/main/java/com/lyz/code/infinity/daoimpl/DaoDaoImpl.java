package com.lyz.code.infinity.daoimpl;

import java.util.List;

import com.lyz.code.infinity.core.Verb;
import com.lyz.code.infinity.dao.DaoDao;
import com.lyz.code.infinity.domain.Class;
import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Interface;

public class DaoDaoImpl implements DaoDao{

	@Override
	public Interface generateDao(Domain domain,List<Verb> verbs) throws Exception {
		Interface dao = new Interface();
		//dao.set
		return null;
	}

	@Override
	public Class generateDaoImpl(Domain domain,List<Verb> verbs) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
