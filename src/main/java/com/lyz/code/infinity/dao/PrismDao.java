package com.lyz.code.infinity.dao;

import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Naming;
import com.lyz.code.infinity.domain.Prism;

public interface PrismDao {
	public Prism generatePrism(Naming naming, String standardName,Domain domain) throws Exception;
	public void generatePrismFiles(Prism prism);
}
