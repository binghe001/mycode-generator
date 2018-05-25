package test.lyz.code.infinity.domain;

import junit.framework.TestCase;

import org.junit.Test;

import com.lyz.code.infinity.domain.Bo;
import com.lyz.code.infinity.domain.Field;
import com.lyz.code.infinity.domain.Method;
import com.lyz.code.infinity.domain.Signature;
import com.lyz.code.infinity.domain.Type;

public class BoTest extends TestCase {
	@Test
	public void testGenerateClassString(){
		Bo bo = new Bo();
		bo.setPackageToken("com.lyz.code.infinity");
		bo.setStandardName("Leave");
		bo.addField( new Field("id","long"));
		bo.addField( new Field("name", "String"));
		bo.addField( new Field("comment","String"));
		bo.addField( new Field("description", "String"));
		bo.addField( new Field("price", "double"));
		bo.addField( new Field("amount", "int"));
		bo.addField("updateTime","Date","java.sql");
		
		Method methoda = new Method();
		methoda.setStandardName("methoda");
		methoda.setReturnType(new Type("String"));
		Signature sig1 = new Signature(1,0,"myid",new Type("long"));
		Signature sig2 = new Signature(2,1,"mydescription",new Type("String"));
		methoda.addSignature(sig1);
		methoda.addSignature(sig2);
		methoda.setContent("// TODO:Jerry comments");
		
		bo.addMethod(methoda);
		
		System.out.println(bo.generateClassString());
		
	}
}
