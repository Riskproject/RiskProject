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
/**
 * Test class for MapSaver Class which will test whether map object is being saved
 * 
 *
 */
public class MapSaverTest extends TestCase {

	/**
	 * Testcase for testing saveMap method which will save the map object
	 */
	@Test
	public void testSaveMap() {
		Map testMap;
		testMap = Map.getInstance();
		
		MapSaver testMapSaver = new MapSaver(); 
				
		boolean testSaveFile = testMapSaver.saveMap("C:'\'abc.xml", testMap);
		
		assertEquals(true, testSaveFile);
	}
	
//	@Test
//	public void testSaveNullObject(){
//		Map testMap=null;
//		String message=null;
//		//assertNull(testMap);
//		//assertNull(message);
//		MapSaver testMapSaver = new MapSaver(); 
//		try{
//			boolean result = testMapSaver.saveMap("C:'\'xyz.xml", testMap);
//			
//			//assertFalse(result);
//			
//			//assertEquals(false, result);
//			System.out.println(result);
//		}catch (Exception e){
//			//message = e.getMessage();
//			//System.out.println(e.getMessage());
//		}
//		
//		
//	}
}
