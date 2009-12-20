package org.adligo.i.log;

import org.adligo.i.log.client.DefaultLogFactory;
import org.adligo.i.log.mocks.MockDefaultLogFactory;

import junit.framework.TestCase;

public class DefaultLogFactoryTests extends TestCase {

	/**
	 * note this is mostly to test a optimization time
	 * which takes this from almost 9 seconds 
	 * down to 0.187 - 0.266 
	 * on a 64 bit 2.4 Ghz processor
	 * for a system with 20,000 classes that use logging
	 * 
	 */
	public void testObtainingLoggers() {
		MockDefaultLogFactory.uninit();
		DefaultLogFactory dlf = new DefaultLogFactory();
		
		for (int i = 0; i < 20000; i++) {
			dlf.getLog("" + i);
		}
		
	}
}
