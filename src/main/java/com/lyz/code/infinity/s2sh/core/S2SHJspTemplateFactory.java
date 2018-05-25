package com.lyz.code.infinity.s2sh.core;

import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.exception.ValidateException;
import com.lyz.code.infinity.generator.JspTemplate;

public class S2SHJspTemplateFactory {
	public static JspTemplate getInstance(String type,Domain domain) throws Exception {
		switch (type) {
			case "grid" : return new GridJspTemplate(domain);
			case "pagingGrid" : return new PagingGridJspTemplate(domain);
			case "onlyloginindex":
						  return new OnlyLoginIndexJspTemplate();
			default		: return null;
		}
	}
}
