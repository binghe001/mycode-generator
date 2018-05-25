package test.lyz.code.infinity.database;

import com.lyz.code.infinity.database.DBConf;

import junit.framework.TestCase;

/**
 * DataSafeTestCase must owned by Project Manager
 * Do not change it while you are a developer.
 * 
 * @author Rocketship
 *
 */
public class DataSafeTestCase extends TestCase{
	
	public DataSafeTestCase(){
		if (DBConf.isTestsuiteOffline() == true){ 
			Thread.currentThread().destroy();
		}
		try {
			this.setUp();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	final protected  void setUp() throws Exception {
		super.setUp();
		DBConf.switchToTest();		
		System.out.println("Jerry::DataSafeTestCase::setup()");
	}
	
	final protected void tearDown() throws Exception {
		super.tearDown();
		DBConf.switchToProduction();
		System.out.println("Jerry::DataSafeTestCase::teardown()");
	}

}
