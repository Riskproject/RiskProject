/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.risk.model;

import static org.junit.Assert.*;

import java.io.File;

import junit.framework.TestCase;

import org.junit.Test;
import org.risk.model.Utility.*;
/**
 * Test class for MapSaver Class which will test whether map object is being loaded
 * 
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
