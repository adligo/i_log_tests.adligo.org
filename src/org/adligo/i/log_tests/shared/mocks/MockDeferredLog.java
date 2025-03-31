package org.adligo.i.log_tests.shared.mocks;

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
