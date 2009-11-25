package org.adligo.i.log.mocks;

import org.adligo.i.log.client.DefaultLogFactory;

public class MockDefaultLogFactory extends DefaultLogFactory {

	public static void uninit() {
		DefaultLogFactory.loggers = null;
		DefaultLogFactory.firstTime = true;
	}
}
