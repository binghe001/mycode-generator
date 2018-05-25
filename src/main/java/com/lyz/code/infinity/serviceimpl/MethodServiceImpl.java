package com.lyz.code.infinity.serviceimpl;

import java.util.List;

import com.lyz.code.infinity.dao.MethodDao;
import com.lyz.code.infinity.daoimpl.MethodDaoImpl;
import com.lyz.code.infinity.domain.Method;
import com.lyz.code.infinity.domain.Naming;
import com.lyz.code.infinity.service.MethodService;

public class MethodServiceImpl implements MethodService{
	private MethodDao methodDao = new MethodDaoImpl();

	@Override
	public boolean validateMethodSignature(List<String> signature)
			throws Exception {
		return methodDao.validateMethodSignature(signature);
	}

	@Override
	public Method generateMethod(Naming naming, String standardName)
			throws Exception {
		return methodDao.generateMethod(naming, standardName);
	}

	@Override
	public String generateMethodToString(Naming naming, String standardName)
			throws Exception {
		return methodDao.generateMethodToString(naming, standardName);
	}

	@Override
	public String generateMethodSkeleton(Naming naming, String standardName)
			throws Exception {
		return methodDao.generateMethodSkeleton(naming, standardName);
	}

	@Override
	public String generateMethodContent(Naming naming, String standardName)
			throws Exception {
		return methodDao.generateMethodContent(naming, standardName);
	}

}
