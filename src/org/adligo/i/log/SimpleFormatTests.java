package org.adligo.i.log;

import org.adligo.i.log.client.I_LogDelegate;
import org.adligo.i.log.client.SimpleFormatter;
import org.adligo.i.log.client.models.FormatItem;
import org.adligo.i.log.client.models.StringLogMessage;
import org.adligo.i.util.client.I_Iterator;
import org.adligo.tests.ATest;

public class SimpleFormatTests extends ATest {

	
	public void testSimpleFormat() {
		MockSimpleFormatter sf = new MockSimpleFormatter();
		
		sf.setFormatString("%p [%t]: %c - %m");
		I_Iterator it = sf.getItems();
		for (int i = 0; it.hasNext(); i++) {
			FormatItem fi = (FormatItem) it.next();
			switch (i) {
				case 0:
					assertEquals(FormatItem.PRIORITY_TYPE, fi.getType());
					break;
				case 1:
					assertEquals(FormatItem.STRING_TYPE, fi.getType());
					assertEquals(" [", fi.getAsString());
					break;
				case 2:
					assertEquals(FormatItem.THREAD_TYPE, fi.getType());
					break;
				case 3:
					assertEquals(FormatItem.STRING_TYPE, fi.getType());
					assertEquals("]: ", fi.getAsString());
					break;
				case 4:
					assertEquals(FormatItem.CATEGORY_TYPE, fi.getType());
					assertEquals(0, fi.getCategory_precision());
					break;
				case 5:
					assertEquals(FormatItem.STRING_TYPE, fi.getType());
					assertEquals(" - ", fi.getAsString());
					break;
				case 6:
					assertEquals(FormatItem.MESSAGE_TYPE, fi.getType());
					break;
			}
			
		}
		StringLogMessage slm = new StringLogMessage();
		slm.setThread("main");
		slm.setLevel(I_LogDelegate.LOG_LEVEL_DEBUG);
		slm.setMessage("one big message");
		slm.setName("Foo");
		String out = sf.format(slm);
		
		assertEquals("DEBUG [main]: Foo - one big message", out);
	}
	
	public void testSimpleFormat2() {
		MockSimpleFormatter sf = new MockSimpleFormatter();
		
		sf.setFormatString("%p %c{1} - %m");
		I_Iterator it = sf.getItems();
		for (int i = 0; it.hasNext(); i++) {
			FormatItem fi = (FormatItem) it.next();
			switch (i) {
				case 0:
					assertEquals(FormatItem.PRIORITY_TYPE, fi.getType());
					break;
				case 1:
					assertEquals(FormatItem.STRING_TYPE, fi.getType());
					assertEquals(" ", fi.getAsString());
					break;
				case 2:
					assertEquals(FormatItem.CATEGORY_TYPE, fi.getType());
					assertEquals(1, fi.getCategory_precision());
					break;
				case 3:
					assertEquals(FormatItem.STRING_TYPE, fi.getType());
					assertEquals(" - ", fi.getAsString());
					break;
				case 4:
					assertEquals(FormatItem.MESSAGE_TYPE, fi.getType());
					break;
			}
			
		}
		StringLogMessage slm = new StringLogMessage();
		slm.setThread("main");
		slm.setLevel(I_LogDelegate.LOG_LEVEL_DEBUG);
		slm.setMessage("one big message");
		slm.setName("com.foo.bar.Foo");
		String out = sf.format(slm);
		
		assertEquals("DEBUG Foo - one big message", out);
	}
}
