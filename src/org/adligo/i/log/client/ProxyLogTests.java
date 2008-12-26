package org.adligo.i.log.client;

import java.util.HashMap;

import org.adligo.i.util.client.I_Map;
import org.adligo.j2se.util.MapWrapper;

import junit.framework.TestCase;

public class ProxyLogTests extends TestCase {
	Exception x = new Exception("PEx");
	LogTestDelegate example = new LogTestDelegate();
	LogTestDelegate delegate = new LogTestDelegate();
	
	public void testLogProxyNPEs() {
		ProxyLog log = new ProxyLog(this.getClass());
		log.trace("foo");
		log.trace("foo", x);
		log.debug("foo");
		log.debug("foo", x);
		log.info("foo");
		log.info("foo", x);
		log.warn("foo");
		log.warn("foo", x);
		log.error("foo");
		log.error("foo", x);
		log.fatal("foo");
		log.fatal("foo", x);
	}
	
	public void testSingleProxy() {
		
		
		ProxyLog log = new ProxyLog(this.getClass());
		log.addDelegate(delegate);
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
		
	}
	
	
	public void assertFatal(ProxyLog log) {
		setupNextBlock();
		log.trace("hey");
		assertOutput();
		log.trace("hey", x);
		assertOutput();
		
		setupNextBlock();
		log.debug("hey");
		assertOutput();
		log.debug("hey", x);
		assertOutput();
		
		setupNextBlock();
		log.info("hey");
		assertOutput();
		log.info("hey", x);
		assertOutput();
		
		setupNextBlock();
		log.warn("hey");
		assertOutput();
		log.warn("hey", x);
		assertOutput();
		
		setupNextBlock();
		log.error("hey");
		assertOutput();
		log.error("hey", x);
		assertOutput();
		
		setupNextMessage(I_LogDelegate.LOG_LEVEL_FATAL,"hey",x);
		log.fatal("hey", x);
		assertOutput();
		setupNextMessage(I_LogDelegate.LOG_LEVEL_FATAL,"hey", null);
		log.fatal("hey");
		assertOutput();
		
	}

	public void assertError(ProxyLog log) {
		
		setupNextBlock();
		log.trace("hey");
		assertOutput();
		
		setupNextBlock();
		log.debug("hey");
		assertOutput();
		
		setupNextBlock();
		log.info("hey");
		assertOutput();
		
		setupNextBlock();
		log.warn("hey");
		assertOutput();
		
		setupNextMessage(I_LogDelegate.LOG_LEVEL_ERROR,"hey", null);
		log.error("hey");
		assertOutput();
		setupNextMessage(I_LogDelegate.LOG_LEVEL_ERROR,"hey", x);
		log.error("hey", x);
		assertOutput();
		
		setupNextMessage(I_LogDelegate.LOG_LEVEL_FATAL,"hey", null);
		log.fatal("hey");
		assertOutput();
		setupNextMessage(I_LogDelegate.LOG_LEVEL_FATAL,"hey", x);
		log.fatal("hey", x);
		assertOutput();
	}
	
	public void assertWarn(ProxyLog log) {
		setupNextBlock();
		log.trace("hey");
		assertOutput();
		
		setupNextBlock();
		log.debug("hey");
		assertOutput();
		
		setupNextBlock();
		log.info("hey");
		assertOutput();
		
		setupNextMessage(I_LogDelegate.LOG_LEVEL_WARN,"hey", null);
		log.warn("hey");
		assertOutput();
		setupNextMessage(I_LogDelegate.LOG_LEVEL_WARN,"hey", x);
		log.warn("hey", x);
		assertOutput();
		
		setupNextMessage(I_LogDelegate.LOG_LEVEL_ERROR,"hey", null);
		log.error("hey");
		assertOutput();
		setupNextMessage(I_LogDelegate.LOG_LEVEL_ERROR,"hey", x);
		log.error("hey", x);
		assertOutput();
		
		setupNextMessage(I_LogDelegate.LOG_LEVEL_FATAL,"hey", null);
		log.fatal("hey");
		assertOutput();
		setupNextMessage(I_LogDelegate.LOG_LEVEL_FATAL,"hey", x);
		log.fatal("hey", x);
		assertOutput();
	}
	
	public void assertInfo(ProxyLog log) {
		setupNextBlock();
		log.trace("hey");
		assertOutput();
		
		setupNextBlock();
		log.debug("hey");
		assertOutput();
		
		setupNextMessage(I_LogDelegate.LOG_LEVEL_INFO,"hey", null);
		log.info("hey");
		assertOutput();
		setupNextMessage(I_LogDelegate.LOG_LEVEL_INFO,"hey", x);
		log.info("hey", x);
		assertOutput();
		
		setupNextMessage(I_LogDelegate.LOG_LEVEL_WARN,"hey", null);
		log.warn("hey");
		assertOutput();
		setupNextMessage(I_LogDelegate.LOG_LEVEL_WARN,"hey", x);
		log.warn("hey", x);
		assertOutput();
		
		setupNextMessage(I_LogDelegate.LOG_LEVEL_ERROR,"hey", null);
		log.error("hey");
		assertOutput();
		setupNextMessage(I_LogDelegate.LOG_LEVEL_ERROR,"hey", x);
		log.error("hey", x);
		assertOutput();
		
		setupNextMessage(I_LogDelegate.LOG_LEVEL_FATAL,"hey", null);
		log.fatal("hey");
		assertOutput();
		setupNextMessage(I_LogDelegate.LOG_LEVEL_FATAL,"hey", x);
		log.fatal("hey", x);
		assertOutput();
	}
	
	public void assertDebug(ProxyLog log) {
		setupNextBlock();
		log.trace("hey");
		assertOutput();
		
		setupNextMessage(I_LogDelegate.LOG_LEVEL_DEBUG,"hey", null);
		log.debug("hey");
		assertOutput();
		setupNextMessage(I_LogDelegate.LOG_LEVEL_DEBUG,"hey", x);
		log.debug("hey", x);
		assertOutput();
		
		setupNextMessage(I_LogDelegate.LOG_LEVEL_INFO,"hey", null);
		log.info("hey");
		assertOutput();
		setupNextMessage(I_LogDelegate.LOG_LEVEL_INFO,"hey", x);
		log.info("hey", x);
		assertOutput();
		
		setupNextMessage(I_LogDelegate.LOG_LEVEL_WARN,"hey", null);
		log.warn("hey");
		assertOutput();
		setupNextMessage(I_LogDelegate.LOG_LEVEL_WARN,"hey", x);
		log.warn("hey", x);
		assertOutput();
		
		setupNextMessage(I_LogDelegate.LOG_LEVEL_ERROR,"hey", null);
		log.error("hey");
		assertOutput();
		setupNextMessage(I_LogDelegate.LOG_LEVEL_ERROR,"hey", x);
		log.error("hey", x);
		assertOutput();
		
		setupNextMessage(I_LogDelegate.LOG_LEVEL_FATAL,"hey", null);
		log.fatal("hey");
		assertOutput();
		setupNextMessage(I_LogDelegate.LOG_LEVEL_FATAL,"hey", x);
		log.fatal("hey", x);
		assertOutput();
	}
	
	
	public void assertTrace(ProxyLog log) {
		setupNextMessage(I_LogDelegate.LOG_LEVEL_TRACE,"hey", null);
		log.trace("hey");
		assertOutput();
		setupNextMessage(I_LogDelegate.LOG_LEVEL_TRACE,"hey", x);
		log.trace("hey", x);
		assertOutput();
		
		setupNextMessage(I_LogDelegate.LOG_LEVEL_DEBUG,"hey", null);
		log.debug("hey");
		assertOutput();
		setupNextMessage(I_LogDelegate.LOG_LEVEL_DEBUG,"hey", x);
		log.debug("hey", x);
		assertOutput();
		
		setupNextMessage(I_LogDelegate.LOG_LEVEL_INFO,"hey", null);
		log.info("hey");
		assertOutput();
		setupNextMessage(I_LogDelegate.LOG_LEVEL_INFO,"hey", x);
		log.info("hey", x);
		assertOutput();
		
		setupNextMessage(I_LogDelegate.LOG_LEVEL_WARN,"hey", null);
		log.warn("hey");
		assertOutput();
		setupNextMessage(I_LogDelegate.LOG_LEVEL_WARN,"hey", x);
		log.warn("hey", x);
		assertOutput();
		
		setupNextMessage(I_LogDelegate.LOG_LEVEL_ERROR,"hey", null);
		log.error("hey");
		assertOutput();
		setupNextMessage(I_LogDelegate.LOG_LEVEL_ERROR,"hey", x);
		log.error("hey", x);
		assertOutput();
		
		setupNextMessage(I_LogDelegate.LOG_LEVEL_FATAL,"hey", null);
		log.fatal("hey");
		assertOutput();
		setupNextMessage(I_LogDelegate.LOG_LEVEL_FATAL,"hey", x);
		log.fatal("hey", x);
		assertOutput();
	}
	
	public void assertOutput() {
		assertEquals("should match",example, delegate);
	}
	
	public void setupNextBlock() {
		example.log(-10, null, null);
		delegate.log(-10, null, null);
	}
	
	public void setupNextMessage(int level, String p, Throwable t) {
		example.log(level, p, t);
	}
	
	public void setTestLog(ProxyLog log, I_Map map, String level) {
		map.put(this.getClass().getName(), level);
		log.setLogLevel(map);
	}
	
	public void setDefaultLog(ProxyLog log, I_Map map, String level) {
		map.put("defaultlog", level);
		log.setLogLevel(map);
	}
}
