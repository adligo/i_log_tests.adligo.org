package org.adligo.i.log.client.models;

import org.adligo.i.log.client.models.LogUrl;
import org.adligo.tests.ATest;

public class LogUrlTests extends ATest {

	public void testCgi() {
		LogUrl url = new LogUrl("hey?", true);
		assertEquals("hey?",url.toString());
		assertEquals("",url.toQueryString());
		
		url.append(LogUrl.LEVEL, 1);
		
		assertEquals("hey?l=1",url.toString());
		assertEquals("l=1",url.toQueryString());
		
		url.addMissingParameters();
		assertEquals("hey?l=1&m=&s=&t=&n=&w=",url.toString());
		//assertEquals("hey?l=1&m=&s=&t=&n=&w=",url.toString());
		assertEquals("l=1&m=&s=&t=&n=&w=",url.toQueryString());
		
		url = new LogUrl("hey?", true);
		url.append(LogUrl.MESSAGE, "mes");
		
		assertEquals("hey?m=mes",url.toString());
		assertEquals("m=mes",url.toQueryString());
		
		url.addMissingParameters();
		assertEquals("hey?m=mes&l=0&s=&t=&n=&w=",url.toString());
		assertEquals("m=mes&l=0&s=&t=&n=&w=",url.toQueryString());
		
		
		url = new LogUrl("hey?", true);
		url.append(LogUrl.NAME, "name");
		
		assertEquals("hey?n=name",url.toString());
		assertEquals("n=name",url.toQueryString());
		
		url.addMissingParameters();
		assertEquals("hey?n=name&l=0&m=&s=&t=&w=",url.toString());
		assertEquals("n=name&l=0&m=&s=&t=&w=",url.toQueryString());
		
		url = new LogUrl("hey?", true);
		url.append(LogUrl.STACK, "stack");
		
		assertEquals("hey?s=stack",url.toString());
		assertEquals("s=stack",url.toQueryString());
		
		url.addMissingParameters();
		assertEquals("hey?s=stack&l=0&m=&t=&n=&w=",url.toString());
		assertEquals("s=stack&l=0&m=&t=&n=&w=",url.toQueryString());
		
		url = new LogUrl("hey?", true);
		url.append(LogUrl.THREAD, "tred");
		
		assertEquals("hey?t=tred",url.toString());
		assertEquals("t=tred",url.toQueryString());
		
		url.addMissingParameters();
		assertEquals("hey?t=tred&l=0&m=&s=&n=&w=",url.toString());
		assertEquals("t=tred&l=0&m=&s=&n=&w=",url.toQueryString());
		
		url = new LogUrl("hey?", true);
		url.append(LogUrl.TIME, "time");
		
		assertEquals("hey?ms=time",url.toString());
		assertEquals("ms=time",url.toQueryString());
		
		url.addMissingParameters();
		assertEquals("hey?ms=time&l=0&m=&s=&t=&n=&w=",url.toString());
		assertEquals("ms=time&l=0&m=&s=&t=&n=&w=",url.toQueryString());
		
		url = new LogUrl("hey?", true);
		url.append(LogUrl.WINDOW_ID, "win");
		
		assertEquals("hey?w=win",url.toString());
		assertEquals("w=win",url.toQueryString());
		
		url.addMissingParameters();
		assertEquals("hey?w=win&l=0&m=&s=&t=&n=",url.toString());
		assertEquals("w=win&l=0&m=&s=&t=&n=",url.toQueryString());
		
		
		url = new LogUrl("hey?", true);
		
		url.append(LogUrl.LEVEL, 1);
		url.append(LogUrl.MESSAGE, "mes");
		url.append(LogUrl.NAME, "name");
		url.append(LogUrl.STACK, "stack");
		url.append(LogUrl.THREAD, "tred");
		url.append(LogUrl.TIME, "time");
		url.append(LogUrl.WINDOW_ID, "win");
		
		assertEquals("hey?l=1&m=mes&n=name&s=stack&t=tred&ms=time&w=win",url.toString());
		assertEquals("l=1&m=mes&n=name&s=stack&t=tred&ms=time&w=win",url.toQueryString());
		
		url.addMissingParameters();
		
		assertEquals("hey?l=1&m=mes&n=name&s=stack&t=tred&ms=time&w=win",url.toString());
		assertEquals("l=1&m=mes&n=name&s=stack&t=tred&ms=time&w=win",url.toQueryString());
	}
}
