package org.risk.model;

import static org.junit.Assert.*;

import java.io.File;

import junit.framework.TestCase;

import org.junit.Test;
import org.risk.model.Utility.*;
/**
 * Test class for MapSaver Class which will test whether map object is being loaded
 * @author Omer
 *
 */
public class MapLoaderTest extends TestCase{

	/**
	 * Test case to convert the file in to xml
	 */
	@Test
	public void testConvertingXml() {
		
		String testMessage=null;
		assertNull(testMessage);
		//MapLoader testMapLoader = new MapLoader();
		File file = null;
		boolean validateRequired=false;
		
		try{
		XmlHelper.convertFromXml(file);
		}catch (Exception e){
			testMessage = e.getMessage();
			System.out.println(e.getMessage());
		}
		assertNotNull(testMessage);
		assertEquals("file parameter must not be null", testMessage);
	}

}
