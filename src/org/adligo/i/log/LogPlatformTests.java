package org.adligo.i.log;

import junit.framework.TestCase;

import org.adligo.i.log.client.LogPlatform;
import org.adligo.i.log.mocks.MockLogPlatform;
import org.adligo.i.util.client.I_SystemOutput;
import org.adligo.i.util.mocks.MockPropertyFactory;
import org.adligo.j2se.util.J2SEPlatform;

public class LogPlatformTests extends TestCase implements I_SystemOutput {
	private Exception lastExceptionPrint = null;

	public void testInit() throws Exception {
		Exception ex = null;
		try {
			LogPlatform.init();
		} catch (Exception x) {
			ex = x;
		}
		assertNotNull(ex);
		assertEquals("Please initalize your platform BEFORE the LogPlatform, " +
				"for instance J2SEPlatform.init(), GWTPlatform.init(), J2MEPlatform.init. ", 
				ex.getMessage());
		
		J2SEPlatform.init();
		MockLogPlatform.unInit();
		LogPlatform.init();
		
		
		ex = null;
		try {
			LogPlatform.init();
		} catch (Exception x) {
			ex = x;
		}
		assertNotNull(ex);
		assertEquals("LogPlatform has already been initialized.", 
				ex.getMessage());
		
		
		MockLogPlatform.unInit();
		lastExceptionPrint  = null;
		LogPlatform.init("adligo_log_log4j_factory.properties");
		assertNotNull(lastExceptionPrint);
		assertEquals("Error reading property file '/adligo_log_log4j_factory.properties'", 
				lastExceptionPrint .getMessage());
		
		assertNotNull(lastExceptionPrint.getCause());
		assertEquals("log_factory is null, because your code needs to call " +
				"LogPlatform.addLogFactoryClass(String name, I_LogFactory p) " +
				"with a valid instance of your logFactory org.adligo.i.log.log4j.Log4jFactory!", 
				lastExceptionPrint.getCause().getMessage());
	}

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		J2SEPlatform.init();
		MockLogPlatform.setOutput(this);
		MockPropertyFactory.uninit();
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		J2SEPlatform.init();
	}

	@Override
	public void err(String p) {
		assertTrue("Shouldn't have printing here " + p, false);
	}

	@Override
	public void exception(Exception x) {
		lastExceptionPrint  = x;
	}

	@Override
	public void out(String p) {
		assertTrue("Shouldn't have printing here " + p, false);
	}
}
