package org.adligo.i.log.shared.tests;

import java.util.HashMap;

import junit.framework.TestCase;

import org.adligo.i.log.shared.DeferredLog;
import org.adligo.i.log.shared.I_LogDelegate;
import org.adligo.i.log.shared.LogPlatform;
import org.adligo.i.log.shared.SimpleLog;
import org.adligo.i.util.shared.I_Map;
import org.adligo.i.util.shared.I_SystemOutput;
import org.adligo.i.util.shared.MapFactory;
import org.adligo.i.util.shared.SystemOutput;
import org.adligo.jse.util.MapWrapper;

public class SimpleLogTests extends TestCase implements I_SystemOutput {
	private String currentLog = "";
	private String newMessage = "";
	private String errTrace;
	private Exception x = null;
	
	static {
		JUnitNEclipseErrorMessage.init();
	}
	
	public void setUp() {
		try {
			 Exception t = new Exception("Ex");
			 throw t;
		} catch (Exception y) {
			x = y;
		}
		
		StackTraceElement[] elements = x.getStackTrace();
		StringBuffer sb = new StringBuffer();
		sb.append("\n   <java.lang.Exception: Ex>\n");
		for (int i = 0; i < elements.length; i++) {
			sb.append("     at ");
			sb.append(elements[i].toString());
			sb.append("\n");
		}
		errTrace = sb.toString();
	}
	
	@Override
	public void out(String p) {
		newMessage = p;
		assertEquals("Sould match", currentLog,p);
	}

	@SuppressWarnings("unchecked")
	public void testOutputs() {
		MockSimpleLog.setOut(this);
		
		SimpleLog log = new SimpleLog("TestLog", new MapWrapper(new HashMap()));
		log.setLevel(I_LogDelegate.LOG_LEVEL_FATAL);
		assertFatal(log);
		log.setLevel(I_LogDelegate.LOG_LEVEL_ERROR);
		assertError(log);
		log.setLevel(I_LogDelegate.LOG_LEVEL_WARN);
		assertWarn(log);
		log.setLevel(I_LogDelegate.LOG_LEVEL_INFO);
		assertInfo(log);
		log.setLevel(I_LogDelegate.LOG_LEVEL_DEBUG);
		assertDebug(log);
		log.setLevel(I_LogDelegate.LOG_LEVEL_TRACE);
		assertTrace(log);
		
		I_Map map = new MapWrapper(new HashMap());
		setDefaultLog(log, map, "FATAL");
		assertFatal(log);
		setDefaultLog(log, map, "ERROR");
		assertError(log);
		setDefaultLog(log, map, "WARN");
		assertWarn(log);
		setDefaultLog(log, map, "INFO");
		assertInfo(log);
		setDefaultLog(log, map, "DEBUG");
		assertDebug(log);
		setDefaultLog(log, map, "TRACE");
		assertTrace(log);
		
		setDefaultLog(log, map, "fatal");
		assertFatal(log);
		setDefaultLog(log, map, "error");
		assertError(log);
		setDefaultLog(log, map, "warn");
		assertWarn(log);
		setDefaultLog(log, map, "info");
		assertInfo(log);
		setDefaultLog(log, map, "debug");
		assertDebug(log);
		setDefaultLog(log, map, "trace");
		assertTrace(log);
		
		setDefaultLog(log, map, "fatal");
		setTestLog(log, map, "fatal");
		assertFatal(log);
		setTestLog(log, map, "error");
		assertError(log);
		setTestLog(log, map, "warn");
		assertWarn(log);
		setTestLog(log, map, "info");
		assertInfo(log);
		setTestLog(log, map, "debug");
		assertDebug(log);
		setTestLog(log, map, "trace");
		assertTrace(log);
		
		setTestLog(log, map, "FATAL");
		assertFatal(log);
		setTestLog(log, map, "ERROR");
		assertError(log);
		setTestLog(log, map, "WARN");
		assertWarn(log);
		setTestLog(log, map, "INFO");
		assertInfo(log);
		setTestLog(log, map, "DEBUG");
		assertDebug(log);
		setTestLog(log, map, "TRACE");
		assertTrace(log);
		
		MockSimpleLog.setOut(SystemOutput.INSTANCE);
	}

	public void setTestLog(SimpleLog log, I_Map map, String level) {
		map.put("TestLog", level);
		log.setLogLevel(map);
	}
	
	public void setDefaultLog(SimpleLog log, I_Map map, String level) {
		map.put("defaultlog", level);
		log.setLogLevel(map);
	}
	
	/**
	 * fix for defaultlog
	 * set to something besides INFO
	 * 
	 */
	public void testgetStringProperty() {
		LogPlatform.setDebug(true);
		
		I_Map props = MapFactory.create();
		
		props.put("defaultlog", "DEBUG");
		
		short result = SimpleLog.getLogLevel(props, "org.adligo");
		assertEquals(I_LogDelegate.LOG_LEVEL_DEBUG, result);
		
		props.put("org", "WARN");
		result = SimpleLog.getLogLevel(props, "org.adligo");
		assertEquals(I_LogDelegate.LOG_LEVEL_WARN, result);
		
		props.put("org.adligo", "INFO");
		result = SimpleLog.getLogLevel(props, "org.adligo");
		assertEquals(I_LogDelegate.LOG_LEVEL_INFO, result);
		
		props.put("defaultlog", "FATAL");
		result = SimpleLog.getLogLevel(props, "com.bar");
		assertEquals(I_LogDelegate.LOG_LEVEL_FATAL, result);
		
		result = SimpleLog.getLogLevel(props, "org.adligo");
		assertEquals(I_LogDelegate.LOG_LEVEL_INFO, result);
	}
	
	
	
	public void assertFatal(SimpleLog log) {
		setupNextBlock();
		log.trace("hey");
		assertOutputNotCalled();
		log.trace("hey", x);
		assertOutputNotCalled();
		
		setupNextBlock();
		log.debug("hey");
		assertOutputNotCalled();
		log.debug("hey", x);
		assertOutputNotCalled();
		
		setupNextBlock();
		log.info("hey");
		assertOutputNotCalled();
		log.info("hey", x);
		assertOutputNotCalled();
		
		setupNextBlock();
		log.warn("hey");
		assertOutputNotCalled();
		log.warn("hey", x);
		assertOutputNotCalled();
		
		setupNextBlock();
		log.error("hey");
		assertOutputNotCalled();
		log.error("hey", x);
		assertOutputNotCalled();
		
		setupNextMessage("[FATAL] TestLog -   hey" + errTrace);
		log.fatal("hey", x);
		assertOutputCalled();
		setupNextMessage("[FATAL] TestLog -   hey\n");
		log.fatal("hey");
		assertOutputCalled();
		
	}

	public void assertError(SimpleLog log) {
		
		setupNextBlock();
		log.trace("hey");
		assertOutputNotCalled();
		
		setupNextBlock();
		log.debug("hey");
		assertOutputNotCalled();
		
		setupNextBlock();
		log.info("hey");
		assertOutputNotCalled();
		
		setupNextBlock();
		log.warn("hey");
		assertOutputNotCalled();
		
		setupNextMessage("[ERROR] TestLog -   hey\n");
		log.error("hey");
		assertOutputCalled();
		setupNextMessage("[ERROR] TestLog -   hey" + errTrace);
		log.error("hey", x);
		assertOutputCalled();
		
		setupNextMessage("[FATAL] TestLog -   hey\n");
		log.fatal("hey");
		assertOutputCalled();
		setupNextMessage("[FATAL] TestLog -   hey" + errTrace);
		log.fatal("hey", x);
		assertOutputCalled();
	}
	
	public void assertWarn(SimpleLog log) {
		setupNextBlock();
		log.trace("hey");
		assertOutputNotCalled();
		
		setupNextBlock();
		log.debug("hey");
		assertOutputNotCalled();
		
		setupNextBlock();
		log.info("hey");
		assertOutputNotCalled();
		
		setupNextMessage("[WARN] TestLog -   hey\n");
		log.warn("hey");
		assertOutputCalled();
		setupNextMessage("[WARN] TestLog -   hey" + errTrace);
		log.warn("hey", x);
		assertOutputCalled();
		
		setupNextMessage("[ERROR] TestLog -   hey\n");
		log.error("hey");
		assertOutputCalled();
		setupNextMessage("[ERROR] TestLog -   hey" + errTrace);
		log.error("hey", x);
		assertOutputCalled();
		
		setupNextMessage("[FATAL] TestLog -   hey\n");
		log.fatal("hey");
		assertOutputCalled();
		setupNextMessage("[FATAL] TestLog -   hey" + errTrace);
		log.fatal("hey", x);
		assertOutputCalled();
	}
	
	public void assertInfo(SimpleLog log) {
		setupNextBlock();
		log.trace("hey");
		assertOutputNotCalled();
		
		setupNextBlock();
		log.debug("hey");
		assertOutputNotCalled();
		
		setupNextMessage("[INFO] TestLog -   hey\n");
		log.info("hey");
		assertOutputCalled();
		setupNextMessage("[INFO] TestLog -   hey" + errTrace);
		log.info("hey", x);
		assertOutputCalled();
		
		setupNextMessage("[WARN] TestLog -   hey\n");
		log.warn("hey");
		assertOutputCalled();
		setupNextMessage("[WARN] TestLog -   hey" + errTrace);
		log.warn("hey", x);
		assertOutputCalled();
		
		setupNextMessage("[ERROR] TestLog -   hey\n");
		log.error("hey");
		assertOutputCalled();
		setupNextMessage("[ERROR] TestLog -   hey" + errTrace);
		log.error("hey", x);
		assertOutputCalled();
		
		setupNextMessage("[FATAL] TestLog -   hey\n");
		log.fatal("hey");
		assertOutputCalled();
		setupNextMessage("[FATAL] TestLog -   hey" + errTrace);
		log.fatal("hey", x);
		assertOutputCalled();
	}
	
	public void assertDebug(SimpleLog log) {
		setupNextBlock();
		log.trace("hey");
		assertOutputNotCalled();
		
		setupNextMessage("[DEBUG] TestLog -   hey\n");
		log.debug("hey");
		assertOutputCalled();
		setupNextMessage("[DEBUG] TestLog -   hey" + errTrace);
		log.debug("hey", x);
		assertOutputCalled();
		
		setupNextMessage("[INFO] TestLog -   hey\n");
		log.info("hey");
		assertOutputCalled();
		setupNextMessage("[INFO] TestLog -   hey" + errTrace);
		log.info("hey", x);
		assertOutputCalled();
		
		setupNextMessage("[WARN] TestLog -   hey\n");
		log.warn("hey");
		assertOutputCalled();
		setupNextMessage("[WARN] TestLog -   hey" + errTrace);
		log.warn("hey", x);
		assertOutputCalled();
		
		setupNextMessage("[ERROR] TestLog -   hey\n");
		log.error("hey");
		assertOutputCalled();
		setupNextMessage("[ERROR] TestLog -   hey" + errTrace);
		log.error("hey", x);
		assertOutputCalled();
		
		setupNextMessage("[FATAL] TestLog -   hey\n");
		log.fatal("hey");
		assertOutputCalled();
		setupNextMessage("[FATAL] TestLog -   hey" + errTrace);
		log.fatal("hey", x);
		assertOutputCalled();
	}
	
	
	public void assertTrace(SimpleLog log) {
		setupNextMessage("[TRACE] TestLog -   hey\n");
		log.trace("hey");
		assertOutputCalled();
		setupNextMessage("[TRACE] TestLog -   hey" + errTrace);
		log.trace("hey", x);
		assertOutputCalled();
		
		setupNextMessage("[DEBUG] TestLog -   hey\n");
		log.debug("hey");
		assertOutputCalled();
		setupNextMessage("[DEBUG] TestLog -   hey" + errTrace);
		log.debug("hey", x);
		assertOutputCalled();
		
		setupNextMessage("[INFO] TestLog -   hey\n");
		log.info("hey");
		assertOutputCalled();
		setupNextMessage("[INFO] TestLog -   hey" + errTrace);
		log.info("hey", x);
		assertOutputCalled();
		
		setupNextMessage("[WARN] TestLog -   hey\n");
		log.warn("hey");
		assertOutputCalled();
		setupNextMessage("[WARN] TestLog -   hey" + errTrace);
		log.warn("hey", x);
		assertOutputCalled();
		
		setupNextMessage("[ERROR] TestLog -   hey\n");
		log.error("hey");
		assertOutputCalled();
		setupNextMessage("[ERROR] TestLog -   hey" + errTrace);
		log.error("hey", x);
		assertOutputCalled();
		
		setupNextMessage("[FATAL] TestLog -   hey\n");
		log.fatal("hey");
		assertOutputCalled();
		setupNextMessage("[FATAL] TestLog -   hey" + errTrace);
		log.fatal("hey", x);
		assertOutputCalled();
	}
	
	public void assertOutputCalled() {
		assertTrue("Should have logged something", !"".equals(newMessage));
	}

	public void assertOutputNotCalled() {
		assertTrue("Should NOT have logged something", "".equals(newMessage));
	}
	
	public void setupNextBlock() {
		newMessage = "";
		currentLog = "";
	}
	
	public void setupNextMessage(String p) {
		newMessage = "";
		currentLog = p;
	}
	

	protected String getErrTrace() {
		return errTrace;
	}

	protected void setErrTrace(String errTrace) {
		this.errTrace = errTrace;
	}

	@SuppressWarnings("unchecked")
	public void testGetLogLevels() {
		I_Map props = new MapWrapper(new HashMap());
		props.put("defaultlog", "WARN");
		props.put(SimpleLogTests.class.getName(), "INFO");
		
		short level = SimpleLog.getLogLevel(props, SimpleLogTests.class.getName());
		assertEquals(DeferredLog.LOG_LEVEL_INFO, level);
		
		level = SimpleLog.getLogLevel(props, DeferredLogTests.class.getName());
		assertEquals(DeferredLog.LOG_LEVEL_WARN, level);
		
		props.put("defaultlog", "DEBUG");
		level = SimpleLog.getLogLevel(props, SimpleLogTests.class.getName());
		assertEquals(DeferredLog.LOG_LEVEL_INFO, level);
		
		level = SimpleLog.getLogLevel(props, DeferredLogTests.class.getName());
		assertEquals(DeferredLog.LOG_LEVEL_DEBUG, level);
		
		props.put("defaultlog", "WARN");
		props.put("org.adligo.i.log", "DEBUG");
		
		level = SimpleLog.getLogLevel(props, DeferredLogTests.class.getName());
		assertEquals(DeferredLog.LOG_LEVEL_DEBUG, level);
		
		props.remove("org.adligo.i.log");
		level = SimpleLog.getLogLevel(props, DeferredLogTests.class.getName());
		assertEquals(DeferredLog.LOG_LEVEL_WARN, level);
		
		props.put("org.adligo.i", "DEBUG");
		level = SimpleLog.getLogLevel(props, DeferredLogTests.class.getName());
		assertEquals(DeferredLog.LOG_LEVEL_DEBUG, level);
		
		props.put("org.adligo.i.log", "WARN");
		level = SimpleLog.getLogLevel(props, DeferredLogTests.class.getName());
		assertEquals(DeferredLog.LOG_LEVEL_WARN, level);
		
		props.put("org.adligo.i.log", "TRACE");
		level = SimpleLog.getLogLevel(props, DeferredLogTests.class.getName());
		assertEquals(DeferredLog.LOG_LEVEL_TRACE, level);
		
	}

	@Override
	public void err(String p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exception(Throwable x) {
		// TODO Auto-generated method stub
		
	}

}
