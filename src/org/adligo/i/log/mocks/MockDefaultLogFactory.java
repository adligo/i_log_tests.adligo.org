package org.adligo.i.log.mocks;

import org.adligo.i.log.client.DefaultLogFactory;
import org.adligo.i.util.client.HashCollection;

public class MockDefaultLogFactory extends DefaultLogFactory {

	public static void uninit() {
		DefaultLogFactory.preInitLoggers = new HashCollection();
		DefaultLogFactory.loggers = null;
		DefaultLogFactory.firstTime = true;
	}
	
	public static void uninit(HashCollection p_preInitLoggers) {
		DefaultLogFactory.preInitLoggers = p_preInitLoggers;
		DefaultLogFactory.loggers = null;
		DefaultLogFactory.firstTime = true;
	}
}
