package com.lyz.code.infinity.compiler;

import java.util.ArrayList;
import java.util.List;


public class SGSTokenizer
{
	public static List<String> generateTokens(String sgs){
		List<String> list = new ArrayList<String>();
		
		StringBuilder sb = new StringBuilder(sgs);
		StringBuilder token = new StringBuilder();
		for (int ii = 0; ii < sb.length();ii++){			
			if ((sb.charAt(ii)>='a'&& sb.charAt(ii) <= 'z')||(sb.charAt(ii)>='A'&& sb.charAt(ii) <= 'Z')||(sb.charAt(ii)>='0'&& sb.charAt(ii) <= '9') || sb.charAt(ii) =='.'||sb.charAt(ii) =='_'){
				token.append(sb.charAt(ii));
				continue;
			}
			if (sb.charAt(ii) == ' ' || sb.charAt(ii) == '\t' || sb.charAt(ii)=='\n') {
				String token0 = token.toString().trim();
				if (token0 != null && !"".equals(token0)) {
					list.add(token0);
					token = new StringBuilder();
				}
			}
			if (sb.charAt(ii) == '{'){
				String token0 = token.toString().trim();
				if (token0 != null && !"".equals(token0)) {
					list.add(token0);
					token = new StringBuilder();
				}
				list.add("{");
			}
			if (sb.charAt(ii) == '}'){
				String token0 = token.toString().trim();
				if (token0 != null && !"".equals(token0)) {
					list.add(token0);
					token = new StringBuilder();
				}
				list.add("}");
			}
			if (sb.charAt(ii) == ';'){
				String token0 = token.toString().trim();
				if (token0 != null && !"".equals(token0)) {
					list.add(token0);
					token = new StringBuilder();
				}
				list.add(";");
			}
			if (sb.charAt(ii) == ':'){
				String token0 = token.toString().trim();
				if (token0 != null && !"".equals(token0)) {
					list.add(token0);
					token = new StringBuilder();
				}
				list.add(":");
			}
			if (sb.charAt(ii) == '/'){
				String token0 = token.toString().trim();
				if (token0 != null && !"".equals(token0)) {
					list.add(token0);
					token = new StringBuilder();
				}
				if (sb.charAt(ii+1) == '/') {
					for(int jj = ii; jj <sb.length();jj++){
						if (sb.charAt(jj)=='\n'){
							String commentToken = sb.substring(ii,jj);
							list.add(commentToken);
							token = new StringBuilder();
							ii = jj;
							break;
						}
					}
				} else {
					return null;
				}
			}
		}
		return list;
		
	}
}

