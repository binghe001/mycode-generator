package com.lyz.code.infinity.dao;

import com.lyz.code.infinity.domain.Naming;

public interface DomainDao {
public String generateDomainString(Naming naming, String standardName) throws Exception;
public String generateDomainString(long namingid);
}
