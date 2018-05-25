package test.lyz.code.infinity.domain;

import com.lyz.code.infinity.domain.Method;
import com.lyz.code.infinity.domain.Signature;
import com.lyz.code.infinity.domain.Type;

import junit.framework.TestCase;

public class MethodTest extends TestCase{
	public void testGetStandardCallString(){
		Method method = new Method();
		method.setStandardName("myMethod");
		
		Signature s1 = new Signature(0, "var1", new Type("int"));
		Signature s2 = new Signature(1, "myString", new Type("String"));
		
		method.addSignature(s1);
		method.addSignature(s2);
		
		System.out.println(method.getStandardCallString());
	}
}
