package com.lyz.code.infinity.utils;

import java.util.Set;

import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Field;


public class DomainTokenUtil {
	public static String changeDomainFieldtoTableColum(String value){
		StringBuilder sb = new StringBuilder(value);
		StringBuilder sb0 = new StringBuilder("");
		boolean continueCap = false;
		for(int i=0; i < sb.length(); i++){
			char ch = sb.charAt(i);
			if (ch<='Z'&& ch>='A'&&i>0&&!continueCap){
				sb0.append("_").append((""+ch).toLowerCase());
				continueCap = true;
			}else if (ch<='Z'&& ch>='A'&&i==0){
				sb0.append((""+ch).toLowerCase());
				continueCap = true;
			} else if (ch<='Z'&& ch>='A'&&continueCap){
				sb0.append((""+ch).toLowerCase());
			}else if (ch<='z'&& ch>='a') {
				sb0.append(ch);
				continueCap = false;
			}else {
				sb0.append(ch);
			}
		}
		return sb0.toString();
	}
	
	public static String generateTableCommaFields(Domain domain){
		Set<Field> set = domain.getFields();
		StringBuilder sb = new StringBuilder();
		for (Field f: set){
			sb.append(changeDomainFieldtoTableColum(f.getFieldName())).append(",");
		}
		if (set.size() > 0) sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
}
