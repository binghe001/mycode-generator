package com.lyz.code.infinity.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.lyz.code.infinity.core.Writeable;
import com.lyz.code.infinity.domain.Statement;
import com.lyz.code.infinity.domain.StatementList;

public class WriteableUtil {
	public static StatementList merge(List<Writeable> cw){
		StatementList sc = new StatementList();
		Collections.sort(cw);
		long ii = 100;
		for (Writeable wc:cw){
			if (wc instanceof Statement) {
				Statement s = (Statement)wc;
				s.setSerial(ii);
				sc.add(s);
				ii += 100;
			}
			else if (wc instanceof StatementList){
				List<Statement> list = ((StatementList) wc).getStatementList();
				Collections.sort(list);
				for (Statement s:list){
					s.setSerial(ii);
					ii += 100;
					sc.add(s);
				}
			}
		}
		return sc;
	}
}
