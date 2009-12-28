package org.adligo.i.log;

import org.adligo.i.log.client.DeferredLog;
import org.adligo.i.log.client.LogLookup;

import junit.framework.TestCase;

public class LogLookupTests extends TestCase {

	public void testEqualsHashCodeLookup() {
		
		LogLookup ll = new LogLookup("loglookup");
		
		DeferredLog dl = new DeferredLog("loglookup");
		
		assertEquals(ll.hashCode(), dl.hashCode());
		assertTrue(ll.equals(dl));
		assertTrue(dl.equals(ll));
	}
}
