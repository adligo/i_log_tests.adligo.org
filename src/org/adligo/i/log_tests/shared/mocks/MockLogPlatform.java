package org.adligo.i.log_tests.shared.mocks;

import org.adligo.i.log.shared.LogPlatform;
import org.adligo.i.util.shared.HashCollection;
import org.adligo.i.util.shared.I_SystemOutput;

public class MockLogPlatform extends LogPlatform {

	public static void unInit() {
		LogPlatform.isInit = false;
		LogPlatform.isInitLevelsSet = false;
		MockDeferredLog.uninit();
	}

	public static void unInit(HashCollection preInitLoggers) {
		LogPlatform.isInit = false;
		LogPlatform.isInitLevelsSet = false;
		MockDeferredLog.uninit();
	}
	
	public static void setOutput(I_SystemOutput p) {
		LogPlatform.setOut(p);
	}
	
	public static LogPlatform getInstance() {
		return LogPlatform.INSTANCE;
	}
}
