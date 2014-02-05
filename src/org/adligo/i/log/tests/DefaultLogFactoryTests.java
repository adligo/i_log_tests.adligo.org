package org.adligo.i.log.tests;

import org.adligo.i.log.client.DefaultLogFactory;
import org.adligo.i.log.client.LogPlatform;
import org.adligo.i.log.client.MockDefaultLogFactory;
import org.adligo.jse.util.JSEPlatform;

import junit.framework.TestCase;

public class DefaultLogFactoryTests extends TestCase {

	static {
		try {
			JSEPlatform.init();
			LogPlatform.init();
		} catch (Exception x) {
			x.printStackTrace();
		}
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
		MockDefaultLogFactory.uninit();
		DefaultLogFactory dlf = new DefaultLogFactory();
		
		for (int i = 0; i < 20000; i++) {
			dlf.getLog("" + i);
		}
		
	}
}
