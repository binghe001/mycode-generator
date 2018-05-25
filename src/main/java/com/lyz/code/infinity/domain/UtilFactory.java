package com.lyz.code.infinity.domain;

public class UtilFactory {
	public static Util getInstance(String type){
		switch (type){
			case "project": return new ProjectUtil();
			default: return null;
		}
	}
}
