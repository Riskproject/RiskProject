package org.risk.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.*;

import org.junit.Test;

public class ResourceTest extends TestCase {

	@Test
	public void testGetResourceList() {
		ArrayList<Resource> testResourceList = new ArrayList<Resource>();
		Resource testResource = new Resource();
		testResourceList =  testResource.getResourceList();
		
		assertEquals("metal", testResourceList.get(1).resourceName().toLowerCase());
		assertEquals(4, testResourceList.size());
	}

}
