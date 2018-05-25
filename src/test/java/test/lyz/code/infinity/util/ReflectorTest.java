package test.lyz.code.infinity.util;

import junit.framework.TestCase;

import org.junit.Test;

import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.utils.Reflector;
import com.lyz.code.infinity.utils.StringUtil;

public class ReflectorTest extends TestCase {
	@Test
	public void testResultSetDomainValue() throws Exception {
		Domain d = new Domain();
		d.setStandardName("Leave");
		d.addField( "id","long");
		d.addField("name", "String");
		d.addField("comment","String");
		d.addField("description", "String");
		
		Reflector r = new Reflector();
		System.out.println(r.resultSetDomainValue(d,"result"));
	}
	
	@Test
	public void testSetDomainFromHttpPost() throws Exception {
		Domain d = new Domain();
		d.setStandardName("Leave");
		d.addField( "id","long");
		d.addField("name", "String");
		d.addField("comment","String");
		d.addField("description", "String");
		d.addField("price", "double");
		d.addField("amount", "int");
		
		Reflector r = new Reflector();
		System.out.println(r.setDomainFromHttpPost(d));
	}
}
