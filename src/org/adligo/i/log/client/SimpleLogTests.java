package org.adligo.i.log.client;

import java.util.HashMap;

import junit.framework.TestCase;

import org.adligo.i.util.client.I_Map;
import org.adligo.j2se.util.MapWrapper;

public class SimpleLogTests extends TestCase implements I_LogOutput {
	private String currentLog = "";
	private String newMessage = "";
	
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

	private void setTestLog(SimpleLog log, I_Map map, String level) {
		map.put("TestLog", level);
		log.setLogLevel(map);
	}
	
	private void setDefaultLog(SimpleLog log, I_Map map, String level) {
		map.put("defaultlog", level);
		log.setLogLevel(map);
	}

	private void assertFatal(SimpleLog log) {
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
		
		setupNextBlock();
		log.error("hey");
		assertOutputNotCalled();
		
		setupNextMessage("[FATAL] TestLog - hey");
		log.fatal("hey");
		assertOutputCalled();
	}

	private void assertError(SimpleLog log) {
		
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
		
		setupNextMessage("[FATAL] TestLog - hey");
		log.fatal("hey");
		assertOutputCalled();
	}
	
	private void assertWarn(SimpleLog log) {
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
		
		setupNextMessage("[ERROR] TestLog - hey");
		log.error("hey");
		assertOutputCalled();
		
		setupNextMessage("[FATAL] TestLog - hey");
		log.fatal("hey");
		assertOutputCalled();
	}
	
	private void assertInfo(SimpleLog log) {
		setupNextBlock();
		log.trace("hey");
		assertOutputNotCalled();
		
		setupNextBlock();
		log.debug("hey");
		assertOutputNotCalled();
		
		setupNextMessage("[INFO] TestLog - hey");
		log.info("hey");
		assertOutputCalled();
		
		setupNextMessage("[WARN] TestLog - hey");
		log.warn("hey");
		assertOutputCalled();
		
		setupNextMessage("[ERROR] TestLog - hey");
		log.error("hey");
		assertOutputCalled();
		
		setupNextMessage("[FATAL] TestLog - hey");
		log.fatal("hey");
		assertOutputCalled();
	}
	
	private void assertDebug(SimpleLog log) {
		setupNextBlock();
		log.trace("hey");
		assertOutputNotCalled();
		
		setupNextMessage("[DEBUG] TestLog - hey");
		log.debug("hey");
		assertOutputCalled();
		
		setupNextMessage("[INFO] TestLog - hey");
		log.info("hey");
		assertOutputCalled();
		
		setupNextMessage("[WARN] TestLog - hey");
		log.warn("hey");
		assertOutputCalled();
		
		setupNextMessage("[ERROR] TestLog - hey");
		log.error("hey");
		assertOutputCalled();
		
		setupNextMessage("[FATAL] TestLog - hey");
		log.fatal("hey");
		assertOutputCalled();
	}
	
	
	private void assertTrace(SimpleLog log) {
		setupNextMessage("[TRACE] TestLog - hey");
		log.trace("hey");
		assertOutputCalled();
		
		setupNextMessage("[DEBUG] TestLog - hey");
		log.debug("hey");
		assertOutputCalled();
		
		setupNextMessage("[INFO] TestLog - hey");
		log.info("hey");
		assertOutputCalled();
		
		setupNextMessage("[WARN] TestLog - hey");
		log.warn("hey");
		assertOutputCalled();
		
		setupNextMessage("[ERROR] TestLog - hey");
		log.error("hey");
		assertOutputCalled();
		
		setupNextMessage("[FATAL] TestLog - hey");
		log.fatal("hey");
		assertOutputCalled();
	}
	
	private void assertOutputCalled() {
		assertTrue("Should have logged something", !"".equals(newMessage));
	}

	private void assertOutputNotCalled() {
		assertTrue("Should NOT have logged something", "".equals(newMessage));
	}
	
	private void setupNextBlock() {
		newMessage = "";
		currentLog = "";
	}
	
	private void setupNextMessage(String p) {
		newMessage = "";
		currentLog = p;
	}
}
