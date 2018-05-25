package test.lyz.code.infinity.compiler;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.lyz.code.infinity.compiler.SGSCompiler;
import com.lyz.code.infinity.compiler.SGSTokenizer;
import com.lyz.code.infinity.domain.Project;

public class SGSCompilerTest extends TestCase{
	@Test
	public void testSGSCompiler() throws Exception{
		String sgs = "project EmployeeTest{\n" +
					 "domain Employee {\n" +
					 "domainid:employeeId long;\n" +
					 "field:joinTime Timestamp java.util;\n" +
					 "field:employeeId long;\n" +
					 "}\n"+
					 "prism EmployeePrism{\n" +
					 "prismdomain:Employee;\n" +
					 "}\n"+
					 "}\n";
		
		List<String> ls = SGSTokenizer.generateTokens(sgs);
		
		for (int i = 0 ; i < ls.size();i++){
			System.out.println(""+(i+1)*100+":\t"+ls.get(i));
		}
		
		Project project = SGSCompiler.translate(sgs,false);
		project.setFolderPath("D:/JerryWork/Infinity/testFiles2/");
		project.generateProjectZip();
	}

}
