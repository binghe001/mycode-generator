package com.lyz.code.infinity.dao;

import java.util.List;

import com.lyz.code.infinity.domain.Method;
import com.lyz.code.infinity.domain.Naming;

public interface UtilDao {
	public String generateUtilString(Naming naming, String standardName) throws Exception;

}
