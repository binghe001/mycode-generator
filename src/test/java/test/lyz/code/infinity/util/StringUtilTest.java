package test.lyz.code.infinity.util;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.utils.SqlReflector;
import com.lyz.code.infinity.utils.StringUtil;

public class StringUtilTest extends TestCase{
	@Test
	public void testChangeDomainFieldtoTableColum(){
		String s = "DomainNameTokenId";
		String result = StringUtil.changeDomainFieldtoTableColum(s);
		System.out.println(result);
		Assert.assertEquals("domain_name_token_id",result);
	}
	
	@Test
	public void testGenerateSelectAllStatement() throws Exception{
		Domain domain = new Domain();
		domain.setPackageToken("com.lyz.code.infinity");
		domain.setStandardName("Employee");
		domain.addField("EmployeeId","long");
		domain.addField("Name", "String");
		domain.addField("Gender", "String");
		domain.addField("Age", "int");
		domain.addField("EmployeeDescription", "String");
		domain.addField("EmployeeComment","String");
		domain.addField("updateTime","Timestamp", "java.sql");
		
		System.out.println(SqlReflector.generateSelectAllStatement(domain));
	}
	
}
