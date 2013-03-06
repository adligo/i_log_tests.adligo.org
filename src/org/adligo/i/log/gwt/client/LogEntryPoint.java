package org.adligo.i.log.gwt.client;

import org.adligo.i.log.client.DefaultLogFactory;
import org.adligo.i.log.client.DeferredLog;
import org.adligo.i.log.client.I_Formatter;
import org.adligo.i.log.client.I_Log;
import org.adligo.i.log.client.I_LogDelegate;
import org.adligo.i.log.client.I_LogDispatcher;
import org.adligo.i.log.client.I_LogFactory;
import org.adligo.i.log.client.I_LogMutant;
import org.adligo.i.log.client.I_ProxyLog;
import org.adligo.i.log.client.Log;
import org.adligo.i.log.client.LogFactory;
import org.adligo.i.log.client.LogFactoryMemory;
import org.adligo.i.log.client.LogLookup;
import org.adligo.i.log.client.LogPlatform;
import org.adligo.i.log.client.ProxyLog;
import org.adligo.i.log.client.SimpleFormatter;
import org.adligo.i.log.client.SimpleLog;
import org.adligo.i.log.client.SingleDispatchLog;
import org.adligo.i.log.client.models.FormatItem;
import org.adligo.i.log.client.models.I_LogMessage;
import org.adligo.i.log.client.models.LogMessage;
import org.adligo.i.log.client.models.LogMessageFactory;
import org.adligo.i.log.client.models.LogUrl;
import org.adligo.i.log.client.models.ObjectLogMessage;
import org.adligo.i.log.client.models.StringLogMessage;
import org.adligo.i.util.client.ClassUsageView;
import org.adligo.i.util.client.I_UsageHolder;
import org.adligo.i.util.client.MockMap;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class LogEntryPoint implements EntryPoint {
	I_UsageHolder holder;
	ClassUsageView view;
	
	
	public LogEntryPoint() {
		view = new ClassUsageView();
		holder = view;
	}
	public LogEntryPoint(I_UsageHolder p_holder) {
		holder = p_holder;
	}
	
	@Override
	public void onModuleLoad() {
		if (view != null) {
			// TODO Auto-generated method stub
			RootPanel.get().add(view);
		}
		
		
		holder.addUsed(DefaultLogFactory.class);
		holder.addUsed(new DeferredLog("hey"));
		holder.addUsed(I_Formatter.class);
		holder.addUsed(I_LogDelegate.class);
		holder.addUsed(I_LogDispatcher.class);
		holder.addUsed(I_LogFactory.class);
		holder.addUsed(I_LogMutant.class);
		holder.addUsed(I_ProxyLog.class);
		holder.addUsed(I_Log.class);
		holder.addUsed(Log.class);
		holder.addUsed(LogFactory.class);
		holder.addUsed(LogPlatform.class);
		holder.addUsed(new ProxyLog("you"));
		holder.addUsed(SimpleFormatter.class);
		holder.addUsed(new SimpleLog("guys", new MockMap()));
		holder.addUsed(new SingleDispatchLog("testing", new MockMap()));
		holder.addUsed(new LogLookup("hey"));
		holder.addUsed(new LogFactoryMemory());
		
		holder.addUsed(FormatItem.class);
		holder.addUsed(I_LogMessage.class);
		holder.addUsed(LogMessage.class);
		holder.addUsed(LogMessageFactory.class);
		holder.addUsed(LogUrl.class);
		holder.addUsed(ObjectLogMessage.class);
		holder.addUsed(StringLogMessage.class);
	}
	
}
