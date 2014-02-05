package org.adligo.i.log.tests;

import org.adligo.i.log.client.LogPlatform;
import org.adligo.jse.util.JSEPlatform;

public class JUnitNEclipseErrorMessage {
	private static boolean init = false;
	
	public static void init() {
		if (!init) {
			try {
				JSEPlatform.init();
				LogPlatform.init();
			} catch (Exception x) {
				System.err.println("Note if you are seeing a a LogPlatform has already been initialized. message in eclipse");
				System.err.println("Run this project's test through ANT as it starts up a JVM for each test");
				System.err.println("This allows i_log to have bulletproof memory encapsulation");
				
				x.printStackTrace();
			}
			init = true;
		}
	}
}
