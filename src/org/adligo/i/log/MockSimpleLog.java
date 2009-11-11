package org.adligo.i.log;

import org.adligo.i.log.client.I_LogOutput;
import org.adligo.i.log.client.SimpleLog;
import org.adligo.i.util.client.I_Map;

public class MockSimpleLog extends SimpleLog {

	public MockSimpleLog(String name, I_Map props) {
		super(name, props);
	}
	
	protected static I_LogOutput getOut() {
		return out;
	}

	protected static void setOut(I_LogOutput out) {
		SimpleLog.out = out;
	}


}
