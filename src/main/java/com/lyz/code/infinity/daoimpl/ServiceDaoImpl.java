package com.lyz.code.infinity.daoimpl;

import java.util.List;

import com.lyz.code.infinity.dao.ServiceDao;
import com.lyz.code.infinity.domain.Class;
import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Interface;
import com.lyz.code.infinity.domain.Method;
import com.lyz.code.infinity.domain.Naming;
import com.lyz.code.infinity.domain.Service;

public class ServiceDaoImpl implements ServiceDao{

	@Override
	public Interface generateService(Naming naming, Domain domain,List<Method> methods)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class generateServiceImpl(Naming naming, Domain domain,List<Method> methods) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Class generateServiceImpl(Service service) throws Exception {
		return null;
	}
	
	public Service generateService(){
		return null;
	}
}
