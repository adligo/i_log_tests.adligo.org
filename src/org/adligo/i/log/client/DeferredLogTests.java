package org.adligo.i.log.client;

import java.util.HashMap;

import org.adligo.i.util.client.I_Collection;
import org.adligo.j2se.util.J2SEPlatform;
import org.adligo.j2se.util.MapWrapper;
import org.adligo.tests.ATest;

public class DeferredLogTests extends ATest implements I_LogOutput {

	private int counter = 0;
	DeferredLog log = new DeferredLog(DeferredLogTests.class);
	Exception x = new Exception("DEx");
	LogMockDelegate example = new LogMockDelegate();
	LogMockDelegate delegate = new LogMockDelegate();
	
	public void setUp() throws Exception {
		J2SEPlatform.init();
	}
	@SuppressWarnings("unchecked")
	public void testPrePostInitalizationState() throws Exception {
		log = new DeferredLog(DeferredLogTests.class);
		String message = "before the deferred log has " +
				"a delegate all log level queries should return true!";
		
		assertTrue(message, log.isTraceEnabled());
		assertTrue(message, log.isDebugEnabled());
		assertTrue(message, log.isInfoEnabled());
		assertTrue(message, log.isWarnEnabled());
		assertTrue(message, log.isErrorEnabled());
		assertTrue(message, log.isFatalEnabled());
		
		SimpleLog slog = new SimpleLog("", new MapWrapper(new HashMap()));
		log.addDelegate(slog);
		
		message = "after the deferred log has " +
			"a delegate all log level queries should return " +
			"the level!";
		
		log.setLevel(I_LogDelegate.LOG_LEVEL_TRACE);
		assertTrue(message, log.isTraceEnabled());
		assertTrue(message, log.isDebugEnabled());
		assertTrue(message, log.isInfoEnabled());
		assertTrue(message, log.isWarnEnabled());
		assertTrue(message, log.isErrorEnabled());
		assertTrue(message, log.isFatalEnabled());
		
		log.setLevel(I_LogDelegate.LOG_LEVEL_DEBUG);
		assertFalse(message, log.isTraceEnabled());
		assertTrue(message, log.isDebugEnabled());
		assertTrue(message, log.isInfoEnabled());
		assertTrue(message, log.isWarnEnabled());
		assertTrue(message, log.isErrorEnabled());
		assertTrue(message, log.isFatalEnabled());
		
		log.setLevel(I_LogDelegate.LOG_LEVEL_INFO);
		assertFalse(message, log.isTraceEnabled());
		assertFalse(message, log.isDebugEnabled());
		assertTrue(message, log.isInfoEnabled());
		assertTrue(message, log.isWarnEnabled());
		assertTrue(message, log.isErrorEnabled());
		assertTrue(message, log.isFatalEnabled());
		
		log.setLevel(I_LogDelegate.LOG_LEVEL_WARN);
		assertFalse(message, log.isTraceEnabled());
		assertFalse(message, log.isDebugEnabled());
		assertFalse(message, log.isInfoEnabled());
		assertTrue(message, log.isWarnEnabled());
		assertTrue(message, log.isErrorEnabled());
		assertTrue(message, log.isFatalEnabled());
		
		log.setLevel(I_LogDelegate.LOG_LEVEL_ERROR);
		assertFalse(message, log.isTraceEnabled());
		assertFalse(message, log.isDebugEnabled());
		assertFalse(message, log.isInfoEnabled());
		assertFalse(message, log.isWarnEnabled());
		assertTrue(message, log.isErrorEnabled());
		assertTrue(message, log.isFatalEnabled());
		
		log.setLevel(I_LogDelegate.LOG_LEVEL_FATAL);
		assertFalse(message, log.isTraceEnabled());
		assertFalse(message, log.isDebugEnabled());
		assertFalse(message, log.isInfoEnabled());
		assertFalse(message, log.isWarnEnabled());
		assertFalse(message, log.isErrorEnabled());
		assertTrue(message, log.isFatalEnabled());
	}
	
	@SuppressWarnings("unchecked")
	public void testDeferrment() throws Exception {
		DeferredLog.deferredMessages.clear();
		
		logMessages();
		I_Collection messages = DeferredLog.deferredMessages;
		assertEquals("should be this many messages", 12, messages.size());
		
		SimpleLog sl = new SimpleLog("", new MapWrapper(new HashMap()));
		SimpleLog.setOut(this);
		log.addDelegate(sl);
		log.setLevel(I_LogDelegate.LOG_LEVEL_TRACE);
		logMessages();
		assertEquals("should have logged messages", 12, counter);
		
		counter = 2;
		log.setLevel(I_LogDelegate.LOG_LEVEL_DEBUG);
		logMessages();
		assertEquals("should have logged messages", 12, counter);
		
		counter = 4;
		log.setLevel(I_LogDelegate.LOG_LEVEL_INFO);
		logMessages();
		assertEquals("should have logged messages", 12, counter);
		
		counter = 6;
		log.setLevel(I_LogDelegate.LOG_LEVEL_WARN);
		logMessages();
		assertEquals("should have logged messages", 12, counter);
		
		counter = 8;
		log.setLevel(I_LogDelegate.LOG_LEVEL_ERROR);
		logMessages();
		assertEquals("should have logged messages", 12, counter);
		
		counter = 10;
		log.setLevel(I_LogDelegate.LOG_LEVEL_FATAL);
		logMessages();
		assertEquals("should have logged messages", 12, counter);
		//setting of log levels via map is tested in ProxyLogTests
		
	}
	
	public void logMessages() throws Exception {
		log.trace("traceS");
		log.trace("traceX", new Exception("trace"));
		log.debug("debugS");
		log.debug("debugX", new Exception("debug"));
		log.info("infoS");
		log.info("infoX", new Exception("info"));
		log.warn("warnS");
		log.warn("warnX", new Exception("warn"));
		log.error("errorS");
		log.error("errorX", new Exception("error"));
		log.fatal("fatalS");
		log.fatal("fatalX", new Exception("fatal"));
	}
	
	public void write(String p) {
		switch (counter) {
			case 0:
				assertTrue("should have message with traceS",
						p.indexOf("traceS") != -1);
				break;
			case 1:
				assertTrue("should have message with traceX",
						p.indexOf("traceX") != -1);
				break;
			case 2:
				assertTrue("should have message with debugS",
						p.indexOf("debugS") != -1);
				break;
			case 3:
				assertTrue("should have message with debugX",
						p.indexOf("debugX") != -1);
				break;
			case 4:
				assertTrue("should have message with infoS",
						p.indexOf("infoS") != -1);
				break;
			case 5:
				assertTrue("should have message with infoX",
						p.indexOf("infoX") != -1);
				break;
			case 6:
				assertTrue("should have message with warnS",
						p.indexOf("warnS") != -1);
				break;
			case 7:
				assertTrue("should have message with warnX",
						p.indexOf("warnX") != -1);
				break;
			case 8:
				assertTrue("should have message with errorS",
						p.indexOf("errorS") != -1);
				break;
			case 9:
				assertTrue("should have message with errorX",
						p.indexOf("errorX") != -1);
				break;
			case 10:
				assertTrue("should have message with fatalS",
						p.indexOf("fatalS") != -1);
				break;
			case 11:
				assertTrue("should have message with fatalX",
						p.indexOf("fatalX") != -1);
				break;
		}
		counter++;
	}
}
