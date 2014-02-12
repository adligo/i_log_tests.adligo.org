package org.adligo.i.log.shared.tests.mocks;

import org.adligo.i.log.shared.DeferredLog;

public class MockDeferredLog extends DeferredLog {

	public MockDeferredLog(String clazz) {
		super(clazz);
		// TODO Auto-generated constructor stub
	}

	public static void uninit() {
		DeferredLog.deferredMessages.clear();
	}
}
