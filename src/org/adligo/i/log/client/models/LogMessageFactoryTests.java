package org.adligo.i.log.client.models;

import org.adligo.tests.ATest;

public class LogMessageFactoryTests extends ATest {

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
