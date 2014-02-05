package org.adligo.i.log.client;

import org.adligo.i.log.client.DefaultLogFactory;
import org.adligo.i.log.client.DeferredLog;
import org.adligo.i.log.client.LogFactoryMemory;
import org.adligo.i.log.client.LogFactoryMemorySnapshot;
import org.adligo.i.util.client.HashCollection;
import org.adligo.i.util.client.I_Map;
import org.adligo.i.util.client.SynchronizedHashCollection;

/**
 * this class overwrites the DefaultLogFactory memory
 * for tests of pre initialization log message state
 * 
 * @author scott
 *
 */
public class MockDefaultLogFactory extends LogFactoryMemory {

	public static void uninit() {
		DefaultLogFactory.memory.preInitLoggers = 
					new SynchronizedHashCollection(new HashCollection());
		DefaultLogFactory.memory.loggers = null;
		DefaultLogFactory.memory.firstCallToSetInitalLogLevels = true;
	}
	
	public static void uninit(HashCollection p_preInitLoggers) {
		HashCollection toSet = new HashCollection();
		for (int i = 0; i < p_preInitLoggers.size(); i++) {
			DeferredLog dl = (DeferredLog) p_preInitLoggers.get(i);
			toSet.add(dl);
		}
		DefaultLogFactory.memory.preInitLoggers = 
					new SynchronizedHashCollection(p_preInitLoggers);
		DefaultLogFactory.memory.loggers = null;
		DefaultLogFactory.memory.firstCallToSetInitalLogLevels = true;
	}
}

