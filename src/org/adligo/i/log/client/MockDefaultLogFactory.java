package org.adligo.i.log.client;

import org.adligo.i.util.client.HashCollection;

public class MockDefaultLogFactory extends LogFactoryMemory {

	public static void uninit() {
		DefaultLogFactory.memory.preInitLoggers = new HashCollection();
		DefaultLogFactory.memory.loggers = null;
		DefaultLogFactory.memory.firstCallToSetInitalLogLevels = true;
	}
	
	public static void uninit(HashCollection p_preInitLoggers) {
		HashCollection toSet = new HashCollection();
		for (int i = 0; i < p_preInitLoggers.size(); i++) {
			DeferredLog dl = (DeferredLog) p_preInitLoggers.get(i);
			toSet.add(dl);
		}
		DefaultLogFactory.memory.preInitLoggers = toSet;
		DefaultLogFactory.memory.loggers = null;
		DefaultLogFactory.memory.firstCallToSetInitalLogLevels = true;
	}
}
