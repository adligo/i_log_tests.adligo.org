package org.adligo.i.log;

import org.adligo.i.log.client.Log;
import org.adligo.i.log.client.LogFactory;
import org.adligo.tests.ATest;

public class SameLogFetchRequest extends ATest {

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
