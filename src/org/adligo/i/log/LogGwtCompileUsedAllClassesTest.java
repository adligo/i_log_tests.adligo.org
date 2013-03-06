package org.adligo.i.log;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

import org.adligo.i.log.client.MockDefaultLogFactory;
import org.adligo.i.log.gwt.client.LogEntryPoint;
import org.adligo.i.util.GwtCompileUsedAllClassesAsserter;
import org.adligo.i.util.client.ClassUsageView;
import org.adligo.i.util.client.ClassUtils;
import org.adligo.i.util.client.I_UsageHolder;
import org.adligo.i.util.client.UtilEntryPoint;
import org.adligo.jse.util.JSEPlatform;
import org.adligo.tests.ATest;

public class LogGwtCompileUsedAllClassesTest extends ATest {

	static {
		try {
			JSEPlatform.init();
		} catch (Exception x) {
			x.printStackTrace();
		}
	}
	
	public void testAllClassesUsed() throws Exception {
		
		
		Set<String> ignore = new HashSet<String>();
		String utilEntryPointClassName = ClassUtils.getClassName(LogEntryPoint.class);
		ignore.add(ClassUtils.getClassName(ClassUsageView.class));
		ignore.add(ClassUtils.getClassName(I_UsageHolder.class));
		ignore.add(ClassUtils.getClassName(MockDefaultLogFactory.class));
		ignore.add(utilEntryPointClassName);
		
		Set<Class<?>> classes = GwtCompileUsedAllClassesAsserter.getClasses(
				"org.adligo.i.log.client", ignore);
		assertEquals("there should be classes in org.adligo.i.log.client ", 25, classes.size());
		
		GwtCompileUsedAllClassesAsserter holder = new GwtCompileUsedAllClassesAsserter();
		LogEntryPoint entryPoint = new LogEntryPoint(holder);
		entryPoint.onModuleLoad();
		
		
		assertCollectionEquals(classes, holder.getUsedClasses());
	}
	
}

