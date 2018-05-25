package com.lyz.code.infinity.utils;

public class StringUtil {
	public static String capFirst(String value){
		if (value!=null && !"".equals(value)){
			return value.substring(0, 1).toUpperCase()+value.substring(1);
		} else {
			return "";
		}
	}

	public static String lowerFirst(String value){
		if (value!=null && !"".equals(value)){
			return value.substring(0, 1).toLowerCase()+value.substring(1);
		} else {
			return "";
		}
	}
	
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
}
