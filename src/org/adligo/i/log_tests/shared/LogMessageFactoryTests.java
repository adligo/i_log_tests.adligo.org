package org.adligo.i.log_tests.shared;

import junit.framework.TestCase;

import org.adligo.i.log.shared.models.LogMessage;
import org.adligo.i.log.shared.models.LogMessageFactory;
import org.adligo.i.log.shared.models.ObjectLogMessage;
import org.adligo.i.log.shared.models.StringLogMessage;

public class LogMessageFactoryTests extends TestCase {

	public void testLogFactory() {
		LogMessage message = LogMessageFactory.createMessage(null);
		assertNotNull(message);
		assertTrue(message instanceof StringLogMessage);
		assertEquals("", message.getMessage());
		
		message = LogMessageFactory.createMessage("hey");
		assertNotNull(message);
		assertTrue(message instanceof StringLogMessage);
		assertEquals("hey", message.getMessage());
		
		Object o = new Object();
		message = LogMessageFactory.createMessage(o);
		assertNotNull(message);
		assertTrue(message instanceof ObjectLogMessage);
		assertEquals(o, message.getMessage());
	}
}
