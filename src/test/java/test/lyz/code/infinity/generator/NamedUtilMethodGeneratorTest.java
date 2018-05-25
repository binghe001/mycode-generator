package test.lyz.code.infinity.generator;

import junit.framework.TestCase;

import org.junit.Test;

import com.lyz.code.infinity.domain.Method;
import com.lyz.code.infinity.domain.Type;
import com.lyz.code.infinity.domain.Var;
import com.lyz.code.infinity.generator.NamedStatementListGenerator;
import com.lyz.code.infinity.generator.NamedUtilMethodGenerator;

public class NamedUtilMethodGeneratorTest extends TestCase{

	@Test
	public void testGenerateServletDoGetCallProcessRequestMethod() {
		Var request = new Var("request",new Type("HttpServletRequest","javax.servlet.http"));
		Var response = new Var("response",new Type("HttpServletResponse","javax.servlet.http"));
		Method method = NamedUtilMethodGenerator.generateServletDoGetCallProcessRequestMethod(2, request, response);
		System.out.println(method.generateMethodString());
	}
	
	@Test
	public void testGenerateServletDoPostCallProcessRequestMethod() {
		Var request = new Var("request",new Type("HttpServletRequest","javax.servlet.http"));
		Var response = new Var("response",new Type("HttpServletResponse","javax.servlet.http"));
		Method method = NamedUtilMethodGenerator.generateServletDoPostCallProcessRequestMethod(2, request, response);
		System.out.println(method.generateMethodString());
	}
	
	@Test
	public void testGenerateGetServletInfoMethod() {
		Method method = NamedUtilMethodGenerator.generateGetServletInfoMethod(2, "JerryTest");
		System.out.println(method.generateMethodString());
	}
}
