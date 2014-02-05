package org.adligo.i.log.tests;

import junit.framework.TestCase;

import org.adligo.i.log.client.DefaultLogFactory;

public class DefaultLogFactoryTests extends TestCase {

	static {
		JUnitNEclipseErrorMessage.init();
	}
	
	/**
	 * note this is mostly to test a optimization time
	 * which takes this from almost 9 seconds 
	 * down to 0.156  - 0.174 
	 * on a 64 bit 2.4 Ghz processor
	 * for a system with 20,000 classes that use logging
	 * 
	 */
	public void testObtainingLoggers() {
		DefaultLogFactory dlf = new DefaultLogFactory();
		
		for (int i = 0; i < 20000; i++) {
			dlf.getLog("" + i);
		}
		
	}
}
