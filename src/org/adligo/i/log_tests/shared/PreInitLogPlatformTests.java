package org.adligo.i.log_tests.shared;

import junit.framework.TestCase;

import org.adligo.i.log.shared.Log;
import org.adligo.i.log.shared.LogFactory;
import org.adligo.i.log.shared.LogFactoryMemorySnapshot;
import org.adligo.i.log.shared.LogPlatform;
import org.adligo.i.util.shared.Event;
import org.adligo.i.util.shared.I_Map;
import org.adligo.i.util.shared.MapFactory;
import org.adligo.jse.util.JSEPlatform;

public class PreInitLogPlatformTests extends TestCase {

	public void testPreInit() throws Exception {
		MockDeferredPropertyFactory propFactory = new MockDeferredPropertyFactory();
		propFactory.init();
		JSEPlatform.init();
		
		
		LogPlatform.init("remote_log_file.properties");
		Log log = LogFactory.getLog(PreInitLogPlatformTests.class);
		log.debug("hey");
		
		LogFactoryMemorySnapshot snap = LogPlatform.getDefaultLogFactoryMemorySnapshot();
		assertEquals(1, snap.getPreInitLoggers().size());
		assertNull(snap.getLoggers());
		assertTrue(snap.isFirstCallToSetInitalLogLevels());
		
		Event e = new Event();
		e.setSource(PreInitLogPlatformTests.class.getName());
		I_Map map = MapFactory.create();
		map.put("defaultlog", "DEBUG");
		e.setValue(map);
		propFactory.sendEvent(e);
		
		snap = LogPlatform.getDefaultLogFactoryMemorySnapshot();
		assertEquals(1, snap.getPreInitLoggers().size());
		assertNotNull(snap.getLoggers());
		assertFalse(snap.isFirstCallToSetInitalLogLevels());
		
	}
}
