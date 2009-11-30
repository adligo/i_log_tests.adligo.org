package org.adligo.i.log.mocks;

import org.adligo.i.log.client.DefaultLogFactory;
import org.adligo.i.util.client.ArrayCollection;

public class MockDefaultLogFactory extends DefaultLogFactory {

	public static void uninit() {
		DefaultLogFactory.preInitLoggers = new ArrayCollection();
		DefaultLogFactory.loggers = null;
		DefaultLogFactory.firstTime = true;
	}
	
	public static void uninit(ArrayCollection p_preInitLoggers) {
		DefaultLogFactory.preInitLoggers = p_preInitLoggers;
		DefaultLogFactory.loggers = null;
		DefaultLogFactory.firstTime = true;
	}
}
