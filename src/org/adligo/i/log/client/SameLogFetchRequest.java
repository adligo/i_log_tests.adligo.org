package org.adligo.i.log.client;

import junit.framework.TestCase;

public class SameLogFetchRequest extends TestCase {

	public void testLogFetching() {
		Log logRef1 = LogFactory.getLog(SameLogFetchRequest.class);
		Log logRef2 = LogFactory.getLog(SameLogFetchRequest.class);
		
		assertTrue("The references should point to the same " +
				"instance!", logRef1 == logRef2);
		
		
		logRef1 = LogFactory.getLog("scott_log");
		logRef2 = LogFactory.getLog("scott_log");
		
		assertTrue("The references should point to the same " +
				"instance!", logRef1 == logRef2);
	}
}
