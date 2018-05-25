package test.lyz.code.infinity.domain;

import junit.framework.TestCase;

import org.junit.Test;

import com.lyz.code.infinity.domain.Class;
import com.lyz.code.infinity.domain.Field;
import com.lyz.code.infinity.domain.Method;
import com.lyz.code.infinity.domain.Signature;
import com.lyz.code.infinity.domain.Type;

public class ClassTest extends TestCase {
	@Test
	public void testGenerateClassString(){
		Class cz = new Class();
		cz.setStandardName("Leave");
		cz.setPackageToken("com.lyz.code.infinity");
		cz.addField( new Field("id","long"));
		cz.addField( new Field("name", "String"));
		cz.addField( new Field("comment","String"));
		cz.addField( new Field("description", "String"));
		cz.addField( new Field("price", "double"));
		cz.addField( new Field("amount", "int"));
		cz.addField(new Field("updatetime", "Date", "java.sql"));
		
		Method methoda = new Method();
		methoda.setStandardName("methoda");
		methoda.setReturnType(new Type("String"));
		Signature sig1 = new Signature(1,0,"myid",new Type("long"));
		Signature sig2 = new Signature(2,1,"mydescription",new Type("String"));
		methoda.addSignature(sig1);
		methoda.addSignature(sig2);
		methoda.setContent("// TODO:Jerry comments");
		
		cz.addMethod(methoda);
		
		System.out.println(cz.generateClassString());
		
	}
}
