package org.adligo.i.log;

import java.util.HashSet;
import java.util.Set;

import org.adligo.i.log.gwt.client.LogEntryPoint;
import org.adligo.i.util.GwtCompileUsedAllClassesAsserter;
import org.adligo.i.util.client.ClassUsageView;
import org.adligo.i.util.client.ClassUtils;
import org.adligo.i.util.client.I_UsageHolder;
import org.adligo.i.util.client.UtilEntryPoint;
import org.adligo.tests.ATest;

public class LogGwtCompileUsedAllClassesTest extends ATest {

	
	public void testAllClassesUsed() throws Exception {
		
		Set<String> ignore = new HashSet<String>();
		String utilEntryPointClassName = ClassUtils.getClassName(UtilEntryPoint.class);
		ignore.add(ClassUtils.getClassName(ClassUsageView.class));
		ignore.add(ClassUtils.getClassName(I_UsageHolder.class));
		ignore.add(utilEntryPointClassName);
		
		Set<Class<?>> classes = GwtCompileUsedAllClassesAsserter.getClasses(
				"org.adligo.i.log.client", ignore);
		assertEquals("there should be classes in org.adligo.i.log.client ", 22, classes.size());
		
		GwtCompileUsedAllClassesAsserter holder = new GwtCompileUsedAllClassesAsserter();
		LogEntryPoint entryPoint = new LogEntryPoint(holder);
		entryPoint.onModuleLoad();
		
		
		assertCollectionEquals(classes, holder.getUsedClasses());
	}
}

