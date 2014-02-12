package org.adligo.i.log.shared.tests;

import org.adligo.i.log.shared.SimpleLog;
import org.adligo.i.util.shared.I_Map;
import org.adligo.i.util.shared.I_SystemOutput;

public class MockSimpleLog extends SimpleLog {

	public MockSimpleLog(String name, I_Map props) {
		super(name, props);
	}
	

	protected static void setOut(I_SystemOutput out) {
		SimpleLog.out = out;
	}


}
