package test.lyz.code.infinity.compiler;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.lyz.code.infinity.compiler.SGSTokenizer;

public class SGSTokenizerTest extends TestCase{
	@Test
	public void testSGSTokenizer(){
		String sgs = "project EmployeeTest {\n" +
					 "  // myTest\n" +
					 "domain Employee{\n"+
					 "field: long employeeid;\n"+
					 "}\n"+
					 "}\n";
		List<String> ls = SGSTokenizer.generateTokens(sgs);
		
		for (int i = 0 ; i < ls.size();i++){
			System.out.println(""+i*100+":\t"+ls.get(i));
		}
	}
}
