package test.lyz.code.infinity.daoimpl;

import java.sql.Connection;
import java.util.HashMap;

import junit.framework.Assert;
import test.lyz.code.infinity.database.DataSafeTestCase;

import org.junit.Test;

import com.lyz.code.infinity.dao.MethodDao;
import com.lyz.code.infinity.daoimpl.MethodDaoImpl;
import com.lyz.code.infinity.database.DBConf;
import com.lyz.code.infinity.domain.Method;
import com.lyz.code.infinity.domain.Naming;

public class MethodDaoImplTest extends DataSafeTestCase {

	private MethodDao methodDao = new MethodDaoImpl();

	public MethodDao getMethodDao() {
		return methodDao;
	}

	public void setMethodDao(MethodDao methodDao) {
		this.methodDao = methodDao;
	}

	public void testGenerateMethod() throws Exception {
		// prepare data
		Connection con = DBConf.getConnection();
		con.prepareStatement("delete from methods;").executeUpdate();
		con.prepareStatement(
				"INSERT INTO `methods` (`method_id`, `method_standard_name`, `method_content`, `method_token`, `method_comment`, `naming_id`) VALUES (1, 'Create', '		connection = DBConf.initDB();\r\n        String query;\r\n\r\n        query = \"SELECT * FROM fine WHERE ID=\"\"+fine.getId()+\"\"\";\r\n\r\n        Fine fine0 = new Fine();\r\n        ResultSet result = connection.prepareStatement(query).executeQuery();\r\n\r\n        while(result.next()) {            \r\n        	//Build the fine object            \r\n            fine0.setId(result.getLong(\"id\"));\r\n            fine0.setUserId(result.getLong(\"userid\"));\r\n            fine0.setEmpId(result.getLong(\"empId\"));\r\n            fine0.setReason(result.getString(\"reason\"));\r\n            fine0.setFineBalance(result.getBigDecimal(\"fine_balance\"));\r\n            fine0.setDescription(result.getString(\"description\"));\r\n        }\r\n        \r\n        fine0.setFineBalance(fine0.getFineBalance().add(quantity));\r\n        String errorMessage = \"\";\r\n        updateFine(fine0, errorMessage);\r\n        DBConf.closeDB(connection);\r\n		return false;', 'general.crudfls.simple.Create', NULL, 1)")
				.executeUpdate();
		// Test begin
		Naming naming = new Naming();
		naming.setNamingid(1L);
		Method method = getMethodDao().generateMethod(naming, "Create");
		System.out.println(method.getContent());

		Assert.assertTrue(method.getContent().length() > 0);
		Assert.assertEquals("general.crudfls.simple.Create", method.getMethodToken());

		// Clean
		con.prepareStatement("delete from methods;").executeUpdate();

	}

	public void testGenerateMethodToString() throws Exception {
		// prepare data
		Connection con = DBConf.getConnection();
		con.prepareStatement("delete from methods;").executeUpdate();
		con.prepareStatement(
				"INSERT INTO `methods` (`method_id`, `method_standard_name`, `method_content`, `method_token`, `method_comment`, `naming_id`) VALUES (3, 'Create', '		connection = DBConf.initDB();\r\n        String query;\r\n\r\n        query = \"SELECT * FROM fine WHERE ID=\"\"+fine.getId()+\"\"\";\r\n\r\n        Fine fine0 = new Fine();\r\n        ResultSet result = connection.prepareStatement(query).executeQuery();\r\n\r\n        while(result.next()) {            \r\n        	//Build the fine object            \r\n            fine0.setId(result.getLong(\"id\"));\r\n            fine0.setUserId(result.getLong(\"userid\"));\r\n            fine0.setEmpId(result.getLong(\"empId\"));\r\n            fine0.setReason(result.getString(\"reason\"));\r\n            fine0.setFineBalance(result.getBigDecimal(\"fine_balance\"));\r\n            fine0.setDescription(result.getString(\"description\"));\r\n        }\r\n        \r\n        fine0.setFineBalance(fine0.getFineBalance().add(quantity));\r\n        String errorMessage = \"\";\r\n        updateFine(fine0, errorMessage);\r\n        DBConf.closeDB(connection);\r\n		return false;', 'general.crudfls.simple.Create', NULL, 1)")
				.executeUpdate();
		// Test begin
		Naming naming = new Naming();
		naming.setNamingid(1L);
		String result = getMethodDao().generateMethodToString(naming, "Create");
		System.out.println(result);

		Assert.assertTrue(result.length() > 0);

		// Clean
		con.prepareStatement("delete from methods;").executeUpdate();

	}

	@Test
	public void testGenerateMethodAndVars() throws Exception {
		// prepare data
		Connection con = DBConf.getConnection();
		con.prepareStatement("delete from methods;").executeUpdate();
		con.prepareStatement(
				"INSERT INTO `methods` (`method_id`, `method_standard_name`, `method_content`, `method_token`, `method_comment`, `naming_id`) VALUES (1, 'Create', '		connection = DBConf.initDB();\r\n        String query;\r\n\r\n        query = \"SELECT * FROM fine WHERE ID=\"\"+fine.getId()+\"\"\";\r\n\r\n        Fine fine0 = new Fine();\r\n        ResultSet result = connection.prepareStatement(query).executeQuery();\r\n\r\n        while(result.next()) {            \r\n        	//Build the fine object            \r\n            fine0.setId(result.getLong(\"id\"));\r\n            fine0.setUserId(result.getLong(\"userid\"));\r\n            fine0.setEmpId(result.getLong(\"empId\"));\r\n            fine0.setReason(result.getString(\"reason\"));\r\n            fine0.setFineBalance(result.getBigDecimal(\"fine_balance\"));\r\n            fine0.setDescription(result.getString(\"description\"));\r\n        }\r\n        \r\n        fine0.setFineBalance(fine0.getFineBalance().add(quantity));\r\n        String errorMessage = \"\";\r\n        updateFine(fine0, errorMessage);\r\n        DBConf.closeDB(connection);\r\n		return false;', 'general.crudfls.simple.Create', NULL, 1)")
				.executeUpdate();
		// Test begin
		Naming naming = new Naming();
		naming.setNamingid(1L);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Fine", "Leave");
		map.put("fine", "leave");
		Method method = getMethodDao().generateMethod(naming, "Create", map);
		System.out.println(method.getContent());

		Assert.assertTrue(method.getContent().length() > 0);

		// Clean
		con.prepareStatement("delete from methods;").executeUpdate();

	}
}
