package org.adligo.i.log;

import org.adligo.i.log.client.DeferredLog;
import org.adligo.i.util.client.I_Collection;

public class MockDeferredLog extends DeferredLog {

	public MockDeferredLog(Class clazz) {
		super(clazz);
	}

	public MockDeferredLog(String name) {
		super(name);
	}
	
	public static I_Collection getDeferredMessages() {
		return DeferredLog.deferredMessages;
	}
}
