package test.lyz.code.infinity.domain;

import junit.framework.TestCase;

import org.junit.Test;

import com.lyz.code.infinity.domain.Class;
import com.lyz.code.infinity.domain.Field;
import com.lyz.code.infinity.domain.Interface;
import com.lyz.code.infinity.domain.Method;
import com.lyz.code.infinity.domain.Signature;
import com.lyz.code.infinity.domain.Type;

public class InterfaceTest extends TestCase {

	@Test
	public void testGenerateInterfaceString() {
		Interface ifc = new Interface();
		ifc.setStandardName("Leave");
		ifc.setPackageToken("com.lyz.code.infinity");
		ifc.addField(new Field("id", "long"));
		ifc.addField(new Field("name", "String"));
		ifc.addField(new Field("comment", "String"));
		ifc.addField(new Field("description", "String"));
		ifc.addField(new Field("price", "double"));
		ifc.addField(new Field("amount", "int"));
		ifc.addField(new Field("updateTime", "Timestamp", "java.sql"));

		Method methoda = new Method();
		methoda.setStandardName("methoda");
		methoda.setReturnType(new Type("String"));
		Signature sig1 = new Signature(1, 0, "myid", new Type("long"));
		Signature sig2 = new Signature(2, 1, "mydescription", new Type("String"));
		methoda.addSignature(sig1);
		methoda.addSignature(sig2);
		methoda.setContent("// TODO:Jerry comments");

		ifc.addMethod(methoda);

		System.out.println(ifc.generateInterfaceString());
	}
}
