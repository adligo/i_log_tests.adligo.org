package org.adligo.i.log.tests;

import org.adligo.i.util.client.I_Event;
import org.adligo.i.util.client.I_Factory;
import org.adligo.i.util.client.I_Listener;
import org.adligo.i.util.client.ListenerValueObject;
import org.adligo.i.util.client.PropertyFactory;

/**
 * this class simulates a remote call to obtain 
 * the log configuration file (which is what happens on gwt)
 * in the mean time the log platform is in limbo (ie pre init)
 * or in otherwords 
 * LogPlatform.init was called
 *    but the property file with the settings is deferred untill
 *    the file shows up from the server
 * @author scott
 *
 */
public class MockDeferredPropertyFactory extends PropertyFactory implements I_Factory {
	private I_Listener lastListener;
	
	@Override
	public Object createNew(Object p) {
		ListenerValueObject list = (ListenerValueObject) p;
		lastListener = list.getListener();
		return null;
	}

	public void sendEvent(I_Event p) {
		lastListener.onEvent(p);
	}
	
	public void init() throws Exception {
		super.init(this);
	}
}
