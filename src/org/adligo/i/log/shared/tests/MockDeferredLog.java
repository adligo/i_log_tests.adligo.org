package org.adligo.i.log.shared.tests;

import org.adligo.i.log.shared.DeferredLog;
import org.adligo.i.util.shared.I_Collection;

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
