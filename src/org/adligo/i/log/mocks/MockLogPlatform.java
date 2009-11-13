package org.adligo.i.log.mocks;

import org.adligo.i.log.client.LogPlatform;
import org.adligo.i.util.client.I_SystemOutput;

public class MockLogPlatform extends LogPlatform {

	public static void unInit() {
		LogPlatform.isInit = false;
		LogPlatform.isInitLevelsSet = false;
	}
	
	public static void setOutput(I_SystemOutput p) {
		LogPlatform.setOut(p);
	}
}
