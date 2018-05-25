package test.lyz.code.infinity.test;

import com.lyz.code.infinity.database.DBConf;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for default package");
		if (!DBConf.isTestsuiteOffline()){			
			//$JUnit-BEGIN$
//			suite.addTestSuite(PrivilegeDaoImplTest.class);

		} 
		//$JUnit-END$
		return suite;
	}
	
	public static void main(String[] args){
		junit.textui.TestRunner.run(suite());
	}
}
