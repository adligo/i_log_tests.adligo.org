package org.adligo.i.log.tests.mocks;

import org.adligo.i.log.client.LogPlatform;
import org.adligo.i.log.client.MockDefaultLogFactory;
import org.adligo.i.util.client.HashCollection;
import org.adligo.i.util.client.I_SystemOutput;

public class MockLogPlatform extends LogPlatform {

	public static void unInit() {
		LogPlatform.isInit = false;
		LogPlatform.isInitLevelsSet = false;
		MockDefaultLogFactory.uninit();
		MockDeferredLog.uninit();
	}

	public static void unInit(HashCollection preInitLoggers) {
		LogPlatform.isInit = false;
		LogPlatform.isInitLevelsSet = false;
		MockDefaultLogFactory.uninit(preInitLoggers);
		MockDeferredLog.uninit();
	}
	
	public static void setOutput(I_SystemOutput p) {
		LogPlatform.setOut(p);
	}
	
	public static LogPlatform getInstance() {
		return LogPlatform.instance;
	}
}
