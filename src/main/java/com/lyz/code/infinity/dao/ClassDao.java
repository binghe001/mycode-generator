package com.lyz.code.infinity.dao;

import com.lyz.code.infinity.domain.Class;
import com.lyz.code.infinity.domain.Naming;

public interface ClassDao {
	public Class generateClass(Naming naming, String standardName) throws Exception;
	public String generateClassString(Naming naming, String standardName) throws Exception;
}
