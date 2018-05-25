package com.lyz.code.infinity.utils;

import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Type;

public class TypeUtil {
	public static class Collection {
		public static class List {
			public static Type getListInstance(Domain d){
				if (d != null)
					return new Type("List", d, "java.util");
				else
					return new Type("List", "java.util");
			}
		}
		
		public static class ArrayList {
			public static Type getListInstance(Domain d){
				if (d != null)
					return new Type("ArrayList", d, "java.util");
				else
					return new Type("ArrayList", "java.util");
			}
		}
		
		public static class Set {
			public static Type getSetInstance(Domain d){
				if (d != null)
					return new Type("Set", d,"java.util");
				else
					return new Type("Set","java.util");
			}
		}
	}
}
