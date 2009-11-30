package org.adligo.i.log.mocks;

import org.adligo.i.log.client.LogPlatform;
import org.adligo.i.util.client.ArrayCollection;
import org.adligo.i.util.client.I_SystemOutput;

public class MockLogPlatform extends LogPlatform {

	public static void unInit() {
		LogPlatform.isInit = false;
		LogPlatform.isInitLevelsSet = false;
		MockDefaultLogFactory.uninit();
		MockDeferredLog.uninit();
	}

	public static void unInit(ArrayCollection preInitLoggers) {
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
