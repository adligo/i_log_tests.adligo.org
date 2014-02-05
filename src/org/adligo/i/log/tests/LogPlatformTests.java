package org.adligo.i.log.tests;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.adligo.i.log.client.LogPlatform;
import org.adligo.i.log.tests.mocks.MockLogPlatform;
import org.adligo.i.util.client.I_SystemOutput;
import org.adligo.i.util.tests.mocks.MockPropertyFactory;
import org.adligo.jse.util.JSEPlatform;

public class LogPlatformTests extends TestCase implements I_SystemOutput {
	private Throwable lastExceptionPrint = null;
	private List<String> prints = new ArrayList<String>();
	private boolean log = true;
	
	public void testInit() throws Exception {
		Exception ex = null;
		try {
			LogPlatform.init();
		} catch (Exception x) {
			ex = x;
		}
		assertNotNull(ex);
		assertEquals("Please initalize your platform BEFORE the LogPlatform, " +
				"for instance JSEPlatform.init(), GWTPlatform.init(), J2MEPlatform.init. ", 
				ex.getMessage());
		
		JSEPlatform.init();
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
		String dotFile = new File(".").getAbsolutePath();
		String fileName = dotFile.substring(0, dotFile.length() - 2) + File.separator +
			"no_file.properties";
		
		LogPlatform.init("/no_file.properties");
		assertNull(lastExceptionPrint);
		assertEquals(2, prints.size());
		assertEquals("error reading file '/no_file.properties' " +
				"system file 'null' using defaults;", prints.get(0));
		assertEquals("using defaults {gwt_loggers=simple, defaultlog=INFO}", prints.get(1));
		
		URL url = LogPlatform.class.getResource("/adligo_log_log4j_factory.properties");
		fileName = url.getFile();
		MockLogPlatform.unInit();
		prints.clear();
		lastExceptionPrint = null;
		LogPlatform.init("/adligo_log_log4j_factory.properties");
		
		//this does in fact try to call out for a class that doesn't exist
		//
		assertNotNull(lastExceptionPrint);
		assertEquals("log_factory is null, because your code needs to call " +
				"LogPlatform.init(String name, I_LogFactory p) " +
				"with a valid instance of your logFactory org.adligo.i.log.log4j.Log4jFactory!", 
				lastExceptionPrint.getMessage());
	}

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		JSEPlatform.init();
		MockLogPlatform.setOutput(this);
		MockPropertyFactory.uninit();
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		JSEPlatform.init();
	}

	@Override
	public void err(String p) {
		prints.add(p);
	}

	@Override
	public void exception(Throwable x) {
		lastExceptionPrint  = x;
	}

	@Override
	public void out(String p) {
		assertTrue("Shouldn't have printing here " + p, false);
	}
}
