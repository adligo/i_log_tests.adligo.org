package org.adligo.i.log.client;

import java.util.HashMap;

import junit.framework.TestCase;

import org.adligo.i.util.client.I_Map;
import org.adligo.j2se.util.MapWrapper;

public class SimpleLogTests extends TestCase implements I_LogOutput {
	private String currentLog = "";
	private String newMessage = "";
	private String errTrace;
	private Exception x = null;
	
	public void setUp() {
		try {
			 Exception t = new Exception("Ex");
			 throw t;
		} catch (Exception y) {
			x = y;
		}
		
		StackTraceElement[] elements = x.getStackTrace();
		StringBuffer sb = new StringBuffer();
		sb.append(" <java.lang.Exception: Ex>\n");
		for (int i = 0; i < elements.length; i++) {
			sb.append("\t at ");
			sb.append(elements[i].toString());
			sb.append("\n");
		}
		errTrace = sb.toString();
	}
	
	@Override
	public void write(String p) {
		newMessage = p;
		assertEquals("Sould match", currentLog,p);
	}

	public void testOutputs() {
		SimpleLog.setOut(this);
		
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
		
		SimpleLog.setOut(new SystemErrOutput());
	}

	public void setTestLog(SimpleLog log, I_Map map, String level) {
		map.put("TestLog", level);
		log.setLogLevel(map);
	}
	
	public void setDefaultLog(SimpleLog log, I_Map map, String level) {
		map.put("defaultlog", level);
		log.setLogLevel(map);
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
		
		setupNextMessage("[FATAL] TestLog - hey" + errTrace);
		log.fatal("hey", x);
		assertOutputCalled();
		setupNextMessage("[FATAL] TestLog - hey");
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
		
		setupNextMessage("[ERROR] TestLog - hey");
		log.error("hey");
		assertOutputCalled();
		setupNextMessage("[ERROR] TestLog - hey" + errTrace);
		log.error("hey", x);
		assertOutputCalled();
		
		setupNextMessage("[FATAL] TestLog - hey");
		log.fatal("hey");
		assertOutputCalled();
		setupNextMessage("[FATAL] TestLog - hey" + errTrace);
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
		
		setupNextMessage("[WARN] TestLog - hey");
		log.warn("hey");
		assertOutputCalled();
		setupNextMessage("[WARN] TestLog - hey" + errTrace);
		log.warn("hey", x);
		assertOutputCalled();
		
		setupNextMessage("[ERROR] TestLog - hey");
		log.error("hey");
		assertOutputCalled();
		setupNextMessage("[ERROR] TestLog - hey" + errTrace);
		log.error("hey", x);
		assertOutputCalled();
		
		setupNextMessage("[FATAL] TestLog - hey");
		log.fatal("hey");
		assertOutputCalled();
		setupNextMessage("[FATAL] TestLog - hey" + errTrace);
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
		
		setupNextMessage("[INFO] TestLog - hey");
		log.info("hey");
		assertOutputCalled();
		setupNextMessage("[INFO] TestLog - hey" + errTrace);
		log.info("hey", x);
		assertOutputCalled();
		
		setupNextMessage("[WARN] TestLog - hey");
		log.warn("hey");
		assertOutputCalled();
		setupNextMessage("[WARN] TestLog - hey" + errTrace);
		log.warn("hey", x);
		assertOutputCalled();
		
		setupNextMessage("[ERROR] TestLog - hey");
		log.error("hey");
		assertOutputCalled();
		setupNextMessage("[ERROR] TestLog - hey" + errTrace);
		log.error("hey", x);
		assertOutputCalled();
		
		setupNextMessage("[FATAL] TestLog - hey");
		log.fatal("hey");
		assertOutputCalled();
		setupNextMessage("[FATAL] TestLog - hey" + errTrace);
		log.fatal("hey", x);
		assertOutputCalled();
	}
	
	public void assertDebug(SimpleLog log) {
		setupNextBlock();
		log.trace("hey");
		assertOutputNotCalled();
		
		setupNextMessage("[DEBUG] TestLog - hey");
		log.debug("hey");
		assertOutputCalled();
		setupNextMessage("[DEBUG] TestLog - hey" + errTrace);
		log.debug("hey", x);
		assertOutputCalled();
		
		setupNextMessage("[INFO] TestLog - hey");
		log.info("hey");
		assertOutputCalled();
		setupNextMessage("[INFO] TestLog - hey" + errTrace);
		log.info("hey", x);
		assertOutputCalled();
		
		setupNextMessage("[WARN] TestLog - hey");
		log.warn("hey");
		assertOutputCalled();
		setupNextMessage("[WARN] TestLog - hey" + errTrace);
		log.warn("hey", x);
		assertOutputCalled();
		
		setupNextMessage("[ERROR] TestLog - hey");
		log.error("hey");
		assertOutputCalled();
		setupNextMessage("[ERROR] TestLog - hey" + errTrace);
		log.error("hey", x);
		assertOutputCalled();
		
		setupNextMessage("[FATAL] TestLog - hey");
		log.fatal("hey");
		assertOutputCalled();
		setupNextMessage("[FATAL] TestLog - hey" + errTrace);
		log.fatal("hey", x);
		assertOutputCalled();
	}
	
	
	public void assertTrace(SimpleLog log) {
		setupNextMessage("[TRACE] TestLog - hey");
		log.trace("hey");
		assertOutputCalled();
		setupNextMessage("[TRACE] TestLog - hey" + errTrace);
		log.trace("hey", x);
		assertOutputCalled();
		
		setupNextMessage("[DEBUG] TestLog - hey");
		log.debug("hey");
		assertOutputCalled();
		setupNextMessage("[DEBUG] TestLog - hey" + errTrace);
		log.debug("hey", x);
		assertOutputCalled();
		
		setupNextMessage("[INFO] TestLog - hey");
		log.info("hey");
		assertOutputCalled();
		setupNextMessage("[INFO] TestLog - hey" + errTrace);
		log.info("hey", x);
		assertOutputCalled();
		
		setupNextMessage("[WARN] TestLog - hey");
		log.warn("hey");
		assertOutputCalled();
		setupNextMessage("[WARN] TestLog - hey" + errTrace);
		log.warn("hey", x);
		assertOutputCalled();
		
		setupNextMessage("[ERROR] TestLog - hey");
		log.error("hey");
		assertOutputCalled();
		setupNextMessage("[ERROR] TestLog - hey" + errTrace);
		log.error("hey", x);
		assertOutputCalled();
		
		setupNextMessage("[FATAL] TestLog - hey");
		log.fatal("hey");
		assertOutputCalled();
		setupNextMessage("[FATAL] TestLog - hey" + errTrace);
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
	}

}
